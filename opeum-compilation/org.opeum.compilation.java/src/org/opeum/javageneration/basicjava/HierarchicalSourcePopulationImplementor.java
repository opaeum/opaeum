package org.opeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.List;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.StereotypeAnnotator;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedTypedElement;
import org.opeum.metamodel.core.internal.StereotypeNames;
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
