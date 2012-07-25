package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;

public class NakedClassifierMap extends ClassifierMap{
	public NakedClassifierMap(Classifier modelClass, CollectionKind kind){
		super(modelClass,kind);
		// NB!! remember that modelClass could be a collectionType
	}
	@Override
	protected ClassifierMap newClassifierMap(Classifier elementType){
		return OJUtil.buildClassifierMap(elementType,(CollectionKind) null);
	}
	@Override
	public boolean isJavaPrimitive(){
		String javatype = javaType();
		return javatype.equals("int") || javatype.equals("double") || javatype.equals("float") || javatype.equals("long") || javatype.equals("boolean");
	}
	protected OJPathName pathname(NamedElement t){
		if(t instanceof Classifier){
			return OJUtil.classifierPathname((Classifier) t);
		}else{
			return super.pathname(t);
		}
	}

}
