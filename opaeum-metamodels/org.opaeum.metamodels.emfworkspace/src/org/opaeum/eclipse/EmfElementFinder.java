package org.opaeum.eclipse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class EmfElementFinder{
	public static List<TypedElement> getTypedElementsInScope(Classifier c){
		List<TypedElement> result = new ArrayList<TypedElement>(getPropertiesInScope(c));
		if(c instanceof Behavior){
			Behavior b = (Behavior) c;
			result.addAll(b.getOwnedParameters());
			if(b.getContext() != null){
				result.addAll(getPropertiesInScope(b.getContext()));
			}
		}
		return result;
	}
	public static boolean isMeasure(Property p){
		for(Stereotype s:p.getAppliedStereotypes()){
			if(s.getDefinition().getEStructuralFeature("roleInCube") != null){
				EStructuralFeature f = s.getDefinition().getEStructuralFeature("roleInCube");
				EEnumLiteral l = (EEnumLiteral) p.getStereotypeApplication(s).eGet(f);
				if(l.getName().equals("MEASURE")){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isDimension(Property p){
		if(p.isMultivalued() || p.getQualifiers().size() > 0){
			return false;
		}
		if(p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
			return true;
		}
		for(Stereotype s:p.getAppliedStereotypes()){
			if(s.getDefinition().getEStructuralFeature("roleInCube") != null){
				EStructuralFeature f = s.getDefinition().getEStructuralFeature("roleInCube");
				EEnumLiteral l = (EEnumLiteral) p.getStereotypeApplication(s).eGet(f);
				if(l.getName().equals("DIMENSION")){
					return true;
				}
			}
		}
		return false;
	}
	public static Collection<Class> getCubes(Class dimension){
		TreeSet<Class> result = new TreeSet<Class>(new ElementComparator());
		addPotentialCubes(dimension, result);
		Iterator<Class> iterator = result.iterator();
		while(iterator.hasNext()){
			Class class1 = (Class) iterator.next();
			if(!isFact(class1)){
				iterator.remove();
			}
		}
		return result;
	}
	public static boolean isFact(Class class1){
		List<Property> propertiesInScope = getPropertiesInScope(class1);
		for(Property property:propertiesInScope){
			if(isMeasure(property)){
				return true;
			}
		}
		return false;
	}
	private static void addPotentialCubes(Class dimension,Set<Class> result){
		ECrossReferenceAdapter a = ECrossReferenceAdapter.getCrossReferenceAdapter(dimension);
		if(a == null){
		}else{
			for(Setting setting:a.getInverseReferences(dimension)){
				if(setting.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getTypedElement_Type())){
					TypedElement te = (TypedElement) setting.getEObject();
					if(te instanceof Property){
						Property p = (Property) te;
						if(isDimension(p)){
							Class potentialCube = (Class) getContainer(p);
							result.add(potentialCube);
							addPotentialCubes(potentialCube, result);
						}
					}
				}
			}
		}
	}
	public static Classifier getNearestClassifier(Element e){
		if(e instanceof Classifier){
			return (Classifier) e;
		}else if(e == null){
			return null;
		}else{
			return getNearestClassifier((Element) getContainer(e));
		}
	}
	public static org.eclipse.uml2.uml.Package getRootObject(Element e){
		if(e instanceof Model || e instanceof Profile || (e instanceof Package && e.eContainer() == null)){
			return (org.eclipse.uml2.uml.Package) e;
		}else if(e == null){
			return null;
		}else{
			return (Package) getRootObject((Element) getContainer(e));
		}
	}
	public static List<TypedElement> getTypedElementsInScope(Element behavioralElement){
		List<TypedElement> result = new ArrayList<TypedElement>();
		if(behavioralElement != null){
			Element a = behavioralElement;
			if(a instanceof Constraint){
				if(a.getOwner() instanceof Action){
					Action act = (Action) a.getOwner();
					result.addAll(act.getInputs());
					if(act.getLocalPostconditions().contains(a)){
						result.addAll(act.getOutputs());
					}
					return result;
				}else if(a.getOwner() instanceof Operation){
					Operation oper = (Operation) a.getOwner();
					for(Parameter parameter:oper.getOwnedParameters()){
						if(parameter.getDirection() == ParameterDirectionKind.IN_LITERAL
								|| parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL){
							result.add(parameter);
						}else if(oper.getPostconditions().contains(a)){
							result.add(parameter);
						}
					}
				}
			}else if(a instanceof Operation){
				Operation oper = (Operation) a;
				for(Parameter parameter:oper.getOwnedParameters()){
					if(parameter.getDirection() == ParameterDirectionKind.IN_LITERAL
							|| parameter.getDirection() == ParameterDirectionKind.INOUT_LITERAL){
						result.add(parameter);
					}
				}
			}
			do{
				if(a instanceof StructuredActivityNode){
					result.addAll(((StructuredActivityNode) a).getVariables());
				}
				if(a instanceof Transition){
					addTransitionParameters(result, (Transition) a);
				}
				if(a instanceof Behavior){
					result.addAll(((Behavior) a).getOwnedParameters());
					if(a instanceof Activity){
						Activity activity = (Activity) a;
						result.addAll(activity.getVariables());
					}
					if(a.getOwner() instanceof Transition){
						Transition owner = (Transition) a.getOwner();
						addTransitionParameters(result, owner);
						a = EmfStateMachineUtil.getStateMachine(a.getOwner());
					}else if(a.getOwner() instanceof State){
						a = EmfStateMachineUtil.getStateMachine(a.getOwner());
					}
				}
				a = (Element) EmfElementFinder.getContainer(a);
			}while(!(a == null || a instanceof Classifier || a instanceof Operation));
			if(a != null){
				result.addAll(getTypedElementsInScope(a));
			}
		}
		return result;
	}
	protected static void addTransitionParameters(List<TypedElement> result,Transition a){
		EList<Trigger> triggers = a.getTriggers();
		if(triggers.size() > 0){
			Event event = triggers.get(0).getEvent();
			if(event instanceof CallEvent){
				result.addAll(((CallEvent) event).getOperation().getOwnedParameters());
			}else if(event instanceof SignalEvent){
				for(Property p:((SignalEvent) event).getSignal().getAllAttributes()){
					// Create parameter to emulate parameter behavior in ocl, "self" would be invalid
					Parameter param = UMLFactory.eINSTANCE.createParameter();
					param.setType(p.getType());
					param.setName(p.getName());
					result.add(param);
				}
			}
		}
	}
	// TODO rename to getEffectiveProperties
	public static List<Property> getPropertiesInScope(Classifier c){
		List<Property> result = new ArrayList<Property>(c.getAttributes());
		for(Association a:c.getAssociations()){
			for(Property end:a.getMemberEnds()){
				if(end.getOtherEnd().getType().equals(c) && end.isNavigable() && end.getOwner() == a){
					result.add(end);
				}
			}
		}
		for(Generalization ir:c.getGeneralizations()){
			for(Property p:getPropertiesInScope(ir.getGeneral())){
				result.add(p);
			}
		}
		if(c instanceof BehavioredClassifier){
			BehavioredClassifier cls = (BehavioredClassifier) c;
			for(InterfaceRealization ir:cls.getInterfaceRealizations()){
				for(Property p:getPropertiesInScope(ir.getContract())){
					result.add(p);
				}
			}
		}
		return result;
	}
	public static EObject getContainer(EObject s){
		if(s == null){
			return null;
		}else if(s instanceof IEmulatedElement){
			{
				return getContainer(((IEmulatedElement) s).getOriginalElement());
			}
		}else if(s.eContainer() instanceof DynamicEObjectImpl){
			while(!(s.eContainer() == null)){
				// find top level stereotype
				s = s.eContainer();
			}
			for(EObject eObject:s.eCrossReferences()){
				if(eObject instanceof Element){
					if(((Element) eObject).getStereotypeApplications().contains(s)){
						return eObject;
					}
				}
			}
			return s.eContainer();
		}else if(s instanceof Event){
			org.eclipse.uml2.uml.Event event = (org.eclipse.uml2.uml.Event) s;
			ECrossReferenceAdapter cra = ECrossReferenceAdapter.getCrossReferenceAdapter(s);
			// Contained by an annotation inside another element?
			if(event.eContainer() instanceof EAnnotation){
				// Skip event AND annotation straight to the containing element
				EAnnotation ea = (EAnnotation) event.eContainer();
				return ea.getEModelElement();
			}else{
				// Old strategy - could be problematic if the event is referenced from multiple triggers
				EAnnotation ann = event.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
				if(ann != null){
					for(EObject eObject:ann.getReferences()){
						if(eObject instanceof Trigger){
							return eObject;
						}
					}
				}
			}
			for(Setting setting:cra.getNonNavigableInverseReferences(event)){
				if(setting.getEObject() instanceof Trigger){
					return setting.getEObject();
				}
			}
			return event.getOwner();
			// throw new IllegalStateException("No context could be found for Event:" + event.getQualifiedName());
		}else if(s.eContainer() instanceof EAnnotation){
			return ((EAnnotation) s.eContainer()).getEModelElement();
		}else if(s instanceof Property && s.eContainer() instanceof Association){
			Property p = (Property) s;
			if(p.getOtherEnd() != null && p.isNavigable()){
				return p.getOtherEnd().getType();
			}else{
				return s.eContainer();
			}
		}else if(s instanceof InterfaceRealization){
			return ((InterfaceRealization) s).getImplementingClassifier();
		}else if(s instanceof Generalization){
			return ((Generalization) s).getSpecific();
		}
		return s.eContainer();
	}
	public static void main(String[] args) throws Exception{
		System.out.println(toId("862713@_6M9kh9EyEd-XueQF87eovw"));
		System.out.println(toId("862713@_6KxMI9EyEd-XueQF87eovw"));
		int c = 100000;
		Set<Integer> hashcodes = new HashSet<Integer>(c);
		Set<String> strings = new HashSet<String>(c);
		int duplicates = 0;
		for(int i = 0;i < c;i++){
			String string = UUID.randomUUID().toString().substring(0, 30);
			if(!strings.contains(string)){
				strings.add(string);
				int hashCode = toId(string);
				if(hashcodes.contains(hashCode)){
					duplicates++;
				}else{
					hashcodes.add(hashCode);
				}
			}else{
				i--;
			}
		}
		System.out.println(duplicates);
		// for(int i = 0 ; i < 10; i ++){
		// System.out.println(UUID.randomUUID().toString().length());
		// }
	}
	protected static int toId(String string){
		char[] charArray = string.toCharArray();
		int result = 0;
		for(int i = 0;i < charArray.length;i++){
			result = (result << 1) + charArray[i];
		}
		return (int) result;
		// return string.hashCode();
	}
	public static Collection<Element> getCorrectOwnedElements(Element root){
		return getCorrectOwnedElementsAndRetryIfFailed(root, 0);
	}
	protected static Collection<Element> getCorrectOwnedElementsAndRetryIfFailed(Element root,int count){
		if(root instanceof TemplateSignature){
			// TODO add other elements we do not wish to traverse
			return new TreeSet<Element>(new ElementComparator());
		}else{
			Collection<Element> elements = new TreeSet<Element>(new ElementComparator());
			elements.addAll(root.getOwnedElements());
			// Unimplemented containment features, oy
			if(root instanceof Activity){
				Activity node = (Activity) root;
				getOwnedNodesForEclipseUml4(elements, node);
				elements.addAll(node.getEdges());
			}else if(root instanceof StructuredActivityNode){
				StructuredActivityNode node = (StructuredActivityNode) root;
				elements.addAll(node.getNodes());
				elements.addAll(node.getEdges());
			}else if(root instanceof Transition){
				Transition t = (Transition) root;
				elements.addAll(t.getTriggers());
			}else if(root instanceof AcceptEventAction){
				elements.addAll(((AcceptEventAction) root).getTriggers());
			}else if(root instanceof ValuePin){
				ValuePin vp = (ValuePin) root;
				if(vp.getValue() != null){
					elements.add(vp.getValue());
				}
			}else if(root instanceof ActivityEdge){
				ActivityEdge e = (ActivityEdge) root;
				if(e.getGuard() != null){
					elements.add(e.getGuard());
				}
				if(e.getWeight() != null){
					elements.add(e.getWeight());
				}
			}else if(root instanceof TimeExpression){
				elements.add(((TimeExpression) root).getExpr());
			}
			try{
				for(EObject stereotype:root.getStereotypeApplications()){
					for(EReference property:stereotype.eClass().getEAllContainments()){
						Object v = stereotype.eGet(property);
						if(v instanceof Element){
							elements.add((Element) v);
						}else if(v instanceof EList){
							EList<?> c = (EList<?>) v;
							for(Object element:c){
								if(element instanceof Element){
									elements.add((Element) element);
								}
							}
						}
					}
				}
			}catch(RuntimeException e){
				if(e instanceof ArrayIndexOutOfBoundsException || e instanceof NullPointerException){
					// HACK weird bug in:
					// org.eclipse.emf.ecore.util.ECrossReferenceAdapter.getInverseReferences(ECrossReferenceAdapter.java:332)
					// and
					// org.eclipse.emf.ecore.util.ECrossReferenceAdapter.getInverseReferences(ECrossReferenceAdapter.java:323)
					if(count < 5){
						try{
							Thread.sleep(2000);
						}catch(InterruptedException e1){
						}
						return getCorrectOwnedElementsAndRetryIfFailed(root, ++count);
					}
				}else{
					throw e;
				}
			}
			return elements;
		}
	}
	private static void getOwnedNodesForEclipseUml4(Collection<Element> elements,Activity node){
		Method getOwnedNodes;
		try{
			getOwnedNodes = node.getClass().getMethod("getOwnedNodes");
			if(getOwnedNodes != null){
				elements.addAll((Collection<? extends Element>) getOwnedNodes.invoke(node));
			}
		}catch(SecurityException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NoSuchMethodException e){
		}catch(IllegalArgumentException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IllegalAccessException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(InvocationTargetException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Classifier getNearestClassContext(Element element){
		Classifier clss = EmfElementFinder.getNearestClassifier(element);
		if(clss instanceof StateMachine){
			return clss;
		}else if(clss instanceof Behavior && ((Behavior) clss).getContext() != null
				&& !StereotypesHelper.hasKeyword(clss, StereotypeNames.BUSINES_PROCESS)){
			clss = ((Behavior) clss).getContext();
		}
		return clss;
	}
	public static Element getOwnedElement(Element toEnum,String id){
		for(Element element:getCorrectOwnedElements(toEnum)){
			if(EmfWorkspace.getId(element).equals(id)){
				return element;
			}
		}
		return null;
	}
	public static Set<Element> getDependentElements(Element e){
		Set<Element> result = new TreeSet<Element>(new ElementComparator());
		for(Setting s:ECrossReferenceAdapter.getCrossReferenceAdapter(e).getInverseReferences(e)){
			if(s.getEObject() instanceof Element){
				result.add((Element) s.getEObject());
			}
		}
		for(Setting s:ECrossReferenceAdapter.getCrossReferenceAdapter(e).getNonNavigableInverseReferences(e)){
			if(s.getEObject() instanceof Element){
				result.add((Element) s.getEObject());
			}
		}
		return result;
	}
	public static Namespace getNearestNamespace(Element ns){
		Element parent = (Element) getContainer(ns);
		while(!(parent instanceof Namespace || parent == null)){
			parent = (Element) getContainer(parent);
		}
		return (Namespace) parent;
	}
}
