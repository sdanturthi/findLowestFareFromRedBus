package com.RedBus.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	String strPath = System.getProperty("user.dir") + "/Configuration/Config.Properties";
	Properties pro;

	public ReadConfig() {
		File file;
		try {
			file = new File(strPath);
			FileInputStream fis = new FileInputStream(file);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}

	public String getUrl() {
		String urlPath = pro.getProperty("url");
		return urlPath;
	}

	public String getOriginCity() {
		String originCity = pro.getProperty("origin");
		return originCity;
	}

	public String getDestinationCity() {
		String destinationCity = pro.getProperty("destination");
		return destinationCity;
	}

	public String getTravelDate() {
		String travelDate = pro.getProperty("DateOfTravel");
		return travelDate;
	}
	
	public String getChromeDriverPath() {
		String cdPath = pro.getProperty("cdPath");
		return cdPath;
	}

}
