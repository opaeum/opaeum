package net.sf.nakeduml.emf.workspace;

import java.io.File;
import java.io.IOException;

import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class DefaultUriResolver implements EmfResourceHelper{
	@Override
	public File resolveUri(URI uri){
		if(!uri.isFile()){
			throw new IllegalStateException();
		}else{
			try{
				return new File(uri.toFileString()).getCanonicalFile();
			}catch(IOException e){
				throw new IllegalStateException(e);
			}
		}
	}
	@Override
	public String getId(EModelElement e){
		if(e instanceof EmfWorkspace){
			return ((EmfWorkspace) e).getIdentifier();
		}else{
			EAnnotation eAnnotation = e.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
			if(eAnnotation != null){
				String string = eAnnotation.getDetails().get("uuid");
				if(string != null){
					return string;
				}
			}
			Resource eResource = e.eResource();
			return eResource.getURI().lastSegment() + "@" + eResource.getURIFragment(e);
		}
	}
}
