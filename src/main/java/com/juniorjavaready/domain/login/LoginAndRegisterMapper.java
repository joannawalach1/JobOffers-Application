package com.juniorjavaready.domain.login;

import com.juniorjavaready.domain.login.dto.UserDto;

public class LoginAndRegisterMapper {
    public static UserDto toDto(User user) {
      return UserDto.builder()
              .login(user.login())
              .password(user.password())
              .build();
    }

    public static User ToEntity(UserDto userDto) {
        return User.builder()
                .login(userDto.login())
                .password(userDto.password())
                .build();
    }
}
