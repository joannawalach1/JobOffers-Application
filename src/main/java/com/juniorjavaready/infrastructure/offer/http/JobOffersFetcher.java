package com.juniorjavaready.infrastructure.offer.http;

import org.springframework.web.client.RestTemplate;

public class JobOffersFetcher {
    private final RestTemplate restTemplate;

    public JobOffersFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchApiData(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
