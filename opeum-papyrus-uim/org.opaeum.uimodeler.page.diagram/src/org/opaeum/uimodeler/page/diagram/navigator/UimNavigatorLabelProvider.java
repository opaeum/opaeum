package org.opaeum.uimodeler.page.diagram.navigator;

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
import org.opaeum.uim.Page;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.panel.HorizontalPanel;
import org.opaeum.uim.panel.VerticalPanel;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelName2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonName2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uimodeler.page.diagram.part.UimDiagramEditorPlugin;
import org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry;
import org.opaeum.uimodeler.page.diagram.providers.UimElementTypes;
import org.opaeum.uimodeler.page.diagram.providers.UimParserProvider;

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
			return getImage("Navigator?Diagram?http://opaeum.org/uimetamodel/1.0?Page", UimElementTypes.UserInterface_1000); //$NON-NLS-1$
		case GridPanelEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?GridPanel", UimElementTypes.GridPanel_2001); //$NON-NLS-1$
		case HorizontalPanelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?HorizontalPanel", UimElementTypes.HorizontalPanel_2002); //$NON-NLS-1$
		case VerticalPanelEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?VerticalPanel", UimElementTypes.VerticalPanel_2003); //$NON-NLS-1$
		case UimFieldEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/1.0?UimField", UimElementTypes.UimField_3001); //$NON-NLS-1$
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInActionButton", UimElementTypes.BuiltInActionButton_3018); //$NON-NLS-1$
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/panel/1.0?HorizontalPanel", UimElementTypes.HorizontalPanel_3003); //$NON-NLS-1$
		case VerticalPanel2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/panel/1.0?VerticalPanel", UimElementTypes.VerticalPanel_3004); //$NON-NLS-1$
		case TransitionButtonEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?TransitionButton", UimElementTypes.TransitionButton_3019); //$NON-NLS-1$
		case InvocationButtonEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?InvocationButton", UimElementTypes.InvocationButton_3020); //$NON-NLS-1$
		case LinkToQueryEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?LinkToQuery", UimElementTypes.LinkToQuery_3021); //$NON-NLS-1$
		case BuiltInLinkEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInLink", UimElementTypes.BuiltInLink_3022); //$NON-NLS-1$
		case UimDataTableEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/1.0?UimDataTable", UimElementTypes.UimDataTable_3009); //$NON-NLS-1$
		case UimField2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/1.0?UimField", UimElementTypes.UimField_3010); //$NON-NLS-1$
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInActionButton", UimElementTypes.BuiltInActionButton_3023); //$NON-NLS-1$
		case InvocationButton2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?InvocationButton", UimElementTypes.InvocationButton_3024); //$NON-NLS-1$
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInActionButton", UimElementTypes.BuiltInActionButton_3025); //$NON-NLS-1$
		case InvocationButton3EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?InvocationButton", UimElementTypes.InvocationButton_3026); //$NON-NLS-1$
		case BuiltInLink2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInLink", UimElementTypes.BuiltInLink_3027); //$NON-NLS-1$
		case GridPanel2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/panel/1.0?GridPanel", UimElementTypes.GridPanel_3017); //$NON-NLS-1$
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
			return getGridPanel_2001Text(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002Text(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003Text(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3001Text(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3018Text(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003Text(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004Text(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3019Text(view);
		case InvocationButtonEditPart.VISUAL_ID:
			return getInvocationButton_3020Text(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3021Text(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3022Text(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009Text(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010Text(view);
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getBuiltInActionButton_3023Text(view);
		case InvocationButton2EditPart.VISUAL_ID:
			return getInvocationButton_3024Text(view);
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getBuiltInActionButton_3025Text(view);
		case InvocationButton3EditPart.VISUAL_ID:
			return getInvocationButton_3026Text(view);
		case BuiltInLink2EditPart.VISUAL_ID:
			return getBuiltInLink_3027Text(view);
		case GridPanel2EditPart.VISUAL_ID:
			return getGridPanel_3017Text(view);
		}
		return getUnknownElementText(view);
	}
	/**
	 * @generated
	 */
	private String getUserInterface_1000Text(View view){
		Page domainModelElement = (Page) view.getElement();
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
	private String getGridPanel_2001Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.GridPanel_2001, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(GridPanelNameEditPart.VISUAL_ID));
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
	private String getHorizontalPanel_2002Text(View view){
		HorizontalPanel domainModelElement = (HorizontalPanel) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getVerticalPanel_2003Text(View view){
		VerticalPanel domainModelElement = (VerticalPanel) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getUimField_3001Text(View view){
		UimField domainModelElement = (UimField) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getBuiltInActionButton_3018Text(View view){
		BuiltInActionButton domainModelElement = (BuiltInActionButton) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3018); //$NON-NLS-1$
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
	private String getTransitionButton_3019Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.TransitionButton_3019, view.getElement() != null ? view.getElement()
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
	private String getInvocationButton_3020Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.InvocationButton_3020,
				view.getElement() != null ? view.getElement() : view, UimVisualIDRegistry.getType(InvocationButtonNameEditPart.VISUAL_ID));
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
	private String getLinkToQuery_3021Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.LinkToQuery_3021, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(LinkToQueryNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getBuiltInLink_3022Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.BuiltInLink_3022, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(BuiltInLinkNameEditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5018); //$NON-NLS-1$
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
	private String getBuiltInActionButton_3023Text(View view){
		BuiltInActionButton domainModelElement = (BuiltInActionButton) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3023); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getInvocationButton_3024Text(View view){
		InvocationButton domainModelElement = (InvocationButton) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3024); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getBuiltInActionButton_3025Text(View view){
		BuiltInActionButton domainModelElement = (BuiltInActionButton) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3025); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getInvocationButton_3026Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.InvocationButton_3026,
				view.getElement() != null ? view.getElement() : view, UimVisualIDRegistry.getType(InvocationButtonName2EditPart.VISUAL_ID));
		if(parser != null){
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view), ParserOptions.NONE.intValue());
		}else{
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5019); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getBuiltInLink_3027Text(View view){
		BuiltInLink domainModelElement = (BuiltInLink) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3027); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getGridPanel_3017Text(View view){
		IParser parser = UimParserProvider.getParser(UimElementTypes.GridPanel_3017, view.getElement() != null ? view.getElement() : view,
				UimVisualIDRegistry.getType(GridPanelName2EditPart.VISUAL_ID));
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
