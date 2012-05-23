package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.swt.GridPanelComposite;

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
		if(element instanceof EditorActionBar){
			children.numColumns = 30;
		}else if(element instanceof GridPanel){
			children.numColumns = ((GridPanel) element).getNumberOfColumns();
		}
		composite.getContentPane().layout();
	}
	@Override
	public void figureMoved(IFigure source){
		super.figureMoved(source);
		if(source == figure && composite.getParent() instanceof Shell){
			//IF it is the toplevel panel, resize the shell and root composite
			Rectangle b = source.getBounds();
			fig.getWidget().getParent().setSize(b.width, b.height);
			fig.getWidget().setLayoutData(new GridData(b.width, b.height));
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(fig.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else if(msg.getNotifier() instanceof AbstractPanel){
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
					fig.layout();
					prepareForRepaint();
				}
				break;
			case Notification.SET:
				switch(msg.getFeatureID(GridPanel.class)){
				case PanelPackage.GRID_PANEL__NUMBER_OF_COLUMNS:
					GridLayout children = (GridLayout) composite.getContentPane().getLayout();
					children.numColumns = (Integer) msg.getNewValue();
					composite.getContentPane().layout();
					fig.layout();
					prepareForRepaint();
				}
			}
		}
	}
	public void prepareForRepaint(){
		super.prepareForRepaint();
	}
}