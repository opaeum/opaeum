package org.opaeum.javageneration.organization;

import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
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

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class})
public class OrganizationImplementor extends AbstractStructureVisitor{
	@Override
	protected void visitProperty(Classifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		//TODO find another place for this
		OJAnnotatedClass c = findJavaClass(umlOwner);
		if(c != null){
			c.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.opaeum.audit.AuditMe")));
		}
		if(umlOwner instanceof BusinessComponent){
			BusinessComponent bc = (BusinessComponent) umlOwner;
			OJAnnotationValue an = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.BusinessComponent"));
			c.addAnnotationIfNew(an);
			OJAnnotationAttributeValue br = new OJAnnotationAttributeValue("businessRoles");
			an.putAttribute(br);
			for(Property p:bc.getEffectiveAttributes()){
				if(p.isComposite() && p.getType() instanceof BusinessRole){
					br.addClassValue(OJUtil.classifierPathname(p.getType()));
				}
			}
			if(bc.isRoot()){
				an.putAttribute("isRoot", true);
			}
			if(bc.getAdminRole() != null){
				an.putAttribute("adminRole", OJUtil.classifierPathname(bc.getAdminRole()));
			}
		}
		if(umlOwner instanceof Actor){
			OJAnnotatedOperation setRepresentedPerson = new OJAnnotatedOperation("setRepresentedPerson");
			c.addToOperations(setRepresentedPerson);
			setRepresentedPerson.addParam("p", new OJPathName("org.opaeum.runtime.organization.IPersonNode"));
			setRepresentedPerson.getBody().addToStatements("setRepresentedPerson((PersonNode)p)");
			OJAnnotatedOperation setOrganization = new OJAnnotatedOperation("setOrganization");
			c.addToOperations(setOrganization);
			setOrganization.addParam("org", new OJPathName("org.opaeum.runtime.organization.IOrganizationNode"));
			setOrganization.getBody().addToStatements("setOrganization((OrganizationNode)org)");
		}else if(umlOwner instanceof BusinessRole){
			// Set Person
			OJAnnotatedOperation setRepresentedPerson = new OJAnnotatedOperation("setRepresentedPerson");
			c.addToOperations(setRepresentedPerson);
			setRepresentedPerson.addParam("p", new OJPathName("org.opaeum.runtime.organization.IPersonNode"));
			setRepresentedPerson.getBody().addToStatements("setRepresentedPerson((PersonNode)p)");
			OJAnnotationValue br = c.findAnnotation(new OJPathName("org.opaeum.annotation.BusinessRole"));
		}else if(umlOwner instanceof BusinessComponent){
			OJAnnotatedOperation setRepresentedOrganization = new OJAnnotatedOperation("setRepresentedOrganization");
			c.addToOperations(setRepresentedOrganization);
			setRepresentedOrganization.addParam("p", new OJPathName("org.opaeum.runtime.organization.IOrganizationNode"));
			setRepresentedOrganization.getBody().addToStatements("setRepresentedOrganization((OrganizationNode)p)");
			// Set Orgnization
		}else if(umlOwner instanceof NakedBusinessCollaboration){
		}
	}
}
