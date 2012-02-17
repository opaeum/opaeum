package org.opaeum.topcased.propertysections.ocl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EnvironmentFactory;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.ocl.uml.impl.TypeTypeImpl;
import org.eclipse.ocl.uml.internal.UMLForeignMethods;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfElementFinder;

public final class OpaeumEnvironment extends UMLEnvironment{
	@Override
	public Variable<Classifier,Parameter> lookup(String name){
		// TODO Auto-generated method stub
		return super.lookup(name);
	}
	@Override
	public Collection<Variable<Classifier,Parameter>> getVariables(){
		return super.getVariables();
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
		if(ns instanceof Classifier){
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
	public void setSelfVariable(Variable<Classifier,Parameter> var){
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
	public OpaeumEnvironment(EPackage.Registry registry,ResourceSet rset){
		super(registry, rset);
	}
	public OpaeumEnvironment(
			Environment<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> parent){
		super(parent);
	}
	public void setFactory(
			EnvironmentFactory<Package,Classifier,Operation,Property,EnumerationLiteral,Parameter,State,CallOperationAction,SendSignalAction,Constraint,Class,EObject> d){
		super.setFactory(d);
	}
}