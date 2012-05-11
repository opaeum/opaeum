package org.opaeum.rap.runtime.cubetree;


public class ColumnHeaderRow{
	int level;
	public boolean shouldDisplay(CubeColumnNode node){
		return node.getLevel()==level;
	}
}
