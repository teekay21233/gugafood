package com.gugafood.gugafood.api.controller;

import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/kitchens/by-name")
    public List<Kitchen> listKitchenByName(@RequestParam("name") String name){
        return kitchenRepository.findByNameContaining(name);
    }

    @GetMapping("/restaurants/by-fee")
    public List<Restaurant> listRestaurantByFee(BigDecimal firstFee, BigDecimal lastFee){
        return restaurantRepository.findByTaxaFreteBetween(firstFee,lastFee);
    }

    @GetMapping("/restaurant/por-nome-e-frete")
    public List<Restaurant> porNomeEFrete(String name, BigDecimal firstFee, BigDecimal lastFee){
        return restaurantRepository.find(name,firstFee,lastFee);
    }

    @GetMapping("/restaurants/first-by-name")
    public Optional<Restaurant> findFirstByName(String name){
        return restaurantRepository.findFirstByNameContaining(name);
    }

    @GetMapping("/restaurants/top-2-by-name")
    public List<Restaurant> findTop2ByName(String name){
        return restaurantRepository.findTop2ByNameContaining(name);
    }

    @GetMapping("/kitchen/exists")
    public boolean exists(String name){
        return kitchenRepository.existsByName(name);
    }

    @GetMapping("/restaurant/count-by-kitchen")
    public int restaurantCountByKitchenId(Long kitchenId){
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @GetMapping("/restaurant/por-nome")
    public List<Restaurant> consultarPorNome(String name, Long id){
        return restaurantRepository.consultarPorNome(name,id);
    }

    @GetMapping("/restaurant/frete-gratis")
    public List<Restaurant> noDeliveryFee(String name){

        return restaurantRepository.findNoDeliveryFee(name);
    }

    @GetMapping("restaurant/first")
    public Optional<Restaurant> findFirst(){
        return restaurantRepository.findFirst();
    }

    @GetMapping("kitchen/first")
    public Optional<Kitchen> findFirstKitchen(){
        return kitchenRepository.findFirst();
    }
}
