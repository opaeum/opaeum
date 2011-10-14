package org.opaeum.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.NavigationDecorator;
import org.opaeum.topcased.propertysections.OpaeumChooserPropertySection;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;

public class CallBehaviorActionBehaviorSection extends OpaeumChooserPropertySection{
	private Button createButton;
	public void refresh(){
		super.refresh();
		if(isBusinessProcessCall()){
			super.labelCombo.setText("Select Business Process:");
			createButton.setText("Create Business Process:");
		}else if(isScreenflowCall()){
			super.labelCombo.setText("Select :");
			createButton.setText("Create Screen Flow:");
		}else{
			super.labelCombo.setText("Select Method:");
			createButton.setText("Create Method:");
		}
	}
	@Override
	protected void setSectionData(Composite composite){
		super.setSectionData(composite);
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{
			getLabelText()
		}));
		data.right = new FormAttachment(100,-150);
		data.top = new FormAttachment(labelCombo, 0, SWT.CENTER);
		cSingleObjectChooser.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(cSingleObjectChooser);
		data.right= new FormAttachment(100,0);
		createButton.setLayoutData(data);
	};
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.createButton = getWidgetFactory().createButton(composite, "Create Behavior", SWT.PUSH);
		this.createButton.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				Activity a = EmfActivityUtil.getContainingActivity(getAction());
				BehavioredClassifier owner = a;
				if(a.getContext() != null){
					owner = a.getContext();
				}
				Behavior b = null;
				if(isBusinessProcessCall()){
					b = UMLFactory.eINSTANCE.createActivity();
					StereotypesHelper.getNumlAnnotation(b).getDetails().put(StereotypeNames.BUSINES_PROCESS, "");
				}else if(isScreenflowCall()){
					b = UMLFactory.eINSTANCE.createStateMachine();
					StereotypesHelper.getNumlAnnotation(b).getDetails().put(StereotypeNames.SCREEN_FLOW, "");
				}else{
					b = UMLFactory.eINSTANCE.createActivity();
					StereotypesHelper.getNumlAnnotation(b).getDetails().put(StereotypeNames.METHOD, "");
				}
				for(InputPin inputPin:getAction().getArguments()){
					createParam(b, inputPin).setDirection(ParameterDirectionKind.IN_LITERAL);
				}
				EList<OutputPin> results = getAction().getResults();
				for(OutputPin inputPin:results){
					createParam(b, inputPin).setDirection(results.size()==1? ParameterDirectionKind.RETURN_LITERAL: ParameterDirectionKind.OUT_LITERAL);
				}
				getEditingDomain().getCommandStack().execute(
						AddCommand.create(getEditingDomain(), owner, UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior(), b));
				getEditingDomain().getCommandStack().execute(
						SetCommand.create(getEditingDomain(), getAction(), UMLPackage.eINSTANCE.getCallBehaviorAction_Behavior(), b));
				NavigationDecorator.goToEObject(b, getActivePage());
			}
			protected Parameter createParam(Behavior b,Pin inputPin){
				Parameter p = b.createOwnedParameter(inputPin.getName(), inputPin.getType());
				p.setIsUnique(inputPin.isUnique());
				p.setIsOrdered(inputPin.isOrdered());
				p.setUpper(inputPin.getUpper());
				return p;
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
	}
	@Override
	protected CSingleObjectChooser createObjectChooser(Composite parent,TabbedPropertySheetWidgetFactory factory,int style){
		return new CSingleObjectChooser(parent, factory, style);
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<Behavior> ownedBehaviors = EmfBehaviorUtil.findBehaviorsInScope(getAction());
		List<Behavior> all = new ArrayList<Behavior>();
		// TODO handle transition and state owned behaviors
		for(Behavior behavior:ownedBehaviors){
			if((isMatch(behavior))){
				all.add(behavior);
			}
		}
		choices.addAll(all);
		return choices.toArray();
	}
	private boolean isMatch(Behavior behavior){
		return (isProcess(behavior) && isBusinessProcessCall()) || (isScreenflowCall() && isScreenflow(behavior)) || (isMethodCall() && isMethod(behavior));
	}
	private boolean isProcess(Behavior behavior){
		return StereotypesHelper.hasKeyword(behavior, StereotypeNames.BUSINES_PROCESS) && (behavior instanceof Activity || behavior instanceof StateMachine);
	}
	private boolean isMethod(Behavior behavior){
		return !StereotypesHelper.hasKeyword(behavior, StereotypeNames.BUSINES_PROCESS) && behavior instanceof Activity;
	}
	private boolean isScreenflow(Behavior behavior){
		return StereotypesHelper.hasKeyword(behavior, StereotypeNames.SCREEN_FLOW) && behavior instanceof StateMachine;
	}
	@Override
	protected Object getFeatureValue(){
		return getAction().getBehavior();
	}
	private CallBehaviorAction getAction(){
		return((CallBehaviorAction) getEObject());
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getCallBehaviorAction_Behavior();
	}
	@Override
	protected String getLabelText(){
		return "bla";
	}
	private boolean isBusinessProcessCall(){
		return StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.CALL_BUSINES_PROCESS_ACTION);
	}
	private boolean isScreenflowCall(){
		return StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
	}
	private boolean isMethodCall(){
		return !StereotypesHelper.hasStereotype((Element) getEObject(), StereotypeNames.CALL_BUSINES_PROCESS_ACTION);
	}
}
