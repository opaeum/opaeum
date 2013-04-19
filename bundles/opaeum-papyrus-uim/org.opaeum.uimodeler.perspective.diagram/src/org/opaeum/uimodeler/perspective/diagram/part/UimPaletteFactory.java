package org.opaeum.uimodeler.perspective.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.opaeum.uimodeler.perspective.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimPaletteFactory extends PaletteFactory.Adapter{
	/**
	 * @generated
	 */
	private final static String CREATEEDITORCONFIGURATION1CREATIONTOOL = "createEditorConfiguration1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEPROPERTIESCONFIGURATION2CREATIONTOOL = "createPropertiesConfiguration2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEEXPLORERCONFIGURATION3CREATIONTOOL = "createExplorerConfiguration3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public UimPaletteFactory(){
	}
	/**
	 * @generated
	 */
	public Tool createTool(String toolId){
		if(toolId.equals(CREATEEDITORCONFIGURATION1CREATIONTOOL)){
			return createEditorConfiguration1CreationTool();
		}
		if(toolId.equals(CREATEPROPERTIESCONFIGURATION2CREATIONTOOL)){
			return createPropertiesConfiguration2CreationTool();
		}
		if(toolId.equals(CREATEEXPLORERCONFIGURATION3CREATIONTOOL)){
			return createExplorerConfiguration3CreationTool();
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
	private Tool createEditorConfiguration1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.EditorConfiguration_2001);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createPropertiesConfiguration2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.PropertiesConfiguration_2002);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createExplorerConfiguration3CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.ExplorerConfiguration_2003);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
