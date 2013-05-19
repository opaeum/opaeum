package org.opaeum.papyrus;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.sasheditor.DiModel;
import org.eclipse.papyrus.infra.core.resource.sasheditor.SashModel;
import org.eclipse.papyrus.infra.core.sashwindows.di.DiFactory;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.papyrus.infra.gmfdiag.common.model.NotationModel;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.newchild.RelationshipDirection;
import org.opaeum.name.NameConverter;

public class CreateClassDiagramNotationElements extends AbstractCommand{
	Collection<? extends Element> elements;
	NotationModel notationModel;
	DiModel diModel;
	private SashWindowsMngr sashWindowsMngr;
	private Set<Diagram> diagrams = new HashSet<Diagram>();
	RelationshipExtractor relationshipExtractor;
	ShapeBuilder shapeBuilder = new ShapeBuilder(ModelEditPart.MODEL_ID);
	private Map<Element,Shape> shapes = new HashMap<Element,Shape>();
	private String suffix;
	public CreateClassDiagramNotationElements(Collection<? extends Element> elements2,ModelSet modelSet,EClass[] relationshipTypes,
			RelationshipDirection d,String suffix){
		super();
		relationshipExtractor = new RelationshipExtractor(relationshipTypes, d);
		this.elements = elements2;
		this.notationModel = (NotationModel) modelSet.getModel(NotationModel.MODEL_ID);
		Resource rst = ((SashModel) modelSet.getModel(DiModel.MODEL_ID)).getResource();
		this.sashWindowsMngr = (SashWindowsMngr) rst.getContents().get(0);
		this.suffix = suffix;
	}
	@Override
	public void execute(){
		redo();
	}
	@Override
	public void redo(){
		Package pkg = getMostCommonPackage();
		Diagram dgm = NotationFactory.eINSTANCE.createDiagram();
		dgm.setType(ModelEditPart.MODEL_ID);
		dgm.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		dgm.setElement(pkg);
		dgm.setName(NameConverter.capitalize(pkg.getName() + " " + suffix));
		notationModel.getResource().getContents().add(dgm);
		PageRef page = DiFactory.eINSTANCE.createPageRef();
		page.setEmfPageIdentifier(dgm);
		sashWindowsMngr.getPageList().getAvailablePage().add(page);
		diagrams.add(dgm);
		for(Element type:elements){
			relationshipExtractor.doSwitch(type);
		}
		for(Element type:relationshipExtractor.getNodeElements()){
			Shape shape = shapeBuilder.doSwitch(type);
			if(shape != null){
				shapes.put(type, shape);
				dgm.getPersistedChildren().add(shape);
			}
		}
		ConnectorBuilder connectorBuilder = new ConnectorBuilder(ModelEditPart.MODEL_ID, shapes);
		for(Element r:relationshipExtractor.getRelationships()){
			Connector c = connectorBuilder.doSwitch(r);
			if(c != null){
				c.getSource().getDiagram().getPersistedEdges().add(c);
			}
		}
	}
	private Package getMostCommonPackage(){
		Set<Package> pkgs = collectPackages();
		for(Package pkg2:pkgs){
			while(pkg2!=null){
				if(containsAll(pkg2,pkgs)){
					return pkg2;
				}
				pkg2=pkg2.getNestingPackage();
			}
		}
		return null;
	}
	private boolean containsAll(Package potentialParent,Set<Package> pkgs){
		for(Package p:pkgs){
			if(!p.getQualifiedName().contains(potentialParent.getQualifiedName())){
				return false;
			}
		}
		return true;
	}
	private Set<Package> collectPackages(){
		Set<Package> pkgs = new HashSet<Package>();
		for(Element element:elements){
			pkgs.add(element.getNearestPackage());
		}
		return pkgs;
	}
	@Override
	public boolean canExecute(){
		return true;
	}
	public Set<Diagram> getDiagrams(){
		return diagrams;
	}
}
