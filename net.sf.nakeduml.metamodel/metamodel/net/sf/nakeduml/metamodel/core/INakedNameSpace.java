package net.sf.nakeduml.metamodel.core;
import nl.klasse.octopus.model.INameSpace;
import nl.klasse.octopus.model.IPackage;
public interface INakedNameSpace extends INakedPackageableElement, INameSpace {

	IPackage getRoot();

	INakedNameSpace getParent();
}