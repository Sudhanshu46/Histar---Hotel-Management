package com.hotel.service.service.impl;

import com.hotel.service.model.Hotel;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;
import com.hotel.service.service.NotificationService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class HotelServicesImpl implements HotelService {

    private static final Logger log = LoggerFactory.getLogger(HotelServicesImpl.class);

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Hotel addHotel(Hotel hotel) {
        hotel.setHotelId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {

        return hotelRepository.findById(hotelId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    @Override
    @KafkaListener(topics = "test-topic", groupId = "consumer-group")
    public void getHotelRatings(String message) throws MessagingException, UnsupportedEncodingException {
        System.out.println("Your hotel has received rating as follows: ");
        System.out.println(message);
        notificationService.sendNotification(message);
    }


}
