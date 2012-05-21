package org.nakeduml.tinker.activity.maps;

import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.metamodel.core.INakedProperty;

public class TinkerStructuralFeatureMap extends NakedStructuralFeatureMap {
	public TinkerStructuralFeatureMap(INakedProperty feature) {
		super(feature);
		baseTypeMap = new TinkerClassifierMap(feature.getNakedBaseType());
		featureTypeMap = new TinkerClassifierMap(feature.getType());
	}
}
