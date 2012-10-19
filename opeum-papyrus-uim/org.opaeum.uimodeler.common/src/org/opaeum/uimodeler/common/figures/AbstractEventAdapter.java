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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.swt.GridPanelComposite;

public class AbstractEventAdapter extends AdapterImpl implements FigureListener,LayoutListener,ControlListener,EditPartListener{
	protected ISWTFigure figure;
	protected GraphicalEditPart editPart;
	protected UserInteractionElement element;
	protected boolean readyForMove = false;
	protected boolean updatingSize;
	public AbstractEventAdapter(GraphicalEditPart editPart,ISWTFigure figure){
		super();
		this.editPart = editPart;
		editPart.getParent().addEditPartListener(this);
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
		Object eObject = this.editPart.getAdapter(EObject.class);
		this.element = (UserInteractionElement) eObject;
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
		if(isActive()){
			if(msg.getOldValue() == null || msg.getNewValue() == null || !msg.getNewValue().equals(msg.getOldValue())){
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
		}
	}
	public void prepareForRepaint(){
		// if(figure instanceof CustomGridPanelFigure){
		// figure.getWidget().setData("NEEDS_LAYOUT", Boolean.TRUE);
		// }
		figure.getParent().invalidate();
		if(figure.getWidget() instanceof Composite){
			((Composite) figure.getWidget()).layout();
		}
		figure.getWidget().getParent().layout();
		Composite parent = getParent();
		Figure fig = (Figure) parent.getData(UimFigureUtil.FIGURE);
		if(fig == null){
			parent = parent.getParent();
			fig = (Figure) parent.getData(UimFigureUtil.FIGURE);
		}
		figure.invalidate();
		if(fig != null){
			fig.invalidateTree();
			parent.setData("NEEDS_LAYOUT", Boolean.TRUE);
			if(parent.getParent() instanceof UimDataTableComposite || parent.getParent() instanceof GridPanelComposite){
				parent.getParent().setData("NEEDS_LAYOUT", Boolean.TRUE);
			}
		}
		WindowBuilderUtil.markRecursivelyForShot(figure.getWidget());
		figure.revalidate();
	}
	@Override
	public void figureMoved(IFigure source){
		if(isActive()){
			if(source == figure.getParent() && readyForMove && !updatingSize && source instanceof HackedDefaultSizeNodeFigure){
				GridData layoutData = (GridData) figure.getWidget().getLayoutData();
				Rectangle originalBounds = ((HackedDefaultSizeNodeFigure) source).getOriginalBounds();
				if(!updatingSize){
					updatingSize = true;
					boolean changed = false;
					if(layoutData.widthHint != originalBounds.width){
						try{
							CompoundCommand cc = new CompoundCommand();
							TransactionalEditingDomain editingDomain = ServiceUtilsForActionHandlers.getInstance().getTransactionalEditingDomain();
							cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_PreferredWidth(),
									originalBounds.width));
							cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_FillHorizontally(), Boolean.FALSE));
							editingDomain.getCommandStack().execute(cc);
							GridData gd = (GridData) ((Control) figure.getWidget()).getLayoutData();
							fillHorizontally(gd, (Outlayable) element);
							setWidthHint(gd, (Outlayable) element);
							changed = true;
						}catch(ServiceException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(layoutData.heightHint != originalBounds.height){
						try{
							CompoundCommand cc = new CompoundCommand();
							TransactionalEditingDomain editingDomain = ServiceUtilsForActionHandlers.getInstance().getTransactionalEditingDomain();
							cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_PreferredHeight(),
									originalBounds.height));
							cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_FillVertically(), Boolean.FALSE));
							editingDomain.getCommandStack().execute(cc);
							GridData gd = (GridData) ((Control) figure.getWidget()).getLayoutData();
							fillHorizontally(gd, (Outlayable) element);
							setHeightHint(gd, (Outlayable) element);
							changed = true;
						}catch(ServiceException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(changed){
						prepareForRepaint();
					}
				}
			}
			updatingSize = false;
		}
	}
	@Override
	public void invalidate(IFigure container){
		if(isActive()){
		}
	}
	protected Composite getParent(){
		return ((Control) figure.getWidget()).getParent();
	}
	@Override
	public boolean layout(IFigure container){
		if(isActive()){
			if(container == figure.getParent()){
				readyForMove = false;
			}
		}
		return false;
	}
	@Override
	public void postLayout(IFigure container){
		if(isActive()&& container == figure.getParent()){
			readyForMove = true;
		}
	}
	@Override
	public void remove(IFigure child){
		if(isActive()&& child == figure){
			figure.getWidget().dispose();
		}
	}
	@Override
	public void setConstraint(IFigure child,Object constraint){
	}
	@Override
	public void controlMoved(ControlEvent e){
	}
	@Override
	public void controlResized(ControlEvent e){
		e.widget.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
	@Override
	public void childAdded(EditPart child,int index){
		// TODO Auto-generated method stub
	}
	@Override
	public void partActivated(EditPart editpart){
		// TODO Auto-generated method stub
	}
	@Override
	public void partDeactivated(EditPart editpart){
		// TODO Auto-generated method stub
	}
	@Override
	public void removingChild(EditPart child,int index){
		if(child == editPart){
			removeListeners();
		}
		// TODO Auto-generated method stub
	}
	public boolean isActive(){
		if(editPart.isActive() && !figure.getWidget().isDisposed()){
			return true;
		}else{
			removeListeners();
			return false;
		}
	}
	private void removeListeners(){
		element.eAdapters().remove(this);
		this.figure.removeFigureListener(this);
		this.figure.getParent().removeFigureListener(this);
		this.figure.removeLayoutListener(this);
		this.figure.getParent().removeLayoutListener(this);
		element.eAdapters().remove(this);
		figure.getWidget().dispose();
	}
	@Override
	public void selectedStateChanged(EditPart editpart){
		// TODO Auto-generated method stub
	}
}