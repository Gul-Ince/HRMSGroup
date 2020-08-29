package com.hrms.API.utils;

import org.json.JSONObject;


import com.hrms.API.steps.Practice.HardcodedExamples;

public class PayloadConstants {
	
	/**
	 * Create employee body
	 * @return
	 */
	
	public static String createEmployeeBody() {
		String createEmployeeBody= "{\r\n" + 
				"  \"emp_firstname\": \"ShaH\",\r\n" + 
				"  \"emp_lastname\": \"Niazi\",\r\n" + 
				"  \"emp_middle_name\": \"\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"1980-07-10\",\r\n" + 
				"  \"emp_status\": \"Employee\",\r\n" + 
				"  \"emp_job_title\": \"Developer\"\r\n" + 
				"}";
		
		return createEmployeeBody;
		
	}
	
	

	
	/**
	 * Creating payload using JSONObject and returning it as a String
	 * 
	 * @return
	 */
	
	public static String createEmployeePayload() {
		
		JSONObject obj= new JSONObject();
		obj.put("emp_firstname","ShaH");
		obj.put("emp_lastname","Niazi");
		obj.put("emp_middle_name","");
		obj.put("emp_gender","M");
		obj.put("emp_birthday","1980-07-10");
		obj.put("emp_status","Employee");
		obj.put("emp_job_title","Developer");
		
		// NEED TO SERILIAZSE IT
		
		return obj.toString();
		
		
		
	}
	
	
	/**
	 * Created method to return payload- to reduce messy code
	 * @return
	 */
	

	public static String updateCreatedEmpBody() {
		
		
		String updateBody= "{\r\n" + 
				"  \"employee_id\": \""+HardcodedExamples.employeeID+"\",\r\n" + 
				"  \"emp_firstname\": \"Sha\",\r\n" + 
				"  \"emp_lastname\": \"Niazi\",\r\n" + 
				"  \"emp_middle_name\": \"\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"1980-07-11\",\r\n" + 
				"  \"emp_status\": \"Employee\",\r\n" + 
				"  \"emp_job_title\": \"API Tester\"\r\n" + 
				"}";
		return updateBody;
		
	}
	
	
	
}
