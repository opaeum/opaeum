package org.opaeum.metamodel.core;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.INameSpace;
public interface INakedNameSpace extends INakedPackageableElement, INameSpace {


	INakedNameSpace getParent();

	boolean isImported(IClassifier cls);
}