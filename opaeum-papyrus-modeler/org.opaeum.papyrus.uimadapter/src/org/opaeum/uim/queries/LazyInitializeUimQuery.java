package org.opaeum.uim.queries;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.editor.ObjectEditor;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.util.UimResourceImpl;
import org.opaeum.uim.util.UmlUimLinks;
import org.opaeum.uim.wizard.NewObjectWizard;

public abstract class LazyInitializeUimQuery<T extends Element,S extends AbstractUserInteractionModel,R extends UserInterfaceRoot> implements
		IJavaModelQuery<T,R>{
	public LazyInitializeUimQuery(){
		super();
	}
	public R evaluate(final T context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		if(Display.getCurrent() == null){
			return null;
		}
		URI directoryUri = context.eResource().getURI().trimSegments(1);
		URI formUri = directoryUri.appendSegment("ui");
		formUri = formUri.appendSegment(EmfWorkspace.getId(context));
		formUri = formUri.appendFileExtension("uim");
		if(!context.eResource().getResourceSet().getURIConverter().exists(formUri, Collections.emptyMap())){
			ResourceSetImpl tempRst = new ResourceSetImpl();
			FormSynchronizer2 fs2 = new FormSynchronizer2(directoryUri, tempRst, false);
			if(generateModel(context, fs2)){
				saveAndReload(context, null, tempRst);
			}else{
				return null;
			}
		}
		Resource resource2 = context.eResource().getResourceSet().getResource(formUri, true);
		AbstractUserInteractionModel eObject = (AbstractUserInteractionModel) resource2.getContents().get(0);
		new UmlUimLinks(resource2, OpaeumEclipseContext.findOpenUmlFileFor(context).getEmfWorkspace());
		return getResult((S) eObject);
	}
	protected abstract boolean generateModel(final T context,FormSynchronizer2 fs2);
	public final Resource getResource(ResourceSet uimRst,URI directoryUri,String id,String extenstion){
		URI formUri = directoryUri.appendSegment("ui");
		formUri = formUri.appendSegment(id);
		formUri = formUri.appendFileExtension(extenstion);
		return getOrCreateResource(uimRst, formUri);
	}
	protected abstract R getResult(S eObject);
	public Resource getOrCreateResource(ResourceSet uimRst,URI formUri){
		Resource resource = null;
		try{
			resource = uimRst.getResource(formUri, true);
		}catch(Exception e){
		}
		if(resource == null){
			resource = uimRst.createResource(formUri);
		}
		return resource;
	}
	protected void saveAndReload(final EObject context,Resource resource2,ResourceSet tempRst){
		try{
			EList<Resource> resources = tempRst.getResources();
			for(Resource resource:resources){
				resource.save(Collections.EMPTY_MAP);
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}