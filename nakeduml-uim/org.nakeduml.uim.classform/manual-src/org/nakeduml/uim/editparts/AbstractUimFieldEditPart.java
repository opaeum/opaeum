package org.nakeduml.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.control.ControlKind;
import org.nakeduml.uim.control.UimCheckBox;
import org.nakeduml.uim.control.UimControl;
import org.nakeduml.uim.control.UimDatePopup;
import org.nakeduml.uim.control.UimDropdown;
import org.nakeduml.uim.control.UimMultiSelectListBox;
import org.nakeduml.uim.control.UimMultiSelectPopupSearch;
import org.nakeduml.uim.control.UimMultiSelectTreeView;
import org.nakeduml.uim.control.UimNumberScroller;
import org.nakeduml.uim.control.UimSingleSelectListBox;
import org.nakeduml.uim.control.UimSingleSelectPopupSearch;
import org.nakeduml.uim.control.UimSingleSelectTreeView;
import org.nakeduml.uim.control.UimText;
import org.nakeduml.uim.control.UimTextArea;
import org.nakeduml.uim.control.UimToggleButton;
import org.nakeduml.uim.figures.UimFieldFigure;
import org.nakeduml.uim.figures.controls.UimCheckBoxFigure;
import org.nakeduml.uim.figures.controls.UimDatePopupFigure;
import org.nakeduml.uim.figures.controls.UimDropdownFigure;
import org.nakeduml.uim.figures.controls.UimMultiSelectFigure;
import org.nakeduml.uim.figures.controls.UimNumberScrollerFigure;
import org.nakeduml.uim.figures.controls.UimPopupFigure;
import org.nakeduml.uim.figures.controls.UimTextAreaFigure;
import org.nakeduml.uim.figures.controls.UimTextFigure;
import org.nakeduml.uim.figures.controls.UimToggleButtonFigure;
import org.nakeduml.uim.figures.controls.UimTreeFigure;
import org.nakeduml.uim.util.ControlUtil;
import org.topcased.modeler.di.model.GraphNode;

public class AbstractUimFieldEditPart extends BoundEditPart{
	public AbstractUimFieldEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		return new UimFieldFigure();
	}
	@Override
	protected void refreshVisuals(){
		if(getEObject() instanceof UimField){
			UimField pf = (UimField) getEObject();
			UimFieldFigure fig = (UimFieldFigure) getFigure();
			updateControlFigure();
			fig.getLabel().setText(pf.getName());
			layoutLabel(pf.getLabelWidth());
		}
		super.refreshVisuals();
	}
	private void layoutLabel(int width){
		UimFieldFigure fig = (UimFieldFigure) getFigure();
		fig.setLabelWidth(width);
		fig.getLayoutManager().invalidate();
		fig.revalidate();
	}
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		Object notifier = msg.getNotifier();
		Object newValue = msg.getNewValue();
		Object oldValue = msg.getOldValue();
		if(notifier instanceof UimField && notifier == getEObject() && newValue != oldValue){
			UimFieldFigure fig = (UimFieldFigure) getFigure();
			ControlKind kind = ((UimField) notifier).getControlKind();
			switch(msg.getFeatureID(UimField.class)){
			case UimPackage.UIM_FIELD__CONTROL:
				fig.getControl().setSize(new Dimension(ControlUtil.getPreferredWidth(kind), ControlUtil.getPreferredHeight(kind)));
				updateModelListening(oldValue, newValue);
				refreshVisuals();
				break;
			case UimPackage.UIM_FIELD__BINDING:
				updateModelListening(oldValue, newValue);
				refreshVisuals();
			default:
				break;
			}
		}
		if(notifier instanceof UimControl){
			// refresh all control related visuals
			refreshVisuals();
		}
	}
	private void updateControlFigure(){
		final UimControl control = ((UimField) getEObject()).getControl();
		UimFieldFigure figure = (UimFieldFigure) getFigure();
		if(control instanceof UimText){
			figure.setControl(new UimTextFigure());
		}else if(control instanceof UimDatePopup){
			figure.setControl(new UimDatePopupFigure());
		}else if(control instanceof UimSingleSelectPopupSearch || control instanceof UimMultiSelectPopupSearch){
			figure.setControl(new UimPopupFigure());
		}else if(control instanceof UimSingleSelectListBox || control instanceof UimTextArea){
			figure.setControl(new UimTextAreaFigure());
			figure.setMinimumSize(new Dimension(figure.getMinimumSize().width, 80));
		}else if(control instanceof UimDropdown){
			figure.setControl(new UimDropdownFigure());
		}else if(control instanceof UimCheckBox){
			figure.setControl(new UimCheckBoxFigure());
		}else if(control instanceof UimToggleButton){
			figure.setControl(new UimToggleButtonFigure());
		}else if(control instanceof UimNumberScroller){
			figure.setControl(new UimNumberScrollerFigure());
		}else if(control instanceof UimMultiSelectListBox){
			figure.setControl(new UimMultiSelectFigure());
			figure.setMinimumSize(new Dimension(figure.getMinimumSize().width, 80));
		}else if(control instanceof UimMultiSelectTreeView){
			figure.setControl(new UimTreeFigure());
			figure.setMinimumSize(new Dimension(figure.getMinimumSize().width, 80));
		}else if(control instanceof UimSingleSelectTreeView){
			figure.setControl(new UimTreeFigure());
			figure.setMinimumSize(new Dimension(figure.getMinimumSize().width, 80));
		}
	}
}