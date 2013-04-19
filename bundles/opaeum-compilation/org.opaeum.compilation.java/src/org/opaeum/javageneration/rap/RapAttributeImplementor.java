package org.opaeum.javageneration.rap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.uml2.uml.Classifier;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.StepDependency.StrategyRequirement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.AttributeStrategy;
import org.opaeum.javageneration.hibernate.HibernateAttributeStrategy;

@StepDependency(phase = JavaTransformationPhase.class,replaces = AttributeImplementor.class,strategyRequirement = @StrategyRequirement(strategyContract = AttributeStrategy.class,replaces = HibernateAttributeStrategy.class,requires = RapAttributeStrategy.class))
public class RapAttributeImplementor extends AttributeImplementor{
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojClass,Classifier umlOwner){
		OJAnnotatedField support = new OJAnnotatedField("propertyChangeSupport", new OJPathName(PropertyChangeSupport.class.getName()));
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
		return super.visitComplexStructure(ojClass, umlOwner);
	}
}
