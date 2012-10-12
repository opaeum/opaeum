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
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.commands.SetOclBodyCommand;

public class TimeEventWhenExprEditingSupport extends EditingDomainEditingSupport{
	private TabbedPropertySheetWidgetFactory toolkit;
	private OpaqueExpressionCellEditor editor;
	public TimeEventWhenExprEditingSupport(ColumnViewer viewer,TabbedPropertySheetWidgetFactory toolkit){
		super(viewer, "When", 300);
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
					return UMLPackage.eINSTANCE.getTimeExpression_Expr();
				}
			};
		}
		TimeEvent c = (TimeEvent) element;
		editor.setOclContext(c, c.getWhen().getExpr() instanceof OpaqueExpression? (OpaqueExpression) c.getWhen().getExpr():null);
		return editor;
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		TimeEvent c = (TimeEvent) element;
		if(c.getWhen()!=null && c.getWhen().getExpr() instanceof OpaqueExpression){
			OpaqueExpression oe = (OpaqueExpression) c.getWhen().getExpr();
			return EmfValueSpecificationUtil.getOclBody(oe.getBodies(), oe.getLanguages());
		}
		return "";
	}
	@Override
	protected void setValue(Object element,Object value){
		TimeEvent c = (TimeEvent) element;
		if(c.getWhen()==null){
			//TODO createwhen
		}
		if(!(c.getWhen().getExpr() instanceof OpaqueExpression)){
			OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
			oe.setName(c.getName() + "Specification");
			editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, c, UMLPackage.eINSTANCE.getTimeExpression_Expr(), oe));
		}
		EAttribute bodies = UMLPackage.eINSTANCE.getOpaqueExpression_Body();
		EAttribute languages = UMLPackage.eINSTANCE.getOpaqueExpression_Language();
		editingDomain.getCommandStack().execute(new SetOclBodyCommand(editingDomain, c.getWhen().getExpr(), bodies, languages, (String) value));
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				TimeEvent timeEvent = (TimeEvent) cell.getElement();
				if(timeEvent.getWhen()!=null && timeEvent.getWhen().getExpr() instanceof OpaqueExpression){
					OpaqueExpression expr = (OpaqueExpression) timeEvent.getWhen().getExpr();
					cell.setText(EmfValueSpecificationUtil.getOclBody(expr));
				}else{
					cell.setText("");
				}
			}
		};
	}
}
