package org.opaeum.eclipse.newchild;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.ResourceSet;

public interface IOpaeumResourceSet extends ResourceSet{
	IContainer getModelDirectory();
	IFile getPrimaryFile();
}
