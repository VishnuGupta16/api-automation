package com.zivame.api.automation.framework.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Environment {

	private static final Properties PROPERTY_READER = new Properties();
	private static final String[] PROPERTY_FILE_NAME = new String[] {"application.properties", "credential.file.path"};
	private static final Map<String, String> KEY_VS_VALUE = new HashMap<String, String>();

	static {
		for (String fileName : PROPERTY_FILE_NAME) {
			InputStream input = null;
			try {
				input = Environment.class.getClassLoader().getResourceAsStream(fileName);
				if(input == null && KEY_VS_VALUE.containsKey(fileName))
					input = new FileInputStream(new File(KEY_VS_VALUE.get(fileName)));
				if(input != null) {
					PROPERTY_READER.load(input);
					Enumeration<?> e = PROPERTY_READER.propertyNames();
					while (e.hasMoreElements()) {
						String key = (String) e.nextElement();
						String value = PROPERTY_READER.getProperty(key);
						if(key != null && value != null 
								&& !key.trim().isEmpty()
								&& !value.trim().isEmpty())
							KEY_VS_VALUE.put(key, value);
					}
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

	public static String getProperty(String key) {
		if(KEY_VS_VALUE.containsKey(key))
			return KEY_VS_VALUE.get(key);
		return null;
	}

	public static Set<String> getKeys(){
		return KEY_VS_VALUE.keySet();
	}

	public static Boolean containsKey(String key){
		return getKeys().contains(key);
	}
}
