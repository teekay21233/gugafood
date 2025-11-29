package com.gugafood.gugafood.infrastructure.repository.spec;

import com.gugafood.gugafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> noDeliveryFee(){
        return ((root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO));
    }

    public static Specification<Restaurant> similarName(String name) {
        return ((root, query, builder) -> builder.like(root.get("name"),"%" + name + "%"));
    }
}
