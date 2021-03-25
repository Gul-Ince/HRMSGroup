package com.hrms.API.steps.Practice;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class APIPractice {

	@Test
	public void requestUsZipCode90210_CheckPlaceNameInResponseBpdy() {

		given().when().get("http://zippopotam.us/us/90210").then().

				assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));

	}

}
