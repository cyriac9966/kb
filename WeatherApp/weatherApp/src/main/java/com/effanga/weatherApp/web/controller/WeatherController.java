package com.effanga.weatherApp.web.controller;

import com.effanga.weatherApp.biz.model.Geo;
import com.effanga.weatherApp.biz.model.Location;
import com.effanga.weatherApp.biz.service.WeatherService;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;
    private Location location;
    Geo theGeo = new Geo();
    Location lct = new Location("X",0d,0d,0d);


    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @ModelAttribute("geo")
    public Geo geo(){
        return new Geo();
    }

    @ModelAttribute("lct")
    public Location lct(){
        return lct;
    }


    @GetMapping
    public String getWeatherPage(){
        return "weather";
    }

    @PostMapping(params = "action=back")
    public String getBack(){
        return "redirect:weather";
    }

    @PostMapping(params = "action=find")
    public String saveGeo(Geo geo){
        weatherService.save(geo);
        theGeo.setLat(geo.getLat());
        theGeo.setLon(geo.getLon());
//        log.info(theGeo.getLat()+" LATITUDE "+ theGeo.getLon()+"LONGITUDE");
        extracted(theGeo);
        return "realweather";
    }




    private void extracted(Geo theGeo ) {

         String link = "https://api.openweathermap.org/data/2.5/weather?lat="
                +theGeo.getLat()+"&lon="
                +theGeo.getLon()+"&appid=463d5ac617c3747461485d42e01a221e&units=metric";
         String name;
         Double temp;
         Double tempMax;
         Double tempMin;
        try {
            String strJson = weatherService.getJSONFROMURL(link);
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(strJson);
            JSONObject mainJsonObject = (JSONObject) object;

            Object main = jsonParser.parse(mainJsonObject.get("main").toString());
            JSONObject mainTemp = (JSONObject) main;
            name = (mainJsonObject.get("name")).toString();
            temp = (Double) mainTemp.get("temp");
            tempMax = (Double) mainTemp.get("temp_max");
            tempMin = (Double) mainTemp.get("temp_min");
//            log.info("\n"+name +"\nMAXIMÁLNI TEPLOTA = "+ tempMax+"\nMINIMÁLNÍ TEPLOTA = "+tempMin+"\nTEPLOTA = "+temp);
    } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lct.setName(name);
        lct.setTemp(temp);
        lct.setMaxTemp(tempMax);
        lct.setMinTemp(tempMin);
    }
}


