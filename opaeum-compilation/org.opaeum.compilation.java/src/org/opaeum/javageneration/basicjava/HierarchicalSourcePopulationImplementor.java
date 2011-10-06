package org.opaeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})

public class HierarchicalSourcePopulationImplementor extends StereotypeAnnotator {
	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		boolean isComposition = p.isComposite() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite());
		if (!isComposition && shouldResolve(p, p.getOwner()) && !p.isDerived() && !p.isReadOnly() && p.getOwner().hasStereotype(StereotypeNames.HIERARCHY)) {
			
			INakedEntity entityOwner = (INakedEntity)p.getOwner();
			INakedProperty endToComposite = entityOwner.getEndToComposite();
			
			OJAnnotatedClass owner = findJavaClass(p.getOwner());
			OJOperation sourcePopulation = new OJAnnotatedOperation("get" + NameConverter.capitalize(p.getName()) + "SourcePopulation");
			sourcePopulation.getBody().addToStatements("return " + endToComposite.getName()+"."+"get" + NameConverter.capitalize(p.getName()) + "SourcePopulation()");
			owner.addToOperations(sourcePopulation);

			OJAnnotatedClass abstractOwner = findJavaClass(endToComposite.getNakedBaseType());
			OJOperation abstractSourcePopulation = new OJAnnotatedOperation("get" + NameConverter.capitalize(p.getName()) + "SourcePopulation");
			OJPathName pathName = new OJPathName("java.util.Set");
			
			OJPathName lookupPathName = new OJPathName(p.getNakedBaseType().getMappingInfo().getQualifiedJavaName().toString());
			abstractOwner.addToImports(lookupPathName); 
			List<OJPathName> x = new ArrayList<OJPathName>();
			x.add(lookupPathName);
			pathName.setElementTypes(x);
			sourcePopulation.setReturnType(pathName);
			
			abstractSourcePopulation.setReturnType(pathName);
			abstractSourcePopulation.setAbstract(true);
			abstractOwner.addToOperations(abstractSourcePopulation);
		}
	}
	
	private boolean shouldResolve(INakedTypedElement p,INakedClassifier owner){
		return((p.getNakedBaseType() instanceof INakedEntity || p.getNakedBaseType() instanceof INakedInterface || p.getNakedBaseType() instanceof INakedEnumeration) && owner instanceof INakedEntity);
	}	
}
