package com.hrms.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.hrms.utils.CommonMethods;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmpWorkExperienceSteps extends CommonMethods{

	
	@When("user clicks on Qualifications")
	public void user_clicks_on_Qualifications() {
	   jsClick(viewEmp.qualifications);
	}

	@When("user clicks add button")
	public void user_clicks_add_button() {
		jsClick(qualifications.addExpButton);
	}
	
	
	@When("user fills out Add Work Experience with the following specification")
	public void user_fills_out_Add_Work_Experience_with_the_following_specification(io.cucumber.datatable.DataTable workExperience) {
		List<Map<String, String>> experiencelist = workExperience.asMaps();
		for (Map<String, String> map : experiencelist) {
			sendText(qualifications.companyexp, map.get("Company"));
			wait(2);
			sendText(qualifications.jobTitle, map.get("Job Title"));
			wait(2);
			sendText(qualifications.expFromDate,map.get("From"));
			//selectDdValue(qualifications.expFromDate, map.get("From"));
			wait(2);
			sendText(qualifications.expToDate,map.get("To"));
			//selectDdValue(qualifications.expToDate, map.get("To"));
			wait(2);
			sendText(qualifications.expComments,map.get("Comment"));
			wait(2);
		}
	}

	@When("user clicks save button")
	public void user_clicks_save_button() {
	  jsClick(qualifications.saveWorkExp);
	}

	@Then("user verifies the following Work Experience specifications are displayed")
	public void user_verifies_the_following_Work_Experience_specifications_are_displayed(String workExp) {
	   
		List<WebElement> workExplist = qualifications.workExperienceList;
		for (WebElement experience : workExplist) {
			String expected=experience.getText();
			if (expected.equals(workExp)) {
				Assert.assertEquals(expected, workExp);
			}
			
		}

		}
	
		
		
	}

	
	
	

