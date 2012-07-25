package org.eclipse.uml2.uml;


public interface INakedEvent extends INakedElement,INakedElementOwner{
	INakedClassifier getContext();
}