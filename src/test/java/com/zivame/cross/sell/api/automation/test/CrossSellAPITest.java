package com.zivame.cross.sell.api.automation.test;

import static com.zivame.api.automation.framework.helper.CommonMethod.getResourceUri;
import static com.zivame.api.automation.framework.helper.CommonMethod.loadProperties;
import static io.restassured.RestAssured.given;

import java.util.Properties;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zivame.api.automation.framework.helper.Constants;

public class CrossSellAPITest {

	private Properties CROSS_SELL_PROPERIES = null;
	private String BASE_PATH = null;
	

	@BeforeClass
	@Parameters({"basePath", "resourceFileName", "moduleName"})
	public void init(String basePath, @Optional String resourceFileName, @Optional String moduleName ) {
		loadProperties(resourceFileName, moduleName);
		CROSS_SELL_PROPERIES = Constants.MODULE_VS_PROPERTIES.get(moduleName);
		BASE_PATH = basePath;
	}

	@Test
	public void validateTestApi() {
		given().get(getResourceUri(BASE_PATH, CROSS_SELL_PROPERIES.getProperty("test.path")))
		.then().statusCode(HttpStatus.SC_OK).log().all();
	}
}