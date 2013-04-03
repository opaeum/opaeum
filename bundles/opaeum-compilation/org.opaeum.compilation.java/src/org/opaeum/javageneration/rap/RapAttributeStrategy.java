package org.opaeum.javageneration.rap;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.hibernate.HibernateAttributeStrategy;

public class RapAttributeStrategy extends HibernateAttributeStrategy{
	@Override
	public OJAnnotatedOperation buildInternalAdder(OJAnnotatedClass owner,PropertyMap map){
		return super.buildInternalAdder(owner, map);
	}
	@Override
	public void startSetter(OJAnnotatedClass owner,OJAnnotatedOperation setter,PropertyMap map){
		if(!map.isStatic()){
			if(owner.findField("propertyChangeSupport") != null){
				setter.getBody().addToStatements(0,
						new OJSimpleStatement("propertyChangeSupport.firePropertyChange(\"" + map.umlName() + "\"," + map.getter() + "()," + map.fieldname() + ")"));
			}
		}
	}
}
