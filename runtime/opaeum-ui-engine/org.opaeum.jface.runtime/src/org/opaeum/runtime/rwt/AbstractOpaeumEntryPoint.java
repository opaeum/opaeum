package org.opaeum.runtime.rwt;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.opaeum.runtime.jface.ui.OpaeumWorkbenchPage;

public abstract class AbstractOpaeumEntryPoint implements EntryPoint{
	IOpaeumApplication application;
	protected AbstractOpaeumEntryPoint(IOpaeumApplication a){
		this.application=a;
	}
	@Override
	public int createUI(){
		OpaeumRapSession ors = new OpaeumRapSession(application);
		RWT.getUISession().setAttribute(OpaeumRapSession.ATTRIBUTE_NAME, ors);
		OpaeumWorkbenchPage ow=new OpaeumWorkbenchPage(ors);
		ow.createParts();
		return 0;
	}
}
