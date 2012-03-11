package org.opaeum.uimodeler.abstractactionbar.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.opaeum.uimodeler.abstractactionbar.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimPaletteFactory extends PaletteFactory.Adapter{
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
	private final static String CREATEOPERATIONBUTTON3CREATIONTOOL = "createOperationButton3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public UimPaletteFactory(){
	}
	/**
	 * @generated
	 */
	public Tool createTool(String toolId){
		if(toolId.equals(CREATEBUILTINACTIONBUTTON1CREATIONTOOL)){
			return createBuiltInActionButton1CreationTool();
		}
		if(toolId.equals(CREATETRANSITIONBUTTON2CREATIONTOOL)){
			return createTransitionButton2CreationTool();
		}
		if(toolId.equals(CREATEOPERATIONBUTTON3CREATIONTOOL)){
			return createOperationButton3CreationTool();
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
	private Tool createBuiltInActionButton1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.BuiltInAction_2001);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createTransitionButton2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.TransitionAction_2002);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createOperationButton3CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.OperationAction_2003);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
