package com.hrms.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;
import com.hrms.utils.CommonMethods;

public class PersonalDetailsPageElements extends CommonMethods {

	@FindBy(id = "empPic")
	public WebElement empPicture;

	@FindBy(id = "btnSave")
	public WebElement editSaveBtn;

	@FindBy(id = "personal_cmbNation")
	public WebElement nationalityDD;

	@FindBy(xpath = "//input[@id='personal_txtLicenNo']")
	public WebElement driverLicenceNum;

	@FindBy(id = "personal_txtLicExpDate")
	public WebElement licenceExp;

	@FindBy(id = "personal_txtNICNo")
	public WebElement SSN;

	@FindBy(id = "personal_txtSINNo")
	public WebElement SIN;

	@FindBy(id = "personal_cmbMarital")
	public WebElement maritalStatus;

	@FindBy(id = "personal_DOB")
	public WebElement dateOfBirth;

	@FindBy(id = "personal_txtEmpNickName")
	public WebElement nickName;

	@FindBy(id = "personal_chkSmokeFlag")
	public WebElement smoker;

	@FindBy(id = "personal_txtMilitarySer")
	public WebElement militaryService;

	@FindBy(xpath="//label[contains(@for,'personal_optGender')]")
	public List<WebElement> genderRadio;
	
	@FindBy(name = "personal[optGender]")
	public List<WebElement> genderRadioGroup;

	@FindBy(xpath = "//div[@id='pdMainContainer']/div[1]/h1")
	public WebElement lblPersonalDetails;

	@FindBy(id = "personal_txtEmployeeId")
	public WebElement employeeId;
	

	
	
	
	
	

	public PersonalDetailsPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}