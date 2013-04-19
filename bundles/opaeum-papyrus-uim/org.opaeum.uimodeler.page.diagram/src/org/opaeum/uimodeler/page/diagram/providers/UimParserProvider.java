package org.opaeum.uimodeler.page.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.UimPackage;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelName2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonName2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonNameEditPart;
import org.opaeum.uimodeler.page.diagram.parsers.MessageFormatParser;
import org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UimParserProvider extends AbstractProvider implements
		IParserProvider {
	/**
	 * @generated
	 */
	private IParser gridPanelName_5011Parser;

	/**
	 * @generated
	 */
	private IParser getGridPanelName_5011Parser() {
		if (gridPanelName_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			gridPanelName_5011Parser = parser;
		}
		return gridPanelName_5011Parser;
	}

	/**
	 * @generated
	 */
	private IParser transitionButtonName_5020Parser;

	/**
	 * @generated
	 */
	private IParser getTransitionButtonName_5020Parser() {
		if (transitionButtonName_5020Parser == null) {
			EAttribute[] features = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			transitionButtonName_5020Parser = parser;
		}
		return transitionButtonName_5020Parser;
	}

	/**
	 * @generated
	 */
	private IParser invocationButtonName_5021Parser;

	/**
	 * @generated
	 */
	private IParser getInvocationButtonName_5021Parser() {
		if (invocationButtonName_5021Parser == null) {
			EAttribute[] features = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			invocationButtonName_5021Parser = parser;
		}
		return invocationButtonName_5021Parser;
	}

	/**
	 * @generated
	 */
	private IParser linkToQueryName_5022Parser;

	/**
	 * @generated
	 */
	private IParser getLinkToQueryName_5022Parser() {
		if (linkToQueryName_5022Parser == null) {
			EAttribute[] features = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			linkToQueryName_5022Parser = parser;
		}
		return linkToQueryName_5022Parser;
	}

	/**
	 * @generated
	 */
	private IParser builtInLinkName_5023Parser;

	/**
	 * @generated
	 */
	private IParser getBuiltInLinkName_5023Parser() {
		if (builtInLinkName_5023Parser == null) {
			EAttribute[] features = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			builtInLinkName_5023Parser = parser;
		}
		return builtInLinkName_5023Parser;
	}

	/**
	 * @generated
	 */
	private IParser invocationButtonName_5024Parser;

	/**
	 * @generated
	 */
	private IParser getInvocationButtonName_5024Parser() {
		if (invocationButtonName_5024Parser == null) {
			EAttribute[] features = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			invocationButtonName_5024Parser = parser;
		}
		return invocationButtonName_5024Parser;
	}

	/**
	 * @generated
	 */
	private IParser gridPanelName_5025Parser;

	/**
	 * @generated
	 */
	private IParser getGridPanelName_5025Parser() {
		if (gridPanelName_5025Parser == null) {
			EAttribute[] features = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { UimPackage.eINSTANCE
					.getUserInteractionElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			gridPanelName_5025Parser = parser;
		}
		return gridPanelName_5025Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case GridPanelNameEditPart.VISUAL_ID:
			return getGridPanelName_5011Parser();
		case TransitionButtonNameEditPart.VISUAL_ID:
			return getTransitionButtonName_5020Parser();
		case InvocationButtonNameEditPart.VISUAL_ID:
			return getInvocationButtonName_5021Parser();
		case LinkToQueryNameEditPart.VISUAL_ID:
			return getLinkToQueryName_5022Parser();
		case BuiltInLinkNameEditPart.VISUAL_ID:
			return getBuiltInLinkName_5023Parser();
		case InvocationButtonName2EditPart.VISUAL_ID:
			return getInvocationButtonName_5024Parser();
		case GridPanelName2EditPart.VISUAL_ID:
			return getGridPanelName_5025Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(UimVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(UimVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (UimElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {
		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}
}
