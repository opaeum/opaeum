package org.opaeum.java.metamodel.generated;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibleElement;


abstract public class OJFieldGEN extends OJVisibleElement {
	private String f_initExp = "";
	private OJClass owner = null;
	private OJPathName type = null;

	protected OJFieldGEN(String name) {
		super(name);
	}
	
	public String getInitExp() {
		return f_initExp;
	}
	
	public void setInitExp(String element) {
		if ( f_initExp != element ) {
			f_initExp = element;
		}
	}
	public void setOwner(OJClass element) {
		if ( this.owner != element ) {
			if ( this.owner != null ) {
				this.owner.z_internalRemoveFromFields( (OJField)((OJField)this) );
			}
			this.owner = element;
			if ( element != null ) {
				element.z_internalAddToFields( (OJField)((OJField)this) );
			}
		}
	}
	
	public OJClass getOwner() {
		return owner;
	}
	
	public void z_internalAddToOwner(OJClass element) {
		this.owner = element;
	}
	
	public void z_internalRemoveFromOwner(OJClass element) {
		this.owner = null;
	}
	
	public OJPathName getType() {
		return type;
	}
	
	public void setType(OJPathName element) {
		if ( type != element ) {
			type = element;
		}
	}
	public String toString() {
		String result = "";
		result = super.toString();
		if ( this.getInitExp() != null ) {
			result = result + " initExp:" + this.getInitExp();
		}
		return result;
	}
	public String getIdString() {
		String result = "";
		if ( this.getInitExp() != null ) {
			result = result + this.getInitExp();
		}
		return result;
	}

	
	public void copyInfoInto(OJField copy) {
		super.copyInfoInto(copy);
		copy.setInitExp(getInitExp());
		if ( getOwner() != null ) {
			copy.setOwner(getOwner());
		}
		if ( getType() != null ) {
			copy.setType(getType());
		}
	}

}