package org.nakeduml.topcased.uml.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.ModelNavigator;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.uml.actions.UMLEObjectAction;
import org.topcased.modeler.uml.editor.outline.UMLNavigator;
import org.topcased.modeler.uml.editor.outline.UMLOutlinePage;

public class NakedUmlEditor extends org.topcased.modeler.uml.editor.UMLEditor{
	private static Map<IFile,NakedUmlEclipseContext> contexts = new WeakHashMap<IFile,NakedUmlEclipseContext>();
	private static NakedUmlEclipseContext currentContext;
	public static NakedUmlEclipseContext getCurrentContext(){
		return currentContext;
	}
	private IFile currentUmlFile;
	@Override
	public void doSave(IProgressMonitor monitor){
		super.doSave(monitor);
		currentContext.onSave(monitor);
	}
	@Override
	protected IContentOutlinePage createOutlinePage(){
		return new UMLOutlinePage(this){
			@Override
			protected ModelNavigator createNavigator(Composite parent,Modeler editor,IPageSite pageSite){
				return new UMLNavigator(parent, editor, pageSite){
					@Override
					protected void createSingleSelectionMenu(IMenuManager manager,Object selection){
						super.createSingleSelectionMenu(manager, selection);
						for(IContributionItem item:manager.getItems()){
							if(item instanceof ActionContributionItem){
								ActionContributionItem actionItem = (ActionContributionItem) item;
								if(actionItem.getAction() instanceof UMLEObjectAction){
									manager.remove(item);
								}
							}
						}
					}
				};
			}
		};
	}
	public void dispose(){
		super.dispose();
		contexts.remove(getFileEditorInput(getEditorInput()).getFile());
		currentContext.onClose(true);
		currentContext = null;
	}
	@Override
	protected void setInput(IEditorInput input){
		super.setInput(input);
		IFileEditorInput f = getFileEditorInput(getEditorInput());
		currentContext = getContext(f);
		currentUmlFile = getUmlFile(f);
	}
	private NakedUmlEclipseContext getContext(final IFileEditorInput fe){
		NakedUmlEclipseContext result = contexts.get(fe.getFile());
		if(result == null){
			final NakedUmlEclipseContext newOne = result = new NakedUmlEclipseContext(getSite().getShell(), getResourceSet());
			contexts.put(fe.getFile(), result);
			getSite().getShell().getDisplay().timerExec(1000, new Runnable(){
				@Override
				public void run(){
					try{
						if(newOne.startSynch(getUmlFile(fe))){
							NakedUmlErrorMarker errorMarker = new NakedUmlErrorMarker(getSite().getShell(), newOne);
							errorMarker.run();
						}else{
							getSite().getShell().getDisplay().timerExec(10000, this);
						}
					}catch(IOException e){
						NakedUmlPlugin.getDefault().getLog().log(new Status(Status.ERROR, NakedUmlPlugin.getId(), e.getMessage(), e));
					}
				}
			});
		}
		return result;
	}
	private IFile getUmlFile(final IFileEditorInput fe){
		return fe.getFile().getProject().getFile(fe.getFile().getProjectRelativePath().removeFileExtension().addFileExtension("uml"));
	}
	@Override
	protected EObject openFile(final IFile file,boolean resolve){
		EObject openFile = super.openFile(file, resolve);
		getResourceSet().eAdapters().add(new NakedUmlElementLinker());
		IPreferenceStore ps = getPreferenceStore(file);
		if(ps != null){
			String filters = ps.getString(ModelerPreferenceConstants.FILTERS_PREF);
			if(filters == null || filters.length() == 0){
				ps.setValue(ModelerPreferenceConstants.FILTERS_PREF, NakedUmlFilter.class.getName());
			}
			ps.setValue(ModelerPreferenceConstants.CREATE_CHILD_MENU_PREF, NakedUmlEditorMenu.class.getName());
		}
		return openFile;
	}
	@Override
	protected List<AdapterFactory> getAdapterFactories(){
		List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		factories.add(new NakedUmlItemProviderAdapterFactory());
		factories.add(new org.topcased.modeler.uml.providers.UMLModelerProviderAdapterFactory());
		factories.addAll(super.getAdapterFactories());
		return factories;
	}
	public IFile getCurrentUmlFile(){
		return currentUmlFile;
	}
	@Override
	public DefaultEditDomain getEditDomain(){
		return super.getEditDomain();
	}
}
