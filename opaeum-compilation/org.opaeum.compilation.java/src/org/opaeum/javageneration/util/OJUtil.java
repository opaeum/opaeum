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

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UseCase;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
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
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;

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
	private static Map<NamedElement,OJPathName> oldClassifierPaths = new HashMap<NamedElement,OJPathName>();
	private static Map<Namespace,OJPathName> oldPackagePaths = new HashMap<Namespace,OJPathName>();
	private static Map<NamedElement,OJPathName> classifierPaths = new HashMap<NamedElement,OJPathName>();
	private static Map<Namespace,OJPathName> packagePaths = new HashMap<Namespace,OJPathName>();
	private static Map<NamedElement,NakedOperationMap> operationMaps = new HashMap<NamedElement,NakedOperationMap>();
	private static Map<TypedElement,NakedStructuralFeatureMap> locallyUniqueFeatureMaps = new HashMap<TypedElement,NakedStructuralFeatureMap>();
	private static Map<TypedElement,NakedStructuralFeatureMap> structuralFeatureMaps = new HashMap<TypedElement,NakedStructuralFeatureMap>();
	private static Map<Action,NakedStructuralFeatureMap> actionFeatureMaps = new HashMap<Action,NakedStructuralFeatureMap>();
	private static Map<Signal,SignalMap> signalMaps = new HashMap<Signal,SignalMap>();
	private static Map<String,NakedClassifierMap> classifierMaps = new HashMap<String,NakedClassifierMap>();
	private static Map<Namespace,OJPathName> statePathnames = new HashMap<Namespace,OJPathName>();
	public static void clearCache(){
		oldClassifierPaths=classifierPaths;
		oldPackagePaths=packagePaths;
		classifierPaths = new HashMap<NamedElement,OJPathName>();
		packagePaths = new HashMap<Namespace,OJPathName>();
		operationMaps = new HashMap<NamedElement,NakedOperationMap>();
		locallyUniqueFeatureMaps = new HashMap<TypedElement,NakedStructuralFeatureMap>();
		structuralFeatureMaps = new HashMap<TypedElement,NakedStructuralFeatureMap>();
		actionFeatureMaps = new HashMap<Action,NakedStructuralFeatureMap>();
		signalMaps = new HashMap<Signal,SignalMap>();
		classifierMaps = new HashMap<String,NakedClassifierMap>();
		statePathnames = new HashMap<Namespace,OJPathName>();
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
	public static boolean isBuiltIn(TypedElement f){
		return BUILT_IN_ATTRIBUTES.contains(f.getName());
	}
	public static OJPathName utilPackagePath(Element e){
		return new OJPathName(e.getQualifiedJavaName()).append("util");
	}
	public static OJAnnotatedOperation buildMain(OJAnnotatedClass ojClass){
		OJAnnotatedOperation main = new OJAnnotatedOperation("main");
		main.setStatic(true);
		main.addParam("args", new OJPathName("String[]"));
		ojClass.addToOperations(main);
		return main;
	}
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(Classifier owner,TypedElement typedAndOrdered){
		NakedStructuralFeatureMap linkedParameter;
		if(typedAndOrdered instanceof Property){
			linkedParameter = OJUtil.buildStructuralFeatureMap((Property) typedAndOrdered);
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
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(Property sf){
		NakedStructuralFeatureMap map = structuralFeatureMaps.get(sf);
		if(map == null){
			map = new NakedStructuralFeatureMap(sf);
			structuralFeatureMaps.put(sf, map);
		}
		return map;
	}
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(Action action,OpaeumLibrary lib){
		NakedStructuralFeatureMap map = actionFeatureMaps.get(action);
		if(map == null){
			ActionFeatureBridge bridge = buildActionBridge(action, lib);
			map = new NakedStructuralFeatureMap(bridge);
			actionFeatureMaps.put(action, map);
		}
		return map;
	}
	private static ActionFeatureBridge buildActionBridge(Action action,OpaeumLibrary lib){
		if(EmfActionUtil.getTargetElement(action)!=null){
			return new ActionFeatureBridge((IActionWithTargetElement) action, lib);
		}else{
			return new ActionFeatureBridge((AcceptCallAction) action, lib);
		}
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static OJPathName packagePathname(Namespace p){
		OJPathName result = packagePaths.get(p);
		if(result == null){
			packagePaths.put(p, result = new ImmutablePathName(JavaNameGenerator.packagePathname(p)));
		}
		return result;
	}
	public static OJPathName getOldClassifierPathname(NamedElement c){
		if(oldClassifierPaths!=null){
			return oldClassifierPaths.get(c);
		}
		return null;
		
	}
	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static OJPathName classifierPathname(NamedElement classifier){
		OJPathName result = classifierPaths.get(classifier);
		if(result == null){
			classifierPaths.put(classifier, result = new ImmutablePathName(JavaNameGenerator.classifierPathname(classifier)));
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
	public static boolean hasOJClass(Classifier c){
		if(c == null || c instanceof Stereotype || c instanceof Collaboration || c instanceof UseCase){
			return false;
		}else if(EmfElementUtil.isMarkedForDeletion( c) || EmfClassifierUtil.getCodeGenerationStrategy( c)==CodeGenerationStrategy.NO_CODE){
			return false;
		}else if(c instanceof Behavior){
			return EmfBehaviorUtil.hasExecutionInstance((Behavior) c);
		}else if(c instanceof Association){
			return EmfAssociationUtil.isClass((Association) c);
		}else if(c instanceof EmulatedCompositionMessageStructure){
			return true;
		}else{
			return true;
		}
	}
	public static NakedStructuralFeatureMap buildStructuralFeatureMap(Classifier umlOwner,ObjectNode pin,boolean ensureUniquenes){
		Map<TypedElement,NakedStructuralFeatureMap> maps = ensureUniquenes ? locallyUniqueFeatureMaps : structuralFeatureMaps;
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
	public static OJPathName classifierPathname(EmbeddedScreenFlowTask origin){
		return packagePathname(origin.getContainingActivity()).append(origin.getName().getCapped().getAsIs());
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
	public static String addQualifierArguments(List<Property> qualifiers,String varName){
		StringBuilder sb = new StringBuilder();
		// Assume qualifiers are back by attributes as we are doing composition here
		for(Property q:qualifiers){
			NakedStructuralFeatureMap qMap = buildStructuralFeatureMap(q);
			sb.append(varName);
			sb.append(".");
			sb.append(qMap.getter());
			sb.append("(),");
		}
		String string = sb.toString();
		return string;
	}
	public static NakedClassifierMap buildClassifierMap(Classifier c, MultiplicityElement m){
		CollectionKind kind=EmfPropertyUtil.getCollectionKind(m);
		return buildClassifierMap(c, kind);
	}
	public static NakedClassifierMap buildClassifierMap(Classifier c,CollectionKind kind){
		String key = c.getQualifiedName() + kind==null?"base": kind.getName();
		NakedClassifierMap result = classifierMaps.get(key);
		if(result == null){
			classifierMaps.put(key, result = new NakedClassifierMap(c,kind));
		}
		return result;
	}
	public static NakedOperationMap buildOperationMap(Operation s){
		NakedOperationMap result = operationMaps.get(s);
		if(result == null){
			operationMaps.put(s, result = new NakedOperationMap(s));
		}
		return result;
	}
	public static SignalMap buildSignalMap(Signal s){
		SignalMap result = signalMaps.get(s);
		if(result == null){
			signalMaps.put(s, result = new SignalMap(s));
		}
		return result;
	}
	public static boolean isJavaKeyword(String name){
		return javaKeyWords.contains(name);
	}
	public static OJPathName statePathname(Namespace activity){
		OJPathName result = statePathnames.get(activity);
		if(result == null){
			Namespace namespace=(Namespace) EmfElementFinder.getContainer(activity);
			statePathnames.put(activity, result = new ImmutablePathName(packagePathname(namespace), classifierPathname(activity).getLast() + "State"));
		}
		return result;
	}
	public static NakedOperationMap buildOperationMap(NamedElement o){
		if(o instanceof Behavior){
			return buildOperationMap((Behavior)o);
		}
		return buildOperationMap((Operation)o);
	}
	public static boolean requiresJavaRename(NamedElement a){
		return oldClassifierPaths!=null && !oldClassifierPaths.get(a).equals(classifierPaths.get(a));
	}
	public static OJPathName getOldPackagePathname(Namespace c){
		if(oldPackagePaths!=null){
			return oldPackagePaths.get(c);
		}
		return null;
	}


}
