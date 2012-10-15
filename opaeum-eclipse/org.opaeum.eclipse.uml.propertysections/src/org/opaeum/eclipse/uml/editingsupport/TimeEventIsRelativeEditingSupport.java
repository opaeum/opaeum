package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.ImageManager;

public class TimeEventIsRelativeEditingSupport extends EditingDomainEditingSupport{
	public TimeEventIsRelativeEditingSupport(ColumnViewer viewer){
		super(viewer, "Relative", 50);
	}
	@Override
	protected CellEditor getCellEditor(Object element){
		return new CheckboxCellEditor((Composite) viewer.getControl());
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		return ((TimeEvent)element).isRelative();
	}
	@Override
	protected void setValue(Object element,Object value){
		Command cmd = SetCommand.create(editingDomain, element, UMLPackage.eINSTANCE.getTimeEvent_IsRelative(), Boolean.TRUE.equals(value));
		editingDomain.getCommandStack().execute(cmd);

	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			
			@Override
			public void update(ViewerCell cell){
				cell.setText("");
				if(Boolean.TRUE.equals(getValue(cell.getElement()))){
					cell.setImage(ImageManager.IMG_CHECKED);
				}else{
					cell.setImage(ImageManager.IMG_UNCHECKED);
				}
			}
		};
	}

}
