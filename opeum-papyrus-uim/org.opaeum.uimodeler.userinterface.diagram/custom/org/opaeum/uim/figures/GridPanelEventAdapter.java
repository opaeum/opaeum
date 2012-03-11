package org.opaeum.uim.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uimodeler.util.UimFigureUtil;

public class GridPanelEventAdapter extends AbstractEventAdapter{
	private AbstractPanelFigure fig;
	public GridPanelEventAdapter(GraphicalEditPart ed,AbstractPanelFigure fig){
		super(ed, fig);
		this.fig = fig;
	}
	@Override
	public void figureMoved(IFigure source){
		super.figureMoved(source);
		if(source == figure){
			Rectangle b = source.getBounds();
			fig.getWidget().setBounds(0, 0, b.width, b.height);
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(fig.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else if(msg.getNotifier() instanceof GridPanel){
			switch(msg.getEventType()){
			case Notification.REMOVE:
				switch(msg.getFeatureID(GridPanel.class)){
				case PanelPackage.GRID_PANEL__CHILDREN:
					Control[] children = ((Composite) fig.getWidget()).getChildren();
					for(Control control:children){
						if(control.getData(UimFigureUtil.ELEMENT) == msg.getOldValue()){
							control.dispose();
						}
					}
				}
			}
		}
	}
}
