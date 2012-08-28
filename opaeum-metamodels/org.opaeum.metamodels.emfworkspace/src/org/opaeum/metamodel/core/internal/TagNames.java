package org.opaeum.metamodel.core.internal;

import java.util.HashMap;
import java.util.Map;

public class TagNames{
	public static final Map<String,String> COMPOSITE_ATTRIBUTES=new HashMap<String,String>();
	public static final String IS_ROOT_ENTITY = "isRootEntity";
	public static final String DEFAULT_EMAIL_ADDRESS = "defaultEmailAddress";
	public static final String MAPPED_IMPLEMENTATION_TYPE = "mappedImplementationType";
	public static final String CODE_GENERATION_STRATEGY = "codeGenerationStrategy";
	public static final String MAPPED_IMPLEMENTATION_PACKAGE = "mappedImplementationPackage";
	public static final String IS_ROOT_PACKAGE = "isRootPackage";
	public static final String IS_SCHEMA = "isSchema";
	public static final String NAME_PROPERTY = "nameProperty";
	public static final String ROLE_IN_CUBE = "roleInCube";
	public static final String AGGREGATION_FORMULAS="aggregationFormulas";
	public static final String DERIVATION_FORMULA = "derivationFormula";
	public static final String IS_PRIMARY_KEY = "isPrimaryKey";
	public static final String FROM_EXPRESSION = "fromExpression";
	public static final String CC_EXPRESSION = "ccExpression";
	public static final String BCC_EXPRESSION = "bccExpression";			
	public static final String TEMPLATE_EXPRESSION = "templateExpression";
	public static final String REASSIGNMENT= "reassignment";			
	public static final String DEADLINES = "deadlines";			
	public static final String NOTIFICATIONS = "notifications";			
	public static final String POTENTIAL_STAKEHOLDERS= "potentialStakeholders";			
	public static final String STAKEHOLDERS= "stakeholders";			
	public static final String POTENTIAL_OWNERS= "potentialOwners";			
	public static final String POTENTIAL_BUSINESS_ADMINISTRATORS= "potentialBusinessAdministrators";			
	public static final String BUSINESS_ADMINISTRATORS= "businessAdministrators";			
	public static final String TIME_OBSERVATIONS= "timeObservations";			
	public static final String DURATION_OBSERVATIONS= "durationObservations";			
	public static final String EVALUATION_INTERVAL= "evaluationInterval";			
	public static final String SELECTOR= "selector";			
	public static final String BUSINESS_CALENDAR_TO_USE= "businessCalendarToUse";
	public static final String TIME_UNIT = "timeUnit";
	public static final String DEADLINE_KIND = "kind";			
	static{
		COMPOSITE_ATTRIBUTES.put(FROM_EXPRESSION, StereotypeNames.RECIPIENT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(CC_EXPRESSION, StereotypeNames.RECIPIENT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(BCC_EXPRESSION, StereotypeNames.RECIPIENT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(FROM_EXPRESSION, StereotypeNames.RECIPIENT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(TEMPLATE_EXPRESSION, null);
		COMPOSITE_ATTRIBUTES.put(REASSIGNMENT, StereotypeNames.PARTICIPANT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(DEADLINES, StereotypeNames.DEADLINE);
		COMPOSITE_ATTRIBUTES.put(NOTIFICATIONS, StereotypeNames.SEND_NOTIFICATION_ACTION);
		COMPOSITE_ATTRIBUTES.put(POTENTIAL_STAKEHOLDERS, StereotypeNames.PARTICIPANT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(STAKEHOLDERS, StereotypeNames.PARTICIPANT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(POTENTIAL_OWNERS, StereotypeNames.PARTICIPANT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(POTENTIAL_BUSINESS_ADMINISTRATORS, StereotypeNames.PARTICIPANT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(BUSINESS_ADMINISTRATORS, StereotypeNames.PARTICIPANT_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(TIME_OBSERVATIONS, null);
		COMPOSITE_ATTRIBUTES.put(DURATION_OBSERVATIONS, StereotypeNames.BUSINESS_DURATION_OBSERVATION);
		COMPOSITE_ATTRIBUTES.put(EVALUATION_INTERVAL, StereotypeNames.DURATION_EXPRESSION);
		COMPOSITE_ATTRIBUTES.put(SELECTOR, StereotypeNames.METHOD);
		COMPOSITE_ATTRIBUTES.put(BUSINESS_CALENDAR_TO_USE, StereotypeNames.BUSINESS_CALENDAR_EXPRESSION);
	}
}
