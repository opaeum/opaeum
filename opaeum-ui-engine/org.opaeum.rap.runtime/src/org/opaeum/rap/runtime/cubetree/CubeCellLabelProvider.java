package org.opaeum.rap.runtime.cubetree;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.olap4j.metadata.Member;

public class CubeCellLabelProvider extends ColumnLabelProvider{
	CubeColumnNode node;
	public CubeCellLabelProvider(CubeColumnNode node,Member measure){
		super();
		this.node = node;
		this.measure = measure;
	}
	Member measure;
	@Override
	public String getText(Object element){
		if(element instanceof ColumnHeaderRow){
			if(((ColumnHeaderRow) element).shouldDisplay(node)){
				return node.member.getCaption();
			}
		}
		if(element instanceof CubeRowNode){
			CubeRowNode row=(CubeRowNode) element;
			TreeCubeCell cubeCell = row.getCell(node,measure);
			return cubeCell.getCell().getFormattedValue();
		}
		return "";
	}
}
