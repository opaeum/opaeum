package org.opaeum.uimodeler.common.figures;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.BuiltInLinkKind;
import org.opaeum.uim.swt.UimSwtUtil;

public class BuiltInLinkEventAdapter extends AbstractEventAdapter{
	public BuiltInLinkEventAdapter(GraphicalEditPart editPart,CustomBuiltInLinkFigure figure){
		super(editPart, figure);
		setKind(getBuiltInLink().getKind());
	}
	private BuiltInLink getBuiltInLink(){
		return (BuiltInLink) element;
	}
	private CustomBuiltInLinkFigure getFigure(){
		return (CustomBuiltInLinkFigure) super.figure;
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else{
			if(msg.getNotifier() instanceof BuiltInLink){
				int featureId = msg.getFeatureID(BuiltInLink.class);
				switch(featureId){
				case ActionPackage.BUILT_IN_LINK__KIND:
					BuiltInLinkKind kind = getBuiltInLink().getKind();
					setKind(kind);
					prepareWidgetForRepaint();
					break;
				}
			}
		}
	}
	private void setKind(BuiltInLinkKind kind){
			getFigure().getLink().setImage(UimSwtUtil.getImageFor(kind));
	}
}
