package com.hotel.service.service;

import com.hotel.service.model.Hotel;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface HotelService {

    Hotel addHotel(Hotel hotel);

    Hotel getHotel(String hotelId);

    List<Hotel> getAllHotels();

    void deleteHotel(String hotelId);

    void getHotelRatings(String message) throws MessagingException, UnsupportedEncodingException;

}
