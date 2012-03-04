package org.opaeum.uim.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.opaeum.uim.diagram.providers.UimElementTypes;

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
	public UimPaletteFactory(){
	}
	/**
	 * @generated
	 */
	public Tool createTool(String toolId){
		if(toolId.equals(CREATEFIELD1CREATIONTOOL)){
			return createField1CreationTool();
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
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.UimField_3001);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createGridPanel1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.GridPanel_2001);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createVerticalPanel2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.VerticalPanel_2003);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createHorizontalPanel3CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.HorizontalPanel_2002);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createBuiltInAction1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.BuiltInAction_3002);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
