package org.opeum.topcased.uml;

import java.util.HashMap;

import org.topcased.modeler.preferences.ITopcasedPreferenceInitializer;
import org.topcased.modeler.uml.classdiagram.ClassElementsVisibilityConstants;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;

public class ClassDiagramTopcasedPreferenceInitializer extends org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramTopcasedPreferenceInitializer implements ITopcasedPreferenceInitializer{
	public HashMap<String,String> getDefaultPreference(){
		HashMap<String,String> defaultProfilePreference = super.getDefaultPreference();
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_SRCPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATION_TARGETPROPERTIES_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_ROUTER, "TreeRouter");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.GENERALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.INTERFACEREALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_FOREGROUND_COLOR, "160,32,240");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_ROUTER, "ObliqueRouter");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_SRCNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_TARGETNAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.TEMPLATEBINDING_MIDDLENAME_EDGE_OBJECT_DEFAULT_VISIBILITY, "true");
		defaultProfilePreference.put(ClassDiagramPreferenceConstants.TEMPLATEBINDING_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY, "false");
		String[] prefixes = {
				ClassElementsVisibilityConstants.CLASS_PREFERENCE_PREFIX,ClassElementsVisibilityConstants.DATATYPE_PREFERENCE_PREFIX,
				ClassElementsVisibilityConstants.INTERFACE_PREFERENCE_PREFIX,ClassElementsVisibilityConstants.PRIMITIVE_TYPE_PREFERENCE_PREFIX
		};
		for(String prefix:prefixes){
			defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_PARAMETER_DEFAULT_VALUE_DEFAULT, "true");
			defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_PARAMETER_TYPE_DEFAULT, "true");
			defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_PARAMETERS_DEFAULT, "true");
			defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_OPERATION_RETURN_TYPE_DEFAULT, "true");
			defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_PROPERTY_DEFAULT_VALUE_DEFAULT, "true");
			defaultProfilePreference.put(prefix + ClassDiagramPreferenceConstants.SHOW_PROPERTY_TYPE_DEFAULT, "true");
		}
		return defaultProfilePreference;
	}
}
