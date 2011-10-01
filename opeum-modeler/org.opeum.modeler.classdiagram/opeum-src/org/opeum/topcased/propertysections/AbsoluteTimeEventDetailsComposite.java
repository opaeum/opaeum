package org.opeum.topcased.propertysections;

import org.opeum.emf.extraction.StereotypesHelper;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opeum.topcased.propertysections.ocl.OclBodyComposite;
import org.opeum.topcased.propertysections.ocl.OpaqueExpressionComposite;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class AbsoluteTimeEventDetailsComposite extends Composite{
	public static interface TimeEventListener{
		void timeEventChanged(TimeEvent t);
	}
	protected CLabel expressionLabel;
	private OpaqueExpressionComposite expressionComposite;
	protected TimeEvent event;
	Element trigger;
	protected Text nameTxt;
	private TimeEventListener listener;
	private EditingDomain editingDomain;
	public AbsoluteTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth,
			TimeEventListener listener){
		this(toolkit, parent, standardLabelWidth);
		this.listener = listener;
	}
	public AbsoluteTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		super(parent, SWT.NONE);
		setBackground(parent.getBackground());
		super.setLayout(new FormLayout());
		Label createLabel = toolkit.createLabel(this, "Timer Name");
		createLabel.setLayoutData(new FormData());
		this.nameTxt = toolkit.createText(this, "", SWT.BORDER);
		FormData nameData = new FormData();
		nameData.left = new FormAttachment(0, standardLabelWidth);
		nameData.right = new FormAttachment(100, 0);
		nameData.height = 12;
		nameTxt.setLayoutData(nameData);
		nameTxt.addFocusListener(new FocusListener(){
			public void focusLost(FocusEvent e){
				event.setName(nameTxt.getText());
				maybeFire();
			}
			public void focusGained(FocusEvent e){
			}
		});
		TextChangeListener textChangeListener = new TextChangeListener(){
			public void textChanged(Control control){
				event.setName(nameTxt.getText());
				maybeFire();
			}
			public void focusOut(Control control){
			}
			public void focusIn(Control control){
			}
		};
		textChangeListener.startListeningForEnter(nameTxt);
		addChildrenAfterName(toolkit, parent, standardLabelWidth);
		addOclComposite(toolkit, parent, standardLabelWidth);
		addChildrenAfterOclComposite(toolkit, parent, standardLabelWidth);
		toolkit.adapt(this);
	}
	public void maybeFire(){
		if(listener != null){
			listener.timeEventChanged(event);
		}
	}
	protected void addChildrenAfterOclComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
	}
	protected void addChildrenAfterName(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
	}
	private void addOclComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		setBackground(parent.getBackground());
		expressionLabel = toolkit.createCLabel(this, "Date value");
		FormData labelData = new FormData();
		FormData expData = new FormData();
		Control[] c = getChildren();
		if(c.length == 1){
			labelData.top = new FormAttachment(0, 4, 0);
			expData.top = new FormAttachment(0, 4, 0);
		}else{
			labelData.top = new FormAttachment(c[c.length - 2], 4, 0);
			expData.top = new FormAttachment(c[c.length - 2], 4, 0);
		}
		expressionLabel.setLayoutData(labelData);
		expressionComposite = new OpaqueExpressionComposite(this, toolkit){
			@Override
			public void fireOclChanged(String value){
				super.oclBodyOwner=getOpaqueExpression();
				super.fireOclChanged(value);
				maybeFire();
			}

			@Override
			protected EditingDomain getEditingDomain(){
				return editingDomain;
			}
		};
		expData.left = new FormAttachment(0, standardLabelWidth);
		expData.right = new FormAttachment(100, 0);
		expData.bottom = new FormAttachment(100, 0);
		expData.height = 50;
		expressionComposite.setBackground(getBackground());
		expressionComposite.setLayoutData(expData);
	}
	protected OpaqueExpression getOpaqueExpression(){
		return (OpaqueExpression) event.getWhen().getExpr();
	}
	protected TimeEvent findOrCreateTimeEvent(Trigger t){
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
				Command cmd = AddCommand.create(editingDomain, eventsPackage, UMLPackage.eINSTANCE.getPackage_PackagedElement(), timeEvent);
				editingDomain.getCommandStack().execute(cmd);
				StereotypesHelper.getNumlAnnotation(timeEvent).getReferences().add(trigger);
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
				if(((TimeEvent) pe).isRelative() == isRelative()){
					EAnnotation eAnnotation = StereotypesHelper.getNumlAnnotation(pe);
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
	public void setTrigger(Trigger t){
		if(t.eResource() != null){
			trigger = t;
			TimeEvent timeEvent = findOrCreateTimeEvent(t);
			setContext(t, timeEvent);
		}
	}
	public void setContext(NamedElement context,TimeEvent te){
		if(context != null && context.eResource() != null){
			trigger = context;
			initProfileElements(context);
			setTimeEvent(te);
			expressionComposite.setOclContext(context, getOpaqueExpression());
		}else{
			setTimeEvent(null);
		}
	}
	public TimeEvent getTimeEvent(){
		return event;
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		expressionComposite.setEnabled(b);
		nameTxt.setEnabled(b);
	}
	protected void initProfileElements(Element e){
	}
	protected void setTimeEvent(TimeEvent timeEvent){
		if(timeEvent != null){
			setEnabled(true);
			if(timeEvent.getWhen() == null){
				TimeExpression when = UMLFactory.eINSTANCE.createTimeExpression();
				when.setName("when");
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getTimeEvent_When(), when));
			}
			if(timeEvent.getWhen().getExpr() == null){
				OpaqueExpression expr = UMLFactory.eINSTANCE.createOpaqueExpression();
				expr.setName("expr");
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, timeEvent.getWhen(), UMLPackage.eINSTANCE.getTimeExpression_Expr(), expr));
			}
			EList<String> bodies = ((OpaqueExpression) timeEvent.getWhen().getExpr()).getBodies();
			if(bodies.size() == 0){
				bodies.add(OclBodyComposite.DEFAULT_TEXT);
			}
			expressionComposite.setOclContext(timeEvent, (OpaqueExpression) timeEvent.getWhen().getExpr());
			nameTxt.setText(timeEvent.getName());
		}else{
			nameTxt.setText("");
			expressionComposite.getTextControl().setText("");
			setEnabled(false);
		}
		this.event = timeEvent;
	}
	public EditingDomain getEditingDomain(){
		return editingDomain;
	}
	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain;
	}

}