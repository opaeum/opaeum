package net.sf.nakeduml.linkage;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

public class ConstraintUtil{
	public static NakedConstraintImpl buildArtificialConstraint(INakedProperty p,String ocl,String constraintName){
		NakedConstraintImpl constraint = new NakedConstraintImpl();
		IMappingInfo mi = p.getMappingInfo().getCopy();
		constraint.setMappingInfo(mi);
		mi.setIdInModel(mi.getIdInModel() + constraintName);
		NakedValueSpecificationImpl vs = new NakedValueSpecificationImpl();
		vs.setOwnerElement(constraint);
		mi = mi.getCopy();
		vs.setMappingInfo(mi);
		mi.setIdInModel(mi.getIdInModel() + constraintName);
		ParsedOclString parsedOclContext = new ParsedOclString(p.getName() + constraintName, OclUsageType.INV);
		parsedOclContext.setContext(p.getOwner(), p);
		vs.setValue(parsedOclContext);
		constraint.setSpecification(vs);
		vs.setOwnerElement(constraint);
		constraint.setName(constraintName);
		p.getOwner().addOwnedElement(constraint);
		parsedOclContext.setExpressionString(ocl);
		return constraint;
	}
	
	public static NakedConstraintImpl buildArtificialConstraint(INakedClassifier owner, INakedProperty p,String ocl,String constraintName){
		NakedConstraintImpl constraint = new NakedConstraintImpl();
		IMappingInfo mi = p.getMappingInfo().getCopy();
		constraint.setMappingInfo(mi);
		mi.setIdInModel(mi.getIdInModel() + constraintName);
		NakedValueSpecificationImpl vs = new NakedValueSpecificationImpl();
		vs.setOwnerElement(constraint);
		mi = mi.getCopy();
		vs.setMappingInfo(mi);
		mi.setIdInModel(mi.getIdInModel() + constraintName);
		ParsedOclString parsedOclContext = new ParsedOclString(p.getName() + constraintName, OclUsageType.INV);
		parsedOclContext.setContext(owner, p);
		vs.setValue(parsedOclContext);
		constraint.setSpecification(vs);
		vs.setOwnerElement(constraint);
		constraint.setName(constraintName);
		owner.addOwnedElement(constraint);
		parsedOclContext.setExpressionString(ocl);
		return constraint;
	}	
}
