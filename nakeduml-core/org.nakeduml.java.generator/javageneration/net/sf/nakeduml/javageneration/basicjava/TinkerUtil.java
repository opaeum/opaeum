package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public class TinkerUtil {

	public static String constructCompositeRelationshipName(INakedClassifier compositeOwner, INakedClassifier otherOwner, INakedProperty composite) {
		String relationshipName = compositeOwner.getMappingInfo().getQualifiedUmlName();
		relationshipName += composite.getAssociation().getName();
		relationshipName += otherOwner.getMappingInfo().getQualifiedUmlName();
		return relationshipName;
	}
	
	public static String tinkerpoperizeUmlName(String umlName) {
		return umlName.replace("::", "__");
	}

}
