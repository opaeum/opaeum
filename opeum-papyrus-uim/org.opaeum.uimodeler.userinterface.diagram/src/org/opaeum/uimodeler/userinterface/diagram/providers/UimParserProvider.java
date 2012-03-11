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
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionName3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToOperationNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionName3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionActionNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldNameEditPart;
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
	private IParser transitionActionName_5003Parser;
	/**
	 * @generated
	 */
	private IParser getTransitionActionName_5003Parser(){
		if(transitionActionName_5003Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			transitionActionName_5003Parser = parser;
		}
		return transitionActionName_5003Parser;
	}
	/**
	 * @generated
	 */
	private IParser linkToOperationName_5005Parser;
	/**
	 * @generated
	 */
	private IParser getLinkToOperationName_5005Parser(){
		if(linkToOperationName_5005Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			linkToOperationName_5005Parser = parser;
		}
		return linkToOperationName_5005Parser;
	}
	/**
	 * @generated
	 */
	private IParser linkToEntityName_5006Parser;
	/**
	 * @generated
	 */
	private IParser getLinkToEntityName_5006Parser(){
		if(linkToEntityName_5006Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			linkToEntityName_5006Parser = parser;
		}
		return linkToEntityName_5006Parser;
	}
	/**
	 * @generated
	 */
	protected IParser getParser(int visualID){
		switch(visualID){
		case GridPanelNameEditPart.VISUAL_ID:
			return getGridPanelName_5011Parser();
		case TransitionActionNameEditPart.VISUAL_ID:
			return getTransitionActionName_5003Parser();
		case LinkToOperationNameEditPart.VISUAL_ID:
			return getLinkToOperationName_5005Parser();
		case LinkToEntityNameEditPart.VISUAL_ID:
			return getLinkToEntityName_5006Parser();
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
