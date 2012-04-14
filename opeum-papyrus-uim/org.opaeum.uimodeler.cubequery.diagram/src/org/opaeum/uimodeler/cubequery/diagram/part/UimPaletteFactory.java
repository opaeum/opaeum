package org.opaeum.uimodeler.cubequery.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.opaeum.uimodeler.cubequery.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimPaletteFactory extends PaletteFactory.Adapter{
	/**
	 * @generated
	 */
	private final static String CREATEROWAXISENTRY1CREATIONTOOL = "createRowAxisEntry1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	private final static String CREATECOLUMNAXISENTRY2CREATIONTOOL = "createColumnAxisEntry2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public UimPaletteFactory(){
	}
	/**
	 * @generated
	 */
	public Tool createTool(String toolId){
		if(toolId.equals(CREATEROWAXISENTRY1CREATIONTOOL)){
			return createRowAxisEntry1CreationTool();
		}
		if(toolId.equals(CREATECOLUMNAXISENTRY2CREATIONTOOL)){
			return createColumnAxisEntry2CreationTool();
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
	private Tool createRowAxisEntry1CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.RowAxisEntry_2002);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
	/**
	 * @generated
	 */
	private Tool createColumnAxisEntry2CreationTool(){
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.ColumnAxisEntry_2001);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
