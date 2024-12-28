package com.juniorjavaready.domain.offer;

import lombok.Builder;

@Builder
public record JobOffer(String company, String position, String Salary, String offerUrl
) {
}
