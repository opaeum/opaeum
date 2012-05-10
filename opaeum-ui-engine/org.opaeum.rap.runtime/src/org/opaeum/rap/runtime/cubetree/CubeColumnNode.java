package org.opaeum.rap.runtime.cubetree;

import org.olap4j.Axis;
import org.olap4j.Axis.Standard;

public class CubeColumnNode extends AbstractCubeNode{
	protected Standard getOtherAxis(){
		return Axis.ROWS;
	}
	protected Standard getAxis(){
		return Axis.COLUMNS;
	}
	@Override
	protected AbstractCubeNode createChild(){
		return new CubeColumnNode();
	}

}
