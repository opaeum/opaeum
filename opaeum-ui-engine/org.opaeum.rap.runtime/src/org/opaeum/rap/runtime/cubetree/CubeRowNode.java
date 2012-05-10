package org.opaeum.rap.runtime.cubetree;

import org.olap4j.Axis;
import org.olap4j.Axis.Standard;

public class CubeRowNode extends AbstractCubeNode{
	protected Standard getOtherAxis(){
		return Axis.COLUMNS;
	}
	protected Standard getAxis(){
		return Axis.ROWS;
	}
	@Override
	protected AbstractCubeNode createChild(){
		return new CubeRowNode();
	}
}
