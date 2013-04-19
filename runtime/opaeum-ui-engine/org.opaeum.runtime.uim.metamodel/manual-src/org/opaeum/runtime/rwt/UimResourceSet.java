package org.opaeum.runtime.rwt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.osgi.framework.Bundle;
import org.w3c.dom.Element;

public class UimResourceSet{
	Map<String,EObject> map = new HashMap<String,EObject>();
	Map<String,AbstractUimResource> resources = new HashMap<String,AbstractUimResource>();
	private Bundle bundle;
	private File dir;
	public UimResourceSet(Bundle bundle){
		this.bundle = bundle;
	}
	public UimResourceSet(File file){
		this.dir = file;
	}
	public EObject getReference(Element e){
		String href = e.getAttribute("xmi:href");
		if(href != null && href.length() > 0){
			return resolveSingleReference(href);
		}else{
			return null;
		}
	}
	private EObject resolveSingleReference(String href){
		EObject result=null;
		if(href.indexOf('#') > 0){
			String[] split = href.split("\\#");
			result = getResource(split[0]).getElement(split[1]);
		}
		if(result!=null){
			return result;
		}
		throw new IllegalStateException(href + " not found!!!");
	}
	public AbstractUimResource getResource(String fileName){
		AbstractUimResource result = resources.get(fileName);
		if(result == null){
			resources.put(fileName, result = createResource(fileName));
			result.setResourceSet(this);
		}
		return result;
	}
	private AbstractUimResource createResource(String fileName){
		if(dir == null){
			return new BundleUimResource(bundle, fileName);
		}else{
			return new FileUimResource(dir, fileName);
		}
	}
	@SuppressWarnings("rawtypes")
	public List getReferences(Element e){
		List result = new ArrayList();
		String href = e.getAttribute("xmi:href");
		if(href != null && href.length() > 0){
			for(String string:href.split("\\ ")){
				result.add(resolveSingleReference(string));
			}
		}
		return result;
	}
}
