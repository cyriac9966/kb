package com.effanga.weatherApp.biz.service;

import com.effanga.weatherApp.biz.model.Geo;
import com.effanga.weatherApp.data.GeoRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class WeatherService {
    private GeoRepository geoRepository;
    private Geo geo;



    public WeatherService(GeoRepository geoRepository,Geo geo) {
        this.geoRepository = geoRepository;
        this.geo=geo;

    }

    public Geo save(Geo geo){
        Geo saveGeo = geoRepository.save(geo);
        return saveGeo;
    }

    public Geo newGeo (Geo geo,int lat,int lon){
//        geo = new Geo(null,lat,lon);
        geo.setLat(lat);
        geo.setLon(lon);
        return geo;
    }

    public static String getJSONFROMURL(String link){
        String jsonText = "";
        try {
            URL url = new URL(link);
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line=bufferedReader.readLine())!=null){
                jsonText+=line+"\n";
            }
            inputStream.close();
            bufferedReader.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonText;
    }


}
