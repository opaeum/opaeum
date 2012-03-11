package org.opaeum.uim.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;

public final class UimFieldEventAdapter extends AbstractEventAdapter{
	private IUimFieldFigure fig;
	private UimField field;
	public UimFieldEventAdapter(GraphicalEditPart ep,IUimFieldFigure fig){
		super(ep, fig);
		field = (UimField) super.element;
		fig.setMinimumLabelWidth(field.getMinimumLabelWidth());
		this.fig = fig;
		populateControl();
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
			case UimPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH + 1000000:// TODO HEIGHT
				fig.setMinimumLabelHeigh((Integer) notification.getNewValue());
				super.prepareForRepaint();
				break;
			}
		}
	}
	private void onControlChanged(Notification notification){
		if(notification.getOldValue() != notification.getNewValue()){
			// TODO check for eClass only
			fig.getControl().dispose();
			populateControl();
		}
	}
	private void populateControl(){
		if(fig.getControl() != null && !fig.getControl().isDisposed()){
			fig.getControl().dispose();
		}
		switch(field.getControlKind()){
		case CHECK_BOX:
			fig.setControl(new Button(fig.getComposite(), SWT.CHECK));
			break;
		case DATE_POPUP:
			fig.setControl(new DateTime(fig.getComposite(), SWT.BORDER));
			break;
		case DROPDOWN:
			fig.setControl(new CCombo(fig.getComposite(), SWT.BORDER));
			break;
		case MULTI_SELECT_LIST_BOX:
		case SINGLE_SELECT_LIST_BOX:
			List list = new List(fig.getComposite(), SWT.BORDER | SWT.MULTI);
			list.add("Item 1");
			list.add("Item 2");
			list.add("Item 3");
			list.add("Item 4");
			fig.setControl(list);
			break;
		case MULTI_SELECT_TREE_VIEW:
		case SINGLE_SELECT_TREE_VIEW:
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
		case MULTI_SELECT_POPUP_SEARCH:
		case SINGLE_SELECT_POPUP_SEARCH:
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
		}
		fig.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	public Composite getParent(){
		return fig.getComposite().getParent();
	}
}