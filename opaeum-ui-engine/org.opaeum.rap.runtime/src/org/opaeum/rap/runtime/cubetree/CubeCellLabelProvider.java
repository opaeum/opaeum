package org.opaeum.rap.runtime.cubetree;

import org.eclipse.jface.viewers.ColumnLabelProvider;

public class CubeCellLabelProvider extends ColumnLabelProvider{
	CubeColumnNode node;
	@Override
	public String getText(Object element){
		if(element instanceof ColumnHeaderRow){
			if(((ColumnHeaderRow) element).shouldDisplay(node)){
				return node.member.getCaption();
			}
		}
		if(element instanceof CubeRowNode){
			CubeRowNode row=(CubeRowNode) element;
			TreeCubeCell cubeCell = row.getCell(node);
			return cubeCell.getCell().getFormattedValue();
		}
		return "";
	}
}
