package org.opaeum.runtime.jface.cubetree;

import org.olap4j.Cell;
import org.olap4j.CellSet;
import org.olap4j.Position;

public class TreeCubeCell{
	private Position rowPosition;
	private Position columnposition;
	private CellSet cellSet;
	public TreeCubeCell(CellSet cellSet,Position rowPosition,Position columnposition){
		super();
		this.cellSet = cellSet;
		this.rowPosition = rowPosition;
		this.columnposition = columnposition;
	}
	public Cell getCell(){
		return cellSet.getCell(columnposition,rowPosition);
	}
}
