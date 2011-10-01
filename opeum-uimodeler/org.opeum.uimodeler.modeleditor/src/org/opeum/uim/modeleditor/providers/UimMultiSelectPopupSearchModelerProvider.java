/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opeum.uim.modeleditor.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.opeum.uim.control.ControlPackage;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the item provider adpater for a {@link org.opeum.uim.control.UimMultiSelectPopupSearch} object.
 *
 * @generated
 */
public class UimMultiSelectPopupSearchModelerProvider extends UimLookupModelerProvider implements ILabelFeatureProvider{
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public UimMultiSelectPopupSearchModelerProvider(AdapterFactory adapterFactory){
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