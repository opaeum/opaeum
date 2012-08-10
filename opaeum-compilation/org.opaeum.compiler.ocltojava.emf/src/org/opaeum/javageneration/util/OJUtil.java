package org.opaeum.javageneration.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.codegen.umlToJava.maps.TupleTypeMap;
import org.eclipse.uml2.uml.Package;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Variable;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.AbstractEmulatedMessageType;
import org.opaeum.eclipse.emulated.ActionFeatureBridge;
import org.opaeum.eclipse.emulated.ExpansionRegionMessageType;
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
import org.opaeum.metamodel.workspace.MappedType;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;

public class OJUtil extends OJUtill{
	public static int instanceCount;
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
	private Map<Package,Map<String,MappedType>> typeMap = new HashMap<Package,Map<String,MappedType>>();
	public OJUtil(){
		super();
		instanceCount++;
	}
	public void initialise(OpaeumLibrary o){
		if(library != o){
			clearCache();
		}
		this.library = o;
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
					prop = new TypedElementPropertyBridge(messageStructure, typedAndOrdered,getLibrary());
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
			ActionFeatureBridge bridge = null;
			if(nearestNodeContainer instanceof Activity){
				bridge = (ActionFeatureBridge) library.getEmulatedPropertyHolder((Activity) nearestNodeContainer).getEmulatedAttribute(action);
			}else{
				StructuredActivityNodeMessageType msg = (StructuredActivityNodeMessageType) library.getMessageStructure(nearestNodeContainer);
				bridge = (ActionFeatureBridge) msg.getEmulatedAttribute(action);
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
			String qn = null;
			System.out.println();
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
		if(c == null){
			System.out.println();
		}
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
		return oldClassifierPaths != null && oldClassifierPaths.containsKey(a) && !oldClassifierPaths.get(a).equals(classifierPaths.get(a));
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
	public OJPathName utilClass(Element owner,String suffix){
		OJPathName result = utilPackagePath(owner).getCopy();
		if(owner instanceof Namespace){
			return result.append(NameConverter.capitalize(((Namespace) owner).getName()) + suffix);
		}else if(owner instanceof EmfWorkspace){
			return result.append(NameConverter.capitalize(((EmfWorkspace) owner).getName()) + suffix);
		}
		return null;
	}
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		instanceCount--;
	}
	public Map<String,MappedType> getTypeMap(Package p){
		if(p == null){
			System.out.println();
		}
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
					System.out.println("Loaded mappings: " + mappedTypesUri);
				}catch(IOException e1){
				}
			}
		}
		return map;
	}
}
