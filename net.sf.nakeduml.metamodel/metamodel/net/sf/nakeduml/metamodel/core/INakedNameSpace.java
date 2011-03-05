package net.sf.nakeduml.metamodel.core;
import nl.klasse.octopus.model.INameSpace;
public interface INakedNameSpace extends INakedPackageableElement, INameSpace {


	INakedNameSpace getParent();
}