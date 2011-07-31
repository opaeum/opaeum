package net.sf.nakeduml.javageneration.basicjava;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.name.NameConverter;

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
			
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
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
