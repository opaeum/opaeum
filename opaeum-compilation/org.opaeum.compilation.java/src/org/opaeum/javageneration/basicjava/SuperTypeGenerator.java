package org.opaeum.javageneration.basicjava;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedInterfaceRealization;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class SuperTypeGenerator extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses = true)
	protected void visitComplexStructure(INakedComplexStructure c){
		if(c.getGeneralizations().size() == 1){
			OJAnnotatedClass myClass = findJavaClass(c);
			if(myClass != null){
				for(INakedGeneralization g:c.getNakedGeneralizations()){
					NakedClassifierMap map = OJUtil.buildClassifierMap(g.getGeneral());
					myClass.setSuperclass(map.javaTypePath());
					myClass.addToImports(map.javaTypePath());
					OJConstructor constructor = myClass.getDefaultConstructor();
					constructor.getBody().addToStatements("super()");
				}
			}
		}else if(c.getNakedGeneralizations().size() > 1){
		}
		if(OJUtil.hasOJClass(c) && c instanceof INakedBehavioredClassifier){
			for(INakedInterfaceRealization ir:((INakedBehavioredClassifier)c).getInterfaceRealizations()){
				OJAnnotatedClass myClass = findJavaClass(c);
				myClass.addToImplementedInterfaces(OJUtil.classifierPathname(ir.getContract()));
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface c){
		if(c.getGeneralizations().isEmpty())
			return;
		OJAnnotatedInterface myIntf = (OJAnnotatedInterface) findJavaClass(c);
		if(myIntf != null){
			for(INakedGeneralization g:c.getNakedGeneralizations()){
				OJPathName pathname = OJUtil.classifierPathname(g.getGeneral());
				myIntf.getSuperInterfaces().add(pathname);
				myIntf.addToImports(pathname);
			}
		}
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		
	}
}
