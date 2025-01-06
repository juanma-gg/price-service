package com.ecommerce.price_service.model.repository;

import java.util.List;

import com.ecommerce.price_service.model.repository.entity.Price;
import com.ecommerce.price_service.model.repository.service.filters.PriceFilterParameters;

public interface PriceRepository {

    List<Price> priceFilterParameters(PriceFilterParameters priceFilterParameters);

}
