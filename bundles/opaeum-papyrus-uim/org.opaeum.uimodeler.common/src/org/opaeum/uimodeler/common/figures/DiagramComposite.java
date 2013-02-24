package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.uim.swt.IUimWidget;

public class DiagramComposite extends Composite implements IUimWidget{
	public DiagramComposite(Composite parent,int style){
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
