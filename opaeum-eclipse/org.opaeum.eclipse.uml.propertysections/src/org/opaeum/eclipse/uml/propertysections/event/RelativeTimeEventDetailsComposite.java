package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyOpaeumBpmProfileCommand;
import org.opaeum.eclipse.uml.propertysections.ocl.AutoCreateOpaqueExpressionComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class RelativeTimeEventDetailsComposite extends AbsoluteTimeEventDetailsComposite{
	private CLabel timeUnitLabel;
	private ComboViewer timeUnitCombo;
	private EEnum timeUnit;
	private Stereotype stereotype;
	private String stereotypeName;
	private OpaqueExpressionComposite businessCalendarToUseComposite;
	private CLabel businessCalendarToUseLabel;
	public RelativeTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		super(toolkit, parent, standardLabelWidth);
		this.stereotypeName = StereotypeNames.CONTEXTUAL_BUSINESS_TIME_EVENT;
	}
	public RelativeTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory widgetFactory,Composite details,int i,String stereotypeName){
		super(widgetFactory, details, i);
		this.stereotypeName = stereotypeName;
	}
	private void addOclComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		businessCalendarToUseLabel = toolkit.createCLabel(this, "Calendar to use");
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
		businessCalendarToUseLabel.setLayoutData(labelData);
		businessCalendarToUseComposite = new AutoCreateOpaqueExpressionComposite(this, toolkit){
			@Override
			protected EditingDomain getEditingDomain(){
				return editingDomain;
			}
			@Override
			public EReference getValueSpecificationFeature(){
				return (EReference) stereotype.getDefinition().getEStructuralFeature(TagNames.BUSINESS_CALENDAR_TO_USE);
			}
			@Override
			public void fireOclChanged(String value){
				super.oclBodyOwner = (NamedElement) event.getValue(stereotype, TagNames.BUSINESS_CALENDAR_TO_USE);
				super.fireOclChanged(value);
			}
		};
		expData.left = new FormAttachment(0, standardLabelWidth);
		expData.right = new FormAttachment(100, 0);
		expData.bottom = new FormAttachment(100, 0);
		expData.height = 50;
		businessCalendarToUseComposite.setBackground(getBackground());
		businessCalendarToUseComposite.setLayoutData(expData);
	}
	@Override
	protected TimeEvent findOrCreateTimeEvent(Trigger t){
		TimeEvent te = super.findOrCreateTimeEvent(t);
		return te;
	}
	@Override
	protected void addChildrenAfterOclComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		expressionLabel.setText("Number value");
		addOclComposite(toolkit, parent, standardLabelWidth);
	}
	@Override
	protected void addChildrenAfterName(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		setBackground(parent.getBackground());
		timeUnitLabel = toolkit.createCLabel(this, "Time unit of the delay");
		Control[] c = getChildren();
		FormData data = new FormData();
		data.top = new FormAttachment(c[c.length - 2], 4, 0);
		timeUnitLabel.setLayoutData(data);
		timeUnitCombo = new ComboViewer(toolkit.createCCombo(this, SWT.BORDER | SWT.FLAT));
		timeUnitCombo.setContentProvider(new ArrayContentProvider());
		timeUnitCombo.getCCombo().setBackground(getBackground());
		timeUnitCombo.addSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent e){
				if(event != null){// Selection event gets generate when the current timeEvent is cleared
					Object selected = ((IStructuredSelection) e.getSelection()).getFirstElement();
					EObject sa = event.getStereotypeApplication(stereotype);
					EStructuralFeature f = stereotype.getDefinition().getEStructuralFeature(TagNames.TIME_UNIT);
					if(selected != sa.eGet(f)){
						Command cmd = SetCommand.create(getEditingDomain(), sa, f, selected);
						getEditingDomain().getCommandStack().execute(cmd);
					}
				}
			}
		});
		data = new FormData();
		data.top = new FormAttachment(c[c.length - 2], 4, 0);
		data.left = new FormAttachment(0, standardLabelWidth);
		data.right = new FormAttachment(100, 0);
		data.height = 15;
		timeUnitCombo.getCCombo().setLayoutData(data);
		toolkit.adapt(timeUnitCombo.getCCombo(), true, true);
	}
	protected boolean isRelative(){
		return true;
	}
	public void setTrigger(Trigger t){
		super.setTrigger(t);
	}
	@Override
	protected void setTimeEvent(TimeEvent timeEvent){
		super.setTimeEvent(timeEvent);
		if(event != null){
			EObject sa = event.getStereotypeApplication(stereotype);
			timeUnitCombo.setSelection(new StructuredSelection(sa.eGet(sa.eClass().getEStructuralFeature(TagNames.TIME_UNIT))));
			businessCalendarToUseComposite.setOclContext(timeEvent,
					(OpaqueExpression) event.getValue(stereotype, TagNames.BUSINESS_CALENDAR_TO_USE));
		}else{
			businessCalendarToUseComposite.getTextControl().setText("");
			timeUnitCombo.setSelection(null);
		}
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		timeUnitCombo.getCCombo().setEnabled(b);
		businessCalendarToUseComposite.setEnabled(b);
	}
	protected void initProfileElements(Element e){
		if(e.eResource() != null){
			Profile p = ProfileApplier.getAppliedProfile(e.getModel(), StereotypeNames.OPAEUM_BPM_PROFILE);
			if(p != null){
				stereotype = (Stereotype) p.getOwnedType(stereotypeName);
			}
		}
		if(this.stereotype == null || this.timeUnit == null){
			ApplyOpaeumBpmProfileCommand cmd = new ApplyOpaeumBpmProfileCommand(getEditingDomain(), e.getModel());
			getEditingDomain().getCommandStack().execute(cmd);
			Profile p = cmd.getProfile();
			this.stereotype = (Stereotype) p.getOwnedType(stereotypeName);
		}
		EStructuralFeature f = (EStructuralFeature) stereotype.getDefinition().getEStructuralFeature(TagNames.TIME_UNIT);
		this.timeUnit = (EEnum) f.getEType();
		timeUnitCombo.setInput(this.timeUnit.getELiterals());
	}
}
