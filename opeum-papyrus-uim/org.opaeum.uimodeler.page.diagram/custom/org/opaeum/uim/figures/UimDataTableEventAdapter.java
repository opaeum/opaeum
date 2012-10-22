package org.opaeum.uim.figures;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.widgets.Control;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uimodeler.common.UimFigureUtil;
import org.opaeum.uimodeler.common.figures.AbstractEventAdapter;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;

public class UimDataTableEventAdapter extends AbstractEventAdapter{
	CustomUimDataTableFigure figure;
	public UimDataTableEventAdapter(UimDataTableEditPart editPart,CustomUimDataTableFigure figure){
		super(editPart, figure);
		this.figure = figure;
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else if(msg.getNotifier() instanceof UimDataTable){
			switch(msg.getEventType()){
			case Notification.ADD:
				switch(msg.getFeatureID(UimDataTable.class)){
				case ComponentPackage.UIM_DATA_TABLE__CHILDREN:
					prepareWidgetForRepaint();
					break;
				}
				break;
			case Notification.REMOVE:
				switch(msg.getFeatureID(UimDataTable.class)){
				case ComponentPackage.UIM_DATA_TABLE__CHILDREN:
					int i=0;
					for(Control control:figure.getComposite(). getColumnControls()){
						if(control.getData(UimFigureUtil.ELEMENT) == msg.getOldValue()){
							figure.getComposite().removeColumn(i);
						}
						i++;
					}
					prepareWidgetForRepaint();
				}
			}
		}
	}
}
