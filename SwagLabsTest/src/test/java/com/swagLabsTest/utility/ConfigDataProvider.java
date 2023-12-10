package com.swagLabsTest.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {

	Properties prop;

	public ConfigDataProvider() {

		File src = new File("Configuration\\config.properties");
		try {
			FileInputStream file = new FileInputStream(src);
			prop = new Properties();
			prop.load(file);
		} catch (Exception e) {
			System.out.println("Not able to load properties file"+ e.getMessage());
		}

	}
	
	public String getBrowser() {
		return prop.getProperty("browser");
	}

	public String getStagingUrl() {
		return prop.getProperty("testurl");
	}
}
