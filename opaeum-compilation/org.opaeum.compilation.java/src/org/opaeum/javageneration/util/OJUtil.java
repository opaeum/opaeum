package org.opaeum.javageneration.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.IActionWithTargetElement;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.compositestructures.INakedCollaboration;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.core.internal.InverseArtificialProperty;
import org.opaeum.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.metamodel.profiles.INakedStereotype;
import org.opaeum.metamodel.usecases.INakedActor;
import org.opaeum.metamodel.usecases.INakedUseCase;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.validation.namegeneration.AbstractJavaNameGenerator;
import org.opeum.annotation.NumlMetaInfo;
import org.opeum.name.NameConverter;

public class OJUtil{
	public static void clearCache(){
		structuralFeatureMaps.clear();
		locallyUniqueFeatureMaps.clear();
		actionFeatureMaps.clear();
	}
	private static final Set<String> BUILT_IN_ATTRIBUTES = new HashSet<String>();
	static{
		BUILT_IN_ATTRIBUTES.add("now");
		BUILT_IN_ATTRIBUTES.add("currentUser");
		BUILT_IN_ATTRIBUTES.add("today");
	}
	private static Map<INakedTypedElement,NakedStructuralFeatureMap> locallyUniqueFeatureMaps = new HashMap<INakedTypedElement,NakedStructuralFeatureMap>();
	private static Map<INakedTypedElement,NakedStructuralFeatureMap> structuralFeatureMaps = new HashMap<INakedTypedElement,NakedStructuralFeatureMap>();
	private static Map<INakedAction,NakedStructuralFeatureMap> actionFeatureMaps = new HashMap<INakedAction,NakedStructuralFeatureMap>();
	public static boolean isBuiltIn(INakedTypedElement f){
		return BUILT_IN_ATTRIBUTES.contains(f.getName());
	}
	public static OJPathName utilPackagePath(INakedElementOwner e){
		return new OJPathName(e.getMappingInfo().getQualifiedJavaName()).append("util");
	}
	public static OJAnnotatedOperation buildMain(OJAnnotatedClass ojClass){
		OJAnnotatedOperation main = new OJAnnotatedOperation("main");
		main.setStatic(true);
		main.addParam("args", new OJPathName("String[]"));
		ojClass.addToOperations(main);
		return main;
	}
	public static synchronized NakedStructuralFeatureMap buildStructuralFeatureMap(INakedClassifier owner,INakedTypedElement typedAndOrdered){
		NakedStructuralFeatureMap linkedParameter;
		if(typedAndOrdered instanceof INakedProperty){
			linkedParameter = OJUtil.buildStructuralFeatureMap((INakedProperty) typedAndOrdered);
		}else{
			NakedStructuralFeatureMap map = structuralFeatureMaps.get(typedAndOrdered);
			if(map == null){
				map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(owner, typedAndOrdered));
				structuralFeatureMaps.put(typedAndOrdered, map);
			}
			return map;
		}
		return linkedParameter;
	}
	public static synchronized NakedStructuralFeatureMap buildStructuralFeatureMap(INakedProperty sf){
		NakedStructuralFeatureMap map = structuralFeatureMaps.get(sf);
		if(map == null){
			map = new NakedStructuralFeatureMap(sf);
			structuralFeatureMaps.put(sf, map);
		}
		return map;
	}
	public static synchronized NakedStructuralFeatureMap buildStructuralFeatureMap(INakedAction action,OpaeumLibrary lib){
		NakedStructuralFeatureMap map = actionFeatureMaps.get(action);
		if(map == null){
			ActionFeatureBridge bridge = buildActionBridge(action, lib);
			map = new NakedStructuralFeatureMap(bridge);
			actionFeatureMaps.put(action, map);
		}
		return map;
	}
	private static ActionFeatureBridge buildActionBridge(INakedAction action,OpaeumLibrary lib){
		if(action instanceof IActionWithTargetElement){
			return new ActionFeatureBridge((IActionWithTargetElement) action, lib);
		}else{
			return new ActionFeatureBridge((INakedAcceptCallAction) action, lib);
		}
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static OJPathName packagePathname(INakedNameSpace p){
		return new OJPathName(AbstractJavaNameGenerator.packagePathname(p));
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static OJPathName classifierPathname(INakedClassifier classifier){
		return new OJPathName(AbstractJavaNameGenerator.classifierPathname(classifier));
	}
	public static final OJOperation addMethod(OJClass theClass,String name,String type,String expression){
		OJOperation get = OJUtil.findOperation(theClass, name);
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
	public static OJAnnotatedField addProperty(OJClassifier ojClass,String name,OJPathName type,boolean withBody){
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
	// TODO move to annotated class
	public static OJOperation findOperation(OJClass theClass,String name){
		Iterator<OJOperation> iter = theClass.getOperations().iterator();
		while(iter.hasNext()){
			OJOperation o = (OJOperation) iter.next();
			if(o.getName().equals(name)){
				return o;
			}
		}
		return null;
	}
	public static void addFailedConstraints(OJOperation execute){
		// String failedConstraints = UtilityCreator.getUtilPathName() + ".FailedConstraintsException";
		execute.getOwner().addToImports("org.opeum.runtime.domain.FailedConstraintsException");
		execute.addToThrows("org.opeum.runtime.domain.FailedConstraintsException");
	}
	/**
	 * Some classifiers in UML would not necessarily be generated as Java classes. Returns false for NakedBehaviors that have one or less
	 * resulting parameters
	 * 
	 */
	public static boolean hasOJClass(INakedClassifier c){
		if(c == null || c instanceof INakedStereotype || c instanceof INakedActor || c instanceof INakedCollaboration || c instanceof INakedUseCase){
			return false;
		}else if(c.isMarkedForDeletion() || c.getCodeGenerationStrategy().isNone()){
			return false;
		}else if(c instanceof INakedBehavior){
			return BehaviorUtil.hasExecutionInstance((IParameterOwner) c);
		}else if(c instanceof INakedAssociation){
			return ((INakedAssociation) c).isClass();
		}else if(c instanceof EmulatedCompositionMessageStructure){
			return true;
		}else{
			return true;
		}
	}
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(INakedClassifier umlOwner,INakedObjectNode pin,boolean ensureUniquenes){
		Map<INakedTypedElement,NakedStructuralFeatureMap> maps = ensureUniquenes ? locallyUniqueFeatureMaps : structuralFeatureMaps;
		NakedStructuralFeatureMap map = maps.get(pin);
		if(map == null){
			map = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(umlOwner, pin, ensureUniquenes));
			maps.put(pin, map);
		}
		return map;
	}
	public static void removeReturnStatement(OJOperation javaMethod){
		List<OJStatement> sts = new ArrayList<OJStatement>(javaMethod.getBody().getStatements());
		OJStatement last = sts.get(sts.size() - 1);
		if(last.toJavaString().startsWith("return ")){
			javaMethod.getBody().removeFromStatements(last);
		}
	}
	public static OJPathName classifierPathname(INakedEmbeddedScreenFlowTask origin){
		return packagePathname(origin.getActivity()).append(origin.getMappingInfo().getJavaName().getCapped().getAsIs());
	}
	public static void addMetaInfo(OJAnnotatedElement element,INakedElement property){
		if(!(property instanceof InverseArtificialProperty)){
			OJAnnotationValue metaInfo = new OJAnnotationValue(new OJPathName(NumlMetaInfo.class.getName()));
			metaInfo.putAttribute("uuid", property.getMappingInfo().getIdInModel());
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
	public static String toJavaLiteral(INakedEnumerationLiteral l){
		return l.getName().toUpperCase();
	}
}
