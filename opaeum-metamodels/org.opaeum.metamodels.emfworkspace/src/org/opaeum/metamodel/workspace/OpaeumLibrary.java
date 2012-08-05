package org.opaeum.metamodel.workspace;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ExpansionRegion;
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
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.ResponsibilityDefinitionImpl;
import org.opaeum.eclipse.emulated.AbstractEmulatedProperty;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForActivity;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForBehavioredClassifier;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForStateMachine;
import org.opaeum.eclipse.emulated.ExpansionRegionMessageType;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.eclipse.emulated.IEmulatedPropertyHolder;
import org.opaeum.eclipse.emulated.OpaqueActionMessageType;
import org.opaeum.eclipse.emulated.OperationMessageType;
import org.opaeum.eclipse.emulated.StructuredActivityNodeMessageType;
import org.opaeum.eclipse.emulated.TypedElementPropertyBridge;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.ocl.uml.OclActionContext;
import org.opaeum.ocl.uml.OclBehaviorContext;
import org.opaeum.ocl.uml.OclContext;
import org.opaeum.ocl.uml.OpaeumEnvironmentFactory;
import org.opaeum.ocl.uml.ResponsibilityDefinition;

public class OpaeumLibrary implements IPropertyEmulation{
	private Map<NamedElement,Classifier> emulatedClassifiers = new HashMap<NamedElement,Classifier>();
	private Map<Classifier,IEmulatedPropertyHolder> classifierAttributes = new HashMap<Classifier,IEmulatedPropertyHolder>();
	private ResourceSet resourceSet;
	private Map<OpaqueExpression,OclContext> opaqueExpressions = new HashMap<OpaqueExpression,OclContext>();
	private Map<OpaqueBehavior,OclBehaviorContext> opaqueBehaviors = new HashMap<OpaqueBehavior,OclBehaviorContext>();
	private Map<OpaqueAction,OclActionContext> opaqueActions = new HashMap<OpaqueAction,OclActionContext>();
	private Map<String,MappedType> typeMap = new HashMap<String,MappedType>();
	// Built in types
	private DataType emailAddressType;
	private DataType dateType;
	private DataType dateTimeType;
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
	public DataType getDateTimeType(){
		return dateTimeType;
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
		return businessRole = findClassifier(businessRole, StereotypeNames.OPAEUM_BPM_LIBRARY, "IBusinessRole");
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
		return durationType = findClassifier(durationType, StereotypeNames.OPAEUM_BPM_LIBRARY, "Duration");
	}
	public Class getPersonNode(){
		return opaeumPerson = findClassifier(opaeumPerson, StereotypeNames.OPAEUM_BPM_LIBRARY, "PersonNode");
	}
	public Interface getResponsibilityProcessObject(){
		return processResponsibilityObject = findClassifier(processResponsibilityObject, StereotypeNames.OPAEUM_BPM_LIBRARY,
				"IResponsibilityProcessObject");
	}
	public Interface getTaskResponsibilityObject(){
		return taskResponsibilityObject = findClassifier(taskResponsibilityObject, StereotypeNames.OPAEUM_BPM_LIBRARY,
				"IResponsibilityTaskObject");
	}
	public Interface getProcessObject(){
		return this.processObject = findClassifier(processObject, StereotypeNames.OPAEUM_BPM_LIBRARY, "IProcessObject");
	}
	public StateMachine getAbstractRequest(){
		return abstractRequest = findClassifier(abstractRequest, StereotypeNames.OPAEUM_BPM_LIBRARY, "AbstractRequest");
	}
	public StateMachine getProcessRequest(){
		return processRequest = findClassifier(processRequest, StereotypeNames.OPAEUM_BPM_LIBRARY, "ProcessRequest");
	}
	public Interface getBusinessCollaboration(){
		return businessCollaboration = findClassifier(businessCollaboration, StereotypeNames.OPAEUM_BPM_LIBRARY, "IBusinessCollaboration");
	}
	public Interface getBusiness(){
		return business = findClassifier(business, StereotypeNames.OPAEUM_BPM_LIBRARY, "IBusiness");
	}
	public Interface getBusinessActor(){
		return businessActor = findClassifier(businessActor, StereotypeNames.OPAEUM_BPM_LIBRARY, "IBusinessActor");
	}
	public Class getBusinessNetwork(){
		return businessNetwork = findClassifier(businessNetwork, StereotypeNames.OPAEUM_BPM_LIBRARY, "BusinessNetwork");
	}
	public Classifier getMessageStructure(CallAction action){
		if(action instanceof CallBehaviorAction){
			return ((CallBehaviorAction) action).getBehavior();
		}else if(action instanceof CallOperationAction){
			return getMessageStructure(((CallOperationAction) action).getOperation());
		}else{
			return null;
		}
	}
	public Classifier getMessageStructure(AcceptCallAction action2){
		return getMessageStructure(EmfActionUtil.getOperation(action2));
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
	public Property getEndToComposite(Classifier entity){
		Property etc = EmfPropertyUtil.getEndToComposite(entity, this);
		if(etc==null && !(entity instanceof IEmulatedElement)){
			return getArtificialEndToComposite(entity);
		}
		return etc;
	}
	public Property getArtificialEndToComposite(Classifier entity){
		for(AbstractEmulatedProperty p:getEmulatedPropertyHolder(entity).getEmulatedAttributes()){
			if(p.getOtherEnd()!=null && p.getOtherEnd().isComposite()){
				return p;
			}
		}
		return null;
	}
	public OclContext getOclExpressionContext(OpaqueExpression valueSpec){
		OclContext result = opaqueExpressions.get(valueSpec);
		if(result == null){
			Element context = EmfValueSpecificationUtil.getContext(valueSpec);
			OCL ocl = createOcl(context, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OclContext(valueSpec, helper);
			opaqueExpressions.put(valueSpec, result);
		}
		return result;
	}
	public OclBehaviorContext getOclBehaviorContext(OpaqueBehavior valueSpec){
		OclBehaviorContext result = opaqueBehaviors.get(valueSpec);
		if(result == null){
			OCL ocl = createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OclBehaviorContext(valueSpec, helper);
			opaqueBehaviors.put(valueSpec, result);
		}
		return result;
	}
	public OclActionContext getOclActionContext(OpaqueAction valueSpec){
		OclActionContext result = opaqueActions.get(valueSpec);
		if(result == null){
			OCL ocl = createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OclActionContext(valueSpec, helper);
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
	public OCL createOcl(Element context,Map<String,Classifier> vars){
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
			return (Behavior) container;
		}else if(container!=null){
			Classifier classifier = emulatedClassifiers.get(container);
			if(classifier == null){
				if(container instanceof Operation){
					classifier = new OperationMessageType((Operation) container, this);
					((OperationMessageType)classifier).createInterfaceRealization(getTaskObject().getName(), getTaskObject());
				}else if(container instanceof ExpansionRegion){
					classifier = new ExpansionRegionMessageType((ExpansionRegion) container, this);
				}else if(container instanceof StructuredActivityNode){
					classifier = new StructuredActivityNodeMessageType((StructuredActivityNode) container, this);
				}
				emulatedClassifiers.put(container, classifier);
			}
			return classifier;
		}else{
			return null;
		}
	}
	public Set<Property> getDirectlyImplementedAttributes(Classifier c){
		Set<Property> propertiesInScope = EmfPropertyUtil.getDirectlyImplementedAttributes(c);
		addAllEmulatedProperties(c, propertiesInScope);
		return propertiesInScope;
	}
	public List<Property> getEffectiveAttributes(Classifier bc){
		List<Property> propertiesInScope = EmfElementFinder.getPropertiesInScope(bc);
		addAllEmulatedProperties(bc, propertiesInScope);
		return propertiesInScope;
	}
	protected void addAllEmulatedProperties(Classifier bc,Collection<Property> propertiesInScope){
		addEmulatedProperties(bc, propertiesInScope);
		for(Classifier classifier:bc.getGenerals()){
			addAllEmulatedProperties(classifier, propertiesInScope);
		}
		if(bc instanceof BehavioredClassifier){
			for(Interface i:((BehavioredClassifier) bc).getImplementedInterfaces()){
				addAllEmulatedProperties(i, propertiesInScope);
			}
		}
	}
	private void addEmulatedProperties(Classifier bc,Collection<Property> propertiesInScope){
		IEmulatedPropertyHolder holder = getEmulatedPropertyHolder(bc);
		for(AbstractEmulatedProperty aep:holder.getEmulatedAttributes()){
			if(aep.shouldEmulate()){
				propertiesInScope.add(aep);
			}
		}
	}
	public IEmulatedPropertyHolder getEmulatedPropertyHolder(Classifier bc){
		IEmulatedPropertyHolder holder = classifierAttributes.get(bc);
		if(holder == null){
			if(bc instanceof Activity){
				holder = new EmulatedPropertyHolderForActivity((Activity) bc, this);
			}else if(bc instanceof StateMachine){
				holder = new EmulatedPropertyHolderForStateMachine((StateMachine) bc, this);
			}else if(bc instanceof BehavioredClassifier){
				holder = new EmulatedPropertyHolderForBehavioredClassifier((BehavioredClassifier) bc, this);
			}
		}
		return holder;
	}
	public Property findEffectiveAttribute(Classifier fromClass,String name){
		for(Property p:getEffectiveAttributes(fromClass)){
			if(p.getName().equals(name)){
				return p;
			}
		}
		return null;
	}
	public Classifier getEventContext(Event event){
		if(EmfEventUtil.isDeadline(event)){
			EObject container = EmfElementFinder.getContainer(event);
			if(container instanceof Operation){
				return getMessageStructure((Operation) container);
			}else if(container instanceof CallBehaviorAction){
				return getMessageStructure((CallBehaviorAction) container);
			}else if(container instanceof OpaqueAction){
				return getMessageStructure((OpaqueAction) container);
			}
		}else{
			return EmfEventUtil.getBehaviorContext(event);
		}
		return null;
	}
	@Override
	public Classifier getMessageStructure(OpaqueAction n){
		OpaqueActionMessageType classifier = (OpaqueActionMessageType) this.emulatedClassifiers.get(n);
		if(classifier == null){
			this.emulatedClassifiers.put(n, classifier = new OpaqueActionMessageType(n, this));
			classifier.createInterfaceRealization(getTaskObject().getName(), getTaskObject());
		}
		return classifier;
	}
	public ResponsibilityDefinition getResponsibilityDefinition(NamedElement node,String embeddedScreenFlowTask){
		return new ResponsibilityDefinitionImpl(this,node, StereotypesHelper.getStereotype(node, embeddedScreenFlowTask));
	}
}
