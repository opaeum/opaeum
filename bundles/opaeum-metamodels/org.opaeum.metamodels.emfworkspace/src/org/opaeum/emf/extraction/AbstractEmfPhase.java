package org.opaeum.emf.extraction;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;

public abstract class AbstractEmfPhase{
	public static Map<Class<?>, Integer> counts=new HashMap<Class<?>,Integer>();
	{
		Integer i = counts.get(getClass());
		i=i==null?0:i+1;
		counts.put(getClass(),i);
	}
	public void finalize() throws Throwable {
		Integer i = counts.get(getClass().getName());
		i=i==null?0:i-1;
		counts.put(getClass(),i);
	};

	public AbstractEmfPhase(){
		super();
	}
	protected Set<Element> calculateEffectiveChanges(Collection<Element> elements){
		Set<Element> result = new HashSet<Element>();
		for(Element object:elements){
			Element ne = (Element) object;
			if(ne instanceof Trigger){
				// TODO establish a better design to do this
				// Influences validity of senSignalActions
				Trigger t = (Trigger) ne;
				if(t.getEvent() instanceof SignalEvent){
					SignalEvent se = (SignalEvent) t.getEvent();
					if(se.getSignal() != null){
						addCrossReferences(result, se.getSignal());
					}
				}
			}else if(ne instanceof Reception){
				// Influences validity of senSignalActions
				Reception r = (Reception) ne;
				if(r.getSignal() != null){
					addCrossReferences(result, r.getSignal());
				}
			}
			if(ne instanceof Property && ((Property) ne).getAssociation() != null){
				Association ass = (Association) ((Property) ne).getAssociation();
				addAssociation(result, ass);
			}else if(ne instanceof Association){
				addAssociation(result, (Association) ne);
			}else if(ne instanceof Generalization){
				addProcessibleElementsRecursively(result, ((Generalization) ne).getSpecific());
			}else if(ne instanceof InterfaceRealization){
				addProcessibleElementsRecursively(result, ((InterfaceRealization) ne).getImplementingClassifier());
			}
			addProcessibleElementsRecursively(result, ne);
		}
		result.remove(null);// Just in case the null element was added
		return filterChildrenOut(result);
	}
	protected void addCrossReferences(Set<Element> result,EObject o){
		Collection<Setting> non = getModelWorkspace().getCrossReferenceAdapter().getNonNavigableInverseReferences(o, true);
		for(Setting setting:non){
			Element processibleElement = getProcessibleElement(setting.getEObject());
			if(processibleElement != null){
				result.add(processibleElement);
			}
		}
	}
	protected abstract EmfWorkspace getModelWorkspace();
	protected Element getProcessibleElement(EObject o){
		while(!(canBeProcessedIndividually(o) || o == null)){
			o = EmfElementFinder.getContainer(o);
		}
		return (Element) o;
	}
	private void addAssociation(Set<Element> result,Association ass){
		if(EmfAssociationUtil.isClass(ass)){
			addProcessibleElementsRecursively(result, ass);
		}
		for(Property p:ass.getAttributes()){
			addProcessibleElementsRecursively(result, p);
		}
	}
	private void addProcessibleElementsRecursively(Set<Element> result,Element ne){
		if(ne != null){
			Element processibleElement = getProcessibleElement(ne);
			result.add(processibleElement);
		}
	}
	private Element getProcessibleElement(Element ne){
		while(!(ne instanceof Classifier || ne instanceof Package || ne instanceof Event || ne == null || ne instanceof Operation || (ne instanceof Action && EmfActionUtil
				.isEmbeddedTask((ActivityNode) ne)))){
			ne = (Element) EmfElementFinder.getContainer(ne);
		}
		return ne;
	}
	private Set<Element> filterChildrenOut(Collection<Element> elements){
		Set<Element> elementsToProcess = new HashSet<Element>();
		outer:for(Element element:elements){
			for(EObject element2:elements){
				if(contains(element2, element)){
					// skip - parent will be processed
					continue outer;
				}
			}
			elementsToProcess.add((Element) element);
		}
		return elementsToProcess;
	}
	private boolean contains(EObject parent,EObject child){
		if(child == parent){
			return false;
		}else{
			final EObject childsContainer = EmfElementFinder.getContainer(child);
			if(childsContainer == null){
				return false;
			}else if(parent.equals(childsContainer)){
				return true;
			}else{
				return contains(parent, childsContainer);
			}
		}
	}
	public static boolean canBeProcessedIndividually(EObject e){
		return e instanceof Action || e instanceof ControlNode || e instanceof State || e instanceof Pseudostate || e instanceof StructuredActivityNode
				|| e instanceof Region || e instanceof Operation || (e instanceof Property && ((Property) e).getAssociation() == null) || e instanceof Classifier
				|| e instanceof Transition || e instanceof ActivityEdge || e instanceof Package || e instanceof Association || e instanceof Generalization
				|| e instanceof InterfaceRealization || e instanceof Reception || e instanceof Constraint;
	}
}