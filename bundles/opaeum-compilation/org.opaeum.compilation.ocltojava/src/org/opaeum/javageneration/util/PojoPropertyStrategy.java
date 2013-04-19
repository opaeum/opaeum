package org.opaeum.javageneration.util;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.name.NameConverter;

public class PojoPropertyStrategy implements PropertyStrategy{
	public OJAnnotatedField addPersistentProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
		return addProperty(ojClass, name, type, withBody);
	}

	public OJAnnotatedField addTransientProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
		OJAnnotatedField result = addProperty(ojClass, name, type, withBody);
//		result.setTransient(true);
		return result;
	}

	protected OJAnnotatedField addProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
		ojClass.addToImports(type);
		String capped = NameConverter.capitalize(name);
		OJOperation set = new OJAnnotatedOperation("set" + capped);
		set.addParam(name, type);
		set.setBody(new OJBlock());
		ojClass.addToOperations(set);
		String getPrefix = type.getLast().equals("boolean")?"is": "get";
		OJOperation get = new OJAnnotatedOperation(getPrefix + capped);
		get.setReturnType(type);
		get.setBody(new OJBlock());
		ojClass.addToOperations(get);
		if(withBody){
			set.getBody().addToStatements("this." + name + "=" + name);
			get.getBody().addToStatements("return this." + name);
			OJAnnotatedField field = new OJAnnotatedField(name, type);
			((OJClass) ojClass).addToFields(field);
			return field;
		}
		return null;
	}

}
