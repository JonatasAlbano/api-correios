package com.joalbano.correiosapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiCorreiosApplicationTests {

	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "delivery/calcMaxDate";
	}
	
	@Test
	public void whenAllParametersAreSentCorrectly_thenReturns200() {
		
		RestAssured
		.given()
		.queryParam("CdServico", "04014")
		.queryParam("sCepOrigem", "13224550")
		.queryParam("sCepDestino", "13337300")
		.accept(ContentType.JSON)
		.when()
		.post()
		.then()
		.statusCode(200);
	}
	
	@Test
	public void whenAParameterContainsAStrangeCharacter_thenReturns400() {
		
		RestAssured
		.given()
		.queryParam("CdServico", "abc1324")
		.queryParam("sCepOrigem", "13224550")
		.queryParam("sCepDestino", "13337300")
		.accept(ContentType.JSON)
		.when()
		.post()
		.then()
		.statusCode(400);
	}
	
	@Test
	public void whenAParameterIsNotSent_thenReturns400() {
		
		RestAssured
		.given()
		.queryParam("CdServico", "abc1324")
		.queryParam("sCepOrigem", "13224550")
		.accept(ContentType.JSON)
		.when()
		.post()
		.then()
		.statusCode(400);
	}
	
	@Test
	public void whenClientSendTheWrongVerb_thenReturns405() {
		
		RestAssured
		.given()
		.queryParam("CdServico", "04014")
		.queryParam("sCepOrigem", "13224550")
		.queryParam("sCepDestino", "13337300")
		.accept(ContentType.JSON)
		.when()
		.get()
		.then()
		.statusCode(405);
	}

}
