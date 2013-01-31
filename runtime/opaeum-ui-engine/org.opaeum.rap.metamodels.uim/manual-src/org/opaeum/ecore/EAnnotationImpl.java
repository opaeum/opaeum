package org.opaeum.ecore;

import java.util.Map;

public class EAnnotationImpl implements EAnnotation{
	Map<String,String> details;
	@Override
	public Map<String,String> getDetails(){
		return details;
	}
}
