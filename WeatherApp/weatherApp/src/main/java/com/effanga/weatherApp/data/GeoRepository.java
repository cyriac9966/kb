package com.effanga.weatherApp.data;

import com.effanga.weatherApp.biz.model.Geo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends CrudRepository<Geo,Long> {
}
