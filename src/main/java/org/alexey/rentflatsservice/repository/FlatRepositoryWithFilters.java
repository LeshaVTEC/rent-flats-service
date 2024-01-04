package org.alexey.rentflatsservice.repository;

import org.alexey.rentflatsservice.core.dto.FlatFilter;
import org.alexey.rentflatsservice.core.entity.Flat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepositoryWithFilters {

    Page<Flat> getFlatsWithFilters(Pageable pageable);
    Page<Flat> getFlatsWithFilters(FlatFilter flatFilter, Pageable pageable);
}
