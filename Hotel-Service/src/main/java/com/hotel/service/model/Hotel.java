package com.hotel.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Hotels")
public class Hotel {

    @Id
    private String hotelId;
    private String name;
    private String location;
    private String description;
}
