package org.opaeum.runtime.jface.cubetree;


public class ColumnHeaderRow{
	int level;
	public ColumnHeaderRow(int level){
		super();
		this.level = level;
	}
	public boolean shouldDisplay(CubeColumnNode node){
		return node.getLevel()==level;
	}
}
