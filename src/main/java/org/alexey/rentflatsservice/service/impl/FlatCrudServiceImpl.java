package org.alexey.rentflatsservice.service.impl;

import org.alexey.rentflatsservice.core.dto.FlatFilter;
import org.alexey.rentflatsservice.core.dto.FlatInfoDto;
import org.alexey.rentflatsservice.core.dto.FlatWriteDto;
import org.alexey.rentflatsservice.core.entity.Flat;
import org.alexey.rentflatsservice.repository.FlatRepository;
import org.alexey.rentflatsservice.service.FlatCrudService;
import org.alexey.rentflatsservice.transformer.FlatTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlatCrudServiceImpl implements FlatCrudService {

    private final FlatRepository flatRepository;
    private final FlatTransformer flatTransformer;

    public FlatCrudServiceImpl(
            FlatRepository flatRepository,
            FlatTransformer flatTransformer
    ) {
        this.flatRepository = flatRepository;
        this.flatTransformer = flatTransformer;
    }

    @Override
    public void createFlat(FlatWriteDto flatWriteDto) {
        flatRepository.save(flatTransformer.transformEntityFromFlatWriteDto(flatWriteDto));
    }

    @Override
    public Page<FlatInfoDto> getAllFlats(FlatFilter flatFilter, Pageable pageable) {
        Page<Flat> pageEntity = flatRepository.getFlatsWithFilters(flatFilter, pageable);
        List<FlatInfoDto> auditDtoList = pageEntity.stream()
                .map(flatTransformer::transformFlatInfoDtoFromEntity)
                .toList();
        return new PageImpl<FlatInfoDto>(auditDtoList, pageable, pageEntity.getTotalElements());
    }
}
