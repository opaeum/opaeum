package org.opaeum.metamodel.workspace;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.uml.MessageType;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.ResponsibilityDefinitionImpl;
import org.opaeum.eclipse.emulated.AbstractEmulatedProperty;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolder;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForActivity;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForAssociation;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForBehavior;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForBehavioredClassifier;
import org.opaeum.eclipse.emulated.EmulatedPropertyHolderForStateMachine;
import org.opaeum.eclipse.emulated.ExpansionRegionMessageType;
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.eclipse.emulated.IEmulatedPropertyHolder;
import org.opaeum.eclipse.emulated.OpaqueActionMessageType;
import org.opaeum.eclipse.emulated.OperationMessageType;
import org.opaeum.eclipse.emulated.StructuredActivityNodeMessageType;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.emf.workspace.UriToFileConverter;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.ocl.uml.ResponsibilityDefinition;

@SuppressWarnings("restriction")
public class ArtificialElementFactory implements IPropertyEmulation{
	private Map<NamedElement,Classifier> emulatedClassifiers = new HashMap<NamedElement,Classifier>();
	private Map<Classifier,IEmulatedPropertyHolder> classifierAttributes = new HashMap<Classifier,IEmulatedPropertyHolder>();
	private UriToFileConverter uriToFileConverter;
	private Map<Model,Map<String,String>> implementationCode = new HashMap<Model,Map<String,String>>();
	private TreeSet<IEmulatedElement> emulatedElements = new TreeSet<IEmulatedElement>(new DefaultOpaeumComparator());
	private OpaeumOcl opaeumOcl;
	public ArtificialElementFactory(OpaeumOcl opaeumOcl,UriToFileConverter uriToFileConverter){
		this.uriToFileConverter = uriToFileConverter;
		this.opaeumOcl=opaeumOcl;
	}
	public Classifier getMessageStructure(CallAction action){
		if(action instanceof CallBehaviorAction){
			return ((CallBehaviorAction) action).getBehavior();
		}else if(action instanceof CallOperationAction){
			return getMessageStructure(((CallOperationAction) action).getOperation());
		}else{
			return null;
		}
	}
	public Classifier getMessageStructure(AcceptCallAction action2){
		return getMessageStructure(EmfActionUtil.getOperation(action2));
	}
	public Property getEndToComposite(Classifier entity){
		Property etc = EmfPropertyUtil.getEndToComposite(entity);
		if(etc == null && !(entity instanceof IEmulatedElement)){
			return getArtificialEndToComposite(entity);
		}
		return etc;
	}
	public Property getArtificialEndToComposite(Classifier entity){
		if(!(entity instanceof MessageType)){
			IEmulatedPropertyHolder eph = getEmulatedPropertyHolder(entity);
			for(AbstractEmulatedProperty p:eph.getEmulatedAttributes()){
				if(p.getOtherEnd() != null && p.getOtherEnd().isComposite()){
					return p;
				}
			}
		}
		return null;
	}
	public Classifier getMessageStructure(Namespace container){
		if(container instanceof Behavior){
			return (Behavior) container;
		}else if(container != null){
			Classifier classifier = emulatedClassifiers.get(container);
			if(classifier == null){
				if(container instanceof Operation){
					classifier = new OperationMessageType((Operation) container, this);
					if(getLibrary().getRequestObject() != null){
						((OperationMessageType) classifier).createInterfaceRealization(getLibrary().getResponsibilityObject().getName(), getLibrary().getResponsibilityObject());
					}
				}else if(container instanceof ExpansionRegion){
					classifier = new ExpansionRegionMessageType((ExpansionRegion) container, this);
				}else if(container instanceof StructuredActivityNode){
					classifier = new StructuredActivityNodeMessageType((StructuredActivityNode) container, this);
				}
				emulatedClassifiers.put(container, classifier);
				emulatedElements.add((IEmulatedElement) classifier);
			}
			return classifier;
		}else{
			return null;
		}
	}
	public Set<Property> getDirectlyImplementedAttributes(Classifier c){
		Set<Property> propertiesInScope = EmfPropertyUtil.getDirectlyImplementedAttributes(c);
		addEmulatedProperties(c, propertiesInScope);
		if(c instanceof BehavioredClassifier){
			for(Interface intf:((BehavioredClassifier) c).getImplementedInterfaces()){
				// TODO filter out emulated properties that may already have been implemented by the superclass
				addAllEmulatedProperties(intf, propertiesInScope);
			}
		}
		Iterator<Property> iterator = propertiesInScope.iterator();
		while(iterator.hasNext()){
			Property property = (Property) iterator.next();
			if(!property.isNavigable()){
				iterator.remove();
			}
		}
		return propertiesInScope;
	}
	public List<Property> getEffectiveAttributes(Classifier bc){
		List<Property> props = EmfPropertyUtil.getEffectiveProperties(bc);
		List<Property> result = new ArrayList<Property>(props);
		addAllEmulatedProperties(bc, result);
		Iterator<Property> iterator = result.iterator();
		while(iterator.hasNext()){
			Property property = (Property) iterator.next();
			if(!property.isNavigable()){
				iterator.remove();
			}
		}
		return result;
	}
	protected void addAllEmulatedProperties(Classifier bc,Collection<Property> propertiesInScope){
		addEmulatedProperties(bc, propertiesInScope);
		for(Classifier classifier:bc.getGenerals()){
			addAllEmulatedProperties(classifier, propertiesInScope);
		}
		if(bc instanceof BehavioredClassifier){
			for(Interface i:((BehavioredClassifier) bc).getImplementedInterfaces()){
				addAllEmulatedProperties(i, propertiesInScope);
			}
		}
	}
	private void addEmulatedProperties(Classifier bc,Collection<Property> propertiesInScope){
		IEmulatedPropertyHolder holder = getEmulatedPropertyHolder(bc);
		if(holder != null){
			for(AbstractEmulatedProperty aep:holder.getEmulatedAttributes()){
				if(aep.shouldEmulate()){
					propertiesInScope.add(aep);
				}
			}
		}
		for(Association a:bc.getAssociations()){
			if(EmfAssociationUtil.isClass(a)){
				EmulatedPropertyHolderForAssociation epha = (EmulatedPropertyHolderForAssociation) getEmulatedPropertyHolder(a);
				for(Property m:a.getMemberEnds()){
					if(m.getOtherEnd().getType() == bc){
						propertiesInScope.add(epha.getEndToAssociation(m));
					}
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public IEmulatedPropertyHolder getEmulatedPropertyHolder(Classifier bc){
		IEmulatedPropertyHolder holder = classifierAttributes.get(bc);
		if(holder == null){
			if(bc instanceof Activity){
				holder = new EmulatedPropertyHolderForActivity((Activity) bc, this);
			}else if(bc instanceof StateMachine){
				holder = new EmulatedPropertyHolderForStateMachine((StateMachine) bc, this);
			}else if(bc instanceof Association){
				holder = new EmulatedPropertyHolderForAssociation((Association) bc, this);
			}else if(bc instanceof Behavior){
				holder = new EmulatedPropertyHolderForBehavior((Behavior) bc, this);
			}else if(bc instanceof BehavioredClassifier){
				holder = new EmulatedPropertyHolderForBehavioredClassifier((BehavioredClassifier) bc, this);
			}else{
				holder = new EmulatedPropertyHolder(bc, this);
			}
			classifierAttributes.put(bc, holder);
		}
		return holder;
	}
	public Property findEffectiveAttribute(Classifier fromClass,String name){
		for(Property p:getDirectlyImplementedAttributes(fromClass)){
			if(p.getName().equals(name)){
		return p;
			}
		}
		for(Classifier classifier:fromClass.getGenerals()){
			Property found = findEffectiveAttribute(classifier, name);
			if(found!=null){
				return found;
			}
		}
		return null;
	}
	public Classifier getEventGeneratingClassifier(NamedElement event){
		if((event instanceof Constraint && StereotypesHelper.hasStereotype(event, StereotypeNames.ESCALATION))
				|| (event instanceof Event && EmfEventUtil.isDeadline((Event) event))){
			EObject container = EmfElementFinder.getContainer(event);
			if(container instanceof Operation){
				return getMessageStructure((Operation) container);
			}else if(container instanceof CallBehaviorAction){
				return getMessageStructure((CallBehaviorAction) container);
			}else if(container instanceof OpaqueAction){
				return getMessageStructure((OpaqueAction) container);
			}else if(container instanceof Behavior){
				return (Behavior) container;
			}
		}else{
			return getMessageStructure(EmfEventUtil.getBehavioralNamespaceContext((Event) event));
		}
		return null;
	}
	@Override
	public Classifier getMessageStructure(OpaqueAction n){
		OpaqueActionMessageType classifier = (OpaqueActionMessageType) this.emulatedClassifiers.get(n);
		if(classifier == null){
			this.emulatedClassifiers.put(n, classifier = new OpaqueActionMessageType(n, this));
			classifier.createInterfaceRealization(getLibrary(). getTaskObject().getName(), getLibrary().getTaskObject());
		}
		return classifier;
	}
	public ResponsibilityDefinition getResponsibilityDefinition(NamedElement node,String...deadlineOper){
		return new ResponsibilityDefinitionImpl(this, node, StereotypesHelper.getStereotype(node, deadlineOper));
	}
	public String getImplementationCodeFor(Model m,String artefactName){
		Map<String,String> map = implementationCode.get(m);
		if(map == null){
			try{
				map = new HashMap<String,String>();
				implementationCode.put(m, map);
				URI uri = m.eResource().getURI().trimFileExtension().appendFileExtension("zip");
				uri = m.eResource().getResourceSet().getURIConverter().normalize(uri);
				File file = uriToFileConverter.resolveUri(uri);
				if(file != null){
					ZipFile zipFile = new ZipFile(file);
					java.util.Enumeration<? extends ZipEntry> entries = zipFile.entries();
					while(entries.hasMoreElements()){
						ZipEntry zipEntry = entries.nextElement();
						if(!zipEntry.isDirectory()){
							BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry)));
							StringBuilder sb = new StringBuilder();
							String line;
							while((line = reader.readLine()) != null){
								sb.append(line);
								sb.append("\n");
							}
							map.put(zipEntry.getName(), sb.toString());
						}
					}
				}
			}catch(IOException e){
				System.out.println(e.toString());
			}
		}
		return map.get(artefactName);
	}
	public void reset(){
		emulatedClassifiers.clear();
		classifierAttributes.clear();
	}
	public SortedSet<IEmulatedElement> getAllEmulatedElements(){
		return emulatedElements;
	}
	@Override
	public DataType getDateTimeType(){
		return getLibrary().getDateTimeType();
	}
	@Override
	public DataType getDurationType(){
		return getLibrary().getDurationType();
	}
	@Override
	//TODO move
	public OpaqueExpressionContext getOclExpressionContext(OpaqueExpression valueSpec){
		return opaeumOcl.getOclExpressionContext(valueSpec);
	}
	@Override
	public DataType getCumulativeDurationType(){
		return getLibrary().getCumulativeDurationType();
	}
	@Override
	public Classifier getQuantityBasedCost(){
		return getLibrary().getQuantityBasedCost();
	}
	@Override
	public Classifier getDurationBasedCost(){
		return getLibrary().getDurationBasedCost();
	}
	private OpaeumLib getLibrary(){
		return opaeumOcl.getLibrary();
	}
}
