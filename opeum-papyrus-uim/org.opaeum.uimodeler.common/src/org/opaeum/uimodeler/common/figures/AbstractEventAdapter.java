package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
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
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uimodeler.common.UimFigureUtil;

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
		prepareParentWidgetForRepaint();
	}
	protected void setWidthHint(GridData gd,Outlayable outlayable){
		if(outlayable.getPreferredWidth() != null){
			gd.widthHint = outlayable.getPreferredWidth();
		}
	}
	protected void setHeightHint(GridData gd,Outlayable outlayable){
		if(outlayable.eContainer() instanceof UimDataTable){
			gd.heightHint = -1;
		}else if(outlayable.getPreferredHeight() != null){
			gd.heightHint = outlayable.getPreferredHeight();
		}
	}
	protected void fillVertically(GridData gd,Outlayable outlayable){
		if(outlayable.getFillVertically() != null){
			gd.grabExcessVerticalSpace = outlayable.getFillVertically();
			gd.verticalAlignment = outlayable.getFillVertically() ? SWT.FILL : GridData.BEGINNING;
		}
	}
	protected void fillHorizontally(GridData gd,Outlayable outlayable){
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
							prepareParentWidgetForRepaint();
							break;
						case PanelPackage.OUTLAYABLE__FILL_VERTICALLY:
							fillVertically((GridData) ((Control) figure.getWidget()).getLayoutData(), (Outlayable) element);
							prepareParentWidgetForRepaint();
							break;
						case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT:
							setHeightHint((GridData) ((Control) figure.getWidget()).getLayoutData(), (Outlayable) element);
							prepareParentWidgetForRepaint();
							break;
						case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH:
							setWidthHint((GridData) ((Control) figure.getWidget()).getLayoutData(), (Outlayable) element);
							prepareParentWidgetForRepaint();
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
					//May changeminimumsize
					prepareParentWidgetForRepaint();
					break;
				}
			}
		}
	}
	public void prepareParentWidgetForRepaint(){
		IUimWidget parent = getParent();
		parent.markForShot();
		parent.layout();
		Figure parentFigure = (Figure) parent.getData(UimFigureUtil.FIGURE);
		parentFigure.invalidateTree();
		parentFigure.revalidate();
	}
	public void prepareWidgetForRepaint(){
		this.figure.getWidget().markForShot();
		if(figure.getWidget() instanceof Composite){
			((Composite) figure.getWidget()).layout();
		}
		figure.invalidateTree();
		figure.revalidate();
	}
	@Override
	public void figureMoved(IFigure source){
		if(isActive()){
			if(source == figure.getParent() && readyForMove && !updatingSize && source instanceof HackedDefaultSizeNodeFigure){
				IUimWidget widget = figure.getWidget();
				GridData layoutData = (GridData) widget.getLayoutData();
				Rectangle newBounds = ((HackedDefaultSizeNodeFigure) source).getOriginalBounds();
				if(!updatingSize){
					updatingSize = true;
					boolean changed = false;
					if(layoutData.widthHint != newBounds.width){
						try{
							applyWidthAfterMove(newBounds);
							changed = true;
						}catch(ServiceException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(layoutData.heightHint != newBounds.height && !(getParent() instanceof UimDataTableComposite)){
						try{
							applyHeightAfterMove(newBounds);
							changed = true;
						}catch(ServiceException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(changed){
						prepareParentWidgetForRepaint();
					}
				}
			}
			updatingSize = false;
		}
	}
	protected void applyWidthAfterMove(Rectangle newBounds) throws ServiceException{
		CompoundCommand cc = new CompoundCommand();
		TransactionalEditingDomain editingDomain = ServiceUtilsForActionHandlers.getInstance().getTransactionalEditingDomain();
		cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_PreferredWidth(), newBounds.width));
		cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_FillHorizontally(), Boolean.FALSE));
		editingDomain.getCommandStack().execute(cc);
		GridData gd = (GridData) ((Control) figure.getWidget()).getLayoutData();
		fillHorizontally(gd, (Outlayable) element);
		setWidthHint(gd, (Outlayable) element);
	}
	protected void applyHeightAfterMove(Rectangle newBounds) throws ServiceException{
		CompoundCommand cc = new CompoundCommand();
		TransactionalEditingDomain editingDomain = ServiceUtilsForActionHandlers.getInstance().getTransactionalEditingDomain();
		cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_PreferredHeight(), newBounds.height));
		cc.append(SetCommand.create(editingDomain, element, PanelPackage.eINSTANCE.getOutlayable_FillVertically(), Boolean.FALSE));
		editingDomain.getCommandStack().execute(cc);
		GridData gd = (GridData) ((Control) figure.getWidget()).getLayoutData();
		fillVertically(gd, (Outlayable) element);
		setHeightHint(gd, (Outlayable) element);
	}
	@Override
	public void invalidate(IFigure container){
		if(isActive()){
		}
	}
	protected final IUimWidget getParent(){
		Composite parent = figure.getWidget().getParent();
		while(!(parent instanceof IUimWidget) || parent.getData(UimFigureUtil.FIGURE) == null){
			parent = parent.getParent();
		}
		return (IUimWidget) parent;
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
		if(isActive() && container == figure.getParent()){
			readyForMove = true;
		}
	}
	@Override
	public void remove(IFigure child){
		if(isActive() && child == figure){
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
		// e.widget.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
	@Override
	public void childAdded(EditPart child,int index){
		if(child == editPart){
			// addListeners
			// this.figure.
		}
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
		if(figure.getWidget().isDisposed()){
			removeListeners();
			return false;
		}else{
			return true;
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