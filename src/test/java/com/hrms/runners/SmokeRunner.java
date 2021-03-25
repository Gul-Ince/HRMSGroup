package com.hrms.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "src/test/resources/features/" 
		
			, glue = "com/hrms/steps" 
										
			, dryRun = true 
							
			, strict = true
			, monochrome = true
			, tags = "@location"
			, plugin = {
					"pretty", 
																							
					"html:target/cucumber-default-report" 
					,"json:target/cucumber.json",
					"rerun:target/failed1.txt"
					
			}
			
			)
	
	public class SmokeRunner {

	
	
	
	
}
