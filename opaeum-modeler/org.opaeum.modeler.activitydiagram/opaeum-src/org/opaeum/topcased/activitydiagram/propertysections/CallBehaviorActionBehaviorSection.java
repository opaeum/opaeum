package org.opaeum.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.topcased.modeler.commands.EMFtoGEFCommandWrapper;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.widgets.CSingleObjectChooserOrDisplay;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;

public class CallBehaviorActionBehaviorSection extends AbstractChooserPropertySection{
	public void refresh(){
		super.refresh();
		if(isBusinessProcessCall()){
			super.labelCombo.setText("Select Business Process:");
		}else if(isScreenflowCall()){
			super.labelCombo.setText("Select Screen Flow:");
		}else{
			super.labelCombo.setText("Select Method:");
		}
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		cSingleObjectChooser.setEditable(true);
		cSingleObjectChooser.addTextKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.character == SWT.CR){
					Text field = (Text) e.widget;
					String content = field.getText();
					Matcher matcher = Pattern.compile("(<[\\w\\s]*>)?((.)*)").matcher(content);
					String name = content;
					if(matcher.matches()){
						name = matcher.group(2).trim();
					}
					if(cSingleObjectChooser.getSelection() instanceof Behavior){
						Behavior behavior = (Behavior) cSingleObjectChooser.getSelection();
						Command setCommmand = SetCommand.create(getEditingDomain(), behavior, UMLPackage.Literals.NAMED_ELEMENT__NAME, name);
						MixedEditDomain mixedEditDomain = (MixedEditDomain) ((Modeler) getPart().getAdapter(Modeler.class)).getAdapter(MixedEditDomain.class);
						if(mixedEditDomain != null){
							mixedEditDomain.getCommandStack().execute(new EMFtoGEFCommandWrapper(setCommmand));
						}
						if(getPart() instanceof Modeler){
							Modeler modeler = (Modeler) getPart();
							modeler.refreshActiveDiagram();
						}
					}
				}
			}
		});
	}
	@Override
	protected CSingleObjectChooser createObjectChooser(Composite parent,TabbedPropertySheetWidgetFactory factory,int style){
		return new CSingleObjectChooserOrDisplay(parent, factory, style);
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
