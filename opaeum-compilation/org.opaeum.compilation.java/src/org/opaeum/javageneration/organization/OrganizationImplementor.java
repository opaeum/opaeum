package org.opaeum.javageneration.organization;

import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.metamodel.bpm.INakedBusinessComponent;
import org.opaeum.metamodel.bpm.INakedBusinessRole;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.emulated.NakedBusinessCollaboration;
import org.opaeum.metamodel.usecases.INakedActor;

@StepDependency(phase = JavaTransformationPhase.class,requires = {CompositionEmulator.class,OperationAnnotator.class},after = {OperationAnnotator.class})
public class OrganizationImplementor extends AbstractStructureVisitor{
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		if(umlOwner instanceof INakedBusinessComponent){
			INakedBusinessComponent bc = (INakedBusinessComponent) umlOwner;
			OJAnnotatedClass c = findJavaClass(bc);
			OJAnnotationValue an = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.BusinessComponent"));
			c.addAnnotationIfNew(an);
			OJAnnotationAttributeValue br = new OJAnnotationAttributeValue("businessRoles");
			an.putAttribute(br);
			for(INakedProperty p:bc.getEffectiveAttributes()){
				if(p.isComposite() && p.getNakedBaseType() instanceof INakedBusinessRole){
					br.addClassValue(OJUtil.classifierPathname(p.getNakedBaseType()));
				}
			}
			if(bc.isRoot()){
				an.putAttribute("isRoot", true);
			}
			if(bc.getAdminRole() != null){
				an.putAttribute("adminRole", OJUtil.classifierPathname(bc.getAdminRole()));
			}
		}
		OJAnnotatedClass c = findJavaClass(umlOwner);
		if(umlOwner instanceof INakedActor){
			OJAnnotatedOperation setRepresentedPerson = new OJAnnotatedOperation("setRepresentedPerson");
			c.addToOperations(setRepresentedPerson);
			setRepresentedPerson.addParam("p", new OJPathName("org.opaeum.runtime.organization.IPersonNode"));
			setRepresentedPerson.getBody().addToStatements("setRepresentedPerson((PersonNode)p)");
			OJAnnotatedOperation setOrganization = new OJAnnotatedOperation("setOrganization");
			c.addToOperations(setOrganization);
			setOrganization.addParam("org", new OJPathName("org.opaeum.runtime.organization.IOrganizationNode"));
			setOrganization.getBody().addToStatements("setOrganization((OrganizationNode)org)");
		}else if(umlOwner instanceof INakedBusinessRole){
			// Set Person
			OJAnnotatedOperation setRepresentedPerson = new OJAnnotatedOperation("setRepresentedPerson");
			c.addToOperations(setRepresentedPerson);
			setRepresentedPerson.addParam("p", new OJPathName("org.opaeum.runtime.organization.IPersonNode"));
			setRepresentedPerson.getBody().addToStatements("setRepresentedPerson((PersonNode)p)");
			OJAnnotationValue br = c.findAnnotation(new OJPathName("org.opaeum.annotation.BusinessRole"));
		}else if(umlOwner instanceof INakedBusinessComponent){
			OJAnnotatedOperation setRepresentedOrganization = new OJAnnotatedOperation("setRepresentedOrganization");
			c.addToOperations(setRepresentedOrganization);
			setRepresentedOrganization.addParam("p", new OJPathName("org.opaeum.runtime.organization.IOrganizationNode"));
			setRepresentedOrganization.getBody().addToStatements("setRepresentedOrganization((OrganizationNode)p)");
			// Set Orgnization
		}else if(umlOwner instanceof NakedBusinessCollaboration){
		}
	}
}
