package org.alexey.rentflatsservice.service;

import org.alexey.rentflatsservice.core.dto.FlatFilter;
import org.alexey.rentflatsservice.core.dto.FlatInfoDto;
import org.alexey.rentflatsservice.core.dto.FlatWriteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlatCrudService {

    void createFlat(FlatWriteDto flatWriteDto);

    Page<FlatInfoDto> getAllFlats(FlatFilter flatFilter, Pageable pageable);
}
