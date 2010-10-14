package net.sf.nakeduml.javametamodel.annotation;

import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJPathName;
/**
 * A class representing the invocation of a Class, typically from an annotation attribute value.
 * 
 *
 */
public class OJClassValue extends OJElement {
	private OJPathName type;
	public OJClassValue(OJPathName type) {
		super();
		this.type = type;
	}
	public OJPathName getType() {
		return this.type;
	}
	public void setType(OJPathName type) {
		this.type = type;
	}
	@Override
	public String toJavaString() {
		return this.type.getLast() + ".class"; 
	}
	
}
