package org.nakeduml.topcased.propertysections;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
import org.nakeduml.topcased.propertysections.ocl.OpaqueExpressionBodyComposite;

public class ChangeEventDetailsComposite extends Composite{
	protected OpaqueExpressionBodyComposite changeComposite;
	Trigger trigger;

	public ChangeEventDetailsComposite(final EditingDomain domain, Composite parent,int labelWidth, FormToolkit toolkit){
		super(parent, SWT.NONE);
		setLayout(new FormLayout());
		Label l = toolkit.createLabel(this, "Condition");
		setBackground(parent.getBackground());
		changeComposite = new OpaqueExpressionBodyComposite(this, toolkit){
			@Override
			protected EditingDomain getEditingDomain(){
				return domain;
			}

			@Override
			public void fireOclChanged(String value){
				ChangeEvent event = null;
				if(!(trigger.getEvent() instanceof ChangeEvent)){
					EAnnotation ann = StereotypesHelper.getNumlAnnotation(trigger);
					EList<EObject> contents = ann.getContents();
					for(EObject eObject:contents){
						if(eObject instanceof ChangeEvent){
							event = ((ChangeEvent) eObject);
						}
					}
					if(event == null){
						event = UMLFactory.eINSTANCE.createChangeEvent();
						event.createChangeExpression("changeExpression", null, UMLPackage.eINSTANCE.getOpaqueExpression());
						ann.getContents().add(event);
						domain.getCommandStack().execute(AddCommand.create(domain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), event));
					}
					domain.getCommandStack().execute(SetCommand.create(domain, trigger, UMLPackage.eINSTANCE.getTrigger_Event(), event));
				}else{
					event = (ChangeEvent) trigger.getEvent();
				}
				super.valueSpecificationOwner=trigger.getEvent();
				super.fireOclChanged(value);
			}

			@Override
			public EReference getValueSpecificationFeature(){
				return UMLPackage.eINSTANCE.getChangeEvent_ChangeExpression();
			}
		};
		changeComposite.setBackground(getBackground());
		FormData layoutData = new FormData();
		layoutData.left=new FormAttachment(0,labelWidth);
		layoutData.right=new FormAttachment(100);
		layoutData.top=new FormAttachment();
		layoutData.bottom=new FormAttachment(100);
		changeComposite.setLayoutData(layoutData);

	}
	public void setTrigger(Trigger t){
		this.trigger=t;
		if(trigger.getEvent() instanceof ChangeEvent){
			ChangeEvent ce = (ChangeEvent) trigger.getEvent();
			if(ce.getChangeExpression() instanceof OpaqueExpression){
				OpaqueExpression expr = (OpaqueExpression) ce.getChangeExpression();
				changeComposite.setOclContext(trigger,ce,expr);
			}
		}
		layout();
		
	}
}
