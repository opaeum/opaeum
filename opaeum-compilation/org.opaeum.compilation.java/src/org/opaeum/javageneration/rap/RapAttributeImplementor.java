package org.opaeum.javageneration.rap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.hibernate.HibernateAttributeImplementor;

@StepDependency(phase = JavaTransformationPhase.class,replaces = HibernateAttributeImplementor.class)
public class RapAttributeImplementor extends HibernateAttributeImplementor{
	@Override
	protected OJOperation buildSetter(Classifier umlOwner,OJAnnotatedClass owner,PropertyMap map){
		OJOperation setter = super.buildSetter(umlOwner, owner, map);
		if(owner.findField("propertyChangeSupport") != null){
			setter.getBody().addToStatements(
					0,
					new OJSimpleStatement("propertyChangeSupport.firePropertyChange(\"" + map.umlName() + "\"," + map.getter() + "(),"
							+ map.fieldname() + ")"));
		}
		return setter;
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		OJAnnotatedField support = new OJAnnotatedField("propertyChangeSupport", new OJPathName(PropertyChangeSupport.class.getName()));
		OJAnnotatedClass ojClass = findJavaClass(umlOwner);
		ojClass.addToFields(support);
		support.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		support.setInitExp("new PropertyChangeSupport(this)");
		OJAnnotatedOperation remove = new OJAnnotatedOperation("removePropertyChangeListener");
		ojClass.addToOperations(remove);
		remove.addParam("property", new OJPathName("String"));
		remove.addParam("listener", new OJPathName(PropertyChangeListener.class.getName()));
		remove.getBody().addToStatements("propertyChangeSupport.removePropertyChangeListener(property,listener)");
		OJAnnotatedOperation add = new OJAnnotatedOperation("addPropertyChangeListener");
		ojClass.addToOperations(add);
		add.addParam("property", new OJPathName("String"));
		add.addParam("listener", new OJPathName(PropertyChangeListener.class.getName()));
		add.getBody().addToStatements("propertyChangeSupport.addPropertyChangeListener(property,listener)");
		// TODO Auto-generated method stub
		super.visitComplexStructure(umlOwner);
	}
}
