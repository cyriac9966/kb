package com.effanga.weatherApp.biz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Repository
public class Geo {
    @Id
    @GeneratedValue
    private Long id;
    private double lon;
    private double lat;

}