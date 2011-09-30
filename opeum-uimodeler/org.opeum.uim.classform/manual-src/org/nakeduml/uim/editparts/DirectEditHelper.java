package org.opeum.uim.editparts;

import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.swt.widgets.Display;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class DirectEditHelper{
	ILabelFigure figure;
	EMFGraphNodeEditPart editPart;
	boolean shouldEdit = false;
	public DirectEditHelper(ILabelFigure figure,EMFGraphNodeEditPart editPart){
		super();
		this.figure = figure;
		figure.getLabel().addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent me){
			}
			@Override
			public void mouseEntered(MouseEvent me){
			}
			@Override
			public void mouseExited(MouseEvent me){
			}
			@Override
			public void mouseHover(MouseEvent me){
				shouldEdit=true;
			}
			@Override
			public void mouseMoved(MouseEvent me){
			}
		});
		figure.getLabel().addMouseListener(new MouseListener(){
			@Override
			public void mousePressed(final MouseEvent me){
				shouldEdit=false;
				Display.getCurrent().timerExec(400, new Runnable(){
					@Override
					public void run(){
						performRequest(me);
					}
				});
			}
			@Override
			public void mouseReleased(MouseEvent me){
			}
			@Override
			public void mouseDoubleClicked(MouseEvent me){
			}
		});
		this.editPart = editPart;
	}
	private void performRequest(MouseEvent me){
		if(shouldEdit){
			DirectEditRequest req = new DirectEditRequest();
			req.setLocation(me.getLocation());
			editPart.performRequest(req);
		}
	}
}
