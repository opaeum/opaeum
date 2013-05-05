package org.opaeum.propertysections;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptorProvider;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StructuralFeatureAction;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.VariableAction;
import org.opaeum.eclipse.uml.filters.activity.CallBehaviorActionFilter;
import org.opaeum.eclipse.uml.filters.activity.NewObjectPinFilter;
import org.opaeum.eclipse.uml.filters.activity.OclOpaqueActionFilter;
import org.opaeum.eclipse.uml.filters.activity.OclPinFilter;
import org.opaeum.eclipse.uml.filters.activity.SendSignalActionFilter;
import org.opaeum.eclipse.uml.filters.bpm.AcceptDeadlineActionFilter;
import org.opaeum.eclipse.uml.filters.bpm.AcceptEventActionTriggerSectionFilter;
import org.opaeum.eclipse.uml.filters.bpm.AcceptTaskEventActionTaskEventFilter;
import org.opaeum.eclipse.uml.filters.bpm.BusinessDocumentFilter;
import org.opaeum.eclipse.uml.filters.bpm.BusinessDurationObservationFilter;
import org.opaeum.eclipse.uml.filters.bpm.BusinessTimeEventFilter;
import org.opaeum.eclipse.uml.filters.bpm.DeadlineContainerFilter;
import org.opaeum.eclipse.uml.filters.bpm.DeadlineFilter;
import org.opaeum.eclipse.uml.filters.bpm.DurationExpressionFilter;
import org.opaeum.eclipse.uml.filters.bpm.NotificationFilter;
import org.opaeum.eclipse.uml.filters.bpm.SendNotificationActionFilter;
import org.opaeum.eclipse.uml.filters.bpm.StakeholderContainerFilter;
import org.opaeum.eclipse.uml.filters.bpm.TaskFilter;
import org.opaeum.eclipse.uml.filters.compositestructures.DelegationFilter;
import org.opaeum.eclipse.uml.filters.compositestructures.InterfacesFilter;
import org.opaeum.eclipse.uml.filters.compositestructures.PrimaryRoleFilter;
import org.opaeum.eclipse.uml.filters.core.AssociationEndFilter;
import org.opaeum.eclipse.uml.filters.core.BehavioredClassifierNotBehaviorFilter;
import org.opaeum.eclipse.uml.filters.core.ClassifierNotAssociationFilter;
import org.opaeum.eclipse.uml.filters.core.ConstraintFilter;
import org.opaeum.eclipse.uml.filters.core.EscalationFilter;
import org.opaeum.eclipse.uml.filters.core.InstanceSpecificationNoEnumerationLiteralFilter;
import org.opaeum.eclipse.uml.filters.core.OclBehaviorFilter;
import org.opaeum.eclipse.uml.filters.core.OperationNotResponsibilityFilter;
import org.opaeum.eclipse.uml.filters.core.PackageNoProfileFilter;
import org.opaeum.eclipse.uml.filters.core.PersistentClassNotInProfileFilter;
import org.opaeum.eclipse.uml.filters.core.PersistentNameFilter;
import org.opaeum.eclipse.uml.filters.core.PropertyNotInProfileFilter;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.AcceptEventActionTriggerSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ActionLocalPostconditionsSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ActionLocalPreconditionsSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ActionPinsSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ActivityEdgeGuardSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ActivityEdgeWeightSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ActivityParameterNodeParameterSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.BehaviorSpecificationSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.CallActionIsSynchronousSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.CallBehaviorActionBehaviorSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.CallOperationActionOperationSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.DurationObservationFromEventSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.DurationObservationToEventSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ExceptionHandlerExceptionTypesSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.NewObjectPinValueSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ObjectFlowSelectionSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.ObjectFlowTransformationSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.OclPinValueSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.OpaqueActionBodySection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.SendSignalActionSignalSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.StructuralFeatureActionFeatureSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.TimeObservationEventSection;
import org.opaeum.eclipse.uml.propertysections.activitydiagram.VariableActionVariableSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.AcceptDeadlineDeadlineSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.AcceptTaskEventActionTaskEventSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.BusinessDocumentDocumentTypeSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.BusinessDurationObservationBusinessCalendarToUseSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.BusinessDurationObservationIsCumulativeSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.BusinessDurationObservationTimeUnitSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.BusinessTimeEventBusinessCalendarToUseSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.DeadlineDeadlineKindSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.DeadlineTimeUnitSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.DeadlinesSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.DurationExpressionBusinessCalendarToUseSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.DurationExpressionTimeUnitSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.EscalationDeadlineSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.EscalationReassignmentSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.NotificationTemplateSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.SendNotificationActionBccSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.SendNotificationActionCcSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.SendNotificationActionFromSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.SendNotificationActionNotificationSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.StakeholdersSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.TaskBusinessAdministratorsSection;
import org.opaeum.eclipse.uml.propertysections.bpmprofile.TaskPotentialOwnersSection;
import org.opaeum.eclipse.uml.propertysections.compositestructures.ConnectorSelectionSection;
import org.opaeum.eclipse.uml.propertysections.compositestructures.PortProvidedInterfaces;
import org.opaeum.eclipse.uml.propertysections.compositestructures.PortRequiredInterfaces;
import org.opaeum.eclipse.uml.propertysections.compositestructures.PropertyPrimaryCompositionRole;
import org.opaeum.eclipse.uml.propertysections.constraints.BehaviorPostconditionsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.BehaviorPreconditionsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.ClassInvariantsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.OperationPostconditionsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.OperationPreconditionsSection;
import org.opaeum.eclipse.uml.propertysections.core.BehaviorParametersSection;
import org.opaeum.eclipse.uml.propertysections.core.BehavioredClassifierClassifierBehaviorSection;
import org.opaeum.eclipse.uml.propertysections.core.ClassifierIsAbstractSection;
import org.opaeum.eclipse.uml.propertysections.core.ConstraintValueSpecificationSection;
import org.opaeum.eclipse.uml.propertysections.core.EObjectErrorSection;
import org.opaeum.eclipse.uml.propertysections.core.ElementImportAliasSection;
import org.opaeum.eclipse.uml.propertysections.core.ElementImportImportedElementSection;
import org.opaeum.eclipse.uml.propertysections.core.GeneralizationGeneralSection;
import org.opaeum.eclipse.uml.propertysections.core.InstanceSpecificationClassifierSection;
import org.opaeum.eclipse.uml.propertysections.core.InstanceSpecificationSlotsSection;
import org.opaeum.eclipse.uml.propertysections.core.InterfaceRealizationContractSection;
import org.opaeum.eclipse.uml.propertysections.core.MultiplicityElementFeaturesSection;
import org.opaeum.eclipse.uml.propertysections.core.NamedElementNameSection;
import org.opaeum.eclipse.uml.propertysections.core.OpaqueBehaviorBodySection;
import org.opaeum.eclipse.uml.propertysections.core.OpaqueExpressionBodySection;
import org.opaeum.eclipse.uml.propertysections.core.OperationBodyConditionSection;
import org.opaeum.eclipse.uml.propertysections.core.OperationBooleanFeaturesSection;
import org.opaeum.eclipse.uml.propertysections.core.OperationParametersSection;
import org.opaeum.eclipse.uml.propertysections.core.PackageImportImportedPackageSection;
import org.opaeum.eclipse.uml.propertysections.core.ProfileApplicationAppliedProfileSection;
import org.opaeum.eclipse.uml.propertysections.core.ReceptionSignalSection;
import org.opaeum.eclipse.uml.propertysections.core.SlotFeatureSection;
import org.opaeum.eclipse.uml.propertysections.core.TypedElementTypeSection;
import org.opaeum.eclipse.uml.propertysections.event.TimeEventIsRelativeSection;
import org.opaeum.eclipse.uml.propertysections.event.TimeEventWhenSection;
import org.opaeum.eclipse.uml.propertysections.property.AssociationEndNavigabilityAndCompositionSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndBooleanPropertiesSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndDefaultValueSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndMultiplicityElemementFeaturesSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndNameSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndNavigabilityAndCompositionSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndQualifierSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndRedefinedPropertySection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndSubsettedPropertiesSection;
import org.opaeum.eclipse.uml.propertysections.property.PropertyBooleanFeaturesSection;
import org.opaeum.eclipse.uml.propertysections.property.PropertyDefaultValueSection;
import org.opaeum.eclipse.uml.propertysections.property.PropertyQualifiersSection;
import org.opaeum.eclipse.uml.propertysections.property.PropertyRedefinedPropertySection;
import org.opaeum.eclipse.uml.propertysections.property.PropertySubsettedPropertySection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndBooleanPropertiesSection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndDefaultValueSection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndMultiplicityElemementFeaturesSection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndNameSection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndNavigabilityAndCompositionSection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndQualifierSection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndRedefinedPropertySection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndSubsettedPropertiesSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.AuditingEnabledSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.ClassifierNamePropertySection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.FirstEndRoleInCubeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.GenerateAbstractSuperclassSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.MappedImplementationTypeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.ModelArtifactIdentifierSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.ModelModelTypeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PackageIsSchemaSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PackageMappedImplementationPackageSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PersistentNameSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PropertyRoleInCubeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.SecondEndRoleInCubeSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.RegionExtendedRegionSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.StateRedefinedStateSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.TransitionGuardSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.TransitionRedefinedTransitionSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.TransitionTriggerSection;

public class OpaeumSectionDescriptorProvider extends AbstractSectionDescriptorProvider implements ISectionDescriptorProvider{
	OpaeumTypeMapper typeMapper = new OpaeumTypeMapper();
	@SuppressWarnings("restriction")
	public OpaeumSectionDescriptorProvider(){
		super("dummytoavoidrecursion");
		super.contributorId = "TreeOutlinePage";
	}
	@Override
	public ISectionDescriptor[] getSectionDescriptors(){
		this.result = new ArrayList<ISectionDescriptor>();
		addBasic(EObject.class, new EObjectErrorSection());
		addBasic(NamedElement.class, new NamedElementNameSection());
		addBasic(Classifier.class, new ClassifierIsAbstractSection());
		addBasic(TimeEvent.class, new TimeEventWhenSection());
		addBasic(new ConstraintFilter(), new ConstraintValueSpecificationSection());
		addBasic(ElementImport.class, new ElementImportAliasSection());
		addBasic(ElementImport.class, new ElementImportImportedElementSection());
		addBasic(Generalization.class, new GeneralizationGeneralSection());
		addBasic(new InstanceSpecificationNoEnumerationLiteralFilter(), new InstanceSpecificationClassifierSection());
		addBasic(InstanceSpecification.class, new InstanceSpecificationSlotsSection());
		addBasic(InterfaceRealization.class, new InterfaceRealizationContractSection());
		addBasic(TypedElement.class, new TypedElementTypeSection());
		addBasic(MultiplicityElement.class, new MultiplicityElementFeaturesSection());
		addBasic(new OclBehaviorFilter(), new OpaqueBehaviorBodySection());
		addBasic(OpaqueExpression.class, new OpaqueExpressionBodySection());
		addBasic(Operation.class, new OperationBooleanFeaturesSection());
		addBasic(new OperationNotResponsibilityFilter(), new OperationBodyConditionSection());
		addParamters(Operation.class, new OperationParametersSection());
		addBasic(PackageImport.class, new PackageImportImportedPackageSection());
		addBasic(ProfileApplication.class, new ProfileApplicationAppliedProfileSection());
		addBasic(Reception.class, new ReceptionSignalSection());
		addBasic(Slot.class, new SlotFeatureSection());
		addBasic(new AssociationEndFilter(), new AssociationEndNavigabilityAndCompositionSection());
		addBasic(new BehavioredClassifierNotBehaviorFilter(), new BehavioredClassifierClassifierBehaviorSection());
		// Constraints
		addPreconditions(Operation.class, new OperationPreconditionsSection());
		addPostconditions(Operation.class, new OperationPostconditionsSection());
		addParamters(Behavior.class, new BehaviorParametersSection());
		addPreconditions(Behavior.class, new BehaviorPreconditionsSection());
		addPostconditions(Behavior.class, new BehaviorPostconditionsSection());
		add(new ClassifierNotAssociationFilter(), new ClassInvariantsSection()).setTabId("org.opaeum.eclipse.invariantsTab");
		// BPM
		addDeadlines(new AcceptDeadlineActionFilter(), new AcceptDeadlineDeadlineSection());
		addBasic(new AcceptTaskEventActionTaskEventFilter(), new AcceptTaskEventActionTaskEventSection());
		addExtended(new BusinessDocumentFilter(), new BusinessDocumentDocumentTypeSection());
		addBasic(new BusinessDurationObservationFilter(), new BusinessDurationObservationBusinessCalendarToUseSection());
		addBasic(new BusinessDurationObservationFilter(), new BusinessDurationObservationIsCumulativeSection());
		addBasic(new BusinessDurationObservationFilter(), new BusinessDurationObservationTimeUnitSection());
		addBasic(new BusinessTimeEventFilter(), new BusinessTimeEventBusinessCalendarToUseSection());
		addBasic(new DeadlineFilter(), new DeadlineDeadlineKindSection());
		addDeadlines(new DeadlineContainerFilter(), new DeadlinesSection());
		addBasic(new DeadlineFilter(), new DeadlineTimeUnitSection());
		addBasic(new DurationExpressionFilter(), new DurationExpressionBusinessCalendarToUseSection());
		addBasic(new DurationExpressionFilter(), new DurationExpressionTimeUnitSection());
		addBasic(new EscalationFilter(), new EscalationDeadlineSection());
		addBasic(new EscalationFilter(), new EscalationReassignmentSection());
		// addBasic(new IntervalExpressionBusinessCalendarToUseSection(), new IntervalExpressionBusinessCalendarToUseSection());
		add(new NotificationFilter(), new NotificationTemplateSection()).setTabId("org.opaeum.eclipse.templateTab");
		addBasic(new SendNotificationActionFilter(), new SendNotificationActionNotificationSection());
		addExtended(new SendNotificationActionFilter(), new SendNotificationActionFromSection());
		addExtended(new SendNotificationActionFilter(), new SendNotificationActionBccSection());
		addExtended(new SendNotificationActionFilter(), new SendNotificationActionCcSection());
		addExtended(new StakeholderContainerFilter(), new StakeholdersSection());
		addExtended(new TaskFilter(), new TaskBusinessAdministratorsSection());
		addExtended(new TaskFilter(), new TaskPotentialOwnersSection());
		// addBasic(new ??(), new TimeEventTimeUnitSection());
		addExtended(new DelegationFilter(), new ConnectorSelectionSection());
		// Event
		addBasic(TimeEvent.class, new TimeEventIsRelativeSection());
		// addBasic(TimeEvent.class, new TimeEventWhenSection());
		// Property
		addFirstEnd(Association.class, new FirstEndNameSection());
		addFirstEnd(Association.class, new FirstEndMultiplicityElemementFeaturesSection());
		addFirstEnd(Association.class, new FirstEndNavigabilityAndCompositionSection());
		addFirstEnd(Association.class, new FirstEndBooleanPropertiesSection());
		addFirstEnd(Association.class, new FirstEndDefaultValueSection());
		add(Association.class, new FirstEndQualifierSection()).setTabId("org.opaeum.eclipse.firstEndQualifiersTab");
		add(Association.class, new FirstEndRedefinedPropertySection()).setTabId("org.opaeum.eclipse.firstEndRedefinitionsTab");
		add(Association.class, new FirstEndSubsettedPropertiesSection()).setTabId("org.opaeum.eclipse.firstEndRedefinitionsTab");
		addBasic(Property.class, new PropertyBooleanFeaturesSection());
		addBasic(Property.class, new PropertyDefaultValueSection());
		add(Association.class, new SecondEndQualifierSection()).setTabId("org.opaeum.eclipse.secondEndQualifiersTab");
		addRedefinitions(Property.class, new PropertyRedefinedPropertySection());
		addRedefinitions(Property.class, new PropertySubsettedPropertySection());
		add(Property.class, new PropertyQualifiersSection()).setTabId("org.opaeum.eclipse.qualifiersTab");
		addSecondEnd(Association.class, new SecondEndNameSection());
		addSecondEnd(Association.class, new SecondEndMultiplicityElemementFeaturesSection());
		addSecondEnd(Association.class, new SecondEndNavigabilityAndCompositionSection());
		addSecondEnd(Association.class, new SecondEndBooleanPropertiesSection());
		addSecondEnd(Association.class, new SecondEndDefaultValueSection());
		add(Association.class, new SecondEndRedefinedPropertySection()).setTabId("org.opaeum.eclipse.secondEndRedefinitionsTab");
		add(Association.class, new SecondEndSubsettedPropertiesSection()).setTabId("org.opaeum.eclipse.secondEndRedefinitionsTab");
		// Standard profile
		addExtended(new PersistentClassNotInProfileFilter(), new AuditingEnabledSection());
		addExtended(new PersistentClassNotInProfileFilter(), new ClassifierNamePropertySection());
		addFirstEnd(Association.class, new FirstEndRoleInCubeSection());
		addExtended(new PersistentClassNotInProfileFilter(), new GenerateAbstractSuperclassSection());
		addExtended(new PersistentClassNotInProfileFilter(), new MappedImplementationTypeSection());
		addExtended(Interface.class, new MappedImplementationTypeSection());
		addExtended(Interface.class, new GenerateAbstractSuperclassSection());
		addExtended(Model.class, new ModelArtifactIdentifierSection());
		addExtended(new PackageNoProfileFilter(), new PackageIsSchemaSection());
		addExtended(new PackageNoProfileFilter(), new PackageMappedImplementationPackageSection());
		addExtended(new PersistentNameFilter(), new PersistentNameSection());
		addExtended(new PropertyNotInProfileFilter(), new PropertyRoleInCubeSection());
		addExtended(Model.class, new ModelModelTypeSection());
		addSecondEnd(Association.class, new SecondEndRoleInCubeSection());
		// Composite Structures
		addBasic(new DelegationFilter(), new ConnectorSelectionSection());
		addBasic(new InterfacesFilter(), new PortRequiredInterfaces());
		addBasic(new InterfacesFilter(), new PortProvidedInterfaces());
		addBasic(new PrimaryRoleFilter(), new PropertyPrimaryCompositionRole());
		// StateMachine
		addBasic(Transition.class, new TransitionGuardSection());
		addBasic(Transition.class, new TransitionTriggerSection());
		addBasic(Transition.class, new TransitionRedefinedTransitionSection());
		addBasic(State.class, new StateRedefinedStateSection());
		addBasic(Region.class, new RegionExtendedRegionSection());
		// Activity
		addBasic(TimeObservation.class, new TimeObservationEventSection());
		addBasic(DurationObservation.class, new DurationObservationFromEventSection());
		addBasic(DurationObservation.class, new DurationObservationToEventSection());
		addBasic(ExceptionHandler.class, new ExceptionHandlerExceptionTypesSection());
		addBasic(new OclOpaqueActionFilter(), new OpaqueActionBodySection());
		addBasic(CallOperationAction.class, new CallOperationActionOperationSection());
		addBasic(Behavior.class, new BehaviorSpecificationSection());
		addBasic(new AcceptEventActionTriggerSectionFilter(), new AcceptEventActionTriggerSection());
		addBasic(CallAction.class, new CallActionIsSynchronousSection());
		addBasic(StructuralFeatureAction.class, new StructuralFeatureActionFeatureSection());
		addBasic(VariableAction.class, new VariableActionVariableSection());
		addBasic(new OclPinFilter(), new OclPinValueSection());
		addBasic(new NewObjectPinFilter(), new NewObjectPinValueSection());
		addBasic(ActivityEdge.class, new ActivityEdgeGuardSection());
		addBasic(ObjectFlow.class, new ObjectFlowSelectionSection());
		addBasic(ObjectFlow.class, new ObjectFlowTransformationSection());
		addBasic(ActivityEdge.class, new ActivityEdgeWeightSection());
		addBasic(new SendSignalActionFilter(), new SendSignalActionSignalSection());
		addBasic(new SendNotificationActionFilter(), new SendNotificationActionNotificationSection());
		addBasic(new CallBehaviorActionFilter(), new CallBehaviorActionBehaviorSection());
		addBasic(ActivityParameterNode.class, new ActivityParameterNodeParameterSection());
		addParamters(Action.class, new ActionPinsSection());
		addPreconditions(Action.class, new ActionLocalPreconditionsSection());
		addPostconditions(Action.class, new ActionLocalPostconditionsSection());
		
		
		result.addAll(Arrays.asList(readSectionDescriptors()));
		return result.toArray(new ISectionDescriptor[result.size()]);
	}
	private void addExtended(Class<? extends EObject> class1,ISection section){
		add(class1, section).setTabId("org.opaeum.eclipse.extendedTab");
	}
	private void addFirstEnd(Class<Association> class1,ISection s){
		add(class1, s).setTabId("org.opaeum.eclipse.firstEndTab");
	}
	private void addSecondEnd(Class<Association> class1,ISection s){
		add(class1, s).setTabId("org.opaeum.eclipse.secondEndTab");
	}
	private void addRedefinitions(Class<? extends EObject> class1,ISection firstEndRedefinedPropertySection){
		add(class1, firstEndRedefinedPropertySection).setTabId("org.opaeum.eclipse.redefinitionsTab");
	}
	private void addBasic(IFilter filter,ISection section){
		add(filter, section).setTabId("org.opaeum.eclipse.opaeumTab");
	}
	private void addInterfaces(Class<Port> class1,ISection section){
		add(class1, section).setTabId("org.opaeum.eclipse.interfacesTab");
	}
	private void addExtended(IFilter filter,ISection section){
		add(filter, section).setTabId("org.opaeum.eclipse.extendedTab");
	}
	private void addParamters(Class<? extends EObject> c,ISection section){
		add(c, section).setTabId("org.opaeum.eclipse.parametersTab");
	}
	private void addPreconditions(Class<? extends EObject> c,ISection section){
		add(c, section).setTabId("org.opaeum.eclipse.preconditionsTab");
	}
	private void addPostconditions(Class<? extends EObject> c,ISection section){
		add(c, section).setTabId("org.opaeum.eclipse.postconditionsTab");
	}
	private void addDeadlines(IFilter deadlineContainerFilter,ISection section){
		add(deadlineContainerFilter, section).setTabId("org.opaeum.eclipse.deadlinesTab");
	}
	private void addBasic(Class<? extends EObject> class1,ISection namedElementNameSection){
		add(class1, namedElementNameSection).setTabId("org.opaeum.eclipse.opaeumTab");
	}
}
