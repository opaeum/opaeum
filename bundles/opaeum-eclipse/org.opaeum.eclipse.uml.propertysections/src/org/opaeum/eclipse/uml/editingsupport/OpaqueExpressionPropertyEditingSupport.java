package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.EmfValueSpecificationUtil;

public class OpaqueExpressionPropertyEditingSupport extends EditingDomainEditingSupport{
	private TabbedPropertySheetWidgetFactory toolkit;
	private OpaqueExpressionCellEditor editor;
	private EReference valueSpecificationFeature;
	public OpaqueExpressionPropertyEditingSupport(ColumnViewer viewer,TabbedPropertySheetWidgetFactory toolkit, EReference valueSpecificationFeature){
		super(viewer,"Constraint", 300);
		this.toolkit = toolkit;
		this.valueSpecificationFeature=valueSpecificationFeature;
	}
	@Override
	protected CellEditor getCellEditor(final Object element){
		if(this.editor == null){
			this.editor = new OpaqueExpressionCellEditor((Composite) super.viewer.getControl(), toolkit){
				@Override
				protected EditingDomain getEditingDomain(){
					return editingDomain;
				}
				@Override
				protected EReference getValueSpecificationFeature(){
					return OpaqueExpressionPropertyEditingSupport.this.getValueSpecificationFeature();
				}
				@Override
				public void deactivate(){
					super.deactivate();
					OpaqueExpressionPropertyEditingSupport.this.deactivated();
				}
				@Override
				public void activate(){
					super.activate();
					OpaqueExpressionPropertyEditingSupport.this.activated();
				}
				@Override
				protected EObject getValueSpecificatonOwner(){
					return (EObject) element;
				}
			};
		}
		NamedElement c = (NamedElement) element;
		editor.setOclContext(c, c.eGet(getValueSpecificationFeature()) instanceof OpaqueExpression? (OpaqueExpression) c.eGet(getValueSpecificationFeature()):null);
		editor.addListener(this); 
		
		return editor;
	}
	@Override
	protected boolean canEdit(Object element){
		return super.canEdit && element instanceof EObject && getValueSpecificationFeature().getEContainingClass().isInstance(element);
	}
	@Override
	protected Object getValue(Object element){
		NamedElement c = (NamedElement) element;
		if(c.eGet(getValueSpecificationFeature()) instanceof OpaqueExpression){
			OpaqueExpression oe = (OpaqueExpression) c.eGet(getValueSpecificationFeature());
			return EmfValueSpecificationUtil.getOclBody(oe.getBodies(), oe.getLanguages());
		}
		return "";
	}
	@Override
	protected void setValue(Object element,Object value){
//		if(!(((Constraint) element).getSpecification() instanceof OpaqueExpression)){
//			OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
//			oe.setName(((Constraint) element).getName() + "Specification");
//			editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, OpaqueExpressionPropertyEditingSupport.this.getValueSpecificationFeature(), oe));
//		}
//		EAttribute bodies = UMLPackage.eINSTANCE.getOpaqueExpression_Body();
//		EAttribute languages = UMLPackage.eINSTANCE.getOpaqueExpression_Language();
//		editingDomain.getCommandStack().execute(new SetOclBodyCommand(editingDomain, ((Constraint) element).getSpecification(), bodies, languages, (String) value));
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				NamedElement constraint = (NamedElement) cell.getElement();
				if(getValueSpecificationFeature().getContainerClass().isInstance(constraint) && constraint.eGet(getValueSpecificationFeature()) instanceof OpaqueExpression){
					OpaqueExpression expr = (OpaqueExpression) constraint.eGet(getValueSpecificationFeature());
					cell.setText(EmfValueSpecificationUtil.getOclBody(expr));
				}
			}
		};
	}
	protected EReference getValueSpecificationFeature(){
		return valueSpecificationFeature;
	}
}
