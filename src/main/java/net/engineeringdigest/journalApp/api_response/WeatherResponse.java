package net.engineeringdigest.journalApp.api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse{

    private Current current;

    @Setter
    @Getter
    public class Current{

        private int temperature;

        @JsonProperty("weather_icons")
        private ArrayList<String> weatherIcons;

        @JsonProperty("wind_speed")
        private int windSpeed;

    }



}


