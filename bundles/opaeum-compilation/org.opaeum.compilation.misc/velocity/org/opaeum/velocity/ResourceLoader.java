package org.opaeum.velocity;

import java.io.InputStream;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * A workaround for a bug in the ClasspathResourceLoader. In Eclipse it sometimes does not resolve a resource correctly
 */
public class ResourceLoader extends ClasspathResourceLoader{
	@Override
	public InputStream getResourceStream(String name) throws ResourceNotFoundException{
		InputStream is = null;
		try{
			is = super.getResourceStream(name);
		}catch(Exception e){
		}
		if(is == null && name != null){
			if(name.contains(":")){
				String[] split = name.split("\\:");
				return MiscCompilationActivator.getInstance().getResource(split[0], split[1]);
			}else{
				is = getClass().getResourceAsStream(name);
			}
		}
		return is;
	}
}
