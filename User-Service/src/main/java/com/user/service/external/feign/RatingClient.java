package com.user.service.external.feign;

import com.user.service.model.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingClient {

//    @CircuitBreaker(name = "serviceBreaker", fallbackMethod = "fallbackMethod")
    @GetMapping("ratings/user/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);

//    default List<Rating> ratingBreaker(String id) {
//        return null;
//    }
}
