package com.user.service.external.feign;

import com.user.service.model.Hotel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {

//    @CircuitBreaker(name = "serviceBreaker", fallbackMethod = "fallbackMethod")
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

//    default Hotel fallbackMethod(String id, RuntimeException runtimeException){
//        return Hotel.builder()
//                .hotelId("0")
//                .name("default")
//                .description("This is default response. Server Down! ")
//                .location("default")
//                .build();
//    }
}
