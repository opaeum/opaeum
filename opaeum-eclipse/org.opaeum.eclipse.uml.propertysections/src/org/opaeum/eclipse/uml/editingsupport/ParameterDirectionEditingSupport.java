package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLPackage;

public class ParameterDirectionEditingSupport extends EditingDomainEditingSupport{
	public ParameterDirectionEditingSupport(ColumnViewer viewer){
		super(viewer,"Direction", 100);
	}
	@Override
	protected CellEditor getCellEditor(Object element){
		// Not a ComboViewer because we bastardized with the exception entry
		ComboBoxCellEditor result = new ComboBoxCellEditor(((TableViewer) viewer).getTable(), new String[]{"In Only","In and Out","Out only","Return","Exception"});
		return result;
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		Parameter p = (Parameter) element;
		ParameterDirectionKind direction = p.getDirection();
		if(p.isException()){
			return 4;
		}else{
			switch(direction){
			case IN_LITERAL:
				return 0;
			case INOUT_LITERAL:
				return 1;
			case OUT_LITERAL:
				return 2;
			case RETURN_LITERAL:
				return 3;
			}
		}
		return direction.getValue();
	}
	@Override
	protected void setValue(Object element,Object value){
		Parameter p = (Parameter) element;
		if(p.isException() != value.equals(4)){
			Command cmd2 = SetCommand.create(editingDomain, element, UMLPackage.eINSTANCE.getParameter_IsException(), value.equals(4));
			editingDomain.getCommandStack().execute(cmd2);
		}
		ParameterDirectionKind newKind = calcDirection(value);
		if(p.getDirection() != newKind){
			Command cmd = SetCommand.create(editingDomain, element, UMLPackage.eINSTANCE.getParameter_Direction(), newKind);
			editingDomain.getCommandStack().execute(cmd);
		}
	}
	private ParameterDirectionKind calcDirection(Object value){
		switch(((Integer) value).intValue()){
		case 0:
			return ParameterDirectionKind.IN_LITERAL;
		case 1:
			return ParameterDirectionKind.INOUT_LITERAL;
		case 3:
			return ParameterDirectionKind.RETURN_LITERAL;
		default:
			return ParameterDirectionKind.OUT_LITERAL;
		}
	}
	public CellLabelProvider getLabelProvider(){
		return new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				Parameter p = (Parameter) cell.getElement();
				ParameterDirectionKind dir = p.getDirection();
				String value = "Return";
				if(p.isException()){
					value = "Exception";
				}else{
					switch(dir){
					case IN_LITERAL:
						value = "In Only";
						break;
					case INOUT_LITERAL:
						value = "In and Out";
						break;
					case OUT_LITERAL:
						value = "Out Only";
						break;
					}
				}
				cell.setText(value);
			}
		};
	}
}
