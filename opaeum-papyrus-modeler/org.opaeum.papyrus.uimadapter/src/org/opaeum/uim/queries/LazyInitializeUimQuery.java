package org.opaeum.uim.queries;

import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.resources.UimModelSet;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.uml2uim.UimResourceUtil;
import org.opaeum.uim.util.UmlUimLinks;
import org.opaeum.uimodeler.util.UimContentAdapter;

public abstract class LazyInitializeUimQuery<T extends Element,S extends AbstractUserInteractionModel,R extends UserInterfaceRoot>
		implements IJavaModelQuery<T,R>{
	ResourceSetImpl tempResourceSet;
	public LazyInitializeUimQuery(){
		super();
	}
	public R evaluate(final T context,ParameterValueList parameterValues) throws ModelQueryExecutionException{
		if(Display.getCurrent() == null){
			return null;
		}
		UimModelSet ums = (UimModelSet) context.eResource().getResourceSet();
		URI directoryUri = context.eResource().getURI().trimSegments(1);
		Resource r = UimResourceUtil.getUiResource(context, ums, directoryUri);
		if(r.getResourceSet() == null){
			FormSynchronizer2 fs2 = new FormSynchronizer2(directoryUri, ums, false);
			if(generateModel(context, fs2)){
				for(Entry<Element,Resource> entry:fs2.getNewResources().entrySet()){
					ums.registerUimResource(entry.getKey(), entry.getValue());
					new UmlUimLinks(entry.getValue(), OpaeumEclipseContext.findOpenUmlFileFor(context).getEmfWorkspace());
				}
				r=fs2.getNewResources().get(context);
			}else{
				return null;
			}
		}else{
			new UmlUimLinks(r, OpaeumEclipseContext.findOpenUmlFileFor(context).getEmfWorkspace());
		}
		AbstractUserInteractionModel eObject = (AbstractUserInteractionModel) r.getContents().get(0);
		R result = getResult((S) eObject);
		tempResourceSet = null;
		return result;
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
}