package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLPackage;

public class NamedElementNameEditingSupport extends EditingDomainEditingSupport{
	private final TableViewer viewer;
	public NamedElementNameEditingSupport(TableViewer viewer){
		super(viewer, "Name");
		this.viewer = viewer;
	}
	@Override
	protected CellEditor getCellEditor(Object element){
		return new TextCellEditor(viewer.getTable());
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		return ((NamedElement) element).getName();
	}
	@Override
	protected void setValue(Object element,Object value){
		NamedElement ne = (NamedElement) element;
		if(ne.getName() == null || !ne.getName().equals(value)){
			Command cmd = SetCommand.create(editingDomain, element, UMLPackage.eINSTANCE.getNamedElement_Name(), String.valueOf(value));
			editingDomain.getCommandStack().execute(cmd);
		}
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				cell.setText(((NamedElement) cell.getElement()).getName());
			}
		};
	}
}
