package org.opaeum.uimodeler.page.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.opaeum.uimodeler.page.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimPaletteFactory extends PaletteFactory.Adapter {
	/**
	 * @generated
	 */
	public final static String CREATEFIELD1CREATIONTOOL = "createField1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATEDATATABLE2CREATIONTOOL = "createDataTable2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATEGRIDPANEL1CREATIONTOOL = "createGridPanel1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATEVERTICALPANEL2CREATIONTOOL = "createVerticalPanel2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATEHORIZONTALPANEL3CREATIONTOOL = "createHorizontalPanel3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATEBUILTINACTIONBUTTON1CREATIONTOOL = "createBuiltInActionButton1CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATETRANSITIONBUTTON2CREATIONTOOL = "createTransitionButton2CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATEINVOCATIONBUTTON3CREATIONTOOL = "createInvocationButton3CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATELINKTOQUERY4CREATIONTOOL = "createLinkToQuery4CreationTool"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public final static String CREATEBUILTINLINK5CREATIONTOOL = "createBuiltInLink5CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public UimPaletteFactory() {

	}

	/**
	 * @generated
	 */
	public Tool createTool(String toolId) {
		if (toolId.equals(CREATEFIELD1CREATIONTOOL)) {
			return createField1CreationTool();
		}
		if (toolId.equals(CREATEDATATABLE2CREATIONTOOL)) {
			return createDataTable2CreationTool();
		}
		if (toolId.equals(CREATEGRIDPANEL1CREATIONTOOL)) {
			return createGridPanel1CreationTool();
		}
		if (toolId.equals(CREATEVERTICALPANEL2CREATIONTOOL)) {
			return createVerticalPanel2CreationTool();
		}
		if (toolId.equals(CREATEHORIZONTALPANEL3CREATIONTOOL)) {
			return createHorizontalPanel3CreationTool();
		}
		if (toolId.equals(CREATEBUILTINACTIONBUTTON1CREATIONTOOL)) {
			return createBuiltInActionButton1CreationTool();
		}
		if (toolId.equals(CREATETRANSITIONBUTTON2CREATIONTOOL)) {
			return createTransitionButton2CreationTool();
		}
		if (toolId.equals(CREATEINVOCATIONBUTTON3CREATIONTOOL)) {
			return createInvocationButton3CreationTool();
		}
		if (toolId.equals(CREATELINKTOQUERY4CREATIONTOOL)) {
			return createLinkToQuery4CreationTool();
		}
		if (toolId.equals(CREATEBUILTINLINK5CREATIONTOOL)) {
			return createBuiltInLink5CreationTool();
		}

		// default return: null
		return null;
	}

	/**
	 * @generated
	 */
	public Object getTemplate(String templateId) {

		// default return: null
		return null;
	}

	/**
	 * @generated
	 */
	private Tool createField1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.UimField_3028);
		types.add(UimElementTypes.UimField_3037);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDataTable2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.UimDataTable_3036);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createGridPanel1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.GridPanel_2001);
		types.add(UimElementTypes.GridPanel_3043);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createVerticalPanel2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.VerticalPanel_3031);
		types.add(UimElementTypes.VerticalPanel_2003);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createHorizontalPanel3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.HorizontalPanel_3030);
		types.add(UimElementTypes.HorizontalPanel_2002);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createBuiltInActionButton1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(UimElementTypes.BuiltInActionButton_3029);
		types.add(UimElementTypes.BuiltInActionButton_3038);
		types.add(UimElementTypes.BuiltInActionButton_3040);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createTransitionButton2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.TransitionButton_3032);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createInvocationButton3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(UimElementTypes.InvocationButton_3033);
		types.add(UimElementTypes.InvocationButton_3039);
		types.add(UimElementTypes.InvocationButton_3041);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLinkToQuery4CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UimElementTypes.LinkToQuery_3034);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createBuiltInLink5CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UimElementTypes.BuiltInLink_3035);
		types.add(UimElementTypes.BuiltInLink_3042);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}
}
