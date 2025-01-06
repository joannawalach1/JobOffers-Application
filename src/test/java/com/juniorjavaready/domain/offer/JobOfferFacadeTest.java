package com.juniorjavaready.domain.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.juniorjavaready.infrastructure.offer.http.JobOffersFetcher;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class JobOfferFacadeTest {
    private static final String BASE_URL = "http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers";
    private final JobOffersFetcher jobOffersFetcher = new JobOffersFetcher(new RestTemplate());
    private final JobOfferGenerator generator = new JobOfferGenerator();
    private final InMemoryJobOfferRepository repository = new InMemoryJobOfferRepository();
    private final JobOfferIdGenerator idGenerator = new JobOfferIdGenerator();
    private final JobOfferFacade facade = new JobOfferFacade(
            repository,
            generator,
            idGenerator
    );

    @Test
    void save() throws JsonProcessingException {
        // given
        List<JobOffer> jobOffers = List.of(
                new JobOffer(1, "Developer", "Remote", "4000", "url1"),
                new JobOffer(2, "Developer", "Remote", "4000", "url2"));
        // when
        List<JobOffer> fetchedOffers = facade.saveJob();

        // then
        assertNotNull(fetchedOffers);
    }

    @Test
    void find() throws JobOfferNotFoundException {
        // given
        JobOffer jobOffer = new JobOffer(1,
                "BlueSoft Sp. z o.o.",
                "Junior Java Developer",
                "7 000 – 9 000 PLN",
                "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre");

        // when
        Optional<JobOffer> foundOffer = facade.findByTitle(jobOffer.title());

        // then
        assertEquals("Junior Java Developer", jobOffer.title());
    }

    @Test
    void findAll() {
        // given
        JobOffer jobOffer = new JobOffer(1,
                "BlueSoft Sp. z o.o.",
                "Junior Java Developer",
                "7 000 – 9 000 PLN",
                "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre");

        // when
        List<JobOffer> allOffers = facade.findAllOffers();

        // then
        assertEquals("Junior Java Developer", allOffers.get(1).title());
    }



}