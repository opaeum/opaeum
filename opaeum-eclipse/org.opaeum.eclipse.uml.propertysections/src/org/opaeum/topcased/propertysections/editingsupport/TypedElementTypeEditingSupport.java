package org.opaeum.topcased.propertysections.editingsupport;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.TypedElementTypeSection;

public class TypedElementTypeEditingSupport extends EditingDomainEditingSupport{
	private TabbedPropertySheetWidgetFactory toolkit;
	public TypedElementTypeEditingSupport(ColumnViewer viewer,TabbedPropertySheetWidgetFactory toolkit){
		super(viewer);
		this.toolkit = toolkit;
	}
	@Override
	protected CellEditor getCellEditor(final Object element){
		return new ObjectChooserCompositeCellEditor(((TableViewer) viewer).getTable(), toolkit){
			@Override
			protected Object[] getChoices(){
				return TypedElementTypeSection.getValidTypes((EObject) element);
			}
		};
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		return ((TypedElement) element).getType();
	}
	@Override
	protected void setValue(Object element,Object value){
		Command cmd = SetCommand.create(editingDomain, element, UMLPackage.eINSTANCE.getTypedElement_Type(), value);
		editingDomain.getCommandStack().execute(cmd);
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				Type type = ((TypedElement) cell.getElement()).getType();
				if(type == null){
					cell.setText(null);
				}else{
					cell.setText(type.getName());
				}
			}
		};
	}
}
