package org.opaeum.runtime.jface.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public abstract class SelectionAction extends Action implements ISelectionChangedListener{
	private IStructuredSelection structuredSelection;
	public SelectionAction(){
		super();
	}
	public SelectionAction(String text){
		super(text);
	}
	public SelectionAction(String text,ImageDescriptor image){
		super(text, image);
	}
	public SelectionAction(String text,int style){
		super(text, style);
	}
	protected IStructuredSelection getStructuredSelection(){
		return structuredSelection;
	}
	@Override
	public void selectionChanged(SelectionChangedEvent event){
		if(event.getSelection() instanceof IStructuredSelection){
			this.structuredSelection=(IStructuredSelection)event.getSelection();
		}else{
			this.structuredSelection=null;
		}
		
	}
}