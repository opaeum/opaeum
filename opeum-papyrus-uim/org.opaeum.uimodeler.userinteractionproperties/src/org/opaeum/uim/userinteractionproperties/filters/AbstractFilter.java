package org.opaeum.uim.userinteractionproperties.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.UserInteractionElement;
import org.topcased.tabbedproperties.utils.ObjectAdapter;

public abstract class AbstractFilter implements IFilter {
	public abstract boolean select(UserInteractionElement e);

	@Override
	public boolean select(Object toTest) {
		UserInteractionElement element = null;
		if (!(toTest instanceof Element)) {
			EObject o = ObjectAdapter.adaptObject(toTest);
			if (o instanceof UserInteractionElement) {
				toTest = o;
			}
		}
		if (toTest instanceof UserInteractionElement) {
			element = (UserInteractionElement) toTest;
			return select(element);
		} else {
			return false;
		}
	}
}
