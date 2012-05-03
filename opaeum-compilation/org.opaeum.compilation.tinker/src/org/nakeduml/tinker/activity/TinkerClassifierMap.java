package org.nakeduml.tinker.activity;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;

public class TinkerClassifierMap extends NakedClassifierMap{
	public TinkerClassifierMap(IClassifier modelClass){
		super(modelClass);
		// NB!! remember that modelClass could be a collectionType
	}
	protected OJPathName pathname(IModelElement t){
		if (t instanceof ConcretePinEmulatedClassifier) {
			return TinkerBehaviorUtil.activityNodePathName(((ConcretePinEmulatedClassifier)t).getPin());
		} else if(t instanceof INakedClassifier){
			return OJUtil.classifierPathname((INakedClassifier) t);
		}else{
			return super.pathname(t);
		}
	}

}
