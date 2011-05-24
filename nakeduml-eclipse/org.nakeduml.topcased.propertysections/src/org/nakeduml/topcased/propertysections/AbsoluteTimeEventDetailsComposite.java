package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class AbsoluteTimeEventDetailsComposite extends Composite{
	private static final String EVENT_CONTEXT_SOURCE = "http://www.nakeduml.org/eventContext";
	protected CLabel expressionLabel;
	private OclValueComposite expressionComposite;
	Trigger trigger;
	public AbsoluteTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		super(parent, SWT.NONE);
		super.setLayout(new FormLayout());

		addChildren(toolkit, parent, standardLabelWidth);
	}
	protected void addChildren(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		setBackground(parent.getBackground());
		expressionLabel = toolkit.createCLabel(this, "Date value");
		FormData labelData = new FormData();
		FormData expData = new FormData();
		Control[] c = getChildren();
		if(c.length == 1){
			//There is a dummy label control
			labelData.top = new FormAttachment(0, 4, 0);
			expData.top = new FormAttachment(0, 4, 0);
		}else{
			labelData.top = new FormAttachment(c[c.length - 1], 4, 0);
			expData.top = new FormAttachment(c[c.length - 1], 4, 0);
		}
		expressionLabel.setLayoutData(labelData);
		expressionComposite = new OclValueComposite(this, toolkit);
		GridData ecd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, GridData.VERTICAL_ALIGN_BEGINNING, true, true);
		ecd.minimumHeight = 100;
		ecd.minimumWidth = 300;
		expData.left = new FormAttachment(0, standardLabelWidth);
		expData.right = new FormAttachment(100, 0);
		// data.bottom=new FormAttachment(100,0);
		expData.height = 50;
		expressionComposite.setLayoutData(expData);
		TextChangeListener listener = new TextChangeListener(){
			public void textChanged(Control control){
				handleTextModified();
			}
			public void focusIn(Control control){
			}
			public void focusOut(Control control){
			}
		};
		listener.startListeningTo(expressionComposite.getTextControl());
	}
	protected void handleTextModified(){
		OpaqueExpression oe = getOpaqueExpression();
		oe.getBodies().set(0, expressionComposite.getTextControl().getText());
	}
	protected OpaqueExpression getOpaqueExpression(){
		return (OpaqueExpression) ((TimeEvent) trigger.getEvent()).getWhen().getExpr();
	}
	protected TimeEvent findOrCreateTimeEvent(EditingDomain domain,Trigger t){
		TimeEvent timeEvent = null;
		if(t.getEvent() instanceof TimeEvent){
			if(((TimeEvent) t.getEvent()).isRelative() == isRelative()){
				timeEvent = (TimeEvent) t.getEvent();
			}
		}
		if(timeEvent == null){
			Package eventsPackage = trigger.getNearestPackage().getNestedPackage("events", false, UMLPackage.eINSTANCE.getPackage(), true);
			timeEvent = findTimeEvent(eventsPackage);
			if(timeEvent == null){
				timeEvent = UMLFactory.eINSTANCE.createTimeEvent();
				timeEvent.setName(((NamedElement) t.getOwner()).getName() + "TimeEvent");
				Command cmd = AddCommand.create(domain, eventsPackage, UMLPackage.eINSTANCE.getPackage_PackagedElement(), timeEvent);
				domain.getCommandStack().execute(cmd);
				timeEvent.createEAnnotation(EVENT_CONTEXT_SOURCE).getReferences().add(trigger);
				timeEvent.setIsRelative(isRelative());
			}
			t.setEvent(timeEvent);
		}
		return timeEvent;
	}
	protected boolean isRelative(){
		return false;
	}
	private TimeEvent findTimeEvent(Package eventsPackage){
		TimeEvent timeEvent = null;
		outer:for(PackageableElement pe:eventsPackage.getPackagedElements()){
			if(pe instanceof TimeEvent){
				if(((TimeEvent) pe).isRelative()==isRelative()){
					EAnnotation eAnnotation = pe.getEAnnotation(EVENT_CONTEXT_SOURCE);
					if(eAnnotation != null){
						for(EObject eObject:eAnnotation.getReferences()){
							if(eObject == trigger){
								timeEvent = (TimeEvent) pe;
								break outer;
							}
						}
					}
				}
			}
		}
		return timeEvent;
	}
	public void setTrigger(EditingDomain domain,Trigger t){
		if(t.eResource() != null){
			trigger = t;
			expressionComposite.setValueElement(t);
			TimeEvent timeEvent = findOrCreateTimeEvent(domain, t);
			if(timeEvent.getWhen() == null){
				timeEvent.createWhen("when", null);
			}
			if(timeEvent.getWhen().getExpr() == null){
				timeEvent.getWhen().createExpr("expr", null, UMLPackage.eINSTANCE.getOpaqueExpression());
			}
			EList<String> bodies = ((OpaqueExpression) timeEvent.getWhen().getExpr()).getBodies();
			if(bodies.size() == 0){
				bodies.add("Type OCL");
			}
			expressionComposite.getTextControl().setText(bodies.get(0));
		}
	}
}