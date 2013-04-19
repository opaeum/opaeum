package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.ocl.AutoCreateOpaqueExpressionComposite;

public abstract class OpaqueExpressionCellEditor extends CellEditor{
	private TabbedPropertySheetWidgetFactory toolkit;
	private AutoCreateOpaqueExpressionComposite oclBodyComposite;
	public OpaqueExpressionCellEditor(Composite parent,TabbedPropertySheetWidgetFactory toolkit){
		super();
		this.toolkit = toolkit;
		create(parent);
	}
	@Override
	protected Control createControl(Composite parent){
		 this.oclBodyComposite = new AutoCreateOpaqueExpressionComposite(parent, toolkit,SWT.NONE){
			@Override
			protected EditingDomain getEditingDomain(){
				return OpaqueExpressionCellEditor.this.getEditingDomain();
			}
			@Override
			public EReference getValueSpecificationFeature(){
				return OpaqueExpressionCellEditor.this.getValueSpecificationFeature();
			}
			@Override
			public EObject getValueSpecificatonOwner(){
				return OpaqueExpressionCellEditor.this.getValueSpecificatonOwner();
			}
		};
		return oclBodyComposite;
	}
	protected abstract EObject getValueSpecificatonOwner();
	protected abstract EReference getValueSpecificationFeature();
	@Override
	protected Object doGetValue(){
		return oclBodyComposite.getTextControl().getText();
	}
	public void setOclContext(NamedElement context, OpaqueExpression oe){
		oclBodyComposite.setOclContext(context, oe);
	}
	@Override
	protected void doSetFocus(){
		oclBodyComposite.getTextControl().setFocus();
	}
	@Override
	protected void doSetValue(Object value){
		oclBodyComposite.getTextControl().setText((String) value);
	}
	public EStructuralFeature getLanguagesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Language();
	}
	protected abstract EditingDomain getEditingDomain();
	public EStructuralFeature getBodiesFeature(){
		return UMLPackage.eINSTANCE.getOpaqueExpression_Body();
	}
}
