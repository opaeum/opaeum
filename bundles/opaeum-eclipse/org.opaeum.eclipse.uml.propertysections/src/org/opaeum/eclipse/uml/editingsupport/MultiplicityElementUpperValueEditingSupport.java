package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

public class MultiplicityElementUpperValueEditingSupport extends EditingDomainEditingSupport{
	private final TableViewer viewer;
	public MultiplicityElementUpperValueEditingSupport(TableViewer viewer){
		super(viewer, "Upper Limit");
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
		ValueSpecification upperValue = ((MultiplicityElement) element).getUpperValue();
		if(upperValue==null){
			return "1";
		}
		return upperValue.stringValue();
	}
	@Override
	protected void setValue(Object element,Object value){
		MultiplicityElement ne = (MultiplicityElement) element;
		if(ne.getUpperValue() == null || !ne.getUpperValue().stringValue().equals(value)){
			LiteralUnlimitedNatural n=null;
			String stringValue = value==null?"":value.toString();
			if(stringValue.length()>0){
				n=UMLFactory.eINSTANCE.createLiteralUnlimitedNatural();
				n.setValue(stringValue.equals("*")?LiteralUnlimitedNatural.UNLIMITED:Integer.parseInt(stringValue));
			}
			Command cmd = SetCommand.create(editingDomain, element, UMLPackage.eINSTANCE.getMultiplicityElement_UpperValue(), n);
			editingDomain.getCommandStack().execute(cmd);
		}
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				cell.setText((String)getValue(cell.getElement()));
			}
		};
	}
}
