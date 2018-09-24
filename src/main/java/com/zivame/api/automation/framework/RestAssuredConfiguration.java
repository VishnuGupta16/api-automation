package com.zivame.api.automation.framework;

import org.testng.annotations.BeforeSuite;

import com.zivame.api.automation.framework.helper.CommonMethod;
import com.zivame.api.automation.framework.helper.Constants;

import io.restassured.RestAssured;

public class RestAssuredConfiguration {

	@BeforeSuite(alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = CommonMethod.getBaseUrl();
		RestAssured.port= CommonMethod.getPort();
		if(Constants.ACCESS_TOKEN == null) {
			//perform login
		}
	}
}
