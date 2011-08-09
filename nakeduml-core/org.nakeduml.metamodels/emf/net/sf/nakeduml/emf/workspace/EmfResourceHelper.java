package net.sf.nakeduml.emf.workspace;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

public interface EmfResourceHelper{
	File resolveUri(URI uri);
	String getId(EModelElement e);
}
