package org.opaeum.eclipse.newchild;

import static org.opaeum.metamodel.core.internal.TagNames.BCC_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.BUSINESS_ADMINISTRATORS;
import static org.opaeum.metamodel.core.internal.TagNames.BUSINESS_CALENDAR_TO_USE;
import static org.opaeum.metamodel.core.internal.TagNames.CC_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.CONTROLLED_BY;
import static org.opaeum.metamodel.core.internal.TagNames.DURATION_BASED_COST_OBSERVATIONS;
import static org.opaeum.metamodel.core.internal.TagNames.DURATION_OBSERVATIONS;
import static org.opaeum.metamodel.core.internal.TagNames.EVALUATION_INTERVAL;
import static org.opaeum.metamodel.core.internal.TagNames.FROM_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.NOTIFICATIONS;
import static org.opaeum.metamodel.core.internal.TagNames.POTENTIAL_OWNERS;
import static org.opaeum.metamodel.core.internal.TagNames.QUANTITY_BASED_COST_OBSERVATIONS;
import static org.opaeum.metamodel.core.internal.TagNames.QUANTITY_EXPRESSION;
import static org.opaeum.metamodel.core.internal.TagNames.REASSIGNMENT;
import static org.opaeum.metamodel.core.internal.TagNames.RESOURCES_PAID_FOR;
import static org.opaeum.metamodel.core.internal.TagNames.RESOURCE_PAID_FOR;
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
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.workspace.LibraryType;

public class CreateChildActions implements ICreateChildActionProvider{
	@Override
	public Set<? extends ICreateChildAction> getActions(){
		return ACTIONS;
	}
	@Override
	public Set<EReference> getControlledFeatures(){
		return CONTROLLED_FEATURES;
	}
	private static UMLPackage pkg = UMLPackage.eINSTANCE;
	private static Set<AbstractCreateChildAction> ACTIONS = new HashSet<AbstractCreateChildAction>();
	private static Set<EReference> CONTROLLED_FEATURES = new HashSet<EReference>();
	private static MatchingOwner DURATION_BASED_COST_OBSERVATION = new MatchingOwner(pkg.getDurationObservation(),
			StereotypeNames.DURATION_BASED_COST_OBSERVATION);
	private static MatchingOwner QUANTITY_BASED_COST_OBSERVATION = new MatchingOwner(pkg.getTimeObservation(),
			StereotypeNames.QUANTITY_BASED_COST_OBSERVATION);
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
	public static final Map<String,Set<AbstractCreateChildAction>> FEATURES = new HashMap<String,Set<AbstractCreateChildAction>>();
	private static final MatchingOwner OPERATION = new MatchingOwner(pkg.getOperation());
	private static final MatchingOwner STATEMACHINE = new MatchingOwner(pkg.getStateMachine());
	private static MatchingOwner[] PACKAGES = {PACKAGE,MODEL};
	private static MatchingOwner[] DEADLINE_OWNERS = {RESPONSIBILITY,STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,BUSINESS_PROCESS};
	private static MatchingOwner[] NOTIFICATION_OWNERS = {BUSINESS_STATEMACHINE,STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,
			BUSINESS_PROCESS};
	private static MatchingOwner[] OBSERVED = {new MatchingOwner(pkg.getStateMachine(), "Observed"),BUSINESS_STATEMACHINE,
			STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,BUSINESS_PROCESS,STRUCTURED_BUSINESS_NODE};
	private static MatchingOwner[] TASKS = {STANDALONE_SINGLSCREEN_TASK,STANDALONE_SCREENFLOW_TASL,BUSINESS_PROCESS};
	private static MatchingOwner[] INTERVAL_EVALUATED = {CONTEXTUAL_CHANGE_EVENT};
	private static MatchingOwner[] BUSINESS_CALENDAR_USERS = {DURATION_OBSERVATION,DURATION_EXPRESSION,CONTEXTUAL_BUSINESS_TIME_EVENT,
			DEADLINE};
	private static MatchingOwner[] ATTRIBUTE_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,STATEMACHINE,
			STANDALONE_SINGLSCREEN_TASK,ENTITY,BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] OPERATION_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,
			STANDALONE_SINGLSCREEN_TASK,ENTITY,BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] METHOD_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,ENTITY,
			BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] PROCESS_OWNERS = {BUSINESS_PROCESS,BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,ENTITY,
			BUSINESS_COMPONENT,BUSINESS_DOCUMENT};
	private static MatchingOwner[] TASK_OWNERS = {BUSINESS_ROLE,BUSINESS_ACTOR,BUSINESS_COMPONENT};
	private static MatchingOwner[] INVARIANT_OWNERS = {BUSINESS_ROLE,BUSINESS_ACTOR,BUSINESS_COMPONENT,ENTITY,OPERATION,BUSINESS_PROCESS,
			BUSINESS_STATEMACHINE,STANDALONE_SCREENFLOW_TASL,STANDALONE_SINGLSCREEN_TASK};
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
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getSignal(), StereotypeNames.NOTIFICATION);
		add(PACKAGES, pkg.getPackage_OwnedType().getName(), pkg.getCollaboration(), StereotypeNames.BUSINESS_COLLABORATION);
		add(INVARIANT_OWNERS, pkg.getNamespace_OwnedRule().getName(), pkg.getConstraint());
		add(ATTRIBUTE_OWNERS, pkg.getStructuredClassifier_OwnedAttribute().getName(), pkg.getProperty());
		add(CLASSIFIER_BEHAVIOR_OWNERS, pkg.getBehavioredClassifier_ClassifierBehavior().getName(), pkg.getStateMachine(),
				StereotypeNames.BUSINESS_STATE_MACHINE);
		add(CLASSIFIER_BEHAVIOR_OWNERS, pkg.getBehavioredClassifier_ClassifierBehavior().getName(), pkg.getActivity(),
				StereotypeNames.BUSINES_PROCESS);
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
		add(SEND_NOTIFICATION_ACTION, FROM_EXPRESSION, LibraryType.RECIPIENT);
		add(SEND_NOTIFICATION_ACTION, CC_EXPRESSION, LibraryType.RECIPIENT);
		add(SEND_NOTIFICATION_ACTION, BCC_EXPRESSION, LibraryType.RECIPIENT);
		add(NOTIFICATION, TEMPLATE_EXPRESSION, LibraryType.STRING);
		add(ESCALATION, REASSIGNMENT, LibraryType.PARTICIPANT);
		add(DEADLINE_OWNERS, TagNames.DEADLINES, pkg.getTimeEvent(), StereotypeNames.DEADLINE);
		add(ESCALATION, NOTIFICATIONS, pkg.getSendSignalAction(), StereotypeNames.SEND_NOTIFICATION_ACTION);
		add(DEADLINE_OWNERS, STAKEHOLDERS, LibraryType.PARTICIPANT);
		add(TASKS, POTENTIAL_OWNERS, LibraryType.PARTICIPANT);
		add(TASKS, BUSINESS_ADMINISTRATORS, LibraryType.PARTICIPANT);
		add(OBSERVED, pkg.getNamespace_OwnedRule().getName(), pkg.getConstraint(),StereotypeNames.ESCALATION ).setName("Escalations|Escalation");
		add(OBSERVED, TIME_OBSERVATIONS, pkg.getTimeObservation()).setName("Observations|Time Observation");
		add(OBSERVED, DURATION_OBSERVATIONS, pkg.getDurationObservation(), StereotypeNames.BUSINESS_DURATION_OBSERVATION).setName(
				"Observations|" + StereotypeNames.BUSINESS_DURATION_OBSERVATION);
		add(OBSERVED, DURATION_BASED_COST_OBSERVATIONS, pkg.getDurationObservation(), StereotypeNames.DURATION_BASED_COST_OBSERVATION).setName(
				"Observations|" + StereotypeNames.DURATION_BASED_COST_OBSERVATION);
		;
		add(OBSERVED, QUANTITY_BASED_COST_OBSERVATIONS, pkg.getTimeObservation(), StereotypeNames.QUANTITY_BASED_COST_OBSERVATION).setName(
				"Observations|" + StereotypeNames.QUANTITY_BASED_COST_OBSERVATION);
		;
		add(INTERVAL_EVALUATED, EVALUATION_INTERVAL, LibraryType.DURATION);
		add(BUSINESS_CALENDAR_USERS, BUSINESS_CALENDAR_TO_USE, LibraryType.BUSINESS_CALENDAR);
		add(DURATION_BASED_COST_OBSERVATION, RESOURCES_PAID_FOR, LibraryType.TIMED_RESOURCE);
		add(DURATION_BASED_COST_OBSERVATION, CONTROLLED_BY, LibraryType.BUSINESS_ROLE);
		add(QUANTITY_BASED_COST_OBSERVATION, RESOURCE_PAID_FOR, LibraryType.QUANTIFIED_RESOURCE);
		add(QUANTITY_BASED_COST_OBSERVATION, QUANTITY_EXPRESSION, LibraryType.REAL);
		add(QUANTITY_BASED_COST_OBSERVATION, CONTROLLED_BY, LibraryType.BUSINESS_ROLE);
	}
	private static AbstractCreateChildAction add(MatchingOwner[] mo,String f,EClass type,String stereotype){
		return add(new DefaultCreateChildAction(mo, f, type, stereotype));
	}
	private static AbstractCreateChildAction add(MatchingOwner[] mo,String f,LibraryType libType){
		return add(new CreateTypedExpressionAction(mo, f, libType));
	}
	private static AbstractCreateChildAction add(MatchingOwner mo,String f,LibraryType libType){
		return add(new CreateTypedExpressionAction(mo, f, libType));
	}
	private static AbstractCreateChildAction add(MatchingOwner mo,String f,EClass type,String st){
		return add(new DefaultCreateChildAction(mo, f, type, st));
	}
	private static AbstractCreateChildAction add(MatchingOwner mo,String f,EClass type){
		return add(new DefaultCreateChildAction(mo, f, type));
	}
	private static AbstractCreateChildAction add(MatchingOwner[] mo,String f,EClass type){
		return add(new DefaultCreateChildAction(mo, f, type));
	}
	private static AbstractCreateChildAction add(AbstractCreateChildAction defaultCreateChildAction){
		ACTIONS.add(defaultCreateChildAction);
		Set<AbstractCreateChildAction> s = FEATURES.get(defaultCreateChildAction.featureName);
		if(s == null){
			s = new HashSet<AbstractCreateChildAction>();
			FEATURES.put(defaultCreateChildAction.featureName, s);
		}
		s.add(defaultCreateChildAction);
		return defaultCreateChildAction;
	}
}
