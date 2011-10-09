package org.opaeum.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.metamodel.bpm.internal.NakedDeadlineImpl;
import org.opaeum.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedPackageableElement;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.internal.NakedElementImpl;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.NakedTypedElementImpl;
import org.opaeum.metamodel.core.internal.NakedValueSpecificationImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public abstract class AbstractExtractorFromEmf extends EmfElementVisitor implements ITransformationStep{
	protected INakedModelWorkspace nakedWorkspace;
	protected EmfWorkspace emfWorkspace;
	private Set<INakedElement> affectedElements = new HashSet<INakedElement>();
	protected synchronized void addAffectedElement(INakedElement a){
		affectedElements.add(a);
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
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
	public void visitRecursively(Element o){
		if(requiresExtraction(o)){
			super.visitRecursively(o);
		}
	}
	protected final boolean requiresExtraction(Element o){
		if(o instanceof Profile || o instanceof Model){
			boolean b = emfWorkspace.getGeneratingModelsOrProfiles().contains(o) || getNakedPeer(o) == null
					|| !((INakedRootObject) getNakedPeer(o)).getStatus().isExtracted();
			return b;
		}else{
			return true;
		}
	}
	@Override
	protected final void maybeVisit(Element o,VisitSpec v){
		super.maybeVisit(o, v);
	}
	@Override
	protected Object resolvePeer(Element o,Class<?> peerClass){
		INakedElement ne = getNakedPeer(o);
		Element owner = (Element) EmfElementFinder.getContainer(o);
		if(ne == null && o.eResource()!=null){
			ne = createElementFor(o, peerClass);
			if(ne != null){
				initialize(ne, o, owner);
			}
		}else if(o instanceof NamedElement && ne!=null){
			if((owner == null || getId(owner) == null || o.eResource()==null) && ne.getOwnerElement() != null ){
				//do deletion
				nakedWorkspace.removeModelElement(ne);
				ne.getOwnerElement().removeOwnedElement(ne, true);
				ne.markForDeletion();
			}else{
				//do reparenting
				INakedElement nakedOwner = getNakedPeer(owner);
				if(nakedOwner != null){
					if(ne.getOwnerElement() != null && !(nakedOwner.equals(ne.getOwnerElement()))){
						ne.getOwnerElement().removeOwnedElement(ne, false);
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
		if(modelElement.getOwnedComments().size() > 0){
			Comment comment = modelElement.getOwnedComments().iterator().next();
			nakedElement.setDocumentation(comment.getBody());
		}
		this.nakedWorkspace.putModelElement(nakedElement);
		INakedElement nakedOwner = getNakedPeer(owner);
		if(nakedOwner != null){
			// if ownerElement=nul assume linkage will be done elsewher, i.e. state.doActivity etc.
			nakedOwner.addOwnedElement(nakedElement);
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
		return getId(t.getEvent()) + getId(t);
	}
	public String getId(EObject e){
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
		if(emfTimeEvent.getWhen() != null && emfTimeEvent.getWhen().getExpr() != null){
			ValueSpecification vs= emfTimeEvent.getWhen().getExpr() ;
			String id = getId(vs) + nakedTimeEvent.getId();
			INakedValueSpecification nvs = (INakedValueSpecification) nakedWorkspace.getModelElement(id);
			if(nvs == null){
				nvs = new NakedValueSpecificationImpl();
				nvs.initialize(id, vs.getName(), false);
				nakedWorkspace.putModelElement(nvs);
			}
			if(emfTimeEvent.getWhen().getExpr() instanceof OpaqueExpression){
				OpaqueExpression oe = (OpaqueExpression) emfTimeEvent.getWhen().getExpr();
				nvs.setValue(buildParsedOclString(oe, oe.getLanguages(), oe.getBodies(), OclUsageType.DEF));
			}else if(emfTimeEvent.getWhen().getExpr() instanceof InstanceValue){
				INakedElement instance = getNakedPeer(((InstanceValue) emfTimeEvent.getWhen().getExpr()).getInstance());
				nvs.setValue(instance);
				if(!(instance instanceof INakedEnumerationLiteral)){
					nvs.addOwnedElement(instance);
				}
			}
			nakedTimeEvent.setWhen(nvs);
		}
		nakedTimeEvent.setRelative(emfTimeEvent.isRelative());
	}
	@SuppressWarnings("unchecked")
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
			deadline.getRootObject().addDirectlyAccessibleElement(deadline);
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
	Set<INakedElement> getAffectedElements(){
		return affectedElements;
	}
	protected boolean ignoreDeletedElements(){
		return true;
	}
}
