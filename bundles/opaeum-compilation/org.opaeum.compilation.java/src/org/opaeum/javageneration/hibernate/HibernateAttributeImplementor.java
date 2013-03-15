package org.opaeum.javageneration.hibernate;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;

@StepDependency(phase = JavaTransformationPhase.class,replaces = AttributeImplementor.class,requires=HibernateAssociationClassAttributeImplementor.class, after=HibernateAssociationClassAttributeImplementor.class)
public class HibernateAttributeImplementor extends AttributeImplementor{
	ThreadLocal<Boolean> isExternalValue = new ThreadLocal<Boolean>();
	ThreadLocal<Boolean> isInterfaceValue = new ThreadLocal<Boolean>();
	@Override
	protected void visitProperty(OJAnnotatedClass owner,Classifier umlOwner,PropertyMap map){
		isExternalValue.set(HibernateUtil.isExternalValue(owner, map));
		isInterfaceValue.set(HibernateUtil.isInterfaceValue(owner, map));
		super.visitProperty(owner, umlOwner, map);
	}
	@Override
	protected OJAnnotatedOperation buildGetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map,boolean derived){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return HibernateUtil.getFromInterfaceValue(umlOwner, owner, map, isExternalValue.get(), getLibrary());
		}else{
			return super.buildGetter(umlOwner, owner, map, derived);
		}
	}
	@Override
	protected OJAnnotatedOperation buildInternalRemover(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return HibernateUtil.removeFromInterfaceValue(owner, map);
		}else{
			return super.buildInternalRemover(owner, map);
		}
	}
	@Override
	protected OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return HibernateUtil.addToInterfaceValue(owner, map, isExternalValue.get());
		}else{
			return super.buildInternalAdder(owner, map);
		}
	}
	@Override
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,PropertyMap map){
		if(isInterfaceValue.get() || isExternalValue.get()){
			return HibernateUtil.putField(owner, map, isExternalValue.get());
		}else{
			return super.buildField(owner, map);
		}
	}
}
