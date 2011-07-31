package org.nakeduml.topcased.uml.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.NakedUmlConfig;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.nakeduml.eclipse.NakedUmlConfigDialog;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.ModelNavigator;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.uml.editor.outline.UMLOutlinePage;

public class NakedUmlEditor extends org.topcased.modeler.uml.editor.UMLEditor{
	UmlElementCache umlElementMap;
	private IPath filePath;
	public IPath getFilePath(){
		return filePath;
	}
	public UmlElementCache getUmlElementCache(){
		return umlElementMap;
	}
	@Override
	protected EObject openFile(final IFile file,boolean resolve){
		this.filePath = file.getLocation().removeFileExtension().addFileExtension("uml");
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
		getSite().getShell().getDisplay().timerExec(2000, new Runnable(){
			@Override
			public void run(){
				try{
					startSynch(file);
				}catch(IOException e){
					NakedUmlPlugin.getDefault().getLog().log(new Status(Status.ERROR, NakedUmlPlugin.getId(), e.getMessage(), e));
				}
			}
		});
		return openFile;
	}
	private void startSynch(IFile file) throws IOException{
		NakedUmlConfig cfg = new NakedUmlConfig();
		java.io.File file2 = new java.io.File(file.getLocation().toFile().getParentFile(), "nakeduml.properties");
		if(!file2.exists()){
			NakedUmlConfigDialog dlg = new NakedUmlConfigDialog(getSite().getShell());
			dlg.open();
			String domain = dlg.getCompanyDomain();
			StringBuilder mavenGroup = null;
			StringTokenizer st = new StringTokenizer(domain, ".");
			while(st.hasMoreTokens()){
				if(mavenGroup == null){
					mavenGroup = new StringBuilder(st.nextToken());
				}else{
					mavenGroup.insert(0, '.');
					mavenGroup.insert(0, st.nextToken());
				}
			}
			mavenGroup.append('.');
			mavenGroup.append(dlg.getWorkspaceIdentifier());
			cfg.loadDefaults(dlg.getWorkspaceIdentifier());
			cfg.setMavenGroupId(mavenGroup.toString());
			cfg.store(new FileWriter(file2));

		}else{
			cfg.load(file2, "");
		}
		try{
			umlElementMap = new UmlElementCache(getResourceSet());
			umlElementMap.startSynchronizing(filePath.toFile(), cfg);
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	protected IContentOutlinePage createOutlinePage(){
		return new UMLOutlinePage(this){
			@Override
			protected ModelNavigator createNavigator(Composite parent,Modeler editor,IPageSite pageSite){
				ModelNavigator createNavigator = super.createNavigator(parent, editor, pageSite);
				return createNavigator;
			}
		};
	}
}
