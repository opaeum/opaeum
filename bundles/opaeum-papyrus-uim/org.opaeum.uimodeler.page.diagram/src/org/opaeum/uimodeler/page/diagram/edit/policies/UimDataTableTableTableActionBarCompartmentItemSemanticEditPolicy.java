package org.opaeum.uimodeler.page.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.infra.extendedtypes.types.IExtendedHintedElementType;
import org.eclipse.papyrus.infra.extendedtypes.util.ElementTypeUtils;
import org.opaeum.uimodeler.page.diagram.edit.commands.BuiltInActionButton3CreateCommand;
import org.opaeum.uimodeler.page.diagram.edit.commands.InvocationButton3CreateCommand;
import org.opaeum.uimodeler.page.diagram.providers.UimElementTypes;

/**
 * @generated
 */
public class UimDataTableTableTableActionBarCompartmentItemSemanticEditPolicy
		extends UimBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public UimDataTableTableTableActionBarCompartmentItemSemanticEditPolicy() {
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
		if (UimElementTypes.BuiltInActionButton_3040 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new BuiltInActionButton3CreateCommand(req));
		}
		if (UimElementTypes.InvocationButton_3041 == baseElementType) {
			if (isExtendedType) {
				return getExtendedTypeCreationCommand(req,
						(IExtendedHintedElementType) requestElementType);
			}
			return getGEFWrapper(new InvocationButton3CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
