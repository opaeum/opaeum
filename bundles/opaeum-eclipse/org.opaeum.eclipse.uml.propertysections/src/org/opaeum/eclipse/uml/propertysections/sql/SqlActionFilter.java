package org.opaeum.eclipse.uml.propertysections.sql;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opaeum.eclipse.uml.filters.core.AbstractFilter;

public class SqlActionFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e instanceof OpaqueAction){
			OpaqueAction oa=(OpaqueAction) e;
			EList<String> languages = oa.getLanguages();
			for(String string:languages){
				if(string.equalsIgnoreCase("sql")){
					return true;
				}
			}
		}
		return false;
	}
}
