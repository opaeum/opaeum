package org.opaeum.eclipse;

import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpenUmlFile;


public interface OpaeumSynchronizationListener{
	public void synchronizationComplete(OpenUmlFile openUmlFile, Set<Element> affectedElements);
}
