package org.opaeum.javageneration.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.codegen.umlToJava.maps.TupleTypeMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Variable;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.AbstractEmulatedMessageType;
import org.opaeum.eclipse.emulated.ActionFeatureBridge;
import org.opaeum.eclipse.emulated.ExpansionRegionMessageType;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.eclipse.emulated.InverseArtificialProperty;
import org.opaeum.eclipse.emulated.OpaqueActionMessageType;
import org.opaeum.eclipse.emulated.OperationMessageType;
import org.opaeum.eclipse.emulated.StructuredActivityNodeMessageType;
import org.opaeum.eclipse.emulated.TypedElementPropertyBridge;
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
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.ActivityNodeMap;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.maps.StructuredActivityNodeMap;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;

public class OJUtil{
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
	private OpaeumLibrary library;
	private Map<NamedElement,OJPathName> oldClassifierPaths = new HashMap<NamedElement,OJPathName>();
	private Map<Namespace,OJPathName> oldPackagePaths = new HashMap<Namespace,OJPathName>();
	private Map<NamedElement,OJPathName> classifierPaths = new HashMap<NamedElement,OJPathName>();
	private Map<Namespace,OJPathName> packagePaths = new HashMap<Namespace,OJPathName>();
	private Map<NamedElement,OperationMap> operationMaps = new HashMap<NamedElement,OperationMap>();
	private Map<NamedElement,StructuralFeatureMap> structuralFeatureMaps = new HashMap<NamedElement,StructuralFeatureMap>();
	private Map<Signal,SignalMap> signalMaps = new HashMap<Signal,SignalMap>();
	private Map<String,ClassifierMap> classifierMaps = new HashMap<String,ClassifierMap>();
	private Map<Namespace,OJPathName> statePathnames = new HashMap<Namespace,OJPathName>();
	public OJUtil(OpaeumLibrary library){
		super();
		this.library = library;
	}
	public OpaeumLibrary getLibrary(){
		return library;
	}
	public void clearCache(){
		oldClassifierPaths = classifierPaths;
		oldPackagePaths = packagePaths;
		classifierPaths = new HashMap<NamedElement,OJPathName>();
		packagePaths = new HashMap<Namespace,OJPathName>();
		operationMaps = new HashMap<NamedElement,OperationMap>();
		structuralFeatureMaps = new HashMap<NamedElement,StructuralFeatureMap>();
		signalMaps = new HashMap<Signal,SignalMap>();
		classifierMaps = new HashMap<String,ClassifierMap>();
		statePathnames = new HashMap<Namespace,OJPathName>();
	}
	public void lock(){
		structuralFeatureMaps = Collections.unmodifiableMap(structuralFeatureMaps);
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
	public OJPathName utilPackagePath(Element e){
		if(e instanceof Namespace){
			return packagePathname((Namespace) e).getCopy().append("util");
		}else if(e instanceof EmfWorkspace){
			return new OJPathName(((EmfWorkspace) e).getPrefix()).append("util");
		}
		return new OJPathName("util");
	}
	public static OJAnnotatedOperation buildMain(OJAnnotatedClass ojClass){
		OJAnnotatedOperation main = new OJAnnotatedOperation("main");
		main.setStatic(true);
		main.addParam("args", new OJPathName("String[]"));
		ojClass.addToOperations(main);
		return main;
	}
	public StructuralFeatureMap buildStructuralFeatureMap(TypedElement typedAndOrdered){
		StructuralFeatureMap map = structuralFeatureMaps.get(typedAndOrdered);
		if(map == null){
			Property prop = null;
			if(typedAndOrdered instanceof Property){
				prop = (Property) typedAndOrdered;
			}else if(typedAndOrdered instanceof Pin){
				Pin pin = (Pin) typedAndOrdered;
				Action a = (Action) pin.getOwner();
				if(EmfActionUtil.isSingleScreenTask(a)){
					OpaqueActionMessageType msg = (OpaqueActionMessageType) library.getMessageStructure((OpaqueAction) a);
					prop = msg.getEmulatedAttribute(typedAndOrdered);
				}else{
					Namespace ns = EmfActivityUtil.getNearestNodeContainer(a);
					Classifier messageStructure = library.getMessageStructure(ns);
					prop = new TypedElementPropertyBridge(messageStructure, typedAndOrdered);
				}
			}else if(typedAndOrdered instanceof Parameter){
				Parameter parm = (Parameter) typedAndOrdered;
				if(parm.getOwner() instanceof Operation){
					OperationMessageType msg = (OperationMessageType) library.getMessageStructure((Operation) parm.getOwner());
					prop = msg.getEmulatedAttribute(parm);
				}else{
					Behavior b = (Behavior) parm.getOwner();
					prop = library.getEmulatedPropertyHolder(b).getEmulatedAttribute(parm);
				}
			}else if(typedAndOrdered instanceof ExpansionNode){
				ExpansionNode node = (ExpansionNode) typedAndOrdered;
				ExpansionRegionMessageType msg = (ExpansionRegionMessageType) library.getMessageStructure(EmfActivityUtil.getExpansionRegion(node));
				prop = msg.getEmulatedAttribute(node);
			}else if(typedAndOrdered instanceof ActivityParameterNode){
				ActivityParameterNode node = (ActivityParameterNode) typedAndOrdered;
				Activity a = EmfActivityUtil.getContainingActivity(node);
				if(node.getParameter() == null){
				}else{
					prop = library.getEmulatedPropertyHolder(a).getEmulatedAttribute(node.getParameter());
				}
			}else if(typedAndOrdered instanceof Variable){
				Variable v = (Variable) typedAndOrdered;
				if(v.getActivityScope() != null){
					prop = library.getEmulatedPropertyHolder(v.getActivityScope()).getEmulatedAttribute(v);
				}else{
					prop = ((AbstractEmulatedMessageType) library.getMessageStructure(v.getScope())).getEmulatedAttribute(v);
				}
			}
			map = new StructuralFeatureMap(this, prop);
			structuralFeatureMaps.put(typedAndOrdered, map);
		}
		return map;
	}
	public StructuralFeatureMap buildStructuralFeatureMap(Action action){
		StructuralFeatureMap map = structuralFeatureMaps.get(action);
		if(map == null){
			Namespace nearestNodeContainer = EmfActivityUtil.getNearestNodeContainer(action);
			ActionFeatureBridge bridge=null;
			if(nearestNodeContainer instanceof Activity){
				bridge=(ActionFeatureBridge) library.getEmulatedPropertyHolder((Activity)nearestNodeContainer).getEmulatedAttribute(action);
			}else {
				StructuredActivityNodeMessageType msg= (StructuredActivityNodeMessageType) library.getMessageStructure(nearestNodeContainer);
				bridge=(ActionFeatureBridge) msg.getEmulatedAttribute(action);
			}
			map = new StructuralFeatureMap(this, bridge);
			structuralFeatureMaps.put(action, map);
		}
		return map;
	}

	/**
	 * A Opaeum specific algorithm that takes mapped implementation types into account as well as classifier nesting. With UML classifier
	 * nesting a package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public OJPathName packagePathname(Namespace p){
		OJPathName result = packagePaths.get(p);
		if(result == null){
			packagePaths.put(p, result = new ImmutablePathName(JavaNameGenerator.packagePathname(p)));
		}
		return result;
	}
	public OJPathName getOldClassifierPathname(NamedElement c){
		if(oldClassifierPaths != null){
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
	public OJPathName classifierPathname(NamedElement classifier){
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
	public String addQualifierArguments(List<Property> qualifiers,String varName){
		StringBuilder sb = new StringBuilder();
		// Assume qualifiers are back by attributes as we are doing composition here
		for(Property q:qualifiers){
			StructuralFeatureMap qMap = buildStructuralFeatureMap(q);
			sb.append(varName);
			sb.append(".");
			sb.append(qMap.getter());
			sb.append("(),");
		}
		String string = sb.toString();
		return string;
	}
	public ClassifierMap buildClassifierMap(Classifier c){
		String key = c.getQualifiedName();
		ClassifierMap result = classifierMaps.get(key);
		if(result == null){
			classifierMaps.put(key, result = new ClassifierMap(this, c));
		}
		return result;
	}
	public ClassifierMap buildClassifierMap(Classifier c,MultiplicityElement m){
		CollectionKind kind = EmfPropertyUtil.getCollectionKind(m);
		return buildClassifierMap(c, kind);
	}
	public ClassifierMap buildClassifierMap(Classifier c,CollectionKind kind){
		if(kind != null){
			Classifier actualType = (Classifier) library.getTypeResolver().resolveCollectionType(kind, c);
			return buildClassifierMap(actualType);
		}else{
			return buildClassifierMap(c);
		}
	}
	public OperationMap buildOperationMap(Operation s){
		OperationMap result = operationMaps.get(s);
		if(result == null){
			operationMaps.put(s, result = new OperationMap(this, s));
		}
		return result;
	}
	public SignalMap buildSignalMap(Signal s){
		SignalMap result = signalMaps.get(s);
		if(result == null){
			signalMaps.put(s, result = new SignalMap(this, s));
		}
		return result;
	}
	public static boolean isJavaKeyword(String name){
		return javaKeyWords.contains(name);
	}
	public OJPathName statePathname(Namespace activity){
		OJPathName result = statePathnames.get(activity);
		if(result == null){
			Namespace namespace = (Namespace) EmfElementFinder.getContainer(activity);
			statePathnames.put(activity, result = new ImmutablePathName(packagePathname(namespace), classifierPathname(activity).getLast()
					+ "State"));
		}
		return result;
	}
	public OperationMap buildOperationMap(NamedElement o){
		if(o instanceof Behavior){
			return buildOperationMap((Behavior) o);
		}
		return buildOperationMap((Operation) o);
	}
	public boolean requiresJavaRename(NamedElement a){
		return oldClassifierPaths != null && !oldClassifierPaths.get(a).equals(classifierPaths.get(a));
	}
	public OJPathName getOldPackagePathname(Namespace c){
		if(oldPackagePaths != null){
			return oldPackagePaths.get(c);
		}
		return null;
	}
	public ActionMap buildActionMap(Action a){
		return new ActionMap(this, a);
	}
	public ActivityNodeMap buildActivityNodeMap(ActivityNode a){
		return new ActivityNodeMap(this, a);
	}
	public StateMap buildStateMap(Vertex referredState){
		return new StateMap(this, referredState);
	}
	public TupleTypeMap buildTupleTypeMap(TupleType in){
		return new TupleTypeMap(this, in);
	}
	public AssociationClassEndMap buildAssociationClassEndMap(Property redefinedProperty){
		return new AssociationClassEndMap(this, redefinedProperty);
	}
	public StructuredActivityNodeMap buildStructuredActivityNodeMap(StructuredActivityNode san){
		return new StructuredActivityNodeMap(this, san);
	}
	public StructuralFeatureMap buildStructuralFeatureMap(Observation o){
		StructuralFeatureMap map = structuralFeatureMaps.get(o);
		if(map == null){
			Property prop = null;
			EObject container = EmfElementFinder.getContainer(o);
			if(container instanceof Behavior){
				prop = library.getEmulatedPropertyHolder((Behavior) container).getEmulatedAttribute(o);
			}else{
				prop = ((AbstractEmulatedMessageType) library.getMessageStructure((StructuredActivityNode) container)).getEmulatedAttribute(o);
			}
			structuralFeatureMaps.put(o, map = new StructuralFeatureMap(this, prop));
		}
		return map;
	}

}
