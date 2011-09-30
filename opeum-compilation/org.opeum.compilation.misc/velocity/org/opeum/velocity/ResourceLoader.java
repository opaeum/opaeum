package org.nakedum.velocity;
import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
/**
 * A workaround for a bug in the ClasspathResourceLoader. In Eclipse it
 * sometimes does not resolve a resource correctly
 */
public class ResourceLoader extends ClasspathResourceLoader {
	@Override
	public InputStream getResourceStream(String name) throws ResourceNotFoundException {
		InputStream is = super.getResourceStream(name);
		if (is == null && name != null) {
			is = getClass().getResourceAsStream(name);
		}
		return is;
	}
}
