package com.user.service.service.impl;

import com.user.service.external.feign.HotelClient;
import com.user.service.external.feign.RatingClient;
import com.user.service.model.Hotel;
import com.user.service.model.Rating;
import com.user.service.model.User;
import com.user.service.repository.UserRepository;
import com.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelClient hotelClient;

    @Autowired
    private RatingClient ratingClient;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        List<Rating> ratings = ratingClient.getRatingsByUserId(userId);
        for(Rating rating : ratings){
            Hotel hotel = hotelClient.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        }
        user.setRatings(ratings);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for(User user : users){
            List<Rating> ratings = ratingClient.getRatingsByUserId(user.getUserId());
            for(Rating rating : ratings) {
                rating.setHotel(hotelClient.getHotel(rating.getHotelId()));
            }
            user.setRatings(ratings);
        }
        return users;
//        return users.stream().map(user -> {
//            List<Rating> ratings = ratingInterface.getRatingsByUserId(user.getUserId());
//            for(Rating rating : ratings) {
//                rating.setHotel(hotelInterface.getHotel(rating.getHotelId()));
//            }
//            user.setRatings(ratings);
//            return user;
//        }).collect(Collectors.toList());
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
