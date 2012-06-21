package org.opaeum.eclipse.newchild;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.opaeum.eclipse.context.EObjectSelectorUI;

public class CreateChildAndSelectAction extends CreateChildAction{
	private EObjectSelectorUI selector;
	public CreateChildAndSelectAction(EditingDomain editingDomain,ISelection selection,Object descriptor){
		super(editingDomain, selection, descriptor);
	}
	public CreateChildAndSelectAction(IEditorPart editorPart,ISelection selection,Object descriptor){
		super(editorPart, selection, descriptor);
	}
	public CreateChildAndSelectAction(IWorkbenchPart workbenchPart,ISelection selection,Object descriptor){
		super(workbenchPart, selection, descriptor);
	}
	protected void gotoNewObject(){
		if(selector != null){
			for(final Object object:command.getResult()){
				if(object instanceof EObject){
					Display.getCurrent().timerExec(300, new Runnable(){
						@Override
						public void run(){
							selector.gotoEObject((EObject) object);
						}
					});
					break;
				}
			}
		}
	}
	@Override
	public void run(){
		super.run();
		gotoNewObject();
	}
	public EObjectSelectorUI getSelector(){
		return selector;
	}
	public void setSelector(EObjectSelectorUI selector){
		this.selector = selector;
	}
}
