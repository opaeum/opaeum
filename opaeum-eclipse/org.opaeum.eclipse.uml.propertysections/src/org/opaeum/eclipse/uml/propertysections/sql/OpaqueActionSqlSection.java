package org.opaeum.eclipse.uml.propertysections.sql;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class OpaqueActionSqlSection extends AbstractTabbedPropertySection{
	protected SqlBodyComposite oclComposite;
	protected CLabel label;
	@Override
	protected String getLabelText(){
		return "SQL";
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		oclComposite.setOclContextImpl((NamedElement)getEObject(), (NamedElement) getEObject());
	}
	protected void createWidgets(Composite composite){
		label = getWidgetFactory().createCLabel(composite, getLabelText());
		oclComposite = new SqlBodyComposite(composite,getWidgetFactory(),SWT.BORDER|SWT.MULTI){
			@Override
			public EStructuralFeature getLanguagesFeature(){
				return UMLPackage.eINSTANCE.getOpaqueAction_Language();
			}
			@Override
			protected EditingDomain getEditingDomain(){
				return OpaqueActionSqlSection.this.getEditingDomain();
			}
			@Override
			public EStructuralFeature getBodiesFeature(){
				return UMLPackage.eINSTANCE.getOpaqueAction_Body();
			}
		};
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
		fd.bottom= new FormAttachment(100, 0);
		fd.top= new FormAttachment(0, 0);
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		fd.height = 50;
		this.oclComposite.setLayoutData(fd);
	}
	public void refresh(){
		super.refresh();
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(oclComposite.getTextControl() != null){
			oclComposite.getTextControl().setEnabled(true);
		}
	}
	public boolean shouldUseExtraSpace() {
		return true;
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
}
