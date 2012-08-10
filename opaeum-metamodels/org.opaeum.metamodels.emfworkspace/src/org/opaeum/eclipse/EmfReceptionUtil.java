package org.opaeum.eclipse;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Reception;

public class EmfReceptionUtil{
	public static Collection<Reception> getDirectlyImplementedReceptions(BehavioredClassifier bc){
		Set<String> inheritedConcreteReceptionNames = new TreeSet<String>();
		for(Generalization g:bc.getGeneralizations()){
			if(g.getGeneral() instanceof BehavioredClassifier){
				for(Reception o:getDirectlyImplementedReceptions((BehavioredClassifier) g.getGeneral())){
					//Remember, an interface may be implemented both by the superclass as well as 'bc'
					inheritedConcreteReceptionNames.add(EmfParameterUtil.toIdentifyingString(o));
				}
			}
		}
		Set<Reception> results = new TreeSet<Reception>(new ElementComparator());
		for(Reception o:getEffectiveReceptions(bc)){
			if(o.getOwner()==bc ||  !inheritedConcreteReceptionNames.contains(EmfParameterUtil.toIdentifyingString(o))){
				results.add(o);
			}
		}
		return results;
	}
	public static Set<Reception> getEffectiveReceptions(BehavioredClassifier bc){
		Set<Reception> result = new TreeSet<Reception>(new ElementComparator());
		if(bc instanceof Class){
			addReceptions(result, (Class) bc);
		}
		for(Interface i:bc.getImplementedInterfaces()){
			addReceptions(result, i);
		}
		return result;
	}

	private static void addReceptions(Set<Reception> result,Class bc2){
		result.addAll(bc2.getOwnedReceptions());
		for(Classifier c:bc2.getGenerals()){
			if(c instanceof Class){
				addReceptions(result, (Class) c);
			}
		}
	}

	private static void addReceptions(Set<Reception> result,Interface i){
		result.addAll(i.getOwnedReceptions());
		EList<Classifier> generals = i.getGenerals();
		for(Classifier classifier:generals){
			if(classifier instanceof Interface){
				addReceptions(result, (Interface) classifier);
			}
		}
	}
}
