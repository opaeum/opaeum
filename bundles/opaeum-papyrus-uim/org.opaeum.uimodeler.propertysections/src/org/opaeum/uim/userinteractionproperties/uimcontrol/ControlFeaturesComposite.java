package org.opaeum.uim.userinteractionproperties.uimcontrol;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uim.control.UimControl;

public class ControlFeaturesComposite<T extends UimControl> extends Composite{
	protected T control;
	protected EditingDomain editingDomain;

	public void setControl(UimControl ctl, EditingDomain ed){
		this.control=(T) ctl;
		this.editingDomain=ed;
	}
	public ControlFeaturesComposite(Composite parent,int style){
		super(parent, style);
	}
	public void createContent(){
		
	}
	public void refresh(){
		
	}
}
