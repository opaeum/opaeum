package org.opaeum.eclipse.uml.editingsupport;

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
import org.opaeum.eclipse.uml.propertysections.OpaeumLabelProvider;
import org.opaeum.eclipse.uml.propertysections.core.TypedElementTypeSection;

public class TypedElementTypeEditingSupport extends EditingDomainEditingSupport{
	protected OpaeumLabelProvider labelProvider = new OpaeumLabelProvider();
	private TabbedPropertySheetWidgetFactory toolkit;
	public TypedElementTypeEditingSupport(ColumnViewer viewer,TabbedPropertySheetWidgetFactory toolkit){
		super(viewer,"Type",200);
		this.toolkit = toolkit;
	}
	@Override
	protected CellEditor getCellEditor(final Object element){
		ObjectChooserCompositeCellEditor cellEditor = new ObjectChooserCompositeCellEditor(((TableViewer) viewer).getTable(), toolkit){
			@Override
			protected Object[] getChoices(){
				return TypedElementTypeSection.getValidTypes((EObject) element);
			}
		};
		cellEditor.addListener(this);
		return cellEditor;
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
		TypedElement te = (TypedElement) element;
		if(te.getType() == null || !te.getType().equals(value)){
			Command cmd = SetCommand.create(editingDomain, element, UMLPackage.eINSTANCE.getTypedElement_Type(), value);
			editingDomain.getCommandStack().execute(cmd);
		}
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				Type type = ((TypedElement) cell.getElement()).getType();
				if(type == null){
					cell.setText(null);
				}else{
					cell.setText(labelProvider.getText(type));
					cell.setImage(labelProvider.getImage(type));
				}
			}
		};
	}
}
