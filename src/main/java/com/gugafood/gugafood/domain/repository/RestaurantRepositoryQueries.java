package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal firstFee, BigDecimal lastFee);

    List<Restaurant> findNoDeliveryFee(String name);
}
