package org.opaeum.java.metamodel.generated;

import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJVisibleElement;

abstract public class OJVisibleElementGEN extends OJElement {
	private boolean isStatic = false;
	private boolean isFinal = false;
	private boolean isVolatile = false;
	private OJVisibilityKind visibility = OJVisibilityKind.lookup(0);

	protected OJVisibleElementGEN() {
		super();
		this.setVisibility( OJVisibilityKind.PUBLIC );
	}
	
	protected OJVisibleElementGEN(String name, String comment, boolean isStatic, boolean isFinal, boolean isVolatile) {
		super();
		super.setName(name);
		super.setComment(comment);
		this.setStatic(isStatic);
		this.setFinal(isFinal);
		this.setVolatile(isVolatile);
		this.setVisibility( OJVisibilityKind.PUBLIC );
	}

	public boolean isStatic() {
		return isStatic;
	}
	
	public void setStatic(boolean element) {
		if ( isStatic != element ) {
			isStatic = element;
		}
	}
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean element) {
		if ( isFinal != element ) {
			isFinal = element;
		}
	}
	
	public boolean isVolatile() {
		return isVolatile;
	}
	
	public void setVolatile(boolean element) {
		if ( isVolatile != element ) {
			isVolatile = element;
		}
	}
	
	public OJVisibilityKind getVisibility() {
		return visibility;
	}
	
	public void setVisibility(OJVisibilityKind element) {
		if ( visibility != element ) {
			visibility = element;
		}
	}
	public String toString() {
		String result = "";
		result = super.toString();
		result = result + " isStatic:" + this.isStatic() + ", ";
		result = result + " isFinal:" + this.isFinal() + ", ";
		result = result + " isVolatile:" + this.isVolatile() + ", ";
		if ( this.getVisibility() != null ) {
			result = result + " visibility:" + this.getVisibility();
		}
		return result;
	}
	
	public String getIdString() {
		String result = "";
		result = super.getIdString();
		return result;
	}
	
	
	/** Copies all attributes and associations of this instance into 'copy'.
			True parts, i.e. associations marked 'aggregate' or 'composite', and attributes, 
			are copied as well. References to other objects, i.e. associations not marked 
			'aggregate' or 'composite', will not be copied. The 'copy' will refer 
			to the same objects as the original (this) instance.
	 * 
	 * @param copy 
	 */
	public void copyInfoInto(OJVisibleElement copy) {
		super.copyInfoInto(copy);
		copy.setStatic(isStatic());
		copy.setFinal(isFinal());
		copy.setVolatile(isVolatile());
		copy.setVisibility(getVisibility());
	}

}