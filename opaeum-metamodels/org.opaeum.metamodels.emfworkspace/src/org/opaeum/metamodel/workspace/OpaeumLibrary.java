package org.opaeum.metamodel.workspace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.uml.MessageType;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.ocl.uml.OCL.Helper;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.ocl.uml.internal.OCLStandardLibraryImpl;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OclActionContext;
import org.opaeum.ocl.uml.OclBehaviorContext;
import org.opaeum.ocl.uml.OclContext;
import org.opaeum.ocl.uml.OpaeumEnvironmentFactory;

public class OpaeumLibrary {
	private ResourceSet resourceSet;
	private Map<OpaqueExpression,OclContext> opaqueExpressions = new HashMap<OpaqueExpression,OclContext>();
	private Map<OpaqueBehavior,OclBehaviorContext> opaqueBehaviors = new HashMap<OpaqueBehavior,OclBehaviorContext>();
	private Map<OpaqueAction,OclActionContext> opaqueActions= new HashMap<OpaqueAction,OclActionContext>();
	private Map<String,MappedType> typeMap = new HashMap<String,MappedType>();
	// Built in types
	private DataType emailAddressType;
	private DataType dateType;
	private DataType durationType;
	private DataType defaultType;
	private DataType realType;
	private DataType stringType;
	private DataType integerType;
	private DataType booleanType;
	private Interface businessRole;
	private Interface taskObject;
	private Class opaeumPerson;
	private Interface processResponsibilityObject;
	private Interface taskResponsibilityObject;
	private StateMachine taskRequest;
	private StateMachine processRequest;
	private Interface processObject;
	private Interface businessCollaboration;
	private StateMachine abstractRequest;
	private Interface business;
	private Interface businessActor;
	private Class businessNetwork;
	private UMLEnvironment parentEnvironment;
	public OpaeumLibrary(ResourceSet resourceSet){
		super();
		UMLEnvironmentFactory factory = new UMLEnvironmentFactory(resourceSet);
		this.parentEnvironment = factory.createEnvironment();
		this.resourceSet = resourceSet;
	}
	public void setAbstractRequest(StateMachine abstractRequest){
		this.abstractRequest = abstractRequest;
	}
	public void setProcessObject(Interface processObject){
		this.processObject = processObject;
	}
	public DataType getBooleanType(){
		return booleanType;
	}
	public DataType getDateType(){
		return dateType;
	}
	public DataType getDefaultType(){
		return defaultType;
	}
	public DataType getEmailAddressType(){
		return emailAddressType;
	}
	public DataType getIntegerType(){
		return integerType;
	}
	public DataType getRealType(){
		return realType;
	}
	public DataType getStringType(){
		return stringType;
	}
	public Map<String,MappedType> getTypeMap(){
		return typeMap;
	}
	public Classifier lookupStandardType(String standardType){
		if(standardType.equals("Integer")){
			return getIntegerType();
		}
		if(standardType.equals("Real")){
			return getRealType();
		}
		if(standardType.equals("String")){
			return getStringType();
		}
		if(standardType.equals("Boolean")){
			return getBooleanType();
		}
		return null;
	}
	public Interface getBusinessRole(){
		return businessRole = findClassifier(taskObject, StereotypeNames.OPAEUM_BPM_LIBRARY, "IBusinessRole");
	}
	public Interface getTaskObject(){
		return taskObject = findClassifier(taskObject, StereotypeNames.OPAEUM_BPM_LIBRARY, "ITaskObject");
	}
	@SuppressWarnings("unchecked")
	private <T extends Classifier>T findClassifier(T c,String libName,String classifierName){
		if(c == null){
			URI uri = URI.createURI(StereotypeNames.MODELS_PATHMAP + "libraries/" + libName);
			EList<EObject> contents = resourceSet.getResource(uri, true).getContents();
			if(contents.size() >= 1 && contents.get(0) instanceof Model){
				Model model = (Model) contents.get(0);
				TreeIterator<EObject> eAllContents = model.eAllContents();
				while(eAllContents.hasNext()){
					EObject eObject = (EObject) eAllContents.next();
					if(eObject instanceof Classifier && ((Classifier) eObject).getName().equals(classifierName)){
						return (T) eObject;
					}
				}
			}
		}
		return c;
	}
	public StateMachine getTaskRequest(){
		return taskRequest = findClassifier(taskRequest, StereotypeNames.OPAEUM_BPM_LIBRARY, "TaskRequest");
	}
	public DataType getDurationType(){
		return durationType;
	}
	public Class getOpaeumPerson(){
		return opaeumPerson;
	}
	public Interface getProcessResponsibilityObject(){
		return processResponsibilityObject;
	}
	public Interface getTaskResponsibilityObject(){
		return taskResponsibilityObject;
	}
	public Interface getProcessObject(){
		return this.processObject;
	}
	public StateMachine getAbstractRequest(){
		return abstractRequest;
	}
	public StateMachine getProcessRequest(){
		return processRequest;
	}
	public Interface getBusinessCollaboration(){
		return businessCollaboration;
	}
	public Interface getBusiness(){
		return business;
	}
	public Interface getBusinessActor(){
		return businessActor;
	}
	public Class getBusinessNetwork(){
		return businessNetwork;
	}
	public Property getArtificialEndToComposite(Classifier c){
		implment();
	}
	public Classifier getMessageStructure(Action action){
		if(action instanceof StructuredActivityNode){
		}
		if(EmfActionUtil.isEmbeddedTask(action)){
			return ((EmbeddedTask) action).getMessageStructure();
		}else if(action instanceof CallBehaviorAction){
			return ((CallBehaviorAction) action).getBehavior();
		}else if(action instanceof CallOperationAction){
			return getMessageStructure(((CallOperationAction) action).getOperation());
		}else if(action instanceof AcceptCallAction){
			return getMessageStructure(EmfActionUtil.getOperation((AcceptCallAction) action));
		}else{
			return null;
		}
	}
	public Classifier getActualType(MultiplicityElement me){
		Classifier type = null;
		if(me instanceof TypedElement){
			type = (Classifier) ((TypedElement) me).getType();
		}else if(me instanceof Variable){
			type = (Classifier) ((Variable) me).getType();
		}
		if(type != null){
			CollectionKind collectionKind = EmfPropertyUtil.getCollectionKind(me);
			if(collectionKind == null){
				return type;
			}else{
				return (Classifier) parentEnvironment.getTypeResolver().resolveCollectionType(collectionKind, type);
			}
		}
		return type;
	}
	public Classifier getMessageStructure(Operation o){
		return (MessageType) parentEnvironment.getTypeResolver().resolveOperationMessageType(o);
	}
	public Property getEndToComposite(Classifier entity){
		// TODO Clean this up a bit
		return EmfPropertyUtil.getEndToComposite(entity, this);
	}
	public OclContext getOclExpressionContext(OpaqueExpression valueSpec){
		OclContext result = opaqueExpressions.get(valueSpec);
		if(result == null){
			Element context = EmfValueSpecificationUtil.getContext(valueSpec);
			OCL ocl=createOcl(context, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result=new OclContext(valueSpec, helper);
			opaqueExpressions.put(valueSpec, result);
		}
		return result;
	}
	public OclBehaviorContext getOclBehaviorContext(OpaqueBehavior valueSpec){
		OclBehaviorContext result = opaqueBehaviors.get(valueSpec);
		if(result == null){
			OCL ocl=createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result=new OclBehaviorContext(valueSpec, helper);
			opaqueBehaviors.put(valueSpec, result);
		}
		return result;
	}
	public OclActionContext getOclActionContext(OpaqueAction valueSpec){
		OclActionContext result = opaqueActions.get(valueSpec);
		if(result == null){
			OCL ocl=createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result=new OclActionContext(valueSpec, helper);
			opaqueActions.put(valueSpec, result);
		}
		return result;
	}

	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	public Model findLibrary(String librName){
		Model library = null;
		for(Resource resource:getResourceSet().getResources()){
			if(resource.getContents().size() > 0 && resource.getContents().get(0) instanceof Model){
				Model m = (Model) resource.getContents().get(0);
				if(!m.eIsProxy() && resource.getURI().lastSegment().equals(librName)){
					library = m;
				}
			}
		}
		return library;
	}
	public OCL createOcl(Element context, Map<String, Classifier> vars){
		OpaeumEnvironmentFactory factory = new OpaeumEnvironmentFactory(context, this);
		factory.addVariables(vars);
		return OCL.newInstance(factory.createEnvironment(parentEnvironment));
	}
	public TypeResolver<Classifier,Operation,Property> getTypeResolver(){
		return parentEnvironment.getTypeResolver();
	}
	public OCLStandardLibrary<Classifier> getOclLibrary(){
		return parentEnvironment.getOCLStandardLibrary();
	}
	public Classifier getMessageStructure(Namespace container){
		if(container instanceof Behavior){
			return (Behavior)container;
		}else if(container instanceof StructuredActivityNode){
			return getMessageStructure((StructuredActivityNode)container);
		}else if(container instanceof Operation){
			return getMessageStructure((Operation)container);
		}else if(container instanceof Action){
			return getMessageStructure((Action)container);
		}
		return null;
	}
	public Classifier getMessageStructure(StructuredActivityNode n){
		return doit();
	}
}
