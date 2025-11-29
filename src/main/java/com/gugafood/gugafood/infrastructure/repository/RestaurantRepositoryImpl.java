package com.gugafood.gugafood.infrastructure.repository;

import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import com.gugafood.gugafood.domain.repository.RestaurantRepositoryQueries;
import com.gugafood.gugafood.infrastructure.repository.spec.RestaurantSpecs;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.gugafood.gugafood.infrastructure.repository.spec.RestaurantSpecs.noDeliveryFee;
import static com.gugafood.gugafood.infrastructure.repository.spec.RestaurantSpecs.similarName;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String name, BigDecimal firstFee, BigDecimal lastFee){

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
         Root<Restaurant> root = criteria.from(Restaurant.class);

         var predicates = new ArrayList<Predicate>();

         if (StringUtils.hasLength(name)){
             predicates.add(builder.like(root.get("name"), "%" + name + "%"));
         }

         if (firstFee != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), firstFee));
         }

         if (lastFee != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"),lastFee));
         }

         criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();

//        var jpql = new StringBuilder();
//        jpql.append("from Restaurant where 0 = 0 ");

//        var parameters = new HashMap<String,Object>();
//
//        if (StringUtils.hasLength(name)){
//            jpql.append("and name like :name ");
//            parameters.put("name", "%" + name + "%");
//        }
//
//        if (firstFee != null){
//            jpql.append("and taxaFrete >= :firstFee ");
//            parameters.put("firstFee", firstFee);
//        }
//
//        if (lastFee != null){
//            jpql.append("and taxaFrete <= :lastFee ");
//            parameters.put("lastFee", lastFee);
//        }
//
//        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
//
//        parameters.forEach((key,value) -> query.setParameter(key,value));

//        return query.getResultList();
    }

    @Override
    public List<Restaurant> findNoDeliveryFee(String name) {
        return restaurantRepository.findAll(noDeliveryFee().and(similarName(name)));
    }

}
