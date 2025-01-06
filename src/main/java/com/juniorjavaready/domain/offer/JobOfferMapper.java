package com.juniorjavaready.domain.offer;

import com.juniorjavaready.domain.offer.dto.JobOfferDto;

import java.util.List;
import java.util.stream.Collectors;

public class JobOfferMapper {
        public static JobOfferDto toDto(JobOffer jobOffer) {
            return JobOfferDto.builder()
                    .id(jobOffer.id())
                    .company(jobOffer.company())
                    .position(jobOffer.title())
                    .salary(jobOffer.salary())
                    .offerUrl(jobOffer.offerUrl())
                    .build();
        }

        public static JobOffer ToEntity(JobOfferDto jobOfferDto) {
            return JobOffer.builder()
                    .id(jobOfferDto.id())
                    .company(jobOfferDto.company())
                    .title(jobOfferDto.position())
                    .salary(jobOfferDto.salary())
                    .offerUrl(jobOfferDto.offerUrl())
                    .build();
        }

    public static List<JobOfferDto> toDtos(List<JobOffer> jobOffers) {
        return jobOffers.stream()
                .map(JobOfferMapper::toDto)
                .collect(Collectors.toList());
    }
}
