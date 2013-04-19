package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;

public class PackageNoProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		return e instanceof Package && !(e instanceof Profile);
	}
}
