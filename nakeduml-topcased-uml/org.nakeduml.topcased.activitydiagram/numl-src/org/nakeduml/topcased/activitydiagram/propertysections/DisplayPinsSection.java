package org.nakeduml.topcased.activitydiagram.propertysections;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.commands.ChangeBoundsCommand;
import org.topcased.modeler.commands.ChangeGraphElementPresentationCommand;
import org.topcased.modeler.commands.CreateGraphNodeCommand;
import org.topcased.modeler.commands.GEFtoEMFCommandWrapper;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.utils.Utils;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class DisplayPinsSection extends AbstractTabbedPropertySection{
	private Button button;
	public Action getAction(){
		return (Action) getEObject();
	}
	protected void createWidgets(Composite composite){
		button = getWidgetFactory().createButton(composite, "Display Pins", SWT.PUSH);
		button.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData fData = new FormData();
		fData.top = new FormAttachment(0);
		fData.left = new FormAttachment(0);
		fData.right = new FormAttachment(100);
		button.setLayoutData(fData);
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getLabelText(){
		return "Display Pins";
	}
}
