package com.ecommerce.price_service.model.repository.service.filters;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PriceFilterParameters {

    private Instant startDate;

    private Long brandId;

    private Long productId;

}
