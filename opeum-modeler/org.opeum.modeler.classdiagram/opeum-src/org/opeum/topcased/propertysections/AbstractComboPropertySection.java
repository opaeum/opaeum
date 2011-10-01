package org.opeum.topcased.propertysections;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractComboPropertySection extends AbstractTabbedPropertySection{
	private CCombo combo;
	private List<? extends EObject> comboValues;
	protected abstract List<? extends EObject> getComboValues();
	protected abstract String getFeatureAsText();
	protected abstract Object getOldFeatureValue();
	@Override
	protected void createWidgets(Composite composite){
		combo = getWidgetFactory().createCCombo(composite, SWT.FLAT | SWT.READ_ONLY | SWT.BORDER);
		if(getFeature() != null){
			boolean isChangeable = getFeature().isChangeable();
			combo.setEditable(false);
			combo.setEnabled(isChangeable);
		}
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{
			getLabelText()
		}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		combo.setLayoutData(data);
		CLabel nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(combo, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(combo, 0, SWT.CENTER);
		nameLabel.setLayoutData(data);
	}
	@Override
	protected void hookListeners(){
		combo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent event){
				handleComboModified();
			}
		});
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected void handleComboModified(){
		int index = combo.getSelectionIndex();
		createCommand(getOldFeatureValue(), comboValues.get(index));
	}
	@Override
	public void refresh(){
		super.refresh();
		ILabelProvider lp = getLabelProvider();
		combo.removeAll();
		for(EObject eObject:comboValues){
			String text = lp.getText(eObject);
			combo.add(text);
		}
		if(getOldFeatureValue() != null){
			combo.setText(lp.getText(getOldFeatureValue()));
		}
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(combo != null){
			combo.setEnabled(enabled);
		}
	}
	protected CCombo getCombo(){
		return combo;
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		this.comboValues = getComboValues();
	}
}