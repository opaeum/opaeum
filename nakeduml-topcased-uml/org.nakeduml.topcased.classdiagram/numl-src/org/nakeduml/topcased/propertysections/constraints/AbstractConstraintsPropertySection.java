package org.nakeduml.topcased.propertysections.constraints;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractConstraintsPropertySection extends AbstractTabbedPropertySection{
	private OclOwnedRuleComposite composite = null;
	@Override
	protected void createWidgets(Composite parent){
		super.createWidgets(parent);
		this.composite = new OclOwnedRuleComposite(getWidgetFactory(), parent, getFeature());
	}
	@Override
	protected void setSectionData(Composite p){
		FormData fd = new FormData();
		fd.width=p.getSize().x;
		fd.left = new FormAttachment(0);
//		fd.right = new FormAttachment(100, 100,0);
		composite.setLayoutData(fd);
		super.setSectionData(p);
	}
	@Override
	public void refresh(){
		super.refresh();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		if(getEObject() != null){
			composite.setContext((Element) getEObject());
			composite.setEditDomain(getEditingDomain());
		}
	}
	@Override
	protected String getLabelText(){
		return "";
	}
}
