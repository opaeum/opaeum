package org.opaeum.javageneration.persistence;

import java.util.ArrayList;

import javax.persistence.Transient;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.AbstractPersistence;

/**
 * This class builds all the operations specified by the AbstractClass interface. It also provides an implementation for the equals method
 * that uses the id of the instance involved
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {AttributeImplementor.class},after = {AttributeImplementor.class})
public class PersistentObjectImplementor extends AbstractStructureVisitor{
	private static final OJPathName ABSTRACT_ENTITY = new OJPathName(IPersistentObject.class.getName());
	@VisitBefore
	public void visitModel(Model p){
	}
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(Interface a){
		if(ojUtil.hasOJClass(a) && !EmfClassifierUtil.isHelper(a)){
			OJAnnotatedInterface asdf = (OJAnnotatedInterface) findJavaClass(a);
			asdf.addToSuperInterfaces(new OJPathName(IPersistentObject.class.getName()));
		}
	}
	private void visitClass(OJAnnotatedClass ojClassifier,Classifier c){
		if(isPersistent(c)){
			OJClass ojClass = (OJClass) ojClassifier;
			OJAnnotatedField persistence = new OJAnnotatedField("persistence", new OJPathName(AbstractPersistence.class.getName()));
			persistence.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(Transient.class.getName())));
			ojClass.addToFields(persistence);
			if(c instanceof Class && EmfClassifierUtil.getPrimaryKeyProperties((Class) c).size() > 0){
				return;
			}else{
				ojClass.addToImports(ABSTRACT_ENTITY);
				if(ojClass.findOperation("getName", new ArrayList<OJPathName>()) == null){
					Property nameProperty = EmfPropertyUtil.getNameProperty(c);
					if(nameProperty == null){
						addGetName(c, ojClass);
					}else if(!nameProperty.getName().equals("name")){
						OJOperation getName = new OJAnnotatedOperation("getName");
						getName.setReturnType(new OJPathName("String"));
						getName.setBody(new OJBlock());
						getName.getBody().addToStatements("return " + ojUtil.buildStructuralFeatureMap(nameProperty).getter() + "()");
						ojClass.addToOperations(getName);
					}
				}
				ojClass.addToImplementedInterfaces(ABSTRACT_ENTITY);
				if(c instanceof Class){
					addDiscriminatorInitialization((Class) c, ojClass);
				}
			}
		}
	}
	private void addDiscriminatorInitialization(Class entity,OJClass ojClass){
		for(Property attr:getLibrary().getEffectiveAttributes(entity)){
			if(EmfPropertyUtil.isDiscriminator(attr)){
				Enumeration powerType = (Enumeration) attr.getType();
				if(EmfClassifierUtil.isPowerTypeInstanceOn(entity, powerType)){
					Generalization generalization = entity.getGeneralizations().iterator().next();
					String literal = ojUtil.classifierPathname(powerType) + "."
							+ OJUtil.toJavaLiteral(EmfClassifierUtil.getPowerTypeLiteral(generalization, powerType));
					ojClass.getDefaultConstructor().getBody().addToStatements("set" + NameConverter.capitalize(attr.getName()) + "(" + literal + ")");
				}
			}
		}
	}
	private void addGetName(Classifier entity,OJClass ojClass){
		OJOperation getName = new OJAnnotatedOperation("getName");
		getName.setReturnType(new OJPathName("String"));
		getName.setBody(new OJBlock());
		getName.getBody().addToStatements("return \"" + entity.getName() + "[\"+getId()+\"]\"");
		ojClass.addToOperations(getName);
	}
	@Override
	protected void visitProperty(OJAnnotatedClass c,Classifier owner,PropertyMap buildStructuralFeatureMap){
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass c,Classifier umlOwner){
		visitClass(c, umlOwner);
		return false;
	}
}
