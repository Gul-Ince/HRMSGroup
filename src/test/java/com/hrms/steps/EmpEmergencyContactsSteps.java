package com.hrms.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.hrms.utils.CommonMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmpEmergencyContactsSteps extends CommonMethods {

	@Given("user is navigated to HRMS with valid Admin credentials")
	public void user_is_navigated_to_HRMS_with_valid_Admin_credentials() {
		login.login();
	}
	@Given("user is navigated to the employee list page")
	public void user_is_navigated_to_the_employee_list_page() {
		dashboard.navigateToEmpList();
	}
	@Given("user enters employee id as {string}")
	public void user_enters_employee_id_as(String Id) {
		viewEmplist.empID.sendKeys(Id);
	}
	@Given("user clicks employee id {string}")
	public void user_clicks_employee_id(String expectedId) {
		viewEmplist.searchBtn.click();
		radioClickElement(viewEmplist.idResultTable, expectedId);
	}
	@When("user clicks on Emergency Contacts")
	public void user_clicks_on_Emergency_Contacts() {
		wait(2);
		jsClick(viewEmp.emergencyContacts);
	}
	@When("user clicks Add button")
	public void user_clicks_Add_button() {
		jsClick(emergencyContacts.addContactbtn);
	}
	@When("user fills out Emergency Contacts with the following specification")
	public void user_fills_out_Emergency_Contacts_with_the_following_specification(
			io.cucumber.datatable.DataTable EmergencyContacts) {
		List<Map<String, String>> emgContacts = EmergencyContacts.asMaps();
		for (Map<String, String> map : emgContacts) {
			sendText(emergencyContacts.emgContactName, map.get("Name"));
			sendText(emergencyContacts.emgContactRelarionship, map.get("Relationship"));
			sendText(emergencyContacts.emgHomePhone, map.get("Home Telephone"));
			sendText(emergencyContacts.emgMobilePhone, map.get("Mobile"));
			sendText(emergencyContacts.emgWorkPhone, map.get("Work Telephone"));
		}
	}
	@When("user clicks Save button")
	public void user_clicks_Save_button() {
		click(emergencyContacts.saveBtnContact);
	}

	@Then("user verifies the following Emergency specifications are displayed")
	public void user_verifies_the_following_Emergengy_specifications_are_displayed(String emergContacs) {

		List<WebElement> contactlist = emergencyContacts.emgContactList;
		for (WebElement contact : contactlist) {
			String expected = contact.getText();
			if (expected.equals(emergContacs)) {
				Assert.assertEquals(expected, emergContacs);
				
			}

		}

	}

}
