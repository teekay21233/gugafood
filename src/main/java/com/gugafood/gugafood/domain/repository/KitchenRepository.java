package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen,Long> {
    List<Kitchen> findByNameContaining(String name);

    boolean existsByName(String name);
}
