package com.ecommerce.price_service.model.repository.h2;

import static com.ecommerce.price_service.model.Constants.COLUMN_BRAND_ID;
import static com.ecommerce.price_service.model.Constants.COLUMN_CURRENCY;
import static com.ecommerce.price_service.model.Constants.COLUMN_END_DATE;
import static com.ecommerce.price_service.model.Constants.COLUMN_ID;
import static com.ecommerce.price_service.model.Constants.COLUMN_PRICE;
import static com.ecommerce.price_service.model.Constants.COLUMN_PRICE_LIST;
import static com.ecommerce.price_service.model.Constants.COLUMN_PRIORITY;
import static com.ecommerce.price_service.model.Constants.COLUMN_PRODUCT_ID;
import static com.ecommerce.price_service.model.Constants.COLUMN_START_DATE;
import static com.ecommerce.price_service.model.Constants.PRICE_TABLE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ecommerce.price_service.model.repository.VO.PriceVO;

@Repository
public class H2PriceRepository {

    private static final Logger logger = LoggerFactory.getLogger(H2PriceRepository.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2PriceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Object> applyParams(StringBuilder query, Instant appDate, Long productId, Long brandId) {
        List<Object> params = new ArrayList<>();
        boolean hasConditions = false;

        if (appDate != null || productId != null || brandId != null) {
            query.append(" WHERE ");
            if (appDate != null) {
                hasConditions = true;
                query.append("? BETWEEN ")
                           .append(COLUMN_START_DATE)
                           .append(" AND ")
                           .append(COLUMN_END_DATE);
                params.add(appDate);
            }
            if (productId != null) {
                if (hasConditions) {
                    query.append(" AND ");
                }
                hasConditions = true;
                query.append(COLUMN_PRODUCT_ID).append(" = ?");
                params.add(productId);
            }
            if (brandId != null) {
                if (hasConditions) {
                    query.append(" AND ");
                }
                query.append(COLUMN_BRAND_ID).append ("= ?");
                params.add(brandId);
            }
        }
        return params;
    }

    public List<PriceVO> findByDateRangeBrandIdProductId(Instant appDate, Long productId, Long brandId) {
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(COLUMN_ID).append(", ")
                   .append(COLUMN_BRAND_ID).append(", ")
                   .append(COLUMN_START_DATE).append(", ")
                   .append(COLUMN_END_DATE).append(", ")
                   .append(COLUMN_PRICE_LIST).append(", ")
                   .append(COLUMN_PRODUCT_ID).append(", ")
                   .append(COLUMN_PRIORITY).append(", ")
                   .append(COLUMN_PRICE).append(", ")
                   .append(COLUMN_CURRENCY);

        query.append(" FROM ").append(PRICE_TABLE);

        List<Object> params = applyParams(query, appDate, productId, brandId);

        logger.info("Executing query: {} with params: {}", query, params);

        return this.jdbcTemplate.query(query.toString(), new PriceRowMapper(),
                   !params.isEmpty() ? params.toArray() : null );
    }


    public static class PriceRowMapper implements RowMapper<PriceVO> {

        @Override
        public PriceVO mapRow(ResultSet rs, int rowNum ) throws SQLException{
            PriceVO entity =new PriceVO();
            entity.setId(rs.getLong(COLUMN_ID));
            entity.setBrandId(rs.getLong(COLUMN_BRAND_ID));
            entity.setStartDate(rs.getTimestamp(COLUMN_START_DATE).toInstant());
            entity.setEndDate(rs.getTimestamp(COLUMN_END_DATE).toInstant());
            entity.setPriceList(rs.getString(COLUMN_PRICE_LIST));
            entity.setProductId(rs.getLong(COLUMN_PRODUCT_ID));
            entity.setPriority(rs.getInt(COLUMN_PRIORITY));
            entity.setPrice(rs.getBigDecimal(COLUMN_PRICE));
            entity.setCurrency(rs.getString(COLUMN_CURRENCY));
            return entity;
        }
    }
}