package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.scheduler.UserSchedular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-mails")
public class SendMails {
    @Autowired
    private UserSchedular userSchedular;

    @GetMapping
    private ResponseEntity<?> sendMailing(){
        userSchedular.fetchUserAndSendSaMail();
        return new ResponseEntity<>("Send Mail Complete",HttpStatus.OK);
    }


}
