package com.hrms.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;

public class ViewQualificationsPageElements {

	@FindBy(id = "addWorkExperience")
	public WebElement addExpButton;

	@FindBy(id = "experience_employer")
	public WebElement companyexp;

	@FindBy(id = "experience_jobtitle")
	public WebElement jobTitle;

	@FindBy(id = "experience_from_date")
	public WebElement expFromDate;

	@FindBy(id = "experience_to_date")
	public WebElement expToDate;

	@FindBy(id = "experience_comments")
	public WebElement expComments;

	@FindBy(id = "btnWorkExpSave")
	public WebElement saveWorkExp;

	@FindBy(xpath = "//table[@class='table hover']//tbody//tr")
	public List<WebElement> workExperienceList;

	public ViewQualificationsPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
