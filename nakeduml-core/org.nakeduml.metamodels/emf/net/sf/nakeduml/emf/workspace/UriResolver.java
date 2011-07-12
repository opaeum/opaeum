package net.sf.nakeduml.emf.workspace;

import java.io.File;

import org.eclipse.emf.common.util.URI;

public interface UriResolver{
	File resolve(URI uri);
}
