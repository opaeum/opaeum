package org.opaeum.uimodeler.userinterface.diagram.providers;

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
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLinkNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToQueryNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationButtonName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationButtonNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionButtonNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.parsers.MessageFormatParser;
import org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UimParserProvider extends AbstractProvider implements IParserProvider{
	/**
	 * @generated
	 */
	private IParser gridPanelName_5011Parser;
	/**
	 * @generated
	 */
	private IParser getGridPanelName_5011Parser(){
		if(gridPanelName_5011Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			gridPanelName_5011Parser = parser;
		}
		return gridPanelName_5011Parser;
	}
	/**
	 * @generated
	 */
	private IParser transitionButtonName_5015Parser;
	/**
	 * @generated
	 */
	private IParser getTransitionButtonName_5015Parser(){
		if(transitionButtonName_5015Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			transitionButtonName_5015Parser = parser;
		}
		return transitionButtonName_5015Parser;
	}
	/**
	 * @generated
	 */
	private IParser operationButtonName_5016Parser;
	/**
	 * @generated
	 */
	private IParser getOperationButtonName_5016Parser(){
		if(operationButtonName_5016Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			operationButtonName_5016Parser = parser;
		}
		return operationButtonName_5016Parser;
	}
	/**
	 * @generated
	 */
	private IParser linkToQueryName_5017Parser;
	/**
	 * @generated
	 */
	private IParser getLinkToQueryName_5017Parser(){
		if(linkToQueryName_5017Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			linkToQueryName_5017Parser = parser;
		}
		return linkToQueryName_5017Parser;
	}
	/**
	 * @generated
	 */
	private IParser builtInLinkName_5018Parser;
	/**
	 * @generated
	 */
	private IParser getBuiltInLinkName_5018Parser(){
		if(builtInLinkName_5018Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			builtInLinkName_5018Parser = parser;
		}
		return builtInLinkName_5018Parser;
	}
	/**
	 * @generated
	 */
	private IParser operationButtonName_5019Parser;
	/**
	 * @generated
	 */
	private IParser getOperationButtonName_5019Parser(){
		if(operationButtonName_5019Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			operationButtonName_5019Parser = parser;
		}
		return operationButtonName_5019Parser;
	}
	/**
	 * @generated
	 */
	private IParser gridPanelName_5014Parser;
	/**
	 * @generated
	 */
	private IParser getGridPanelName_5014Parser(){
		if(gridPanelName_5014Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			gridPanelName_5014Parser = parser;
		}
		return gridPanelName_5014Parser;
	}
	/**
	 * @generated
	 */
	protected IParser getParser(int visualID){
		switch(visualID){
		case GridPanelNameEditPart.VISUAL_ID:
			return getGridPanelName_5011Parser();
		case TransitionButtonNameEditPart.VISUAL_ID:
			return getTransitionButtonName_5015Parser();
		case OperationButtonNameEditPart.VISUAL_ID:
			return getOperationButtonName_5016Parser();
		case LinkToQueryNameEditPart.VISUAL_ID:
			return getLinkToQueryName_5017Parser();
		case BuiltInLinkNameEditPart.VISUAL_ID:
			return getBuiltInLinkName_5018Parser();
		case OperationButtonName2EditPart.VISUAL_ID:
			return getOperationButtonName_5019Parser();
		case GridPanelName2EditPart.VISUAL_ID:
			return getGridPanelName_5014Parser();
		}
		return null;
	}
	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type,EObject object,String parserHint){
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}
	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint){
		String vid = (String) hint.getAdapter(String.class);
		if(vid != null){
			return getParser(UimVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if(view != null){
			return getParser(UimVisualIDRegistry.getVisualID(view));
		}
		return null;
	}
	/**
	 * @generated
	 */
	public boolean provides(IOperation operation){
		if(operation instanceof GetParserOperation){
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if(UimElementTypes.getElement(hint) == null){
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}
	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter{
		/**
		 * @generated
		 */
		private final IElementType elementType;
		/**
		 * @generated
		 */
		public HintAdapter(IElementType type,EObject object,String parserHint){
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}
		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter){
			if(IElementType.class.equals(adapter)){
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}
}
