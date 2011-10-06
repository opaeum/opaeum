package org.opaeum.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimCheckBox;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.control.UimDatePopup;
import org.opaeum.uim.control.UimDropdown;
import org.opaeum.uim.control.UimMultiSelectListBox;
import org.opaeum.uim.control.UimMultiSelectPopupSearch;
import org.opaeum.uim.control.UimMultiSelectTreeView;
import org.opaeum.uim.control.UimNumberScroller;
import org.opaeum.uim.control.UimSingleSelectListBox;
import org.opaeum.uim.control.UimSingleSelectPopupSearch;
import org.opaeum.uim.control.UimSingleSelectTreeView;
import org.opaeum.uim.control.UimText;
import org.opaeum.uim.control.UimTextArea;
import org.opaeum.uim.control.UimToggleButton;
import org.opaeum.uim.figures.ColumnFigure;
import org.opaeum.uim.figures.UimFieldFigure;
import org.opaeum.uim.figures.controls.UimCheckBoxFigure;
import org.opaeum.uim.figures.controls.UimDatePopupFigure;
import org.opaeum.uim.figures.controls.UimDropdownFigure;
import org.opaeum.uim.figures.controls.UimMultiSelectFigure;
import org.opaeum.uim.figures.controls.UimNumberScrollerFigure;
import org.opaeum.uim.figures.controls.UimPopupFigure;
import org.opaeum.uim.figures.controls.UimTextAreaFigure;
import org.opaeum.uim.figures.controls.UimTextFigure;
import org.opaeum.uim.figures.controls.UimToggleButtonFigure;
import org.opaeum.uim.figures.controls.UimTreeFigure;
import org.opaeum.uim.util.ControlUtil;
import org.topcased.modeler.di.model.GraphNode;

public class AbstractUimFieldEditPart extends BoundEditPart{
	boolean refreshing = false;
	LayoutListener listener = new LayoutListener(){
		@Override
		public void invalidate(IFigure container){
			Rectangle constraint = (Rectangle) container.getLayoutManager().getConstraint(getFigure());
			if(!refreshing && constraint!=null){
				UimField uimField = (UimField) getEObject();
				if(container instanceof ColumnFigure){
					if(uimField.getLabelWidth()==null ||constraint.width != uimField.getLabelWidth()){
						uimField.setLabelWidth(constraint.width);
					}
				}else{
//					if(constraint.width != (uimField.getLabelWidth()+Integer.parseInt(uimField.getControl().getWidth()))){
//						uimField.setLabelWidth(constraint.width-uimField.getControl().getWidth());
//					}
				}
			}
		}
		@Override
		public boolean layout(IFigure container){
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void postLayout(IFigure container){
			// TODO Auto-generated method stub
		}
		@Override
		public void remove(IFigure child){
			// TODO Auto-generated method stub
		}
		@Override
		public void setConstraint(IFigure child,Object constraint){
			// TODO Auto-generated method stub
		}
	};
	public AbstractUimFieldEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		UimFieldFigure uimFieldFigure = new UimFieldFigure();
		new DirectEditHelper(uimFieldFigure, this);
		return uimFieldFigure;
	}
	@Override
	protected void refreshVisuals(){
		refreshing = true;
		if(getEObject() instanceof UimField){
			UimField pf = (UimField) getEObject();
			UimFieldFigure fig = (UimFieldFigure) getFigure();
			updateControlFigure();
			fig.getLabel().setText(pf.getName());
			layoutLabel(pf.getLabelWidth());
		}
		super.refreshVisuals();
		refreshing = false;
	}
	private void layoutLabel(int width){
		UimFieldFigure fig = (UimFieldFigure) getFigure();
		fig.setLabelWidth(width);
		fig.getParent().getParent().invalidateTree();
		fig.getParent().getParent().revalidate();
	}
	@Override
	public void activate(){
		super.activate();
		getFigure().getParent().addLayoutListener(listener);
	}
	@Override
	public void deactivate(){
		super.deactivate();
		getFigure().getParent().removeLayoutListener(listener);
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
				updateControlFigure();
				super.refreshVisuals();
				break;
			case UimPackage.UIM_FIELD__BINDING:
				updateModelListening(oldValue, newValue);
				super.refreshVisuals();
				break;
			case UimPackage.UIM_FIELD__LABEL_WIDTH:
				Integer newWidth = (Integer) msg.getNewValue();
				layoutLabel(newWidth);
				break;
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