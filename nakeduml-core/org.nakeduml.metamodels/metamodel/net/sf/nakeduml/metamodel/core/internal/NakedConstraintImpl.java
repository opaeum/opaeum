package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public class NakedConstraintImpl extends NakedElementImpl implements INakedConstraint{
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
	public void setSpecification(INakedValueSpecification specification){
		super.addOwnedElement(specification);
		this.specification = specification;
	}
	@Override
	public String getMetaClass(){
		return "constraint";
	}
}
