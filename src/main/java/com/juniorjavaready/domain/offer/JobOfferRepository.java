package com.juniorjavaready.domain.offer;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, Integer> {
    @NotNull List<JobOffer> findAll();
    List<JobOffer> save(List<JobOffer> jobOffer);
    JobOffer findByTitle(String title);
}
