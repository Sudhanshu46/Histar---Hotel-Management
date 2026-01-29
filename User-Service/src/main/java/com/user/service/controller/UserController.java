package com.user.service.controller;

import com.user.service.model.User;
import com.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "serviceBreaker", fallbackMethod = "fallbackMethod")
//    @Retry(name = "serviceRetry", fallbackMethod = "fallbackMethod")
    @RateLimiter(name = "serviceLimiter", fallbackMethod = "fallbackMethod")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
//    @CircuitBreaker(name = "serviceBreaker", fallbackMethod = "fallbackMethod")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    public ResponseEntity<User> fallbackMethod(String userId, RuntimeException runtimeException) {
        log.info("Oops! Something went wrong, please try again after some time!");
        User user = User.builder()
                            .userId("0")
                            .email("default@gmail.com")
                            .name("Default")
                            .about("Server Down")
                            .build();
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
