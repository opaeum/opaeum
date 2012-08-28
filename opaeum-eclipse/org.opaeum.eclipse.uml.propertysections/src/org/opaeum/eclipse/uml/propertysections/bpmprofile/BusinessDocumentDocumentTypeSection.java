package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BusinessDocumentDocumentTypeSection extends AbstractEnumerationOnStereotypeSection{
	protected String getAttributeName(){
		return "documentType";
	}
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_BPM_PROFILE;
	}
	protected String getStereotypeName(){
		return StereotypeNames.BUSINESS_DOCUMENT;
	}
	protected Classifier getClassifier(){
		return (Classifier) getEObject();
	}
	@Override
	protected Element getElement(){
		return getClassifier();
	}
	@Override
	protected String getLabelText(){
		return "Document Type";
	}
}
