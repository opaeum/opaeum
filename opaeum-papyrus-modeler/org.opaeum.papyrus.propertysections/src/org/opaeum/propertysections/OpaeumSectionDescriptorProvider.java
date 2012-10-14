package org.opaeum.propertysections;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptorProvider;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
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
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.uml.filters.bpm.AcceptDeadlineActionFilter;
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
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.eclipse.uml.filters.core.AssociationEndFilter;
import org.opaeum.eclipse.uml.filters.core.ConstraintFilter;
import org.opaeum.eclipse.uml.filters.core.EscalationFilter;
import org.opaeum.eclipse.uml.filters.core.InstanceSpecificationNoEnumerationLiteralFilter;
import org.opaeum.eclipse.uml.filters.core.OclBehaviorFilter;
import org.opaeum.eclipse.uml.filters.core.PackageNoProfileFilter;
import org.opaeum.eclipse.uml.filters.core.PersistentClassNotInProfileFilter;
import org.opaeum.eclipse.uml.filters.core.PersistentNameFilter;
import org.opaeum.eclipse.uml.filters.core.PropertyNotInProfileFilter;
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
import org.opaeum.eclipse.uml.propertysections.constraints.BehaviorPostconditionsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.BehaviorPreconditionsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.ClassInvariantsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.OperationPostconditionsSection;
import org.opaeum.eclipse.uml.propertysections.constraints.OperationPreconditionsSection;
import org.opaeum.eclipse.uml.propertysections.core.AssociationEndNavigabilityAndCompositionSection;
import org.opaeum.eclipse.uml.propertysections.core.BehaviorParametersSection;
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
import org.opaeum.eclipse.uml.propertysections.property.FirstEndBooleanPropertiesSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndDefaultValueSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndMultiplicityElemementFeaturesSection;
import org.opaeum.eclipse.uml.propertysections.property.FirstEndNameSection;
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
import org.opaeum.eclipse.uml.propertysections.property.SecondEndRedefinedPropertySection;
import org.opaeum.eclipse.uml.propertysections.property.SecondEndSubsettedPropertiesSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.AuditingEnabledSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.ClassifierNamePropertySection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.FirstEndRoleInCubeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.GenerateAbstractSuperclassSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.MappedImplementationTypeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.ModelArtifactIdentifierSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PackageIsSchemaSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PackageMappedImplementationPackageSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PersistentNameSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.PropertyRoleInCubeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.SecondEndRoleInCubeSection;
import org.opaeum.eclipse.uml.propertysections.standardprofile.UserInterfaceTextSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.RegionExtendedRegionSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.StateRedefinedStateSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.TransitionGuardSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.TransitionRedefinedTransitionSection;
import org.opaeum.eclipse.uml.propertysections.statemachine.TransitionTriggerSection;

public class OpaeumSectionDescriptorProvider extends TabbedPropertyRegistry implements ISectionDescriptorProvider{
	OpaeumTypeMapper typeMapper = new OpaeumTypeMapper();
	private static final class FilterBasedDescriptor extends AbstractSectionDescriptor{
		private final ISection s;
		private final IFilter f;
		private String tabId;
		private String afterSection;
		private FilterBasedDescriptor(ISection s,IFilter f){
			this.s = s;
			this.f = f;
		}
		@Override
		public IFilter getFilter(){
			return f;
		}
		@Override
		public String getTargetTab(){
			return getTabId();
		}
		@Override
		public ISection getSectionClass(){
			return s;
		}
		@Override
		public String getId(){
			return s.getClass().getName();
		}
		public String getTabId(){
			return tabId;
		}
		public void setTabId(String tabId){
			this.tabId = tabId;
		}
		public String getAfterSection(){
			if(afterSection == null){
				return super.getAfterSection();
			}
			return afterSection;
		}
		public void setAfterSection(String afterSection){
			this.afterSection = afterSection;
		}
	}
	private ArrayList<ISectionDescriptor> result;
	ISection previousSection;
	private String contributorId = "TreeOutlinePage";
	public OpaeumSectionDescriptorProvider(){
		super("dummy");
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
		addBasic(MultiplicityElement.class, new MultiplicityElementFeaturesSection());
		addBasic(new OclBehaviorFilter(), new OpaqueBehaviorBodySection());
		addBasic(OpaqueExpression.class, new OpaqueExpressionBodySection());
		addBasic(Operation.class, new OperationBooleanFeaturesSection());
		addBasic(Operation.class, new OperationBodyConditionSection());
		addParamters(Operation.class, new OperationParametersSection());
		addBasic(PackageImport.class, new PackageImportImportedPackageSection());
		addBasic(ProfileApplication.class, new ProfileApplicationAppliedProfileSection());
		addBasic(Reception.class, new ReceptionSignalSection());
		addBasic(Slot.class, new SlotFeatureSection());
		addBasic(TypedElement.class, new TypedElementTypeSection());
		addBasic(new AssociationEndFilter(), new AssociationEndNavigabilityAndCompositionSection());
		// Constraints
		addPreconditions(Operation.class, new OperationPreconditionsSection());
		addPostconditions(Operation.class, new OperationPostconditionsSection());
		addParamters(Behavior.class, new BehaviorParametersSection());
		addPreconditions(Behavior.class, new BehaviorPreconditionsSection());
		addPostconditions(Behavior.class, new BehaviorPostconditionsSection());
		add(Classifier.class, new ClassInvariantsSection()).setTabId("org.opaeum.eclipse.invariantsTab");
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
		addBasic(new SendNotificationActionFilter(), new SendNotificationActionFromSection());
		addBasic(new SendNotificationActionFilter(), new SendNotificationActionBccSection());
		addBasic(new SendNotificationActionFilter(), new SendNotificationActionCcSection());
		addBasic(new StakeholderContainerFilter(), new StakeholdersSection());
		addBasic(new TaskFilter(), new TaskBusinessAdministratorsSection());
		addBasic(new TaskFilter(), new TaskPotentialOwnersSection());
		// addBasic(new ??(), new TimeEventTimeUnitSection());
		addBasic(new DelegationFilter(), new ConnectorSelectionSection());
		addInterfaces(Port.class, new PortProvidedInterfaces());
		addInterfaces(Port.class, new PortRequiredInterfaces());
		// Event
		addBasic(TimeEvent.class, new TimeEventIsRelativeSection());
		// addBasic(TimeEvent.class, new TimeEventWhenSection());
		// Property
		addFirstEnd(Association.class, new FirstEndBooleanPropertiesSection());
		addFirstEnd(Association.class, new FirstEndDefaultValueSection());
		addFirstEnd(Association.class, new FirstEndMultiplicityElemementFeaturesSection());
		addFirstEnd(Association.class, new FirstEndNameSection());
		addRedefinitions(Association.class, new FirstEndRedefinedPropertySection());
		addRedefinitions(Association.class, new FirstEndSubsettedPropertiesSection());
		addBasic(Property.class, new PropertyBooleanFeaturesSection());
		addBasic(Property.class, new PropertyDefaultValueSection());
		addRedefinitions(Property.class, new PropertyRedefinedPropertySection());
		addRedefinitions(Property.class, new PropertySubsettedPropertySection());
		add(Property.class, new PropertyQualifiersSection()).setTabId("org.opaeum.eclipse.qualifiersTab");
		addSecondEnd(Association.class, new SecondEndBooleanPropertiesSection());
		addSecondEnd(Association.class, new SecondEndDefaultValueSection());
		addSecondEnd(Association.class, new SecondEndMultiplicityElemementFeaturesSection());
		addSecondEnd(Association.class, new SecondEndNameSection());
		addRedefinitions(Association.class, new SecondEndRedefinedPropertySection());
		addRedefinitions(Association.class, new SecondEndSubsettedPropertiesSection());
		// Standard profile
		addExtended(new PersistentClassNotInProfileFilter(), new AuditingEnabledSection());
		addExtended(new PersistentClassNotInProfileFilter(), new ClassifierNamePropertySection());
		addFirstEnd(Association.class, new FirstEndRoleInCubeSection());
		addExtended(new PersistentClassNotInProfileFilter(), new GenerateAbstractSuperclassSection());
		addExtended(new PersistentClassNotInProfileFilter(), new MappedImplementationTypeSection());
		addExtended(Model.class, new ModelArtifactIdentifierSection());
		addExtended(new PackageNoProfileFilter(), new PackageIsSchemaSection());
		addExtended(new PackageNoProfileFilter(), new PackageMappedImplementationPackageSection());
		addExtended(new PersistentNameFilter(), new PersistentNameSection());
		addExtended(new PropertyNotInProfileFilter(), new PropertyRoleInCubeSection());
		addSecondEnd(Association.class, new SecondEndRoleInCubeSection());
		add(NamedElement.class,new UserInterfaceTextSection()).setTabId("org.opaeum.eclipse.i8nTab");
		//StateMachine
		addBasic(Transition.class,new TransitionGuardSection());
		addBasic(Transition.class, new TransitionTriggerSection());
		addBasic(Transition.class, new TransitionRedefinedTransitionSection());
		addBasic(State.class, new StateRedefinedStateSection());
		addBasic(Region.class, new RegionExtendedRegionSection());

		
		result.addAll(Arrays.asList(readSectionDescriptors()));
		return result.toArray(new ISectionDescriptor[result.size()]);
	}
	private void addExtended(Class<? extends EObject> class1,ISection section){
		add(class1, section).setTabId("org.opaeum.eclipse.extendedTab");
	}
	private void addFirstEnd(Class<Association> class1,ISection s){
		add(class1,s).setTabId("org.opaeum.eclipse.firstEndTab");
	}
	private void addSecondEnd(Class<Association> class1,ISection s){
		add(class1,s).setTabId("org.opaeum.eclipse.secondEndTab");
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
	private FilterBasedDescriptor add(IFilter filter,ISection section){
		FilterBasedDescriptor result = new FilterBasedDescriptor(section, filter);
		this.result.add(result);
		if(previousSection != null){
			result.setAfterSection(previousSection.getClass().getName());
		}
		previousSection = section;
		return result;
	}
	private void addBasic(Class<? extends EObject> class1,ISection namedElementNameSection){
		add(class1, namedElementNameSection).setTabId("org.opaeum.eclipse.opaeumTab");
	}
	private FilterBasedDescriptor add(final Class<? extends EObject> c,final ISection s){
		return add(new AbstractFilter(){
			@Override
			public boolean select(EObject e){
				return c.isInstance(e);
			}
			@Override
			public boolean select(Element e){
				return c.isInstance(e);
			}
		}, s);
	}
}
