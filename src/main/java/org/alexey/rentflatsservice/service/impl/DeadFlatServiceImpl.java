package org.alexey.rentflatsservice.service.impl;

import org.alexey.rentflatsservice.core.entity.DeadFlat;
import org.alexey.rentflatsservice.repository.DeadFlatRepository;
import org.alexey.rentflatsservice.service.DeadFlatService;
import org.springframework.stereotype.Component;

@Component
public class DeadFlatServiceImpl implements DeadFlatService {

    private final DeadFlatRepository deadFlatRepository;

    public DeadFlatServiceImpl(DeadFlatRepository deadFlatRepository) {
        this.deadFlatRepository = deadFlatRepository;
    }

    @Override
    public void saveDeadFlat(DeadFlat deadFlat){
        deadFlatRepository.save(deadFlat);
    }
}
