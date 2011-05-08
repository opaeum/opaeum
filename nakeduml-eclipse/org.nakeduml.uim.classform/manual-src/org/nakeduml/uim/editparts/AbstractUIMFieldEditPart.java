package org.nakeduml.uim.editparts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.nakeduml.uim.UIMCheckBox;
import org.nakeduml.uim.UIMControl;
import org.nakeduml.uim.UIMDatePopup;
import org.nakeduml.uim.UIMDropdown;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMMultiSelectListBox;
import org.nakeduml.uim.UIMMultiSelectPopupSearch;
import org.nakeduml.uim.UIMMultiSelectTreeView;
import org.nakeduml.uim.UIMNumberScroller;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.UIMSingleSelectListBox;
import org.nakeduml.uim.UIMSingleSelectPopupSearch;
import org.nakeduml.uim.UIMSingleSelectTreeView;
import org.nakeduml.uim.UIMText;
import org.nakeduml.uim.UIMTextArea;
import org.nakeduml.uim.UIMToggleButton;
import org.nakeduml.uim.figures.UIMFieldFigure;
import org.nakeduml.uim.figures.controls.UIMCheckBoxFigure;
import org.nakeduml.uim.figures.controls.UIMDropdownFigure;
import org.nakeduml.uim.figures.controls.UIMMultiSelectFigure;
import org.nakeduml.uim.figures.controls.UIMNumberScrollerFigure;
import org.nakeduml.uim.figures.controls.UIMPopupFigure;
import org.nakeduml.uim.figures.controls.UIMTextAreaFigure;
import org.nakeduml.uim.figures.controls.UIMTextFigure;
import org.nakeduml.uim.figures.controls.UIMToggleButtonFigure;
import org.nakeduml.uim.figures.controls.UIMTreeFigure;
import org.topcased.modeler.di.model.GraphNode;

public class AbstractUIMFieldEditPart extends BoundEditPart {
	public AbstractUIMFieldEditPart(GraphNode obj) {
		super(obj);
	}

	protected IFigure createFigure() {

		return new UIMFieldFigure();
	}

	@Override
	protected void refreshVisuals() {
		if (getEObject() instanceof UIMField) {
			UIMField pf = (UIMField) getEObject();
			UIMFieldFigure fig = (UIMFieldFigure) getFigure();
			updateControlFigure();
			fig.getLabel().setText(pf.getName());
			layoutLabel(pf.getLabelWidth());
		}
		super.refreshVisuals();
	}

	private void layoutLabel(int width) {
		UIMFieldFigure fig = (UIMFieldFigure) getFigure();
		fig.setLabelWidth(width);
		fig.getLayoutManager().invalidate();
		fig.revalidate();
	}

	protected void handleModelChanged(Notification msg) {
		super.handleModelChanged(msg);
		Object notifier = msg.getNotifier();
		Object newValue = msg.getNewValue();
		Object oldValue = msg.getOldValue();

		if (notifier instanceof UIMField && notifier == getEObject()
				&& newValue != oldValue) {
			switch (msg.getFeatureID(UIMField.class)) {
			case UIMPackage.UIM_FIELD__CONTROL:
				updateModelListening(oldValue, newValue);
				refreshVisuals();
			default:
				break;
			}
		}
		if (notifier instanceof UIMControl) {
			// refresh all control related visuals
			refreshVisuals();
		}

	}

	private void updateControlFigure() {
		final UIMControl control = ((UIMField) getEObject()).getControl();
		UIMFieldFigure figure = (UIMFieldFigure) getFigure();
		if (control instanceof UIMText) {
			figure.setControl(new UIMTextFigure());
		} else if (control instanceof UIMDatePopup
				|| control instanceof UIMSingleSelectPopupSearch
				|| control instanceof UIMMultiSelectPopupSearch) {
			figure.setControl(new UIMPopupFigure());
		} else if (control instanceof UIMSingleSelectListBox
				|| control instanceof UIMSingleSelectTreeView
				|| control instanceof UIMTextArea) {
			figure.setControl(new UIMTextAreaFigure());
			figure.setMinimumSize(new Dimension(figure.getMinimumSize().width,
					80));
		} else if (control instanceof UIMDropdown) {
			figure.setControl(new UIMDropdownFigure());
		} else if (control instanceof UIMCheckBox) {
			figure.setControl(new UIMCheckBoxFigure());
		} else if (control instanceof UIMToggleButton) {
			figure.setControl(new UIMToggleButtonFigure());
		} else if (control instanceof UIMNumberScroller) {
			figure.setControl(new UIMNumberScrollerFigure());
		} else if (control instanceof UIMMultiSelectListBox
				) {
			figure.setControl(new UIMMultiSelectFigure());
			figure.setMinimumSize(new Dimension(figure.getMinimumSize().width,
					80));
		} else if ( control instanceof UIMMultiSelectTreeView) {
			figure.setControl(new UIMTreeFigure());
			figure.setMinimumSize(new Dimension(figure.getMinimumSize().width,
					80));
		}
	}
}