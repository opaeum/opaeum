package org.opaeum.modeler.compositestructures.filters;

import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.Element;
import org.opaeum.topcased.propertysections.filters.AbstractFilter;


public class DelegationFilter extends AbstractFilter{

	@Override
	public boolean select(Element e){
		return e instanceof Connector && ((Connector)e).getKind()==ConnectorKind.DELEGATION_LITERAL;
	}
}
