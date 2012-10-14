package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.uml.propertysections.ocl.AutoCreateOpaqueExpressionComposite;

public abstract class AbstractAutoCreatingOclExpressionSection extends AbstractOpaqueExpressionSection{
	@Override
	protected final OpaqueExpression beforeOclChanged(String text){
		throw new IllegalStateException();
	}
	@Override
	protected void createWidgets(Composite composite){
		labelCombo = getWidgetFactory().createCLabel(composite, getLabelText());
		oclComposite = new AutoCreateOpaqueExpressionComposite(composite, getWidgetFactory()){
			@Override
			public EReference getValueSpecificationFeature(){
				return AbstractAutoCreatingOclExpressionSection.this.getValueSpecificationFeature();
			}
			@Override
			protected void fireOclChanged(String text){
				super.valueSpecificationOwner=getValueSpecificationOwner();
				super.fireOclChanged(text);
			}
			@Override
			protected EditingDomain getEditingDomain(){
				return AbstractAutoCreatingOclExpressionSection.this.getEditingDomain();
			}
		};
		oclComposite.setBackground(composite.getBackground());
	}
}
