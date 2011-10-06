package org.opaeum.metamodel.core.internal;

import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedValueSpecification;

public class NakedConstraintImpl extends NakedElementImpl implements INakedConstraint{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7552731810827480586L;
	INakedValueSpecification specification;
	INakedElement constrainedElement;
	public INakedElement getConstrainedElement(){
		return constrainedElement;
	}
	public void setConstrainedElement(INakedElement constraintElement){
		this.constrainedElement = constraintElement;
	}
	public INakedValueSpecification getSpecification(){
		return specification;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedValueSpecification){
			this.specification=(INakedValueSpecification) element;
		}
	}
	public void setSpecification(INakedValueSpecification specification){
		addOwnedElement(specification);
	}
	@Override
	public String getMetaClass(){
		return "constraint";
	}
}
