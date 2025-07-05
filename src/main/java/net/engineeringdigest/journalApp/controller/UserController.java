package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api_response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    //crud operation
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {


        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            User userInDb = userService.findByUserName(userName);

            if (userInDb == null) {
                System.out.println("Username not found: " + userName);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String city =  "Mumbai";
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        String greeting = "";
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greeting = " , Weather feels like " + weatherResponse.getCurrent().getTemperature();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting + " in " + city, HttpStatus.OK);
    }


}
