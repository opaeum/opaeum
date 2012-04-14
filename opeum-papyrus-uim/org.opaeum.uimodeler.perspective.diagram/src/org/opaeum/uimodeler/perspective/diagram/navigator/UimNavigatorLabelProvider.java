package org.opaeum.uimodeler.perspective.diagram.navigator;

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
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.EditorConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.ExplorerConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PropertiesConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.UimPerspectiveEditPart;
import org.opaeum.uimodeler.perspective.diagram.part.UimPerspectiveDiagramEditorPlugin;
import org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry;
import org.opaeum.uimodeler.perspective.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,ITreePathLabelProvider{
	/**
	 * @generated
	 */
	static{
		UimPerspectiveDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		UimPerspectiveDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
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
			return UimPerspectiveDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
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
		case UimPerspectiveEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://opaeum.org/uimetamodel/perspective/1.0?UimPerspective", UimElementTypes.UimPerspective_1000); //$NON-NLS-1$
		case EditorConfigurationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://opaeum.org/uimetamodel/perspective/1.0?EditorConfiguration", UimElementTypes.EditorConfiguration_2001); //$NON-NLS-1$
		case PropertiesConfigurationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://opaeum.org/uimetamodel/perspective/1.0?PropertiesConfiguration", UimElementTypes.PropertiesConfiguration_2002); //$NON-NLS-1$
		case ExplorerConfigurationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://opaeum.org/uimetamodel/perspective/1.0?ExplorerConfiguration", UimElementTypes.ExplorerConfiguration_2003); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}
	/**
	 * @generated
	 */
	private Image getImage(String key,IElementType elementType){
		ImageRegistry imageRegistry = UimPerspectiveDiagramEditorPlugin.getInstance().getImageRegistry();
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
		case UimPerspectiveEditPart.VISUAL_ID:
			return getUimPerspective_1000Text(view);
		case EditorConfigurationEditPart.VISUAL_ID:
			return getEditorConfiguration_2001Text(view);
		case PropertiesConfigurationEditPart.VISUAL_ID:
			return getPropertiesConfiguration_2002Text(view);
		case ExplorerConfigurationEditPart.VISUAL_ID:
			return getExplorerConfiguration_2003Text(view);
		}
		return getUnknownElementText(view);
	}
	/**
	 * @generated
	 */
	private String getUimPerspective_1000Text(View view){
		return ""; //$NON-NLS-1$
	}
	/**
	 * @generated
	 */
	private String getEditorConfiguration_2001Text(View view){
		EditorConfiguration domainModelElement = (EditorConfiguration) view.getElement();
		if(domainModelElement != null){
			return String.valueOf(domainModelElement.getWidth());
		}else{
			UimPerspectiveDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getPropertiesConfiguration_2002Text(View view){
		PropertiesConfiguration domainModelElement = (PropertiesConfiguration) view.getElement();
		if(domainModelElement != null){
			return String.valueOf(domainModelElement.getWidth());
		}else{
			UimPerspectiveDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getExplorerConfiguration_2003Text(View view){
		ExplorerConfiguration domainModelElement = (ExplorerConfiguration) view.getElement();
		if(domainModelElement != null){
			return String.valueOf(domainModelElement.getWidth());
		}else{
			UimPerspectiveDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2003); //$NON-NLS-1$
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
		return UimPerspectiveEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(view));
	}
}
