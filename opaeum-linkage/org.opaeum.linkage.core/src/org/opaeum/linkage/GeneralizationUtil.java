package org.opaeum.linkage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class GeneralizationUtil{
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	public static Collection<INakedBehavioredClassifier> getConcreteEntityImplementationsOf(INakedInterface baseType,Collection<INakedRootObject> models){
		SortedSet<INakedClassifier> results = new TreeSet<INakedClassifier>(new DefaultOpaeumComparator());
		addConcreteSubclasses(results, baseType, models, true);
		results.remove(baseType);
		return (Collection) results;
	}
	public static Collection<INakedClassifier> getAllSubClassifiers(INakedClassifier baseType,Collection<INakedRootObject> models){
		Set<INakedClassifier> results = new HashSet<INakedClassifier>();
		addConcreteSubclasses(results, baseType, models, false);
		results.remove(baseType);
		return results;
	}
	public static Collection<INakedClassifier> getAllConcreteSubClassifiers(INakedClassifier baseType,Collection<INakedRootObject> models){
		Set<INakedClassifier> results = new HashSet<INakedClassifier>();
		addConcreteSubclasses(results, baseType, models, true);
		results.remove(baseType);
		return results;
	}
	private static void addConcreteSubclasses(Set<INakedClassifier> results,INakedClassifier baseType,Collection<INakedRootObject> models,boolean concreteOnly){
		if(models.contains(baseType.getNakedRoot())){
			if(!(baseType.getIsAbstract() && concreteOnly)){
				if(baseType.getStereotype(StereotypeNames.HELPER) == null){
					results.add(baseType);
				}
			}
			if(baseType instanceof INakedInterface){
				for(INakedClassifier ic:((INakedInterface) baseType).getImplementingClassifiers()){
					addConcreteSubclasses(results, ic, models, concreteOnly);
				}
			}
			for(IClassifier c:baseType.getSubClasses()){
				addConcreteSubclasses(results, (INakedClassifier) c, models, concreteOnly);
			}
		}
	}
}
