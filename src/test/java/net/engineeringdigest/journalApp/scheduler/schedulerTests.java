package net.engineeringdigest.journalApp.scheduler;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class schedulerTests {

    @Autowired
    private UserSchedular userSchedular;

    @Disabled
    @Test
    public void UserSchedulerTesting(){
        userSchedular.fetchUserAndSendSaMail();
    }
}
