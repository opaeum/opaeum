package org.opaeum.metamodel.core.internal;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.uml2.uml.INakedConstraint;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedValueSpecification;

public class NakedConstraintImpl extends NakedElementImpl implements INakedConstraint{
	private static final long serialVersionUID = -7552731810827480586L;
	INakedValueSpecification specification;
	private Collection<INakedElement> constrainedElement = new HashSet<INakedElement>();
	public INakedValueSpecification getSpecification(){
		return specification;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedValueSpecification){
			this.specification = (INakedValueSpecification) element;
		}
	}
	public void setSpecification(INakedValueSpecification specification){
		addOwnedElement(specification);
	}
	@Override
	public String getMetaClass(){
		return "constraint";
	}
	public Collection<INakedElement> getConstrainedElements(){
		return constrainedElement;
	}
	public void setConstrainedElements(Collection<INakedElement> constrainedElement){
		this.constrainedElement = constrainedElement;
	}
}
