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
import org.opaeum.uim.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionNameKindEditPart;
import org.opaeum.uim.diagram.edit.parts.EditorPageEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldNameEditPart;
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
		case EditorPageEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://opaeum.org/uimetamodel/form/1.0?EditorPage", UimElementTypes.EditorPage_1000); //$NON-NLS-1$
		case GridPanelEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?GridPanel", UimElementTypes.GridPanel_2001); //$NON-NLS-1$
		case HorizontalPanelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?HorizontalPanel", UimElementTypes.HorizontalPanel_2002); //$NON-NLS-1$
		case VerticalPanelEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/panel/1.0?VerticalPanel", UimElementTypes.VerticalPanel_2003); //$NON-NLS-1$
		case UimFieldEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/1.0?UimField", UimElementTypes.UimField_3001); //$NON-NLS-1$
		case BuiltInActionEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://opaeum.org/uimetamodel/action/1.0?BuiltInAction", UimElementTypes.BuiltInAction_3002); //$NON-NLS-1$
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
		case EditorPageEditPart.VISUAL_ID:
			return getEditorPage_1000Text(view);
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2001Text(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002Text(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003Text(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3001Text(view);
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_3002Text(view);
		}
		return getUnknownElementText(view);
	}
	/**
	 * @generated
	 */
	private String getEditorPage_1000Text(View view){
		return ""; //$NON-NLS-1$
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
			UimDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5002); //$NON-NLS-1$
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
		return EditorPageEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(view));
	}
}
