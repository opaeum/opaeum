package org.opaeum.eclipse.uml.filters.bpm;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;

public class TaskFilter extends DeadlineContainerFilter{
	@Override
	public boolean select(Element e){
		if(e instanceof Operation){
			return false;
		}else{
			return super.select(e);
		}
	}
}
