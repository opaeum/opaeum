package org.eclipse.uml2.uml;


public interface INakedStructuralFeatureAction extends IActionWithTargetPin{
	INakedProperty getFeature();
	void setFeature(INakedProperty feature);
	INakedInputPin getObject();
	void setObject(INakedInputPin object);
}
