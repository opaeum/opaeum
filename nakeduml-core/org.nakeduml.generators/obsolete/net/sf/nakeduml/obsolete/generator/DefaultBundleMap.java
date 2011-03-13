package net.sf.nakeduml.obsolete.generator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class DefaultBundleMap extends Properties {
	private static final long serialVersionUID = 8273154125569399945L;
	public DefaultBundleMap() {
	}
	@Override
	public Object get(Object key) {
		Object result = super.get(key.toString());
		if (result == null) {
			return "MISSING: " + key + " :MISSING";
		} else {
			return result;
		}
	}
	public void store(File root) {
		try {
			File file = new File(root, "DefaultBundle.properties");
			super.store(new FileOutputStream(file), "DefaultBundle");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}