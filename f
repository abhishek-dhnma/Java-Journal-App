[1mdiff --git a/src/main/java/net/engineeringdigest/journalApp/controller/HomeController.java b/src/main/java/net/engineeringdigest/journalApp/controller/HomeController.java[m
[1mindex 1b7fb54..7a913e9 100644[m
[1m--- a/src/main/java/net/engineeringdigest/journalApp/controller/HomeController.java[m
[1m+++ b/src/main/java/net/engineeringdigest/journalApp/controller/HomeController.java[m
[36m@@ -1,9 +1,11 @@[m
 package net.engineeringdigest.journalApp.controller;[m
 [m
 import org.springframework.web.bind.annotation.GetMapping;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.RequestMapping;[m
 import org.springframework.web.bind.annotation.RestController;[m
 [m
 @RestController[m
[32m+[m[32m@RequestMapping("/check-status")[m
 public class HomeController {[m
     @GetMapping("/")[m
     public String home() {[m
[1mdiff --git a/src/main/java/net/engineeringdigest/journalApp/controller/UserController.java b/src/main/java/net/engineeringdigest/journalApp/controller/UserController.java[m
[1mindex 04c95fc..4a86a31 100644[m
[1m--- a/src/main/java/net/engineeringdigest/journalApp/controller/UserController.java[m
[1m+++ b/src/main/java/net/engineeringdigest/journalApp/controller/UserController.java[m
[36m@@ -68,12 +68,13 @@[m [mpublic class UserController {[m
     @GetMapping[m
     public ResponseEntity<?> greeting() {[m
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();[m
[31m-        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");[m
[32m+[m[32m        String city =  "Mumbai";[m
[32m+[m[32m        WeatherResponse weatherResponse = weatherService.getWeather(city);[m
         String greeting = "";[m
         if (weatherResponse != null && weatherResponse.getCurrent() != null) {[m
             greeting = " , Weather feels like " + weatherResponse.getCurrent().getTemperature();[m
         }[m
[31m-        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);[m
[32m+[m[32m        return new ResponseEntity<>("Hi " + authentication.getName() + greeting + " in " + city, HttpStatus.OK);[m
     }[m
 [m
 [m
