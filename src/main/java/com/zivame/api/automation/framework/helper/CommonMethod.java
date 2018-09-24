package com.zivame.api.automation.framework.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

import com.zivame.api.automation.framework.RestAssuredConfiguration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonMethod {
	
	public static String getBaseUrl() {
		if(Environment.getProperty(getProfile()+".base.url") != null)
			return Environment.getProperty(getProfile()+".base.url");
		return Constants.DEFAULT_BASE_URL;
	}
	
	public static Integer getPort() {
		if(Environment.getProperty(getProfile()+".port") != null)
			return Integer.valueOf(Environment.getProperty(getProfile()+".port"));
		return Constants.DEFAULT_PORT;
	}
	
	public static String getProfile() {
		if(Environment.getProperty("test.profile") != null)
			return Environment.getProperty("test.profile");
		return Constants.DEFAULT_PROFILE;
	}
	
	public static String getResourceUri(String basePath, String resource) {
		if(resource != null)
			return basePath+resource;
		return basePath;
	}
	
	public static void loadProperties(String resourceFileName, String moduleName) {

		if(resourceFileName != null && moduleName != null) {
			InputStream input = null;
			Properties properties = new Properties();
			try {
				input = RestAssuredConfiguration.class.getClassLoader().getResourceAsStream(resourceFileName);
				if(input != null) {
					properties.load(input);
					Constants.MODULE_VS_PROPERTIES.put(moduleName, properties);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if(input != null)
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

		}
	}
	
	public static RequestSpecification getRequestSpecification(Headers headers) {
		return RestAssured.given().headers(headers).contentType(ContentType.JSON);
	}
	
	public static RequestSpecification getRequestSpecification() {
		return RestAssured.given().headers(Constants.DEFAULT_HEADER).contentType(ContentType.JSON);
	}
	
	public Response getResponse(RequestSpecification requestSpecification, String resourceURI, int httpStatus) {
		Response response = requestSpecification.get(resourceURI);
		Assert.assertEquals(response.getStatusCode(), httpStatus);
		response.then().log().all();
		return response;
	}
	
	public Response putResponse(RequestSpecification requestSpecification, String resourceURI, Object requestObject, int httpStatus) {
		Response response = requestSpecification.put(resourceURI, requestObject);
		Assert.assertEquals(response.getStatusCode(), httpStatus);
		response.then().log().all();
		return response;
	}
	
	public Response postResponse(RequestSpecification requestSpecification, String resourceURI, Object requestObject, int httpStatus) {
		Response response = requestSpecification.post(resourceURI, requestObject);
		Assert.assertEquals(response.getStatusCode(), httpStatus);
		response.then().log().all();
		return response;
	}
	
	public Response deleteResponse(RequestSpecification requestSpecification, String resourceURI, Object requestObject, int httpStatus) {
		Response response = requestSpecification.delete(resourceURI, requestObject);
		Assert.assertEquals(response.getStatusCode(), httpStatus);
		response.then().log().all();
		return response;
	}
	
}
