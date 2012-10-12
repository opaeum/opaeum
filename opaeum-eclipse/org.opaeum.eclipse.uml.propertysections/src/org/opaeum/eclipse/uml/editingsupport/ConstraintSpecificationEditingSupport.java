package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.commands.SetOclBodyCommand;

public class ConstraintSpecificationEditingSupport extends EditingDomainEditingSupport{
	private TabbedPropertySheetWidgetFactory toolkit;
	private OpaqueExpressionCellEditor editor;
	public ConstraintSpecificationEditingSupport(ColumnViewer viewer,TabbedPropertySheetWidgetFactory toolkit){
		super(viewer,"Constraint", 300);
		this.toolkit = toolkit;
	}
	@Override
	protected CellEditor getCellEditor(Object element){
		if(this.editor == null){
			this.editor = new OpaqueExpressionCellEditor((Composite) super.viewer.getControl(), toolkit){
				@Override
				protected EditingDomain getEditingDomain(){
					return editingDomain;
				}
				@Override
				protected EReference getValueSpecificationFeature(){
					return UMLPackage.eINSTANCE.getConstraint_Specification();
				}
			};
		}
		Constraint c = (Constraint) element;
		editor.setOclContext(c, c.getSpecification() instanceof OpaqueExpression? (OpaqueExpression) c.getSpecification():null);
		return editor;
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		Constraint c = (Constraint) element;
		if(c.getSpecification() instanceof OpaqueExpression){
			OpaqueExpression oe = (OpaqueExpression) c.getSpecification();
			return EmfValueSpecificationUtil.getOclBody(oe.getBodies(), oe.getLanguages());
		}
		return "";
	}
	@Override
	protected void setValue(Object element,Object value){
		Constraint c = (Constraint) element;
		if(!(c.getSpecification() instanceof OpaqueExpression)){
			OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
			oe.setName(c.getName() + "Specification");
			editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, c, UMLPackage.eINSTANCE.getConstraint_Specification(), oe));
		}
		EAttribute bodies = UMLPackage.eINSTANCE.getOpaqueExpression_Body();
		EAttribute languages = UMLPackage.eINSTANCE.getOpaqueExpression_Language();
		editingDomain.getCommandStack().execute(new SetOclBodyCommand(editingDomain, c.getSpecification(), bodies, languages, (String) value));
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				Constraint timeEvent = (Constraint) cell.getElement();
				if(timeEvent.getSpecification() instanceof OpaqueExpression){
					OpaqueExpression expr = (OpaqueExpression) timeEvent.getSpecification();
					cell.setText(EmfValueSpecificationUtil.getOclBody(expr));
				}
			}
		};
	}
}
