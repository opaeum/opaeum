package org.opaeum.uimodeler.common.figures;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.swt.UimSwtUtil;

public class BuiltinActionButtonEventAdapter extends ActionButtonEventAdapter{
	public BuiltinActionButtonEventAdapter(GraphicalEditPart editPart,CustomUimActionFigure figure){
		super(editPart, figure);
		setImage(getButton().getKind());
	}
	private CustomUimActionFigure getFigure(){
		return (CustomUimActionFigure) super.figure;
	}
	private BuiltInActionButton getButton(){
		return (BuiltInActionButton) super.element;
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else{
			ActionKind kind = getButton().getKind();
			setImage(kind);
			prepareWidgetForRepaint();
		}
	}
	private void setImage(ActionKind kind){
		getFigure().getButton().setImage(UimSwtUtil.getImageFor(kind));
	}
}
