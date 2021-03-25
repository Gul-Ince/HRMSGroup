package com.hrms.API.steps.Practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;
import org.junit.Test;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.hrms.API.utils.PayloadConstants;

/**
 * This @FixMethodOrder(MethodSorters.NAME_ASCENDING) will execute @Test
 * annotation in ascending alphabetical order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardcodedExamples {

	static String baseUTI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU3NDE2MTMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTc4NDgxMywidXNlcklkIjoiMTA5MyJ9.mC8pkDV3aUlk6ZcQ0i3rKlxgTrD647v6DrYZTutAmh0";
	public static String employeeID;

	public void sampleTestNotes() {
		/**
		 * BaseURI for all calls
		 */

		RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

		/**
		 * JWT required for all calls- expires every 12 hours
		 */
		String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU3NDE2MTMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTc4NDgxMywidXNlcklkIjoiMTA5MyJ9.mC8pkDV3aUlk6ZcQ0i3rKlxgTrD647v6DrYZTutAmh0";

		/**
		 * Preparing /getOneEmployee.php request
		 */

		RequestSpecification getOneEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", "20756A").log().all();

		/**
		 * Storing response
		 */
		Response getOneEmployeeResponse = getOneEmployeeRequest.when().get("/getOneEmployee.php");

		/**
		 * Two ways to print response
		 */

		// 1st way. This will print it as string
		getOneEmployeeResponse.prettyPrint();

		// 2nd way. this will convert it.. you can then print it as a string
		// String response=getOneEmployeeResponse.body().asString();
		// System.out.println(response);

		/**
		 * Verifying response status code is 200
		 */

		getOneEmployeeResponse.then().assertThat().statusCode(200);
	}

	@Test
	public void aPOSTcreateEmployee() {
		/**
		 * Preparing request for /createEmployee.php
		 */

		RequestSpecification createEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token)
				.body("{\r\n" + "  \"emp_firstname\": \"ShaH\",\r\n" + "  \"emp_lastname\": \"Niazi\",\r\n"
						+ "  \"emp_middle_name\": \"\",\r\n" + "  \"emp_gender\": \"M\",\r\n"
						+ "  \"emp_birthday\": \"1980-07-10\",\r\n" + "  \"emp_status\": \"Employee\",\r\n"
						+ "  \"emp_job_title\": \"Developer\"\r\n" + "}");

		/**
		 * Storing response into createEmployeeResponse
		 */

		Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");

		/**
		 * Printing response using prettyPrint() method
		 */
		createEmployeeResponse.prettyPrint();
		/**
		 * jsonPath() to view response body which lets us get the employee ID We store
		 * employee ID as a global variable so that we may we use it with our other
		 * calls. Return type is string
		 *
		 */

		employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
		/** optional to print employee ID */
		System.out.println(employeeID);
		createEmployeeResponse.then().statusCode(201);
		/**
		 * Verifying message using equalTo() method - manually import static package
		 * import static org.hamcrest.Matchers.*;
		 */
		createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));

		/**
		 * Verifying created first name
		 */
		createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("ShaH"));
		/**
		 * Verifying server using then().header()
		 */
		createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
		/**
		 * Verifying Content-Type using assertThat().header()
		 */
		createEmployeeResponse.then().assertThat().header("Content-Type", "application/json");

		/**
		 * Verifying Connection using assertThat().header()
		 */

	}

	@Test
	public void bGETcreatedEmployee() {
		/**
		 * Preparing request for /getOneEmployee.php Using log().all() to see all
		 * information being sent with request
		 */
		RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID).log().all();
		/**
		 * Making call to retrieve created employee
		 */
		Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");
		/**
		 * Printing response using prettyPrint()
		 */
		String response = getCreatedEmployeeResponse.prettyPrint();
		/**
		 * Storing response employeeID into empID which will be used for verification
		 * against stored global employee ID
		 */
		String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		/**
		 * matching exact employeeID’s
		 */

		boolean verifyingEmpoyeeIDsMatch = empID.contentEquals(employeeID);
		System.out.println("Employee ID’s match: " + verifyingEmpoyeeIDsMatch);
		/**
		 * Asserting employee ID’s match
		 */
		Assert.assertTrue(verifyingEmpoyeeIDsMatch);

		/**
		 * Verifying status code is 200
		 */
		getCreatedEmployeeResponse.then().assertThat().statusCode(200);

		/**
		 * JsonPath goes into the body and retreives anything we want
		 * 
		 */

		JsonPath js = new JsonPath(response);
		String emplID = js.getString("employee[0].employee_id");
		String firstName = js.get("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastName = js.getString("employee[0].emp_lastname");
		String emp_bday = js.getString("employee[0].emp_birthday");
		String empGender = js.getString("employee[0].emp_gender");
		String jobtitle = js.getString("employee[0].emp_job_title");
		String empStatus = js.getString("employee[0].emp_status");

		System.out.println(firstName);
		System.out.println(emp_bday);

		/**
		 * Verifying employee ID's match
		 */
		Assert.assertEquals(emplID, employeeID);

		// second way of asserting
		Assert.assertTrue(emplID.contentEquals(employeeID));

		/**
		 * Verification expected vs actual
		 */

		Assert.assertTrue(firstName.contentEquals("ShaH"));
		// Assert.assertTrue(middleName.contentEquals("null"));
		Assert.assertTrue(lastName.contentEquals("Niazi"));
		Assert.assertTrue(emp_bday.contentEquals("1980-07-10"));
		Assert.assertTrue(empGender.contentEquals("Male"));
		Assert.assertTrue(jobtitle.contentEquals("Developer"));
		Assert.assertTrue(empStatus.contentEquals("Employee"));

	}

	@Test
	public void cGETallEmployee() {

		/**
		 * Preparing/getAllEmployee.php request
		 */
		RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token);
		/**
		 * Storing response into getAllEmployeesResponse
		 * 
		 */
		Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

		/**
		 * Printing all employees
		 */
		// getAllEmployeesResponse.prettyPrint();

		String allEmployees = getAllEmployeesResponse.body().asString();

		/**
		 * The below will pass but incorrect
		 */

		// allEmployees.contains(employeeID);

		/**
		 * do research- tried works allEmployees.matches(employeeID);
		 */

		JsonPath js = new JsonPath(allEmployees);

		/**
		 * Retrieving size of Employees list
		 */
		int sizeOfList = js.getInt("Employees.size()");
		System.out.println(sizeOfList);

		/**
		 * Print all employees
		 */

//    		for(int i = 0; i<sizeOfList; i++) {
//    		
//    	    String allEmployeeIDs = js.getString("Employees["+ i +"].employee_id");
//    		//System.out.println(allEmployeeIDs);
//    		
//    		/**
//    		 * If statement inside for loop to find stored employee ID and break the loop when found
//    		 */
//    		if (allEmployeeIDs.contentEquals(employeeID)) {
//    			System.out.println("Employee ID: " + employeeID +" is present in body");
//    			String employeeFirstName= js.getString("Employees[" + i + "].emp_firstname");
//    			System.out.println(employeeFirstName);
//    			break;
//    		}
//
//	}
	}

	@Test
	public void dPUTupdateCreatedEmployee() {
		RequestSpecification updateCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).body(PayloadConstants.updateCreatedEmpBody());

		/**
		 * Storing response into updateCreatedEmployeeResponse
		 */

		Response updateCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");

		/**
		 * Storing response into a string
		 */

		// String response = updateCreatedEmployeeResponse.prettyPrint();

		/**
		 * Asserting using hamcrest matchers equalTo() method to verify employee is
		 * updated
		 */

		updateCreatedEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));

		/**
		 * Retrieving response body employee ID using jsonPath()
		 */

		String empID = updateCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		/**
		 * Asserting that response body employee ID matches globally stored employee ID
		 */

		Assert.assertTrue(empID.contentEquals(employeeID));

	}

	@Test
	public void eGETUpdatedEmployee() {

		/**
		 * Preparing request to get updated employee
		 */

		RequestSpecification getUpdatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID);

		/**
		 * Storing response into getUpdatedEmployeeResponse
		 */

		Response getUpdatedEmployeeResponse = getUpdatedEmployeeRequest.when().get("/getOneEmployee.php");

		/**
		 * Printing response
		 */
		// getUpdatedEmployeeResponse.prettyPrint();

		/**
		 * Asserting expected firstname
		 */
		getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_firstname", equalTo("Sha"));
		// getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_id",
		// equalTo(employeeID));

	}

	@Test
	public void fPATCPartiallyUpdatedEmployee() {

		RequestSpecification partiallyUpdatingEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).body("{\r\n" + "  \"employee_id\": \"" + employeeID + "\",\r\n"
						+ "  \"emp_firstname\": \"Sha\"\r\n" + "}");

		Response partiallyUpdatingEmployeeResponse = partiallyUpdatingEmployeeRequest.when()
				.patch("/updatePartialEmplyeesDetails.php");

		// partialUpdateResponse.prettyPrint();

		partiallyUpdatingEmployeeResponse.then().assertThat().statusCode(201);

		partiallyUpdatingEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));

	}

	@Test
	public void gDELETEemployee() {

		RequestSpecification deletingEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID);// .log().all();

		Response deletingEmployeeResponse = deletingEmployeeRequest.when().delete("/deleteEmployee.php");

		deletingEmployeeResponse.prettyPrint();

		deletingEmployeeResponse.then().body("message", equalTo("Entry deleted"));

	}
}
