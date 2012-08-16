package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.OJSwitchCase;
import org.opaeum.java.metamodel.OJSwitchStatement;

abstract public class OJSwitchStatementGEN extends OJStatement {
	private String condition = "";
	private Set<OJSwitchCase> cases = new HashSet<OJSwitchCase>();
	private OJSwitchCase defCase = null;
	protected OJSwitchStatementGEN() {
		super();
	}
	
	protected OJSwitchStatementGEN(String name, String comment, String condition) {
		super();
		super.setName(name);
		super.setComment(comment);
		this.setCondition(condition);
	}
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String element) {
		if ( condition != element ) {
			condition = element;
		}
	}
	
	public Set<OJSwitchCase> getCases() {
		return cases;
	}
	
	public void setCases(Set<OJSwitchCase> element) {
		if ( cases != element ) {
			cases = element;
		}
	}
	
	public void addToCases(OJSwitchCase element) {
		if ( cases.contains(element) ) {
			return;
		}
		cases.add(element);
	}
	public void removeFromCases(OJSwitchCase element) {
		cases.remove(element);
	}
	public void addToCases(Collection<OJSwitchCase> newElems) {
		Iterator<OJSwitchCase> it = newElems.iterator();
		while ( (it.hasNext()) ) {
			Object item = it.next();
			if ( item instanceof OJSwitchCase ) {
				addToCases((OJSwitchCase)item);
			}
		}
	}
	
	
	public OJSwitchCase getDefCase() {
		return defCase;
	}
	public void setDefCase(OJSwitchCase element) {
		if ( defCase != element ) {
			defCase = element;
		}
	}
	public String toString() {
		String result = "";
		result = super.toString();
		if ( this.getCondition() != null ) {
			result = result + " condition:" + this.getCondition();
		}
		return result;
	}
	
	public String getIdString() {
		String result = "";
		if ( this.getCondition() != null ) {
			result = result + this.getCondition();
		}
		return result;
	}
	
	public OJElement getCopy() {
		OJSwitchStatement result = new OJSwitchStatement();
		this.copyInfoInto(result);
		return result;
	}
	public void copyInfoInto(OJSwitchStatement copy) {
		super.copyInfoInto(copy);
		copy.setCondition(getCondition());
		Iterator<OJSwitchCase> casesIt = new ArrayList<OJSwitchCase>(getCases()).iterator();
		while ( casesIt.hasNext() ) {
			OJSwitchCase elem = (OJSwitchCase) casesIt.next();
			copy.addToCases(elem);
		}
		if ( getDefCase() != null ) {
			copy.setDefCase(getDefCase());
		}
	}

}