package org.opaeum.uimodeler.page.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.infra.extendedtypes.types.IExtendedHintedElementType;
import org.eclipse.papyrus.infra.extendedtypes.util.ElementTypeUtils;
import org.opaeum.uimodeler.page.diagram.edit.commands.BuiltInActionButton2CreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.BuiltInLink2CreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.InvocationButton2CreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.UimField2CreateCommand;
import org.opaeum.uimodeler.page.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimDataTableDataTableColumnCompartmentItemSemanticEditPolicy
		extends UimBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public UimDataTableDataTableColumnCompartmentItemSemanticEditPolicy() {
		super(UimElementTypes.UimDataTable_3036);
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
		if (UimElementTypes.UimField_3037 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new UimField2CreateCommand(req));
		}
		if (UimElementTypes.BuiltInActionButton_3038 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new BuiltInActionButton2CreateCommand(req));
		}
		if (UimElementTypes.InvocationButton_3039 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new InvocationButton2CreateCommand(req));
		}
		if (UimElementTypes.BuiltInLink_3042 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new BuiltInLink2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
