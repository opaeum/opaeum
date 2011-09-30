package org.opeum.javageneration.basicjava;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.maps.NakedClassifierMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedGeneralization;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedInterfaceRealization;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class SuperTypeGenerator extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses = true)
	protected void visitComplexStructure(INakedComplexStructure c){
		if(c.getGeneralizations().size() == 1){
			OJAnnotatedClass myClass = findJavaClass(c);
			if(myClass != null){
				for(INakedGeneralization g:c.getNakedGeneralizations()){
					NakedClassifierMap map = new NakedClassifierMap(g.getGeneral());
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
