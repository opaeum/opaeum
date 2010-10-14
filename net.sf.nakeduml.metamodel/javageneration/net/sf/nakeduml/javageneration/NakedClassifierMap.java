package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;

public class NakedClassifierMap extends ClassifierMap{
	private OJPathName myDefaultTypePath;
	private String myDefaultValue;
	public NakedClassifierMap(IClassifier modelClass){
		super(modelClass);
		// NB!! remember that modelClass could be a collectionType
	}
	@Override
	public OJPathName javaTypePath(){
		if(super.isCollection()){
			IClassifier elementType = ((ICollectionType) modelClass).getElementType();
			OJPathName elementPath = null;
			if(elementType instanceof INakedClassifier){
				elementPath = OJUtil.classifierPathname((INakedClassifier) elementType);
			}else{
				elementPath = super.javaElementTypePath();
			}
			OJPathName pathName = super.javaTypePath().getCopy();
			pathName.removeAllFromElementTypes();
			pathName.addToElementTypes(elementPath);
			return pathName;
		}else if(modelClass instanceof INakedClassifier){
			return OJUtil.classifierPathname((INakedClassifier) modelClass);
		}else{
			return super.javaTypePath().getCopy();
		}
	}
	@Override
	public boolean isJavaPrimitive(){
		String javatype = javaType();
		return javatype.equals("int") || javatype.equals("double") || javatype.equals("float") || javatype.equals("long")
				|| javatype.equals("boolean");
	}
	@Override
	public OJPathName javaDefaultTypePath(){
		if(myDefaultTypePath == null){
			if(modelClass instanceof INakedClassifier){
				myDefaultTypePath = OJUtil.classifierPathname((INakedClassifier) modelClass);
			}else{
				myDefaultTypePath = super.javaDefaultTypePath();
			}
		}
		return myDefaultTypePath;
	}
	@Override
	public String javaDefaultValue(){
		if(myDefaultValue == null){
			myDefaultValue = super.javaDefaultValue();
			if(isJavaPrimitive()){
				if(javaType().equals("long")){
					myDefaultValue=myDefaultValue+"L";
				}
				if(javaType().equals("double")){
					myDefaultValue=myDefaultValue+"D";
				}
				if(javaType().equals("float")){
					myDefaultValue=myDefaultValue+"F";
				}
			}
		}
		return myDefaultValue;
	}
}
