package org.opaeum.uim.figures;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.opaeum.uimodeler.common.figures.HackedDefaultSizeNodeFigure;
import org.opaeum.uimodeler.common.figures.IUimFieldFigure;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;

/**
 * @generated NOT
 */
public class CustomUimFieldFigure extends RectangleFigure implements IUimFieldFigure{
	private Control widget;
	/**
	 * @generated NOT
	 */
	private WrappingLabel fFigureUimFieldNameFigure;
	private Composite composite;
	private Label label;
	/**
	 * @generated NOT
	 */
	public CustomUimFieldFigure(Composite parent){
		FlowLayout layoutThis = new FlowLayout(true);
		layoutThis.setStretchMinorAxis(true);
		layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);
		layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
		layoutThis.setMajorSpacing(5);
		layoutThis.setMinorSpacing(5);
		layoutThis.setHorizontal(true);
		this.setLayoutManager(layoutThis);
		createContents();
		setComposite(new Composite(parent, SWT.BORDER));
		getComposite().setData(UimFigureUtil.FIGURE, this);
		GridLayout rl = new GridLayout(2, false);
		rl.marginWidth = 0;
		rl.marginHeight = 0;
		rl.horizontalSpacing = 0;
		
		getComposite().setLayout(rl);
		setLabel(new Label(getComposite(), SWT.NONE));
		setControl(createDefaultControl());
		getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		GridData labelData = new GridData(GridData.CENTER, GridData.FILL, false, false);
		getLabel().setLayoutData(labelData);
		fFigureUimFieldNameFigure.addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(MouseEvent me){
				mouseDoubleClicked(me);
			}
			@Override
			public void mouseReleased(MouseEvent me){
				mouseDoubleClicked(me);
			}
			@Override
			public void mouseDoubleClicked(MouseEvent me){
			}
		});
		getComposite().layout();
		
		setBounds(UimFigureUtil.toDraw2DRectangle(composite));
	}
	protected Control createDefaultControl(){
		Text text = new Text(getComposite(), SWT.NONE);
		return text;
	}
	public void setMinimumLabelWidth(Integer size){
		if(size != null){
			((GridData) getLabel().getLayoutData()).minimumWidth = size;
			((GridData) getLabel().getLayoutData()).widthHint = size;
			getComposite().layout();
		}
	}
	public void setMinimumLabelHeigh(Integer size){
		if(size != null){
			((GridData) getLabel().getLayoutData()).minimumHeight = size;
		}
	}
	@Override
	protected void layout(){
		super.layout();
		getComposite().layout();
		// fFigureUimFieldNameFigure.setBounds(UimFigureUtil.toDraw2DRectangle(label.getBounds()));
	}
	/**
	 * @generated NOT
	 */
	private void createContents(){
		fFigureUimFieldNameFigure = new WrappingLabel();
		fFigureUimFieldNameFigure.setText("<...>");
		this.add(fFigureUimFieldNameFigure);
	}
	@Override
	public Rectangle getBounds(){
//		if(!widget.isDisposed()){
//			return UimFigureUtil.toDraw2DRectangle(widget);
//		}else{
			return super.getBounds();
//		}
	}
	/**
	 * @generated
	 */
	public WrappingLabel getFigureUimFieldNameFigure(){
		return fFigureUimFieldNameFigure;
	}
	public void paint(Graphics graphics){
		org.eclipse.swt.graphics.Rectangle bounds2 = getComposite().getBounds();
		if(bounds2.width > 0 && bounds2.height > 0){
			final Image image = new Image(null, bounds2);
			// GC gc = new GC(image);
			// getComposite().print(gc)
			;
			Point copy = getLocation().getCopy();
			try{
				graphics.drawImage((Image) getComposite().getData("OPAEUM_IMAGE"), copy.x, copy.y);
			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public Control getWidget(){
		// TODO Auto-generated method stub
		return getComposite();
	}
	public Composite getComposite(){
		return composite;
	}
	public void setComposite(Composite composite){
		this.composite = composite;
	}
	public Control getControl(){
		return widget;
	}
	public void setControl(Control control){
		this.widget = control;
	}
	public Label getLabel(){
		return label;
	}
	public void setLabel(Label label){
		this.label = label;
	}
	@Override
	public void setLabelText(String string){
		this.label.setText(string);
	}
	@Override
	public void markForRepaint(){
		
	}
}