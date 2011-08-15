package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractOclBodyBodySection extends AbstractTabbedPropertySection{
	protected OclBodyComposite oclComposite;
	protected CLabel label;
	protected abstract void setOclContext(OclBodyComposite c);
	protected abstract OclBodyComposite createOclBodyComposite(Composite parent);
	@Override
	protected String getLabelText(){
		return "Body";
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		setOclContext(oclComposite);
	}
	protected void createWidgets(Composite composite){
		label = getWidgetFactory().createCLabel(composite, getLabelText());
		oclComposite = createOclBodyComposite(composite);
		oclComposite.setBackground(composite.getBackground());
	}
	protected String getExpressionLabel(){
		return "Body expression";
	}
	protected void setSectionData(Composite composite){
		FormData labelFd = new FormData();
		labelFd.left = new FormAttachment(0, 0);
		this.label.setLayoutData(labelFd);
		FormData fd = new FormData(400, 400);
		fd.right = new FormAttachment(100, 0);
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{
			getLabelText()
		}));
		fd.height = 50;
		this.oclComposite.setLayoutData(fd);
	}
	public void refresh(){
		super.refresh();
		setOclContext(oclComposite);
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(oclComposite.getTextControl() != null){
			oclComposite.getTextControl().setEnabled(enabled);
		}
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
}
