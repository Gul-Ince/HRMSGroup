package com.hrms.API.steps.Practice;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APIRestAssurePractice {

	static String baseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	static String token = " Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUyMjA5MDAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTI2NDEwMCwidXNlcklkIjoiMTA5MyJ9.29DBm9ZgAZmYX9XReOsmKI_oTp0hDoJbMh4zZA56KKs";
	static String employeeID;

	@Test

	public void aPostCreateEmployee() {

		RequestSpecification createEmpRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\r\n" + "  \"emp_firstname\": \"ShaH\",\r\n" + "  \"emp_lastname\": \"Niazi\",\r\n"
						+ "  \"emp_middle_name\": \"\",\r\n" + "  \"emp_gender\": \"M\",\r\n"
						+ "  \"emp_birthday\": \"1980-07-10\",\r\n" + "  \"emp_status\": \"Employee\",\r\n"
						+ "  \"emp_job_title\": \"Developer\"\r\n" + "}");
		
		
		Response createEmpResponse = createEmpRequest.when().post("/createEmployee.php");
		createEmpResponse.prettyPrint();
		employeeID=createEmpResponse.jsonPath().getString("Employee[0].employee_id");
		
		createEmpResponse.then().assertThat().statusCode(201);
		createEmpResponse.then().assertThat().body("Message",equalTo("Entry Created"));
		createEmpResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("ShaH"));
		createEmpResponse.then().assertThat().body("Employee[0].emp_lastname", equalTo ("Niazi"));
		createEmpResponse.then().assertThat().header("Connection", "Keep-Alive");
		
		
	}
	
	@Test
	public void bGetCreatedEmployee() {
		
		RequestSpecification getCreatedEmp = given().header("Content-Type", "application/json")
		.header("Authorization", token).queryParam("employee_id", employeeID);
		
		Response getCreatedEmployeeResponse=getCreatedEmp.when().get("/getOneEmployee.php");
	    String responseBody=getCreatedEmployeeResponse.prettyPrint();
	    String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
	    Assert.assertEquals(empID, employeeID);
	    getCreatedEmployeeResponse.then().assertThat().statusCode(200);
	    
	    JsonPath js = new JsonPath(responseBody);
	    
	    String emplastname=js.getString("employee[0].emp_lastname");
	 // Assert.assertTrue(emplastname.contentEquals("Niazi"));
	    Assert.assertEquals(emplastname, "Niazi");
	}

}
