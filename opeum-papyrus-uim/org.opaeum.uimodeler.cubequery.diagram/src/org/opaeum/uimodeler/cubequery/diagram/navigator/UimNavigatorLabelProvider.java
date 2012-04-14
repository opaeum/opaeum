package org.opaeum.uimodeler.cubequery.diagram.navigator;

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
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.ColumnAxisEntryEditPart;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.CubeQueryEditPart;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.RowAxisEntryEditPart;
import org.opaeum.uimodeler.cubequery.diagram.part.UimCubeQueryDiagramEditorPlugin;
import org.opaeum.uimodeler.cubequery.diagram.part.UimVisualIDRegistry;
import org.opaeum.uimodeler.cubequery.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,ITreePathLabelProvider{
	/**
	 * @generated
	 */
	static{
		UimCubeQueryDiagramEditorPlugin.getInstance().getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		UimCubeQueryDiagramEditorPlugin.getInstance().getImageRegistry()
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
			return UimCubeQueryDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
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
		case CubeQueryEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://opaeum.org/uimetamodel/cube/1.0?CubeQuery", UimElementTypes.CubeQuery_1000); //$NON-NLS-1$
		case ColumnAxisEntryEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/cube/1.0?ColumnAxisEntry", UimElementTypes.ColumnAxisEntry_2001); //$NON-NLS-1$
		case RowAxisEntryEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://opaeum.org/uimetamodel/cube/1.0?RowAxisEntry", UimElementTypes.RowAxisEntry_2002); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}
	/**
	 * @generated
	 */
	private Image getImage(String key,IElementType elementType){
		ImageRegistry imageRegistry = UimCubeQueryDiagramEditorPlugin.getInstance().getImageRegistry();
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
		case CubeQueryEditPart.VISUAL_ID:
			return getCubeQuery_1000Text(view);
		case ColumnAxisEntryEditPart.VISUAL_ID:
			return getColumnAxisEntry_2001Text(view);
		case RowAxisEntryEditPart.VISUAL_ID:
			return getRowAxisEntry_2002Text(view);
		}
		return getUnknownElementText(view);
	}
	/**
	 * @generated
	 */
	private String getCubeQuery_1000Text(View view){
		CubeQuery domainModelElement = (CubeQuery) view.getElement();
		if(domainModelElement != null){
			return domainModelElement.getName();
		}else{
			UimCubeQueryDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getColumnAxisEntry_2001Text(View view){
		return ""; //$NON-NLS-1$
	}
	/**
	 * @generated
	 */
	private String getRowAxisEntry_2002Text(View view){
		return ""; //$NON-NLS-1$
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
		return CubeQueryEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(view));
	}
}
