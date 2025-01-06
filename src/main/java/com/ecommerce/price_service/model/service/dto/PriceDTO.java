package com.ecommerce.price_service.model.service.dto;

import java.math.BigDecimal;

import lombok.Setter;

@Setter
public class PriceDTO {

    public Long brandId;

    public String startDate;

    public String endDate;

    public String priceList;

    public Long productId;

    public BigDecimal price;

    public String currency;

}
