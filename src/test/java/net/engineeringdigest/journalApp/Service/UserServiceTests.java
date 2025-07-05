package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @ParameterizedTest(name = "Save new user: {0}")
    @ArgumentsSource(UserArgumentProviderImpl.class)
    public void saveNewUserTest(User user){
        assertTrue(userService.saveNewUser(user));
    }



    @Disabled
    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUserName("Ram");
        assertNotNull(user);
        assertTrue(user.getJournalEntries().isEmpty());
    }


    @Disabled
    @ParameterizedTest
    @ValueSource( strings = {
            "Abhishek",
            "Ram",
            "Aniket"
    })
    public void testFindByUserWithParameter( String name){
        User user = userRepository.findByUserName(name);
        assertNotNull(user);
        assertNotNull(user.getJournalEntries().isEmpty());

    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,3,5",
            "2,5,87"
    })
    public void learnParameterizedTesting(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}