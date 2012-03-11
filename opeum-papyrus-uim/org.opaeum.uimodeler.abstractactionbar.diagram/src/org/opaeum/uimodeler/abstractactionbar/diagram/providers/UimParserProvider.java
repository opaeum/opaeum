package org.opaeum.uimodeler.abstractactionbar.diagram.providers;

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
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationActionNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionActionNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.parsers.MessageFormatParser;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UimParserProvider extends AbstractProvider implements IParserProvider{
	/**
	 * @generated
	 */
	private IParser builtInActionName_5001Parser;
	/**
	 * @generated
	 */
	private IParser getBuiltInActionName_5001Parser(){
		if(builtInActionName_5001Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			builtInActionName_5001Parser = parser;
		}
		return builtInActionName_5001Parser;
	}
	/**
	 * @generated
	 */
	private IParser transitionActionName_5002Parser;
	/**
	 * @generated
	 */
	private IParser getTransitionActionName_5002Parser(){
		if(transitionActionName_5002Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			transitionActionName_5002Parser = parser;
		}
		return transitionActionName_5002Parser;
	}
	/**
	 * @generated
	 */
	private IParser operationActionName_5003Parser;
	/**
	 * @generated
	 */
	private IParser getOperationActionName_5003Parser(){
		if(operationActionName_5003Parser == null){
			EAttribute[] features = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			EAttribute[] editableFeatures = new EAttribute[]{UimPackage.eINSTANCE.getUserInteractionElement_Name()};
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			operationActionName_5003Parser = parser;
		}
		return operationActionName_5003Parser;
	}
	/**
	 * @generated
	 */
	protected IParser getParser(int visualID){
		switch(visualID){
		case BuiltInActionNameEditPart.VISUAL_ID:
			return getBuiltInActionName_5001Parser();
		case TransitionActionNameEditPart.VISUAL_ID:
			return getTransitionActionName_5002Parser();
		case OperationActionNameEditPart.VISUAL_ID:
			return getOperationActionName_5003Parser();
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
