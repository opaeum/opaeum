package org.opaeum.papyrus.uml.diagram.businessprocess.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.activity.providers.UMLElementTypes;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.papyrus.common.StereotypeApplyingTypeCreationTool;

public class UMLPaletteFactory extends org.eclipse.papyrus.uml.diagram.activity.part.UMLPaletteFactory{
	public UMLPaletteFactory(){
	}
	@Override
	public Tool createTool(String toolId){
		if(toolId.equals("createEmbeddedTask10CreationTool")){
			return createOpaqueAction1CreationTool();
		}
		return super.createTool(toolId);
	}
	private Tool createOpaqueAction1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.OpaqueAction_3007);
		return new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK);
	}
}
