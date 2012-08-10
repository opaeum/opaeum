package org.opaeum.javageneration.persistence;

import javax.persistence.Transient;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

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
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
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
		if(OJUtil.hasOJClass(a) && !EmfClassifierUtil.isHelper(a)){
			OJAnnotatedInterface asdf = (OJAnnotatedInterface) findJavaClass(a);
			asdf.addToSuperInterfaces(new OJPathName(IPersistentObject.class.getName()));
		}
	}
	private void visitClass(Classifier c){
		OJClassifier ojClassifier = super.findJavaClass(c);
		if(ojClassifier instanceof OJAnnotatedInterface){
			if(!EmfClassifierUtil.isHelper(c)){
				((OJAnnotatedInterface) ojClassifier).addToSuperInterfaces(ABSTRACT_ENTITY);
				ojClassifier.addToImports(ABSTRACT_ENTITY);
			}
		}else if(ojClassifier instanceof OJClass){
			if(c instanceof Class && EmfClassifierUtil.getPrimaryKeyProperties((Class) c).size() > 0){
				return;
			}else{
				OJClass ojClass = (OJClass) ojClassifier;
				if(isPersistent(c)){
					OJAnnotatedField persistence = new OJAnnotatedField("persistence", new OJPathName(AbstractPersistence.class.getName()));
					persistence.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(Transient.class.getName())));
					ojClass.addToFields(persistence);
					ojClass.addToImports(ABSTRACT_ENTITY);
					if(c.getAttribute("name", null) == null){
						addGetName(c, ojClass);
					}
					ojClass.addToImplementedInterfaces(ABSTRACT_ENTITY);
					if(c instanceof Class){
						addDiscriminatorInitialization((Class) c, ojClass);
					}
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
							+ EmfClassifierUtil.getPowerTypeLiteral(generalization, powerType).getName().toUpperCase();
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
	protected void visitProperty(Classifier owner,StructuralFeatureMap buildStructuralFeatureMap){
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		visitClass(umlOwner);
	}
}
