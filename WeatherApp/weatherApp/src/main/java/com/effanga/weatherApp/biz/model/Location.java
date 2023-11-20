package com.effanga.weatherApp.biz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
//    private Long id;
    private String name;
//    private String main;
    private Double temp;
    private Double minTemp;
    private Double maxTemp;
}
