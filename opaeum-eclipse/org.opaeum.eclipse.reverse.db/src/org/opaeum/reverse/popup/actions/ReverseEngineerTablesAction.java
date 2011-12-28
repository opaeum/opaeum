package org.opaeum.reverse.popup.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCCatalog;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpaeumEditingContext;
import org.opaeum.eclipse.reverse.db.UmlGenerator;

public class ReverseEngineerTablesAction extends Action{
	private IStructuredSelection selection;
	@Override
	public void run(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		Shell shell = Display.getCurrent().getActiveShell();
		Collection<IFile> files = new HashSet<IFile>();
		for(IWorkbenchWindow w:PlatformUI.getWorkbench().getWorkbenchWindows()){
			for(IWorkbenchPage p:w.getPages()){
				for(IEditorReference e:p.getEditorReferences()){
					try{
						if(e.getEditorInput() instanceof FileEditorInput){
							FileEditorInput editorInput = (FileEditorInput) e.getEditorInput();
							if(editorInput.getFile().getParent().findMember("opaeum.properties") != null){
								OpaeumEclipseContext ctx = OpaeumEclipseContext.getContextFor(editorInput.getFile().getParent());
								for(IResource r:editorInput.getFile().getParent().members()){
									if(r.getName().endsWith(".uml")){
										if(ctx.getEditingContextFor((IFile) r) != null){
											files.add((IFile) r);
										}
									}
								}
							}
						}
					}catch(PartInitException e1){
						e1.printStackTrace();
					}catch(CoreException e2){
						e2.printStackTrace();
					}
				}
			}
		}
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, new LabelProvider(){
			@Override
			public String getText(Object element){
				IFile file = (IFile)element;
				return file.getProject().getName() + "/" + file.getProjectRelativePath(); 
			}
		});
		dialog.setElements(files.toArray());
		dialog.setTitle("Models in Workspace");
		dialog.setMessage("Select the targetmodel:");
		dialog.open();
		if(dialog.getFirstResult() != null){
			IFile file = (IFile) dialog.getFirstResult();
			OpaeumEclipseContext ctx = OpaeumEclipseContext.getContextFor(file.getParent());
			if(ctx != null){
				OpaeumEditingContext eCtx = ctx.getEditingContextFor(file);
				if(eCtx != null){
					ctx.getEmfToOpaeumSynchronizer().suspend();
					new UmlGenerator().generateUml(calculateTables(), eCtx.getModel());
					ctx.getEmfToOpaeumSynchronizer().resumeAndCatchUp();
				}
			}
			try{
				file.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
			}catch(CoreException e){
				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings("unchecked")
	private Collection<JDBCTable> calculateTables(){
		Iterator<?> iterator = selection.iterator();
		Collection<JDBCTable> result = new HashSet<JDBCTable>();
		while(iterator.hasNext()){
			Object object = (Object) iterator.next();
			if(object instanceof JDBCTable){
				result.add((JDBCTable) object);
			}else if(object instanceof JDBCSchema){
				result.addAll(((JDBCSchema) object).getTables());
			}else if(object instanceof JDBCCatalog){
				EList<JDBCSchema> schemas = ((JDBCCatalog) object).getSchemas();
				for(JDBCSchema jdbcSchema:schemas){
					result.addAll(jdbcSchema.getTables());
				}
			}
		}
		return result;
	}
}
