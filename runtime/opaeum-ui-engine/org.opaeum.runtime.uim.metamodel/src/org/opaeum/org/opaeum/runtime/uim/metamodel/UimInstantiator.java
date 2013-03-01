package org.opaeum.org.opaeum.runtime.uim.metamodel;

import org.opaeum.runtime.domain.AbstractEcoreInstantiator;

public class UimInstantiator extends AbstractEcoreInstantiator {
	static public UimInstantiator INSTANCE = new UimInstantiator();

	/** Default constructor for UimInstantiator
	 */
	public UimInstantiator() {
		classes.put("EStringToStringMapEntry",org.opaeum.ecore.EStringToStringMapEntryImpl.class);
		classes.put("EAnnotation",org.opaeum.ecore.EAnnotationImpl.class);
		classes.put("UmlReference",org.opaeum.uim.UmlReferenceImpl.class);
		classes.put("UserInterfaceRoot",org.opaeum.uim.UserInterfaceRootImpl.class);
		classes.put("Page",org.opaeum.uim.PageImpl.class);
		classes.put("Labels",org.opaeum.uim.LabelsImpl.class);
		classes.put("LabeledElement",org.opaeum.uim.LabeledElementImpl.class);
		classes.put("IgnoredElement",org.opaeum.uim.IgnoredElementImpl.class);
		classes.put("PageOrdering",org.opaeum.uim.PageOrderingImpl.class);
		classes.put("LabelContainer",org.opaeum.uim.LabelContainerImpl.class);
		classes.put("ctl:UimNumberScroller",org.opaeum.uim.control.UimNumberScrollerImpl.class);
		classes.put("ctl:UimToggleButton",org.opaeum.uim.control.UimToggleButtonImpl.class);
		classes.put("ctl:UimPopupSearch",org.opaeum.uim.control.UimPopupSearchImpl.class);
		classes.put("ctl:UimDropdown",org.opaeum.uim.control.UimDropdownImpl.class);
		classes.put("ctl:UimCheckBox",org.opaeum.uim.control.UimCheckBoxImpl.class);
		classes.put("ctl:UimLookup",org.opaeum.uim.control.UimLookupImpl.class);
		classes.put("ctl:UimTextArea",org.opaeum.uim.control.UimTextAreaImpl.class);
		classes.put("ctl:UimText",org.opaeum.uim.control.UimTextImpl.class);
		classes.put("ctl:UimDatePopup",org.opaeum.uim.control.UimDatePopupImpl.class);
		classes.put("ctl:UimListBox",org.opaeum.uim.control.UimListBoxImpl.class);
		classes.put("ctl:UimControl",org.opaeum.uim.control.UimControlImpl.class);
		classes.put("ctl:UimTreeView",org.opaeum.uim.control.UimTreeViewImpl.class);
		classes.put("ctl:UimLinkControl",org.opaeum.uim.control.UimLinkControlImpl.class);
		classes.put("ctl:UimDateScroller",org.opaeum.uim.control.UimDateScrollerImpl.class);
		classes.put("ctl:UimSelectionTable",org.opaeum.uim.control.UimSelectionTableImpl.class);
		classes.put("ctl:UimRadioButton",org.opaeum.uim.control.UimRadioButtonImpl.class);
		classes.put("ctl:UimLabel",org.opaeum.uim.control.UimLabelImpl.class);
		classes.put("ctl:UimDateTimePopup",org.opaeum.uim.control.UimDateTimePopupImpl.class);
		classes.put("editor:AbstractEditor",org.opaeum.uim.editor.AbstractEditorImpl.class);
		classes.put("editor:BehaviorExecutionEditor",org.opaeum.uim.editor.BehaviorExecutionEditorImpl.class);
		classes.put("editor:EditorPage",org.opaeum.uim.editor.EditorPageImpl.class);
		classes.put("editor:ActionBar",org.opaeum.uim.editor.ActionBarImpl.class);
		classes.put("editor:MenuConfiguration",org.opaeum.uim.editor.MenuConfigurationImpl.class);
		classes.put("editor:OperationMenuItem",org.opaeum.uim.editor.OperationMenuItemImpl.class);
		classes.put("editor:ResponsibilityViewer",org.opaeum.uim.editor.ResponsibilityViewerImpl.class);
		classes.put("editor:QueryResultPage",org.opaeum.uim.editor.QueryResultPageImpl.class);
		classes.put("editor:ObjectEditor",org.opaeum.uim.editor.ObjectEditorImpl.class);
		classes.put("bind:LookupBinding",org.opaeum.uim.binding.LookupBindingImpl.class);
		classes.put("bind:TableBinding",org.opaeum.uim.binding.TableBindingImpl.class);
		classes.put("bind:FieldBinding",org.opaeum.uim.binding.FieldBindingImpl.class);
		classes.put("bind:PropertyRef",org.opaeum.uim.binding.PropertyRefImpl.class);
		classes.put("constr:EditableConstrainedObject",org.opaeum.uim.constraint.EditableConstrainedObjectImpl.class);
		classes.put("constr:ConstrainedObject",org.opaeum.uim.constraint.ConstrainedObjectImpl.class);
		classes.put("constr:RequiredRole",org.opaeum.uim.constraint.RequiredRoleImpl.class);
		classes.put("constr:RootUserInteractionConstraint",org.opaeum.uim.constraint.RootUserInteractionConstraintImpl.class);
		classes.put("constr:UserInteractionConstraint",org.opaeum.uim.constraint.UserInteractionConstraintImpl.class);
		classes.put("constr:RequiredState",org.opaeum.uim.constraint.RequiredStateImpl.class);
		classes.put("action:BuiltInActionButton",org.opaeum.uim.action.BuiltInActionButtonImpl.class);
		classes.put("action:TransitionButton",org.opaeum.uim.action.TransitionButtonImpl.class);
		classes.put("action:LinkToQuery",org.opaeum.uim.action.LinkToQueryImpl.class);
		classes.put("action:InvocationButton",org.opaeum.uim.action.InvocationButtonImpl.class);
		classes.put("action:BuiltInLink",org.opaeum.uim.action.BuiltInLinkImpl.class);
		classes.put("panel:GridPanel",org.opaeum.uim.panel.GridPanelImpl.class);
		classes.put("panel:VerticalPanel",org.opaeum.uim.panel.VerticalPanelImpl.class);
		classes.put("panel:HorizontalPanel",org.opaeum.uim.panel.HorizontalPanelImpl.class);
		classes.put("wizard:NewObjectWizard",org.opaeum.uim.wizard.NewObjectWizardImpl.class);
		classes.put("wizard:WizardPage",org.opaeum.uim.wizard.WizardPageImpl.class);
		classes.put("wizard:ResponsibilityInvocationWizard",org.opaeum.uim.wizard.ResponsibilityInvocationWizardImpl.class);
		classes.put("wizard:BehaviorInvocationWizard",org.opaeum.uim.wizard.BehaviorInvocationWizardImpl.class);
		classes.put("wizard:OperationResultPage",org.opaeum.uim.wizard.OperationResultPageImpl.class);
		classes.put("persp:PerspectiveConfiguration",org.opaeum.uim.perspective.PerspectiveConfigurationImpl.class);
		classes.put("persp:NavigatorConfiguration",org.opaeum.uim.perspective.NavigatorConfigurationImpl.class);
		classes.put("persp:ClassNavigationConstraint",org.opaeum.uim.perspective.ClassNavigationConstraintImpl.class);
		classes.put("persp:PropertyNavigationConstraint",org.opaeum.uim.perspective.PropertyNavigationConstraintImpl.class);
		classes.put("persp:EditorConfiguration",org.opaeum.uim.perspective.EditorConfigurationImpl.class);
		classes.put("persp:PropertiesConfiguration",org.opaeum.uim.perspective.PropertiesConfigurationImpl.class);
		classes.put("persp:NavigationConstraint",org.opaeum.uim.perspective.NavigationConstraintImpl.class);
		classes.put("persp:OperationNavigationConstraint",org.opaeum.uim.perspective.OperationNavigationConstraintImpl.class);
		classes.put("persp:BehaviorNavigationConstraint",org.opaeum.uim.perspective.BehaviorNavigationConstraintImpl.class);
		classes.put("persp:InboxConfiguration",org.opaeum.uim.perspective.InboxConfigurationImpl.class);
		classes.put("persp:OutboxConfiguration",org.opaeum.uim.perspective.OutboxConfigurationImpl.class);
		classes.put("persp:ParameterNavigationConstraint",org.opaeum.uim.perspective.ParameterNavigationConstraintImpl.class);
		classes.put("persp:MultiplicityElementNavigationConstraint",org.opaeum.uim.perspective.MultiplicityElementNavigationConstraintImpl.class);
		classes.put("cube:CubeQuery",org.opaeum.uim.cube.CubeQueryImpl.class);
		classes.put("cube:DimensionBinding",org.opaeum.uim.cube.DimensionBindingImpl.class);
		classes.put("cube:LevelProperty",org.opaeum.uim.cube.LevelPropertyImpl.class);
		classes.put("cube:RowAxisEntry",org.opaeum.uim.cube.RowAxisEntryImpl.class);
		classes.put("cube:ColumnAxisEntry",org.opaeum.uim.cube.ColumnAxisEntryImpl.class);
		classes.put("cube:MeasureProperty",org.opaeum.uim.cube.MeasurePropertyImpl.class);
		classes.put("cube:CubeQueryEditor",org.opaeum.uim.cube.CubeQueryEditorImpl.class);
		classes.put("model:ClassUserInteractionModel",org.opaeum.uim.model.ClassUserInteractionModelImpl.class);
		classes.put("model:ResponsibilityUserInteractionModel",org.opaeum.uim.model.ResponsibilityUserInteractionModelImpl.class);
		classes.put("model:BehaviorUserInteractionModel",org.opaeum.uim.model.BehaviorUserInteractionModelImpl.class);
		classes.put("model:QueryInvoker",org.opaeum.uim.model.QueryInvokerImpl.class);
		classes.put("model:OperationInvocationWizard",org.opaeum.uim.model.OperationInvocationWizardImpl.class);
		classes.put("model:EmbeddedTaskEditor",org.opaeum.uim.model.EmbeddedTaskEditorImpl.class);
		classes.put("comp:UimField",org.opaeum.uim.component.UimFieldImpl.class);
		classes.put("comp:UimDataTable",org.opaeum.uim.component.UimDataTableImpl.class);
		classes.put("comp:MasterTree",org.opaeum.uim.component.MasterTreeImpl.class);
		classes.put("comp:DetailComponent",org.opaeum.uim.component.DetailComponentImpl.class);
		classes.put("comp:PanelForClass",org.opaeum.uim.component.PanelForClassImpl.class);
	}


}