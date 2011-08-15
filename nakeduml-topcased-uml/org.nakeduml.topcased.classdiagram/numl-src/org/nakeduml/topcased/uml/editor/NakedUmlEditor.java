package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import net.sf.nakeduml.feature.NakedUmlConfig;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.nakeduml.eclipse.NakedUmlConfigDialog;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.ModelNavigator;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.uml.actions.UMLEObjectAction;
import org.topcased.modeler.uml.editor.outline.UMLNavigator;
import org.topcased.modeler.uml.editor.outline.UMLOutlinePage;

public class NakedUmlEditor extends org.topcased.modeler.uml.editor.UMLEditor{
	private static NakedUmlEclipseContext currentContext;
	private static Map<IContainer,NakedUmlEclipseContext> contexts = new WeakHashMap<IContainer,NakedUmlEclipseContext>();
	public static NakedUmlEclipseContext getCurrentContext(){
		return currentContext;
	}
	@Override
	public void close(boolean save){
		super.close(save);
		if(save==false && currentContext != null){
			currentContext.removeNakedModel(getResourceSet());
		}
		
	}
	@Override
	public void doSave(IProgressMonitor monitor){
		super.doSave(monitor);
		if(currentContext != null){
			currentContext.onSave(monitor,getEditingDomain().getResourceSet());
		}
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
		if(currentContext != null){
			currentContext.onClose(true,getEditingDomain().getResourceSet());
		}
		contexts.remove(getEditingDomain().getResourceSet());
		super.dispose();
		currentContext = null;
	}
	@Override
	protected void setInput(IEditorInput input){
		super.setInput(input);
		IFileEditorInput f = getFileEditorInput(getEditorInput());
		currentContext = getContext(f);
	}
	private NakedUmlEclipseContext getContext(final IFileEditorInput fe){
		IContainer umlDir = fe.getFile().getParent();
		IFile umlFile = getUmlFile(fe);
		NakedUmlEclipseContext result = getNakedUmlEclipseContextFor(umlDir);
		if(result != null){
			if(result.isSyncronizingWith(getResourceSet())){
				result.setCurrentEditContext(getEditingDomain(), umlFile);
			}else{
				result.startSynch(getEditingDomain(), umlFile);
			}
		}
		return result;
	}
	public static NakedUmlEclipseContext getNakedUmlEclipseContextFor(IContainer umlDir){
		NakedUmlEclipseContext result = contexts.get(umlDir);
		if(result == null){
			NakedUmlConfig cfg = null;
			final IFile propsFile = (IFile) umlDir.findMember("nakeduml.properties");
			if(propsFile == null){
				NakedUmlConfigDialog dlg = new NakedUmlConfigDialog(Display.getDefault().getActiveShell(), propsFile.getLocation().toFile());
				if(dlg.open() == SWT.OK){
					cfg = dlg.getConfig();
				}
			}else{
				// Load classes
				NakedUmlEclipsePlugin.getDefault();
				cfg = new NakedUmlConfig(propsFile.getLocation().toFile());
			}
			if(cfg != null){
				final NakedUmlEclipseContext newOne = new NakedUmlEclipseContext(cfg,umlDir);
				contexts.put(umlDir, newOne);
				result=newOne;
			}
		}
		currentContext=result;
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
	@Override
	public DefaultEditDomain getEditDomain(){
		return super.getEditDomain();
	}
}
