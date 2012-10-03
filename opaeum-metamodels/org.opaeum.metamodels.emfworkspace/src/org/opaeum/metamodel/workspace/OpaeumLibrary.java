package org.opaeum.metamodel.workspace;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.MessageType;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.ocl.uml.OCL.Helper;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.Variable;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfActivityUtil.TypeAndMultiplicity;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.ResponsibilityDefinitionImpl;
import org.opaeum.eclipse.emulated.AbstractEmulatedProperty;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolder;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForActivity;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForAssociation;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForBehavior;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForBehavioredClassifier;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForStateMachine;
import org.opaeum.eclipse.emulated.ExpansionRegionMessageType;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.eclipse.emulated.IEmulatedPropertyHolder;
import org.opaeum.eclipse.emulated.OpaqueActionMessageType;
import org.opaeum.eclipse.emulated.OperationMessageType;
import org.opaeum.eclipse.emulated.StructuredActivityNodeMessageType;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.emf.workspace.UriToFileConverter;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaeumEnvironmentFactory;
import org.opaeum.ocl.uml.OpaqueActionActionContext;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.ocl.uml.ResponsibilityDefinition;

@SuppressWarnings("restriction")
public class OpaeumLibrary implements IPropertyEmulation{
	private Map<NamedElement,Classifier> emulatedClassifiers = new HashMap<NamedElement,Classifier>();
	private Map<Classifier,IEmulatedPropertyHolder> classifierAttributes = new HashMap<Classifier,IEmulatedPropertyHolder>();
	private ResourceSet resourceSet;
	private Map<OpaqueExpression,OpaqueExpressionContext> opaqueExpressions = new HashMap<OpaqueExpression,OpaqueExpressionContext>();
	private Map<OpaqueBehavior,OpaqueBehaviorContext> opaqueBehaviors = new HashMap<OpaqueBehavior,OpaqueBehaviorContext>();
	private Map<OpaqueAction,OpaqueActionActionContext> opaqueActions = new HashMap<OpaqueAction,OpaqueActionActionContext>();
	// Built in types
	private Interface emailAddressType;
	private DataType dateType;
	private DataType dateTimeType;
	private DataType durationType;
	private DataType cumulativeDurationType;
	private DataType realType;
	private DataType stringType;
	private DataType integerType;
	private DataType booleanType;
	private Interface businessRole;
	private Interface taskObject;
	private Interface timedResource;
	private Interface quantifiedResource;
	private Interface requestObject;
	private Interface responsibilityObject;
	private Class opaeumPerson;
	private StateMachine taskRequest;
	private StateMachine processRequest;
	private Interface processObject;
	private Interface businessCollaboration;
	private StateMachine abstractRequest;
	private Interface business;
	private Interface businessActor;
	private Class businessNetwork;
	private UMLEnvironment parentEnvironment;
	private Map<String,Operation> additionalOperations = new HashMap<String,Operation>();
	UriToFileConverter uriToFileConverter;
	private Map<Model,Map<String,String>> implementationCode = new HashMap<Model,Map<String,String>>();
	private Interface participant;
	private TreeSet<IEmulatedElement> emulatedElements = new TreeSet<IEmulatedElement>(new DefaultOpaeumComparator());
	private Interface notificationReceiver;
	private Class businessCalendar;
	private DataType durationBasedCost;
	private DataType quantityBasedCost;
	public OpaeumLibrary(ResourceSet resourceSet,UriToFileConverter uriToFileConverter){
		super();
		UMLEnvironmentFactory factory = new UMLEnvironmentFactory(resourceSet);
		this.parentEnvironment = factory.createEnvironment();
		this.resourceSet = resourceSet;
		this.uriToFileConverter = uriToFileConverter;
	}
	public DataType getDateTimeType(){
		return dateTimeType = findClassifier(dateTimeType, StereotypeNames.OPAEUM_SIMPLE_TYPES, "DateTime");
	}
	public DataType getDateType(){
		return dateType = findClassifier(dateType, StereotypeNames.OPAEUM_SIMPLE_TYPES, "Date");
	}
	public DataType getDefaultType(){
		return getStringType();
	}
	public Interface getEmailAddressType(){
		return emailAddressType = findClassifier(emailAddressType, StereotypeNames.OPAEUM_BPM_LIBRARY, "IPersonEMailAddress");
	}
	public DataType getBooleanType(){
		return booleanType = findPrimitiveType("Boolean", booleanType);
	}
	public DataType getIntegerType(){
		return integerType = findPrimitiveType("Integer", integerType);
	}
	public DataType getRealType(){
		realType = findPrimitiveType("Real", realType);
		if(realType == null){
			realType = findClassifier(realType, StereotypeNames.OPAEUM_SIMPLE_TYPES, "Real");
		}
		return realType;
	}
	public DataType getStringType(){
		return stringType = findPrimitiveType("String", stringType);
	}
	protected DataType findPrimitiveType(String name,DataType value){
		if(value == null){
			Resource resource = resourceSet.getResource(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI, true), true);
			TreeIterator<EObject> it = resource.getAllContents();
			while(it.hasNext()){
				EObject eObject = (EObject) it.next();
				if(eObject instanceof DataType && ((DataType) eObject).getName().equals(name)){
					value = (DataType) eObject;
					break;
				}
			}
		}
		return value;
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
	public Interface getRequestObject(){
		return requestObject = findClassifier(requestObject, StereotypeNames.OPAEUM_BPM_LIBRARY, "IRequestObject");
	}
	public Interface getResponsibilityObject(){
		return responsibilityObject = findClassifier(responsibilityObject, StereotypeNames.OPAEUM_BPM_LIBRARY, "IResponsibilityObject");
	}
	@SuppressWarnings("unchecked")
	private <T extends Classifier>T findClassifier(T c,String libName,String classifierName){
		if(c == null){
			Resource found = null;
			for(Resource resource:resourceSet.getResources()){
				// HACK until Topcased becomes Papyris
				String lastSegment = resource.getURI().trimFileExtension().lastSegment();
				if(lastSegment.contains(".")){
					lastSegment = lastSegment.substring(0, lastSegment.indexOf('.'));
				}
				if(libName.startsWith(lastSegment)){
					found = resource;
				}
			}
			if(found == null){
				return null;
				// URI uri = URI.createURI(StereotypeNames.MODELS_PATHMAP + "libraries/" + libName);
				// found=resourceSet.getResource(uri, true);
			}
			EList<EObject> contents = found.getContents();
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
	@Override
	public DataType getCumulativeDurationType(){
		return cumulativeDurationType = findClassifier(cumulativeDurationType, StereotypeNames.OPAEUM_BPM_LIBRARY, "CumulativeDuration");
	}
	public Class getPersonNode(){
		return opaeumPerson = findClassifier(opaeumPerson, StereotypeNames.OPAEUM_BPM_LIBRARY, "PersonNode");
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
		Property etc = EmfPropertyUtil.getEndToComposite(entity);
		if(etc == null && !(entity instanceof IEmulatedElement)){
			return getArtificialEndToComposite(entity);
		}
		return etc;
	}
	public Property getArtificialEndToComposite(Classifier entity){
		if(!(entity instanceof MessageType)){
			IEmulatedPropertyHolder eph = getEmulatedPropertyHolder(entity);
			for(AbstractEmulatedProperty p:eph.getEmulatedAttributes()){
				if(p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
					return p;
				}
			}
		}
		return null;
	}
	@Override
	public OpaqueExpressionContext getOclExpressionContext(OpaqueExpression valueSpec){
		OpaqueExpressionContext result = opaqueExpressions.get(valueSpec);
		if(result == null){
			Element context = EmfValueSpecificationUtil.getContext(valueSpec);
			OCL ocl = createOcl(context, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OpaqueExpressionContext(valueSpec, helper);
			opaqueExpressions.put(valueSpec, result);
		}
		return result;
	}
	public OpaqueBehaviorContext getOclBehaviorContext(OpaqueBehavior valueSpec){
		OpaqueBehaviorContext result = opaqueBehaviors.get(valueSpec);
		if(result == null){
			OCL ocl = createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OpaqueBehaviorContext(valueSpec, helper);
			opaqueBehaviors.put(valueSpec, result);
		}
		return result;
	}
	public OpaqueActionActionContext getOclActionContext(OpaqueAction valueSpec){
		OpaqueActionActionContext result = opaqueActions.get(valueSpec);
		if(result == null){
			OCL ocl = createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OpaqueActionActionContext(valueSpec, helper);
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
		}else if(container != null){
			Classifier classifier = emulatedClassifiers.get(container);
			if(classifier == null){
				if(container instanceof Operation){
					classifier = new OperationMessageType((Operation) container, this);
					if(getRequestObject() != null){
						((OperationMessageType) classifier).createInterfaceRealization(getResponsibilityObject().getName(), getResponsibilityObject());
					}
				}else if(container instanceof ExpansionRegion){
					classifier = new ExpansionRegionMessageType((ExpansionRegion) container, this);
				}else if(container instanceof StructuredActivityNode){
					classifier = new StructuredActivityNodeMessageType((StructuredActivityNode) container, this);
				}
				emulatedClassifiers.put(container, classifier);
				emulatedElements.add((IEmulatedElement) classifier);
			}
			return classifier;
		}else{
			return null;
		}
	}
	public Set<Property> getDirectlyImplementedAttributes(Classifier c){
		Set<Property> propertiesInScope = EmfPropertyUtil.getDirectlyImplementedAttributes(c);
		addEmulatedProperties(c, propertiesInScope);
		if(c instanceof BehavioredClassifier){
			for(Interface intf:((BehavioredClassifier) c).getImplementedInterfaces()){
				// TODO filter out emulated properties that may already have been implemented by the superclass
				addAllEmulatedProperties(intf, propertiesInScope);
			}
		}
		return propertiesInScope;
	}
	public List<Property> getEffectiveAttributes(Classifier bc){
		List<Property> props = EmfElementFinder.getPropertiesInScope(bc);
		List<Property> result = new ArrayList<Property>(props);
		addAllEmulatedProperties(bc, result);
		return result;
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
		if(holder != null){
			for(AbstractEmulatedProperty aep:holder.getEmulatedAttributes()){
				if(aep.shouldEmulate()){
					propertiesInScope.add(aep);
				}
			}
		}
		for(Association a:bc.getAssociations()){
			if(EmfAssociationUtil.isClass(a)){
				EmulatedPropertyHolderForAssociation epha = (EmulatedPropertyHolderForAssociation) getEmulatedPropertyHolder(a);
				for(Property m:a.getMemberEnds()){
					if(m.getOtherEnd().getType() == bc){
						propertiesInScope.add(epha.getEndToAssociation(m));
					}
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public IEmulatedPropertyHolder getEmulatedPropertyHolder(Classifier bc){
		IEmulatedPropertyHolder holder = classifierAttributes.get(bc);
		if(holder == null){
			if(bc instanceof Activity){
				holder = new EmulatedPropertyHolderForActivity((Activity) bc, this);
			}else if(bc instanceof StateMachine){
				holder = new EmulatedPropertyHolderForStateMachine((StateMachine) bc, this);
			}else if(bc instanceof Association){
				holder = new EmulatedPropertyHolderForAssociation((Association) bc, this);
			}else if(bc instanceof Behavior){
				holder = new EmulatedPropertyHolderForBehavior((Behavior) bc, this);
			}else if(bc instanceof BehavioredClassifier){
				holder = new EmulatedPropertyHolderForBehavioredClassifier((BehavioredClassifier) bc, this);
			}else{
				holder = new EmulatedPropertyHolder(bc, this);
			}
			classifierAttributes.put(bc, holder);
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
	public Classifier getEventGeneratingClassifier(NamedElement event){
		if((event instanceof Constraint && StereotypesHelper.hasStereotype(event, StereotypeNames.ESCALATION))
				|| (event instanceof Event && EmfEventUtil.isDeadline((Event) event))){
			EObject container = EmfElementFinder.getContainer(event);
			if(container instanceof Operation){
				return getMessageStructure((Operation) container);
			}else if(container instanceof CallBehaviorAction){
				return getMessageStructure((CallBehaviorAction) container);
			}else if(container instanceof OpaqueAction){
				return getMessageStructure((OpaqueAction) container);
			}else if(container instanceof Behavior){
				return (Behavior) container;
			}
		}else{
			return getMessageStructure(EmfEventUtil.getBehavioralNamespaceContext((Event) event));
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
	public ResponsibilityDefinition getResponsibilityDefinition(NamedElement node,String...deadlineOper){
		return new ResponsibilityDefinitionImpl(this, node, StereotypesHelper.getStereotype(node, deadlineOper));
	}
	public String getImplementationCodeFor(Model m,String artefactName){
		Map<String,String> map = implementationCode.get(m);
		if(map == null){
			try{
				map = new HashMap<String,String>();
				implementationCode.put(m, map);
				URI uri = m.eResource().getURI().trimFileExtension().appendFileExtension("zip");
				uri = m.eResource().getResourceSet().getURIConverter().normalize(uri);
				File file = uriToFileConverter.resolveUri(uri);
				if(file != null){
					ZipFile zipFile = new ZipFile(file);
					java.util.Enumeration<? extends ZipEntry> entries = zipFile.entries();
					while(entries.hasMoreElements()){
						ZipEntry zipEntry = entries.nextElement();
						if(!zipEntry.isDirectory()){
							BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry)));
							StringBuilder sb = new StringBuilder();
							String line;
							while((line = reader.readLine()) != null){
								sb.append(line);
								sb.append("\n");
							}
							map.put(zipEntry.getName(), sb.toString());
						}
					}
				}
			}catch(IOException e){
				System.out.println(e.toString());
			}
		}
		return map.get(artefactName);
	}
	public Collection<AbstractOclContext> getOclContexts(){
		Collection<AbstractOclContext> result = new HashSet<AbstractOclContext>(this.opaqueActions.values());
		result.addAll(this.opaqueBehaviors.values());
		result.addAll(this.opaqueExpressions.values());
		return result;
	}
	public void reset(){
		emulatedClassifiers.clear();
		classifierAttributes.clear();
		opaqueExpressions.clear();
		opaqueBehaviors.clear();
		opaqueActions.clear();
	}
	public Map<String,Operation> getAdditionalOperations(){
		return additionalOperations;
	}
	public void setAdditionalOperations(Map<String,Operation> additionalOperations){
		this.additionalOperations = additionalOperations;
	}
	public Interface getParticipant(){
		return this.participant = findClassifier(this.participant, StereotypeNames.OPAEUM_BPM_LIBRARY, "IParticipant");
	}
	public SortedSet<IEmulatedElement> getAllEmulatedElements(){
		return emulatedElements;
	}
	public Classifier getTargetType(Action a){
		InputPin pin = EmfActionUtil.getTargetPin(a);
		Classifier type = calculateType(pin);
		if(type == null){
			type = EmfActionUtil.getTargetType(a);
		}
		if(type instanceof CollectionType){
			type = ((CollectionType) type).getElementType();
		}
		return (Classifier) type;
	}
	protected Classifier calculateType(InputPin pin){
		Type type = null;
		if(pin instanceof ValuePin){
			ValueSpecification value = ((ValuePin) pin).getValue();
			if(value instanceof OpaqueExpression){
				OpaqueExpressionContext ctx = getOclExpressionContext((OpaqueExpression) value);
				if(!ctx.hasErrors()){
					type = ctx.getExpression().getType();
					if(type instanceof CollectionType){
						type = ((CollectionType) type).getElementType();
					}
				}
			}
		}
		if(type == null && pin != null && pin.getIncomings().size() > 0 && pin.getIncomings().get(0) instanceof ObjectFlow){
			ObjectFlow of = (ObjectFlow) pin.getIncomings().get(0);
			TypeAndMultiplicity mt = EmfActivityUtil.findSourceType(of);
			if(mt != null){
				type = mt.getType();
			}
		}
		return (Classifier) type;
	}
	public Interface getNotificationReceiver(){
		return this.notificationReceiver = findClassifier(this.notificationReceiver, StereotypeNames.OPAEUM_BPM_LIBRARY,
				"INotificationReceiver");
	}
	public Interface getTimedResource(){
		return this.timedResource = findClassifier(this.timedResource, StereotypeNames.OPAEUM_BPM_LIBRARY, "ITimedResource");
	}
	public Interface getQuantifiedResource(){
		return this.quantifiedResource = findClassifier(this.quantifiedResource, StereotypeNames.OPAEUM_BPM_LIBRARY, "IQuantifiedResource");
	}
	public Class getBusinessCalendar(){
		return this.businessCalendar = findClassifier(this.businessCalendar, StereotypeNames.OPAEUM_BPM_LIBRARY, "BusinessCalendar");
	}
	public OpaqueExpressionContext getArtificationExpression(NamedElement ne,String tagName){
		for(EObject e:ne.getStereotypeApplications()){
			EStructuralFeature f = e.eClass().getEStructuralFeature(tagName);
			if(f != null){
				return getOclExpressionContext((OpaqueExpression) e.eGet(f));
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Collection<OpaqueExpressionContext> getArtificialExpressions(NamedElement ne,String tagName){
		Collection<OpaqueExpressionContext> result = new HashSet<OpaqueExpressionContext>();
		for(EObject e:ne.getStereotypeApplications()){
			EStructuralFeature f = e.eClass().getEStructuralFeature(tagName);
			if(f != null){
				Collection<OpaqueExpression> val = (Collection<OpaqueExpression>) e.eGet(f);
				for(OpaqueExpression oe:val){
					result.add(getOclExpressionContext(oe));
				}
			}
		}
		return result;
	}
	public Type getLibraryType(LibraryType typeOfChild){
		switch(typeOfChild){
		case BUSINESS_CALENDAR:
			return getBusinessCalendar();
		case BUSINESS_ROLE:
			return getBusinessRole();
		case TIMED_RESOURCE:
			return getTimedResource();
		case PARTICIPANT:
			return getParticipant();
		case REAL:
			return getRealType();
		case RECIPIENT:
			return getNotificationReceiver();
		case QUANTIFIED_RESOURCE:
			return getQuantifiedResource();
		case DURATION:
			return getDurationType();
		case STRING:
			return getStringType();
		default:
			return null;
		}
	}
	public DataType getDurationBasedCost(){
		return this.durationBasedCost = findClassifier(this.durationBasedCost, StereotypeNames.OPAEUM_BPM_LIBRARY, "DurationBasedCost");
	}
	public DataType getQuantityBasedCost(){
		return this.quantityBasedCost = findClassifier(this.quantityBasedCost, StereotypeNames.OPAEUM_BPM_LIBRARY, "QuantityBasedCost");
	}
}
