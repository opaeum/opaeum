package org.nakeduml.topcased.uml.editor;

import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.oclengine.IOclContext;

import org.eclipse.emf.ecore.EAttribute;

public interface IUmlModelUpdator{

	public abstract void updateOclBody(INakedElement de,final IOclContext oclValue,final EAttribute body,final EAttribute language);
}
