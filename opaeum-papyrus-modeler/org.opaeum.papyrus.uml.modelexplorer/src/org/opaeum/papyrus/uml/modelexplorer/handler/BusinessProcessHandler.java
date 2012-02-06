package org.opaeum.papyrus.uml.modelexplorer.handler;

import org.eclipse.core.commands.IHandler;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BusinessProcessHandler extends StereotypedElementHandler implements IHandler{
	protected IElementType getElementTypeToCreate(){
		return UMLElementTypes.ACTIVITY;
	}
	protected String getStereotype(){
		return StereotypeNames.BUSINES_PROCESS;
	}
	protected String getProfile(){
		return StereotypeNames.OPAEUM_BPM_PROFILE;
	}
}
