package org.opaeum.javageneration.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.Transient;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.annotation.NumlMetaInfo;
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
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.IActionWithTargetElement;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
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
import org.opaeum.metamodel.usecases.INakedUseCase;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;
import org.opaeum.validation.namegeneration.AbstractJavaNameGenerator;

public class OJUtil{
	private static class ImmutablePathName extends OJPathName{
		private ImmutablePathName(String name){
			StringTokenizer st = new StringTokenizer(name, ".");
			while(st.hasMoreTokens()){
				this.getNames().add(st.nextToken());
			}
		}
		public ImmutablePathName(OJPathName classifierPathname,String string){
			this(classifierPathname.toJavaString());
			super.getNames().add(string);
		}
		@Override
		public void addToElementTypes(OJPathName element){
			throw new UnsupportedOperationException();
		}
		@Override
		public void addToElementTypes(Collection<OJPathName> newElems){
			throw new UnsupportedOperationException();
		}
		@Override
		public void addToNames(Collection<String> newElems){
			throw new UnsupportedOperationException();
		}
		@Override
		public void addToNames(String element){
			throw new UnsupportedOperationException();
		}
		@Override
		public void insertBeforeTail(String name){
			throw new UnsupportedOperationException();
		}
		@Override
		public OJPathName append(String str){
			throw new UnsupportedOperationException();
		}
		@Override
		public void replaceTail(String newtail){
			throw new UnsupportedOperationException();
		}
	}
	private static Map<INakedClassifier,OJPathName> classifierPaths = new HashMap<INakedClassifier,OJPathName>();
	private static Map<INakedNameSpace,OJPathName> packagePaths = new HashMap<INakedNameSpace,OJPathName>();
	private static Map<IParameterOwner,NakedOperationMap> operationMaps = new HashMap<IParameterOwner,NakedOperationMap>();
	private static Map<INakedTypedElement,NakedStructuralFeatureMap> locallyUniqueFeatureMaps = new HashMap<INakedTypedElement,NakedStructuralFeatureMap>();
	private static Map<INakedTypedElement,NakedStructuralFeatureMap> structuralFeatureMaps = new HashMap<INakedTypedElement,NakedStructuralFeatureMap>();
	private static Map<INakedAction,NakedStructuralFeatureMap> actionFeatureMaps = new HashMap<INakedAction,NakedStructuralFeatureMap>();
	private static Map<INakedSignal,SignalMap> signalMaps = new HashMap<INakedSignal,SignalMap>();
	private static Map<IClassifier,NakedClassifierMap> classifierMaps = new HashMap<IClassifier,NakedClassifierMap>();
	private static Map<INakedClassifier,OJPathName> statePathnames = new HashMap<INakedClassifier,OJPathName>();
	public static void clearCache(){
		classifierPaths = new HashMap<INakedClassifier,OJPathName>();
		packagePaths = new HashMap<INakedNameSpace,OJPathName>();
		operationMaps = new HashMap<IParameterOwner,NakedOperationMap>();
		locallyUniqueFeatureMaps = new HashMap<INakedTypedElement,NakedStructuralFeatureMap>();
		structuralFeatureMaps = new HashMap<INakedTypedElement,NakedStructuralFeatureMap>();
		actionFeatureMaps = new HashMap<INakedAction,NakedStructuralFeatureMap>();
		signalMaps = new HashMap<INakedSignal,SignalMap>();
		classifierMaps = new HashMap<IClassifier,NakedClassifierMap>();
		statePathnames = new HashMap<INakedClassifier,OJPathName>();
	}
	private static final Set<String> BUILT_IN_ATTRIBUTES = new HashSet<String>(3);
	static{
		BUILT_IN_ATTRIBUTES.add("now");
		BUILT_IN_ATTRIBUTES.add("currentUser");
		BUILT_IN_ATTRIBUTES.add("today");
	}
	private static Set<String> javaKeyWords = new HashSet<String>(21);
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
	public static void lock(){
		structuralFeatureMaps = Collections.unmodifiableMap(structuralFeatureMaps);
		locallyUniqueFeatureMaps = Collections.unmodifiableMap(locallyUniqueFeatureMaps);
		actionFeatureMaps = Collections.unmodifiableMap(actionFeatureMaps);
		packagePaths = Collections.unmodifiableMap(packagePaths);
		classifierPaths = Collections.unmodifiableMap(classifierPaths);
		classifierMaps = Collections.unmodifiableMap(classifierMaps);
		operationMaps = Collections.unmodifiableMap(operationMaps);
		signalMaps = Collections.unmodifiableMap(signalMaps);
		statePathnames = Collections.unmodifiableMap(statePathnames);
	}
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
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(INakedClassifier owner,INakedTypedElement typedAndOrdered){
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
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(INakedProperty sf){
		NakedStructuralFeatureMap map = structuralFeatureMaps.get(sf);
		if(map == null){
			map = new NakedStructuralFeatureMap(sf);
			structuralFeatureMaps.put(sf, map);
		}
		return map;
	}
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(INakedAction action,OpaeumLibrary lib){
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
		OJPathName result = packagePaths.get(p);
		if(result == null){
			packagePaths.put(p, result = new ImmutablePathName(AbstractJavaNameGenerator.packagePathname(p)));
		}
		return result;
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static OJPathName classifierPathname(INakedClassifier classifier){
		OJPathName result = classifierPaths.get(classifier);
		if(result == null){
			classifierPaths.put(classifier, result = new ImmutablePathName(AbstractJavaNameGenerator.classifierPathname(classifier)));
		}
		return result;
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
		result.addAnnotationIfNew(new OJAnnotationValue(new OJPathName(Transient.class.getName())));
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
	public static boolean hasOJClass(INakedClassifier c){
		if(c == null || c instanceof INakedStereotype || c instanceof INakedCollaboration || c instanceof INakedUseCase){
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
	public static String addQualifierArguments(List<INakedProperty> qualifiers,String varName){
		StringBuilder sb = new StringBuilder();
		// Assume qualifiers are back by attributes as we are doing composition here
		for(INakedProperty q:qualifiers){
			NakedStructuralFeatureMap qMap = buildStructuralFeatureMap(q);
			sb.append(varName);
			sb.append(".");
			sb.append(qMap.getter());
			sb.append("(),");
		}
		String string = sb.toString();
		return string;
	}
	public static NakedClassifierMap buildClassifierMap(IClassifier c){
		NakedClassifierMap result = classifierMaps.get(c);
		if(result == null){
			classifierMaps.put(c, result = new NakedClassifierMap(c));
		}
		return result;
	}
	public static NakedOperationMap buildOperationMap(IParameterOwner s){
		NakedOperationMap result = operationMaps.get(s);
		if(result == null){
			operationMaps.put(s, result = new NakedOperationMap(s));
		}
		return result;
	}
	public static SignalMap buildSignalMap(INakedSignal s){
		SignalMap result = signalMaps.get(s);
		if(result == null){
			signalMaps.put(s, result = new SignalMap(s));
		}
		return result;
	}
	public static boolean isJavaKeyword(String name){
		return javaKeyWords.contains(name);
	}
	public static OJPathName statePathname(INakedClassifier activity){
		OJPathName result = statePathnames.get(activity);
		if(result == null){
			statePathnames.put(activity, result = new ImmutablePathName(packagePathname(activity.getNameSpace()), activity.getMappingInfo()
					.getJavaName() + "State"));
		}
		return result;
	}
}
