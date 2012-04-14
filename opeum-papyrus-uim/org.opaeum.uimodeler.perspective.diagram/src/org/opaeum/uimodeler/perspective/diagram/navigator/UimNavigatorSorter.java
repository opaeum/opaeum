package org.opaeum.uimodeler.perspective.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;
import org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UimNavigatorSorter extends ViewerSorter{
	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 2005;
	/**
	 * @generated
	 */
	public int category(Object element){
		if(element instanceof UimNavigatorItem){
			UimNavigatorItem item = (UimNavigatorItem) element;
			return UimVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}
}
