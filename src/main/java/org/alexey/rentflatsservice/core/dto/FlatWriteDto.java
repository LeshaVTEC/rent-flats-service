package org.alexey.rentflatsservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.alexey.rentflatsservice.core.entity.OfferType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class FlatWriteDto {

    private OfferType offerType;

    private String description;

    private Integer bedrooms;

    private Integer area;

    private Integer price;

    private Integer floor;

    private String[] photoUrls;

    private String originalUrl;
}
