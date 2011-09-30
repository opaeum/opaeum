/*****************************************************************************
 * Copyright (c) 2009 atos origin.
 * 
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: eperico (atos origin) emilien.perico@atosorigin.com - Initial
 * API and implementation
 * 
 *****************************************************************************/
package org.topcased.modeler.uml.internal.properties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.modeler.uml.providers.QualifiedNameLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

/**
 * A section for the actual parameter of a TemplateParameterSubstitution object.
 * 
 * @author eperico
 * 
 */
public class TemplateParameterSubstitutionActualSection extends AbstractChooserPropertySection
{

	@Override
	protected EStructuralFeature getFeature() {
		return UMLPackage.eINSTANCE.getTemplateParameterSubstitution_Actual();
	}

	@Override
	protected String getLabelText() {
		 return "Actual:";
	}

    /**
     * {@inheritDoc}
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getAdvancedLabeProvider()
     */
    protected ILabelProvider getAdvancedLabeProvider()
    {
        // Fix bug [#582] : Use the IItemQualifiedTextProvider to display full path types in Choice box
        return new QualifiedNameLabelProvider().createAdapterFactory();
    }
    
    /**
     * {@inheritDoc}
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
     */
    protected ILabelProvider getLabelProvider()
    {
        return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
    }

	@Override
	protected Object[] getComboFeatureValues() {
		return getChoices(getEObject(), UMLPackage.Literals.PARAMETERABLE_ELEMENT);
	}

    /**
     * {@inheritDoc}
     * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
     */
    protected Object getFeatureValue()
    {
        return ((TemplateParameterSubstitution) getEObject()).getActual();
    }
    
}
