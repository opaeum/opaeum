package org.nakeduml.topcased.propertysections;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
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
import org.nakeduml.topcased.propertysections.ocl.OclValueComposite;
import org.nakeduml.topcased.propertysections.ocl.OclValueComposite.OclChangeListener;

public class ChangeEventDetailsComposite extends Composite{
	protected OclValueComposite changeComposite;
	Trigger trigger;

	public ChangeEventDetailsComposite(Composite parent,int labelWidth, FormToolkit toolkit){
		super(parent, SWT.NONE);
		setLayout(new FormLayout());
		Label l = toolkit.createLabel(this, "Condition");
		setBackground(parent.getBackground());
		changeComposite = new OclValueComposite(this, toolkit,new OclChangeListener(){
			@Override
			public void oclChanged(String value){
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
						ann.getContents().add(event);
					}
					trigger.setEvent(event);
				}else{
					event = (ChangeEvent) trigger.getEvent();
				}
				OpaqueExpression expr = UMLFactory.eINSTANCE.createOpaqueExpression();
				event.setChangeExpression(expr);
				expr.getBodies().add(changeComposite.getTextControl().getText());
				expr.getLanguages().add("ocl");
			}
		});
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
				if(expr.getBodies().size() > 0){
					changeComposite.getTextControl().setText(expr.getBodies().get(0));
				}
			}
		}
		changeComposite.setValueElement(trigger);
		layout();
		
	}
}
