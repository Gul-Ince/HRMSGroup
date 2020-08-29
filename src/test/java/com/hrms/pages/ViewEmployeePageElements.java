package com.hrms.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.hrms.utils.CommonMethods;

public class ViewEmployeePageElements extends CommonMethods {
	
	@FindBy (xpath="//ul[@id='sidenav']//a[contains(text(),'Qualifications')]")
	public WebElement qualifications;
	
	@FindBy (xpath="//ul[@id='sidenav']//a[contains(text(),'Memberships')]")
	public WebElement memberships;
	
	
	@FindBy (xpath="//a[contains(text(),'Emergency Contacts')]")
	public WebElement emergencyContacts;
	
	
	
	public ViewEmployeePageElements () {
		PageFactory.initElements(driver, this);
	}
	
	
	
}
