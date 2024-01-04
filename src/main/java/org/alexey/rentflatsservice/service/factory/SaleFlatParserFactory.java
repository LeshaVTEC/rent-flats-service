package org.alexey.rentflatsservice.service.factory;

import org.alexey.rentflatsservice.core.entity.FlatWebSite;
import org.alexey.rentflatsservice.service.SaleFlatParser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class SaleFlatParserFactory {

    private final List<SaleFlatParser> saleFlatParsers;

    public SaleFlatParserFactory(List<SaleFlatParser> saleFlatParsers) {
        this.saleFlatParsers = saleFlatParsers;
    }

    public SaleFlatParser getByType(FlatWebSite type) {
        return saleFlatParsers.stream().filter(it -> it.getType() == type)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("sale parser not found by type " + type));
    }
}
