package org.opaeum.runtime.jface.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.rap.rwt.widgets.DialogCallback;
import org.eclipse.rap.rwt.widgets.DialogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Listener;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;
import org.opaeum.runtime.jface.entityeditor.EntityFormEditor;
import org.opaeum.runtime.persistence.event.ChangedEntity;

public class OpaeumEditorPane implements CTabFolder2Listener,SelectionListener,FocusListener,ISelectionListener{
	List<EntityFormEditor> entityEditors = new ArrayList<EntityFormEditor>();
	List<CTabItem> tabItems = new ArrayList<CTabItem>();
	CTabFolder cTabFolder;
	int activePosition;
	OpaeumWorkbenchPage page;
	public OpaeumEditorPane(OpaeumWorkbenchPage page){
		this.page = page;
		// TODO Auto-generated constructor stub
	}
	public void createPane(Composite parent){
		cTabFolder = new CTabFolder(parent, SWT.TOP);
		cTabFolder.addCTabFolder2Listener(this);
		cTabFolder.addSelectionListener(this);
	}
	public void openEditor(EntityEditorInputJface input){
		boolean isOpen = false;
		for(EntityFormEditor entityEditor:entityEditors){
			if(input.getPersistentObject().equals(entityEditor.getEditorInput().getPersistentObject())){
				cTabFolder.setFocus();
				int index = entityEditors.indexOf(entityEditor);
				cTabFolder.showItem(tabItems.get(activePosition = index));
				cTabFolder.setSelection(tabItems.get(activePosition = index));
				isOpen = true;
				break;
			}
		}
		if(!isOpen){
			CTabItem newItem = new CTabItem(cTabFolder, SWT.CLOSE);
			EntityFormEditor ee = new EntityFormEditor(newItem);
			entityEditors.add(ee);
			ee.init(page, input);
			Composite control = new Composite(cTabFolder, SWT.NONE);
			control.setLayout(new FillLayout());
			ee.createPartControl(control);
			control.layout();
			newItem.setControl(control);
			newItem.setText(ee.getPartName());
			newItem.setImage(ee.getTitleImage());
			tabItems.add(newItem);
			activePosition = tabItems.size() - 1;
			cTabFolder.setFocus();
			cTabFolder.showItem(newItem);
			cTabFolder.setSelection(newItem);
		}
	}
	@Override
	public void close(CTabFolderEvent event){
		final CTabItem item = (CTabItem) event.item;
		final int index = tabItems.indexOf(item);
		final EntityFormEditor entityEditor = this.entityEditors.get(index);
		if(entityEditor.isDirty()){
			MessageDialog dg = new MessageDialog(cTabFolder.getShell().getShell(), "Would you like to save changes?", null, "My question",
					MessageDialog.QUESTION_WITH_CANCEL, new String[]{IDialogConstants.get().YES_LABEL,IDialogConstants.get().NO_LABEL,
							IDialogConstants.get().CANCEL_LABEL}, 0);
			org.opaeum.runtime.rwt.DialogUtil.open(dg, new DialogCallback(){
				@Override
				public void dialogClosed(int returnCode){
					if(returnCode == MessageDialog.OK){
						entityEditor.close(true);
						removeItem(item, entityEditor, index);
					}else{
						entityEditor.close(false);
						removeItem(item, entityEditor, index);
					}
				}
			});
		}else{
			entityEditor.close(false);
			removeItem(item, entityEditor, index);
		}
	}
	protected void removeItem(CTabItem item,EntityFormEditor entityEditor,int index){
		if(activePosition >= index){
			activePosition--;
		}
		page.firePartClosed(entityEditor);
		tabItems.remove(item);
		entityEditors.remove(entityEditor);
	}
	@Override
	public void minimize(CTabFolderEvent event){
		// TODO Auto-generated method stub
	}
	@Override
	public void maximize(CTabFolderEvent event){
		// TODO Auto-generated method stub
	}
	@Override
	public void restore(CTabFolderEvent event){
		// TODO Auto-generated method stub
	}
	@Override
	public void showList(CTabFolderEvent event){
		// TODO Auto-generated method stub
	}
	public CTabFolder getTabFolder(){
		return cTabFolder;
	}
	public IEditorPart getActiveEditor(){
		if(entityEditors.size() > activePosition && activePosition >= 0){
			return entityEditors.get(activePosition);
		}else{
			return null;
		}
	}
	@Override
	public void widgetSelected(SelectionEvent e){
		if(this.tabItems.contains(e.item)){
			activePosition = tabItems.indexOf(e.item);
			page.firePartActivated(getActiveEditor());
			page.fireSelectionChanged(getActiveEditor(), new StructuredSelection(getActiveEditor().getAdapter(IPersistentObject.class)));
			OpaeumEditor ae = (OpaeumEditor) getActiveEditor();
			Map<ChangedEntity,IPersistentObject> conflicts = ae.getEditorInput().getPersistence().synchronizeWithDatabaseAndFindConflicts();
			if(conflicts.size() > 0){
				if(MessageDialog.openQuestion(cTabFolder.getShell(), "Load Changes?",
						"The data you are editing has been changed in the database. Would you like to refresh the editor from the database?")){
					ae.getEditorInput().getPersistence().overwriteConflictsFromDatabase(conflicts);
					ae.refresh();
				}else{
					ae.getEditorInput().getPersistence().overwriteDatabaseWithConflicts(conflicts);
				}
			}
		}
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent e){
	}
	@Override
	public void focusGained(FocusEvent event){
		if(getActiveEditor() != null){
			page.firePartActivated(getActiveEditor());
		}
	}
	@Override
	public void focusLost(FocusEvent event){
	}
	@Override
	public void selectionChanged(IWorkbenchPart part,ISelection selection){
		if(!(part instanceof IEditorPart)){
			// Ignore my own selectionEvents
			Object firstElement = ((IStructuredSelection) selection).getFirstElement();
			IPersistentObject persistentObject = null;
			if(firstElement instanceof IAdaptable){
				persistentObject = (IPersistentObject) ((IAdaptable) firstElement).getAdapter(IPersistentObject.class);
				for(EntityFormEditor ee:this.entityEditors){
					if(ee.getAdapter(IPersistentObject.class).equals(persistentObject)){
						cTabFolder.setSelection(this.entityEditors.indexOf(ee));
						break;
					}
				}
			}
		}
	}
}
