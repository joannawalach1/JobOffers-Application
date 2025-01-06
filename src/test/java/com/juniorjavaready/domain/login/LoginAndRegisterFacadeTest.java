package com.juniorjavaready.domain.login;

import com.juniorjavaready.domain.login.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class LoginAndRegisterFacadeTest {

    private LoginAndRegisterRepository repository;
    private LoginAndRegisterFacade loginAndRegisterFacade;

    @BeforeEach
    void setUp() {
        repository = mock(LoginAndRegisterRepository.class);
        loginAndRegisterFacade = new LoginAndRegisterFacade(repository);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        // given
        UserDto userDto = new UserDto("John", "password123");
        User user = new User("John", "password123");
        User registeredUser = new User("John", "password123");

        when(repository.register(any(User.class))).thenReturn(registeredUser);

        // when
        UserDto result = loginAndRegisterFacade.register(userDto);

        // then
        assertNotNull(result);
        assertEquals("John", result.login());
        assertEquals("password123", result.password());
        verify(repository, times(1)).register(any(User.class));
    }

    @Test
    void shouldThrowExceptionIfUserAlreadyExists() {
        // given
        String existingLogin = "John";
        UserDto userDto = new UserDto("John", "password123");
        User existingUser = new User("John", "password123");

        when(repository.findByLogin(existingLogin)).thenReturn(existingUser);

        // when + then
        assertThrows(UserWithSuchLoginExists.class,
                () -> loginAndRegisterFacade.findByLogin(existingLogin),
                "Użytkownik o loginie 'John' został znaleziony.");
        verify(repository, times(1)).findByLogin(existingLogin);
    }


    @Test
    void shouldThrowExceptionIfUserNotFound() {
        // given
        String nonExistentLogin = "nonexistent";

        when(repository.findByLogin(nonExistentLogin)).thenReturn(null);

        // when + then
        assertThrows(UserNotFoundException.class,
                () -> loginAndRegisterFacade.findByLogin(nonExistentLogin),
                "Użytkownik o loginie 'nonexistent' nie został znaleziony.");
        verify(repository, times(1)).findByLogin(nonExistentLogin);
    }
}