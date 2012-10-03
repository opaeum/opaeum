package org.opaeum.uim.userinteractionproperties.sections;

import java.util.Collection;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;

public interface ITypedElementProvider{

	TypedElement getTypedElement(String teName);

	Collection<? extends TypedElement> getTypedElements();

	Property getProperty(Classifier type,String nextName);
}
