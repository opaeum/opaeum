package org.opaeum.uim.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.opaeum.uim.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uim.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uim.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uim.diagram.part.Messages;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class UimModelingAssistantProvider extends ModelingAssistantProvider{
	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host){
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if(editPart instanceof UserInterfaceEditPart){
			ArrayList<IElementType> types = new ArrayList<IElementType>(3);
			types.add(UimElementTypes.GridPanel_2004);
			types.add(UimElementTypes.HorizontalPanel_2005);
			types.add(UimElementTypes.VerticalPanel_2006);
			return types;
		}
		if(editPart instanceof GridPanelGridPanelChildrenCompartmentEditPart){
			ArrayList<IElementType> types = new ArrayList<IElementType>(9);
			types.add(UimElementTypes.UimField_3001);
			types.add(UimElementTypes.BuiltInAction_3002);
			types.add(UimElementTypes.HorizontalPanel_3003);
			types.add(UimElementTypes.VerticalPanel_3004);
			types.add(UimElementTypes.TransitionAction_3005);
			types.add(UimElementTypes.OperationAction_3006);
			types.add(UimElementTypes.LinkToOperation_3007);
			types.add(UimElementTypes.LinkToEntity_3008);
			types.add(UimElementTypes.UimDataTable_3009);
			return types;
		}
		if(editPart instanceof UimDataTableDataTableColumnCompartmentEditPart){
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(UimElementTypes.UimField_3010);
			return types;
		}
		return Collections.EMPTY_LIST;
	}
	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source){
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return Collections.EMPTY_LIST;
	}
	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target){
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return Collections.EMPTY_LIST;
	}
	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source,IAdaptable target){
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return Collections.EMPTY_LIST;
	}
	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target,IElementType relationshipType){
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return Collections.EMPTY_LIST;
	}
	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source,IElementType relationshipType){
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return Collections.EMPTY_LIST;
	}
	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target,IElementType relationshipType){
		return selectExistingElement(target, getTypesForSource(target, relationshipType));
	}
	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source,IElementType relationshipType){
		return selectExistingElement(source, getTypesForTarget(source, relationshipType));
	}
	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host,Collection types){
		if(types.isEmpty()){
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if(editPart == null){
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		HashSet<EObject> elements = new HashSet<EObject>();
		for(Iterator<EObject> it = diagram.getElement().eAllContents();it.hasNext();){
			EObject element = it.next();
			if(isApplicableElement(element, types)){
				elements.add(element);
			}
		}
		if(elements.isEmpty()){
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements.size()]));
	}
	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element,Collection types){
		IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
		return types.contains(type);
	}
	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements){
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(UimDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
		dialog.setMessage(Messages.UimModelingAssistantProviderMessage);
		dialog.setTitle(Messages.UimModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if(dialog.open() == Window.OK){
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
