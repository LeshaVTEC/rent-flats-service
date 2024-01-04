package org.alexey.rentflatsservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.alexey.rentflatsservice.core.entity.FlatWebSite;
import org.alexey.rentflatsservice.service.SaleContentRequester;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.alexey.rentflatsservice.util.DocumentRequester.requestHtmlDocument;

@Slf4j
@Component
public class KufarSaleContentRequester implements SaleContentRequester {

    @Value("${custom.rent-web-sites.kufar.base-url-sale}")
    private String baseUrl;

    @Override
    public FlatWebSite getType() {
        return FlatWebSite.KUFAR;
    }

    @Override
    public Element getHtmlDocument(String url) {
        return requestHtmlDocument(url == null ? baseUrl : url).getElementById("content");
    }
}
