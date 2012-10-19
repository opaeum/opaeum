package org.opaeum.metamodel.workspace;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.ocl.uml.OCL.Helper;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfActivityUtil.TypeAndMultiplicity;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.ocl.uml.AbstractOclContext;
import org.opaeum.ocl.uml.OpaeumEnvironment;
import org.opaeum.ocl.uml.OpaeumParentEnvironment;
import org.opaeum.ocl.uml.OpaqueActionActionContext;
import org.opaeum.ocl.uml.OpaqueBehaviorContext;
import org.opaeum.ocl.uml.OpaqueExpressionContext;

public class OpaeumOcl{
	// Using weakhashmap safely as the keys will only be garbage collected if the elements are deleted from the model
	private Map<OpaqueExpression,OpaqueExpressionContext> opaqueExpressions = new WeakHashMap<OpaqueExpression,OpaqueExpressionContext>();
	private Map<OpaqueBehavior,OpaqueBehaviorContext> opaqueBehaviors = new WeakHashMap<OpaqueBehavior,OpaqueBehaviorContext>();
	private Map<OpaqueAction,OpaqueActionActionContext> opaqueActions = new WeakHashMap<OpaqueAction,OpaqueActionActionContext>();
	private OpaeumParentEnvironment parentEnvironment;
	public OpaeumOcl(ResourceSet rst){
		super();
		this.parentEnvironment = new OpaeumParentEnvironment(rst);
	}
	public OpaqueExpressionContext getOclExpressionContext(OpaqueExpression valueSpec){
		OpaqueExpressionContext result = opaqueExpressions.get(valueSpec);
		if(result == null){
			Element context = EmfValueSpecificationUtil.getContext(valueSpec);
			OCL ocl = createOcl(context, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OpaqueExpressionContext(valueSpec, helper);
			opaqueExpressions.put(valueSpec, result);
		}
		return result;
	}
	public void reset(){
		opaqueExpressions.clear();
		opaqueBehaviors.clear();
		opaqueActions.clear();
	}
	public OpaqueBehaviorContext getOclBehaviorContext(OpaqueBehavior valueSpec){
		OpaqueBehaviorContext result = opaqueBehaviors.get(valueSpec);
		if(result == null){
			OCL ocl = createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OpaqueBehaviorContext(valueSpec, helper);
			opaqueBehaviors.put(valueSpec, result);
		}
		return result;
	}
	public OpaqueActionActionContext getOclActionContext(OpaqueAction valueSpec){
		OpaqueActionActionContext result = opaqueActions.get(valueSpec);
		if(result == null){
			OCL ocl = createOcl(valueSpec, Collections.<String,Classifier>emptyMap());
			Helper helper = ocl.createOCLHelper();
			result = new OpaqueActionActionContext(valueSpec, helper);
			opaqueActions.put(valueSpec, result);
		}
		return result;
	}
	public OCL createOcl(Element context,Map<String,Classifier> vars){
		OpaeumEnvironment env = new OpaeumEnvironment(context, parentEnvironment);
		env.addVariables(vars);
		return OCL.newInstance(env);
	}
	public TypeResolver<Classifier,Operation,Property> getTypeResolver(){
		return parentEnvironment.getTypeResolver();
	}
	public Collection<AbstractOclContext> getOclContexts(){
		Collection<AbstractOclContext> result = new HashSet<AbstractOclContext>(this.opaqueActions.values());
		result.addAll(this.opaqueBehaviors.values());
		result.addAll(this.opaqueExpressions.values());
		return result;
	}
	public OpaqueExpressionContext getArtificationExpression(NamedElement ne,String tagName){
		for(EObject e:ne.getStereotypeApplications()){
			EStructuralFeature f = e.eClass().getEStructuralFeature(tagName);
			if(f != null){
				OpaqueExpression oe = (OpaqueExpression) e.eGet(f);
				if(oe != null){
					return getOclExpressionContext(oe);
				}
			}
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Collection<OpaqueExpressionContext> getArtificialExpressions(NamedElement ne,String tagName){
		Collection<OpaqueExpressionContext> result = new HashSet<OpaqueExpressionContext>();
		for(EObject e:ne.getStereotypeApplications()){
			EStructuralFeature f = e.eClass().getEStructuralFeature(tagName);
			if(f != null){
				Collection<OpaqueExpression> val = (Collection<OpaqueExpression>) e.eGet(f);
				for(OpaqueExpression oe:val){
					result.add(getOclExpressionContext(oe));
				}
			}
		}
		return result;
	}
	public Classifier getTargetType(Action a){
		InputPin pin = EmfActionUtil.getTargetPin(a);
		Classifier type = calculateType(pin);
		if(type == null){
			type = EmfActionUtil.getTargetType(a);
		}
		if(type instanceof CollectionType){
			type = ((CollectionType) type).getElementType();
		}
		return (Classifier) type;
	}
	public Classifier calculateType(InputPin pin){
		Type type = null;
		if(pin instanceof ValuePin){
			ValueSpecification value = ((ValuePin) pin).getValue();
			if(value instanceof OpaqueExpression){
				OpaqueExpressionContext ctx = getOclExpressionContext((OpaqueExpression) value);
				if(!ctx.hasErrors()){
					type = ctx.getExpression().getType();
					if(type instanceof CollectionType){
						type = ((CollectionType) type).getElementType();
					}
				}
			}
		}
		if(type == null && pin != null && pin.getIncomings().size() > 0 && pin.getIncomings().get(0) instanceof ObjectFlow){
			ObjectFlow of = (ObjectFlow) pin.getIncomings().get(0);
			TypeAndMultiplicity mt = EmfActivityUtil.findSourceType(of);
			if(mt != null){
				type = mt.getType();
			}
		}
		return (Classifier) type;
	}
	public Classifier getActualType(MultiplicityElement me){
		Classifier type = null;
		if(me instanceof TypedElement){
			type = (Classifier) ((TypedElement) me).getType();
		}else if(me instanceof Variable){
			type = (Classifier) ((Variable) me).getType();
		}
		if(type != null){
			CollectionKind collectionKind = EmfPropertyUtil.getCollectionKind(me);
			if(collectionKind == null){
				return type;
			}else{
				return (Classifier) parentEnvironment.getTypeResolver().resolveCollectionType(collectionKind, type);
			}
		}
		return type;
	}
	public OpaeumLib getLibrary(){
		return this.parentEnvironment.getLibrary();
	}
}