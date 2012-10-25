package org.opaeum.ocl.uml;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.LookupException;
import org.eclipse.ocl.TypeChecker;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.uml.UMLFactory;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.ocl.uml.impl.TypeTypeImpl;
import org.eclipse.ocl.uml.internal.UMLForeignMethods;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@SuppressWarnings("restriction")
public final class OpaeumEnvironment extends OpaeumParentEnvironment{
	private Element context;
	private Collection<Variable> variables;
	private EnvironmentFactory<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> factory;
	public OpaeumEnvironment(Element context,OpaeumParentEnvironment parent){
		super(parent.getLibrary().getResourceSet());
		this.factory = new OpaeumEnvironmentFactory(context, parent.getLibrary());
		Variable self = UMLFactory.eINSTANCE.createVariable();
		self.setName("self");
		Classifier selfClassifier = EmfBehaviorUtil.getSelf(context);
		self.setType(selfClassifier);
		setSelfVariable(self);
		addElement("self", self, false);
		if(selfClassifier instanceof Behavior){
			Classifier contextObject = EmfBehaviorUtil.getContext(context);
			if(contextObject != null && contextObject != selfClassifier){
				Variable var = UMLFactory.eINSTANCE.createVariable();
				var.setType(contextObject);
				var.setName("contextObject");
				addElement("contextObject", var, false);
			}
		}
		Operation nearestOp = EmfElementFinder.findNearestElementOfType(Operation.class, context);
		if(nearestOp != null && EmfBehaviorUtil.isResponsibility(nearestOp) && library.getResponsibilityObject() != null){
			Variable var = UMLFactory.eINSTANCE.createVariable();
			var.setType(library.getResponsibilityObject());
			var.setName("responsibility");
			addElement("responsibility", var, false);
		}
		this.context = context;
		this.library = parent.getLibrary();
		this.variables = new HashSet<Variable>();
		setProblemHandler(new OpaeumOclProblemHandler(getParser()));
	}
	@Override
	public EnvironmentFactory<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> getFactory(){
		return this.factory;
	}
	@Override
	public org.eclipse.ocl.expressions.Variable<Classifier,Parameter> lookup(String name){
		org.eclipse.ocl.expressions.Variable<Classifier,Parameter> result = super.lookup(name);
		if(result == null){
			Collection<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> variables = new HashSet<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>>();
			addSpecialVariables(variables);
			for(org.eclipse.ocl.expressions.Variable<Classifier,Parameter> variable:variables){
				if(UMLForeignMethods.isNamed(name, (NamedElement) variable)){
					result = variable;
					break;
				}
			}
		}
		return result;
	}
	@Override
	public Collection<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> getVariables(){
		Collection<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> variables = super.getVariables();
		addSpecialVariables(variables);
		return variables;
	}
	@Override
	public Package getContextPackage(){
		return super.getContextPackage();
	}
	@Override
	public Package lookupPackage(List<String> path){
		// TODO Auto-generated method stub
		return super.lookupPackage(path);
	}
	@Override
	public Classifier lookupClassifier(List<String> names){
		Classifier cls = getContextClassifier();
		Namespace namespace = cls;
		if(cls == null){
			namespace = EmfElementFinder.getNearestNamespace(context);
		}else if(cls.getOwner() instanceof Namespace){
			// Try the owner's nestedClassifier/ownedBehavior containment hierarchy first
			Namespace ns = (Namespace) cls.getOwner();
			for(String string:names){
				if(ns != null){
					ns = (Namespace) ns.getMember(string, false, UMLPackage.eINSTANCE.getClassifier());
				}
			}
			if(ns instanceof Classifier){
				return (Classifier) ns;
			}
		}
		// Try the nestedClassifier/ownedBehavior containment hierarchy first
		for(String string:names){
			if(namespace != null){
				namespace = (Namespace) namespace.getMember(string, false, UMLPackage.eINSTANCE.getClassifier());
			}
		}
		if(namespace instanceof Behavior){
			if(EmfBehaviorUtil.hasExecutionInstance((Behavior) namespace)){
				return (Behavior) namespace;
			}else{
				return null;
			}
		}else if(namespace instanceof Classifier){
			return (Classifier) namespace;
		}
		Classifier result = super.lookupClassifier(names);
		if(result == null && cls != null){
			// try to resolve from imports
			if(names.size() == 1){
				for(PackageableElement pe:cls.getImportedElements()){
					if(pe.getName().equals(names.get(0)) && pe instanceof Classifier){
						return (Classifier) pe;
					}
				}
				for(Package pk:cls.getImportedPackages()){
					Type type = pk.getOwnedType(names.get(0));
					if(type instanceof Classifier){
						return (Classifier) type;
					}
				}
			}
		}
		return result;
	}
	@Override
	public List<State> getStates(Classifier owner,List<String> pathPrefix){
		if(owner instanceof TypeTypeImpl){
			owner = ((TypeTypeImpl) owner).getReferredType();
		}
		EList<State> result = new BasicEList.FastCompare<State>();
		collectStates(owner, pathPrefix, result);
		for(Classifier general:owner.allParents()){
			collectStates(general, pathPrefix, result);
		}
		Set<State> redefinitions = new java.util.HashSet<State>();
		for(State s:result){
			State redef = s.getRedefinedState();
			while(redef != null){
				redefinitions.add(redef);
				redef = redef.getRedefinedState();
			}
		}
		result.removeAll(redefinitions);
		return result;
	}
	private void collectStates(Classifier owner,List<String> pathPrefix,List<State> states){
		StateMachine sm = null;
		if(owner instanceof BehavioredClassifier && ((BehavioredClassifier) owner).getClassifierBehavior() instanceof StateMachine){
			sm = (StateMachine) ((BehavioredClassifier) owner).getClassifierBehavior();
		}else if(owner instanceof StateMachine){
			sm = (StateMachine) owner;
		}
		if(sm != null){
			collectStates(sm, pathPrefix, states);
		}
	}
	private void collectStates(StateMachine machine,List<String> pathPrefix,List<State> states){
		if(pathPrefix.isEmpty()){
			for(Region r:machine.getRegions()){
				collectStates(r, pathPrefix, states);
			}
		}else{
			String firstName = pathPrefix.get(0);
			if(UMLForeignMethods.isNamed(firstName, machine)){
				// we are allowed to qualify the states by machine name
				pathPrefix = pathPrefix.subList(1, pathPrefix.size());
			}
			for(Region r:machine.getRegions()){
				collectStates(r, pathPrefix, states);
			}
		}
	}
	private void collectStates(Region region,List<String> pathPrefix,List<State> states){
		if(pathPrefix.isEmpty()){
			// terminus of the recursion: get all the states in this region
			for(Vertex v:region.getSubvertices()){
				if(v instanceof State){
					states.add((State) v);
				}
			}
		}else{
			String firstName = pathPrefix.get(0);
			Vertex v = UMLForeignMethods.getSubvertex(region, firstName);
			if(v instanceof State){
				State state = (State) v;
				if(state.isComposite()){
					// recursively search the regions of this composite state
					pathPrefix = pathPrefix.subList(1, pathPrefix.size());
					for(Region r:state.getRegions()){
						collectStates(r, pathPrefix, states);
					}
				}
			}
		}
	}
	@Override
	public void setSelfVariable(org.eclipse.ocl.expressions.Variable<Classifier,Parameter> var){
		super.setSelfVariable(var);
	}
	@Override
	public List<Property> getAdditionalAttributes(Classifier c){
		List<Property> additionalAttributes = EmfPropertyUtil.getEffectiveProperties(c);
		if(c instanceof Class){
			Class cls = (Class) c;
			outer:for(Classifier classifier:cls.getNestedClassifiers()){
				for(Classifier general:classifier.getGenerals()){
					if(cls.getNestedClassifiers().contains(general)){
						continue outer;
					}
				}
				if(classifier instanceof Class){
					for(Classifier general:((Class) classifier).getImplementedInterfaces()){
						if(cls.getNestedClassifiers().contains(general)){
							continue outer;
						}
					}
				}
				for(Property property:EmfPropertyUtil.getEffectiveProperties(classifier)){
					if(property.getOtherEnd() != null && property.getOtherEnd().isComposite()){
						continue outer;
					}
				}
				Property p = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createProperty();
				p.setName(Character.toLowerCase(classifier.getName().charAt(0)) + classifier.getName().substring(1));
				p.setType(classifier);
				additionalAttributes.add(p);
			}
		}
		if(c instanceof org.eclipse.uml2.uml.Enumeration){
			Property p = org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createProperty();
			p.setName("values");
			p.setUpper(-1);
			p.setType(c);
			p.setIsStatic(true);
			additionalAttributes.add(p);
		}
		return additionalAttributes;
	}
	public void setFactory(
			EnvironmentFactory<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> d){
		super.setFactory(d);
	}
	private void addSpecialVariables(Collection<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> variables){
		Classifier nearestClassifier = EmfElementFinder.getNearestClassifier(context);
		if(nearestClassifier instanceof Activity){
			Element element = context;
			while(!(element instanceof Activity || element == null)){
				element = (Element) EmfElementFinder.getContainer(element);
				if(element instanceof Namespace){
					List<org.eclipse.uml2.uml.Variable> activityVars = EmfActivityUtil.getVariables((Namespace) element);
					for(org.eclipse.uml2.uml.Variable activityVariable:activityVars){
						Variable var = new EmulatedVariable(activityVariable);
						setActualType(activityVariable, var);
						var.setName(activityVariable.getName());
						variables.add(var);
					}
				}
			}
		}
		Type dateTime = library.getDateTimeType();
		if(dateTime != null){
			if(nearestClassifier instanceof StateMachine){
				addTimeObservations(variables, nearestClassifier, dateTime, StereotypeNames.BUSINESS_STATE_MACHINE);
			}else if(nearestClassifier instanceof Activity){
				addTimeObservations(variables, nearestClassifier, dateTime, StereotypeNames.BUSINES_PROCESS);
			}else{
				Element element = context;
				while(!(element instanceof Activity || element == null)){
					if(element instanceof StructuredActivityNode){
						addTimeObservations(variables, element, dateTime, StereotypeNames.STRUCTURED_BUSINESS_PROCESS_NODE);
					}
					element = (Element) EmfElementFinder.getContainer(element);
				}
			}
		}
		Type duration = library.getDurationType();
		if(duration != null){
			if(nearestClassifier instanceof StateMachine){
				addDurationObservations(variables, nearestClassifier, duration, StereotypeNames.BUSINESS_STATE_MACHINE);
			}else if(nearestClassifier instanceof Activity){
				addDurationObservations(variables, nearestClassifier, duration, StereotypeNames.BUSINES_PROCESS);
			}else{
				Element element = context;
				while(!(element instanceof Activity || element == null)){
					if(element instanceof StructuredActivityNode){
						addDurationObservations(variables, element, duration, StereotypeNames.STRUCTURED_BUSINESS_PROCESS_NODE);
					}
					element = (Element) EmfElementFinder.getContainer(element);
				}
			}
		}
		if(nearestClassifier instanceof Behavior){
		}else if(!(nearestClassifier == null || nearestClassifier.isAbstract())){
			if(EmfPropertyUtil.getEndToComposite(nearestClassifier) == null){
				Classifier owningObject = null;
				owningObject = EmfElementFinder.getNearestClassifier(nearestClassifier.getOwner());
				if(owningObject != null){
					Variable var = UMLFactory.eINSTANCE.createVariable();
					var.setType((Type) owningObject);
					var.setName("owningObject");
					variables.add(var);
				}
			}
		}
		List<TypedElement> tes = EmfElementFinder.getTypedElementsInScope(context);
		for(TypedElement te:tes){
			if(te instanceof org.eclipse.uml2.uml.Variable || te instanceof Parameter || te instanceof Pin){
				Variable var = new EmulatedVariable(te);
				// if(te instanceof Parameter){
				// setContextOperation(org.eclipse.uml2.uml.UMLFactory.eINSTANCE.createOperation());
				// getContextOperation().getOwnedParameters().add((Parameter) te);
				// }
				setActualType(te, var);
				var.setName(te.getName());
				variables.add(var);
			}
		}
		Type br = library.getBusinessRole();
		if(br != null){
			Variable var = UMLFactory.eINSTANCE.createVariable();
			var.setType(br);
			var.setName("currentBusinessRole");
			variables.add(var);
		}
		if(library.getPersonNode() != null){
			Variable var = UMLFactory.eINSTANCE.createVariable();
			var.setType(library.getPersonNode());
			var.setName("currentUser");
			variables.add(var);
		}
		if(library.getBusinessRole() != null){
			Variable var = UMLFactory.eINSTANCE.createVariable();
			var.setType(library.getBusinessRole());
			var.setName("currentRole");
			variables.add(var);
		}
		if(dateTime != null){
			Variable var = UMLFactory.eINSTANCE.createVariable();
			var.setType(dateTime);
			var.setName("now");
			variables.add(var);
		}
//		for(int i = 0;i < super.getElementsSize();i++){
//			VariableEntry element = super.getElement(i);
//			if(!element.isExplicit()){
//				Classifier elementType = (Classifier) element.getVariable().getType();
//				List<Property> props = EmfPropertyUtil.getEffectiveProperties(elementType);
//				for(Property property:props){
//					PropertyOfImplicitObject var = new PropertyOfImplicitObject((Variable) element.getVariable(), property);
//					var.setName(property.getName());
//					var.setType(getTypeChecker().getPropertyType( elementType, property));
//					variables.add(var);
//				}
//			}
//		}
		variables.addAll(this.variables);
	}
	protected void setActualType(TypedElement te,Variable var){
		CollectionKind ck = EmfPropertyUtil.getCollectionKind((MultiplicityElement) te);
		if(ck==null){
		var.setType(te.getType());
		}else{
			var.setType((Type) getTypeResolver().resolveCollectionType(ck, (Classifier) te.getType()));
		}
	}
	private void addDurationObservations(Collection<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> variables,Element element,
			Type duration,String businesStateMachine){
		Stereotype s = StereotypesHelper.getStereotype(element, businesStateMachine);
		if(s != null){
			@SuppressWarnings("unchecked")
			EList<DurationObservation> obs = (EList<DurationObservation>) element.getValue(s, "durationObservations");
			for(DurationObservation ob:obs){
				Variable var = new EmulatedVariable(ob);
				var.setType(duration);
				var.setName(ob.getName());
				variables.add(var);
			}
		}
	}
	protected void addTimeObservations(Collection<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> variables,Element element,
			Type br,String businesStateMachine){
		Stereotype s = StereotypesHelper.getStereotype(element, businesStateMachine);
		if(s != null){
			@SuppressWarnings("unchecked")
			EList<TimeObservation> obs = (EList<TimeObservation>) element.getValue(s, "timeObservations");
			for(TimeObservation timeObservation:obs){
				Variable var = new EmulatedVariable(timeObservation);
				var.setType(br);
				var.setName(timeObservation.getName());
				variables.add(var);
			}
		}
	}
	@Override
	public Property lookupProperty(Classifier owner,String name){
		if(name.equals("allReports")){
			System.out.println();
		}
		Property p = super.lookupProperty(owner, name);
		if(p == null && owner instanceof PrimitiveType){
			PrimitiveType pt = EmfClassifierUtil.getRootClass((PrimitiveType) owner);
			Classifier oclType = getTypeResolver().resolve(pt);
			if(oclType != null){
				p = super.lookupProperty(oclType, name);
			}
		}
		return p;
	}
	@Override
	public Operation lookupOperation(Classifier owner,String name,List<? extends org.eclipse.ocl.utilities.TypedElement<Classifier>> args){
		Operation o = super.lookupOperation(owner, name, args);
		if(o == null){
			if(name.equals("toString")){
				Operation ao = library.getAdditionalOperations().get(owner.getQualifiedName() + "::" + name);
				if(ao == null){
					ao = new StdlLibOperation(){
						@Override
						public Element getOwner(){
							return getOCLStandardLibrary().getOclAny();
						}
					};
					ao.setIsQuery(true);
					ao.setName("toString");
					ao.setType(library.getStringType());
					library.getAdditionalOperations().put(owner.getQualifiedName() + "::" + name, ao);
				}
				o = ao;
			}else if(owner instanceof PrimitiveType){
				PrimitiveType pt = EmfClassifierUtil.getRootClass((PrimitiveType) owner);
				Classifier oclType = getTypeResolver().resolve(pt);
				if(oclType != null){
					if(name.length() > 2){
						o = super.lookupOperation(oclType, name, args);
					}else{
						List<Operation> operations = getTypeChecker().getOperations(oclType);
						for(Operation operation:operations){
							if(operation.getName().equals(name)){
								o = operation;
								break;
							}
						}
					}
				}
			}else if(owner instanceof BehavioredClassifier){
				BehavioredClassifier bc = (BehavioredClassifier) owner;
				for(Interface intf:bc.getImplementedInterfaces()){
					o = super.lookupOperation(intf, name, args);
					if(o != null){
						break;
					}
				}
			}else if(owner instanceof CollectionType){
				outer:for(Operation operation:owner.getAllOperations()){
					if(operation.getName().equals(name)){
						int i = 0;
						for(Parameter p:operation.getOwnedParameters()){
							if(p.getDirection() == ParameterDirectionKind.IN_LITERAL){
								if(!EmfClassifierUtil.conformsTo(args.get(i).getType(), (Classifier) p.getType())){
									continue outer;
								}
								i++;
							}
						}
						return operation;
					}
				}
			}
		}
		return o;
	}
	@Override
	protected TypeChecker<Classifier,Operation,Property> createTypeChecker(){
		return new OpaeumTypeChecker(this);
	}
	@Override
	public State lookupState(Classifier owner,List<String> path) throws LookupException{
		return super.lookupState(owner, path);
	}
	public void addVariables(Map<String,Classifier> variables2){
		for(Entry<String,Classifier> entry:variables2.entrySet()){
			Variable var = UMLFactory.eINSTANCE.createVariable();
			var.setType(entry.getValue());
			var.setName(entry.getKey());
			variables.add(var);
		}
	}
}