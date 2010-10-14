package net.sf.nakeduml.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

public class DataGeneratorProperty {

	private Properties properties;

	@PostConstruct
	public void init() {
		properties = new Properties();
		InputStream in;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.generation.properties");
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getProperty(String property, String defaultValue) {
		return this.properties.getProperty(property, defaultValue);
	}

	public String getProperty(String property) {
		return this.properties.getProperty(property);
	}

	public List<Integer> getIterationListForSizeProperty(String property, String defaultValue) {
		Integer size = Integer.valueOf(properties.getProperty(property, defaultValue));
		List<Integer> iter = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++) {
			iter.add(i);
		}
		return iter;
	}

	public List<String> getPropertiesThatStartsWith(String propertyStartWith) {
		List<String> result = new ArrayList<String>();
		for (String key : properties.stringPropertyNames()) {
			if (key.startsWith(propertyStartWith)) {
				result.add(key);
			}
		}
		return result;
	}
	
	public List<Integer> getSizeListOfPropertiesThatStartsWith(String propertyStartWith) {
		List<String> tmp = getPropertiesThatStartsWith(propertyStartWith);
		List<Integer> result = new  ArrayList<Integer>(tmp.size());
		int i = 0;
		for (@SuppressWarnings("unused") String string : tmp) {
			result.add(i++);
		}
		return result;
	}
}
