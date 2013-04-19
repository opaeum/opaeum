package org.opaeum.uimodeler.modelexplorer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.emf.providers.MoDiscoContentProvider;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.wizard.AbstractWizard;

@Deprecated
public class UimContentProvider extends MoDiscoContentProvider{
	@Override
	protected EObject[] getRootElements(ModelSet modelSet){
		EList<EObject> contents = null;
		for(Resource resource:modelSet.getResources()){
			if(resource.getURI() != null
					&& resource.getURI().trimFileExtension().lastSegment().equals(modelSet.getFilenameWithoutExtension().lastSegment())){
				EObject o = resource.getContents().get(0);
				if(o instanceof AbstractEditor || o instanceof AbstractWizard || o instanceof PerspectiveConfiguration) {
					contents = resource.getContents();
				}
			}
		}
		if(contents == null){
			return null;
		}else{
			return contents.toArray(new EObject[contents.size()]);
		}
	}
}
