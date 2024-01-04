package org.alexey.rentflatsservice.transformer;

import org.alexey.rentflatsservice.core.dto.FlatInfoDto;
import org.alexey.rentflatsservice.core.dto.FlatWriteDto;
import org.alexey.rentflatsservice.core.entity.Flat;

public interface FlatTransformer {

    FlatInfoDto transformFlatInfoDtoFromEntity(Flat flat);

    Flat transformEntityFromFlatWriteDto(FlatWriteDto flatInfoDto);
}
