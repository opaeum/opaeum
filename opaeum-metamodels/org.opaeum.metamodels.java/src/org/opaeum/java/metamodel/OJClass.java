package org.opaeum.java.metamodel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.opaeum.java.metamodel.generated.OJClassGEN;
import org.opaeum.java.metamodel.utilities.JavaStringHelpers;
import org.opaeum.java.metamodel.utilities.JavaUtil;

public class OJClass extends OJClassGEN{
	public OJClass(){
		super();
		this.setVisibility(OJVisibilityKind.PUBLIC);
	}
	public OJClass(String name,String comment,boolean isStatic,boolean isFinal,boolean isVolatile,int uniqueNumber,boolean isAbstract,
			boolean isDerived){
		// super(name, comment, isStatic, isFinal, isVolatile, uniqueNumber, isAbstract, isDerived);
	}
	public OJClass getDeepCopy(OJPackage owner){
		OJClass copy = new OJClass();
		copy.setMyPackage(owner);
		copyDeepInfoInto(copy);
		return copy;
	}
	protected void copyDeepInfoInto(OJClass copy){
		super.copyDeepInfoInto(copy);
		Collection<OJConstructor> constructors = getConstructors();
		for(OJConstructor ojConstructor:constructors){
			OJConstructor copyConstructor = ojConstructor.getDeepConstructorCopy();
			copyConstructor.setReturnType(copy.getPathName());
			copy.addToConstructors(copyConstructor);
		}
		if(getSuperclass() != null){
			OJPathName superClassCopy = getSuperclass().getDeepCopy();
			copy.setSuperclass(superClassCopy);
		}
		Collection<OJField> fields = getFields();
		for(OJField ojField:fields){
			OJField ojFieldCopy = (OJField) ojField.getDeepCopy();
			ojFieldCopy.setOwner(copy);
			copy.addToFields(ojFieldCopy);
		}
	}
	public void calcImports(){
		super.calcImports();
		for(OJField f:getFields()){
			this.addToImports(f.getType());
		}
		for(OJPathName intf:getImplementedInterfaces()){
			this.addToImports(intf);
		}
		for(OJConstructor constr:getConstructors()){
			for(OJPathName pn:constr.getParamTypes()){
				this.addToImports(pn);
			}
		}
		this.addToImports(this.getSuperclass());
	}
	public OJConstructor getDefaultConstructor(){
		OJConstructor result = super.getDefaultConstructor();
		if(result == null){
			OJConstructor constructor = new OJConstructor();
			constructor.setBody(new OJBlock());
			constructor.setComment("default constructor for " + this.getName());
			this.addToConstructors(constructor);
			result = constructor;
		}
		return result;
	}
	public String toJavaString(){
		this.calcImports();
		StringBuilder classInfo = new StringBuilder();
		classInfo.append(getMyPackage().toJavaString());
		classInfo.append("\n");
		classInfo.append(imports());
		classInfo.append("\n");
		if(!getComment().equals("")){
			addJavaDocComment(classInfo);
		}
		if(this.getNeedsSuppress()){
			classInfo.append("@SuppressWarnings(\"serial\")\n");
		}
		if(this.isAbstract()){
			classInfo.append("abstract ");
		}
		classInfo.append(visToJava(this) + " ");
		classInfo.append("class " + getName());
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
	/**
	 * @return
	 */
	private StringBuilder constructors(){
		StringBuilder result = new StringBuilder();
		result.append(JavaUtil.collectionToJavaString(this.getConstructors(), "\n"));
		return result;
	}
	/**
	 * @return
	 */
	private StringBuilder fields(){
		StringBuilder result = new StringBuilder();
		result.append(JavaUtil.collectionToJavaString(this.getFields(), "\n"));
		return result;
	}
	/**
	 * @return
	 */
	private StringBuilder implementedInterfaces(){
		StringBuilder result = new StringBuilder();
		if(!this.getImplementedInterfaces().isEmpty())
			result.append(" implements ");
		Iterator it = getImplementedInterfaces().iterator();
		while(it.hasNext()){
			OJPathName elem = (OJPathName) it.next();
			result.append(elem.getLast());
			if(it.hasNext())
				result.append(", ");
		}
		return result;
	}
	/**
	 * @param string
	 * @return
	 */
	public OJField findField(String name){
		return f_fields.get(name);
	}
	@Override
	public void renameAll(Set<OJPathName> renamePathNames,String suffix){
		super.renameAll(renamePathNames, suffix);
		Collection<OJConstructor> constructors = getConstructors();
		for(OJConstructor ojConstructor:constructors){
			ojConstructor.renameAll(renamePathNames, suffix);
		}
		// This is a jipo to make sure imports are correct.
		// renaming OJForStatement does not seem to add the renamed paths to the
		// imports
		Collection<OJPathName> newImports = new HashSet<OJPathName>();
		Collection<OJPathName> imports = getImports();
		for(OJPathName ojPathName:imports){
			OJPathName newImport = ojPathName.getDeepCopy();
			newImport.renameAll(renamePathNames, suffix);
			newImports.add(newImport);
		}
		addToImports(newImports);
		if(getSuperclass() != null){
			getSuperclass().renameAll(renamePathNames, suffix);
		}
		Collection<OJPathName> implementedInterfaces = getImplementedInterfaces();
		for(OJPathName ojPathName:implementedInterfaces){
			ojPathName.renameAll(renamePathNames, suffix);
		}
		Collection<OJField> fields = getFields();
		for(OJField ojField:fields){
			ojField.renameAll(renamePathNames, suffix);
		}
	}
	public void release(){
		setMyPackage(null);
		f_fields.clear();
		f_operations.clear();
		super.removeAllFromConstructors();
		super.removeAllFromImports();
	}
}