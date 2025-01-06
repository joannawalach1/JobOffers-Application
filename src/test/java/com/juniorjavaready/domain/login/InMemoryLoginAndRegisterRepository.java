package com.juniorjavaready.domain.login;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InMemoryLoginAndRegisterRepository implements LoginAndRegisterRepository {
    List<User> userDatabase = new ArrayList<>();

    @Override
    public User findByLogin(String login) {
        return userDatabase.stream()
                .filter(user -> user.login().equals(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User register(User user) {
        userDatabase.add(user);
        return user;
    }
}
