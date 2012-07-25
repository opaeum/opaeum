package org.opaeum.linkage;

import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedProperty;
import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.core.internal.NakedConstraintImpl;
import org.opaeum.metamodel.core.internal.NakedValueSpecificationImpl;
import org.opaeum.metamodel.core.internal.emulated.EmulatingElement;
import org.opaeum.metamodel.name.SingularNameWrapper;

public class ConstraintUtil{
	public static NakedConstraintImpl buildArtificialConstraint(INakedProperty p,String ocl,String constraintName){
		NakedConstraintImpl constraint = new NakedConstraintImpl();
		MappingInfo mi = p.getMappingInfo().getCopy();
		constraint.setMappingInfo(mi);
		mi.setIdInModel(mi.getIdInModel() + constraintName);
		constraint.initialize(mi.getIdInModel(), "art", false);
		NakedValueSpecificationImpl vs = new NakedValueSpecificationImpl();
		vs.setOwnerElement(constraint);
		mi = mi.getCopy();
		vs.setMappingInfo(mi);
		mi.setIdInModel(mi.getIdInModel() + constraintName);
		vs.initialize(mi.getIdInModel(), "art", false);
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
	public static NakedConstraintImpl buildArtificialConstraint(INakedClassifier owner,INakedProperty p,String ocl,String constraintName){

		NakedConstraintImpl constraint = new NakedConstraintImpl();
		MappingInfo constraintMappingInfo = p.getMappingInfo().getCopy();
		constraint.setMappingInfo(constraintMappingInfo);
		constraint.getMappingInfo().setJavaName(new SingularNameWrapper(constraintName, null));
		constraintMappingInfo.setIdInModel(constraintMappingInfo.getIdInModel() + constraintName);
		constraint.initialize(constraintMappingInfo.getIdInModel(), constraintName, false);
		NakedValueSpecificationImpl vs = new NakedValueSpecificationImpl();
		vs.setOwnerElement(constraint);
		MappingInfo vsMappingInfo = constraintMappingInfo.getCopy();
		vs.setMappingInfo(vsMappingInfo);
		vsMappingInfo.setIdInModel(constraintMappingInfo.getIdInModel() + "VS");
		vs.initialize(vsMappingInfo.getIdInModel(), constraintName, false);
		ParsedOclString parsedOclContext = new ParsedOclString(p.getName() + constraintName, OclUsageType.INV);
		parsedOclContext.setContext(owner, p);
		vs.setValue(parsedOclContext);
		constraint.setSpecification(vs);
		vs.setOwnerElement(constraint);
		constraint.setName(constraintName);
		owner.addOwnedElement(constraint);
		parsedOclContext.setExpressionString(ocl);
		if(p instanceof EmulatingElement){
			constraint.getConstrainedElements().add(((EmulatingElement) p).getOriginalElement());
		}else{
			constraint.getConstrainedElements().add(p);
		}
		return constraint;
	}
}
