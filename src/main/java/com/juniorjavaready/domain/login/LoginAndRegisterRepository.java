package com.juniorjavaready.domain.login;

public interface LoginAndRegisterRepository {
    User findByLogin(String login);
    User register(User user);
}
