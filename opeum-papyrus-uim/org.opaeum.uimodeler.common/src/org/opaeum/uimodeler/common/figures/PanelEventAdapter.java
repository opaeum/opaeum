package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.swt.GridPanelComposite;
import org.opaeum.uimodeler.common.UimFigureUtil;

public class PanelEventAdapter extends AbstractEventAdapter{
	private AbstractPanelFigure fig;
	private GridPanelComposite composite;
	public PanelEventAdapter(GraphicalEditPart ed,AbstractPanelFigure fig){
		super(ed, fig);
		this.fig = fig;
		this.composite = (GridPanelComposite) fig.getWidget();
		composite.getContentPane().setData(UimFigureUtil.ELEMENT, element);
		composite.getContentPane().setData(UimFigureUtil.FIGURE, figure);
		GridLayout children = (GridLayout) composite.getContentPane().getLayout();
		if(element instanceof ActionBar){
			children.numColumns = 30;
		}else if(element instanceof GridPanel){
			children.numColumns = ((GridPanel) element).getNumberOfColumns();
		}
		composite.getContentPane().layout();
	}
	@Override
	public void notifyChanged(Notification msg){
		if(isActive()){
			super.notifyChanged(msg);
			if(msg.getNotifier() instanceof AbstractPanel){
				switch(msg.getEventType()){
				case Notification.REMOVE:
					switch(msg.getFeatureID(GridPanel.class)){
					case PanelPackage.ABSTRACT_PANEL__CHILDREN:
						Control[] children = composite.getContentPane().getChildren();
						for(Control control:children){
							if(control.getData(UimFigureUtil.ELEMENT) == msg.getOldValue()){
								control.dispose();
							}
						}
						composite.getContentPane().layout();
					}
					break;
				case Notification.ADD:
					switch(msg.getFeatureID(GridPanel.class)){
					case PanelPackage.ABSTRACT_PANEL__CHILDREN:
						// fig.layout();
						// if(getParent().getParent() instanceof Shell){
						// getParent().pack();
						// }
						// prepareForRepaint();
					}
					break;
				case Notification.SET:
					switch(msg.getFeatureID(GridPanel.class)){
					case PanelPackage.GRID_PANEL__NUMBER_OF_COLUMNS:
						GridLayout children = (GridLayout) composite.getContentPane().getLayout();
						children.numColumns = (Integer) msg.getNewValue();
						composite.getContentPane().layout();
						fig.layout();
						prepareWidgetForRepaint();
					}
				}
			}
		}
	}
}
