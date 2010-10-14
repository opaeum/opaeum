package net.sf.nakeduml.obsolete.uimetamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UIMModel implements Serializable{
	private static final long serialVersionUID = 1219382799095571786L;
	
	private Map<String,UIMEntityUserInteraction> userInteractionsByPath = new HashMap<String,UIMEntityUserInteraction>();
	public UIMEntityUserInteraction getUserInteractionByPath(String path){
		return this.userInteractionsByPath.get(path);
	}
}
