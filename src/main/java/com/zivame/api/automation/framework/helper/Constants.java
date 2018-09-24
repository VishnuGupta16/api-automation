package com.zivame.api.automation.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.http.Headers;

public class Constants {
	public final static String DEFAULT_PROFILE = "dev";
	public final static String DEFAULT_BASE_URL = "http://localhost";
	public final static Integer DEFAULT_PORT = 8080;
	public final static String DEFAULT_BASE_PATH = "";
	public final static Map<String, Properties> MODULE_VS_PROPERTIES = new HashMap<String, Properties>();
	public static String ACCESS_TOKEN = null;
	public static Headers DEFAULT_HEADER = new Headers();
}
