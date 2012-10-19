package org.opaeum.uim.dnd;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.papyrus.infra.gmfdiag.dnd.strategy.TransactionalDropStrategy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.util.UmlUimLinks;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.page.diagram.part.UimPaletteFactory;

public class TypedElementDroppedOnPanelStrategy extends TransactionalDropStrategy{
	public String getLabel(){
		return "TypedElement->Panel creates Field or table";
	}
	public String getDescription(){
		return "When a Typed Element is dropped on a Panel, a field  or table is created";
	}
	public Image getImage(){
		return null;
	}
	public String getID(){
		return getClass().getName();
	}
	public int getPriority(){
		return 50;
	}
	@Override
	public Command doGetCommand(Request request,final EditPart targetEditPart){
		if(targetEditPart instanceof GridPanelGridPanelChildrenCompartmentEditPart
				|| targetEditPart instanceof UimDataTableDataTableColumnCompartmentEditPart){
			final CompartmentEditPart compartment = (CompartmentEditPart) targetEditPart;
			List<EObject> sourceEObjects = getSourceEObjects(request);
			if(sourceEObjects.size() != 1){
				return null;
			}
			final TypedElement property = (TypedElement) sourceEObjects.get(0);
			final UimContainer targetPanel = (UimContainer) getTargetSemanticElement(targetEditPart);
			boolean isValidTarget = isValidTarget(property, targetPanel);
			if(isValidTarget){
				final DropObjectsRequest dor = (DropObjectsRequest) request;
				return new Command(){
					@Override
					public void execute(){
						Point location = dor.getLocation();
						if(!EmfPropertyUtil.isMany(property)){
							createField(compartment, property, targetPanel, location);
							// IFigure figure = compartment.getFigure();
							// CustomGridPanelFigure pg = (CustomGridPanelFigure) figure.getParent();
							// ((Composite) pg.getWidget()).layout();
							// compartment.getFigure().invalidateTree();
						}else if(targetEditPart instanceof GridPanelGridPanelChildrenCompartmentEditPart
								&& EmfClassifierUtil.isPersistentComplexStructure(property.getType())){
							UimPaletteFactory uimPaletteFactory = new UimPaletteFactory();
							UnspecifiedTypeCreationTool tool = (UnspecifiedTypeCreationTool) uimPaletteFactory
									.createTool(UimPaletteFactory.CREATEDATATABLE2CREATIONTOOL);
							CreateUnspecifiedTypeRequest cr = (CreateUnspecifiedTypeRequest) tool.createCreateRequest();
							cr.setLocation(location);
							cr.setSize(new Dimension(300, 300));
							compartment.performRequest(cr);
							UimComponent newChild = targetPanel.getChildren().get(targetPanel.getChildren().size() - 1);
							UimDataTable table = (UimDataTable) newChild;
							TableBinding binding = BindingFactory.eINSTANCE.createTableBinding();
							binding.setUmlElementUid(EmfWorkspace.getId(property));
							table.setBinding(binding);
							table.setName(NameConverter.capitalize(property.getName()));
							table.setUnderUserControl(true);
							List<Property> props = EmfPropertyUtil.getEffectiveProperties((Classifier) property.getType());
							GraphicalEditPart newEditPart = findEditpartFor(compartment, newChild);
							UimDataTableDataTableColumnCompartmentEditPart columnCompartment = null;
							if(newEditPart instanceof UimDataTableEditPart){
								List<?> children = newEditPart.getChildren();
								for(Object object:children){
									if(object instanceof UimDataTableDataTableColumnCompartmentEditPart){
										columnCompartment = (UimDataTableDataTableColumnCompartmentEditPart) object;
									}
								}
							}
							int offset=0;
							if(columnCompartment != null){
								for(Property property2:props){
									if(!EmfPropertyUtil.isMany(property2)){
										createField(columnCompartment, property2, table, new Point(location.x + offset, location.y + 25));
										offset+=100;
									}
								}
							}
						}
					}
					private void createField(final CompartmentEditPart compartment,final TypedElement property,final UimContainer targetPanel,
							Point location){
						UnspecifiedTypeCreationTool tool = (UnspecifiedTypeCreationTool) new UimPaletteFactory()
								.createTool(UimPaletteFactory.CREATEFIELD1CREATIONTOOL);
						CreateUnspecifiedTypeRequest cr = (CreateUnspecifiedTypeRequest) tool.createCreateRequest();
						cr.setLocation(location);
						cr.setSize(new Dimension(300, 25));
						compartment.performRequest(cr);
						UimField field = (UimField) targetPanel.getChildren().get(targetPanel.getChildren().size() - 1);
						FieldBinding binding = BindingFactory.eINSTANCE.createFieldBinding();
						// NB!! sequence is important here
						binding.setUmlElementUid(EmfWorkspace.getId(property));
						field.setBinding(binding);// THis should trigger UIMcontentAdapter to recalculate the rest
						field.setName(NameConverter.capitalize(property.getName()));
						field.setUnderUserControl(true);
					}
				};
			}
		}
		return null;
	}
	protected boolean isValidTarget(final TypedElement property,final UimContainer targetPanel){
		if(property instanceof Property){
			Classifier cls = UmlUimLinks.getCurrentUmlLinks(targetPanel).getNearestClass(targetPanel);
			return EmfClassifierUtil.conformsTo(cls, EmfPropertyUtil.getOwningClassifier((Property) property));
		}else{
			UserInterfaceRoot nearestForm = UmlUimLinks.getNearestUserInterfaceRoot(targetPanel);
			return EmfWorkspace.getId(property.getOwner()).equals(nearestForm.getUmlElementUid());
		}
	}
	private GraphicalEditPart findEditpartFor(final CompartmentEditPart compartment,UimComponent newChild){
		List<?> children = compartment.getChildren();
		GraphicalEditPart newEditPart = null;
		for(Object object:children){
			if(object instanceof GraphicalEditPart){
				UimDataTableEditPart dtep = (UimDataTableEditPart) object;
				if(dtep.getAdapter(EObject.class) == newChild){
					newEditPart = dtep;
				}
			}
		}
		return newEditPart;
	}
}
