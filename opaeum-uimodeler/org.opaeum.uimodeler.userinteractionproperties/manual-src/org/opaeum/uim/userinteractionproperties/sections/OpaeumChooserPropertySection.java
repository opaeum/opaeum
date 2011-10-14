package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.topcased.modeler.editor.Modeler;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

public abstract class OpaeumChooserPropertySection extends AbstractChooserPropertySection{
	private MouseListener mouseListener;
	public void refresh(){
		super.refresh();
		if(getFeatureValue() != null){
			labelCombo.getFont().getFontData()[0].style = SWT.UNDERLINE_SINGLE;
			labelCombo.setForeground(ColorConstants.blue);
			labelCombo.addMouseListener(getMouseListener());
			labelCombo.setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_HAND));
		}else{
			labelCombo.getFont().getFontData()[0].style = SWT.NORMAL;
			labelCombo.setForeground(ColorConstants.black);
			labelCombo.removeMouseListener(getMouseListener());
		}
		labelCombo.redraw();
	}
	protected MouseListener getMouseListener(){
		if(this.mouseListener == null){
			this.mouseListener = new MouseListener(){
				@Override
				public void mouseUp(MouseEvent e){
					// TODO Auto-generated method stub
				}
				@Override
				public void mouseDown(MouseEvent e){
					Modeler nakedUmlEditor = (Modeler) getActivePage().getActiveEditor();
					nakedUmlEditor.gotoEObject((EObject) getFeatureValue());
					((TabbedPropertySheetPage) getPart().getSite().getPage()).selectionChanged(nakedUmlEditor, new StructuredSelection(getFeatureValue()));
				}
				@Override
				public void mouseDoubleClick(MouseEvent e){
					// TODO Auto-generated method stub
				}
			};
		}
		return this.mouseListener;
	}
}
