package edu.usc.util;

import java.util.Properties;


//code from https://www.opencodez.com/java/read-config-file-in-java.htm
public class ConfigFileReader {
	Properties configFile;
	public ConfigFileReader()
	{
		configFile = new java.util.Properties();
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("config/config.cfg"));
		}catch(Exception eta){
			eta.printStackTrace();
		}
	}

	public String getProperty(String key)
	{
		String value = this.configFile.getProperty(key);
		return value;
	}
}
