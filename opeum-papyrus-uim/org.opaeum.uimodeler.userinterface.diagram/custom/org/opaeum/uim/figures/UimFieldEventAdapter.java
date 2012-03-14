package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.opaeum.uim.Orientation;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uimodeler.common.figures.AbstractEventAdapter;
import org.opaeum.uimodeler.common.figures.IUimFieldFigure;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;

public final class UimFieldEventAdapter extends AbstractEventAdapter{
	private IUimFieldFigure fig;
	public UimFieldEventAdapter(GraphicalEditPart ep,IUimFieldFigure fig){
		super(ep, fig);
		this.fig = fig;
		if(super.element instanceof UimField){
			fig.setMinimumLabelWidth(((UimField) super.element).getMinimumLabelWidth());
			populateControl();
			setOrientation(((UimField) super.element).getOrientation());
		}
		fig.getComposite().setBackground(ColorConstants.cyan);
	}
	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
		if(figure.getWidget().isDisposed()){
			element.eAdapters().remove(this);
		}else if(notification.getNotifier() instanceof UimField){
			switch(notification.getFeatureID(UimField.class)){
			case UimPackage.UIM_FIELD__CONTROL:
				onControlChanged(notification);
				super.prepareForRepaint();
				break;
			case UimPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH:
				fig.setMinimumLabelWidth((Integer) notification.getNewValue());
				super.prepareForRepaint();
				break;
			case UimPackage.UIM_FIELD__ORIENTATION:
				Orientation or = (Orientation) notification.getNewValue();
				setOrientation(or);
				Rectangle bnds = fig.getComposite().getBounds();
				fig.setMinimumSize(new Dimension(bnds.width, bnds.height));
				fig.setBounds(UimFigureUtil.toDraw2DRectangle(bnds));
				super.prepareForRepaint();
				break;
			case UimPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH + 1000000:// TODO HEIGHT
				fig.setMinimumLabelHeigh((Integer) notification.getNewValue());
				super.prepareForRepaint();
				break;
			}
		}
	}
	protected void setOrientation(Orientation or){
		GridLayout layout;
		if(or == Orientation.VERTICAL){
			layout = new GridLayout(1, false);
			fig.getComposite().setLayout(layout);
			fig.getComposite().getChildren()[0].setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
			fig.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		}else{
			layout = new GridLayout(2, false);
			fig.getComposite().setLayout(layout);
			fig.getComposite().getChildren()[0].setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, true));
			fig.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		}
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		fig.getComposite().layout();
	}
	private void onControlChanged(Notification notification){
		if(notification.getOldValue() != notification.getNewValue()){
			// TODO check for eClass only
			fig.getControl().dispose();
			populateControl();
		}
	}
	private void populateControl(){
		if(super.element instanceof UimField){
			if(fig.getControl() != null && !fig.getControl().isDisposed()){
				fig.getControl().dispose();
			}
			switch(((UimField) super.element).getControlKind()){
			case CHECK_BOX:
				fig.setControl(new Button(fig.getComposite(), SWT.CHECK));
				break;
			case DATE_POPUP:
				fig.setControl(new DateTime(fig.getComposite(), SWT.BORDER | SWT.CALENDAR | SWT.DROP_DOWN|SWT.DATE));
				break;
			case DATE_TIME_POPUP:
				fig.setControl(new DateTime(fig.getComposite(), SWT.BORDER | SWT.CALENDAR | SWT.DROP_DOWN | SWT.TIME | SWT.DATE));
				break;
			case DATE_SCROLLER:
				fig.setControl(new DateTime(fig.getComposite(), SWT.BORDER));
				break;
			case DROPDOWN:
				fig.setControl(new CCombo(fig.getComposite(), SWT.BORDER));
				break;
			case LIST_BOX:
				List list = new List(fig.getComposite(), SWT.BORDER | SWT.MULTI);
				list.add("Item 1");
				list.add("Item 2");
				list.add("Item 3");
				list.add("Item 4");
				fig.setControl(list);
				break;
			case TREE_VIEW:
				Tree tree = new Tree(fig.getComposite(), SWT.BORDER | SWT.MULTI);
				TreeItem root = new TreeItem(tree, SWT.NONE);
				root.setText("Root");
				root.setExpanded(true);
				TreeItem _1_1 = new TreeItem(root, SWT.NONE);
				_1_1.setText("Item 1.1");
				_1_1.setExpanded(true);
				TreeItem _1_2 = new TreeItem(root, SWT.NONE);
				_1_2.setText("Item 1.2");
				_1_2.setExpanded(true);
				TreeItem _1_2_1 = new TreeItem(_1_2, SWT.NONE);
				_1_2_1.setText("Item 1.2.1");
				_1_2_1.setExpanded(true);
				TreeItem _1_2_2 = new TreeItem(_1_2, SWT.NONE);
				_1_2_2.setText("Item 1.2.2");
				_1_2_2.setExpanded(true);
				tree.setTopItem(root);
				tree.showItem(_1_2_2);
				fig.setControl(tree);
				break;
			case POPUP_SEARCH:
				fig.setControl(new CSingleObjectChooser(fig.getComposite(), SWT.BORDER));
				break;
			case NUMBER_SCROLLER:
				fig.setControl(new NumberScroller(fig.getComposite(), SWT.NONE | SWT.BORDER));
				break;
			case TEXT:
				fig.setControl(new Text(fig.getComposite(), SWT.BORDER));
				break;
			case TEXT_AREA:
				fig.setControl(new Text(fig.getComposite(), SWT.BORDER | SWT.MULTI));
				break;
			case RADIO_BUTTON:
				Composite radioGroup = new Composite(fig.getComposite(), SWT.BORDER);
				radioGroup.setLayout(new GridLayout(3,true));
				new Button(radioGroup, SWT.RADIO).setText("Option 1");
				new Button(radioGroup, SWT.RADIO).setText("Option 2");
				new Button(radioGroup, SWT.RADIO).setText("Option 3");
				fig.setControl(radioGroup);
				radioGroup.layout();
				break;
			case LINK:
				Composite linkComposite = new LinkComposite(fig.getComposite(), SWT.BORDER);
				fig.setControl(linkComposite);
				break;
			case LABEL:
				CLabel label = new CLabel(fig.getComposite(), SWT.BORDER);
				label.setText("<Object Name>");
				fig.setControl(label);
				break;
			case TOGGLE_BUTTON:
				fig.setControl(new Button(fig.getComposite(), SWT.TOGGLE));
				break;
			case SELECTION_TABLE:
				fig.setControl(new Button(fig.getComposite(), SWT.TOGGLE));
				break;
			}
		}
		fig.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	public Composite getParent(){
		return fig.getComposite().getParent();
	}
}