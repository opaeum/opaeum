package org.opaeum.javageneration.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;
import nl.klasse.octopus.codegen.umlToJava.maps.TupleTypeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.Variable;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.compiler.ocltojava.JavaCompilationPlugin;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.AbstractEmulatedMessageType;
import org.opaeum.eclipse.emulated.ActionFeatureBridge;
import org.opaeum.eclipse.emulated.ExpansionRegionMessageType;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.eclipse.emulated.OpaqueActionMessageType;
import org.opaeum.eclipse.emulated.OperationMessageType;
import org.opaeum.eclipse.emulated.StructuredActivityNodeMessageType;
import org.opaeum.eclipse.emulated.TypedElementPropertyBridge;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.maps.ActivityNodeMap;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.maps.StructuredActivityNodeMap;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.workspace.MappedType;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;

public class OJUtil extends OJUtill{
	public static int instanceCount;
	private OpaeumLibrary library;
	private Map<NamedElement,StateMap> stateMaps = new HashMap<NamedElement,StateMap>();
	private Map<NamedElement,OJPathName> oldClassifierPaths = new HashMap<NamedElement,OJPathName>();
	private Map<Namespace,OJPathName> oldPackagePaths = new HashMap<Namespace,OJPathName>();
	private Map<NamedElement,OJPathName> classifierPaths = new HashMap<NamedElement,OJPathName>();
	private Map<Namespace,OJPathName> packagePaths = new HashMap<Namespace,OJPathName>();
	private Map<NamedElement,OperationMap> operationMaps = new HashMap<NamedElement,OperationMap>();
	private Map<NamedElement,PropertyMap> structuralFeatureMaps = new HashMap<NamedElement,PropertyMap>();
	private Map<Signal,SignalMap> signalMaps = new HashMap<Signal,SignalMap>();
	private Map<String,ClassifierMap> classifierMaps = new HashMap<String,ClassifierMap>();
	private Map<Namespace,OJPathName> statePathnames = new HashMap<Namespace,OJPathName>();
	private Map<Package,Map<String,MappedType>> typeMap = new HashMap<Package,Map<String,MappedType>>();
	private OJPathName environmentPathname;
	private boolean regenMappedTypes;
	public OJUtil(){
		super();
		instanceCount++;
	}
	public OJUtil(boolean regenMappedTypes){
		super();
		this.regenMappedTypes = regenMappedTypes;
		instanceCount++;
	}
	public OJPathName environmentPathname(){
		return environmentPathname;
	}
	public void initialise(EmfWorkspace workspace){
		if(library != workspace.getOpaeumLibrary()){
			clearCache();
		}
		this.library = workspace.getOpaeumLibrary();
		this.environmentPathname = utilClass(workspace, "Environment");
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
		structuralFeatureMaps = new HashMap<NamedElement,PropertyMap>();
		signalMaps = new HashMap<Signal,SignalMap>();
		classifierMaps = new HashMap<String,ClassifierMap>();
		statePathnames = new HashMap<Namespace,OJPathName>();
	}
	public void lock(){
		// structuralFeatureMaps = Collections.unmodifiableMap(structuralFeatureMaps);
		// packagePaths = Collections.unmodifiableMap(packagePaths);
		// classifierPaths = Collections.unmodifiableMap(classifierPaths);
		// classifierMaps = Collections.unmodifiableMap(classifierMaps);
		// operationMaps = Collections.unmodifiableMap(operationMaps);
		// signalMaps = Collections.unmodifiableMap(signalMaps);
		// statePathnames = Collections.unmodifiableMap(statePathnames);
	}
	public OJPathName utilPackagePath(Element e){
		if(e instanceof Namespace){
			return packagePathname((Namespace) e).getCopy().append("util");
		}else if(e instanceof EmfWorkspace){
			return new OJPathName(((EmfWorkspace) e).getPrefix()).append("util");
		}
		return new OJPathName("util");
	}
	public PropertyMap buildStructuralFeatureMap(TypedElement typedAndOrdered){
		PropertyMap map = structuralFeatureMaps.get(typedAndOrdered);
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
					prop = new TypedElementPropertyBridge(messageStructure, typedAndOrdered, getLibrary());
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
			map = new PropertyMap(this, prop);
			structuralFeatureMaps.put(typedAndOrdered, map);
		}
		return map;
	}
	public PropertyMap buildStructuralFeatureMap(Action action){
		PropertyMap map = structuralFeatureMaps.get(action);
		if(map == null){
			Namespace nearestNodeContainer = EmfActivityUtil.getNearestNodeContainer(action);
			ActionFeatureBridge bridge = null;
			if(nearestNodeContainer instanceof Activity){
				bridge = (ActionFeatureBridge) library.getEmulatedPropertyHolder((Activity) nearestNodeContainer).getEmulatedAttribute(action);
			}else{
				StructuredActivityNodeMessageType msg = (StructuredActivityNodeMessageType) library.getMessageStructure(nearestNodeContainer);
				bridge = (ActionFeatureBridge) msg.getEmulatedAttribute(action);
			}
			map = new PropertyMap(this, bridge);
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
			String qn = null;
			Package ro = EmfElementFinder.getRootObject(classifier);
			Map<String,MappedType> typeMap = getTypeMap(ro);
			if(typeMap.containsKey(classifier.getQualifiedName())){
				qn = typeMap.get(classifier.getQualifiedName()).getQualifiedJavaName();
			}else if(classifier instanceof PrimitiveType){
				PrimitiveType root = EmfClassifierUtil.getRootClass((PrimitiveType) classifier);
				if(root.getName().equals("Integer")){
					qn = "java.lang.Integer";
				}else if(root.getName().equals("Real")){
					qn = "java.lang.Double";
				}else if(root.getName().equals("Boolean")){
					qn = "java.lang.Boolean";
				}else{
					qn = "java.lang.String";
				}
			}else if(classifier instanceof CollectionType){
				// may never be called - monitor this
				CollectionType t = (CollectionType) classifier;
				switch(t.getKind()){
				case BAG_LITERAL:
				case COLLECTION_LITERAL:
					return new OJPathName("java.util.Collection");
				case ORDERED_SET_LITERAL:
				case SEQUENCE_LITERAL:
					return new OJPathName("java.util.List");
				case SET_LITERAL:
					return new OJPathName("java.util.Set");
				default:
					break;
				}
			}else{
				qn = JavaNameGenerator.classifierPathname(classifier);
			}
			classifierPaths.put(classifier, result = new ImmutablePathName(qn));
		}
		return result;
	}
	public String addQualifierArguments(List<Property> qualifiers,String varName){
		StringBuilder sb = new StringBuilder();
		// Assume qualifiers are back by attributes as we are doing composition here
		for(Property q:qualifiers){
			PropertyMap qMap = buildStructuralFeatureMap(q);
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
		if(c instanceof CollectionType){
			key += ((CollectionType) c).getElementType().getQualifiedName();
		}
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
	public OJPathName statePathname(Namespace activity){
		OJPathName result = statePathnames.get(activity);
		if(result == null){
			Namespace namespace = (Namespace) EmfElementFinder.getContainer(activity);
			statePathnames.put(activity, result = new ImmutablePathName(packagePathname(namespace), classifierPathname(activity).getLast() + "State"));
		}
		return result;
	}
	public OperationMap buildOperationMap(NamedElement o){
		if(o instanceof Behavior){
			OperationMap result = operationMaps.get(o);
			if(result == null){
				operationMaps.put(o, result = new OperationMap(this, (Behavior) o, ((Behavior) o).getOwnedParameters()));
			}
			return result;
		}else{
			return buildOperationMap((Operation) o);
		}
	}
	public boolean requiresJavaRename(NamedElement a){
		return oldClassifierPaths != null && oldClassifierPaths.containsKey(a) && classifierPaths.containsKey(a)
				&& !oldClassifierPaths.get(a).equals(classifierPaths.get(a));
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
		StateMap stateMap = this.stateMaps.get(referredState);
		if(stateMap == null){
			this.stateMaps.put(referredState, stateMap = new StateMap(this, referredState));
		}
		return stateMap;
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
	public PropertyMap buildStructuralFeatureMap(Observation o){
		PropertyMap map = structuralFeatureMaps.get(o);
		if(map == null){
			Property prop = null;
			EObject container = EmfElementFinder.getContainer(o);
			if(container instanceof Behavior){
				prop = library.getEmulatedPropertyHolder((Behavior) container).getEmulatedAttribute(o);
			}else{
				prop = ((AbstractEmulatedMessageType) library.getMessageStructure((StructuredActivityNode) container)).getEmulatedAttribute(o);
			}
			structuralFeatureMaps.put(o, map = new PropertyMap(this, prop));
		}
		return map;
	}
	public OJPathName utilClass(Element e,String suffix){
		if(e instanceof EmfWorkspace){
			OJPathName result = utilPackagePath(e).getCopy();
			return result.append(NameConverter.capitalize(((EmfWorkspace) e).getName()) + suffix);
		}else{
			Package owner = EmfElementFinder.getRootObject(e);
			OJPathName result = utilPackagePath(owner).getCopy();
			return result.append(NameConverter.capitalize(((Namespace) owner).getName()) + suffix);
		}
	}
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		instanceCount--;
	}
	public Map<String,MappedType> getTypeMap(Package p){
		Map<String,MappedType> map = typeMap.get(p);
		if(map == null){
			map = new HashMap<String,MappedType>();
			typeMap.put(p, map);
			if(p.eResource() != null && p.eResource().getResourceSet() != null){
				Resource eResource = p.eResource();
				URI uri = eResource.getURI();
				URI mappedTypesUri = uri.trimFileExtension().appendFileExtension(MAPPINGS_EXTENSION);
				try{
					InputStream inStream = eResource.getResourceSet().getURIConverter().createInputStream(mappedTypesUri);
					Properties props = new Properties();
					props.load(inStream);
					Set<Entry<Object,Object>> entrySet = props.entrySet();
					for(Entry<Object,Object> entry:entrySet){
						map.put((String) entry.getKey(), new MappedType((String) entry.getValue()));
					}
					JavaCompilationPlugin.logInfo("Loaded mappings: " + mappedTypesUri);
				}catch(IOException e1){
				}
			}
		}
		return map;
	}
	public OJPathName tokenPathName(Namespace b){
		OJPathName copy = classifierPathname(b).getCopy();
		copy.replaceTail(copy.getLast() + "Token");
		return copy;
	}
	/**
	 * Some classifiers in UML would not necessarily be generated as Java classes. Returns false for NakedBehaviors that have one or less
	 * resulting parameters
	 * 
	 */
	public boolean hasOJClass(Classifier c){
		if(c instanceof Collaboration && EmfClassifierUtil.isBusinessCollaboration(c)){
			return true;
		}else if(c == null || c instanceof Stereotype || c instanceof Collaboration || c instanceof UseCase){
			return false;
		}else if(EmfElementUtil.isMarkedForDeletion(c) || getCodeGenerationStrategy(c) == CodeGenerationStrategy.NO_CODE){
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
	public CodeGenerationStrategy getCodeGenerationStrategy(NamedElement c){
		CodeGenerationStrategy codeGenerationStrategy = CodeGenerationStrategy.ALL;
		if(regenMappedTypes){
			return codeGenerationStrategy;
		}else if(getTypeMap(EmfElementFinder.getRootObject(c)).containsKey(c.getQualifiedName())){
			codeGenerationStrategy = CodeGenerationStrategy.NO_CODE;
		}else if(c instanceof Classifier){
			Classifier cl = (Classifier) c;
			String s = (String) EmfClassifierUtil.getTagValue(cl, TagNames.MAPPED_IMPLEMENTATION_TYPE);
			if(s != null && s.length() > 0){
				codeGenerationStrategy = CodeGenerationStrategy.NO_CODE;
			}
			// TODO this bit is obsolete
			EEnumLiteral l = (EEnumLiteral) EmfClassifierUtil.getTagValue(cl, TagNames.CODE_GENERATION_STRATEGY);
			if(l != null){
				codeGenerationStrategy = Enum.valueOf(CodeGenerationStrategy.class, l.getName().toUpperCase());
			}
			if(Boolean.TRUE.equals(EmfClassifierUtil.getTagValue(cl, "generateAbstractSupertype"))){
				codeGenerationStrategy = CodeGenerationStrategy.ABSTRACT_SUPERTYPE_ONLY;
			}
		}else if(c instanceof Package && EmfPackageUtil.hasMappedImplementationPackage((Package) c)){
			codeGenerationStrategy = CodeGenerationStrategy.NO_CODE;
		}
		return codeGenerationStrategy;
	}
}
