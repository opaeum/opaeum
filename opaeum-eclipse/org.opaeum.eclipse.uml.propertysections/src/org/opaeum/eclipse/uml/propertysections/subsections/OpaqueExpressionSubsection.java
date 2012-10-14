package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.ocl.AutoCreateOpaqueExpressionComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;

public class OpaqueExpressionSubsection extends AbstractTabbedPropertySubsection<OpaqueExpressionComposite,OpaqueExpression>{
	boolean isAutoCreate = false;
	public OpaqueExpressionSubsection(IMultiPropertySection section){
		super(section);
	}
	private OpaqueExpressionComposite oclComposite;
	@Override
	protected OpaqueExpression getNewValue(){
		// Won't be called
		return UMLFactory.eINSTANCE.createOpaqueExpression();
	}
	@Override
	protected int getModelSubscriptionLevel(){
		return 4;
	}
	@Override
	protected void populateControls(){
		getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		getControl().setOclContext((NamedElement) section.getFeatureOwner(section.getEObject()), getCurrentValue());
	}
	@Override
	public void hookControlListener(){
		// oclComposite does this itself
	}
	@Override
	protected OpaqueExpressionComposite createControl(Composite parent){
		if(isAutoCreate){
			return new AutoCreateOpaqueExpressionComposite(parent, getWidgetFactory()){
				@Override
				public EReference getValueSpecificationFeature(){
					return (EReference) getFeature();
				}
				@Override
				protected EditingDomain getEditingDomain(){
					return section.getEditingDomain();
				}
			};
		}else{
			return new OpaqueExpressionComposite(parent, getWidgetFactory()){
				@Override
				protected EditingDomain getEditingDomain(){
					return section.getEditingDomain();
				}
			};
		}
	}
}
