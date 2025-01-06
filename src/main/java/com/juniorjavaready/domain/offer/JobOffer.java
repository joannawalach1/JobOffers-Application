package com.juniorjavaready.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("job_offers")
public record JobOffer(
        @Id
        int id,
        String company,
        String title,
        String salary,
        String offerUrl
) {
}
