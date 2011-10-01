package org.opeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Pin;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class ActionPinsSection extends AbstractTabbedPropertySection{
	private PinTable table;
	private PinDetailsComposite details;
	private Group groupDetails;
	protected void createWidgets(Composite composite){
		table = new PinTable(composite, SWT.NONE){
			@Override
			protected void setSelectedPin(Pin firstElement){
				details.setPin(firstElement);
			}
		};
		groupDetails = getWidgetFactory().createGroup(composite, "Pin Details");
		groupDetails.setLayout(new GridLayout());
		details = new PinDetailsComposite(groupDetails, SWT.NONE, getWidgetFactory()){

			@Override
			public void pinUpdated(Pin pin){
				table.refreshPin(pin);
			}
			
		};
		details.setBackground(composite.getBackground());
		details.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		Action action = (Action) getEObject();
		table.setAction(action);
		table.refresh();
		details.setEditingDomain(getEditingDomain());
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
	}
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected String getLabelText(){
		return null;
	}
}
