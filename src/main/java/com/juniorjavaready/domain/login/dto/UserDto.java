package com.juniorjavaready.domain.login.dto;

import lombok.Builder;

@Builder
public record UserDto(String login, String password) {
}
