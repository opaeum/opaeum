package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.bpm.internal.NakedDeadlineImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.AbstractTimeEventImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedPackageableElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedTypedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;

public abstract class AbstractExtractorFromEmf extends EmfElementVisitor implements TransformationStep{
	protected INakedModelWorkspace nakedWorkspace;
	private HashSet<INakedElement> existingModels;
	@Override
	public Collection<? extends Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			Map<String,Element> children = new HashMap<String,Element>();
			EmfWorkspace w = (EmfWorkspace) root;
			for(Element element:w.getOwnedElements()){
				INakedElement nakedElement = nakedWorkspace.getModelElement(getId(element));
				if(nakedElement == null || !existingModels.contains(nakedElement)){
					children.put(getId(element), element);
				}
			}
			return children.values();
		}else{
			return super.getChildren(root);
		}
	}
	@Override
	protected Object resolvePeer(Element o,Class<?> peerClass){
		Element e = o;
		INakedElement ne = getNakedPeer(e);
		if(ne == null){
			ne = createElementFor(e, peerClass);
			if(ne != null){
				initialize(ne, e, e.getOwner());
			}
		}
		return ne;
	}
	// /Visiting logic ends---//
	protected void initialize(INakedElement nakedElement,Element modelElement,Element owner){
		boolean attachToOwner = false;
		if(owner != null && getId(owner) != null){
			INakedElement nakedOwner = getNakedPeer(owner);
			if(nakedElement.getOwnerElement() != null){
				nakedElement.getOwnerElement().removeOwnedElement(nakedElement);
			}
			nakedElement.setOwnerElement(nakedOwner);
			attachToOwner = true;
		}
		if(nakedElement.getOwnerElement() == null && owner != null && !(modelElement instanceof Behavior)){
			// BEhaviors sometimes fall outside of a namespace in the
			// containment hierarchy - transitions, edges ,states
			System.out.println("ownerElement not found:" + owner);
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
		if(attachToOwner){
			nakedElement.getOwnerElement().removeOwnedElement(nakedElement);
			nakedElement.getOwnerElement().addOwnedElement(nakedElement);
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
	static String getEventId(Trigger t){
		return getId(t) + getId(t.getEvent());
	}
	public static String getId(EModelElement e){
		EAnnotation eAnnotation = e.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
		if(eAnnotation != null){
			String string = eAnnotation.getDetails().get("uuid");
			if(string != null){
				return string;
			}
		}
		Resource eResource = e.eResource();
		return eResource.getURI().lastSegment() + "@" + eResource.getURIFragment(e);
	}
	protected INakedElement getNakedPeer(Element e){
		if(e == null || e instanceof EmfWorkspace){
			return null;
		}else{
			return this.nakedWorkspace.getModelElement(getId(e));
		}
	}
	/**
	 * Returns a ValueSpecification for use InstanceSpecfications, pins and guards on activity edges and state transitions
	 * 
	 */
	protected INakedValueSpecification getValueSpecification(INakedElement element,ValueSpecification value,OclUsageType usage){
		NakedValueSpecificationImpl nakedValueSpecification = null;//(NakedValueSpecificationImpl) getNakedPeer(value);
		if(nakedValueSpecification == null){
			if(value instanceof OpaqueExpression){
				OpaqueExpression oe = ((OpaqueExpression) value);
				nakedValueSpecification = buildValueSpecificationFromOpaqueExpression(element, value, usage, oe);
			}else if(value instanceof TimeExpression && ((TimeExpression)value).getExpr() instanceof OpaqueExpression){
				OpaqueExpression oe=(OpaqueExpression) ((TimeExpression)value).getExpr();
				nakedValueSpecification = buildValueSpecificationFromOpaqueExpression(element, value, usage, oe);
			}else{
				if(value instanceof LiteralString){
					nakedValueSpecification = buildStringLiteral(value);
				}else if(value instanceof LiteralBoolean){
					nakedValueSpecification = buildBooleanLiteral(value);
				}else if(value instanceof LiteralInteger){
					nakedValueSpecification = buildIntegerLiteral(value);
				}else if(value instanceof LiteralUnlimitedNatural){
					nakedValueSpecification = buildUnlimitiedLiteral(value);
				}else if(value instanceof InstanceValue){
					nakedValueSpecification = buildInstanceValue(element, value, usage);
				}
			}
		}
		return nakedValueSpecification;
	}
	protected NakedValueSpecificationImpl buildValueSpecificationFromOpaqueExpression(INakedElement element,ValueSpecification value,OclUsageType usage,
			OpaqueExpression oe){
		ParsedOclString bodyExpression = buildParsedOclString(oe, usage, oe.getLanguages(), oe.getBodies());
		NakedValueSpecificationImpl nakedValueSpecification=null;
		if(bodyExpression != null){
			nakedValueSpecification = new NakedValueSpecificationImpl();
			nakedValueSpecification.setValue(bodyExpression);
			initialize(nakedValueSpecification, value, null);
			nakedValueSpecification.setOwnerElement(element);
			element.addOwnedElement(nakedValueSpecification);
			if(value.getType() != null){
				// TODO - type may not be available yet
				nakedValueSpecification.setType((INakedClassifier) getNakedPeer(value.getType()));
			}
			while(!(element instanceof IClassifier )){
				element=(INakedElement) element.getOwnerElement();
			}
			bodyExpression.setContext((IClassifier) element, nakedValueSpecification);
		}
		return nakedValueSpecification;
	}
	private NakedValueSpecificationImpl buildInstanceValue(INakedElement element,ValueSpecification value,OclUsageType usage){
		NakedValueSpecificationImpl nakedValueSpecification = null;
		InstanceValue instanceValue = (InstanceValue) value;
		if(instanceValue.getInstance() != null){
			if(instanceValue.getInstance().getSpecification() != null){
				// simply resolve this instancespecification as
				// another
				// value
				nakedValueSpecification = (NakedValueSpecificationImpl) getValueSpecification(element, instanceValue.getInstance().getSpecification(), usage);
			}else{
				nakedValueSpecification = new NakedValueSpecificationImpl();
				// Most likely an enum literal
				nakedValueSpecification.setValueId(getId(instanceValue.getInstance()));
				initialize(nakedValueSpecification, value, value.getOwner());
				// Take note that INakedInstanceSpecifications
				// SHOULD be
				// created by the InstanceBuilder
				// Slot will be created in the InstanceBuilder
			}
		}
		return nakedValueSpecification;
	}
	private NakedValueSpecificationImpl buildUnlimitiedLiteral(ValueSpecification value){
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		LiteralUnlimitedNatural lun = (LiteralUnlimitedNatural) value;
		nakedValueSpecification.setValue(new Integer(lun.unlimitedValue()));
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}
	private NakedValueSpecificationImpl buildIntegerLiteral(ValueSpecification value){
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		nakedValueSpecification.setValue(new Integer(((LiteralInteger) value).integerValue()));
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}
	private NakedValueSpecificationImpl buildBooleanLiteral(ValueSpecification value){
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		nakedValueSpecification.setValue(new Boolean(((LiteralBoolean) value).booleanValue()));
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}
	private NakedValueSpecificationImpl buildStringLiteral(ValueSpecification value){
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		nakedValueSpecification.setValue(((LiteralString) value).stringValue());
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}
	protected ParsedOclString buildParsedOclString(NamedElement oe,OclUsageType usageType,EList<String> languages,EList<String> bodies){
		String body = null;
		for(int i = 0;i < languages.size();i++){
			String language = languages.get(i);
			if(language.equals("OCL") && bodies.size() > i){
				body = bodies.get(i);
				break;
			}
		}
		if(body == null && bodies.size() == 1){
			// Need something here
			body = bodies.get(0);
		}
		// TODO Extract constant here
		if(body != null && body.trim().length() > 0 && !(body.equals("Type expression here"))){
			ParsedOclString string = new ParsedOclString(oe.getName() == null ? body : oe.getName(), usageType);
			string.setExpressionString(body);
			return string;
		}
		return null;
	}
	protected IOclLibrary getOclLibrary(){
		return this.nakedWorkspace.getOclEngine().getOclLibrary();
	}
	protected void addConstraints(PreAndPostConstrained nakedOper,List preconditions,List postconditions){
		Iterator<Constraint> iter = preconditions.iterator();
		while(iter.hasNext()){
			Constraint c = (Constraint) iter.next();
			INakedConstraint string = buildConstraint(nakedOper, OclUsageType.PRE, c, nakedOper.getContext());
			if(string != null){
				nakedOper.addPreCondition(string);
			}
		}
		Iterator post = postconditions.iterator();
		while(post.hasNext()){
			Constraint c = (Constraint) post.next();
			INakedConstraint string = buildConstraint(nakedOper, OclUsageType.POST, c, nakedOper.getContext());
			if(string != null){
				nakedOper.addPostCondition(string);
			}
		}
	}
	protected NakedConstraintImpl buildConstraint(INakedElement nakedElement,OclUsageType oclUsageType,Constraint c,INakedClassifier context){
		ValueSpecification defaultValue = c.getSpecification();
		String name = c.getName();
		ParsedOclString result = null;
		if(c.getSpecification() instanceof LiteralBoolean){
			LiteralBoolean literal = (LiteralBoolean) c.getSpecification();
			String booleanValue = String.valueOf(literal.booleanValue());
			ParsedOclString string = new ParsedOclString(name == null ? booleanValue : name, oclUsageType);
			string.setExpressionString(booleanValue);
			result = string;
		}else if(defaultValue instanceof OpaqueExpression){
			OpaqueExpression oe = (OpaqueExpression) defaultValue;
			result = buildParsedOclString(oe, oclUsageType, oe.getLanguages(), oe.getBodies());
		}else{
			// VALIDATION put an error here - valuespecification has to be an
			// ocl string or an opaque expression
			return null;
		}
		if(result != null){
			NakedConstraintImpl nc = new NakedConstraintImpl();
			initialize(nc, c, c.getOwner());
			NakedValueSpecificationImpl nvs = new NakedValueSpecificationImpl();
			initialize(nvs,c.getSpecification(),c);
			nvs.setValue(result);
			nc.setSpecification(nvs);
			result.setContext(context, nc);
		}
		return null;
	}
	protected ErrorMap getErrorMap(){
		return nakedWorkspace.getErrorMap();
	}
	public void initialize(INakedModelWorkspace workspace2){
		this.nakedWorkspace = workspace2;
		this.existingModels = new HashSet<INakedElement>(nakedWorkspace.getOwnedElements());
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
		INakedValueSpecification when = getValueSpecification(nakedTimeEvent, emfTimeEvent.getWhen(), OclUsageType.DEF);
		if(when != null){
			System.out.println("VS:"+when.getUuid());
			when.setType((INakedClassifier) getNakedPeer(emfTimeEvent.getWhen().getType()));
			nakedTimeEvent.setWhen(when);
			when.setOwnerElement(nakedTimeEvent);
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
}
