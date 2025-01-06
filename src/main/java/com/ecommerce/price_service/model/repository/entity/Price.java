package com.ecommerce.price_service.model.repository.entity;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Price {

    private Long id;

    private Long brandId;

    private Instant startDate;

    private Instant endDate;

    private String priceList;

    private Long productId;

    private Integer priority;

    private BigDecimal price;

    private String currency;

}
