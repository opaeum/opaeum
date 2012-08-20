package org.opaeum.eclipse;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;

public class EmfOperationUtil{
	public static boolean isPrefix(Operation o){
		return o.getName().equals("++") || o.getName().equals("--");
	}
	public static Collection<Operation> getDirectlyImplementedOperations(Classifier bc){
		Set<String> inheritedConcreteOperationNames = new TreeSet<String>();
		for(Generalization g:bc.getGeneralizations()){
			if(g.getGeneral() instanceof BehavioredClassifier){
				for(Operation o:getDirectlyImplementedOperations((BehavioredClassifier) g.getGeneral())){
					boolean mustImplement = o.isAbstract() && g.getGeneral().isAbstract() && !bc.isAbstract();
					if(!mustImplement){
						// Remember, an interface may be implemented both by the superclass as well as 'bc'
						inheritedConcreteOperationNames.add(EmfParameterUtil.toIdentifyingString(o));
					}
				}
			}
		}
		Set<Operation> results = new TreeSet<Operation>(new ElementComparator());
		for(Operation o:getEffectiveOperations(bc)){
			if(o.getOwner() == bc || !inheritedConcreteOperationNames.contains(EmfParameterUtil.toIdentifyingString(o))){
				results.add(o);
			}
		}
		return results;
	}
	public static Set<Operation> getEffectiveOperations(Classifier bc){
		Set<Operation> result = new TreeSet<Operation>(new ElementComparator());
		addOperations(result, bc);
		if(bc instanceof BehavioredClassifier){
			for(Interface i:((BehavioredClassifier) bc).getImplementedInterfaces()){
				addOperations(result, i);
			}
		}
		return result;
	}
	private static void addOperations(Set<Operation> result,Classifier from){
		result.addAll(from.getOperations());
		for(Classifier c:from.getGenerals()){
			addOperations(result, c);
		}
	}
	private static void addOperations(Set<Operation> result,Interface i){
		result.addAll(i.getOwnedOperations());
		EList<Classifier> generals = i.getGenerals();
		for(Classifier classifier:generals){
			if(classifier instanceof Interface){
				addOperations(result, (Interface) classifier);
			}
		}
	}
	public static boolean hasImplementingMethodIn(Operation o, Classifier c){
		EList<Behavior> methods = o.getMethods();
		for(Behavior behavior:methods){
			if(behavior.getContext()==c){
				return true;
			}
		}
		return false;
	}
}
