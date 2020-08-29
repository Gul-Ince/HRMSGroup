package com.hrms.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.utils.CommonMethods;

public class ViewEmergencyContactsPage extends CommonMethods {

	@FindBy(id = "btnAddContact")
	public WebElement addContactbtn;

	@FindBy(id = "emgcontacts_name")
	public WebElement emgContactName;

	@FindBy(id = "emgcontacts_relationship")
	public WebElement emgContactRelarionship;

	@FindBy(id = "emgcontacts_homePhone")
	public WebElement emgHomePhone;

	@FindBy(id = "emgcontacts_mobilePhone")
	public WebElement emgMobilePhone;

	@FindBy(id = "emgcontacts_workPhone")
	public WebElement emgWorkPhone;
	
	@FindBy(id="btnSaveEContact")
	public WebElement saveBtnContact;

	@FindBy (xpath="//table[@id='emgcontact_list']//tbody//tr")
    public List<WebElement> emgContactList;
	
	
	
	

	
	public ViewEmergencyContactsPage() {
		PageFactory.initElements(driver, this);
	}

}
