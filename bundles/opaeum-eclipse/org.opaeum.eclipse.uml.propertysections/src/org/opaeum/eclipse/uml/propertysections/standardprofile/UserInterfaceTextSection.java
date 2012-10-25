package org.opaeum.eclipse.uml.propertysections.standardprofile;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
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
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;

public class UserInterfaceTextSection extends AbstractOpaeumPropertySection{
	private UserTextTableComposite userInterfaceTextTableComposite;
	private Group parameterDetailsGroup;
	protected void createWidgets(Composite composite){
		userInterfaceTextTableComposite = new UserTextTableComposite(composite, SWT.NONE, getWidgetFactory());
		parameterDetailsGroup = getWidgetFactory().createGroup(composite, "User text");
		parameterDetailsGroup.setLayout(new GridLayout());
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
		userInterfaceTextTableComposite.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(userInterfaceTextTableComposite, ITabbedPropertyConstants.VSPACE);
		parameterDetailsGroup.setLayoutData(data);
	}
	@Override
	public void populateControls(){
		super.populateControls();

		EditingDomain mixedEditDomain = getEditingDomain();
		userInterfaceTextTableComposite.setEditingDomain(mixedEditDomain);
		userInterfaceTextTableComposite.setOwner((Element) getSelectedObject());
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		userInterfaceTextTableComposite.setEnabled(b);
	}
	@Override
	protected EStructuralFeature getFeature(){
		return EcorePackage.eINSTANCE.getEAnnotation_Details();
	}
	@Override
	public String getLabelText(){
		return "";
	}
}
