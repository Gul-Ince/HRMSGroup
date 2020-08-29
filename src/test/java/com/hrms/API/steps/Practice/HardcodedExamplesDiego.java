package com.hrms.API.steps.Practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// importing JUnit packages
import org.junit.Assert;
import org.junit.Test;

import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * This @FixMethodOrder(MethodSorters.NAME_ASCENDING) will execute @Test
 * annotation in ascending alphabetical order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardcodedExamplesDiego {
	/**
	 * Rest Assured
	 *
	 * given - prepare your request when - you are making the call to the endpoint
	 * then - validating
	 *
	 * @param args
	 */
	static String baseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	static String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU2ODkzMzksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTczMjUzOSwidXNlcklkIjoiMTA5MyJ9.nN0lcCDdcKxhgE2ML8o98rBcIP52L5FlZpGggYguuBw";
	static String employeeID;

	public void sampleTestNotes() {
		/**
		 * BaseURI for all calls
		 */
		RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
		/**
		 * JWT required for all calls - expires every 12 hours
		 */
		String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUxNjg1NjAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTIxMTc2MCwidXNlcklkIjoiNjQ1In0.huJh3_deVDC49u1tP55dxMic1zfMxOEohdurkOUSzlk";
		/**
		 * Preparing /getOneEmployee.php request
		 */
		RequestSpecification getOneEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", "16490A"); // .log().all();
		/**
		 * Storing response
		 */
		Response getOneEmployeeResponse = getOneEmployeeRequest.when().get("/getOneEmployee.php");
		/**
		 * Two ways to print response prettyPrint() method converts JSON object into
		 * String and prints - no need to SOP
		 */
		getOneEmployeeResponse.prettyPrint();
		// String response = getOneEmployeeResponse.body().asString();
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
				.body("{\n" + "  \"emp_firstname\": \"syntaxFirstName\",\n"
						+ "  \"emp_lastname\": \"syntaxLastName\",\n" + "  \"emp_middle_name\": \"syntaxMiddleName\",\n"
						+ "  \"emp_gender\": \"F\",\n" + "  \"emp_birthday\": \"2000-07-11\",\n"
						+ "  \"emp_status\": \"Employee\",\n" + "  \"emp_job_title\": \"Cloud Architect\"\n" + "}"); // .log().all();
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
		 * calls
		 *
		 */
		employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
		/** optional to print employee ID */
		System.out.println(employeeID);
		/**
		 * Verifying response status code is 201
		 */
		createEmployeeResponse.then().assertThat().statusCode(201);
		/**
		 * Verifying message using equalTo() method - manually import static package
		 * import static org.hamcrest.Matchers.*;
		 */
		createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));
		/**
		 * Verifying created first name
		 */
		createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("syntaxFirstName"));
		/**
		 * Verifying server using then().header()
		 */
		createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
		/**
		 * Verifying Content-Type using assertThat().header()
		 */
		createEmployeeResponse.then().assertThat().header("Content-Type", "application/json");
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

		Assert.assertEquals(emplID, employeeID);
		emplID.contentEquals(employeeID);

		Assert.assertTrue(firstName.contentEquals("syntaxFirstName"));
		Assert.assertTrue(middleName.contentEquals("syntaxMiddleName"));
		Assert.assertTrue(lastName.contentEquals("syntaxLastName"));
		Assert.assertTrue(emp_bday.contentEquals("2000-07-11"));
		Assert.assertTrue(empGender.contentEquals("Female"));
		Assert.assertTrue(jobtitle.contentEquals("Cloud Architect"));
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
				.header("Authorization", token)
				.body("{\n" + "\"employee_id\":\"" + employeeID + "\",\n"
						+ "  \"emp_firstname\": \"syntaxUpdatedFirstName\",\n"
						+ "  \"emp_lastname\": \"syntaxUpdatedLastName\",\n"
						+ "  \"emp_middle_name\": \"syntaxUpdatedMiddleName\",\n" + "  \"emp_gender\": \"F\",\n"
						+ "  \"emp_birthday\": \"2000-07-11\",\n" + "  \"emp_status\": \"Employee\",\n"
						+ "  \"emp_job_title\": \"Cloud Architect\"\n" + "}");

		// updatedCreatedEmployeeResponse.prettyPrint();

		Response updateCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");

		// String response = updateCreatedEmployeeResponse.prettyPrint();

		updateCreatedEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));

		String empID = updateCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");

		// Asserting that
		Assert.assertTrue(empID.contentEquals(employeeID));

	}

	@Test
	public void eGETUpdatedEmployee() {

		RequestSpecification getUpdatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID);

		Response getUpdatedEmployeeResponse = getUpdatedEmployeeRequest.when().get("/getOneEmployee.php");

		getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_firstname",
				equalTo("syntaxUpdatedFirstName"));
		// getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_id",
		// equalTo(employeeID));

	}

	@Test
	public void fPATCPartiallyUpdatedEmployee() {

		RequestSpecification partiallyUpdatingEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).body("{\r\n" + "  \"employee_id\": \"" + employeeID + "\",\r\n"
						+ "  \"emp_firstname\": \"syntaxPartiallyUpdatedFirstName\"\r\n" + "}");

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
