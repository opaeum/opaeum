package org.opaeum.javageneration.organization;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class})
public class OrganizationImplementor extends AbstractStructureVisitor{
	@Override
	protected void visitProperty(OJAnnotatedClass c,Classifier owner,PropertyMap buildStructuralFeatureMap){
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass c,Classifier umlOwner){
		// TODO find another place for this
		if(c != null && EmfClassifierUtil.isPersistent(umlOwner)){
			c.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.opaeum.audit.AuditMe")));
		}
		if(EmfClassifierUtil.isBusinessComponent(umlOwner)){
			Component bc = (Component) umlOwner;
			OJAnnotationValue an = new OJAnnotationValue(new OJPathName("org.opaeum.annotation.BusinessComponent"));
			c.addAnnotationIfNew(an);
			OJAnnotationAttributeValue br = new OJAnnotationAttributeValue("businessRoles");
			an.putAttribute(br);
			for(Property p:getLibrary().getEffectiveAttributes(bc)){
				if(p.isComposite() && EmfClassifierUtil.isBusinessRole(p.getType())){
					br.addClassValue(ojUtil.classifierPathname(p.getType()));
				}
			}
			Property endToComposite = getLibrary().getEndToComposite(bc);
			if(endToComposite != null && EmfClassifierUtil.isBusinessCollaboration(endToComposite.getType())){
				an.putAttribute("isRoot", true);
			}
			Class adminRole = EmfClassifierUtil.getAdminRole(bc);
			if(adminRole != null){
				an.putAttribute("adminRole", ojUtil.classifierPathname(adminRole));
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
		}else if(EmfClassifierUtil.isBusinessRole(umlOwner)){
			// Set Person
			OJAnnotatedOperation setRepresentedPerson = new OJAnnotatedOperation("setRepresentedPerson");
			c.addToOperations(setRepresentedPerson);
			setRepresentedPerson.addParam("p", new OJPathName("org.opaeum.runtime.organization.IPersonNode"));
			setRepresentedPerson.getBody().addToStatements("setRepresentedPerson((PersonNode)p)");
		}else if(EmfClassifierUtil.isBusinessComponent(umlOwner)){
			OJAnnotatedOperation setRepresentedOrganization = new OJAnnotatedOperation("setRepresentedOrganization");
			c.addToOperations(setRepresentedOrganization);
			setRepresentedOrganization.addParam("p", new OJPathName("org.opaeum.runtime.organization.IOrganizationNode"));
			setRepresentedOrganization.getBody().addToStatements("setRepresentedOrganization((OrganizationNode)p)");
			// Set Orgnization
		}
		return false;
	}
}
