package org.alexey.rentflatsservice.service;

import org.alexey.rentflatsservice.core.entity.FlatWebSite;
import org.jsoup.nodes.Element;

public interface ContentRequester {

    FlatWebSite getType();

    Element getHtmlDocument(String url);
}
