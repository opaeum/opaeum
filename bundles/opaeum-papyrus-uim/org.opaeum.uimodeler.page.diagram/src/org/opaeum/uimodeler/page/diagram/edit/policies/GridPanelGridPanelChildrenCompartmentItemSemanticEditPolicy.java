package org.opaeum.uimodeler.page.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.infra.extendedtypes.types.IExtendedHintedElementType;
import org.eclipse.papyrus.infra.extendedtypes.util.ElementTypeUtils;
import org.opaeum.uimodeler.page.diagram.edit.commands.BuiltInActionButtonCreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.BuiltInLinkCreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.GridPanel2CreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.HorizontalPanel2CreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.InvocationButtonCreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.LinkToQueryCreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.TransitionButtonCreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.UimDataTableCreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.UimFieldCreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.VerticalPanel2CreateCommand;
import org.opaeum.uimodeler.page.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class GridPanelGridPanelChildrenCompartmentItemSemanticEditPolicy extends
		UimBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public GridPanelGridPanelChildrenCompartmentItemSemanticEditPolicy() {
		super(UimElementTypes.GridPanel_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		IElementType requestElementType = req.getElementType();
		if (requestElementType == null) {
			return super.getCreateCommand(req);
		}
		IElementType baseElementType = requestElementType;
		boolean isExtendedType = false;
		if (requestElementType instanceof IExtendedHintedElementType) {
			baseElementType = ElementTypeUtils
					.getClosestDiagramType(requestElementType);
			if (baseElementType != null) {
				isExtendedType = true;
			} else {
				// no reference element type ID. using the closest super element type to give more opportunities, but can lead to bugs.
				baseElementType = ElementTypeUtils
						.findClosestNonExtendedElementType((IExtendedHintedElementType) requestElementType);
				isExtendedType = true;
			}
		}
		if (UimElementTypes.UimField_3028 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new UimFieldCreateCommand(req));
		}
		if (UimElementTypes.BuiltInActionButton_3029 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new BuiltInActionButtonCreateCommand(req));
		}
		if (UimElementTypes.HorizontalPanel_3030 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new HorizontalPanel2CreateCommand(req));
		}
		if (UimElementTypes.VerticalPanel_3031 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new VerticalPanel2CreateCommand(req));
		}
		if (UimElementTypes.TransitionButton_3032 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new TransitionButtonCreateCommand(req));
		}
		if (UimElementTypes.InvocationButton_3033 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new InvocationButtonCreateCommand(req));
		}
		if (UimElementTypes.LinkToQuery_3034 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new LinkToQueryCreateCommand(req));
		}
		if (UimElementTypes.BuiltInLink_3035 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new BuiltInLinkCreateCommand(req));
		}
		if (UimElementTypes.UimDataTable_3036 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new UimDataTableCreateCommand(req));
		}
		if (UimElementTypes.GridPanel_3043 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new GridPanel2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
