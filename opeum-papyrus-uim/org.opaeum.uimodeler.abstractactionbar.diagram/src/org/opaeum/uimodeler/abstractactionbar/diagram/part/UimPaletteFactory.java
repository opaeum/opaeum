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
	private final static String CREATEEDITORACTIONBAR1CREATIONTOOL = "createEditorActionBar1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEBUILTINACTIONBUTTON1CREATIONTOOL = "createBuiltinActionButton1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATETAKETRANSITIONBUTTON2CREATIONTOOL = "createTakeTransitionButton2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEOPERATIONCALLBUTTON3CREATIONTOOL = "createOperationCallButton3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATELINKTOQUERY1CREATIONTOOL = "createLinkToQuery1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATEBUILTINLINK2CREATIONTOOL = "createBuiltinLink2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public UimPaletteFactory(){
	}
	/**
	 * @generated
	 */
	public Tool createTool(String toolId){
		if(toolId.equals(CREATEEDITORACTIONBAR1CREATIONTOOL)){
			return createEditorActionBar1CreationTool();
		}
		if(toolId.equals(CREATEBUILTINACTIONBUTTON1CREATIONTOOL)){
			return createBuiltinActionButton1CreationTool();
		}
		if(toolId.equals(CREATETAKETRANSITIONBUTTON2CREATIONTOOL)){
			return createTakeTransitionButton2CreationTool();
		}
		if(toolId.equals(CREATEOPERATIONCALLBUTTON3CREATIONTOOL)){
			return createOperationCallButton3CreationTool();
		}
		if(toolId.equals(CREATELINKTOQUERY1CREATIONTOOL)){
			return createLinkToQuery1CreationTool();
		}
		if(toolId.equals(CREATEBUILTINLINK2CREATIONTOOL)){
			return createBuiltinLink2CreationTool();
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
	private Tool createEditorActionBar1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.EditorActionBar_2011);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createBuiltinActionButton1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.BuiltInActionButton_3004);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createTakeTransitionButton2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.TransitionButton_3005);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createOperationCallButton3CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.OperationButton_3003);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createLinkToQuery1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.LinkToQuery_3002);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createBuiltinLink2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.BuiltInLink_3001);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
