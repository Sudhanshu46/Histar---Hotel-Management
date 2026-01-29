package com.rating.service.service;

import com.rating.service.model.Rating;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);
}
