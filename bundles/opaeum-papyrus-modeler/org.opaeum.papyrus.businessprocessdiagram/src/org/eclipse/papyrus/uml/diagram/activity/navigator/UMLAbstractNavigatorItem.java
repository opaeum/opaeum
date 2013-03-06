package org.eclipse.papyrus.uml.diagram.activity.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public abstract class UMLAbstractNavigatorItem extends PlatformObject {
	static {
		final Class[] supportedTypes = new Class[]{ ITabbedPropertySheetPageContributor.class };
		final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor() {

			public String getContributorId() {
				return "org.opaeum.papyrus.businessprocessdiagram"; //$NON-NLS-1$
			}
		};
		Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

			public Object getAdapter(Object adaptableObject, Class adapterType) {
				if(adaptableObject instanceof org.eclipse.papyrus.uml.diagram.activity.navigator.UMLAbstractNavigatorItem && adapterType == ITabbedPropertySheetPageContributor.class) {
					return propertySheetPageContributor;
				}
				return null;
			}

			public Class[] getAdapterList() {
				return supportedTypes;
			}
		}, org.eclipse.papyrus.uml.diagram.activity.navigator.UMLAbstractNavigatorItem.class);
	}

	private Object myParent;

	protected UMLAbstractNavigatorItem(Object parent) {
		myParent = parent;
	}

	public Object getParent() {
		return myParent;
	}
}
