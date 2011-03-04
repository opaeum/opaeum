package net.sf.nakeduml.javageneration.basicjava;

import static net.sf.nakeduml.metamodel.core.internal.StereotypeNames.HIERARCHY;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.name.NameConverter;

public class HierarchicalSourcePopulationImplementor extends StereotypeAnnotator {
	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		boolean isComposition = p.isComposite() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite());
		if (!isComposition && shouldResolve(p, p.getOwner()) && !p.isDerived() && !p.isReadOnly() && p.getOwner().hasStereotype(HIERARCHY)) {
			
			INakedEntity entityOwner = (INakedEntity)p.getOwner();
			INakedProperty endToComposite = entityOwner.getEndToComposite();
			
			OJAnnotatedClass owner = findJavaClass(p.getOwner());
			OJOperation sourcePopulation = new OJAnnotatedOperation();
			sourcePopulation.setName("get" + NameConverter.capitalize(p.getName()) + "SourcePopulation");
			sourcePopulation.getBody().addToStatements("return " + endToComposite.getName()+"."+"get" + NameConverter.capitalize(p.getName()) + "SourcePopulation()");
			owner.addToOperations(sourcePopulation);

			OJAnnotatedClass abstractOwner = findJavaClass(endToComposite.getNakedBaseType());
			OJOperation abstractSourcePopulation = new OJAnnotatedOperation();
			OJPathName pathName = new OJPathName("java.util.Set");
			
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			OJPathName lookupPathName = new OJPathName(p.getNakedBaseType().getMappingInfo().getQualifiedJavaName().toString());
			abstractOwner.addToImports(lookupPathName); 
			List<OJPathName> x = new ArrayList<OJPathName>();
			x.add(lookupPathName);
			pathName.setElementTypes(x);
			sourcePopulation.setReturnType(pathName);
			
			abstractSourcePopulation.setReturnType(pathName);
			abstractSourcePopulation.setName("get" + NameConverter.capitalize(p.getName()) + "SourcePopulation");
			abstractSourcePopulation.setAbstract(true);
			abstractOwner.addToOperations(abstractSourcePopulation);
		}
	}
	
	private boolean shouldResolve(INakedTypedElement p,INakedClassifier owner){
		return((p.getNakedBaseType() instanceof INakedEntity || p.getNakedBaseType() instanceof INakedInterface || p.getNakedBaseType() instanceof INakedEnumeration) && owner instanceof INakedEntity);
	}	
}
