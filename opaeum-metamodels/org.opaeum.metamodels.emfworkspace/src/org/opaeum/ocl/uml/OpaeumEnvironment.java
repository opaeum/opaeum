package org.opaeum.ocl.uml;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.uml.UMLEnvironment;
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
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
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
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

@SuppressWarnings("restriction")
public final class OpaeumEnvironment extends UMLEnvironment{
	OpaeumLibrary library;
	Element context;
	private Collection<Variable> variables;
	public OpaeumEnvironment(
			Element context,
			Environment<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> parent,
			OpaeumLibrary library,Collection<Variable> variables){
		super(parent);
		Variable self = UMLFactory.eINSTANCE.createVariable();
		self.setName("self");
		self.setType(EmfBehaviorUtil.getSelf(context));
		setSelfVariable(self);
		this.context = context;
		this.library = library;
		this.variables = variables;
		setProblemHandler(new OpaeumOclProblemHandler(getParser()));
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
		if(cls.getOwner() instanceof Namespace){
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
		Namespace ns = cls;
		for(String string:names){
			if(ns != null){
				ns = (Namespace) ns.getMember(string, false, UMLPackage.eINSTANCE.getClassifier());
			}
		}
		if(ns instanceof Behavior){
			if(EmfBehaviorUtil.hasExecutionInstance((Behavior) ns)){
				return (Behavior) ns;
			}else{
				return null;
			}
		}else if(ns instanceof Classifier){
			return (Classifier) ns;
		}
		Classifier result = super.lookupClassifier(names);
		if(result == null){
			// try to resolve from imports
			if(names.size() == 1){
				result = (Classifier) cls.getImportedMember(names.get(0), false, UMLPackage.eINSTANCE.getClassifier());
				if(result == null && cls.getOwner() instanceof Namespace){
					result = (Classifier) ((Namespace) cls.getOwner()).getImportedMember(names.get(0), false, UMLPackage.eINSTANCE.getClassifier());
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
		List<Property> additionalAttributes = EmfElementFinder.getPropertiesInScope(c);
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
				for(Property property:EmfElementFinder.getPropertiesInScope(classifier)){
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
		Model simpleTypes = library.findLibrary(StereotypeNames.OPAEUM_SIMPLE_TYPES);
		Model bpmLib = library.findLibrary(StereotypeNames.OPAEUM_BPM_LIBRARY);
		Model organizationLib = library.findLibrary(StereotypeNames.OPAEUM_ORGANIZATION_LIBRARY);
		if(simpleTypes != null){
			Type dateTime = simpleTypes.getOwnedType("DateTime");
			if(dateTime != null){
				if(nearestClassifier instanceof StateMachine){
					addTimeObservations(variables, nearestClassifier, dateTime, StereotypeNames.BUSINES_STATE_MACHINE);
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
		}
		if(bpmLib != null){
			Type duration = bpmLib.getNestedPackage("businesscalendar").getOwnedType("Duration");
			if(duration != null){
				if(nearestClassifier instanceof StateMachine){
					addDurationObservations(variables, nearestClassifier, duration, StereotypeNames.BUSINES_STATE_MACHINE);
				}else if(nearestClassifier instanceof Activity){
					addDurationObservations(variables, nearestClassifier, duration, StereotypeNames.BUSINES_PROCESS);
				}else{
					Element element = context;
					while(!(element instanceof Activity || element == null)){
						if(element instanceof StructuredActivityNode){
							addTimeObservations(variables, element, duration, StereotypeNames.STRUCTURED_BUSINESS_PROCESS_NODE);
						}
						element = (Element) EmfElementFinder.getContainer(element);
					}
				}
			}
		}
		if(nearestClassifier instanceof Behavior){
			Classifier contextObject = EmfBehaviorUtil.getContext(context);
			if(contextObject != null){
				Variable var = UMLFactory.eINSTANCE.createVariable();
				var.setType(contextObject);
				var.setName("contextObject");
				variables.add(var);
			}
		}else if(!(nearestClassifier == null || nearestClassifier.isAbstract())){
			if(library.getEndToComposite(nearestClassifier) == null){
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
				var.setType(te.getType());
				var.setName(te.getName());
				variables.add(var);
			}
		}
		if(bpmLib != null){
			Type br = bpmLib.getNestedPackage("organization").getOwnedType("IBusinessRole");
			if(br != null){
				Variable var = UMLFactory.eINSTANCE.createVariable();
				var.setType(br);
				var.setName("currentBusinessRole");
				variables.add(var);
			}
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
		if(simpleTypes != null){
			Type br = simpleTypes.getOwnedType("DateTime");
			if(br != null){
				Variable var = UMLFactory.eINSTANCE.createVariable();
				var.setType(br);
				var.setName("now");
				variables.add(var);
			}
		}
		variables.addAll(this.variables);
	}
	private void addDurationObservations(Collection<org.eclipse.ocl.expressions.Variable<Classifier,Parameter>> variables,Element element,
			Type duration,String businesStateMachine){
		Stereotype s = StereotypesHelper.getStereotype(element, businesStateMachine);
		if(s != null){
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
		if(o == null && owner instanceof PrimitiveType){
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
		}
		return o;
	}
}