package net.sf.nakeduml.obsolete.uimetamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UIMMetaElement implements Serializable{
	private static final long serialVersionUID = 113132414124151245L;
	private Map<String,Object[]> attributes= new HashMap<String,Object[]>();
	public String getStringValue(String name){
		return (String) getFirstValue(name);
	}
	public String[] getStringValues(String name){
		return (String[]) this.attributes.get(name);
	}
	public Enum getEnumValue(String name){
		return (Enum) getFirstValue(name);
	}
	private Object getFirstValue(String name){
		Object[] values = this.attributes.get(name);
		Object value=null;
		if(values==null||values.length==0){
			value=null;
		}else{
			value=values[0];
		}
		return value;
	}
	public final Map<String,Object[]> getAttributes(){
		return this.attributes;
	}
	public boolean hasAttribute(String attribute){
		Object[] a = this.attributes.get(attribute);
		return a!=null && a.length>0;
	}
}
