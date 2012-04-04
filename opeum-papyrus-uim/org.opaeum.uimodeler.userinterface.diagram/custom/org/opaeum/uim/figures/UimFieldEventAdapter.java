package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.Orientation;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.swt.IUimFieldComposite;
import org.opaeum.uim.swt.LinkComposite;
import org.opaeum.uim.swt.UimSwtUtil;
import org.opaeum.uimodeler.common.figures.AbstractEventAdapter;
import org.opaeum.uimodeler.common.figures.IUimFieldFigure;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;

public final class UimFieldEventAdapter extends AbstractEventAdapter{
	private IUimFieldFigure fig;
	private int minimumLabelWidth;
	public UimFieldEventAdapter(GraphicalEditPart ep,IUimFieldFigure fig){
		super(ep, fig);
		this.fig = fig;
		if(super.element instanceof UimField){
			populateControl();
			setOrientation(((UimField) super.element).getOrientation());
			this.minimumLabelWidth = ((UimField) super.element).getMinimumLabelWidth() == null ? 150 : ((UimField) super.element)
					.getMinimumLabelWidth();
			fig.setMinimumLabelWidth(minimumLabelWidth);
		}
		fig.getComposite().layout();
		prepareForRepaint();
		fig.getComposite().setBackground(ColorConstants.cyan);
	}
	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else if(notification.getNotifier() instanceof UimField){
			int featureId = notification.getFeatureID(UimField.class);
			switch(featureId){
			case UimPackage.UIM_FIELD__CONTROL:
				onControlChanged(notification);
				super.prepareForRepaint();
				break;
			case UimPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH:
				fig.setMinimumLabelWidth((Integer) notification.getNewValue());
				this.minimumLabelWidth = (Integer) notification.getNewValue();
				super.prepareForRepaint();
				break;
			case UimPackage.UIM_FIELD__ORIENTATION:
				Orientation or = (Orientation) notification.getNewValue();
				setOrientation(or);
				IUimFieldComposite fig = this.fig.getComposite();
				Rectangle bnds = fig.getBounds();
				this.fig.setMinimumSize(new Dimension(bnds.width, bnds.height));
				this.fig.setBounds(UimFigureUtil.toDraw2DRectangle((Control) this.fig.getWidget()));
				if(((UimField) super.element).getControlKind() == ControlKind.LINK){
					fig.getControl().dispose();
					if(((UimField) super.element).getOrientation() == Orientation.VERTICAL){
						fig.setControl(new LinkComposite((Composite) fig, SWT.BORDER, true));
					}else{
						fig.setControl(new LinkComposite((Composite) fig, SWT.BORDER));
					}
				}
				super.prepareForRepaint();
				break;
			case UimPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH + 1000000:// TODO HEIGHT
				this.fig.setMinimumLabelHeigh((Integer) notification.getNewValue());
				super.prepareForRepaint();
				break;
			}
		}
	}
	protected void setOrientation(Orientation or){
		GridLayout layout;
		IUimFieldComposite fig = this.fig.getComposite();
		if(fig.getLabel() != null){
			UimSwtUtil.setOrientation(or, fig,minimumLabelWidth);
			if(or == Orientation.HORIZONTAL){
				this.fig.setMinimumLabelWidth(minimumLabelWidth);
			}
		}
	}
	private void onControlChanged(Notification notification){
		if(notification.getOldValue() != notification.getNewValue()){
			// TODO check for eClass only
			fig.getComposite().getControl().dispose();
			populateControl();
		}
	}
	private void populateControl(){
		UserInteractionElement userInteractionElement = super.element;
		IUimFieldComposite fig = this.fig.getComposite();
		if(userInteractionElement instanceof UimField){
			UimField uimField = (UimField) userInteractionElement;
			ControlKind kind = uimField.getControlKind();
			UimSwtUtil.populateControl(fig, kind, uimField.getOrientation());
		}
		fig.layout();
		fig.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	public Composite getParent(){
		return fig.getComposite().getParent();
	}
}