package org.alexey.rentflatsservice.transformer.impl;

import org.alexey.rentflatsservice.core.dto.FlatInfoDto;
import org.alexey.rentflatsservice.core.dto.FlatWriteDto;
import org.alexey.rentflatsservice.core.entity.Flat;
import org.alexey.rentflatsservice.transformer.FlatTransformer;
import org.springframework.stereotype.Component;

@Component
public class FlatTransformerImpl implements FlatTransformer {

    @Override
    public FlatInfoDto transformFlatInfoDtoFromEntity(Flat flat) {
        return new FlatInfoDto().setId(flat.getId())
                .setCreationDate(flat.getCreationDate())
                .setUpdatedDate(flat.getUpdateDate())
                .setOfferType(flat.getOfferType())
                .setDescription(flat.getDescription())
                .setBedrooms(flat.getBedrooms())
                .setArea(flat.getArea())
                .setPrice(flat.getPrice())
                .setFloor(flat.getFloor())
                .setPhotoUrls(flat.getPhotoUrls())
                .setOriginalUrl(flat.getOriginalUrl());
    }

    @Override
    public Flat transformEntityFromFlatWriteDto(FlatWriteDto flatWriteDto) {
        return new Flat().setOfferType(flatWriteDto.getOfferType())
                .setDescription(flatWriteDto.getDescription())
                .setBedrooms(flatWriteDto.getBedrooms())
                .setArea(flatWriteDto.getArea())
                .setPrice(flatWriteDto.getPrice())
                .setFloor(flatWriteDto.getFloor())
                .setPhotoUrls(flatWriteDto.getPhotoUrls())
                .setOriginalUrl(flatWriteDto.getOriginalUrl());
    }
}
