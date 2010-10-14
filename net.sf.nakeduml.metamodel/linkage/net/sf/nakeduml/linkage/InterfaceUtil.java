package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import nl.klasse.octopus.model.IClassifier;

public class InterfaceUtil {
	public static Collection<INakedEntity> getImplementationsOf(INakedClassifier baseType) {
		Set<INakedEntity> results = new HashSet<INakedEntity>();
		addConcreteSubclasses(results, baseType);
		results.remove(baseType);
		return results;
	}

	private static void addConcreteSubclasses(Set<INakedEntity> results, INakedClassifier baseType) {
		if (baseType instanceof INakedEntity && !baseType.getIsAbstract()) {
			results.add((INakedEntity) baseType);
		}
		if (baseType instanceof INakedInterface) {
			for (INakedClassifier ic : ((INakedInterface) baseType).getImplementingClassifiers()) {
				addConcreteSubclasses(results, ic);
			}
		}
		for (IClassifier c : baseType.getSubClasses()) {
			addConcreteSubclasses(results, (INakedClassifier) c);
		}
	}
}