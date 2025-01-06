package com.juniorjavaready.domain.login;

import com.juniorjavaready.domain.login.dto.UserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginAndRegisterFacade {

    private final LoginAndRegisterRepository loginAndRegisterRepository;

    public UserDto register(UserDto userDto) {
        User user = new User(userDto.login(), userDto.password());
        User registeredUser = loginAndRegisterRepository.register(user);
        return LoginAndRegisterMapper.toDto(registeredUser);
    }
    public UserDto findByLogin(String login) {
        User foundByLogin = loginAndRegisterRepository.findByLogin(login);
        if (foundByLogin == null) {
            throw new UserNotFoundException("Użytkownik o loginie '" + login + "' nie został znaleziony.");
        }
        if (foundByLogin != null) {
            throw new UserWithSuchLoginExists("Użytkownik o loginie '" + login + "' został znaleziony.");
        }
        return LoginAndRegisterMapper.toDto(foundByLogin);
    }

}
