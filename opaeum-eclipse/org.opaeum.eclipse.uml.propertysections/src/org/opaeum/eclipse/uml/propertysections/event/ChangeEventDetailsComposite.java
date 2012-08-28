package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.ocl.AutoCreateOpaqueExpressionComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ChangeEventDetailsComposite extends Composite{
	protected OpaqueExpressionComposite changeComposite;
	Trigger trigger;
	private EditingDomain editingDomain;
	public ChangeEventDetailsComposite(Composite parent,int labelWidth,FormToolkit toolkit){
		super(parent, SWT.NONE);
		setLayout(new FormLayout());
		Label l = toolkit.createLabel(this, "Condition");
		setBackground(parent.getBackground());
		changeComposite = new AutoCreateOpaqueExpressionComposite(this,toolkit){
			@Override
			public EReference getValueSpecificationFeature(){
				// TODO Auto-generated method stub
				return UMLPackage.eINSTANCE.getChangeEvent_ChangeExpression();
			}
			@Override
			protected EditingDomain getEditingDomain(){
				return editingDomain;
			}
			@Override
			public void fireOclChanged(String value){
				ChangeEvent event = null;
				if(!(trigger.getEvent() instanceof ChangeEvent)){
					EAnnotation ann = StereotypesHelper.getNumlAnnotation(trigger);
					if(ann == null){
						ann=EcoreFactory.eINSTANCE.createEAnnotation();
						ann.setSource(StereotypeNames.NUML_ANNOTATION);
						editingDomain.getCommandStack()
						.execute(AddCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(),ann));
					}
					EList<EObject> contents = ann.getContents();
					for(EObject eObject:contents){
						if(eObject instanceof ChangeEvent){
							event = ((ChangeEvent) eObject);
						}
					}
					if(event == null){
						event = UMLFactory.eINSTANCE.createChangeEvent();
						editingDomain.getCommandStack().execute(
								AddCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), event));
					}
					editingDomain.getCommandStack()
							.execute(SetCommand.create(editingDomain, trigger, UMLPackage.eINSTANCE.getTrigger_Event(), event));
				}else{
					event = (ChangeEvent) trigger.getEvent();
				}
				valueSpecificationOwner=event;
				super.fireOclChanged(value);
			}
		};
		changeComposite.setBackground(getBackground());
		FormData layoutData = new FormData();
		layoutData.left = new FormAttachment(0, labelWidth);
		layoutData.right = new FormAttachment(100);
		layoutData.top = new FormAttachment();
		layoutData.bottom = new FormAttachment(100);
		changeComposite.setLayoutData(layoutData);
	}
	public void setTrigger(Trigger t){
		this.trigger = t;
		if(trigger.getEvent() instanceof ChangeEvent){
			ChangeEvent ce = (ChangeEvent) trigger.getEvent();
			if(ce.getChangeExpression() instanceof OpaqueExpression){
				OpaqueExpression expr = (OpaqueExpression) ce.getChangeExpression();
				changeComposite.setOclContext(trigger, expr);
			}else{
				changeComposite.setOclContext(trigger, null);
			}
		}else{
			changeComposite.setOclContext(trigger, null);
		}
		layout();
	}
	public EditingDomain getEditingDomain(){
		return editingDomain;
	}
	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain;
	}
}
