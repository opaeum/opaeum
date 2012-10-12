package org.opaeum.eclipse.uml.propertysections.sql;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;

public class OpaqueActionSqlSection extends AbstractOpaeumPropertySection{
	protected SqlBodyComposite oclComposite;
	@Override
	public String getLabelText(){
		return "SQL";
	}
	@Override
	public Control getPrimaryInput(){
		throw new IllegalStateException();
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		oclComposite.setOclContextImpl((NamedElement)getEObject(), (NamedElement) getEObject());
	}
	protected void createWidgets(Composite composite){
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
