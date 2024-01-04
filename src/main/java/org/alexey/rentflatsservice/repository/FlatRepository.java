package org.alexey.rentflatsservice.repository;

import org.alexey.rentflatsservice.core.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlatRepository extends JpaRepository<Flat, UUID>, FlatRepositoryWithFilters {
}
