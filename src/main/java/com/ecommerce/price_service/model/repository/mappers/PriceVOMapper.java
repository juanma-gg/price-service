package com.ecommerce.price_service.model.repository.mappers;

import org.springframework.stereotype.Component;

import com.ecommerce.price_service.model.repository.entity.Price;
import com.ecommerce.price_service.model.repository.VO.PriceVO;

@Component
public class PriceVOMapper {

    public Price mappingToBO(PriceVO priceVO) {
        return Price.builder()
                   .id(priceVO.getId())
                   .brandId(priceVO.getBrandId())
                   .startDate(priceVO.getStartDate())
                   .endDate(priceVO.getEndDate())
                   .priceList(priceVO.getPriceList())
                   .productId(priceVO.getProductId())
                   .priority(priceVO.getPriority())
                   .price(priceVO.getPrice())
                   .currency(priceVO.getCurrency())
                   .build();
    }

}
