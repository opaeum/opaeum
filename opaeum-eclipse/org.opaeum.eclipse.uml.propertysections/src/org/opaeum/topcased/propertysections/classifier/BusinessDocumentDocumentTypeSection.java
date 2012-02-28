package org.opaeum.topcased.propertysections.classifier;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.topcased.propertysections.base.AbstractEnumerationOnStereotypeSection;

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
