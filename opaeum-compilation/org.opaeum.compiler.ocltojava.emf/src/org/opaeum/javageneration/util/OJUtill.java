package org.opaeum.javageneration.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UseCase;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.eclipse.emulated.InverseArtificialProperty;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedElement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.name.NameConverter;

public class OJUtill{
	static final Set<String> BUILT_IN_ATTRIBUTES = new HashSet<String>(3);
	protected static final String MAPPINGS_EXTENSION = "mappings";
	static{
		BUILT_IN_ATTRIBUTES.add("now");
		BUILT_IN_ATTRIBUTES.add("currentUser");
		BUILT_IN_ATTRIBUTES.add("today");
	}
	static Set<String> javaKeyWords = new HashSet<String>(21);
	static{
		javaKeyWords.add("class");
		javaKeyWords.add("interface");
		javaKeyWords.add("enum");
		javaKeyWords.add("int");
		javaKeyWords.add("void");
		javaKeyWords.add("long");
		javaKeyWords.add("short");
		javaKeyWords.add("char");
		javaKeyWords.add("byte");
		javaKeyWords.add("double");
		javaKeyWords.add("float");
		javaKeyWords.add("boolean");
		javaKeyWords.add("for");
		javaKeyWords.add("while");
		javaKeyWords.add("if");
		javaKeyWords.add("do");
		javaKeyWords.add("goto");
		javaKeyWords.add("else");
		javaKeyWords.add("switch");
		javaKeyWords.add("case");
		javaKeyWords.add("default");
		javaKeyWords.add("return");
	}

	public static boolean isBuiltIn(TypedElement f){
		return BUILT_IN_ATTRIBUTES.contains(f.getName());
	}

	public static OJAnnotatedOperation buildMain(OJAnnotatedClass ojClass){
		OJAnnotatedOperation main = new OJAnnotatedOperation("main");
		main.setStatic(true);
		main.addParam("args", new OJPathName("String[]"));
		ojClass.addToOperations(main);
		return main;
	}

	public static final OJOperation addMethod(OJClass theClass,String name,String type,String expression){
		OJOperation get = theClass.getUniqueOperation(name);
		if(get == null){
			get = new OJAnnotatedOperation(name);
			theClass.addToOperations(get);
		}else{
			get.setBody(new OJBlock());
		}
		get.setName(name);
		get.setReturnType(new OJPathName(type));
		get.getBody().addToStatements("return " + expression);
		return get;
	}

	public static void addConstructor(OJClass ojClass,OJField...params){
		OJConstructor constructor = new OJConstructor();
		for(OJField ojField:params){
			constructor.addParam(ojField.getName(), ojField.getType());
			OJSimpleStatement setField = new OJSimpleStatement("this." + ojField.getName() + " = " + ojField.getName());
			constructor.getBody().addToStatements(setField);
		}
		constructor.setOwningClass(ojClass);
	}

	public static OJAnnotatedField addPersistentProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
		return addProperty(ojClass, name, type, withBody);
	}

	public static OJAnnotatedField addTransientProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
		OJAnnotatedField result = addProperty(ojClass, name, type, withBody);
		result.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		return result;
	}

	private static OJAnnotatedField addProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
		ojClass.addToImports(type);
		String capped = NameConverter.capitalize(name);
		OJOperation set = new OJAnnotatedOperation("set" + capped);
		set.addParam(name, type);
		set.setBody(new OJBlock());
		ojClass.addToOperations(set);
		OJOperation get = new OJAnnotatedOperation("get" + capped);
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

	public static void addFailedConstraints(OJOperation execute){
		// String failedConstraints = UtilityCreator.getUtilPathName() + ".FailedConstraintsException";
		execute.getOwner().addToImports("org.opaeum.runtime.domain.FailedConstraintsException");
		execute.addToThrows("org.opaeum.runtime.domain.FailedConstraintsException");
	}

	/**
	 * Some classifiers in UML would not necessarily be generated as Java classes. Returns false for NakedBehaviors that have one or less
	 * resulting parameters
	 * 
	 */
	public static boolean hasOJClass(Classifier c){
		if(c == null || c instanceof Stereotype || c instanceof Collaboration || c instanceof UseCase){
			return false;
		}else if(EmfElementUtil.isMarkedForDeletion(c) || EmfClassifierUtil.getCodeGenerationStrategy(c) == CodeGenerationStrategy.NO_CODE){
			return false;
		}else if(c instanceof DataType){
			return !EmfClassifierUtil.isSimpleType(c);
		}else if(c instanceof Behavior){
			return EmfBehaviorUtil.hasExecutionInstance((Behavior) c);
		}else if(c instanceof Association){
			return EmfAssociationUtil.isClass((Association) c);
		}else if(c instanceof IEmulatedElement){
			return ((IEmulatedElement) c).shouldEmulate();
		}else{
			return true;
		}
	}

	public static void removeReturnStatement(OJOperation javaMethod){
		List<OJStatement> sts = new ArrayList<OJStatement>(javaMethod.getBody().getStatements());
		OJStatement last = sts.get(sts.size() - 1);
		if(last.toJavaString().startsWith("return ")){
			javaMethod.getBody().removeFromStatements(last);
		}
	}

	public static void addMetaInfo(OJAnnotatedElement element,Element property){
		if(!(property instanceof InverseArtificialProperty)){
			OJAnnotationValue metaInfo = new OJAnnotationValue(new OJPathName(NumlMetaInfo.class.getName()));
			metaInfo.putAttribute("uuid", EmfWorkspace.getId(property));
			element.putAnnotation(metaInfo);
		}
	}

	public static void addField(OJEnum ojEnum,OJConstructor constr,String name,OJPathName type){
		OJAnnotatedOperation getter = new OJAnnotatedOperation("get" + NameConverter.capitalize(name), type);
		getter.getBody().addToStatements("return this." + name);
		ojEnum.addToOperations(getter);
		constr.addParam(name, type);
		constr.getBody().addToStatements("this." + name + "=" + name);
		OJAnnotatedField field = new OJAnnotatedField(name, type);
		ojEnum.addToFields(field);
	}

	public static void addParameter(OJEnumLiteral l,String name,String value){
		OJAnnotatedField persistentName = new OJAnnotatedField(name, null);
		persistentName.setName(name);
		persistentName.setInitExp(value);
		l.addToAttributeValues(persistentName);
	}

	public static String toJavaLiteral(EnumerationLiteral l){
		return l.getName().toUpperCase();
	}

	public static boolean isJavaKeyword(String name){
		return javaKeyWords.contains(name);
	}

	public OJUtill(){
		super();
	}
}