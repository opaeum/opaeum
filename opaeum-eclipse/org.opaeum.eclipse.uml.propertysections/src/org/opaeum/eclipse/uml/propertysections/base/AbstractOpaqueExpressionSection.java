package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;

public abstract class AbstractOpaqueExpressionSection extends AbstractOclBodyBodySection{
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
	
	@Override
	protected OclBodyComposite createOclBodyComposite(Composite parent){
		return  new OpaqueExpressionComposite(parent, getWidgetFactory()){
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
	}
	/**
	 * Populate the valueSpecificationOwner late here if required
	 */
	protected abstract OpaqueExpression beforeOclChanged(String text);
	protected String getExpressionLabel(){
		return "Value expression";
	}
	@Override
	public void populateControls(){
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
	@Override
	protected void setOclContext(OclBodyComposite c){
		((OpaqueExpressionComposite)c).setOclContext(getOclContext(), getOpaqueExpression());
	}
}