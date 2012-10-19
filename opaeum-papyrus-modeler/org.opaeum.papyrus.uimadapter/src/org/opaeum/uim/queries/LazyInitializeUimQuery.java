package org.opaeum.uim.queries;

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
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.resources.UimModelSet;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.uml2uim.UimResourceUtil;
import org.opaeum.uim.util.UmlUimLinks;

@SuppressWarnings({"deprecation","restriction"})
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
		OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(context);
		if(!ouf.getConfig().isUiModelerActive()){
			return null;
		}
		UimModelSet ums = (UimModelSet) context.eResource().getResourceSet();
		URI directoryUri = context.eResource().getURI().trimSegments(1);
		Resource r;
		if(!UimResourceUtil.hasUiResource(context, ums, directoryUri)){
			FormSynchronizer2 fs2 = new FormSynchronizer2(directoryUri, ums, false);
			if(generateModel(context, fs2)){
				ums.registerUimResources(fs2.getNewResources());
				r = fs2.getNewResources().get(context);
			}else{
				return null;
			}
		}else{
			r = UimResourceUtil.getUiResource(context, ums, directoryUri);
			new UmlUimLinks(r, ouf.getEmfWorkspace());
		}
		AbstractUserInteractionModel eObject = (AbstractUserInteractionModel) r.getContents().get(0);
		@SuppressWarnings("unchecked")
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