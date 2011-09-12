package org.nakeduml.java.metamodel.annotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJElement;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.utilities.JavaStringHelpers;
import org.nakeduml.java.metamodel.utilities.JavaUtil;
import org.nakeduml.java.metamodel.utilities.OJOperationComparator;

public class OJAnnotatedClass extends OJClass implements OJAnnotatedElement{
	Set<OJAnnotationValue> f_annotations = new HashSet<OJAnnotationValue>();
	private List<String> genericTypeParams = new ArrayList<String>();
	public OJAnnotatedClass(String string){
		setName(string);
	}
	public OJAnnotationValue findAnnotation(OJPathName path){
		return AnnotationHelper.getAnnotation(this, path);
	}
	public boolean addAnnotationIfNew(OJAnnotationValue value){
		return AnnotationHelper.maybeAddAnnotation(value, this);
	}
	public Set<OJAnnotationValue> getAnnotations(){
		return f_annotations;
	}
	public OJAnnotationValue putAnnotation(OJAnnotationValue value){
		return AnnotationHelper.putAnnotation(value, this);
	}
	public OJAnnotationValue removeAnnotation(OJPathName type){
		return AnnotationHelper.removeAnnotation(this, type);
	}
	public OJConstructor findConstructor(OJPathName parameter1){
		List<OJPathName> pathnames = Collections.singletonList(parameter1);
		return findConstructor(pathnames);
	}
	// public?
	private OJConstructor findConstructor(List<OJPathName> pathnames){
		for(OJConstructor con:getConstructors()){
			if(isMatch(pathnames, con.getParamTypes())){
				return con;
			}
		}
		return null;
	}
	private static boolean isMatch(List<OJPathName> pathnames,List<OJPathName> paramTypes){
		if(paramTypes.size() == pathnames.size()){
			for(int i = 0;i < paramTypes.size();i++){
				if(!paramTypes.get(i).equals(pathnames.get(0))){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public void calcImports(){
		super.calcImports();
		addToImports(AnnotationHelper.getImportsFrom(getFields()));
		addToImports(AnnotationHelper.getImportsFrom(getOperations()));
		addToImports(AnnotationHelper.getImportsFrom(this));
	}
	@Override
	public String toJavaString(){
		calcImports();
		StringBuilder classInfo = new StringBuilder();
		classInfo.append(getMyPackage().toJavaString());
		classInfo.append("\n");
		classInfo.append(imports());
		classInfo.append("\n");
		addJavaDocComment(classInfo);
		if(getAnnotations().size() > 0){
			classInfo.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(getAnnotations(), "\n"), 0));
			classInfo.append("\n");
		}
		if(isAbstract()){
			classInfo.append("abstract ");
		}
		classInfo.append(visToJava(this) + " ");
		classInfo.append("class ");
		classInfo.append(getName());
		Iterator<String> it = this.genericTypeParams.iterator();
		if(it.hasNext()){
			classInfo.append("<");
		}
		while(it.hasNext()){
			classInfo.append(it.next());
			if(it.hasNext()){
				classInfo.append(",");
			}else{
				classInfo.append(">");
			}
		}
		if(getSuperclass() != null){
			classInfo.append(" extends " + getSuperclass().getLast());
		}
		classInfo.append(implementedInterfaces());
		classInfo.append(" {\n");
		classInfo.append(JavaStringHelpers.indent(fields(), 1));
		classInfo.append("\n\n");
		classInfo.append(JavaStringHelpers.indent(constructors(), 1));
		classInfo.append("\n");
		classInfo.append(JavaStringHelpers.indent(operations(), 1));
		classInfo.append("\n}");
		return classInfo.toString();
	}
	public StringBuilder operations(){
		List<OJOperation> temp = new ArrayList<OJOperation>(this.getOperations());
		Collections.sort(temp, new OJOperationComparator());
		StringBuilder result = new StringBuilder();
		result.append(JavaUtil.collectionToJavaString(temp, "\n"));
		return result;
	}
	protected StringBuilder constructors(){
		StringBuilder result = new StringBuilder();
		result.append(JavaUtil.collectionToJavaString(new HashSet<OJElement>(this.getConstructors()), "\n"));
		return result;
	}
	public StringBuilder fields(){
		StringBuilder result = new StringBuilder();
		result.append(JavaUtil.collectionToJavaString(this.getFields(), "\n"));
		return result;
	}
	protected StringBuilder implementedInterfaces(){
		StringBuilder result = new StringBuilder();
		if(!this.getImplementedInterfaces().isEmpty())
			result.append(" implements ");
		Iterator<OJPathName> it = getImplementedInterfaces().iterator();
		while(it.hasNext()){
			OJPathName elem = it.next();
			result.append(elem.getLast());
			if(it.hasNext())
				result.append(", ");
		}
		return result;
	}
	public void addGenericTypeParam(String string){
		this.genericTypeParams.add(string);
	}
	@Override
	protected void addJavaDocComment(StringBuilder result){
		if(getComment() != null && !getComment().equals("")){
			String comment = JavaStringHelpers.firstCharToUpper(getComment());
			result.append("/** " + comment);
			result.append("\n */\n");
		}
	}
	public boolean hasAnnotation(OJPathName pathName){
		for(OJAnnotationValue v:this.f_annotations){
			if(v.getType().equals(pathName)){
				return true;
			}
		}
		return false;
	}
	public OJAnnotatedClass getDeepCopy(OJPackage owner){
		OJAnnotatedClass copy = new OJAnnotatedClass(getName());
		copy.setMyPackage(owner);
		copyDeepInfoInto(copy);
		return copy;
	}
	protected void copyDeepInfoInto(OJAnnotatedClass copy){
		super.copyDeepInfoInto(copy);
		Set<OJPathName> interfaces = getImplementedInterfaces();
		for(OJPathName ojInterface:interfaces){
			OJPathName copyInterface = ojInterface.getCopy();
			copy.addToImplementedInterfaces(copyInterface);
		}
		Set<OJAnnotationValue> annotations = getAnnotations();
		for(OJAnnotationValue ojAnnotationValue:annotations){
			OJAnnotationValue copyAnnotation = ojAnnotationValue.getDeepCopy();
			copy.addAnnotationIfNew(copyAnnotation);
		}
	}
	public void renameAll(Map<String,OJPathName> renamePathNames,String newName){
		setName(getName() + newName);
		Collection<OJConstructor> constructors = getConstructors();
		for(OJConstructor ojConstructor:constructors){
			ojConstructor.renameAll(renamePathNames, newName);
		}
		// This is a jipo to make sure imports are correct.
		// renaming OJForStatement does not seem to add the renamed paths to the
		// imports
		Collection<OJPathName> newImports = new HashSet<OJPathName>();
		Collection<OJPathName> imports = getImports();
		for(OJPathName ojPathName:imports){
			OJPathName newImport = ojPathName.getDeepCopy();
			newImport.renameAll(renamePathNames, newName);
			newImports.add(newImport);
		}
		addToImports(newImports);
		Collection<OJAnnotationValue> annotations = getAnnotations();
		for(OJAnnotationValue ojAnnotationValue:annotations){
			ojAnnotationValue.renameAll(renamePathNames, newName);
		}
		if(getSuperclass() != null){
			getSuperclass().renameAll(renamePathNames, newName);
		}
		Collection<OJPathName> implementedInterfaces = getImplementedInterfaces();
		for(OJPathName ojPathName:implementedInterfaces){
			ojPathName.renameAll(renamePathNames, newName);
		}
		Collection<OJField> fields = getFields();
		for(OJField ojField:fields){
			ojField.renameAll(renamePathNames, newName);
		}
		Collection<OJOperation> operations = getOperations();
		for(OJOperation ojOperation:operations){
			ojOperation.renameAll(renamePathNames, newName);
		}
	}
	public String toString(){
		return getPathName().toString();
	}
	public void removeFromOperations(String internalRemover,List<OJPathName> singletonList){
		Iterator<OJOperation> iterator = f_operations.iterator();
		while(iterator.hasNext()){
			OJOperation ojOperation = (OJOperation) iterator.next();
			if(ojOperation.getName().equals(internalRemover)){
				iterator.remove();
				break;
			}
		}
	}
	public void removeFromFields(String name){
		super.f_fields.remove(name);
	}
}