package org.opaeum.eclipse.uml.filters.core;

import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;

public class PersistentClassNotInProfileFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e.getModel() == null){
			return false;
		}else{
			boolean entity = e instanceof org.eclipse.uml2.uml.Class
					&& !(e instanceof Behavior && !EmfBehaviorUtil.hasExecutionInstance((Behavior) e));
			return entity || e instanceof Actor || isStructuredDataTyp(e) || isBusinessCollaboration(e) ;
		}
	}

	protected boolean isBusinessCollaboration(Element e){
		boolean businessCollaboration = e instanceof Collaboration && EmfClassifierUtil.isBusinessCollaboration((Type) e);
		return businessCollaboration;
	}

	protected boolean isStructuredDataTyp(Element e){
		boolean structuredDataType = e instanceof DataType && EmfClassifierUtil.isStructuredDataType((Type) e);
		return structuredDataType;
	}
}
