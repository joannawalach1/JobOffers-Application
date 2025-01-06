package com.juniorjavaready.domain.offer;

public class JobOfferNotFoundException extends RuntimeException {
    public JobOfferNotFoundException(String message) {
        super(message);
    }
}
