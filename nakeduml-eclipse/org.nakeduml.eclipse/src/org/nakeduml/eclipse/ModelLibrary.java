package org.nakeduml.eclipse;

import org.eclipse.emf.common.util.URI;

public class ModelLibrary{
	URI uri;
	String name;
	public ModelLibrary(URI uri,String name){
		super();
		this.uri = uri;
		this.name = name;
	}
	public URI getUri(){
		return uri;
	}
	public String getName(){
		return name;
	}
}
