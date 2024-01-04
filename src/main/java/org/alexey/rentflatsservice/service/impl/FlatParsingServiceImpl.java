package org.alexey.rentflatsservice.service.impl;

import org.alexey.rentflatsservice.core.entity.Flat;
import org.alexey.rentflatsservice.core.entity.FlatWebSite;
import org.alexey.rentflatsservice.repository.FlatRepository;
import org.alexey.rentflatsservice.service.ContentRequester;
import org.alexey.rentflatsservice.service.FlatParser;
import org.alexey.rentflatsservice.service.FlatParsingService;
import org.alexey.rentflatsservice.service.factory.RentContentRequesterFactory;
import org.alexey.rentflatsservice.service.factory.RentFlatParserFactory;
import org.alexey.rentflatsservice.service.factory.SaleContentRequesterFactory;
import org.alexey.rentflatsservice.service.factory.SaleFlatParserFactory;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class FlatParsingServiceImpl implements FlatParsingService {

    @Value("${custom.rent-web-sites.pages-limit}")
    private Integer pagesLimit;

    private final FlatRepository flatRepository;
    private final RentFlatParserFactory rentFlatParserFactory;
    private final SaleFlatParserFactory saleFlatParserFactory;
    private final RentContentRequesterFactory rentContentRequesterFactory;
    private final SaleContentRequesterFactory saleContentRequesterFactory;

    public FlatParsingServiceImpl(FlatRepository flatRepository,
                                  RentFlatParserFactory rentFlatParserFactory,
                                  SaleFlatParserFactory saleFlatParserFactory,
                                  RentContentRequesterFactory rentContentRequesterFactory,
                                  SaleContentRequesterFactory saleContentRequesterFactory) {
        this.flatRepository = flatRepository;
        this.rentFlatParserFactory = rentFlatParserFactory;
        this.saleFlatParserFactory = saleFlatParserFactory;
        this.rentContentRequesterFactory = rentContentRequesterFactory;
        this.saleContentRequesterFactory = saleContentRequesterFactory;
    }

    @Async
    @Override
    public void saveRentFlatsFromParsing() {
        Arrays.stream(FlatWebSite.values()).forEach(type -> requestAndSaveFlats(
                rentContentRequesterFactory.getByType(type),
                rentFlatParserFactory.getByType(type)
        ));
    }

    @Async
    @Override
    public void saveSaleFlatsFromParsing() {
        Arrays.stream(FlatWebSite.values()).forEach(type -> requestAndSaveFlats(
                saleContentRequesterFactory.getByType(type),
                saleFlatParserFactory.getByType(type)
        ));
    }

    private void requestAndSaveFlats(ContentRequester contentRequester, FlatParser flatParser) {
        int i = 0;
        String nextPageUrl = null;
        while (i < pagesLimit) {
            Element rentContent = contentRequester.getHtmlDocument(nextPageUrl);
            List<Flat> flats = flatParser.parseFlats(rentContent);
            flatRepository.saveAll(flats);
            nextPageUrl = flatParser.parseNextPageLink(rentContent);
            i++;
        }
    }
}
