package com.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String hotelId;
    private String name;
    private String location;
    private String description;
}
