package org.opaeum.papyrus.uml.diagram.clazz.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.papyrus.common.StereotypeApplyingTypeCreationTool;

public class UMLPaletteFactory extends org.eclipse.papyrus.uml.diagram.clazz.part.UMLPaletteFactory{
	public UMLPaletteFactory(){
	}
	@Override
	public Tool createTool(String toolId){
		if(toolId.equals("clazz.tool.businesscomponent")){
			return createBusinessComponentCreationTool();
		}else if(toolId.equals("clazz.tool.businessrole")){
			return createBusinessRoleCreationTool();
		}else if(toolId.equals("clazz.tool.businessservice")){
			return createInterface10CreationTool();
		}else if(toolId.equals("clazz.tool.notification")){
			return createSignal20CreationTool();
		}
		return super.createTool(toolId);
	}
	private Tool createSignal20CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Signal_2003);
		types.add(UMLElementTypes.Signal_3022);
		Tool tool = new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.NOTIFICATION);
		return tool;
	}
	private Tool createInterface10CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Interface_2004);
		types.add(UMLElementTypes.Interface_3023);
		Tool tool = new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.BUSINESS_SERVICE);
		return tool;
	}
	private Tool createBusinessComponentCreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Component_2002);
		types.add(UMLElementTypes.Component_3021);
		return new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.BUSINESS_COMPONENT);
	}
	private Tool createBusinessRoleCreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Class_3004);
		types.add(UMLElementTypes.Class_3008);
		types.add(UMLElementTypes.Class_3010);
		types.add(UMLElementTypes.Class_3014);
		types.add(UMLElementTypes.Class_2008);
		return new StereotypeApplyingTypeCreationTool(types, StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.BUSINESS_ROLE);
	}
}
