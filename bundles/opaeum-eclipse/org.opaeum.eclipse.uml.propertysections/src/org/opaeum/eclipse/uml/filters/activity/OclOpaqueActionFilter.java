package org.opaeum.eclipse.uml.filters.activity;

import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class OclOpaqueActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof OpaqueAction && !EmfActionUtil.isEmbeddedTask((ActivityNode) e);
	}
}
