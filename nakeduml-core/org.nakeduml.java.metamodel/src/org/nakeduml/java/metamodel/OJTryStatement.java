package org.nakeduml.java.metamodel;

import org.nakeduml.java.metamodel.generated.OJTryStatementGEN;
import org.nakeduml.java.metamodel.utilities.JavaStringHelpers;


public class OJTryStatement extends OJTryStatementGEN {

	private OJBlock f_finallyPart = null;
	
/*********************************************************************
 * The constructors
 ********************************************************************/
	public OJTryStatement() {
		super();
		setCatchPart(new OJBlock());
		setTryPart(new OJBlock());
		setFinallyPart(new OJBlock());
	}
	
	public OJBlock getFinallyPart() {
		return f_finallyPart;
	}
	
	/** Implements the setter for feature '+ tryPart : OJBlock'
	 * 
	 * @param element 
	 */
	public void setFinallyPart(OJBlock element) {
		f_finallyPart = element;
		if ( f_finallyPart != element ) {
		}
	}
	

  	public String toJavaString() {
		String result = "";
		result = "try {\n";
		result = result + JavaStringHelpers.indent(getTryPart().toJavaString(),1);
		result = result + "\n} catch (" + getCatchParam().toJavaString() + ") {\n";
		result = result + JavaStringHelpers.indent(getCatchPart().toJavaString(), 1);
		result = result + "\n}";
		if (getFinallyPart()!=null) {
			result += " finally {\n" ;	
			result = result + JavaStringHelpers.indent(getFinallyPart().toJavaString(), 1);
			result = result + "\n}";
		}
		return result;
	}
  	
	public OJTryStatement getDeepCopy() {
		OJTryStatement copy = new OJTryStatement();
		copyDeepInfoInto(copy);
		return copy;
	}
	
	public void copyDeepInfoInto(OJTryStatement copy) {
		super.copyDeepInfoInto(copy);
		if ( getTryPart() != null ) {
			copy.setTryPart(getTryPart().getDeepCopy());
		}
		if ( getCatchPart() != null ) {
			copy.setCatchPart(getCatchPart().getDeepCopy());
		}
		if ( getCatchParam() != null ) {
			copy.setCatchParam(getCatchParam().getDeepCopy());
		}		
		if ( getFinallyPart() != null ) {
			copy.setFinallyPart(getFinallyPart().getDeepCopy());
		}		
	}  	
}