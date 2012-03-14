package org.opaeum.uim.figures;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimPackage;
import org.opaeum.uimodeler.common.figures.AbstractEventAdapter;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;


public class UimDataTableEventAdapter extends AbstractEventAdapter{
	CustomUimDataTableFigure figure;
	public UimDataTableEventAdapter(UimDataTableEditPart editPart,CustomUimDataTableFigure figure){
		super(editPart, figure);
		this.figure=figure;
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else if(msg.getNotifier() instanceof UimDataTable){
			switch(msg.getEventType()){
			case Notification.REMOVE:
				switch(msg.getFeatureID(UimDataTable.class)){
				case UimPackage.UIM_DATA_TABLE__CHILDREN:
					Table table = figure.getTable();
					table.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
					for(TableColumn control:table.getColumns()){
						if(control.getData(UimFigureUtil.ELEMENT)==msg.getOldValue()){
							control.dispose();
						}
					}
					for(Control control:figure.getFirstRow().getChildren()){
						if(control.getData(UimFigureUtil.ELEMENT)==msg.getOldValue()){
							control.dispose();
						}
					}
				}
			}
		}
	}

}
