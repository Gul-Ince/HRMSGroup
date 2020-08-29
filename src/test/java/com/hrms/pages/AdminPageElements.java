package com.hrms.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.utils.CommonMethods;

public class AdminPageElements extends CommonMethods {

	@FindBy(id = "menu_admin_Qualifications")
	public List<WebElement> admin_Qualifications;


	@FindBy(id="btnAdd")
	public WebElement license_btn_Add;
	
	
	@FindBy(id="btnSave")
	public WebElement license_btn_Save;
	
	
	@FindBy(xpath="//table[@id=\"recordsListTable\"]//tbody//tr")
	public List<WebElement> license_record_table;
	
	
	
	public AdminPageElements() {
		PageFactory.initElements(driver, this);
	}
	
	
	
}
