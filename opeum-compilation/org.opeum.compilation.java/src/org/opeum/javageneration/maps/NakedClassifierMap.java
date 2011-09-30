package org.opeum.javageneration.maps;

import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedClassifier;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;

import org.opeum.java.metamodel.OJPathName;

public class NakedClassifierMap extends ClassifierMap{
	public NakedClassifierMap(IClassifier modelClass){
		super(modelClass);
		// NB!! remember that modelClass could be a collectionType
	}
	@Override
	protected ClassifierMap newClassifierMap(IClassifier elementType){
		return new NakedClassifierMap(elementType);
	}
	@Override
	public boolean isJavaPrimitive(){
		String javatype = javaType();
		return javatype.equals("int") || javatype.equals("double") || javatype.equals("float") || javatype.equals("long") || javatype.equals("boolean");
	}
	protected OJPathName pathname(IModelElement t){
		if(t instanceof INakedClassifier){
			return OJUtil.classifierPathname((INakedClassifier) t);
		}else{
			return super.pathname(t);
		}
	}

}