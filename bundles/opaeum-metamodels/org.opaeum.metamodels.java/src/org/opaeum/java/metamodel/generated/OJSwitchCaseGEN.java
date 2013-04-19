package org.opaeum.java.metamodel.generated;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJSwitchCase;
abstract public class OJSwitchCaseGEN extends OJElement {
	private String f_label = "";
	private OJBlock f_body = null;
	protected OJSwitchCaseGEN() {
		super("");
	}
	public String getLabel() {
		return f_label;
	}
	
	/** Implements the setter for feature '+ label : String'
	 * 
	 * @param element 
	 */
	public void setLabel(String element) {
		if ( f_label != element ) {
			f_label = element;
		}
	}
	
	public OJBlock getBody() {
		return f_body;
	}
	
	public void setBody(OJBlock element) {
		if ( f_body != element ) {
			f_body = element;
		}
	}
	public String toString() {
		String result = "";
		result = super.toString();
		if ( this.getLabel() != null ) {
			result = result + " label:" + this.getLabel();
		}
		return result;
	}
	public String getIdString() {
		String result = "";
		if ( this.getLabel() != null ) {
			result = result + this.getLabel();
		}
		return result;
	}
	public void copyInfoInto(OJSwitchCase copy) {
		super.copyInfoInto(copy);
		copy.setLabel(getLabel());
		if ( getBody() != null ) {
			copy.setBody(getBody());
		}
	}

}