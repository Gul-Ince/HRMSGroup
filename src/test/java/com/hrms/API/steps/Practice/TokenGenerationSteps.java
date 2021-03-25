package com.hrms.API.steps.Practice;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class TokenGenerationSteps {

	/**
	 * Storing token as static variable
	 */

	public static String token;
	String BaseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

	@Given("a JWT is generated")
	public void a_JWT_is_generated() {

		RequestSpecification generateTokenRequest = given().header("Content-Type", "application/json").body(
				"{\r\n" + "  \"email\": \"SSYMNbnnTAA1vXX22@gmail.com\",\r\n" + "  \"password\": \"SDET\"\r\n" + "}");

		Response generateTokenResponse = generateTokenRequest.when().post("/generateToken.php");

		generateTokenResponse.prettyPrint();
		token = "Bearer " + generateTokenResponse.body().jsonPath().getString("token");
		System.out.println(token);

	}

}
