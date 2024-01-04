package org.alexey.rentflatsservice.service.factory;

import jakarta.annotation.PostConstruct;
import org.alexey.rentflatsservice.core.entity.FlatWebSite;
import org.alexey.rentflatsservice.service.RentContentRequester;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public final class RentContentRequesterFactory {

    private final List<RentContentRequester> rentContentRequesters;

    private Map<FlatWebSite, RentContentRequester> rentContentRequestersByType;

    public RentContentRequesterFactory(List<RentContentRequester> rentContentRequesters) {
        this.rentContentRequesters = rentContentRequesters;
    }

    @PostConstruct
    private void prepareMap() {
        rentContentRequestersByType = rentContentRequesters
                .stream()
                .collect(
                        Collectors.toMap(RentContentRequester::getType, Function.identity())
                );
    }

    public RentContentRequester getByType(FlatWebSite type) {
        RentContentRequester rentContentRequester = rentContentRequestersByType.get(type);
        if (rentContentRequester != null) {
            return rentContentRequester;
        } else {
            throw new RuntimeException("rent content requester not found by type " + type);
        }
    }
}
