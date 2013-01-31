package org.opaeum.uim.swt;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class DataTableActionBarComposite extends Composite implements IUimWidget{
	public DataTableActionBarComposite(Composite parent,int style){
		super(parent, style);
	}
	@Override
	public void markForShot(){
		for(Control control:getChildren()){
			if(control instanceof IUimWidget){
				((IUimWidget) control).markForShot();
			}
		}
	}
}
