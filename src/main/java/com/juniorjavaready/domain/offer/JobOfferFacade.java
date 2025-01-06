package com.juniorjavaready.domain.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JobOfferFacade {
    private static final Logger logger = LoggerFactory.getLogger(JobOfferFacade.class);
    private final JobOfferRepository jobOfferRepository;
    private final JobOfferGenerator jobOfferGenerator;
    private final JobOfferIdGenerator jobOfferIdGenerator;

    public List<JobOffer> findAllOffers() {
        List<JobOffer> allJobOffers = jobOfferRepository.findAll();
        logger.info("API response: {}", allJobOffers);
        return allJobOffers;
    }

    public Optional<JobOffer> findByTitle(String title) throws JobOfferNotFoundException {
        return Optional.ofNullable(jobOfferRepository.findByTitle(title));
    }

    public List<JobOffer> saveJob() throws JsonProcessingException {
        List<JobOffer> generatedJobOffers = jobOfferGenerator.generateJobOffer();

        if (generatedJobOffers == null || generatedJobOffers.isEmpty()) {
            return Collections.emptyList();
        }

        List<JobOffer> savedJobOffers = new ArrayList<>();
        for (JobOffer jobOffer : generatedJobOffers) {
            int newId = JobOfferIdGenerator.getNextId();
            JobOffer jobOfferWithNewId = new JobOffer(newId, jobOffer.company(), jobOffer.title(), jobOffer.salary(), jobOffer.offerUrl());
            JobOffer savedJobOffer = jobOfferRepository.save(jobOfferWithNewId);
            savedJobOffers.add(savedJobOffer);
        }

        return savedJobOffers;
    }
}
