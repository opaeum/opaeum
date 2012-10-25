/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.modeleditor.providers;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.topcased.modeler.providers.ILabelFeatureProvider;

/**
 * This is the item provider adpater for a {@link org.eclipse.bpmn2.CorrelationProperty} object.
 *
 * @generated
 */
public class CorrelationPropertyModelerProvider extends RootElementModelerProvider implements ILabelFeatureProvider{
	/**
	 * This constructs an instance from a factory and a notifier.
	 *
	 * @param adapterFactory the adapter factory
	 * @generated
	 */
	public CorrelationPropertyModelerProvider(AdapterFactory adapterFactory){
		super(adapterFactory);
	}
	/**
	 * @see org.topcased.modeler.providers.ILabelFeatureProvider#getLabelFeature(java.lang.Object)
	 * @generated
	 */
	public EAttribute getLabelFeature(Object object){
		return Bpmn2Package.eINSTANCE.getCorrelationProperty_Name();
	}
}