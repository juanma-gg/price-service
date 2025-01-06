package com.ecommerce.price_service.model.service.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.price_service.model.repository.entity.Price;
import com.ecommerce.price_service.model.service.dto.PriceDTO;
import com.ecommerce.price_service.model.service.dto.PriceDTOList;

@Component
public class PriceMapper {

    public PriceDTOList mappingToDTO(List<Price> priceList) {
        PriceDTOList priceDTOList = new PriceDTOList();
        priceDTOList.setPriceDTOList(priceList.stream().map(this::toDTO).toList());
        return priceDTOList;
    }

    public PriceDTO toDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setProductId(price.getProductId());
        priceDTO.setBrandId(price.getBrandId());
        priceDTO.setStartDate(price.getStartDate().toString());
        priceDTO.setEndDate(price.getEndDate().toString());
        priceDTO.setPriceList(price.getPriceList());
        priceDTO.setPrice(price.getPrice());
        priceDTO.setCurrency(price.getCurrency());
        return priceDTO;
    }
}
