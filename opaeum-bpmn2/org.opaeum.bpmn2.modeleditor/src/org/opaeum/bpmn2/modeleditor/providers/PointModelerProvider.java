/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.modeleditor.providers;

import org.eclipse.dd.dc.DcPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the item provider adpater for a {@link org.eclipse.dd.dc.Point} object.
 *
 * @generated
 */
public class PointModelerProvider extends ItemProviderAdapter implements ILabelFeatureProvider{
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public PointModelerProvider(AdapterFactory adapterFactory){
		super(adapterFactory);
	}
	/**
	 * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
	 * @generated
	 */
	public EAttribute getLabelFeature(Object object){
		return DcPackage.eINSTANCE.getPoint_X();
	}
}