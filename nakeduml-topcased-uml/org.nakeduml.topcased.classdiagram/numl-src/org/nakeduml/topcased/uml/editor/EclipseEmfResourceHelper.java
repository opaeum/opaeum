package org.nakeduml.topcased.uml.editor;

import java.io.File;
import java.util.UUID;

import net.sf.nakeduml.emf.workspace.EmfResourceHelper;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Element;

public final class EclipseEmfResourceHelper implements EmfResourceHelper{
	@Override
	public File resolveUri(URI uri){
		String platformString2 = uri.toPlatformString(true);
		try{
			IFile diFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString2));
			return diFile.getLocation().toFile();
		}catch(IllegalArgumentException a){
			try{
				IFolder diFile = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(platformString2));
				return diFile.getLocation().toFile();
			}catch(IllegalArgumentException a2){
				IProject diFile = ResourcesPlugin.getWorkspace().getRoot().getProject(platformString2);
				return diFile.getLocation().toFile();
			}
		}
	}

	@Override
	public String getId(EModelElement umlElement){
		if(umlElement instanceof EmfWorkspace){
			return ((EmfWorkspace) umlElement).getName();
		}else{
			String uid = null;
			EAnnotation ann = umlElement.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
			if(ann == null && umlElement instanceof Element){
				ann = ((Element)umlElement).createEAnnotation(StereotypeNames.NUML_ANNOTATION);
			}
			if(ann == null){
				// not in editable resource,but the filename and fragment would be stable and unique
				Resource eResource = umlElement.eResource();
				URI uri = eResource.getURI();
				uid = uri.lastSegment() + eResource.getURIFragment(umlElement);
			}else{
				uid = ann.getDetails().get("uuid");
				if(uid == null){
					char[] a = UUID.randomUUID().toString().toCharArray();
					for(int i = 0;i < a.length;i++){
						if(!Character.isJavaIdentifierPart(a[i])){
							a[i] = '_';
						}
					}
					uid = new String(a);
					ann.getDetails().put("uuid", uid);
				}
			}
			return uid;
		}
	}
}