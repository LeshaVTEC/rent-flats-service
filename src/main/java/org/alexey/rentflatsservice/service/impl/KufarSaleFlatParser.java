package org.alexey.rentflatsservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.alexey.rentflatsservice.core.entity.DeadFlat;
import org.alexey.rentflatsservice.core.entity.Flat;
import org.alexey.rentflatsservice.core.entity.FlatWebSite;
import org.alexey.rentflatsservice.service.DeadFlatService;
import org.alexey.rentflatsservice.service.SaleFlatParser;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.alexey.rentflatsservice.core.entity.OfferType.SALE;
import static org.alexey.rentflatsservice.util.KufarNextPageLinkParser.parseKufarNextPageLink;

@Slf4j
@Component
public class KufarSaleFlatParser implements SaleFlatParser {

    private final DeadFlatService deadFlatService;

    public KufarSaleFlatParser(DeadFlatService deadFlatService) {
        this.deadFlatService = deadFlatService;
    }

    @Override
    public FlatWebSite getType() {
        return FlatWebSite.KUFAR;
    }

    @Override
    public List<Flat> parseFlats(Element content) {
        List<Element> sections = content.child(0).child(3).child(0).children();
        return sections.stream().map(this::tryConvertToFlat).filter(it -> it != null).toList();
    }

    @Override
    public String parseNextPageLink(Element content) {
        return parseKufarNextPageLink(content);
    }

    private Flat tryConvertToFlat(Element section) {
        try {
            return convertToFlat(section);
        } catch (Exception exception){
            log.error("dead flat: " + exception);
            deadFlatService.saveDeadFlat(convertToDeadFlat(section, exception));
            return null;
        }
    }

    private Flat convertToFlat(Element section) {
        String subtitle = findSubtitle(section);
        return new Flat()
                .setDescription(findDescription(section))
                .setOriginalUrl(findOriginalUrl(section))
                .setPhotoUrls(findPhotoUrls(section))
                .setArea(findArea(subtitle))
                .setFloor(findFloor(subtitle))
                .setBedrooms(findRooms(subtitle))
                .setPrice(findPrice(section))
                .setOfferType(SALE);
    }

    private DeadFlat convertToDeadFlat(Element section, Exception exception){
        return new DeadFlat()
                .setOriginalUrl(findOriginalUrl(section))
                .setExceptionMessage(exception.getMessage())
                .setRawHtml(section.toString())
                .setOfferType(SALE);
    }

    private String findDescription(Element section) {
        return section
                .child(0)
                .child(1)
                .child(0)
                .child(1)
                .child(1)
                .child(0)
                .text();
    }

    private Integer findRooms(String subtitle) {
        String rooms = StringUtils.substringBefore(subtitle, " ");
        return rooms == null ? null : Integer.valueOf(rooms);
    }

    private Integer findArea(String subtitle) {
        String area = StringUtils.substringBetween(subtitle, " комн.,", " м²");
        return area == null ? null : Double.valueOf(area).intValue();
    }

    private Integer findFloor(String subtitle) {
        String floor = StringUtils.substringBetween(subtitle, "этаж ", " из");
        return floor == null ? null : Integer.valueOf(floor);
    }

    private String findSubtitle(Element section) {
        return section
                .child(0)
                .child(1)
                .child(0)
                .child(1)
                .child(0)
                .text();
    }

    private Integer findPrice(Element section) {
         String subtitleForPrice = section.child(0).child(1).child(0).child(0).text();
         String price = StringUtils.substringBetween(subtitleForPrice, "р.", "$");
         return Integer.valueOf(price.replace(" ", ""));
    }

    private String[] findPhotoUrls(Element section) {
        return section
                .child(0)
                .child(0)
                .child(0)
                .getElementsByTag("img")
                .stream()
                .map(it -> it.attr("src")).toArray(String[]::new);
    }

    private String findOriginalUrl(Element section) {
        return section
                .child(0)
                .attr("href");
    }
}
