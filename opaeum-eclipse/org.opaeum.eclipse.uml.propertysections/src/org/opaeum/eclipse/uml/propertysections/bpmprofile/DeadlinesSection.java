package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.uml.propertysections.event.AbsoluteTimeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.event.RelativeTimeEventDetailsComposite;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class DeadlinesSection extends AbstractTabbedPropertySection{
	private DeadlinesTableComposite table;
	private Composite details;
	private AbsoluteTimeEventDetailsComposite absoluteComposite;
	private RelativeTimeEventDetailsComposite relativeComposite;
	private Group groupDetails;
	private EList<EEnumLiteral> deadlineKinds;
	private Stereotype deadlineStereotype;
	private Stereotype stereotype;
	protected void createWidgets(Composite composite){
		table = new DeadlinesTableComposite(composite, SWT.NONE, getWidgetFactory()){
			public void updateSelectedDeadlines(TimeEvent event){
				if(event != null){
					if(event.isRelative()){
						relativeComposite.setContext(element, event);
						((StackLayout) details.getLayout()).topControl = relativeComposite;
					}else{
						absoluteComposite.setContext(element, event);
						((StackLayout) details.getLayout()).topControl = absoluteComposite;
					}
				}else{
					((StackLayout) details.getLayout()).topControl = null;
				}
				details.layout();
				
			}
		};
		groupDetails = getWidgetFactory().createGroup(composite, "Details of the Deadline");
		groupDetails.setLayout(new GridLayout());
		details = new Composite(groupDetails, SWT.NONE);
		details.setBackground(composite.getBackground());
		details.setLayoutData(new GridData(GridData.FILL_BOTH));
		details.setLayout(new StackLayout());
		absoluteComposite = new AbsoluteTimeEventDetailsComposite(getWidgetFactory(), details, 200){
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
			@Override
			public void setEnabled(boolean b){
				super.setEnabled(b);
				deadlineKindsCombo.setEnabled(b);
			}
		};
		relativeComposite = new RelativeTimeEventDetailsComposite(getWidgetFactory(), details, 200,  StereotypeNames.DEADLINE){
			CCombo deadlineKindsCombo;
			@Override
			public void setContext(NamedElement context,TimeEvent te){
				super.setContext(context, te);
				updateCombo(te, deadlineKindsCombo);
			}
			@Override
			public void setEnabled(boolean b){
				super.setEnabled(b);
				deadlineKindsCombo.setEnabled(b);
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
			for(EEnumLiteral l:deadlineKinds){
				deadlineKindsCombo2.add(l.getName());
			}
		}
		deadlineKindsCombo2.setText(getCurrentDeadlineKindName(te));
	}
	private String getCurrentDeadlineKindName(TimeEvent te){
		if(te == null || !te.isStereotypeApplied(deadlineStereotype)){
			return "";
		}
		EnumerationLiteral enumerationLiteral = (EnumerationLiteral) te.getValue(deadlineStereotype, TagNames.DEADLINE_KIND);
		if(enumerationLiteral != null){
			return enumerationLiteral.getName();
		}else{
			return "";
		}
	}
	private CCombo addDeadlineKind(TabbedPropertySheetWidgetFactory toolkit,int standardLabelWidth,
			final AbsoluteTimeEventDetailsComposite composite){
		Control[] children = composite.getChildren();
		CLabel l = toolkit.createCLabel(composite, "Deadline Type");
		FormData labelData = new FormData();
		labelData.top = new FormAttachment(children[children.length - 1]);
		l.setLayoutData(labelData);
		final CCombo deadlineKindsCombo = toolkit.createCCombo(composite, SWT.BORDER | SWT.FLAT);
		FormData comboData = new FormData();
		comboData.height = 15;
		comboData.top = new FormAttachment(children[children.length - 1]);
		comboData.left = new FormAttachment(0, standardLabelWidth);
		comboData.right = new FormAttachment(100, 0);
		deadlineKindsCombo.setLayoutData(comboData);
		deadlineKindsCombo.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				EEnumLiteral newValue = deadlineKinds.get(deadlineKindsCombo.getSelectionIndex());
				Command cmd = SetCommand.create(getEditingDomain(), composite.getTimeEvent().getStereotypeApplication(deadlineStereotype), deadlineStereotype.getDefinition().getEStructuralFeature(TagNames.DEADLINE_KIND), newValue);
				getEditingDomain().getCommandStack().execute(cmd);
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		return deadlineKindsCombo;
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		NamedElement element = (NamedElement) getEObject();
		Profile p = ProfileApplier.getAppliedProfile(element.getModel(), StereotypeNames.OPAEUM_BPM_PROFILE);
		this.deadlineStereotype = p.getOwnedStereotype(StereotypeNames.DEADLINE);
		for(Stereotype as:element.getAppliedStereotypes()){
			if(as.getDefinition().getEStructuralFeature(TagNames.DEADLINES)!=null){
				this.stereotype=as;
			}
		}
		EEnum kind = (EEnum) this.deadlineStereotype.getDefinition().getEStructuralFeature(TagNames.DEADLINE_KIND).getEType();
		this.deadlineKinds = kind.getELiterals();
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
		absoluteComposite.setEditingDomain(getEditingDomain());
		relativeComposite.setEditingDomain(getEditingDomain());
		table.setMixedEditDomain(getEditingDomain());
		table.setOwningElement((NamedElement) getEObject(), deadlineStereotype, this.stereotype);
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