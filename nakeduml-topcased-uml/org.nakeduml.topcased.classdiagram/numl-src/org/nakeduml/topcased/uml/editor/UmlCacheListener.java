package org.nakeduml.topcased.uml.editor;

import java.util.Set;

import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;

import org.eclipse.emf.ecore.EObject;

public interface UmlCacheListener{
	public void updateOclReferencesTo(INakedElement ne);
	public void synchronizationComplete(Set<EObject> asdf,Set<INakedNameSpace> nakedUmlChanges);
}
