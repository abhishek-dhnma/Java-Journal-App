package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableKafka
public class JournalApplication {

    public static void main(String[] args) {


        ConfigurableApplicationContext AppContext = SpringApplication.run(JournalApplication.class, args);
        ConfigurableEnvironment appContextEnvironment = AppContext.getEnvironment();
        //this will print which environment is current;y running dev/prod/sys etc
        System.out.println(appContextEnvironment.getActiveProfiles()[0]);


    }

    @Bean
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}

// PlatformTransactionManager -> ye to intertface h eski -> read down
// MongoTransactionManager -> implimentation karta hai

// MongoDatabaseFactory -> yehe be ek interface hai jo help karti hai mongodb se connection banane me