package org.opaeum.uimodeler.userinterface.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.opaeum.uimodeler.userinterface.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimPaletteFactory extends PaletteFactory.Adapter{
	/**
	 * @generated
	 */
	private final static String CREATEFIELD1CREATIONTOOL = "createField1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEDATATABLE2CREATIONTOOL = "createDataTable2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEGRIDPANEL1CREATIONTOOL = "createGridPanel1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEVERTICALPANEL2CREATIONTOOL = "createVerticalPanel2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEHORIZONTALPANEL3CREATIONTOOL = "createHorizontalPanel3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEBUILTINACTION1CREATIONTOOL = "createBuiltInAction1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATETRANSITIONACTION2CREATIONTOOL = "createTransitionAction2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEOPERATIONACTION3CREATIONTOOL = "createOperationAction3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATELINKTOOPERATION4CREATIONTOOL = "createLinkToOperation4CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATELINKTOENTITY5CREATIONTOOL = "createLinkToEntity5CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public UimPaletteFactory(){
	}
	/**
	 * @generated
	 */
	public Tool createTool(String toolId){
		if(toolId.equals(CREATEFIELD1CREATIONTOOL)){
			return createField1CreationTool();
		}
		if(toolId.equals(CREATEDATATABLE2CREATIONTOOL)){
			return createDataTable2CreationTool();
		}
		if(toolId.equals(CREATEGRIDPANEL1CREATIONTOOL)){
			return createGridPanel1CreationTool();
		}
		if(toolId.equals(CREATEVERTICALPANEL2CREATIONTOOL)){
			return createVerticalPanel2CreationTool();
		}
		if(toolId.equals(CREATEHORIZONTALPANEL3CREATIONTOOL)){
			return createHorizontalPanel3CreationTool();
		}
		if(toolId.equals(CREATEBUILTINACTION1CREATIONTOOL)){
			return createBuiltInAction1CreationTool();
		}
		if(toolId.equals(CREATETRANSITIONACTION2CREATIONTOOL)){
			return createTransitionAction2CreationTool();
		}
		if(toolId.equals(CREATEOPERATIONACTION3CREATIONTOOL)){
			return createOperationAction3CreationTool();
		}
		if(toolId.equals(CREATELINKTOOPERATION4CREATIONTOOL)){
			return createLinkToOperation4CreationTool();
		}
		if(toolId.equals(CREATELINKTOENTITY5CREATIONTOOL)){
			return createLinkToEntity5CreationTool();
		}
		// default return: null
		return null;
	}
	/**
	 * @generated
	 */
	public Object getTemplate(String templateId){
		// default return: null
		return null;
	}
	/**
	 * @generated
	 */
	private Tool createField1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.UimField_3001);
		types.add(UimElementTypes.UimField_3010);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createDataTable2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.UimDataTable_3009);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createGridPanel1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.GridPanel_2001);
		types.add(UimElementTypes.GridPanel_3017);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createVerticalPanel2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.VerticalPanel_3004);
		types.add(UimElementTypes.VerticalPanel_2003);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createHorizontalPanel3CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.HorizontalPanel_3003);
		types.add(UimElementTypes.HorizontalPanel_2002);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createBuiltInAction1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(UimElementTypes.BuiltInAction_3002);
		types.add(UimElementTypes.BuiltInAction_3012);
		types.add(UimElementTypes.BuiltInAction_3015);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createTransitionAction2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.TransitionAction_3005);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createOperationAction3CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(UimElementTypes.OperationAction_3006);
		types.add(UimElementTypes.OperationAction_3014);
		types.add(UimElementTypes.OperationAction_3016);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createLinkToOperation4CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.LinkToOperation_3007);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createLinkToEntity5CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.LinkToEntity_3008);
		types.add(UimElementTypes.LinkToEntity_3011);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
