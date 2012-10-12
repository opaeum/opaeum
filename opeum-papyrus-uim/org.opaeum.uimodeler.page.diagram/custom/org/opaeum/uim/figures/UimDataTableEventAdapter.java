package org.opaeum.uim.figures;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uimodeler.common.figures.AbstractEventAdapter;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;

public class UimDataTableEventAdapter extends AbstractEventAdapter{
	CustomUimDataTableFigure figure;
	public UimDataTableEventAdapter(UimDataTableEditPart editPart,CustomUimDataTableFigure figure){
		super(editPart, figure);
		this.figure = figure;
		figure.composite.getDisplayedContent().setData(UimFigureUtil.FIGURE, figure);
		figure.composite.getTable().addControlListener(this);
		figure.composite.getActionBar().addControlListener(this);
		figure.composite.getFirstRow().addControlListener(this);
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
					Table table = figure.getTable();
					table.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
					break;
				}
				break;
			case Notification.REMOVE:
				switch(msg.getFeatureID(UimDataTable.class)){
				case ComponentPackage.UIM_DATA_TABLE__CHILDREN:
					Table table = figure.getTable();
					figure.getTable().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
					figure.getFirstRow().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
					for(TableColumn control:table.getColumns()){
						if(control.getData(UimFigureUtil.ELEMENT) == msg.getOldValue()){
							control.dispose();
						}
					}
					for(Control control:figure.getFirstRow().getChildren()){
						if(control.getData(UimFigureUtil.ELEMENT) == msg.getOldValue()){
							control.dispose();
						}
					}
				}
			}
		}
	}
}
