package com.hrms.testbase;

import com.hrms.pages.AddEmployeePageElements;
import com.hrms.pages.AdminPageElements;
import com.hrms.pages.DashboardPageElements;
import com.hrms.pages.DefinedPredefinedReports;

import com.hrms.pages.LoginPageElements;
import com.hrms.pages.PersonalDetailsPageElements;
import com.hrms.pages.ViewEmergencyContactsPage;
import com.hrms.pages.ViewEmployeeListPageElements;
import com.hrms.pages.ViewEmployeePageElements;
import com.hrms.pages.ViewQualificationsPageElements;
import com.hrms.utils.CommonMethods;

public class PageInitializer extends BaseClass {
	public static LoginPageElements login;
	public static AddEmployeePageElements addEmp;
	public static DashboardPageElements dashboard;
	public static PersonalDetailsPageElements pdetails;
	public static DefinedPredefinedReports definedreports;
	public static ViewEmployeeListPageElements viewEmplist;
	public static ViewEmergencyContactsPage emergencyContacts;
    public static ViewQualificationsPageElements qualifications;
    public static ViewEmployeePageElements viewEmp;
    public static AdminPageElements admin;
    
	public static void initialize() {

		login = new LoginPageElements();
		dashboard = new DashboardPageElements();
		addEmp = new AddEmployeePageElements();
		pdetails = new PersonalDetailsPageElements();
		definedreports = new DefinedPredefinedReports();
		viewEmplist = new ViewEmployeeListPageElements();
		emergencyContacts = new ViewEmergencyContactsPage();
		qualifications= new ViewQualificationsPageElements();
		viewEmp= new ViewEmployeePageElements();
		admin= new AdminPageElements();
	}

}
