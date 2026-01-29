package com.hotel.service.controller;

import com.hotel.service.model.Hotel;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(hotelService.getAllHotels());
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

}
