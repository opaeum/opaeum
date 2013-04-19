package org.opaeum.runtime.jface.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TypedListener;
import org.opaeum.runtime.rwt.Activator;

public class MessageTable extends ScrolledComposite{
	private List<MessageLink> messages = new ArrayList<MessageLink>();
	private Composite contentPane;
	public MessageTable(Composite parent,int style){
		super(parent, style);
		contentPane = new Composite(this, SWT.NONE);
		setContent(contentPane);
  	setExpandHorizontal(true);
  	setExpandVertical(true);
  	setShowFocusedControl(true);
  	contentPane.setLayout(new FillLayout(SWT.VERTICAL));

	}
	public void refresh(DataBindingContext ctx){
		for(MessageLink Link:messages){
			Link.dispose();
		}
		MultiStatus statuses = new MultiStatus(Activator.ID, Status.ERROR, "Multiple Problems", null);
		for(Iterator it = ctx.getValidationStatusProviders().iterator();it.hasNext();){
			ValidationStatusProvider validationStatusProvider = (ValidationStatusProvider) it.next();
			IStatus status = (IStatus) validationStatusProvider.getValidationStatus().getValue();
			if(!status.isOK()){
				addMessages(status);
			}
		}
		
		contentPane.pack();
		setMinSize(contentPane.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	private void addMessages(IStatus status2){
		if(status2 instanceof MultiStatus){
			IStatus[] children = status2.getChildren();
			for(IStatus s:children){
				addMessages(s);
			}
		}else{
			MessageLink l = new MessageLink(contentPane, SWT.NONE);
			messages.add(l);
			l.setText(getMessage(status2));
			l.getImageLabel().setImage(getImage(status2));
		}
	}
	private Image getImage(IStatus status2){
		switch(status2.getSeverity()){
		case IStatus.ERROR:
			return Display.getCurrent().getSystemImage(SWT.ICON_ERROR);
		case IStatus.WARNING:
			return Display.getCurrent().getSystemImage(SWT.ICON_WARNING);
		case IStatus.INFO:
			return Display.getCurrent().getSystemImage(SWT.ICON_INFORMATION);
		case IStatus.OK:
			return Display.getCurrent().getSystemImage(SWT.ICON_INFORMATION);
		}
		return null;
	}
	private String getMessage(IStatus status2){
		return status2.getMessage() == null || status2.getMessage().length() == 0 ? "Unkown validation error" : status2.getMessage();
	}
	public void addSelectionListener(SelectionListener listener){
		super.addListener(SWT.Selection, new TypedListener(listener));
	}
}
