package org.opaeum.eclipse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;

public class EmfOperationUtil{
	public static boolean isPrefix(Operation o){
		return o.getName().equals("++") || o.getName().equals("--");
	}
	public static Collection<Operation> getDirectlyImplementedOperations(Classifier bc){
		Set<String> inheritedConcreteOperationNames = new HashSet<String>();
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
		Set<Operation> results = new HashSet<Operation>();
		for(Operation o:getEffectiveOperations(bc)){
			if(o.getOwner() == bc || !inheritedConcreteOperationNames.contains(EmfParameterUtil.toIdentifyingString(o))){
				results.add(o);
			}
		}
		return results;
	}
	public static Set<Operation> getEffectiveOperations(Classifier bc){
		Set<Operation> result = new HashSet<Operation>();
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
			addOperations(result, (Class) c);
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
}
