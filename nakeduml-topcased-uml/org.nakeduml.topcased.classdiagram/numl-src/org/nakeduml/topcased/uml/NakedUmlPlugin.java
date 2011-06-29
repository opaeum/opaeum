package org.nakeduml.topcased.uml;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.topcased.modeler.uml.UMLPlugin;

public class NakedUmlPlugin extends UMLPlugin{
	@Override
	protected void initializeImageRegistry(ImageRegistry reg){
		reg.put("Actor", ImageDescriptor.createFromURL(getBundle().getEntry("icons/Actor.gif")));
	}
}
