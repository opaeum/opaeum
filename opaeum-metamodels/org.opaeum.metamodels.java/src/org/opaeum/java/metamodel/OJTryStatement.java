package org.opaeum.java.metamodel;

import org.opaeum.java.metamodel.generated.OJTryStatementGEN;
import org.opaeum.java.metamodel.utilities.JavaStringHelpers;


public class OJTryStatement extends OJTryStatementGEN {


/*********************************************************************
 * The constructors
 ********************************************************************/
	public OJTryStatement() {
		super();
		setCatchPart(new OJBlock());
		setTryPart(new OJBlock());
	}

  	public String toJavaString() {
		String result = "";
		result = "try {\n";
		result = result + JavaStringHelpers.indent(getTryPart().toJavaString(),1);
		result = result + "\n} catch (" + getCatchParam().toJavaString() + ") {\n";
		result = result + JavaStringHelpers.indent(getCatchPart().toJavaString(), 1);
		result = result + "\n}";
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
	}  	
}