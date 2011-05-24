package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class RelativeTimeEventDetailsComposite extends AbsoluteTimeEventDetailsComposite{
	private CLabel timeUnitLabel;
	private CCombo timeUnitCombo;
	private Enumeration timeUnit;
	private Stereotype stereotype;
	public RelativeTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		super(toolkit, parent, standardLabelWidth);
	}
	@Override
	protected TimeEvent findOrCreateTimeEvent(EditingDomain domain,Trigger t){
		TimeEvent te = super.findOrCreateTimeEvent(domain, t);
		return te;
	}
	protected void addChildren(TabbedPropertySheetWidgetFactory toolkit,Composite parent,int standardLabelWidth){
		setBackground(parent.getBackground());
		timeUnitLabel = toolkit.createCLabel(this, "Time unit of the delay");
		FormData data = new FormData();
		data.top = new FormAttachment(0, 0);
		timeUnitLabel.setLayoutData(data);
		timeUnitCombo = toolkit.createCCombo(this);
		timeUnitCombo.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				CCombo s = (CCombo) e.getSource();
				for(EnumerationLiteral el:timeUnit.getOwnedLiterals()){
					if(el.getName().equals(s.getText())){
						trigger.getEvent().setValue(stereotype, "timeUnit", el);
						break;
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.left = new FormAttachment(0, standardLabelWidth);
		data.height = 18;
		timeUnitCombo.setLayoutData(data);
		TextChangeListener listener = new TextChangeListener(){
			public void textChanged(Control control){
				handleTextModified();
			}
			public void focusIn(Control control){
			}
			public void focusOut(Control control){
			}
		};
		super.addChildren(toolkit, parent, standardLabelWidth);
		expressionLabel.setText("Number value");
	}
	protected boolean isRelative(){
		return true;
	}

	public void setTrigger(EditingDomain domain,Trigger t){
		if(t.eResource() != null){
			super.setTrigger(domain, t);
			initProfileElements(t);
			if(!trigger.getEvent().isStereotypeApplied(stereotype)){
				trigger.getEvent().applyStereotype(stereotype);
			}
			EnumerationLiteral currentTImeUnitValue = (EnumerationLiteral) trigger.getEvent().getValue(stereotype, "timeUnit");
			timeUnitCombo.setText(currentTImeUnitValue.getName());
		}
	}
	private void initProfileElements(Trigger t){
		Resource eResource = t.eResource();
		if((timeUnit == null || stereotype == null) && eResource != null){
			for(Resource r:eResource.getResourceSet().getResources()){
				if(r.getURI().toString().contains("NakedUMLProfile")){
					Profile p = (Profile) r.getContents().get(0);
					this.timeUnit = (Enumeration) p.getOwnedType("TimeUnit");
					this.stereotype = (Stereotype) p.getOwnedType("RelativeTimeEvent");
					List<String> result = new ArrayList<String>();
					for(EnumerationLiteral l:timeUnit.getOwnedLiterals()){
						result.add(l.getName());
					}
					timeUnitCombo.setItems((String[]) result.toArray(new String[result.size()]));
					break;
				}
			}
		}
	}
}
