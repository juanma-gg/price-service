package com.ecommerce.price_service.model.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.price_service.model.repository.entity.Price;
import com.ecommerce.price_service.model.repository.PriceRepository;
import com.ecommerce.price_service.model.repository.service.filters.PriceFilterParameters;

@Service
public class PricesService {

    private final PriceRepository priceRepository;

    @Autowired
    public PricesService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getPrices(PriceFilterParameters filterParameters) {
        return this.priceRepository.priceFilterParameters(filterParameters);
    }
}
