package org.nakeduml.topcased.propertysections.constraints;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractConstraintsPropertySection extends AbstractTabbedPropertySection{
	private OclConstraintTable table;
	private OclConstraintDetailsComposite details;
	private Group groupDetails;
	protected void createWidgets(Composite composite){
		table = new OclConstraintTable(getWidgetFactory(), composite, getFeature()){
			@Override
			public void setSelectedConstraint(Constraint theConstraint){
				super.setSelectedConstraint(theConstraint);
				details.setSelectedConstraint(theConstraint);
			}
		};
		groupDetails = getWidgetFactory().createGroup(composite, "Constraint Details");
		groupDetails.setLayout(new GridLayout());
		details = new OclConstraintDetailsComposite(getWidgetFactory(), groupDetails, getFeature()){
			@Override
			public void constraintUpdated(Constraint a){
				table.refreshConstraint(a);
			}
		};
		details.setBackground(composite.getBackground());
		details.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		table.setContext((Element) getEObject());
		details.setContext((Element) getEObject());
		refresh();
		details.setEditDomain(getEditingDomain());
		table.setEditDomain(getEditingDomain());
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
		super.setSectionData(composite);
	}
	public void refresh(){
		super.refresh();
	}
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected String getLabelText(){
		return null;
	}
}
