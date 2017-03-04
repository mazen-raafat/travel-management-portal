package com.crossover.spring.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesReader {

	String result = "";
	InputStream inputStream;
 
	private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);

	public String getPropValue(String key) throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
			result = prop.getProperty(key);
 
		} catch (Exception e) {
			logger.info("Exception: " + e.getMessage());
		} finally {
			inputStream.close();
		}
		return result;
	}

}
