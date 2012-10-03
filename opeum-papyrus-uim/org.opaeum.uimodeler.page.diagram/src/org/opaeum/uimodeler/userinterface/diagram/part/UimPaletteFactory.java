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
	private final static String CREATEBUILTINACTIONBUTTON1CREATIONTOOL = "createBuiltInActionButton1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATETRANSITIONBUTTON2CREATIONTOOL = "createTransitionButton2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEOPERATIONBUTTON3CREATIONTOOL = "createInvocationButton3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATELINKTOQUERY4CREATIONTOOL = "createLinkToQuery4CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEBUILTINLINK5CREATIONTOOL = "createBuiltInLink5CreationTool"; //$NON-NLS-1$
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
		if(toolId.equals(CREATEBUILTINACTIONBUTTON1CREATIONTOOL)){
			return createBuiltInActionButton1CreationTool();
		}
		if(toolId.equals(CREATETRANSITIONBUTTON2CREATIONTOOL)){
			return createTransitionButton2CreationTool();
		}
		if(toolId.equals(CREATEOPERATIONBUTTON3CREATIONTOOL)){
			return createInvocationButton3CreationTool();
		}
		if(toolId.equals(CREATELINKTOQUERY4CREATIONTOOL)){
			return createLinkToQuery4CreationTool();
		}
		if(toolId.equals(CREATEBUILTINLINK5CREATIONTOOL)){
			return createBuiltInLink5CreationTool();
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
	private Tool createBuiltInActionButton1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(UimElementTypes.BuiltInActionButton_3018);
		types.add(UimElementTypes.BuiltInActionButton_3023);
		types.add(UimElementTypes.BuiltInActionButton_3025);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createTransitionButton2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.TransitionButton_3019);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createInvocationButton3CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(UimElementTypes.InvocationButton_3020);
		types.add(UimElementTypes.InvocationButton_3024);
		types.add(UimElementTypes.InvocationButton_3026);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createLinkToQuery4CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.LinkToQuery_3021);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createBuiltInLink5CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.BuiltInLink_3022);
		types.add(UimElementTypes.BuiltInLink_3027);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
