package org.alexey.rentflatsservice.repository;

import org.alexey.rentflatsservice.core.entity.DeadFlat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeadFlatRepository extends JpaRepository<DeadFlat, UUID> {
}
