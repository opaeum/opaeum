package org.opaeum.uim.uml2uim;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfParameterUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.perspective.BehaviorNavigationConstraint;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.NavigatorConfiguration;
import org.opaeum.uim.perspective.OperationNavigationConstraint;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.ParameterNavigationConstraint;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.PropertyNavigationConstraint;
import org.opaeum.uim.util.UmlUimLinks;

public class PerspectiveCreator extends AbstractUimSynchronizer2{
	private NavigatorConfiguration explorerPosition;
	private Map<Element,NavigationConstraint> map = new HashMap<Element,NavigationConstraint>();
	public PerspectiveCreator(URI workspace,ResourceSet resourceSet,boolean regenerate,EmfWorkspace emfWorkspace){
		super(workspace, resourceSet, regenerate);
		boolean isNew = false;
		Resource resource;
		if(!UimResourceUtil.hasUimResource(null, resourceSet, workspace, "perspective")){
			isNew = true;
		}
		resource = UimResourceUtil.getUimResource(null, resourceSet, workspace, "perspective");
		if(isNew){
			getNewResources().put(null, resource);
			PerspectiveConfiguration p = null;
			if(resource.getContents().isEmpty()){
				p = PerspectiveFactory.eINSTANCE.createPerspectiveConfiguration();
				resource.getContents().add(p);
			}else{
				p = (PerspectiveConfiguration) resource.getContents().get(0);
			}
			p = (PerspectiveConfiguration) resource.getContents().get(0);
			if(!p.isUnderUserControl()){
				if(p.getExplorer() == null){
					this.explorerPosition = PerspectiveFactory.eINSTANCE.createNavigatorConfiguration();
					p.setExplorer(explorerPosition);
				}
				this.explorerPosition = p.getExplorer();
				if(!p.getExplorer().isUnderUserControl()){
					p.getExplorer().setPosition(PositionInPerspective.LEFT);
					p.getExplorer().setWidth(200);
				}
				if(p.getEditor() == null){
					EditorConfiguration editorPosition = PerspectiveFactory.eINSTANCE.createEditorConfiguration();
					p.setEditor(editorPosition);
				}
				if(!p.getEditor().isUnderUserControl()){
					p.getEditor().setWidth(300);
				}
				if(p.getProperties() == null){
					PropertiesConfiguration propertiesPosition = PerspectiveFactory.eINSTANCE.createPropertiesConfiguration();
					p.setProperties(propertiesPosition);
				}
				p.getProperties().setHeight(200);
				p.getProperties().setPosition(PositionInPerspective.BOTTOM);
				if(p.getInbox() == null){
					InboxConfiguration inboxConfiguration = PerspectiveFactory.eINSTANCE.createInboxConfiguration();
					p.setInbox(inboxConfiguration);
				}
				if(!p.getInbox().isUnderUserControl()){
					p.getInbox().setPosition(PositionInPerspective.LEFT_TOP);
					p.getInbox().setWidth(200);
					p.getInbox().setHeight(300);
				}
				if(p.getOutbox() == null){
					OutboxConfiguration outboxConfiguration = PerspectiveFactory.eINSTANCE.createOutboxConfiguration();
					p.setOutbox(outboxConfiguration);
				}
				if(!p.getOutbox().isUnderUserControl()){
					p.getOutbox().setPosition(PositionInPerspective.LEFT_BOTTOM);
					p.getOutbox().setWidth(200);
					p.getOutbox().setHeight(300);
				}
			}
			visitRecursively(emfWorkspace);
		}else{
			TreeIterator<EObject> allContents = resource.getAllContents();
			while(allContents.hasNext()){
				EObject eObject = allContents.next();
				if(eObject instanceof NavigationConstraint){
					NavigationConstraint ec = (NavigationConstraint) eObject;
					if(ec.getUmlElementUid()!=null){//Could be auxiliary constraint
						Element e = emfWorkspace.getModelElement(ec.getUmlElementUid());
						if(e != null){
							map.put(e, ec);
						}
						
					}
				}
			}
		}
		new UmlUimLinks(resource, emfWorkspace);
	}
	public void visitOperation(Operation o){
		if(!UserInterfaceUtil.isUnderUserControl(explorerPosition) && explorerPosition != null){
			Classifier owner = (Classifier) o.getOwner();
			if(canDisplayInTree(owner)){
				ClassNavigationConstraint ecc = findOrCreateClassConstraint(owner);
				OperationNavigationConstraint eoc = (OperationNavigationConstraint) UserInterfaceUtil.findRepresentingElement(o, ecc.getOperations());
				if(eoc == null){
					eoc = PerspectiveFactory.eINSTANCE.createOperationNavigationConstraint();
					eoc.setUmlElementUid(EmfWorkspace.getId(o));
					ecc.getOperations().add(eoc);
					eoc.setLabelOverride(UimFactory.eINSTANCE.createLabels());
				}
				if(!eoc.isUnderUserControl()){
					eoc.setOpenToPublic(false);
					eoc.setHidden(false);
					eoc.setInheritFromParent(false);
					eoc.setName(o.getName());
					eoc.setRequiresGroupOwnership(true);
					eoc.setRequiresOwnership(false);
				}
				doParameters(eoc.getParameters(), o.getOwnedParameters());
				map.put(o, eoc);
			}
		}
	}
	public void visitProperty(Property p){
		if(!UserInterfaceUtil.isUnderUserControl(explorerPosition) && explorerPosition != null){
			if(p.getAssociationEnd() == null){// Qualifier
				Classifier classifier = null;
				if(p.getOtherEnd() != null && p.getOtherEnd().getType() != null){
					// We want to catch all properties whether they are navigable or not'
					classifier = (Classifier) p.getOtherEnd().getType();
				}
				if(classifier == null){
					classifier = EmfPropertyUtil.getOwningClassifier(p);
				}
				if(canDisplayInTree(classifier)){
					ClassNavigationConstraint ecc = findOrCreateClassConstraint(classifier);
					PropertyNavigationConstraint eoc = (PropertyNavigationConstraint) UserInterfaceUtil.findRepresentingElement(p, ecc.getProperties());
					if(eoc == null){
						eoc = PerspectiveFactory.eINSTANCE.createPropertyNavigationConstraint();
						eoc.setUmlElementUid(EmfWorkspace.getId(p));
						eoc.setLabelOverride(UimFactory.eINSTANCE.createLabels());
						ecc.getProperties().add(eoc);
					}
					populateMultiplicityConstraint(p, eoc, p.isComposite());
				}
			}
		}
	}
	public void populateMultiplicityConstraint(MultiplicityElement p,MultiplicityElementNavigationConstraint eoc,boolean isCreationContext){
		if(!eoc.isUnderUserControl()){
			eoc.setOpenToPublic(false);
			eoc.setHidden(!isCreationContext);
			eoc.setInheritFromParent(false);
			eoc.setName(((NamedElement) p).getName());
			eoc.setRequiresGroupOwnership(true);
			eoc.setRequiresOwnership(false);
			if(eoc.getAddConstraint() == null){
				eoc.setAddConstraint(PerspectiveFactory.eINSTANCE.createNavigationConstraint());
				eoc.getAddConstraint().setLabelOverride(UimFactory.eINSTANCE.createLabels());
			}
			if(!eoc.getAddConstraint().isUnderUserControl()){
				eoc.getAddConstraint().setHidden(!isCreationContext);
				eoc.getAddConstraint().setInheritFromParent(false);
				eoc.getAddConstraint().setName(((NamedElement) p).getName() + "Adding");
				eoc.getAddConstraint().setRequiresGroupOwnership(true);
				eoc.getAddConstraint().setRequiresOwnership(false);
			}
			if(eoc.getRemoveConstraint() == null){
				eoc.setRemoveConstraint(PerspectiveFactory.eINSTANCE.createNavigationConstraint());
				eoc.getRemoveConstraint().setLabelOverride(UimFactory.eINSTANCE.createLabels());
			}
			if(!eoc.getRemoveConstraint().isUnderUserControl()){
				eoc.getRemoveConstraint().setHidden(!isCreationContext);
				eoc.getRemoveConstraint().setInheritFromParent(false);
				eoc.getRemoveConstraint().setName(((NamedElement) p).getName() + "Removal");
				eoc.getRemoveConstraint().setRequiresGroupOwnership(true);
				eoc.getRemoveConstraint().setRequiresOwnership(false);
			}
		}
		map.put(p, eoc);
	}
	public void visitClassifier(Classifier c){
		if(!UserInterfaceUtil.isUnderUserControl(explorerPosition) && explorerPosition != null){
			if(canDisplayInTree(c)){
				if(c instanceof Behavior){
					Behavior b = (Behavior) c;
					if(EmfBehaviorUtil.isStandaloneTask(b) || EmfBehaviorUtil.isProcess(b) && EmfBehaviorUtil.getContext(b) != null){
						visitBehavior(b);
					}
				}else{
					ClassNavigationConstraint ecc = findOrCreateClassConstraint(c);
					if(!ecc.isUnderUserControl()){
						ecc.setOpenToPublic(EmfClassifierUtil.isBusinessComponent(c) || EmfClassifierUtil.isBusinessService(c)
								|| EmfClassifierUtil.isBusinessCollaboration(c));
						ecc.setHidden(c.eClass().equals(UMLPackage.eINSTANCE.getAssociation()) || EmfClassifierUtil.isStructuredDataType(c)
								|| EmfClassifierUtil.isBusinessActor(c));
						ecc.setInheritFromParent(false);
						ecc.setName(c.getName());
						ecc.setRequiresGroupOwnership(true);
						ecc.setRequiresOwnership(false);
					}
					map.put(c, ecc);
				}
			}
		}
	}
	public void visitBehavior(Behavior b){
		String id = EmfWorkspace.getId(b);
		ClassNavigationConstraint ecc = findOrCreateClassConstraint(EmfBehaviorUtil.getContext(b));
		BehaviorNavigationConstraint ebc = (BehaviorNavigationConstraint) UserInterfaceUtil.findRepresentingElement(b, ecc.getBehaviors());
		if(ebc == null){
			ebc = PerspectiveFactory.eINSTANCE.createBehaviorNavigationConstraint();
			ebc.setUmlElementUid(id);
			ebc.setLabelOverride(UimFactory.eINSTANCE.createLabels());
			ecc.getBehaviors().add(ebc);
		}
		if(!ebc.isUnderUserControl()){
			ebc.setOpenToPublic(false);
			ebc.setHidden(false);
			ebc.setInheritFromParent(false);
			ebc.setName(b.getName());
			ebc.setRequiresGroupOwnership(true);
			ebc.setRequiresOwnership(false);
		}
		EList<ParameterNavigationConstraint> parameters = ebc.getParameters();
		EList<Parameter> ownedParameters = b.getOwnedParameters();
		doParameters(parameters, ownedParameters);
		map.put(b, ebc);
	}
	public void doParameters(EList<ParameterNavigationConstraint> parameters,EList<Parameter> ownedParameters){
		for(Parameter p:ownedParameters){
			ParameterNavigationConstraint pc = (ParameterNavigationConstraint) UserInterfaceUtil.findRepresentingElement(p, parameters);
			if(pc == null){
				pc = PerspectiveFactory.eINSTANCE.createParameterNavigationConstraint();
				pc.setUmlElementUid(EmfWorkspace.getId(p));
				pc.setLabelOverride(UimFactory.eINSTANCE.createLabels());
				parameters.add(pc);
			}
			populateMultiplicityConstraint(p, pc, EmfParameterUtil.isResult(p.getDirection()));
		}
	}
	private boolean canDisplayInTree(Classifier c){
		return EmfClassifierUtil.isPersistentComplexStructure(c) && !(c instanceof DataType);
	}
	private ClassNavigationConstraint findOrCreateClassConstraint(Classifier c){
		String id = EmfWorkspace.getId(c);
		ClassNavigationConstraint ecc = (ClassNavigationConstraint) UserInterfaceUtil.findRepresentingElement(c, this.explorerPosition.getClasses());
		if(ecc == null){
			ecc = PerspectiveFactory.eINSTANCE.createClassNavigationConstraint();
			this.explorerPosition.getClasses().add(ecc);
			ecc.setUmlElementUid(id);
			ecc.setName(c.getName());
			ecc.setLabelOverride(UimFactory.eINSTANCE.createLabels());
		}
		return ecc;
	}
	public void visitOnly(Element element){
		if(element instanceof Operation){
			visitOperation((Operation) element);
		}else if(element instanceof Property){
			visitProperty((Property) element);
		}else if(element instanceof Classifier){
			visitClassifier((Classifier) element);
		}
	}
	public void visitRecursively(Element modelElement){
		visitOnly(modelElement);
		Collection<? extends Element> children = modelElement instanceof EmfWorkspace? ((EmfWorkspace)modelElement).getPotentialGeneratingModels(): EmfElementFinder.getCorrectOwnedElements(modelElement);
		for(Element eObject:children){
			visitRecursively(eObject);
		}
	}
	public NavigationConstraint getConstraintFor(Element e){
		return map.get(e);
	}
}
