package org.opaeum.ecore;

import java.util.List;

public interface EModelElement extends EObject{
	List<EAnnotation> getEAnnotations();
}
