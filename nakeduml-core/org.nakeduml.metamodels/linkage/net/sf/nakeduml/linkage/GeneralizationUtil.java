package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import nl.klasse.octopus.model.IClassifier;

public class GeneralizationUtil{
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	public static Collection<INakedBehavioredClassifier> getConcreteEntityImplementationsOf(INakedInterface baseType,Collection<INakedRootObject> models){
		Set<INakedClassifier> results = new HashSet<INakedClassifier>();
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
