package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.opaeum.eclipse.uml.propertysections.core.ParameterComposite;
import org.opaeum.eclipse.uml.propertysections.core.ParametersTableComposite;

public abstract class AbstractParametersSection extends AbstractOpaeumPropertySection{
	private ParametersTableComposite parametersTableComposite;
	private ParameterComposite parameterDetailsComposite;
	private Group parameterDetailsGroup;
	protected void createWidgets(Composite composite){
		parametersTableComposite = new ParametersTableComposite(composite, SWT.NONE, getWidgetFactory(), getFeature()){
			public void updateSelectedParameter(Parameter newParameter){
				parameterDetailsComposite.setSelection(newParameter);
			}
		};
		parameterDetailsGroup = getWidgetFactory().createGroup(composite, "Parameter Details");
		parameterDetailsGroup.setLayout(new GridLayout());
		parameterDetailsComposite = new ParameterComposite(parameterDetailsGroup, SWT.NONE, getWidgetFactory());
	}
	@Override
	public Control getPrimaryInput(){
		throw new IllegalStateException();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		parametersTableComposite.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(parametersTableComposite, ITabbedPropertyConstants.VSPACE);
		parameterDetailsGroup.setLayoutData(data);
	}
	public void refresh(){
		super.refresh();
		EditingDomain mixedEditDomain = getEditingDomain();
		parametersTableComposite.setEditingDomain(mixedEditDomain);
		parametersTableComposite.setOwner((Element) getEObject());
		parameterDetailsComposite.setEditingDomain(mixedEditDomain);
		parameterDetailsComposite.setSelection(null);
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		parameterDetailsComposite.setEnabled(b);
		parametersTableComposite.setEnabled(b);
		
	}
}