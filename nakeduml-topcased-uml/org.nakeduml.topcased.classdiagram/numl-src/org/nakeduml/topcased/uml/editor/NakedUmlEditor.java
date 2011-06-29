package org.nakeduml.topcased.uml.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.ModelNavigator;
import org.topcased.modeler.preferences.ModelerPreferenceConstants;
import org.topcased.modeler.uml.editor.outline.UMLOutlinePage;

public class NakedUmlEditor extends org.topcased.modeler.uml.editor.UMLEditor{
	@Override
	protected EObject openFile(IFile file,boolean resolve){
		EObject openFile = super.openFile(file, resolve);
		getResourceSet().eAdapters().add(new NakedUmlContentAdaptor());
        IPreferenceStore ps = getPreferenceStore(file);
        if(ps!=null){
        	String filters = ps.getString(ModelerPreferenceConstants.FILTERS_PREF);
        	if(filters==null || filters.length()==0){
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
