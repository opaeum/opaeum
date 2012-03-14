package org.opaeum.uimodeler.abstractactionbar.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionButtonNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInLinkNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.EditorActionBarEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.EditorActionBarNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.LinkToQueryNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationButtonEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationButtonNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionButtonNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimDiagramEditorPlugin;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry;
import org.opaeum.uimodeler.abstractactionbar.diagram.providers.UimElementTypes;
import org.opaeum.uimodeler.abstractactionbar.diagram.providers.UimParserProvider;

/**
 * @generated
 */
public class UimNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,ITreePathLabelProvider{
	/**
	 * @generated
	 */
	static{
		UimDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		UimDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}
	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label,TreePath elementPath){
		Object element = elementPath.getLastSegment();
		if(element instanceof UimNavigatorItem && !isOwnView(((UimNavigatorItem) element).getView())){
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}
	/**
	 * @generated
	 */
	public Image getImage(Object element){
		if(element instanceof UimNavigatorGroup){
			UimNavigatorGroup group = (UimNavigatorGroup) element;
			return UimDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}
		if(element instanceof UimNavigatorItem){
			UimNavigatorItem navigatorItem = (UimNavigatorItem) element;
			if(!isOwnView(navigatorItem.getView())){
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}
		return super.getImage(element);
	}
	/**
	 * @generated
	 */
	public Image getImage(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case AbstractEditorEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://opaeum.org/uimetamodel/form/1.0?AbstractEditor", UimElementTypes.AbstractEditor_1000); //$NON-NLS-1$
		case EditorActionBarEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/form/1.0?EditorActionBar", UimElementTypes.EditorActionBar_2011); //$NON-NLS-1$
		case BuiltInLinkEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInLink", UimElementTypes.BuiltInLink_3001); //$NON-NLS-1$
		case LinkToQueryEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?LinkToQuery", UimElementTypes.LinkToQuery_3002); //$NON-NLS-1$
		case OperationButtonEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?OperationButton", UimElementTypes.OperationButton_3003); //$NON-NLS-1$
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInActionButton", UimElementTypes.BuiltInActionButton_3004); //$NON-NLS-1$
		case TransitionButtonEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?TransitionButton", UimElementTypes.TransitionButton_3005); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}
	/**
	 * @generated
	 */
	private Image getImage(String key,IElementType elementType){
		ImageRegistry imageRegistry = UimDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if(image == null && elementType != null && UimElementTypes.isKnownElementType(elementType)){
			image = UimElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}
		if(image == null){
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}
	/**
	 * @generated
	 */
	public String getText(Object element){
		if(element instanceof UimNavigatorGroup){
			UimNavigatorGroup group = (UimNavigatorGroup) element;
			return group.getGroupName();
		}
		if(element instanceof UimNavigatorItem){
			UimNavigatorItem navigatorItem = (UimNavigatorItem) element;
			if(!isOwnView(navigatorItem.getView())){
				return null;
			}
			return getText(navigatorItem.getView());
		}
		return super.getText(element);
	}
	/**
	 * @generated
	 */
	public String getText(View view){
		if(view.getElement() != null && view.getElement().eIsProxy()){
			return getUnresolvedDomainElementProxyText(view);
		}
		switch(UimVisualIDRegistry.getVisualID(view)){
		case AbstractEditorEditPart.VISUAL_ID:
			return getAbstractEditor_1000Text(view);
		case EditorActionBarEditPart.VISUAL_ID:
			return getEditorActionBar_2011Text(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3001Text(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3002Text(view);
		case OperationButtonEditPart.VISUAL_ID:
			return getOperationButton_3003Text(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3004Text(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3005Text(view);
		}
		return getUnknownElementText(view);
	}
	/**
	 * @generated
	 */
	private String getAbstractEditor_1000Text(View view){
		AbstractEditor domainModelElement = (AbstractEditor) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getEditorActionBar_2011Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.EditorActionBar_2011,
				view.getElement() != null ? view.getElement() : view, UimVisualIDRegistry.getType(EditorActionBarNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5016); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getBuiltInLink_3001Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.BuiltInLink_3001, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(BuiltInLinkNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getLinkToQuery_3002Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.LinkToQuery_3002, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(LinkToQueryNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5012); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getOperationButton_3003Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.OperationButton_3003,
				view.getElement() != null ? view.getElement() : view, UimVisualIDRegistry.getType(OperationButtonNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getBuiltInActionButton_3004Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.BuiltInActionButton_3004, view.getElement() != null ? view.getElement()
				: view, UimVisualIDRegistry.getType(BuiltInActionButtonNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5014); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getTransitionButton_3005Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.TransitionButton_3005, view.getElement() != null ? view.getElement()
				: view, UimVisualIDRegistry.getType(TransitionButtonNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getUnknownElementText(View view){
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}
	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view){
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}
	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig){
	}
	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento){
	}
	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento){
	}
	/**
	 * @generated
	 */
	public String getDescription(Object anElement){
		return null;
	}
	/**
	 * @generated
	 */
	private boolean isOwnView(View view){
		return AbstractEditorEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(view));
	}
}
