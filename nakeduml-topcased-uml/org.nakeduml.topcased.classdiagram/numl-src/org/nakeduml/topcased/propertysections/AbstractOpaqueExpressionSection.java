package org.nakeduml.topcased.propertysections;

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
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;
import org.nakeduml.topcased.propertysections.ocl.OpaqueExpressionBodyComposite;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractOpaqueExpressionSection extends AbstractTabbedPropertySection{
	protected OpaqueExpressionBodyComposite oclComposite;
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
		oclComposite.setOclContext(getOclContext(), getValueSpecificationOwner(), getOpaqueExpression());
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
		oclComposite = new OpaqueExpressionBodyComposite(composite, getWidgetFactory()){
			@Override
			public EReference getValueSpecificationFeature(){
				return AbstractOpaqueExpressionSection.this.getValueSpecificationFeature();
			}
			@Override
			protected void fireOclChanged(String text){
				super.valueSpecificationOwner = beforeOclChanged(text);
				if(valueSpecificationOwner != null){
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
	protected NamedElement beforeOclChanged(String text){
		return getValueSpecificationOwner();
	}
	protected String getExpressionLabel(){
		return "Value expression";
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