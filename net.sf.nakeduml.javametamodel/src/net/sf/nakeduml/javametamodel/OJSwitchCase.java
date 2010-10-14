package net.sf.nakeduml.javametamodel;

import java.util.Map;

import net.sf.nakeduml.javametamodel.generated.OJSwitchCaseGEN;
import net.sf.nakeduml.javametamodel.utilities.JavaStringHelpers;


public class OJSwitchCase extends OJSwitchCaseGEN {


/*********************************************************************
 * The constructor
 ********************************************************************/
	public OJSwitchCase() {
		super();
		this.setBody(new OJBlock());
	}

	public String toJavaString() {
		String result = "case " + getLabel() + ":\n" + 
						JavaStringHelpers.indent(getBody().toJavaString(), 1) + "\n" +
						"break;\n";
		return result;
	}

	public OJSwitchCase getDeepCopy() {
		OJSwitchCase copy = new OJSwitchCase();
		copyDeepInfoInto(copy);
		return copy;
	}
	
	public void copyDeepInfoInto(OJSwitchCase copy) {
		super.copyDeepInfoInto(copy);
		copy.setLabel(getLabel());
		if ( getBody() != null ) {
			copy.setBody(getBody().getDeepCopy());
		}		
	}
	
	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		if ( getBody() != null ) {
			getBody().renameAll(renamePathNames, newName);
		}	
	}	

}