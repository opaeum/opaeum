package org.opaeum.uimodeler.common.figures;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.swt.GridPanelComposite;

public class AbstractEventAdapter extends AdapterImpl implements FigureListener,LayoutListener,MouseMotionListener,MouseListener,
		ControlListener{
	protected ISWTFigure figure;
	protected GraphicalEditPart editPart;
	protected UserInteractionElement element;
	protected boolean readyForMove = false;
	private boolean updatingSize;
	public AbstractEventAdapter(GraphicalEditPart editPart,ISWTFigure figure){
		super();
		this.editPart = editPart;
		org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart gep = (org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart) this.editPart;
		Node view = (Node) gep.getPrimaryView();
		EList children = view.getChildren();
		if(view.getLayoutConstraint() != null){
			view.getLayoutConstraint().eAdapters().add(new AdapterImpl(){
				@Override
				public void notifyChanged(Notification msg){
					super.notifyChanged(msg);
				}
			});
		}
		this.figure = figure;
		figure.getWidget().addControlListener(this);
		this.element = (UserInteractionElement) this.editPart.getAdapter(EObject.class);
		element.eAdapters().add(this);
		this.figure.addFigureListener(this);
		this.figure.getParent().addFigureListener(this);
		this.figure.addLayoutListener(this);
		this.figure.getParent().addLayoutListener(this);
		this.figure.setLabelText(element.getName() == null ? "New" + element.eClass().getName() : element.getName());
		if(element instanceof Outlayable){
			if(this.figure.getWidget() instanceof Control){
				Control ctl = (Control) this.figure.getWidget();
				GridData gd = (GridData) ctl.getLayoutData();
				if(gd == null){
					gd = new GridData();
				}
				Outlayable outlayable = (Outlayable) element;
				fillHorizontally(gd, outlayable);
				fillVertically(gd, outlayable);
				setHeightHint(gd, outlayable);
				setWidthHint(gd, outlayable);
				ctl.setLayoutData(gd);
			}
		}
		figure.getWidget().setData(UimFigureUtil.ELEMENT, element);
		figure.getWidget().setData(UimFigureUtil.FIGURE, figure);
		figure.getWidget().getParent().layout();
	}
	private void setWidthHint(GridData gd,Outlayable outlayable){
		if(outlayable.getPreferredWidth() != null){
			gd.widthHint = outlayable.getPreferredWidth();
		}
	}
	private void setHeightHint(GridData gd,Outlayable outlayable){
		if(outlayable.getPreferredHeight() != null){
			gd.heightHint = outlayable.getPreferredHeight();
		}
	}
	private void fillVertically(GridData gd,Outlayable outlayable){
		if(outlayable.getFillVertically() != null){
			gd.grabExcessVerticalSpace = outlayable.getFillVertically();
			gd.verticalAlignment = outlayable.getFillVertically() ? SWT.FILL : GridData.BEGINNING;
		}
	}
	private void fillHorizontally(GridData gd,Outlayable outlayable){
		if(outlayable.getFillHorizontally() != null){
			gd.grabExcessHorizontalSpace = outlayable.getFillHorizontally();
			gd.horizontalAlignment = outlayable.getFillHorizontally() ? SWT.FILL : GridData.BEGINNING;
		}
	}
	@Override
	public void notifyChanged(Notification msg){
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else{
			if(msg.getNotifier() instanceof Outlayable){
				int featureId = msg.getFeatureID(Outlayable.class);
				if(!updatingSize){
					switch(featureId){
					case PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY:
						fillHorizontally((GridData) ((Control) figure.getWidget()).getLayoutData(), (Outlayable) element);
						prepareForRepaint();
						break;
					case PanelPackage.OUTLAYABLE__FILL_VERTICALLY:
						fillVertically((GridData) ((Control) figure.getWidget()).getLayoutData(), (Outlayable) element);
						prepareForRepaint();
						break;
					case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT:
						setHeightHint((GridData) ((Control) figure.getWidget()).getLayoutData(), (Outlayable) element);
						prepareForRepaint();
						break;
					case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH:
						setWidthHint((GridData) ((Control) figure.getWidget()).getLayoutData(), (Outlayable) element);
						prepareForRepaint();
						break;
					default:
						break;
					}
				}
			}
			int featureId = msg.getFeatureID(UserInteractionElement.class);
			switch(featureId){
			case UimPackage.USER_INTERACTION_ELEMENT__NAME:
				figure.setLabelText(msg.getNewStringValue());
				prepareForRepaint();
				break;
			}
		}
		super.notifyChanged(msg);
	}
	public void prepareForRepaint(){
		figure.getParent().invalidate();
		figure.getWidget().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		if(figure.getWidget() instanceof Composite){
			((Composite) figure.getWidget()).layout();
		}
		Composite parent = getParent();
		Figure fig = (Figure) parent.getData(UimFigureUtil.FIGURE);
		if(fig == null){
			parent = parent.getParent();
			fig = (Figure) parent.getData(UimFigureUtil.FIGURE);
		}
		figure.invalidate();
		if(fig != null){
			parent.layout();
			if(parent.getParent() instanceof UimDataTableComposite || parent.getParent() instanceof GridPanelComposite){
				parent.getParent().layout();
			}
			figure.revalidate();
		}
	}
	@Override
	public void figureMoved(IFigure source){
		if(source == figure.getParent() && readyForMove && !updatingSize && source instanceof HackedDefaultSizeNodeFigure){
			GridData layoutData = (GridData) figure.getWidget().getLayoutData();
			Rectangle originalBounds = ((HackedDefaultSizeNodeFigure) source).getOriginalBounds();
			if(!updatingSize){
				updatingSize = true;
				if(layoutData.widthHint != originalBounds.width){
					try{
						CompoundCommand cc = new CompoundCommand();
						TransactionalEditingDomain editingDomain = ServiceUtilsForActionHandlers.getInstance().getTransactionalEditingDomain();
						cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_PreferredWidth(), originalBounds.width));
						cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_FillHorizontally(), Boolean.FALSE));
						editingDomain.getCommandStack().execute(cc);
						GridData gd = (GridData) ((Control) figure.getWidget()).getLayoutData();
						fillHorizontally(gd, (Outlayable) element);
						setWidthHint(gd, (Outlayable) element);
						prepareForRepaint();
					}catch(ServiceException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(layoutData.widthHint + "," + layoutData.heightHint);
				System.out.println(source.getBounds());
				updatingSize = false;
			}
		}
	}
	@Override
	public void invalidate(IFigure container){
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}
	}
	protected Composite getParent(){
		return ((Control) figure.getWidget()).getParent();
	}
	@Override
	public boolean layout(IFigure container){
		if(container == figure.getParent()){
			readyForMove = false;
		}
		return false;
	}
	@Override
	public void postLayout(IFigure container){
		if(container == figure.getParent()){
			readyForMove = true;
		}
	}
	@Override
	public void remove(IFigure child){
		if(child == figure){
			figure.getWidget().dispose();
		}
	}
	@Override
	public void setConstraint(IFigure child,Object constraint){
	}
	@Override
	public void mouseDragged(MouseEvent me){
	}
	@Override
	public void mouseEntered(MouseEvent me){
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent me){
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseHover(MouseEvent me){
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent me){
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent me){
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent me){
		List<GraphicalEditPart> children = editPart.getParent().getChildren();
		for(GraphicalEditPart editPart:children){
			Point touchPoint = new Point(me.x - 1, me.y + 1);
			if(editPart.getFigure().getBounds().contains(touchPoint)){
				System.out.println(editPart.getFigure());
			}
		}
	}
	@Override
	public void mouseDoubleClicked(MouseEvent me){
		// TODO Auto-generated method stub
	}
	@Override
	public void controlMoved(ControlEvent e){
	}
	@Override
	public void controlResized(ControlEvent e){
		e.widget.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
}