package net.sf.nakeduml.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

@Name("dataGeneratorProperty")
@Scope(ScopeType.APPLICATION)
@AutoCreate
public class DataGeneratorProperty {

	private Properties properties;
	private Properties exportProperties;
	@Logger
	private Log logger;

	@Create
	public void init() {
		properties = new Properties();
		exportProperties = new Properties();
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
		List<Integer> result = new ArrayList<Integer>(tmp.size());
		int i = 0;
		for (@SuppressWarnings("unused")
		String string : tmp) {
			result.add(i++);
		}
		return result;
	}

	public void putExportProperty(Object key, Object value) {
		if (value != null) {
			if (value instanceof String && !((String)value).isEmpty()) {
				exportProperties.put(key, value);
			} else if (!(value instanceof String)) {
				exportProperties.put(key, value.toString());
			}
		}
	}

	public String exportPropertiesToFile(File f) {
		try {
			exportProperties.store(new FileOutputStream(f), "comments go here");
			return "success";
		} catch (FileNotFoundException e) {
			logger.error("could not export properties to file", e);
			return "failure" + e.getMessage();
		} catch (IOException e) {
			logger.error("could not export properties to file", e);
			return "failure" + e.getMessage();
		}
	}
}
