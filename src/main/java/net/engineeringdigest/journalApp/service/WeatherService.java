package net.engineeringdigest.journalApp.service;


import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.api_response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        String cacheKey = "weather_of_" + city;
        log.info("Checking Redis cache for key: {}", cacheKey);

        WeatherResponse weatherResponse = redisService.get(cacheKey, WeatherResponse.class);
        if (weatherResponse != null) {
            log.info("Weather data found in Redis for {}", city);
            return weatherResponse;
        }

        log.info("No cache found. Building API call for {}", city);

        String apiTemplate = appCache.APP_CACHE.get("weather_api");

        if (apiTemplate == null) {
            log.error("Weather API template is missing in AppCache.");
            throw new RuntimeException("Missing weather_api config");
        }

        String finalAPI = apiTemplate.replace("<city>", city).replace("<apiKey>", apiKey);

        log.info("Calling external API: {}", finalAPI);

        try {
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                    finalAPI, HttpMethod.GET, null, WeatherResponse.class
            );
            WeatherResponse body = response.getBody();
            if (body != null) {
                log.info("Received weather data from API. Saving to Redis.");
                redisService.set(cacheKey, body, 300L);
            } else {
                log.warn("API response body is null for city: {}", city);
            }
            return body;
        } catch (Exception e) {
            log.error("Exception while calling weather API: {}", e.getMessage(), e);
            return null;
        }
    }
}
