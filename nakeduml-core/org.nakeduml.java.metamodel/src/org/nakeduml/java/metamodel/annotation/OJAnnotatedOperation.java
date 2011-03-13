package org.nakeduml.java.metamodel.annotation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nakeduml.java.metamodel.OJInterface;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.utilities.JavaStringHelpers;
import org.nakeduml.java.metamodel.utilities.JavaUtil;


public class OJAnnotatedOperation extends OJOperation implements OJAnnotatedElement{
	public OJAnnotatedOperation(String string, OJPathName ojPathName) {
		this(string);
		setReturnType(ojPathName);
	}
	public OJAnnotatedOperation(String string) {
		super();
		setName(string);
	}
	public OJAnnotatedOperation() {
		super();
	}
	@Override
	public OJAnnotatedOperation getCopy(){
		OJAnnotatedOperation oper = new OJAnnotatedOperation();
		super.copyValues(oper);
		oper.removeAllFromParameters();
		for(OJParameter f:getParameters()){
			oper.addToParameters((OJParameter) f.getCopy());
		}
		for(OJAnnotationValue v:getAnnotations()){
			oper.addAnnotationIfNew(v.getCopy());
		}
		// TODO copy exception
		return oper;
	}
	Set<OJAnnotationValue> f_annotations = new HashSet<OJAnnotationValue>();
	public boolean addAnnotationIfNew(OJAnnotationValue value){
		return AnnotationHelper.maybeAddAnnotation(value, this);
	}
	public OJAnnotationValue removeAnnotation(OJPathName type){
		return AnnotationHelper.removeAnnotation(this,type);
	}
	public Set<OJAnnotationValue> getAnnotations(){
		return f_annotations;
	}
	public OJAnnotationValue putAnnotation(OJAnnotationValue value){
		return AnnotationHelper.putAnnotation(value, this);
	}
	@Override
	public String toJavaString(){
		StringBuilder result = new StringBuilder();
		if(!getComment().equals("")){
			addJavaDocComment(result);
		}
		if(this.getNeedsSuppress()){
			result.append("@SuppressWarnings(\"unchecked\")\n");
		}
		if(getAnnotations().size() > 0){
			result.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(getAnnotations(), "\n"), 0));
			result.append("\n");
		}
		// signature
		if(this.isAbstract()){
			result.append("abstract ");
		}
		result.append(visToJava(this) + " ");
		if(this.getGenericTypeParam() != null){
			result.append("<" + this.getGenericTypeParam().getLast() + "> ");
		}
		result.append(getReturnType().getCollectionTypeName());
		result.append(" " + getName());
		// params
		result.append("(" + paramsToJava(this) + ")");
		if(!getThrows().isEmpty()){
			result.append(" throws " + exceptionsToJava(this));
		}
		if(getOwner() instanceof OJAnnotatedInterface || getOwner() instanceof OJInterface || this.isAbstract()){
			result.append(";\n");
		}else{
			result.append(" {\n");
			StringBuilder bodyStr = new StringBuilder();
			String actualBody = getBody().toJavaString();
			bodyStr.append(actualBody);
			result.append(JavaStringHelpers.indent(bodyStr, 1));
			if(result.charAt(result.length() - 1) == '\n'){
				result.deleteCharAt(result.length() - 1);
			}
			// closing bracket
			result.append("\n}\n");
		}
		return result.toString();
	}
	
	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		super.renameAll(renamePathNames, newName);
		for(OJAnnotationValue annotation: getAnnotations()) {
			annotation.renameAll(renamePathNames, newName);
		}
	}
	
	public OJOperation getDeepCopy() {
		OJAnnotatedOperation result = new OJAnnotatedOperation();
		copyValuesDeep(result);
		return result;
	}
	@Override
	public OJAnnotationValue findAnnotation(OJPathName ojPathName) {
		return AnnotationHelper.getAnnotation(this, ojPathName);
	}		
}
