package org.opaeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;

public class CallActionIsSynchronousSection extends AbstractBooleanPropertySection
{
    protected EStructuralFeature getFeature()
    {
        return UMLPackage.eINSTANCE.getCallAction_IsSynchronous();
    }

}