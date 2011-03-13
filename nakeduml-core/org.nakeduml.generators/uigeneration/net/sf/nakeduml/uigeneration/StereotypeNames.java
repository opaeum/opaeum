package net.sf.nakeduml.uigeneration;

import org.nakeduml.name.NameConverter;

import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public class StereotypeNames {
	// Stereotypes
	public static final String CLASSIFIER_SPECIFICATION = "ClassifierSpecification";
	public static final String PROPERTY_SPECIFICATION = "PropertySpecification";
	public static final String PARAMETER_SPECIFICATION = "ParameterSpecification";
	public static final String OPERATION_SPECIFICATION = "OperationSpecification";
	public static final String INBOX_SPECIFICATION = "InboxSpecification";
	public static final String USER_INTERACTION = "UserInteraction";
	public static final String PARTICIPATION = "Participation";
	public static final String SECURITY_ON_VIEW = "SecurityOnView";
	public static final String SECURITY_ON_DELETE = "SecurityOnDelete";
	public static final String SECURITY_ON_EDIT = "SecurityOnEdit";
	public static final String SECURITY_ON_CREATE = "SecurityOnCreate";
	public static final String USER_ROLE = "UserRole";
	public static final String HIERARCHY = "Hierarchy";
	// tagged values
	public static final String DISPLAY_INDEX = "displayIndex";
	public static final String PARTICIPATION_GROUP = "participationGroup";
	public static final String HUMAN_NAME = "humanName";
	public static final String HAS_USER_INTERACTION = "hasUserInteractions";
	public static final String INSTRUCTION_TO_USER = "instructionToUser";
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String PARTICIPATION_KIND = "participationKind";
	public static final String RESULTING_USER_INTERACTION = "resultingUserInteraction";
	public static final String USER_INTERACTION_KIND = "userInteractionKind";
	public static final String USER_INTERACTION_FOR_OWNER = "userInteractionForOwner";
	public static final String USER_INTERACTION_FOR_RESULT = "userInteractionForResult";
	public static final String NAVIGATION_TOO_MANY = "TooMany";

	public static final <V extends Enum> V resolve(INakedEnumerationLiteral l, Class<V> c) {
		for (V e : c.getEnumConstants()) {
			if (e.name().equalsIgnoreCase(l.getName())) {
				return e;
			}
		}
		throw new IllegalArgumentException("Literal " + l.getName() + " not found!");
	}

	public static final <V extends Enum> String participationIn(V c) {
		return "ParticipationIn" + NameConverter.capitalize(c.name().toLowerCase());
	}

	public static final <V extends Enum> String participationInGroup(V c) {
		return "ParticipationIn" + NameConverter.capitalize(c.name().toLowerCase()) + "Group";
	}

	public static <E> E getTag(INakedElement e, String stereotype, String name) {
		if (e.hasTaggedValue(stereotype, name)) {
			INakedValueSpecification tv = e.getTaggedValue(stereotype, name);
			if (tv != null && tv.getValue() != null) {
				return (E) tv.getValue();
			}
		}
		return null;
	}
}
