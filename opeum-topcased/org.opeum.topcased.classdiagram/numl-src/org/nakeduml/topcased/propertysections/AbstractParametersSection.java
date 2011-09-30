package org.opeum.topcased.propertysections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractParametersSection extends AbstractTabbedPropertySection{
	private ParametersTableComposite parametersTableComposite;
	private ParameterComposite parameterDetailsComposite;
	private Group parameterDetailsGroup;
	protected void createWidgets(Composite composite){
		parametersTableComposite = new ParametersTableComposite(composite, SWT.NONE, getWidgetFactory(), getFeature()){
			public void updateSelectedParameter(Parameter newParameter){
				parameterDetailsComposite.setParameter(newParameter);
				refresh();
			}
		};
		parameterDetailsGroup = getWidgetFactory().createGroup(composite, "Parameter Details");
		parameterDetailsGroup.setLayout(new GridLayout());
		parameterDetailsComposite = new ParameterComposite(parameterDetailsGroup, SWT.NONE, getWidgetFactory());
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
		MixedEditDomain mixedEditDomain = (MixedEditDomain) getPart().getAdapter(MixedEditDomain.class);
		parametersTableComposite.setMixedEditDomain(mixedEditDomain);
		parametersTableComposite.setOwner((Element) getEObject());
		parameterDetailsComposite.setMixedEditDomain(mixedEditDomain);
		parameterDetailsComposite.setParameter(null);
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		parameterDetailsComposite.setEnabled(b);
		parametersTableComposite.setEnabled(b);
		
	}
}