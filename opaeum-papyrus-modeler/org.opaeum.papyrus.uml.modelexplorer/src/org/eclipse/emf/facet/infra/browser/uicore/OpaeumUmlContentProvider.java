package org.eclipse.emf.facet.infra.browser.uicore;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class OpaeumUmlContentProvider extends org.eclipse.papyrus.uml.modelexplorer.UMLContentProvider implements ITreeContentProvider{
	public OpaeumUmlContentProvider(){
		super();
	}
	@Override
	protected EObject[] getRootElements(ModelSet modelSet){
		return super.getRootElements(modelSet);
	}
}
