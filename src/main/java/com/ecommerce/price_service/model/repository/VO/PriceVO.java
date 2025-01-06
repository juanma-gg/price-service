package com.ecommerce.price_service.model.repository.VO;

import java.math.BigDecimal;
import java.time.Instant;

import com.ecommerce.price_service.model.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = Constants.PRICE_TABLE)
public class PriceVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.COLUMN_BRAND_ID)
    private Long brandId;

    @Column(name = Constants.COLUMN_START_DATE)
    private Instant startDate;

    @Column(name = Constants.COLUMN_END_DATE)
    private Instant endDate;

    @Column(name = Constants.COLUMN_PRICE_LIST)
    private String priceList;

    @Column(name = Constants.COLUMN_PRODUCT_ID)
    private Long productId;

    @Column(name = Constants.COLUMN_PRIORITY)
    private Integer priority;

    @Column(name = Constants.COLUMN_PRICE)
    private BigDecimal price;

    @Column(name = Constants.COLUMN_CURRENCY)
    private String currency;

}
