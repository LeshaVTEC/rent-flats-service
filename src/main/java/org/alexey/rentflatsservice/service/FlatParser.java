package org.alexey.rentflatsservice.service;

import org.alexey.rentflatsservice.core.entity.Flat;
import org.alexey.rentflatsservice.core.entity.FlatWebSite;
import org.jsoup.nodes.Element;

import java.util.List;

public interface FlatParser {

    FlatWebSite getType();

    List<Flat> parseFlats(Element content);

    String parseNextPageLink(Element content);
}
