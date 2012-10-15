package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.subsections.IDetailsSubsection;

public abstract class AbstractMasterDetailSection<T extends EObject> extends AbstractOpaeumPropertySection{
	private AbstractTableComposite<T> elementsTableComposite;
	private IDetailsSubsection<T> elementDetailsComposite;
	private Group elementDetailsGroup;
	private String detailsLabel;
	public AbstractMasterDetailSection(String detailsLabel){
		super();
		this.detailsLabel = detailsLabel;
	}
	@Override
	public String getLabelText(){
		return "";
	}
	protected void createWidgets(Composite composite){
		elementsTableComposite = createTable(composite);
		elementDetailsGroup = getWidgetFactory().createGroup(composite, detailsLabel);
		elementDetailsGroup.setLayout(new GridLayout());
		elementDetailsComposite = createDetails(elementDetailsGroup);
		elementDetailsComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		elementsTableComposite.addSelectionChangedListener(elementDetailsComposite);
	}
	protected abstract IDetailsSubsection<T> createDetails(Group group);
	protected abstract AbstractTableComposite<T> createTable(Composite composite);
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
		elementsTableComposite.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(elementsTableComposite, ITabbedPropertyConstants.VSPACE);
		elementDetailsGroup.setLayoutData(data);
	}
	@Override
	public void dispose(){
		super.dispose();
		if(elementDetailsComposite != null){
			elementDetailsComposite.dispose();
			elementsTableComposite.dispose();
		}
	}
	@SuppressWarnings("unchecked")
	public void populateControls(){
		if(elementDetailsComposite != null){
			EditingDomain mixedEditDomain = getEditingDomain();
			elementsTableComposite.setEditingDomain(mixedEditDomain);
			elementsTableComposite.setOwner((T) getSelectedObject());
			elementDetailsComposite.setEditingDomain(mixedEditDomain);
			elementDetailsComposite.selectionChanged(null);
		}
	}
	public void setEnabled(boolean b){
		super.setEnabled(b);
		if(elementDetailsComposite != null){
			elementDetailsComposite.setEnabled(b);
			elementsTableComposite.setEnabled(b);
		}
	}
}