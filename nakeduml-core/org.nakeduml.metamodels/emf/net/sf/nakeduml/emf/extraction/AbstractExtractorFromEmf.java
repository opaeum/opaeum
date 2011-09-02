package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.visit.VisitSpec;
import net.sf.nakeduml.metamodel.bpm.internal.NakedDeadlineImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedPackageableElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedTypedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Type;
import org.nakeduml.eclipse.EmfElementFinder;
import org.nakeduml.eclipse.EmfValidationUtil;

public abstract class AbstractExtractorFromEmf extends EmfElementVisitor implements ITransformationStep{
	protected INakedModelWorkspace nakedWorkspace;
	protected EmfWorkspace emfWorkspace;
	private Set<INakedElement> affectedElements = new HashSet<INakedElement>();
	@Override
	public Collection<? extends Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			return ((EmfWorkspace) root).getOwnedElements();
		}else{
			Collection<? extends Element> children = super.getChildren(root);
			return children;
		}
	}
	@Override
	protected final void maybeVisit(Element o,VisitSpec v){
		super.maybeVisit(o, v);
		emfWorkspace.putElement(o);
	}
	@Override
	protected Object resolvePeer(Element o,Class<?> peerClass){
		Element e = o;
		INakedElement ne = getNakedPeer(e);
		Element owner = (Element) EmfElementFinder.getContainer(e);
		if(ne == null){
			ne = createElementFor(e, peerClass);
			if(ne != null){
				initialize(ne, e, owner);
			}
		}else if(o instanceof NamedElement){
			if(EmfElementFinder.getContainer(e) == null && ne.getOwnerElement() != null){
				ne.getOwnerElement().removeOwnedElement(ne);
				ne.markForDeletion();
			}else{
				INakedElement nakedOwner = getNakedPeer(owner);
				if(nakedOwner != null){
					if(ne.getOwnerElement() != null && !(nakedOwner.equals(ne.getOwnerElement()))){
						ne.getOwnerElement().removeOwnedElement(ne);
					}
					if(!nakedOwner.getOwnedElements().contains(ne)){
						nakedOwner.addOwnedElement(ne);
					}
				}
			}
			ne.setName(((NamedElement) o).getName());
		}
		return ne;
	}
	// /Visiting logic ends---//
	protected void initialize(INakedElement nakedElement,Element modelElement,Element owner){
		if((owner == null || getId(owner) == null) && nakedElement.getOwnerElement() != null){
			nakedElement.getOwnerElement().removeOwnedElement(nakedElement);
			nakedElement.setOwnerElement(null);
		}else{
			if(nakedElement.getOwnerElement() != null){
				// remove from previous ownerElement
				nakedElement.getOwnerElement().removeOwnedElement(nakedElement);
			}
			if(nakedElement instanceof INakedPackageableElement){
				INakedPackageableElement pe = (INakedPackageableElement) nakedElement;
				pe.setVisibility(resolveVisibility(modelElement));
			}
			String name = null;
			if(modelElement instanceof NamedElement){
				name = ((NamedElement) modelElement).getName();
			}
			if(nakedElement.getId() == null || nakedElement.getName() == null){
				// allow callers to do own initilization
				nakedElement.initialize(getId(modelElement), name, true);
			}
			this.nakedWorkspace.putModelElement(nakedElement);
			if(modelElement.getOwnedComments().size() > 0){
				Comment comment = modelElement.getOwnedComments().iterator().next();
				nakedElement.setDocumentation(comment.getBody());
			}
			INakedElement nakedOwner = getNakedPeer(owner);
			if(nakedOwner != null){
				// if ownerElement=nul assume linkage will be done elsewher, i.e. state.doActivity etc.
				nakedOwner.addOwnedElement(nakedElement);
			}
		}
	}
	private VisibilityKind resolveVisibility(Element modelElement){
		VisibilityKind octopusKind = VisibilityKind.PUBLIC;
		if(modelElement instanceof PackageableElement){
			PackageableElement epe = (PackageableElement) modelElement;
			if(epe.getVisibility() == org.eclipse.uml2.uml.VisibilityKind.PROTECTED_LITERAL){
				octopusKind = VisibilityKind.PROTECTED;
			}else if(epe.getVisibility() == org.eclipse.uml2.uml.VisibilityKind.PRIVATE_LITERAL){
				octopusKind = VisibilityKind.PRIVATE;
			}else if(epe.getVisibility() == org.eclipse.uml2.uml.VisibilityKind.PACKAGE_LITERAL){
				octopusKind = VisibilityKind.NONE;
			}
		}
		return octopusKind;
	}
	String getEventId(Trigger t){
		return getId(t) + getId(t.getEvent());
	}
	public String getId(EModelElement e){
		return emfWorkspace.getId(e);
	}
	protected INakedElement getNakedPeer(Element e){
		if(e == null || e instanceof EmfWorkspace){
			return null;
		}else{
			return this.nakedWorkspace.getModelElement(getId(e));
		}
	}
	protected ParsedOclString buildParsedOclString(NamedElement oe,EList<String> languages,EList<String> bodies,OclUsageType usage){
		String body = null;
		for(int i = 0;i < languages.size();i++){
			String language = languages.get(i);
			if(language.equals("OCL") && bodies.size() > i){
				body = bodies.get(i);
				break;
			}
		}
		if(body == null && bodies.size() == 1){
			body = bodies.get(0);
		}
		ParsedOclString string = new ParsedOclString(oe.getName() == null ? body : oe.getName(), usage);
		if(body != null && body.trim().length() > 0 && !body.equals(EmfValidationUtil.TYPE_EXPRESSION_HERE)){
			string.setExpressionString(body);
		}else{
			// will generate an error in NakedParsedOclStringResolver
		}
		return string;
	}
	protected IOclLibrary getOclLibrary(){
		return this.nakedWorkspace.getOclEngine().getOclLibrary();
	}
	protected ErrorMap getErrorMap(){
		return nakedWorkspace.getErrorMap();
	}
	public void initialize(EmfWorkspace emfWorkspace,INakedModelWorkspace workspace2){
		this.affectedElements.clear();
		this.nakedWorkspace = workspace2;
		this.emfWorkspace = emfWorkspace;
	}
	private NakedMultiplicityImpl toNakedMultiplicity(MultiplicityElement te){
		int lower = te.getLower();
		int upper = te.getUpper();
		if(upper == -1){
			upper = Integer.MAX_VALUE;
		}
		return new NakedMultiplicityImpl(lower, upper);
	}
	protected void populateMultiplicityAndBaseType(MultiplicityElement emfNode,Type type,NakedTypedElementImpl ae){
		populateMultiplicity(emfNode, ae);
		ae.setBaseType((INakedClassifier) getNakedPeer(type));
	}
	protected void populateMultiplicity(MultiplicityElement emfNode,INakedMultiplicityElement ae){
		ae.setMultiplicity(toNakedMultiplicity(emfNode));
		ae.setIsOrdered(emfNode.isOrdered());
		ae.setIsUnique(emfNode.isUnique());
	}
	protected boolean isDeadline(TimeEvent t){
		return StereotypesHelper.hasStereotype(t, StereotypeNames.DEADLINE);
	}
	protected void initTimeEvent(TimeEvent emfTimeEvent,AbstractTimeEventImpl nakedTimeEvent){
		if(emfTimeEvent.getWhen() != null && emfTimeEvent.getWhen().getExpr() instanceof OpaqueExpression){
			// TODO NB!!! it could also be an InstanceSpecification
			OpaqueExpression oe = (OpaqueExpression) emfTimeEvent.getWhen().getExpr();
			INakedValueSpecification nvs = new NakedValueSpecificationImpl(buildParsedOclString(oe, oe.getLanguages(), oe.getBodies(), OclUsageType.DEF));
			initialize(nvs, oe, emfTimeEvent);
			nvs.initialize(getId(oe), oe.getName(), false);
			nakedTimeEvent.setWhen(nvs);
		}
		nakedTimeEvent.setRelative(emfTimeEvent.isRelative());
	}
	protected void initializeDeadlines(Stereotype responsibility,Element definedResponsibiliti){
		// NB!!! we are deviating from the pattern of leaving stereotypes for last because we need to get the containment tree the
		// way NakedML expects it
		EList<TimeEvent> e = (EList<TimeEvent>) definedResponsibiliti.getValue(responsibility, "deadlines");
		for(TimeEvent timeEvent:e){
			// NB!!! Deviation from UML2 metamodel:
			// Deadline Time Events are stored with its Definied Responsibility
			// The OpaqueAction, CallBehaviorAction of Operations under which it is stored provides the context of the when expression
			NakedDeadlineImpl deadline = new NakedDeadlineImpl();
			initialize(deadline, timeEvent, definedResponsibiliti);
			initTimeEvent(timeEvent, deadline);
		}
	}
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		try{
			return (NakedElementImpl) peerClass.newInstance();
		}catch(IllegalAccessException e1){
			throw new RuntimeException(e1);
		}catch(InstantiationException e1){
			throw new RuntimeException(e1);
		}
	}
	protected Set<INakedElement> getAffectedElements(){
		return affectedElements;
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
}
