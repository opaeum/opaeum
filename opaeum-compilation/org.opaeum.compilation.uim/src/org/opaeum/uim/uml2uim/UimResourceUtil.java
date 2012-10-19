package org.opaeum.uim.uml2uim;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.util.UimResourceImpl;

public class UimResourceUtil{
	private static final URIConverter uriConverter = new ExtensibleURIConverterImpl();
	public static Resource getUiResource(Element e,ResourceSet rst,URI dirUri){
		String id = EmfWorkspace.getId(e);
		URI formUri = e.eResource().getURI().trimFragment().appendSegment("ui").appendSegment(id).appendFileExtension("uim");
		if(rst.getResource(formUri, false) != null || uriConverter.exists(formUri, Collections.emptyMap())){
			return rst.getResource(formUri, true);
		}else{
			// Now look in the local workspace
			formUri = dirUri.appendSegment("ui").appendSegment(id).appendFileExtension("uim");
			if(rst.getResource(formUri, false) != null || uriConverter.exists(formUri, Collections.emptyMap())){
				return rst.getResource(formUri, true);
			}else{
				// None found, create new one
				// NB!!! still needs to be added to resourceset, but this may require a transaction. The caller must do it
				return new UimResourceImpl(formUri);
			}
		}
	}
	public static boolean hasUiResource(Element e,ResourceSet rst,URI dirUri){
		String id = EmfWorkspace.getId(e);
		URI formUri = e.eResource().getURI().trimFragment().appendSegment("ui").appendSegment(id).appendFileExtension("uim");
		if(rst.getResource(formUri, false) != null || uriConverter.exists(formUri, Collections.emptyMap())){
			return true;
		}else{
			// Now look in the local workspace
			formUri = dirUri.appendSegment("ui").appendSegment(id).appendFileExtension("uim");
			if(rst.getResource(formUri, false) != null || uriConverter.exists(formUri, Collections.emptyMap())){
				return true;
			}else{
				// None found, create new one
				// NB!!! still needs to be added to resourceset, but this may require a transaction. The caller must do it
				return false;
			}
		}
	}
}
