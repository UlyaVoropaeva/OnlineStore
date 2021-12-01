package ru.gb.application.unit;


import org.junit.jupiter.api.Test;;
import org.mockito.Mockito;
import ru.gb.controller.RegistrationController;
import ru.gb.entity.User;
import ru.gb.service.UserService;


public class UsersClientTest {

    private User user = new User(1, "test@test.com", "12345", "test 1 ", "secondName");


    @Test
    void welcomeClientUnitTest() {
        RegistrationController registrationController = Mockito.mock(RegistrationController.class);
        Mockito.when(registrationController.welcome()).thenReturn("WELCOME");
    }

    @Test
    void userServiceUnitTest() {
        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.findByEmail(
                        Mockito.argThat(arg -> arg == null || arg.length() > 5)))
                .thenReturn(null);

    }
}
