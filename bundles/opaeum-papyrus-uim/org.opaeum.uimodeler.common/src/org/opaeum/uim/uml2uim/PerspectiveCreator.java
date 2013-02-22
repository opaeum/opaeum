package org.opaeum.uim.uml2uim;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerBehaviorConstraint;
import org.opaeum.uim.perspective.ExplorerClassConstraint;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerConstraint;
import org.opaeum.uim.perspective.ExplorerOperationConstraint;
import org.opaeum.uim.perspective.ExplorerPropertyConstraint;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.util.UmlUimLinks;

public class PerspectiveCreator extends AbstractUimSynchronizer2{
	private ExplorerConfiguration explorerPosition;
	private Map<Element,ExplorerConstraint> map =new HashMap<Element,ExplorerConstraint>();
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
					this.explorerPosition = PerspectiveFactory.eINSTANCE.createExplorerConfiguration();
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
				if(eObject instanceof ExplorerConstraint){
					ExplorerConstraint ec = (ExplorerConstraint) eObject;
					Element e = emfWorkspace.getModelElement(ec.getUmlElementUid());
					if(e!=null){
						map.put(e, ec);
					}
				}
			}
		}
		new UmlUimLinks(resource,emfWorkspace);
	}
	public void visitOperation(Operation o){
		if(!UserInterfaceUtil.isUnderUserControl(explorerPosition) && explorerPosition != null){
			Classifier owner = (Classifier) o.getOwner();
			if(canDisplayInTree(owner)){
				ExplorerClassConstraint ecc = findOrCreateClassConstraint(owner);
				ExplorerOperationConstraint eoc = (ExplorerOperationConstraint) UserInterfaceUtil.findRepresentingElement(o, ecc.getOperations());
				if(eoc == null){
					eoc = PerspectiveFactory.eINSTANCE.createExplorerOperationConstraint();
					eoc.setUmlElementUid(EmfWorkspace.getId(o));
					ecc.getOperations().add(eoc);
				}
				if(!eoc.isUnderUserControl()){
					eoc.setOpenToPublic(false);
					eoc.setHidden(false);
					eoc.setInheritFromParent(false);
					eoc.setName(o.getName());
					eoc.setRequiresGroupOwnership(true);
					eoc.setRequiresOwnership(false);
				}
				map.put(o, eoc);
			}
		}
	}
	public void visitProperty(Property p){
		if(!UserInterfaceUtil.isUnderUserControl(explorerPosition) && explorerPosition != null){
			if(p.getAssociationEnd() == null){// Qualifier
				Classifier classifier = EmfPropertyUtil.getOwningClassifier(p);
				if(canDisplayInTree(classifier)){
					ExplorerClassConstraint ecc = findOrCreateClassConstraint(classifier);
					ExplorerPropertyConstraint eoc = (ExplorerPropertyConstraint) UserInterfaceUtil.findRepresentingElement(p, ecc.getProperties());
					if(eoc == null){
						eoc = PerspectiveFactory.eINSTANCE.createExplorerPropertyConstraint();
						eoc.setUmlElementUid(EmfWorkspace.getId(p));
						ecc.getProperties().add(eoc);
					}
					if(!eoc.isUnderUserControl()){
						eoc.setOpenToPublic(false);
						eoc.setHidden(!p.isComposite());
						eoc.setInheritFromParent(false);
						eoc.setName(p.getName());
						eoc.setRequiresGroupOwnership(true);
						eoc.setRequiresOwnership(false);
					}
					map.put(p, eoc);
				}
			}
		}
	}
	public void visitClassifier(Classifier c){
		if(!UserInterfaceUtil.isUnderUserControl(explorerPosition) && explorerPosition != null){
			if(canDisplayInTree(c)){
				String id = EmfWorkspace.getId(c);
				if(c instanceof Behavior){
					Behavior b = (Behavior) c;
					if(EmfBehaviorUtil.isStandaloneTask(b) || EmfBehaviorUtil.isProcess(b) && EmfBehaviorUtil.getContext(b) != null){
						ExplorerClassConstraint ecc = findOrCreateClassConstraint(EmfBehaviorUtil.getContext(b));
						ExplorerBehaviorConstraint ebc = (ExplorerBehaviorConstraint) UserInterfaceUtil.findRepresentingElement(b, ecc.getBehaviors());
						if(ebc == null){
							ebc = PerspectiveFactory.eINSTANCE.createExplorerBehaviorConstraint();
							ebc.setUmlElementUid(id);
							ecc.getBehaviors().add(ebc);
						}
						if(!ebc.isUnderUserControl()){
							ebc.setOpenToPublic(false);
							ebc.setHidden(false);
							ebc.setInheritFromParent(false);
							ebc.setName(b.getName());
							ebc.setRequiresGroupOwnership(true);
							ebc.setRequiresOwnership(false);
							ebc.setInvocationConstraint(ConstraintFactory.eINSTANCE.createUserInteractionConstraint());
							ebc.getInvocationConstraint().setOpenToPublic(false);
							ebc.getInvocationConstraint().setRequiresGroupOwnership(true);
							ebc.getInvocationConstraint().setRequiresOwnership(false);
						}
						map.put(b, ebc);

					}
				}else{
					ExplorerClassConstraint ecc = findOrCreateClassConstraint(c);
					if(!ecc.isUnderUserControl()){
						ecc.setOpenToPublic(EmfClassifierUtil.isBusinessComponent(c) || EmfClassifierUtil.isBusinessService(c)
								|| EmfClassifierUtil.isBusinessCollaboration(c));
						ecc.setHidden(c.eClass().equals(UMLPackage.eINSTANCE.getAssociation()) || EmfClassifierUtil.isStructuredDataType(c)
								|| EmfClassifierUtil.isBusinessActor(c));
						ecc.setInheritFromParent(false);
						ecc.setName(c.getName());
						ecc.setRequiresGroupOwnership(true);
						ecc.setRequiresOwnership(false);
						ecc.setNewObjectConstraint(ConstraintFactory.eINSTANCE.createUserInteractionConstraint());
						ecc.getNewObjectConstraint().setOpenToPublic(false);
						ecc.getNewObjectConstraint().setRequiresGroupOwnership(true);
						ecc.getNewObjectConstraint().setRequiresOwnership(false);
					}
					map.put(c, ecc);

				}
			}
		}
	}
	private boolean canDisplayInTree(Classifier c){
		return EmfClassifierUtil.isPersistentComplexStructure(c) && !(c instanceof DataType);
	}
	private ExplorerClassConstraint findOrCreateClassConstraint(Classifier c){
		String id = EmfWorkspace.getId(c);
		ExplorerClassConstraint ecc = (ExplorerClassConstraint) UserInterfaceUtil.findRepresentingElement(c, this.explorerPosition.getClasses());
		if(ecc == null){
			ecc = PerspectiveFactory.eINSTANCE.createExplorerClassConstraint();
			this.explorerPosition.getClasses().add(ecc);
			ecc.setUmlElementUid(id);
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
		for(Element eObject:EmfElementFinder.getCorrectOwnedElements(modelElement)){
			visitRecursively(eObject);
		}
	}
	public ExplorerConstraint getConstraintFor(Element e){
		return map.get(e);
	}
	
}
