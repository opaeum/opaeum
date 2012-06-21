package org.opaeum.topcased.uml.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gmf.runtime.common.ui.util.PartListenerAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.OpaeumFilter;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.feature.OpaeumConfig;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.DiagramsOutlinePage;
import org.topcased.modeler.editor.outline.ModelNavigator;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.uml.editor.outline.UMLOutlinePage;

public class OpaeumEditor extends org.topcased.modeler.uml.editor.UMLEditor implements EObjectSelectorUI{
	private final class MyPartListener extends PartListenerAdapter{
		private IFile umlFile;
		@Override
		public void partClosed(IWorkbenchPart part){
			try{
				if(getCurrentContext() != null){
					if(part instanceof EditorPart){
						IEditorInput editorInput = ((EditorPart) part).getEditorInput();
						if(editorInput instanceof FileEditorInput){
							getCurrentContext().onClose(getUmlFile((IFileEditorInput) editorInput));
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				OpaeumEclipseContext.setCurrentContext(null);
			}
		}
		public void setFile(IFile umlFile){
			this.umlFile = umlFile;
		}
		@Override
		public void partActivated(IWorkbenchPart part){
			setCurrentEditorContext();
		}
		@Override
		public void partBroughtToTop(IWorkbenchPart part){
			setCurrentEditorContext();
		}
	}
	private Stack<EObject> history = new Stack<EObject>();
	private MyPartListener partListener;
	private Object diagramOutlinePage;
	public static OpaeumEclipseContext getCurrentContext(){
		return OpaeumEclipseContext.getCurrentContext();
	}
	@Override
	public void close(boolean save){
		if(getCurrentContext() != null && getCurrentContext().isLoading()){
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Opaeum is still initializing",
					"Cannot close the model while the Opaeum tooling is still initializing. Please try again shortly.");
		}else{
			super.close(save);
		}
	}
	public void pushSelection(EObject e){
		if(history.isEmpty() || history.peek() != e){
			history.push(e);
		}
	}
	public void gotoEObjects(List<EObject> eobjects,boolean reveal){
		super.gotoEObjects(eobjects, reveal);
	}
	public EObject popSelection(){
		if(history.size() > 1){
			history.pop();
		}
		EObject peek = history.peek();
		gotoEObject(peek);
		return peek;
	}
	public void init(IEditorSite site,IEditorInput input) throws PartInitException{
		super.init(site, input);
		setIsReadOnly(OpaeumConfig.isValidVersionNumber(getFile(input).getParent().getName()));
	}
	private MyPartListener getPartListener(){
		if(partListener == null){
			this.partListener = new MyPartListener();
			getSite().getPage().addPartListener(partListener);
		}
		return this.partListener;
	}
	@Override
	public void doSave(IProgressMonitor monitor){
		try{
			monitor.beginTask("Saving UML Models", 1000);
			super.doSave(new SubProgressMonitor(monitor, 500));
			OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(getUmlFile((IFileEditorInput) getEditorInput()).getParent());
			ctx.onSave(new SubProgressMonitor(monitor, 500), getUmlFile((IFileEditorInput) getEditorInput()));
		}finally{
			monitor.done();
		}
	}
	public Object getAdapter(@SuppressWarnings("rawtypes")
	Class type){
		if(type == IContentOutlinePage.class){
			if(this.diagramOutlinePage == null){
				this.diagramOutlinePage = super.getAdapter(IContentOutlinePage.class);
			}
			return this.diagramOutlinePage;
		}
		return super.getAdapter(type);
	}
	@Override
	protected IContentOutlinePage createOutlinePage(){
		return new UMLOutlinePage(this){
			private OpaeumNavigator opaeumNavigator;
			@Override
			protected ModelNavigator createNavigator(Composite parent,Modeler editor,IPageSite pageSite){
				opaeumNavigator = new OpaeumNavigator(parent, editor, pageSite);
				return opaeumNavigator;
			}
			@Override
			public Object getAdapter(@SuppressWarnings("rawtypes")
			Class adapter){
				// Workaround for bug in Topcased
				return OpaeumEditor.this.getAdapter(adapter);
			}
			public void dispose(){
				super.dispose();
			}
			public void setSelection(ISelection selection){
				super.setSelection(selection);
				opaeumNavigator.setSelection(selection);
			}
		};
	}
	public void dispose(){
		super.dispose();
	}
	@Override
	protected void setInput(IEditorInput input){
		super.setInput(input);
	}
	public void refreshOutline(){
		// Called when this editor's tab has been selected
		super.refreshOutline();
		setCurrentEditorContext();
	}
	public void setCurrentEditorContext(){
		IFileEditorInput f = getFileEditorInput(getEditorInput());
		OpaeumEclipseContext.setCurrentContext(getContext(f));
		OpaeumEclipseContext.getCurrentContext().setCurrentEditContext(getEditingDomain(), getUmlFile(f), this);
	}
	private OpaeumEclipseContext getContext(final IFileEditorInput fe){
		IContainer umlDir = fe.getFile().getParent();
		IFile umlFile = getUmlFile(fe);
		OpaeumEclipseContext result = OpaeumEclipseContext.findOrCreateContextFor(umlDir);
		if(result != null && getEditingDomain() != null){
			getPartListener().setFile(umlFile);
			if(result.isSyncronizingWith(umlFile)){
				result.setCurrentEditContext(getEditingDomain(), umlFile, this);
			}else{
				result.startSynch(getEditingDomain(), umlFile);
			}
			result.seteObjectSelectorUI(this);
		}
		return result;
	}
	private IFile getUmlFile(final IFileEditorInput fe){
		return fe.getFile().getProject().getFile(fe.getFile().getProjectRelativePath().removeFileExtension().addFileExtension("uml"));
	}
	@Override
	protected EObject openFile(final IFile file,boolean resolve){
		EObject openFile = super.openFile(file, resolve);
		IPreferenceStore ps = getPreferenceStore(file);
		if(ps != null){
			String filters = ps.getString(ModelerPreferenceConstants.FILTERS_PREF);
			if(filters == null || filters.length() == 0){
				ps.setValue(ModelerPreferenceConstants.FILTERS_PREF, OpaeumFilter.class.getName());
			}
			ps.setValue(ModelerPreferenceConstants.CREATE_CHILD_MENU_PREF, OpaeumEditorMenu.class.getName());
		}
		return openFile;
	}
	@Override
	protected List<AdapterFactory> getAdapterFactories(){
		List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		factories.add(new OpaeumItemProviderAdapterFactory());
		for(AdapterFactory adapterFactory:super.getAdapterFactories()){
			if(!(adapterFactory instanceof UMLItemProviderAdapterFactory)){
				factories.add(adapterFactory);
			}
		}
		return factories;
	}
	@Override
	public DefaultEditDomain getEditDomain(){
		return super.getEditDomain();
	}
}
