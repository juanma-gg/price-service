package com.ecommerce.price_service;

import static com.ecommerce.price_service.model.Constants.ENDPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = PriceServiceApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
class PriceControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String url;

	@BeforeEach
	void init() {
		url = "http://localhost:" + port + ENDPOINT;
	}

	@Test
	void testEndpointFirstCase() {
		String appDate = "2020-06-14T10:00:00Z";
		Long productId = 35455L;
		Long brandId = 1L;

		ResponseEntity<String> response = restTemplate.getForEntity(
				url + "?appDate={appDate}&productId={productId}&brandId={brandId}",
				String.class, appDate, productId, brandId);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		String expectedResponse = "{\"priceDTOList\":[{\"brandId\":1,\"startDate\":\"2020-06-13T22:00:00Z\",\"endDate\":\"2020-12-31T22:59:59Z\",\"priceList\":\"1\",\"productId\":35455,\"price\":35.50,\"currency\":\"EUR\"}]}";
		assertEquals(expectedResponse, response.getBody());
	}

	@Test
	void testEndpointSecondCase() {
		String appDate = "2020-06-14T16:00:00Z";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<String> response = restTemplate.getForEntity(
				url + "?appDate={appDate}&productId={productId}&brandId={brandId}",
				String.class, appDate, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testEndpointThirdCase() {
		String appDate = "2020-06-14T21:00:00Z";
		Long productId = 35455L;
		Long brandId = 1L;

		ResponseEntity<String> response = restTemplate.getForEntity(
				url + "?appDate={appDate}&productId={productId}&brandId={brandId}",
				String.class, appDate, productId, brandId);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		String expectedResponse = "{\"priceDTOList\":[{\"brandId\":1,\"startDate\":\"2020-06-13T22:00:00Z\",\"endDate\":\"2020-12-31T22:59:59Z\",\"priceList\":\"1\",\"productId\":35455,\"price\":35.50,\"currency\":\"EUR\"}]}";
		assertEquals(expectedResponse, response.getBody());
	}

	@Test
	void testEndpointFourthCase() {
		String appDate = "2020-06-15T10:00:00Z";
		Long productId = 35455L;
		Long brandId = 1L;

		ResponseEntity<String> response = restTemplate.getForEntity(
				url + "?appDate={appDate}&productId={productId}&brandId={brandId}",
				String.class, appDate, productId, brandId);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		String expectedResponse = "{\"priceDTOList\":[{\"brandId\":1,\"startDate\":\"2020-06-13T22:00:00Z\",\"endDate\":\"2020-12-31T22:59:59Z\",\"priceList\":\"1\",\"productId\":35455,\"price\":35.50,\"currency\":\"EUR\"}]}";
		assertEquals(expectedResponse, response.getBody());
	}

	@Test
	void testEndpointFifthCase() {
		String appDate = "2020-06-16T21:00:00Z";
		Long productId = 35455L;
		Long brandId = 1L;

		ResponseEntity<String> response = restTemplate.getForEntity(
				url + "?appDate={appDate}&productId={productId}&brandId={brandId}",
				String.class, appDate, productId, brandId);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		String expectedResponse = "{\"priceDTOList\":[{\"brandId\":1,\"startDate\":\"2020-06-13T22:00:00Z\",\"endDate\":\"2020-12-31T22:59:59Z\",\"priceList\":\"1\",\"productId\":35455,\"price\":35.50,\"currency\":\"EUR\"},{\"brandId\":1,\"startDate\":\"2020-06-15T14:00:00Z\",\"endDate\":\"2020-12-31T22:59:59Z\",\"priceList\":\"4\",\"productId\":35455,\"price\":38.95,\"currency\":\"EUR\"}]}";
		assertEquals(expectedResponse, response.getBody());
	}

}
