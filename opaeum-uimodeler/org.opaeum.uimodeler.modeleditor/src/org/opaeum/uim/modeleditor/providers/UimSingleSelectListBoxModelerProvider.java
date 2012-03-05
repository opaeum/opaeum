/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.uim.modeleditor.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.opaeum.uim.control.ControlPackage;

/**
 * This is the item provider adpater for a {@link org.opaeum.uim.control.UimSingleSelectListBox} object.
 *
 * @generated
 */
public class UimSingleSelectListBoxModelerProvider extends UimLookupModelerProvider implements ILabelFeatureProvider{
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public UimSingleSelectListBoxModelerProvider(AdapterFactory adapterFactory){
		super(adapterFactory);
	}
	/**
	 * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
	 * @generated
	 */
	public EAttribute getLabelFeature(Object object){
		return ControlPackage.eINSTANCE.getUimControl_Width();
	}
}