package org.opaeum.uimodeler.userinterface.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @generated
 */
public abstract class UimAbstractNavigatorItem extends PlatformObject{
	/**
	 * @generated
	 */
	static{
		final Class[] supportedTypes = new Class[]{ITabbedPropertySheetPageContributor.class};
		final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor(){
			public String getContributorId(){
				return "org.opaeum.uimodeler.userinterface.diagram"; //$NON-NLS-1$
			}
		};
		Platform.getAdapterManager().registerAdapters(new IAdapterFactory(){
			public Object getAdapter(Object adaptableObject,Class adapterType){
				if(adaptableObject instanceof org.opaeum.uimodeler.userinterface.diagram.navigator.UimAbstractNavigatorItem
						&& adapterType == ITabbedPropertySheetPageContributor.class){
					return propertySheetPageContributor;
				}
				return null;
			}
			public Class[] getAdapterList(){
				return supportedTypes;
			}
		}, org.opaeum.uimodeler.userinterface.diagram.navigator.UimAbstractNavigatorItem.class);
	}
	/**
	 * @generated
	 */
	private Object myParent;
	/**
	 * @generated
	 */
	protected UimAbstractNavigatorItem(Object parent){
		myParent = parent;
	}
	/**
	 * @generated
	 */
	public Object getParent(){
		return myParent;
	}
}
