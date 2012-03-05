package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.opaeum.uim.diagram.providers.UimElementTypes;

public class UserInterfaceHandler extends CreateCommandHandler{
	@Override
	protected IElementType getElementTypeToCreate(){
		return UimElementTypes.UserInterface_1000;
	}
}
