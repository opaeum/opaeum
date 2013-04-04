package org.opaeum.uimodeler.page.diagram.edit.parts;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.papyrus.uml.diagram.common.figure.node.IMultilineEditableFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UimEditPartFactory implements EditPartFactory {
	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (UimVisualIDRegistry.getVisualID(view)) {

			case PageEditPart.VISUAL_ID:
				return new PageEditPart(view);

			case GridPanelEditPart.VISUAL_ID:
				return new GridPanelEditPart(view);

			case GridPanelNameEditPart.VISUAL_ID:
				return new GridPanelNameEditPart(view);

			case HorizontalPanelEditPart.VISUAL_ID:
				return new HorizontalPanelEditPart(view);

			case VerticalPanelEditPart.VISUAL_ID:
				return new VerticalPanelEditPart(view);

			case UimFieldEditPart.VISUAL_ID:
				return new UimFieldEditPart(view);

			case BuiltInActionButtonEditPart.VISUAL_ID:
				return new BuiltInActionButtonEditPart(view);

			case HorizontalPanel2EditPart.VISUAL_ID:
				return new HorizontalPanel2EditPart(view);

			case VerticalPanel2EditPart.VISUAL_ID:
				return new VerticalPanel2EditPart(view);

			case TransitionButtonEditPart.VISUAL_ID:
				return new TransitionButtonEditPart(view);

			case TransitionButtonNameEditPart.VISUAL_ID:
				return new TransitionButtonNameEditPart(view);

			case InvocationButtonEditPart.VISUAL_ID:
				return new InvocationButtonEditPart(view);

			case InvocationButtonNameEditPart.VISUAL_ID:
				return new InvocationButtonNameEditPart(view);

			case LinkToQueryEditPart.VISUAL_ID:
				return new LinkToQueryEditPart(view);

			case LinkToQueryNameEditPart.VISUAL_ID:
				return new LinkToQueryNameEditPart(view);

			case BuiltInLinkEditPart.VISUAL_ID:
				return new BuiltInLinkEditPart(view);

			case BuiltInLinkNameEditPart.VISUAL_ID:
				return new BuiltInLinkNameEditPart(view);

			case UimDataTableEditPart.VISUAL_ID:
				return new UimDataTableEditPart(view);

			case UimField2EditPart.VISUAL_ID:
				return new UimField2EditPart(view);

			case BuiltInActionButton2EditPart.VISUAL_ID:
				return new BuiltInActionButton2EditPart(view);

			case InvocationButton2EditPart.VISUAL_ID:
				return new InvocationButton2EditPart(view);

			case BuiltInActionButton3EditPart.VISUAL_ID:
				return new BuiltInActionButton3EditPart(view);

			case InvocationButton3EditPart.VISUAL_ID:
				return new InvocationButton3EditPart(view);

			case InvocationButtonName2EditPart.VISUAL_ID:
				return new InvocationButtonName2EditPart(view);

			case BuiltInLink2EditPart.VISUAL_ID:
				return new BuiltInLink2EditPart(view);

			case GridPanel2EditPart.VISUAL_ID:
				return new GridPanel2EditPart(view);

			case GridPanelName2EditPart.VISUAL_ID:
				return new GridPanelName2EditPart(view);

			case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
				return new GridPanelGridPanelChildrenCompartmentEditPart(view);

			case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
				return new UimDataTableDataTableColumnCompartmentEditPart(view);

			case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
				return new UimDataTableTableTableActionBarCompartmentEditPart(
						view);

			case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
				return new GridPanelGridPanelChildrenCompartment2EditPart(view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof IMultilineEditableFigure) {
			return new MultilineCellEditorLocator(
					(IMultilineEditableFigure) source.getFigure());
		} else {
			return CellEditorLocatorAccess.INSTANCE
					.getTextCellEditorLocator(source);

		}
	}

	/**
	 * @generated
	 */
	static private class MultilineCellEditorLocator implements
			CellEditorLocator {
		/**
		 * @generated
		 */
		private IMultilineEditableFigure multilineEditableFigure;

		/**
		 * @generated
		 */
		public MultilineCellEditorLocator(IMultilineEditableFigure figure) {
			this.multilineEditableFigure = figure;
		}

		/**
		 * @generated
		 */
		public IMultilineEditableFigure getMultilineEditableFigure() {
			return multilineEditableFigure;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getMultilineEditableFigure().getBounds().getCopy();
			rect.x = getMultilineEditableFigure().getEditionLocation().x;
			rect.y = getMultilineEditableFigure().getEditionLocation().y;
			getMultilineEditableFigure().translateToAbsolute(rect);
			if (getMultilineEditableFigure().getText().length() > 0) {
				rect.setSize(new Dimension(text.computeSize(rect.width,
						SWT.DEFAULT)));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
