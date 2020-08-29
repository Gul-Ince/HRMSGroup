package com.hrms.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;

public class ViewEmployeeListPageElements {

	@FindBy(id = "empsearch_employee_name_empName")
	public WebElement empName;

	@FindBy(id = "empsearch_id")
	public WebElement empID;

	@FindBy(xpath = "//input[@id='searchBtn']")
	public WebElement searchBtn;

	@FindBy(xpath = "//table[@id='resultTable']")
	public List<WebElement> resultTable;


	@FindBy(xpath = "//table[@id='resultTable']//tbody//td[3]")
	public WebElement nameResultTable;

	@FindBy(xpath = "//table[@id='resultTable']//tbody//td[2]")
	public WebElement idResultTable;

	public ViewEmployeeListPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
