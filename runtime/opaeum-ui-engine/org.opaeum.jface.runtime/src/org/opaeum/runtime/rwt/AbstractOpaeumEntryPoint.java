package org.opaeum.runtime.rwt;


import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractOpaeumEntryPoint implements EntryPoint{
	IOpaeumApplication application;
	protected AbstractOpaeumEntryPoint(IOpaeumApplication a){
		this.application = a;
	}
	@Override
	public int createUI(){
		OpaeumRapSession ors = new OpaeumRapSession(application);
		RWT.getUISession().setAttribute(OpaeumRapSession.ATTRIBUTE_NAME, ors);
		Display display = new Display();
		final Shell page = new Shell(display, SWT.NO_TRIM);
		page.setMaximized(true);
		page.setLayout(new GridLayout(1,true));
		page.setText(ors.getApplication().getIdentifier());
		new Label(page,SWT.BORDER).setText(ors.getApplication().getIdentifier());
	    page.layout();
	    page.open();
		// OpaeumWorkbenchPage ow=new OpaeumWorkbenchPage(ors);
		// ow.createParts();
		return 0;
	}
}
