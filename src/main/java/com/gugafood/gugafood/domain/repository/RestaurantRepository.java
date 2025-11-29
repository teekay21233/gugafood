package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

    List<Restaurant> findByTaxaFreteBetween(BigDecimal firstFee, BigDecimal lastFee);

//  List<Restaurant> findByNameContainingAndKitchenId(String name, Long id);

//  @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> consultarPorNome(String name, @Param("id") Long id);

    Optional<Restaurant> findFirstByNameContaining(String name);

    List<Restaurant> findTop2ByNameContaining(String name);

    int countByKitchenId(Long kitchenId);

}
