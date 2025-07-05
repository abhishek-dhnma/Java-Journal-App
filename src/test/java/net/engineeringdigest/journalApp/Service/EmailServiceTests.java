package net.engineeringdigest.journalApp.Service;


import net.engineeringdigest.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("abhishek.v.dhiman@gmail.com",
                "Hello from Spring Boot",
                "This is a test email sent from Spring Boot app using Gmail SMTP");

    }

}
