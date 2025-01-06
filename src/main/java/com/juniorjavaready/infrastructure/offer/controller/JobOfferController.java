package com.juniorjavaready.infrastructure.offer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.juniorjavaready.domain.offer.JobOffer;
import com.juniorjavaready.domain.offer.JobOfferFacade;
import com.juniorjavaready.domain.offer.JobOfferMapper;
import com.juniorjavaready.domain.offer.JobOfferNotFoundException;
import com.juniorjavaready.domain.offer.dto.JobOfferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobOffers")
public class JobOfferController {

    private final JobOfferFacade jobOfferFacade;

    public JobOfferController(JobOfferFacade jobOfferFacade) {
        this.jobOfferFacade = jobOfferFacade;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobOfferDto>> getAllJobOffers() {
        List<JobOffer> jobOfferList = jobOfferFacade.findAllOffers();
        List<JobOfferDto> jobOfferDtoList = JobOfferMapper.toDtos(jobOfferList);
        return ResponseEntity.status(HttpStatus.OK).body(jobOfferDtoList);
    }

    @GetMapping("/{title}")
    public ResponseEntity<JobOfferDto> getJobOfferByTitle(@PathVariable String title) throws JobOfferNotFoundException {
        Optional<JobOffer> jobOffer = jobOfferFacade.findByTitle(title);
        if (jobOffer.isPresent()) {
            JobOfferDto jobOfferDto = JobOfferMapper.toDto(jobOffer.get());
            return ResponseEntity.status(HttpStatus.OK).body(jobOfferDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/save")
    public ResponseEntity<List<JobOfferDto>> saveJobOffers() throws JsonProcessingException {
        List<JobOffer> savedJobOffers = jobOfferFacade.saveJob();

        if (savedJobOffers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }

        List<JobOfferDto> jobOfferDtoList = JobOfferMapper.toDtos(savedJobOffers);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOfferDtoList);
    }
}
