package com.juniorjavaready.domain.login;

import lombok.Builder;

@Builder
public record User(String login, String password) {
}
