package org.opaeum.papyrus.usecasediagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLPaletteFactory;
import org.eclipse.papyrus.uml.diagram.usecase.providers.UMLElementTypes;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.papyrus.common.StereotypeApplyingTypeCreationTool;

public class UseCasePaletteFactory extends UMLPaletteFactory{
	public UseCasePaletteFactory(){
	}
	@Override
	public Tool createTool(String toolId){
		if(toolId.equals("usecase.tool.businessactor")){
			List<IElementType> types = new ArrayList<IElementType>(3);
			types.add(UMLElementTypes.Actor_2011);
			types.add(UMLElementTypes.Actor_3018);
			types.add(UMLElementTypes.Actor_3011);
			Tool tool = new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.BUSINESS_ACTOR);
			return tool;
		}else if(toolId.equals("usecase.tool.businessusecase")){
			List<IElementType> types = new ArrayList<IElementType>(3);
			types.add(UMLElementTypes.UseCase_2013);
			types.add(UMLElementTypes.UseCase_3009);
			types.add(UMLElementTypes.UseCase_3012);
			Tool tool = new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.BUSINESS_USE_CASE);
			return tool;
		}else if(toolId.equals("usecase.tool.businesscomponent")){
			List<IElementType> types = new ArrayList<IElementType>(3);
			types.add(UMLElementTypes.Component_2015);
			types.add(UMLElementTypes.Component_3016);
			types.add(UMLElementTypes.Component_3013);
			Tool tool = new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.BUSINESS_COMPONENT);
			return tool;
		}
		return super.createTool(toolId);
	}
}
