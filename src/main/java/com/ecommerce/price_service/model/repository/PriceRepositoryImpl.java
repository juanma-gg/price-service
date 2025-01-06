package com.ecommerce.price_service.model.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.price_service.model.repository.entity.Price;
import com.ecommerce.price_service.model.repository.service.filters.PriceFilterParameters;
import com.ecommerce.price_service.model.repository.VO.PriceVO;
import com.ecommerce.price_service.model.repository.h2.H2PriceRepository;
import com.ecommerce.price_service.model.repository.mappers.PriceVOMapper;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final H2PriceRepository h2PriceRepository;

    private final PriceVOMapper priceVOMapper;

    @Autowired
    public PriceRepositoryImpl(H2PriceRepository h2PriceRepository, PriceVOMapper priceVOMapper) {
        this.h2PriceRepository = h2PriceRepository;
        this.priceVOMapper = priceVOMapper;
    }

    @Override
    public List<Price> priceFilterParameters(PriceFilterParameters priceFilterParameters) {
        List<PriceVO> priceVOList = h2PriceRepository.findByDateRangeBrandIdProductId(
                   priceFilterParameters.getStartDate(),
                   priceFilterParameters.getProductId(),
                   priceFilterParameters.getBrandId()
        );
        if (priceVOList == null) {
            return Collections.emptyList();
        }
        return priceVOList.stream().map(this.priceVOMapper::mappingToBO).toList();
    }
}
