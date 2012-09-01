package org.opaeum.eclipse.newchild;

import static org.opaeum.metamodel.core.internal.TagNames.BCC_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.BUSINESS_ADMINISTRATORS;
import static org.opaeum.metamodel.core.internal.TagNames.BUSINESS_CALENDAR_TO_USE;
import static org.opaeum.metamodel.core.internal.TagNames.CC_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.DURATION_OBSERVATIONS;
import static org.opaeum.metamodel.core.internal.TagNames.EVALUATION_INTERVAL;
import static org.opaeum.metamodel.core.internal.TagNames.FROM_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.NOTIFICATIONS;
import static org.opaeum.metamodel.core.internal.TagNames.POTENTIAL_OWNERS;
import static org.opaeum.metamodel.core.internal.TagNames.REASSIGNMENT;
import static org.opaeum.metamodel.core.internal.TagNames.STAKEHOLDERS;
import static org.opaeum.metamodel.core.internal.TagNames.TEMPLATE_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.TIME_OBSERVATIONS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class CreateChildActions{
	private static UMLPackage pkg = UMLPackage.eINSTANCE;
	public static Set<DefaultCreateChildAction> ACTIONS = new HashSet<DefaultCreateChildAction>();
	public static Set<EReference> CONTROLLED_FEATURES = new HashSet<EReference>();
	private static MatchingOwner DEADLINE = new MatchingOwner(pkg.getTimeEvent(), StereotypeNames.DEADLINE);
	private static MatchingOwner CONTEXTUAL_BUSINESS_TIME_EVENT = new MatchingOwner(pkg.getTimeEvent(),
			StereotypeNames.CONTEXTUAL_BUSINESS_TIME_EVENT);
	private static MatchingOwner CONTEXTUAL_CHANGE_EVENT = new MatchingOwner(pkg.getTimeEvent(), StereotypeNames.CONTEXTUAL_CHANGE_EVENT);
	private static MatchingOwner SEND_NOTIFICATION_ACTION = new MatchingOwner(pkg.getSendSignalAction(),
			StereotypeNames.SEND_NOTIFICATION_ACTION);
	private static MatchingOwner ESCALATION = new MatchingOwner(pkg.getConstraint(), StereotypeNames.ESCALATION);
	private static MatchingOwner STANDALONE_SINGLSCREEN_TASK = new MatchingOwner(pkg.getOpaqueBehavior(),
			StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK);
	private static MatchingOwner NOTIFICATION = new MatchingOwner(pkg.getSignal(), StereotypeNames.NOTIFICATION);
	private static MatchingOwner STANDALONE_SCREENFLOW_TASL = new MatchingOwner(pkg.getStateMachine(),
			StereotypeNames.STANDALONE_SCREENFLOW_TASK, StereotypeNames.BUSINESS_STATE_MACHINE);
	private static MatchingOwner BUSINESS_PROCESS = new MatchingOwner(pkg.getActivity(), StereotypeNames.BUSINES_PROCESS);
	private static final MatchingOwner RESPONSIBILITY = new MatchingOwner(pkg.getOperation(), StereotypeNames.RESPONSIBILITY);
	private static final MatchingOwner BUSINESS_STATEMACHINE = new MatchingOwner(pkg.getStateMachine(),
			StereotypeNames.BUSINESS_STATE_MACHINE);
	private static final MatchingOwner STRUCTURED_BUSINESS_NODE = new MatchingOwner(pkg.getStructuredActivityNode(),
			StereotypeNames.STRUCTURED_BUSINESS_PROCESS_NODE);
	private static final MatchingOwner DURATION_EXPRESSION = new MatchingOwner(pkg.getOpaqueExpression(), StereotypeNames.DURATION_EXPRESSION);
	private static final MatchingOwner DURATION_OBSERVATION = new MatchingOwner(pkg.getDurationObservation(),
			StereotypeNames.BUSINESS_DURATION_OBSERVATION);
	private static final MatchingOwner ENTITY = new MatchingOwner(pkg.getClass_());
	private static final MatchingOwner BUSINESS_COMPONENT = new MatchingOwner(pkg.getComponent(), StereotypeNames.BUSINESS_COMPONENT);
	private static final MatchingOwner BUSINESS_DOCUMENT = new MatchingOwner(pkg.getClass_(), StereotypeNames.BUSINESS_DOCUMENT);
	private static final MatchingOwner BUSINESS_ROLE = new MatchingOwner(pkg.getClass_(), StereotypeNames.BUSINESS_ROLE);
	private static final MatchingOwner BUSINESS_ACTOR = new MatchingOwner(pkg.getActor(), StereotypeNames.BUSINESS_ACTOR);
	private static final MatchingOwner PACKAGE = new MatchingOwner(pkg.getPackage());
	private static final MatchingOwner PROFILE = new MatchingOwner(pkg.getProfile());
	private static final MatchingOwner MODEL = new MatchingOwner(pkg.getModel());
	public static final Map<String,Set<DefaultCreateChildAction>> FEATURES = new HashMap<String,Set<DefaultCreateChildAction>>();
	private static final MatchingOwner OPERATION = new MatchingOwner(pkg.getOperation());
	private static MatchingOwner[] PACKAGES = {PACKAGE,MODEL};
	private static MatchingOwner[] DEADLINE_OWNERS = {RESPONSIBILITY,STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,BUSINESS_PROCESS};
	private static MatchingOwner[] NOTIFICATION_OWNERS = {STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,BUSINESS_PROCESS};
	private static MatchingOwner[] OBSERVED = {BUSINESS_STATEMACHINE,STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,BUSINESS_PROCESS,
			STRUCTURED_BUSINESS_NODE};
	private static MatchingOwner[] TASKS = {STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,BUSINESS_PROCESS};
	private static MatchingOwner[] INTERVAL_EVALUATED = {CONTEXTUAL_CHANGE_EVENT};
	private static MatchingOwner[] BUSINESS_CALENDAR_USERS = {DURATION_OBSERVATION,DURATION_EXPRESSION,CONTEXTUAL_BUSINESS_TIME_EVENT,
			DEADLINE};
	private static MatchingOwner[] ATTRIBUTE_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,
			STANDALONE_SINGLSCREEN_TASK,ENTITY,BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] OPERATION_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,
			STANDALONE_SINGLSCREEN_TASK,ENTITY,BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] METHOD_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,ENTITY,
			BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] PROCESS_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,ENTITY,
			BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] TASK_OWNERS = {BUSINESS_ROLE,BUSINESS_ACTOR,BUSINESS_COMPONENT};
	private static MatchingOwner[] INVARIANT_OWNERS = {BUSINESS_ROLE,BUSINESS_ACTOR,BUSINESS_COMPONENT,ENTITY,OPERATION,
		BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,STANDALONE_SINGLSCREEN_TASK};
	private static MatchingOwner[] CLASSIFIER_BEHAVIOR_OWNERS = {BUSINESS_ROLE,BUSINESS_ACTOR,BUSINESS_COMPONENT,ENTITY};
	static{
		CONTROLLED_FEATURES.add(pkg.getClass_NestedClassifier());
		CONTROLLED_FEATURES.add(pkg.getStructuredClassifier_OwnedAttribute());
		CONTROLLED_FEATURES.add(pkg.getBehavioredClassifier_OwnedBehavior());
		CONTROLLED_FEATURES.add(pkg.getBehavioredClassifier_ClassifierBehavior());
		CONTROLLED_FEATURES.add(pkg.getClass_OwnedOperation());
		CONTROLLED_FEATURES.add(pkg.getPackage_PackagedElement());
		CONTROLLED_FEATURES.add(pkg.getPackage_OwnedType());
		CONTROLLED_FEATURES.add(pkg.getNamespace_OwnedRule());
		add(PROFILE, pkg.getPackage_OwnedType().getName(), pkg.getStereotype());
		add(PROFILE, pkg.getPackage_OwnedType().getName(), pkg.getEnumeration());
		add(PACKAGES, pkg.getPackage_PackagedElement().getName(), pkg.getInstanceSpecification());
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getSignal());
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getUseCase(), StereotypeNames.BUSINESS_USE_CASE);
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getActor(), StereotypeNames.BUSINESS_ACTOR);
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getClass_(), StereotypeNames.BUSINESS_ROLE);
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getClass_(), StereotypeNames.BUSINESS_DOCUMENT);
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getClass_(), StereotypeNames.BUSINESS_COMPONENT);
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getClass_());
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getEnumeration());
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getDataType(), StereotypeNames.STRUCTURED_DATA_TYPE);
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getPrimitiveType());
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getDataType(), StereotypeNames.VALUE_TYPE);
		add(INVARIANT_OWNERS, pkg.getNamespace_OwnedRule().getName(), pkg.getConstraint());
		add(ATTRIBUTE_OWNERS, pkg.getStructuredClassifier_OwnedAttribute().getName(), pkg.getProperty());
		add(CLASSIFIER_BEHAVIOR_OWNERS, pkg.getBehavioredClassifier_ClassifierBehavior().getName(), pkg.getStateMachine(), StereotypeNames.BUSINESS_STATE_MACHINE);
		add(CLASSIFIER_BEHAVIOR_OWNERS, pkg.getBehavioredClassifier_ClassifierBehavior().getName(), pkg.getActivity(), StereotypeNames.BUSINES_PROCESS);
		add(METHOD_OWNERS, pkg.getBehavioredClassifier_OwnedBehavior().getName(), pkg.getOpaqueBehavior());
		add(METHOD_OWNERS, pkg.getBehavioredClassifier_OwnedBehavior().getName(), pkg.getActivity(), StereotypeNames.METHOD);
		add(TASK_OWNERS, pkg.getBehavioredClassifier_OwnedBehavior().getName(), pkg.getOpaqueBehavior(),
				StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK);
		add(TASK_OWNERS, pkg.getBehavioredClassifier_OwnedBehavior().getName(), pkg.getStateMachine(),
				StereotypeNames.STANDALONE_SCREENFLOW_TASK);
		add(TASK_OWNERS, pkg.getClass_OwnedOperation().getName(), pkg.getOperation(), StereotypeNames.RESPONSIBILITY);
		add(OPERATION_OWNERS, pkg.getClass_OwnedOperation().getName(), pkg.getOperation());
		add(PROCESS_OWNERS, pkg.getBehavioredClassifier_OwnedBehavior().getName(), pkg.getStateMachine(),
				StereotypeNames.BUSINESS_STATE_MACHINE);
		add(PROCESS_OWNERS, pkg.getBehavioredClassifier_OwnedBehavior().getName(), pkg.getActivity(), StereotypeNames.BUSINES_PROCESS);
		add(NOTIFICATION_OWNERS, pkg.getClass_NestedClassifier().getName(), pkg.getSignal(), StereotypeNames.NOTIFICATION);
		add(SEND_NOTIFICATION_ACTION, FROM_EXPRESSION, pkg.getOpaqueExpression(), StereotypeNames.RECIPIENT_EXPRESSION);
		add(SEND_NOTIFICATION_ACTION, CC_EXPRESSION, pkg.getOpaqueExpression(), StereotypeNames.RECIPIENT_EXPRESSION);
		add(SEND_NOTIFICATION_ACTION, BCC_EXPRESSION, pkg.getOpaqueExpression(), StereotypeNames.RECIPIENT_EXPRESSION);
		add(SEND_NOTIFICATION_ACTION, TEMPLATE_EXPRESSION, pkg.getOpaqueExpression(), StereotypeNames.RECIPIENT_EXPRESSION);
		add(NOTIFICATION, TEMPLATE_EXPRESSION, pkg.getOpaqueExpression());
		add(ESCALATION, REASSIGNMENT, pkg.getOpaqueExpression(), StereotypeNames.PARTICIPANT_EXPRESSION);
		add(DEADLINE_OWNERS, StereotypeNames.DEADLINE, pkg.getTimeEvent());
		add(ESCALATION, NOTIFICATIONS, pkg.getSendSignalAction(), StereotypeNames.SEND_NOTIFICATION_ACTION);
		add(DEADLINE_OWNERS, STAKEHOLDERS, pkg.getOpaqueExpression(), StereotypeNames.PARTICIPANT_EXPRESSION);
		add(TASKS, POTENTIAL_OWNERS, pkg.getOpaqueExpression(), StereotypeNames.PARTICIPANT_EXPRESSION);
		add(TASKS, BUSINESS_ADMINISTRATORS, pkg.getOpaqueExpression(), StereotypeNames.PARTICIPANT_EXPRESSION);
		add(OBSERVED, TIME_OBSERVATIONS, pkg.getTimeObservation());
		add(OBSERVED, DURATION_OBSERVATIONS, pkg.getDurationObservation(), StereotypeNames.BUSINESS_DURATION_OBSERVATION);
		add(INTERVAL_EVALUATED, EVALUATION_INTERVAL, pkg.getOpaqueExpression(), StereotypeNames.DURATION_EXPRESSION);
		add(BUSINESS_CALENDAR_USERS, BUSINESS_CALENDAR_TO_USE, pkg.getOpaqueExpression(), StereotypeNames.BUSINESS_CALENDAR_EXPRESSION);
	}
	private static void add(MatchingOwner[] mo,String f,EClass type,String stereotype){
		add(new DefaultCreateChildAction(mo, f, type, stereotype));
	}
	private static void add(MatchingOwner mo,String f,EClass type,String st){
		add(new DefaultCreateChildAction(mo, f, type, st));
	}
	private static void add(MatchingOwner mo,String f,EClass type){
		add(new DefaultCreateChildAction(mo, f, type));
	}
	private static void add(MatchingOwner[] mo,String f,EClass type){
		add(new DefaultCreateChildAction(mo, f, type));
	}
	private static void add(DefaultCreateChildAction defaultCreateChildAction){
		ACTIONS.add(defaultCreateChildAction);
		Set<DefaultCreateChildAction> s = FEATURES.get(defaultCreateChildAction.featureName);
		if(s == null){
			s = new HashSet<DefaultCreateChildAction>();
			FEATURES.put(defaultCreateChildAction.featureName, s);
		}
		s.add(defaultCreateChildAction);
	}
}
