package org.opaeum.topcased.propertysections.filters;

import java.beans.PropertyDescriptor;

import nl.klasse.octopus.model.internal.types.ModelElementImpl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.core.ModelElementItemEx;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.topcased.tabbedproperties.utils.ObjectAdapter;

public abstract class AbstractFilter implements IFilter {
	public abstract boolean select(Element e);

	@SuppressWarnings("restriction")
	@Override
	public boolean select(Object toTest) {
		Element element = null;
		if (!(toTest instanceof Element)) {
			EObject o = ObjectAdapter.adaptObject(toTest);
			if (o instanceof Element) {
				toTest = o;
			} else {
				PropertyDescriptor eObjectProp = IntrospectionUtil.getProperty(
						"EObject", toTest.getClass());
				if (eObjectProp != null) {
					toTest = IntrospectionUtil.get(eObjectProp, toTest);
				} else {
					eObjectProp = IntrospectionUtil.getProperty("UMLElement",
							toTest.getClass());
					if (eObjectProp != null) {
						toTest = IntrospectionUtil.get(eObjectProp, toTest);
					}
				}
			}
		}
		if (toTest instanceof Element) {
			element = (Element) toTest;
			return select(element);
		} else {
			return false;
		}
	}
}
