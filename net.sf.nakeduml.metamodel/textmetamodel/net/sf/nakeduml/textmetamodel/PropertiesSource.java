package net.sf.nakeduml.textmetamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesSource implements TextSource {
	public static final String GEN_RESOURCE = "gen-resource";
	private Properties p;

	public PropertiesSource(Properties p) {
		super();
		this.p = p;
	}

	@Override
	public boolean hasContent() {
		return true;
	}

	@Override
	public char[] toCharArray() {
		List<String> sorted = new ArrayList<String>();
		Enumeration<?> e = p.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			sorted.add(key);
		}
		Collections.sort(sorted);
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : sorted) {
			stringBuilder.append(key);
			stringBuilder.append("=");
			stringBuilder.append(p.getProperty(key));
			stringBuilder.append("\n");
		}
		return stringBuilder.toString().toCharArray();
	}
}