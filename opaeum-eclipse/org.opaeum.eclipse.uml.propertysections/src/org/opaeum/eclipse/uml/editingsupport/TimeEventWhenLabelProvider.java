package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.TimeEvent;

public final class TimeEventWhenLabelProvider extends CellLabelProvider{
	@Override
	public void update(ViewerCell cell){
		TimeEvent timeEvent=(TimeEvent) cell.getElement();
		OpaqueExpression expr = (OpaqueExpression) timeEvent.getWhen().getExpr();
		if(expr==null || expr.getBodies().isEmpty()){
			cell.setText("");
		}else{
			cell.setText(expr.getBodies().get(0));
		}
	}
}