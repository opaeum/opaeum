package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.commands.ApplyOpaeumStandardProfileCommand;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class RelativeTimeEventDetailsComposite extends AbsoluteTimeEventDetailsComposite{
	private CLabel timeUnitLabel;
	private Combo timeUnitCombo;
	private Enumeration timeUnit;
	private Stereotype stereotype;
	private String stereotypeName;
	public RelativeTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		super(toolkit, parent, standardLabelWidth);
		this.stereotypeName = "RelativeTimeEvent";
	}
	public RelativeTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory widgetFactory,Composite details,int i,TimeEventListener listener,String stereotypeName){
		super(widgetFactory, details, i, listener);
		this.stereotypeName = stereotypeName;
	}
	@Override
	protected TimeEvent findOrCreateTimeEvent(Trigger t){
		TimeEvent te = super.findOrCreateTimeEvent(t);
		return te;
	}
	@Override
	protected void addChildrenAfterOclComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		expressionLabel.setText("Number value");
	}
	@Override
	protected void addChildrenAfterName(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		setBackground(parent.getBackground());
		timeUnitLabel = toolkit.createCLabel(this, "Time unit of the delay");
		Control[] c = getChildren();
		FormData data = new FormData();
		data.top = new FormAttachment(c[c.length - 2], 4, 0);
		timeUnitLabel.setLayoutData(data);
		timeUnitCombo = new Combo(this, SWT.BORDER|SWT.DROP_DOWN|SWT.READ_ONLY);
		timeUnitCombo.setBackground(getBackground());
		toolkit.adapt(timeUnitCombo);
		timeUnitCombo.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e){
				Combo s = (Combo) e.getSource();
				for(EnumerationLiteral el:timeUnit.getOwnedLiterals()){
					if(el.getName().equals(s.getText())){
						event.setValue(stereotype, "timeUnit", el);
						break;
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		data = new FormData();
		data.top = new FormAttachment(c[c.length - 2], 4, 0);
		data.left = new FormAttachment(0, standardLabelWidth);
		data.right = new FormAttachment(100, 0);
		timeUnitCombo.setLayoutData(data);
		toolkit.adapt(timeUnitCombo , true, true);
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
			if(!event.isStereotypeApplied(stereotype)){
				StereotypesHelper.applyStereotype(event, stereotype);
			}
			EnumerationLiteral currentTImeUnitValue = (EnumerationLiteral) event.getValue(stereotype, "timeUnit");
			timeUnitCombo.setText(currentTImeUnitValue.getName());
		}else{
			timeUnitCombo.setText("");
		}
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		timeUnitCombo.setEnabled(b);
	}
	protected void initProfileElements(Element e){
		if(e.eResource() != null){
			for(Resource resource:e.eResource().getResourceSet().getResources()){
				if(resource.getURI().toString().contains(StereotypeNames.OPAEUM_BPM_PROFILE)){
					Profile p = (Profile) resource.getContents().get(0);
					stereotype = (Stereotype) p.getOwnedType(stereotypeName);
					timeUnit = (Enumeration) p.getOwnedType("BusinessTimeUnit");
					break;
				}
			}
		}
		if(this.stereotype == null || this.timeUnit == null){
			ApplyOpaeumStandardProfileCommand cmd = new ApplyOpaeumStandardProfileCommand(getEditingDomain(), e.getModel());
			getEditingDomain().getCommandStack().execute(cmd);
			Profile p = cmd.getProfile();
			this.stereotype = (Stereotype) p.getOwnedType(stereotypeName);
			this.timeUnit = (Enumeration) p.getOwnedType("TimeUnit");
		}
		List<String> result = new ArrayList<String>();
		for(EnumerationLiteral l:timeUnit.getOwnedLiterals()){
			result.add(l.getName());
		}
		timeUnitCombo.setItems((String[]) result.toArray(new String[result.size()]));
		timeUnitCombo.layout();
	}
}
