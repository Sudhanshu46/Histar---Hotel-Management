package com.rating.service.service.impl;

import com.rating.service.model.Rating;
import com.rating.service.repository.RatingRepository;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public Rating addRating(Rating rating) {
        Rating rate = ratingRepository.save(rating);
        kafkaTemplate.send("test-topic", rating.toString());
        return rate;
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {

        return ratingRepository.findByHotelId(hotelId);
    }
}
