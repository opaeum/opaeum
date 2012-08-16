package org.opaeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})

public class HierarchicalSourcePopulationImplementor extends StereotypeAnnotator {
	@VisitAfter(matchSubclasses = true)
	public void visitFeature(Property p) {
		boolean isComposition = p.isComposite() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite());
		Classifier umlOwner=EmfPropertyUtil.getOwningClassifier(p);
		if (!isComposition && shouldResolve(p, umlOwner) && !EmfPropertyUtil.isDerived( p) && !p.isReadOnly() && StereotypesHelper.hasStereotype((Element) umlOwner,StereotypeNames.HIERARCHY)) {
			
			Class entityOwner = (Class)p.getOwner();
			Property endToComposite = EmfPropertyUtil.getEndToComposite( entityOwner, getLibrary());
			
			OJAnnotatedClass owner = findJavaClass(umlOwner);
			OJOperation sourcePopulation = new OJAnnotatedOperation("get" + NameConverter.capitalize(p.getName()) + "SourcePopulation");
			sourcePopulation.getBody().addToStatements("return " + endToComposite.getName()+"."+"get" + NameConverter.capitalize(p.getName()) + "SourcePopulation()");
			owner.addToOperations(sourcePopulation);

			OJAnnotatedClass abstractOwner = findJavaClass((Classifier) endToComposite.getType());
			OJOperation abstractSourcePopulation = new OJAnnotatedOperation("get" + NameConverter.capitalize(p.getName()) + "SourcePopulation");
			OJPathName pathName = new OJPathName("java.util.Set");
			
			OJPathName lookupPathName  =ojUtil.classifierPathname(p.getType());
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
	
	private boolean shouldResolve(TypedElement p,Classifier owner){
		return((p.getType() instanceof Class || p.getType() instanceof Interface || p.getType() instanceof Enumeration) && owner instanceof Class);
	}	
}
