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
				MixedEditDomain mixedEditDomain = (MixedEditDomain) getPart().getAdapter(MixedEditDomain.class);
				Modeler editor = (Modeler) mixedEditDomain.getEditorPart();
				ICreationUtils creationUtils = editor.getActiveConfiguration().getCreationUtils();
				GraphNode actionNode = (GraphNode) Utils.getGraphElement(editor.getActiveDiagram(), getEObject(), true);
				int width = actionNode.getSize().width - 16;
				int height = actionNode.getSize().height - 16;
				if(actionNode != null){
					int ai=0;
					for(InputPin pin:getAction().getInputs()){
						
						Point location = new Point();
						int position;
						location.x = 16+ai*80;
						location.y = 16;
						position = PositionConstants.TOP;
						createOrChange(mixedEditDomain, creationUtils, actionNode, location, pin, position);
						ai++;
					}
					int ri=0;
					for(OutputPin pin:getAction().getOutputs()){
						Point location = new Point();
						int position;
						location.x = 16+ri*80;
						location.y = Math.round(height - 16);
						position = PositionConstants.BOTTOM;
						createOrChange(mixedEditDomain, creationUtils, actionNode, location, pin, position);
						ri++;
					}
					width =Math.max(ai,ri)*80+32;
					Point location = actionNode.getPosition().getCopy();
					execute(new ChangeBoundsCommand(actionNode, new Rectangle(location.x, location.y, Math.max(actionNode.getSize().width, width), actionNode
							.getSize().height)));
				}
			}
			private GraphNode createOrChange(MixedEditDomain mixedEditDomain,ICreationUtils creationUtils,GraphNode actionNode,Point location,Pin pin,int position){
				GraphNode inputGraph = (GraphNode) Utils.getGraphElement(actionNode, pin, true);
				if(inputGraph == null){
					inputGraph = (GraphNode) creationUtils.createGraphElement(pin);
					execute(new CreateGraphNodeCommand(mixedEditDomain, inputGraph, actionNode, getAction(), location, new Dimension(20, 20), position, null, false));
					execute(new ChangeGraphElementPresentationCommand(inputGraph, "default"));
				}else{
					execute(new ChangeBoundsCommand(inputGraph, new Rectangle(location.x, location.y, inputGraph.getSize().width, inputGraph.getSize().height)));
				}
				return inputGraph;
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
	private void execute(Command createGraphNodeCommand){
		getEditingDomain().getCommandStack().execute(new GEFtoEMFCommandWrapper(createGraphNodeCommand));
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
