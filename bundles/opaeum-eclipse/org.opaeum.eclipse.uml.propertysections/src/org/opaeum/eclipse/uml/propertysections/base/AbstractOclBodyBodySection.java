package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;

public abstract class AbstractOclBodyBodySection extends AbstractOpaeumPropertySection{
	protected OclBodyComposite oclComposite;
	protected abstract void setOclContext(OclBodyComposite c);
	protected abstract OclBodyComposite createOclBodyComposite(Composite parent);
	@Override
	public String getLabelText(){
		return "Body";
	
	}
	@Override
	public Control getPrimaryInput(){
		return oclComposite;
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		setOclContext(oclComposite);
	}
	public int getOclCompositeHeight(){
		return 30;
	}

	protected void createWidgets(Composite composite){
		oclComposite = createOclBodyComposite(composite);
		oclComposite.setBackground(composite.getBackground());
	}
	protected String getExpressionLabel(){
		return "Body expression";
	}
	protected void setSectionData(Composite composite){
		FormData fd = new FormData();
		fd.left = new FormAttachment(labelCombo);
		fd.top= new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.bottom = new FormAttachment(100,0);
		this.oclComposite.setLayoutData(fd);
	}
	@Override
	public void populateControls(){
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
