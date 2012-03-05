package org.opaeum.uim.diagram.navigator;

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
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.LinkToEntity;
import org.opaeum.uim.action.LinkToOperation;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.TransitionAction;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionNameKindEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.LinkToEntityEditPart;
import org.opaeum.uim.diagram.edit.parts.LinkToOperationEditPart;
import org.opaeum.uim.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uim.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uim.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uim.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldNameEditPart;
import org.opaeum.uim.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uim.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uim.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;
import org.opaeum.uim.diagram.part.UimVisualIDRegistry;
import org.opaeum.uim.diagram.providers.UimElementTypes;
import org.opaeum.uim.diagram.providers.UimParserProvider;
import org.opaeum.uim.panel.HorizontalPanel;
import org.opaeum.uim.panel.VerticalPanel;

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
		case UserInterfaceEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://opaeum.org/uimetamodel/1.0?UserInterface", UimElementTypes.UserInterface_1000); //$NON-NLS-1$
		case GridPanelEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?GridPanel", UimElementTypes.GridPanel_2004); //$NON-NLS-1$
		case HorizontalPanelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?HorizontalPanel", UimElementTypes.HorizontalPanel_2005); //$NON-NLS-1$
		case VerticalPanelEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?VerticalPanel", UimElementTypes.VerticalPanel_2006); //$NON-NLS-1$
		case UimFieldEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/1.0?UimField", UimElementTypes.UimField_3001); //$NON-NLS-1$
		case BuiltInActionEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInAction", UimElementTypes.BuiltInAction_3002); //$NON-NLS-1$
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/panel/1.0?HorizontalPanel", UimElementTypes.HorizontalPanel_3003); //$NON-NLS-1$
		case VerticalPanel2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/panel/1.0?VerticalPanel", UimElementTypes.VerticalPanel_3004); //$NON-NLS-1$
		case TransitionActionEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?TransitionAction", UimElementTypes.TransitionAction_3005); //$NON-NLS-1$
		case OperationActionEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?OperationAction", UimElementTypes.OperationAction_3006); //$NON-NLS-1$
		case LinkToOperationEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?LinkToOperation", UimElementTypes.LinkToOperation_3007); //$NON-NLS-1$
		case LinkToEntityEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?LinkToEntity", UimElementTypes.LinkToEntity_3008); //$NON-NLS-1$
		case UimDataTableEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/1.0?UimDataTable", UimElementTypes.UimDataTable_3009); //$NON-NLS-1$
		case UimField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/1.0?UimField", UimElementTypes.UimField_3010); //$NON-NLS-1$
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
		case UserInterfaceEditPart.VISUAL_ID:
			return getUserInterface_1000Text(view);
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2004Text(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2005Text(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2006Text(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3001Text(view);
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_3002Text(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003Text(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004Text(view);
		case TransitionActionEditPart.VISUAL_ID:
			return getTransitionAction_3005Text(view);
		case OperationActionEditPart.VISUAL_ID:
			return getOperationAction_3006Text(view);
		case LinkToOperationEditPart.VISUAL_ID:
			return getLinkToOperation_3007Text(view);
		case LinkToEntityEditPart.VISUAL_ID:
			return getLinkToEntity_3008Text(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009Text(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010Text(view);
		}
		return getUnknownElementText(view);
	}
	/**
	 * @generated
	 */
	private String getUserInterface_1000Text(View view){
		UserInterface domainModelElement = (UserInterface) view.getElement();
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
	private String getGridPanel_2004Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.GridPanel_2004, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(GridPanelNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getHorizontalPanel_2005Text(View view){
		HorizontalPanel domainModelElement = (HorizontalPanel) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getVerticalPanel_2006Text(View view){
		VerticalPanel domainModelElement = (VerticalPanel) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getUimField_3001Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.UimField_3001, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(UimFieldNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getBuiltInAction_3002Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.BuiltInAction_3002, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(BuiltInActionNameKindEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getHorizontalPanel_3003Text(View view){
		HorizontalPanel domainModelElement = (HorizontalPanel) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getVerticalPanel_3004Text(View view){
		VerticalPanel domainModelElement = (VerticalPanel) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getTransitionAction_3005Text(View view){
		TransitionAction domainModelElement = (TransitionAction) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getOperationAction_3006Text(View view){
		OperationAction domainModelElement = (OperationAction) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getLinkToOperation_3007Text(View view){
		LinkToOperation domainModelElement = (LinkToOperation) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getLinkToEntity_3008Text(View view){
		LinkToEntity domainModelElement = (LinkToEntity) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getUimDataTable_3009Text(View view){
		UimDataTable domainModelElement = (UimDataTable) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getUimField_3010Text(View view){
		UimField domainModelElement = (UimField) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3010); //$NON-NLS-1$
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
		return UserInterfaceEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(view));
	}
}
