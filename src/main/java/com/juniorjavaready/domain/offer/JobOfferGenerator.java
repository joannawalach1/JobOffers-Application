package com.juniorjavaready.domain.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juniorjavaready.infrastructure.offer.http.JobOffersFetcher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class JobOfferGenerator {
    private final JobOffersFetcher jobOffersFetcher = new JobOffersFetcher(new RestTemplate());
    private static final Logger logger = LoggerFactory.getLogger(JobOfferGenerator.class);
    private static final String BASE_URL = "http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers";
    private static final AtomicInteger counter = new AtomicInteger(1);


    public List<JobOffer> generateJobOffer() throws JsonProcessingException {
        String responseData = jobOffersFetcher.fetchApiData(BASE_URL);
        logger.info("API response: {}", responseData);

        ObjectMapper objectMapper = new ObjectMapper();
        List<JobOffer> jobOfferList = objectMapper.readValue(responseData, new TypeReference<List<JobOffer>>() {
        });

        List<JobOffer> updatedJobOfferList = new ArrayList<>();
        for (JobOffer jobOffer : jobOfferList) {
            JobOffer updatedJobOffer = new JobOffer(counter.incrementAndGet(), jobOffer.company(), jobOffer.title(), jobOffer.salary(), jobOffer.offerUrl());
            updatedJobOfferList.add(updatedJobOffer);
        }
        logger.info("Generated JobOffers: {}", jobOfferList);
        return updatedJobOfferList;


    }
}
