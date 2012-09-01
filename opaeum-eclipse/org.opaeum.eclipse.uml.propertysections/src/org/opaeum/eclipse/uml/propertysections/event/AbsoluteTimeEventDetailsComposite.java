package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.commands.SetOclBodyCommand;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class AbsoluteTimeEventDetailsComposite extends Composite{
	protected CLabel expressionLabel;
	private OpaqueExpressionComposite expressionComposite;
	protected TimeEvent event;
	Element trigger;
	protected Text nameTxt;
	protected EditingDomain editingDomain;
	protected RecursiveAdapter adapter = new RecursiveAdapter(){
		public void notifyChanged(Notification msg){
			if(nameTxt.isDisposed()){
				adapter.unsubscribe();
			}else{
				setContext((NamedElement) trigger, event);
			}
		};
	};
	public AbsoluteTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		super(parent, SWT.NONE);
		setBackground(parent.getBackground());
		super.setLayout(new FormLayout());
		CLabel createLabel = toolkit.createCLabel(this, "Timer Name");
		FormData labelData = new FormData();
		createLabel.setLayoutData(labelData);
		this.nameTxt = toolkit.createText(this, "", SWT.BORDER);
		FormData nameData = new FormData();
		nameData.left = new FormAttachment(0, standardLabelWidth);
		nameData.right = new FormAttachment(100, 0);
		nameData.height = 12;
		nameTxt.setLayoutData(nameData);
		TextChangeListener textChangeListener = new TextChangeListener(){
			public void textChanged(Control control){
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, event, UMLPackage.eINSTANCE.getNamedElement_Name(), nameTxt.getText()));
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
				super.oclBodyOwner = getWhenExpression();
				super.fireOclChanged(value);
			}
			@Override
			protected EditingDomain getEditingDomain(){
				return editingDomain;
			}
		};
		expData.left = new FormAttachment(0, standardLabelWidth);
		expData.right = new FormAttachment(100, 0);
		expData.height = 50;
		expressionComposite.setBackground(getBackground());
		expressionComposite.setLayoutData(expData);
	}
	protected OpaqueExpression getWhenExpression(){
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
			EAnnotation ann = StereotypesHelper.getNumlAnnotation(t);
			if(ann == null){
				ann = EcoreFactory.eINSTANCE.createEAnnotation();
				ann.setSource(StereotypeNames.NUML_ANNOTATION);
				Command cmd = AddCommand.create(editingDomain, t, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann);
				editingDomain.getCommandStack().execute(cmd);
			}
			timeEvent = findTimeEvent(ann);
			if(timeEvent == null){
				timeEvent = UMLFactory.eINSTANCE.createTimeEvent();
				timeEvent.setName(((NamedElement) t.getOwner()).getName() + "TimeEvent");
				timeEvent.setIsRelative(isRelative());
				Command cmd = AddCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), timeEvent);
				editingDomain.getCommandStack().execute(cmd);
			}
			t.setEvent(timeEvent);
		}
		return timeEvent;
	}
	protected boolean isRelative(){
		return false;
	}
	private TimeEvent findTimeEvent(EAnnotation ann){
		EList<EObject> contents = ann.getContents();
		for(EObject pe:contents){
			if(pe instanceof TimeEvent){
				if(((TimeEvent) pe).isRelative() == isRelative()){
					return (TimeEvent) pe;
				}
			}
		}
		return null;
	}
	public void setTrigger(Trigger t){
		if(t.eResource() != null){
			trigger = t;
			TimeEvent timeEvent = findOrCreateTimeEvent(t);
			setContext(t, timeEvent);
		}
	}
	public void setContext(NamedElement context,TimeEvent te){
		if(this.event != te){
			adapter.unsubscribe();
			if(te != null){
				adapter.subscribeTo(te, 4);
			}
		}
		if(context != null && context.eResource() != null){
			trigger = context;
			initProfileElements(context);
			setTimeEvent(te);
			expressionComposite.setOclContext(context, getWhenExpression());
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
				editingDomain.getCommandStack()
						.execute(SetCommand.create(editingDomain, timeEvent, UMLPackage.eINSTANCE.getTimeEvent_When(), when));
			}
			if(timeEvent.getWhen().getExpr() == null){
				OpaqueExpression expr = UMLFactory.eINSTANCE.createOpaqueExpression();
				expr.setName("expr");
				editingDomain.getCommandStack().execute(
						SetCommand.create(editingDomain, timeEvent.getWhen(), UMLPackage.eINSTANCE.getTimeExpression_Expr(), expr));
			}
			EList<String> bodies = ((OpaqueExpression) timeEvent.getWhen().getExpr()).getBodies();
			if(bodies.size() == 0){
				editingDomain.getCommandStack().execute(
						new SetOclBodyCommand(editingDomain, timeEvent.getWhen().getExpr(), UMLPackage.eINSTANCE.getOpaqueExpression_Body(),
								UMLPackage.eINSTANCE.getOpaqueExpression_Language(), OclBodyComposite.DEFAULT_TEXT));
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