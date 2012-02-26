package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.topcased.propertysections.ocl.OclBodyComposite;
import org.opaeum.topcased.propertysections.ocl.OpaqueExpressionComposite;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractOpaqueExpressionSection extends AbstractTabbedPropertySection{
	protected OpaqueExpressionComposite oclComposite;
	protected CLabel label;
	public AbstractOpaqueExpressionSection(){
		super();
	}
	protected abstract NamedElement getValueSpecificationOwner();
	protected abstract EReference getValueSpecificationFeature();
	/**
	 * Must NEVER return null
	 * 
	 * @return
	 */
	protected NamedElement getOclContext(){
		return getValueSpecificationOwner();
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	private OpaqueExpression getOpaqueExpression(){
		ValueSpecification vs = getValueSpecification();
		return (OpaqueExpression) (vs instanceof OpaqueExpression ? vs : null);
	}
	private ValueSpecification getValueSpecification(){
		NamedElement owner = getValueSpecificationOwner();
		if(owner == null){
			return null;
		}else{
			return (ValueSpecification) owner.eGet(getValueSpecificationFeature());
		}
	}
	protected void createWidgets(Composite composite){
		label = getWidgetFactory().createCLabel(composite, getLabelText());
		oclComposite = new OpaqueExpressionComposite(composite, getWidgetFactory()){
			@Override
			protected void fireOclChanged(String text){
				oclBodyOwner = beforeOclChanged(text);
				if(oclBodyOwner == null){
					oclComposite.getTextControl().setText(OclBodyComposite.DEFAULT_TEXT);
				}else{
					super.fireOclChanged(text);
				}
			}
			@Override
			protected EditingDomain getEditingDomain(){
				return AbstractOpaqueExpressionSection.this.getEditingDomain();
			}
		};
		oclComposite.setBackground(composite.getBackground());
	}
	/**
	 * Populate the valueSpecificationOwner late here if required
	 */
	protected abstract OpaqueExpression beforeOclChanged(String text);
	protected String getExpressionLabel(){
		return "Value expression";
	}
	protected void setSectionData(Composite composite){
		FormData labelFd = new FormData();
		labelFd.left = new FormAttachment(0, 0);
		this.label.setLayoutData(labelFd);
		FormData fd = new FormData(400, getOclCompositeHeight());
		fd.right = new FormAttachment(100, 0);
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{
			getLabelText()
		}));
		this.oclComposite.setLayoutData(fd);
	}
	public int getOclCompositeHeight(){
		return 30;
	}
	public void refresh(){
		super.refresh();
		oclComposite.setOclContext(getOclContext(), getOpaqueExpression());
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		oclComposite.setEnabled(enabled);
	}
	@Override
	protected final EStructuralFeature getFeature(){
		return getValueSpecificationFeature();
	}
}