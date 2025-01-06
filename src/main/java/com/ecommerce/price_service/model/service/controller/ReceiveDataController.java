package com.ecommerce.price_service.model.service.controller;

import static com.ecommerce.price_service.model.Constants.ENDPOINT;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.price_service.model.repository.service.PricesService;
import com.ecommerce.price_service.model.repository.entity.Price;
import com.ecommerce.price_service.model.repository.service.filters.PriceFilterParameters;
import com.ecommerce.price_service.model.service.dto.PriceDTOList;
import com.ecommerce.price_service.model.service.mappers.PriceMapper;

@RestController
public class ReceiveDataController {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveDataController.class);

    private PricesService pricesService;
    private PriceMapper priceMapper;

    @Autowired
    public ReceiveDataController(PricesService pricesService, PriceMapper priceMapper) {
        this.pricesService = pricesService;
        this.priceMapper = priceMapper;
    }

    @GetMapping(ENDPOINT)
    public ResponseEntity<PriceDTOList> prices(
               @RequestParam(value = "appDate", required = false) Instant appDate,
               @RequestParam(value = "productId", required = false) Long productId,
               @RequestParam(value = "brandId", required = false) Long brandId)
    {
        try {
            List<Price> priceList = this.pricesService.getPrices(
                       PriceFilterParameters.builder()
                                  .startDate(appDate)
                                  .productId(productId)
                                  .brandId(brandId)
                                  .build()
            );
            if (priceList.isEmpty()) {
                logger.debug("Request not found with parameters: appDate={}, productId={}, brandId={}",
                           appDate, productId, brandId);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(priceMapper.mappingToDTO(priceList));
        } catch (RuntimeException ex) {
            logger.error("Error processing request with parameters: appDate={}, productId={}, brandId={}",
                       appDate, productId, brandId);
            return ResponseEntity.badRequest().build();
        }
    }
}
