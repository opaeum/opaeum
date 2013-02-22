package org.opaeum.ecore;

import java.util.ArrayList;
import java.util.List;

public abstract class EModelElementImpl extends EObjectImpl implements EModelElement{
	private List<EAnnotation> eAnnotations=new ArrayList<EAnnotation>();

	@Override
	public List<EAnnotation> getEAnnotations(){
		return eAnnotations;
	}
}
