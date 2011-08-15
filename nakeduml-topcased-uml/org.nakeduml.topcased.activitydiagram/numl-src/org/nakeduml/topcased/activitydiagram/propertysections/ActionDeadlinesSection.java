package org.nakeduml.topcased.activitydiagram.propertysections;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.nakeduml.eclipse.ApplyProfileAction;
import org.nakeduml.topcased.propertysections.AbsoluteTimeEventDetailsComposite;
import org.nakeduml.topcased.propertysections.RelativeTimeEventDetailsComposite;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class ActionDeadlinesSection extends AbstractTabbedPropertySection{
	private DeadlinesTableComposite table;
	private Composite details;
	private AbsoluteTimeEventDetailsComposite absoluteComposite;
	private RelativeTimeEventDetailsComposite relativeComposite;
	private Group groupDetails;
	private EList<EnumerationLiteral> deadlineKinds;
	private Stereotype deadlineStereotype;
	private Stereotype taskStereotype;
	protected void createWidgets(Composite composite){
		table = new DeadlinesTableComposite(composite, SWT.NONE, getWidgetFactory()){
			public void updateSelectedDeadlines(TimeEvent event){
				if(event != null){
					if(event.isRelative()){
						relativeComposite.setContext(action, event);
						((StackLayout) details.getLayout()).topControl = relativeComposite;
					}else{
						absoluteComposite.setContext(action, event);
						((StackLayout) details.getLayout()).topControl = absoluteComposite;
					}
				}else{
					((StackLayout) details.getLayout()).topControl = null;
				}
				details.layout();
				refresh();
			}
		};
		groupDetails = getWidgetFactory().createGroup(composite, "Details of the Deadline");
		groupDetails.setLayout(new GridLayout());
		details = new Composite(groupDetails, SWT.NONE);
		details.setBackground(composite.getBackground());
		details.setLayoutData(new GridData(GridData.FILL_BOTH));
		details.setLayout(new StackLayout());
		AbsoluteTimeEventDetailsComposite.TimeEventListener listener = new AbsoluteTimeEventDetailsComposite.TimeEventListener(){
			public void timeEventChanged(TimeEvent t){
				table.refresh();
			}
		};
		absoluteComposite = new AbsoluteTimeEventDetailsComposite(getEditingDomain(), getWidgetFactory(), details, 200, listener){
			CCombo deadlineKindsCombo;
			@Override
			public void setContext(NamedElement context,TimeEvent te){
				super.setContext(context, te);
				updateCombo(te, deadlineKindsCombo);
			}
			@Override
			protected void addChildrenAfterName(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
				deadlineKindsCombo = addDeadlineKind(toolkit, standardLabelWidth, this);
				super.addChildrenAfterName(toolkit, parent, standardLabelWidth);
			}
		};
		relativeComposite = new RelativeTimeEventDetailsComposite(getEditingDomain(),getWidgetFactory(), details, 200, listener){
			CCombo deadlineKindsCombo;
			@Override
			public void setContext(NamedElement context,TimeEvent te){
				super.setContext(context, te);
				updateCombo(te, deadlineKindsCombo);
			}
			@Override
			protected void addChildrenAfterName(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
				deadlineKindsCombo = addDeadlineKind(toolkit, standardLabelWidth, this);
				super.addChildrenAfterName(toolkit, parent, standardLabelWidth);
			}
		};
	}
	private void updateCombo(TimeEvent te,CCombo deadlineKindsCombo2){
		if(deadlineKindsCombo2.getItems().length != deadlineKinds.size()){
			deadlineKindsCombo2.removeAll();
			for(EnumerationLiteral l:deadlineKinds){
				deadlineKindsCombo2.add(l.getName());
			}
		}
		deadlineKindsCombo2.setText(getCurrentDeadlineKindName(te));
	}
	private String getCurrentDeadlineKindName(TimeEvent te){
		if(te == null){
			return "";
		}else if(!te.isStereotypeApplied(deadlineStereotype)){
			StereotypesHelper.applyStereotype(te, deadlineStereotype);
		}
		EnumerationLiteral enumerationLiteral = (EnumerationLiteral) te.getValue(deadlineStereotype, "kind");
		if(enumerationLiteral != null){
			return enumerationLiteral.getName();
		}else{
			return "";
		}
	}
	private CCombo addDeadlineKind(TabbedPropertySheetWidgetFactory toolkit,int standardLabelWidth,final AbsoluteTimeEventDetailsComposite composite){
		Control[] children = composite.getChildren();
		Label l = toolkit.createLabel(composite, "Deadline Type");
		FormData labelData = new FormData();
		labelData.top = new FormAttachment(children[children.length - 1]);
		l.setLayoutData(labelData);
		final CCombo deadlineKindsCombo = toolkit.createCCombo(composite, SWT.BORDER | SWT.READ_ONLY);
		FormData comboData = new FormData();
		comboData.height = 18;
		comboData.top = new FormAttachment(children[children.length - 1]);
		comboData.left = new FormAttachment(0, standardLabelWidth);
		comboData.right = new FormAttachment(100, 0);
		deadlineKindsCombo.setLayoutData(comboData);
		deadlineKindsCombo.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				EnumerationLiteral newValue = deadlineKinds.get(deadlineKindsCombo.getSelectionIndex());
				composite.getTimeEvent().setValue(deadlineStereotype, "kind", newValue);
				composite.maybeFire();
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		return deadlineKindsCombo;
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		Action action = (Action) getEObject();
		Profile p = ApplyProfileAction.applyProfile(action.getModel(), StereotypeNames.NAKEDUML_BPM_PROFILE);
		Enumeration kind = (Enumeration) p.getOwnedType("DeadlineKind");
		this.deadlineStereotype = p.getOwnedStereotype(StereotypeNames.DEADLINE);
		this.taskStereotype = p.getOwnedStereotype(action instanceof OpaqueAction ? StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK
				: StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK);
		this.deadlineKinds = kind.getOwnedLiterals();
		refresh();
	}
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		table.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(table, ITabbedPropertyConstants.VSPACE);
		groupDetails.setLayoutData(data);
	}
	public void refresh(){
		super.refresh();
		MixedEditDomain mixedEditDomain = (MixedEditDomain) getPart().getAdapter(MixedEditDomain.class);
		table.setMixedEditDomain(mixedEditDomain);
		table.setAction((Action) getEObject(), deadlineStereotype, this.taskStereotype);
		absoluteComposite.setContext(null, null);
		relativeComposite.setContext(null, null);
	}
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected String getLabelText(){
		return null;
	}
}