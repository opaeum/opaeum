package org.opaeum.uim.uml2uim;

import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.newchild.IOpaeumResourceSet;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.util.UimResourceImpl;

public class UimResourceUtil{
	private static final URIConverter uriConverter = new ExtensibleURIConverterImpl();
	public static Resource getUiResource(Element e,ResourceSet rst,URI dirUri3){
		String id = EmfWorkspace.getId(e);
		return getUimResource(e, rst, dirUri3, id);
	}
	public static Resource getUimResource(Element e,ResourceSet rst,URI dirUri3,String id){
		URI[] dirUris = buildPotentialDirectoriesByPriority(e, rst, dirUri3);
		URI formUri = null;
		for(URI uri:dirUris){
			if(uri != null){
				formUri = buildFormUri(uri, id);
				if(rst.getResource(formUri, false) != null || uriConverter.exists(formUri, Collections.emptyMap())){
					return rst.getResource(formUri, true);
				}
			}
		}
		for(URI uri:dirUris){
			if(uri != null){
				formUri = buildFormUri(uri, id);
				return new UimResourceImpl(formUri);
			}
		}
		throw new IllegalArgumentException("No0 uris given");
		// None found, create new one
		// NB!!! still needs to be added to resourceset, but this may require a transaction. The caller must do it
	}
	public static boolean hasUiResource(Element e,ResourceSet rst,URI dirUri){
		String id = EmfWorkspace.getId(e);
		return hasUimResource(e, rst, dirUri, id);
	}
	public static boolean hasUimResource(Element e,ResourceSet rst,URI dirUri,String id){
		URI[] dirUris = buildPotentialDirectoriesByPriority(e, rst, dirUri);
		URI formUri = null;
		for(URI uri:dirUris){
			if(uri != null){
				formUri = buildFormUri(uri, id);
				if(rst.getResource(formUri, false) != null || uriConverter.exists(formUri, Collections.emptyMap())){
					return true;
				}
			}
		}
		return false;
	}
	protected static URI[] buildPotentialDirectoriesByPriority(Element e,ResourceSet rst,URI dirUri){
		URI[] dirUris = new URI[3];
		if(rst instanceof IOpaeumResourceSet){
			//Eclipse UIM encironment always comes first
			IContainer uimDir = ((IOpaeumResourceSet) rst).getUimDirectory();
			if(uimDir!=null){
				dirUris[0] = URI.createPlatformResourceURI(uimDir.getFullPath().toString(), true);
			}
		}
		//UML Model directory second
		dirUris[1] = dirUri;
		if(e!=null && e.eResource()!=null){
			//co located files last 
			dirUris[2] = e.eResource().getURI().trimFragment();
		}
		return dirUris;
	}
	protected static URI buildFormUri(URI dirUri,String id){
		return dirUri.appendSegment("ui").appendSegment(id).appendFileExtension("uim");
	}
}
