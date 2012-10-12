package org.opaeum.uim.queries;

import java.io.IOException;
import java.lang.ref.WeakReference;
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
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.resources.UimModelSet;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.util.UmlUimLinks;

public abstract class LazyInitializeUimQuery<T extends Element,S extends AbstractUserInteractionModel,R extends UserInterfaceRoot> implements
		IJavaModelQuery<T,R>{
	WeakReference<ResourceSet> tempResourceSet = new WeakReference<ResourceSet>(new ResourceSetImpl());
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
		if(shouldRegenerate(context, formUri)){
			FormSynchronizer2 fs2 = new FormSynchronizer2(directoryUri, getTempResourceSet(), false);
			if(generateModel(context, fs2)){
				saveAndReload(context, null, getTempResourceSet());
			}else{
				return null;
			}
		}
		Resource resource2 = ((UimModelSet) context.eResource().getResourceSet()).getUiResourceFor(context);
		AbstractUserInteractionModel eObject = (AbstractUserInteractionModel) resource2.getContents().get(0);
		new UmlUimLinks(resource2, OpaeumEclipseContext.findOpenUmlFileFor(context).getEmfWorkspace());
		R result = getResult((S) eObject);
		return result;
	}
	protected ResourceSet getTempResourceSet(){
		if(tempResourceSet.get() == null){
			tempResourceSet = new WeakReference<ResourceSet>(new ResourceSetImpl());
		}
		return tempResourceSet.get();
	}
	protected boolean shouldRegenerate(final T context,URI formUri){
		return !context.eResource().getResourceSet().getURIConverter().exists(formUri, Collections.emptyMap());
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