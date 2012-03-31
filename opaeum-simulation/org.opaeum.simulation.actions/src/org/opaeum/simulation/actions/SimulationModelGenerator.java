package org.opaeum.simulation.actions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IEnumLiteral;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.sashwindows.di.DiFactory;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedPrimitiveType;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.ContainedActualInstance;
import org.opaeum.metamodels.simulation.simulation.InstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.NormalDistribution;
import org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.metamodels.simulation.simulation.SimulationStrategy;
import org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue;
import org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;
import org.opaeum.metamodels.simulation.simulation.WeightedStringValue;

public class SimulationModelGenerator{
	private EmfWorkspace emfWorkspace = null;
	private INakedModelWorkspace nws = null;
	private int numberOfObjectsPerLevel = 3;
	private Map<INakedClassifier,List<ActualInstance>> actualInstances = new HashMap<INakedClassifier,List<ActualInstance>>();
	private Map<INakedClassifier,InstanceSimulation> allInstances = new HashMap<INakedClassifier,InstanceSimulation>();
	private SimulationModel simModel;
	private Set<INakedClassifier> libClasses;
	public SimulationModelGenerator(EmfWorkspace emfWorkspace,INakedModelWorkspace nws){
		super();
		this.emfWorkspace = emfWorkspace;
		this.nws = nws;
	}
	public SimulationModel run(){
		libClasses = new HashSet<INakedClassifier>();
		libClasses.add(nws.getOpaeumLibrary().getProcessObject());
		libClasses.add(nws.getOpaeumLibrary().getProcessRequest());
		libClasses.add(nws.getOpaeumLibrary().getProcessResponsibilityObject());
		libClasses.add(nws.getOpaeumLibrary().getTaskObject());
		libClasses.add(nws.getOpaeumLibrary().getTaskRequest());
		libClasses.add(nws.getOpaeumLibrary().getTaskResponsibilityObject());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		this.simModel = SimulationFactory.eINSTANCE.createSimulationModel();
		simModel.setName(nws.getName() + "_" + sdf.format(new Date()));
		recreateDiagrams(simModel.getName());
		recreateWindowsManager(simModel.getName());
		Resource rs = getResource(simModel.getName(), "uml");
		rs.getContents().add(simModel);
		if(nws.getOpaeumLibrary().getBusinessNetwork() != null){
			simModel.getPackagedElements().add(buildActualInstance(nws.getOpaeumLibrary().getBusinessNetwork(), 1, 2));
		}
		if(nws.getApplicationRoot() instanceof ICompositionParticipant){
			List<? extends INakedProperty> attrs = nws.getApplicationRoot().getEffectiveAttributes();
			for(INakedProperty p:attrs){
				if(p.isComposite() && !libClasses.contains(p.getNakedBaseType())){
					for(INakedClassifier c:getConcreteImplementationsOf(p)){
						simModel.getPackagedElements().add(buildActualInstance(c, 1, 3));
					}
				}
			}
			buildAllInstanceSimulations();
			setReferenceStrategies();
		}
		try{
			rs.save(null);
		}catch(IOException e){
			e.printStackTrace();
		}
		return simModel;
	}
	private void setReferenceStrategies(){
		Collection<List<ActualInstance>> values = actualInstances.values();
		for(List<ActualInstance> list:values){
			for(ActualInstance ais:list){
				setReferenceStrategies(ais);
			}
		}
		for(InstanceSimulation ais:allInstances.values()){
			setReferenceStrategies(ais);
		}
	}
	private void buildAllInstanceSimulations(){
		for(INakedElement e:nws.getAllElements()){
			boolean isAssociation = e instanceof INakedAssociation && !((INakedAssociation) e).isAssociationClass();
			if((e instanceof ICompositionParticipant || e instanceof INakedStructuredDataType)
					&& !(isAssociation || ((IClassifier) e).getIsAbstract()) && !(e instanceof INakedBehavior)){
				if(!actualInstances.containsKey(e) && emfWorkspace.getElement(e.getId()) != null && !libClasses.contains(e)){
					buildAllInstanceSimulation((INakedClassifier) e);
				}
			}
		}
	}
	private void setReferenceStrategies(InstanceSpecification is){
		EList<Slot> slots = is.getSlots();
		for(Slot slot:slots){
			if(slot.getValues().isEmpty()){
				INakedProperty p = (INakedProperty) nws.getModelElement(EmfWorkspace.getId(slot.getDefiningFeature()));
				if(p.getNakedBaseType() instanceof INakedComplexStructure){
					SimulatingSlot ss = (SimulatingSlot) slot;
					if(actualInstances.containsKey(p.getNakedBaseType())){
						List<ActualInstance> list = actualInstances.get(p.getNakedBaseType());
						for(ActualInstance ais:list){
							WeightedInstanceValue ris = SimulationFactory.eINSTANCE.createWeightedInstanceValue();
							ss.getValues().add(ris);
							ris.setInstance(ais);
							ris.setWeight(1d / list.size());
							ss.setSimulationStrategy(SimulationStrategy.WEIGHTED_DISTRIBUTION);
						}
					}else{
						Collection<INakedClassifier> subclasses = getConcreteImplementationsOf(p);
						NormalDistribution nd = SimulationFactory.eINSTANCE.createNormalDistribution();
						ss.setSizeDistribution(nd);
						nd.setName("NormalDistribution");
						nd.setMean(4d);
						nd.setStandardDeviation(1.5d);
						ss.setSimulationStrategy(SimulationStrategy.INSTANCE_SIMULATION);
						for(INakedClassifier c:subclasses){
							WeightedInstanceValue iv = SimulationFactory.eINSTANCE.createWeightedInstanceValue();
							InstanceSpecification instance = allInstances.get(c);
							if(instance == null){
								List<ActualInstance> list = actualInstances.get(c);
								if(list != null){
									instance = list.get((int) (Math.floor(Math.random() * list.size())));
								}
							}
							if(instance != null){
								ss.getValues().add(iv);
								iv.setInstance(instance);
								iv.setWeight(1d / subclasses.size());
							}
						}
					}
				}
			}
		}
	}
	private ActualInstance buildActualInstance(INakedClassifier nc,int count,int numberOfLevelsOfActualInstances){
		Classifier element = (Classifier) emfWorkspace.getElement(nc.getId());
		ActualInstance ais = SimulationFactory.eINSTANCE.createActualInstance();
		List<ActualInstance> list = getInstanceSimulationsFOr(nc);
		list.add(ais);
		ais.setName(element.getName() + "ActualInstance" + count);
		ais.getClassifiers().add(element);
		ais.getSlots().clear();// May have been created automatically
		for(INakedProperty p:nc.getEffectiveAttributes()){
			if(!p.isDerived() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite())){
				addSlotForProperty(count, numberOfLevelsOfActualInstances, ais, p);
			}
		}
		return ais;
	}
	private void addSlotForProperty(int count,int numberOfLevelsOfActualInstances,ActualInstance ais,INakedProperty p){
		StructuralFeature feature = (StructuralFeature) emfWorkspace.getElement(p.getId());
		if(feature != null && !libClasses.contains(p.getNakedBaseType())){
			SimulatingSlot slot = SimulationFactory.eINSTANCE.createSimulatingSlot();
			ais.getSlots().add(slot);
			slot.setDefiningFeature(feature);
			slot.setSimulationStrategy(SimulationStrategy.GIVEN_VALUE);
			if(p.getNakedBaseType() instanceof INakedPrimitiveType){
				addPrimitiveValue(count, p, slot);
			}else if(p.getNakedBaseType() instanceof INakedEnumeration){
				addEnumerationValue(p, slot);
			}else if(p.getNakedBaseType() instanceof INakedComplexStructure){
				if(p.isComposite() && numberOfLevelsOfActualInstances >= 0){
					if(p.getQualifiers().size() == 1 && p.getQualifiers().get(0).getNakedBaseType() instanceof INakedEnumeration){
						addQualifiedContainedInstance(numberOfLevelsOfActualInstances, p, slot, p.getQualifiers().get(0));
					}else{
						addContainedAtualInstance(numberOfLevelsOfActualInstances, p, slot);
					}
				}
			}
		}
	}
	private void addQualifiedContainedInstance(int numberOfLevelsOfActualInstances,INakedProperty p,SimulatingSlot owningSlot,
			INakedProperty qualifer){
		INakedEnumeration en = (INakedEnumeration) qualifer.getNakedBaseType();
		int i = 0;
		for(INakedEnumerationLiteral l:en.getOwnedLiterals()){
			Collection<INakedClassifier> subClasses = getConcreteImplementationsOf(p);
			if(subClasses.size() == 1){// TODO handle polymorphism
				for(IClassifier c:subClasses){
					if(emfWorkspace.getElement(((INakedElement) c).getId()) != null){
						ContainedActualInstance civs = SimulationFactory.eINSTANCE.createContainedActualInstance();
						owningSlot.getValues().add(civs);
						ActualInstance actualInstance = this.buildActualInstance((INakedClassifier) c, i + 1, numberOfLevelsOfActualInstances - 1);
						for(Slot childSlot:actualInstance.getSlots()){
							if(childSlot.getDefiningFeature().getName().equals(qualifer.getName())){
								InstanceValue enumInstanceValue = UMLFactory.eINSTANCE.createInstanceValue();
								EList<ValueSpecification> values = childSlot.getValues();
								values.clear();
								values.add(enumInstanceValue);
								enumInstanceValue.setInstance((EnumerationLiteral) emfWorkspace.getElement(l.getId()));
							}
						}
						civs.setContainedInstance(actualInstance);
					}
				}
			}
			i++;
		}
	}
	private void addContainedAtualInstance(int numberOfLevelsOfActualInstances,INakedProperty p,SimulatingSlot slot){
		Collection<INakedClassifier> subClasses = getConcreteImplementationsOf(p);
		if(subClasses.size() > 0){
			int i = p.getMultiplicity().isSingleObject() && p.getQualifiers().isEmpty()?1:this.numberOfObjectsPerLevel;
			outer:while(i >= 1){
				for(IClassifier c:subClasses){
					if(i == 1){
						break outer;
					}else if(emfWorkspace.getElement(((INakedElement) c).getId()) != null){
						ContainedActualInstance civs = SimulationFactory.eINSTANCE.createContainedActualInstance();
						slot.getValues().add(civs);
						civs.setContainedInstance(this.buildActualInstance((INakedClassifier) c, i + 1, numberOfLevelsOfActualInstances - 1));
					}
					i--;
				}
			}
		}
	}
	private void addEnumerationValue(INakedProperty p,SimulatingSlot slot){
		INakedEnumeration ne = (INakedEnumeration) p.getNakedBaseType();
		if(ne.getLiterals().size() > 0){
			INakedEnumerationLiteral firstLiteral = (INakedEnumerationLiteral) ne.getLiterals().get(0);
			InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
			iv.setInstance((EnumerationLiteral) emfWorkspace.getElement(firstLiteral.getId()));
			slot.getValues().add(iv);
		}
	}
	private void addPrimitiveValue(int count,INakedProperty p,SimulatingSlot slot){
		INakedPrimitiveType pt = (INakedPrimitiveType) p.getNakedBaseType();
		if(pt.conformsTo(nws.getOpaeumLibrary().getBooleanType())){
			LiteralBoolean b = UMLFactory.eINSTANCE.createLiteralBoolean();
			slot.getValues().add(b);
			b.setValue(count % 2 == 0);
		}else if(pt.conformsTo(nws.getOpaeumLibrary().getIntegerType())){
			LiteralInteger i = UMLFactory.eINSTANCE.createLiteralInteger();
			i.setValue(count);
			slot.getValues().add(i);
		}else if(pt.conformsTo(nws.getOpaeumLibrary().getRealType())){
			LiteralReal r = UMLFactory.eINSTANCE.createLiteralReal();
			slot.getValues().add(r);
			r.setValue(1d / count);
		}else if(pt.conformsTo(nws.getOpaeumLibrary().getStringType())){
			LiteralString s = UMLFactory.eINSTANCE.createLiteralString();
			String name = p.getName();
			slot.getValues().add(s);
			//TODO do this with strategies
			if(pt.hasStereotype("Email")){
				name="john.doe@gmail.co";
			}else if(pt.hasStereotype("Digits")){
				name="0823436840";
			}
			s.setValue(name + count);

		}
	}
	private List<ActualInstance> getInstanceSimulationsFOr(INakedClassifier nc){
		List<ActualInstance> list = actualInstances.get(nc);
		if(list == null){
			list = new ArrayList<ActualInstance>();
			actualInstances.put(nc, list);
		}
		return list;
	}
	private InstanceSimulation buildAllInstanceSimulation(INakedClassifier nc){
		// TODO specify algorithms for inheritance
		Classifier element = (Classifier) emfWorkspace.getElement(nc.getId());
		InstanceSimulation ais = SimulationFactory.eINSTANCE.createInstanceSimulation();
		simModel.getPackagedElements().add(ais);
		allInstances.put(nc, ais);
		ais.setName(element.getName() + "InstanceSimulation");
		ais.getClassifiers().add(element);
		ais.getSlots().clear();// May have been created automatically
		for(INakedProperty p:nc.getEffectiveAttributes()){
			StructuralFeature feature = (StructuralFeature) emfWorkspace.getElement(p.getId());
			if(feature != null && !libClasses.contains(p.getNakedBaseType()) && !p.isReadOnly() && !p.isDerived()){
				SimulatingSlot slot = SimulationFactory.eINSTANCE.createSimulatingSlot();
				ais.getSlots().add(slot);
				slot.setDefiningFeature(feature);
				if(p.getNakedBaseType() instanceof INakedPrimitiveType){
					INakedPrimitiveType pt = (INakedPrimitiveType) p.getNakedBaseType();
					if(pt.conformsTo(nws.getOpaeumLibrary().getBooleanType())){
						addWeightedBooleanValues(slot);
					}else if(pt.conformsTo(nws.getOpaeumLibrary().getIntegerType()) || pt.conformsTo(nws.getOpaeumLibrary().getRealType())){
						addNumberRangeDistributions(slot);
					}else if(pt.conformsTo(nws.getOpaeumLibrary().getStringType())){
						addWeightedStringValues(p, slot);
					}
				}else if(p.getNakedBaseType() instanceof INakedEnumeration){
					addWeightedEnumLiteralValues(p, slot);
				}
			}
		}
		return ais;
	}
	private void addWeightedEnumLiteralValues(INakedProperty p,SimulatingSlot slot){
		INakedEnumeration ne = (INakedEnumeration) p.getNakedBaseType();
		for(IEnumLiteral l:ne.getLiterals()){
			WeightedEnumLiteralValue els = SimulationFactory.eINSTANCE.createWeightedEnumLiteralValue();
			els.setLiteral((EnumerationLiteral) emfWorkspace.getElement(((INakedEnumerationLiteral) l).getId()));
			els.setWeight(1d / ne.getLiterals().size());
			slot.getValues().add(els);
		}
		slot.setSimulationStrategy(SimulationStrategy.WEIGHTED_DISTRIBUTION);
	}
	private void addWeightedStringValues(INakedProperty p,SimulatingSlot slot){
		WeightedStringValue svs1 = SimulationFactory.eINSTANCE.createWeightedStringValue();
		String name = p.getName();
		//TODO do this with strategies
		if(p.hasStereotype("EMail")){
			name="john.doe@gmail.co";
		}else if(p.hasStereotype("Digits")){
			name="0823436840";
		}
		
		svs1.setValue(name + 1);
		svs1.setWeight(33d);
		slot.getValues().add(svs1);
		WeightedStringValue svs2 = SimulationFactory.eINSTANCE.createWeightedStringValue();
		svs2.setValue(name + 2);
		svs2.setWeight(33d);
		slot.getValues().add(svs2);
		WeightedStringValue svs3 = SimulationFactory.eINSTANCE.createWeightedStringValue();
		svs3.setValue(name + 3);
		svs3.setWeight(33d);
		slot.getValues().add(svs3);
		slot.setSimulationStrategy(SimulationStrategy.WEIGHTED_DISTRIBUTION);
	}
	private void addNumberRangeDistributions(SimulatingSlot slot){
		Type type = slot.getDefiningFeature().getType();
		Double max = getNumberValue(type, "DecimalMax");
		if(max == null){
			max = getNumberValue(type, "Max");
		}
		Double min = getNumberValue(type, "DecimalMin");
		if(min == null){
			min = getNumberValue(type, "Min");
		}
		if(min == null){
			min = 0d;
		}
		if(max == null){
			max = 4000d;
		}
		double rangeSize = (max - min) / 3;
		NumberRangeDistribution nrd0 = SimulationFactory.eINSTANCE.createNumberRangeDistribution();
		nrd0.setUpperValue(max);
		NumberRangeDistribution nrd1 = SimulationFactory.eINSTANCE.createNumberRangeDistribution();
		nrd1.setLowerValue(min);
		nrd1.setUpperValue(min + rangeSize);
		nrd1.setWeight(33d);
		slot.getValues().add(nrd1);
		NumberRangeDistribution nrd2 = SimulationFactory.eINSTANCE.createNumberRangeDistribution();
		nrd2.setLowerValue(min + rangeSize);
		nrd2.setUpperValue(min + (rangeSize * 2));
		nrd2.setWeight(33d);
		slot.getValues().add(nrd2);
		NumberRangeDistribution nrd3 = SimulationFactory.eINSTANCE.createNumberRangeDistribution();
		nrd3.setLowerValue(min + (rangeSize * 2));
		nrd3.setUpperValue(min + (rangeSize * 3));
		nrd3.setWeight(33d);
		slot.getValues().add(nrd3);
		slot.setSimulationStrategy(SimulationStrategy.WEIGHTED_DISTRIBUTION);
	}
	private Double getNumberValue(Type type,String stName){
		Double max = null;
		if(StereotypesHelper.hasStereotype(type, stName)){
			Stereotype st = StereotypesHelper.getStereotype(type, stName);
			Object value2 = type.getValue(st, "value");
			if(value2 != null && value2.toString().length() > 0){
				String value = value2.toString();
				max = Double.valueOf(value);
			}
		}
		return max;
	}
	private void addWeightedBooleanValues(SimulatingSlot slot){
		WeightedBooleanValue bvs1 = SimulationFactory.eINSTANCE.createWeightedBooleanValue();
		bvs1.setWeight(50d);
		slot.getValues().add(bvs1);
		WeightedBooleanValue bvs2 = SimulationFactory.eINSTANCE.createWeightedBooleanValue();
		bvs2.setWeight(50d);
		slot.getValues().add(bvs2);
		slot.setSimulationStrategy(SimulationStrategy.WEIGHTED_DISTRIBUTION);
	}
	private Collection<INakedClassifier> getConcreteImplementationsOf(INakedProperty p){
		Set<INakedClassifier> result = new HashSet<INakedClassifier>();
		addSubclasses(result, p.getNakedBaseType());
		return result;
	}
	private void addSubclasses(Set<INakedClassifier> result,INakedClassifier nakedBaseType){
		if(nakedBaseType.getIsAbstract() == false){
			result.add(nakedBaseType);
		}
		// Assume they will all be loaded as this operation takes
		// place over the directory
		for(IClassifier c:nakedBaseType.getSubClasses()){
			addSubclasses(result, (INakedClassifier) c);
		}
		if(nakedBaseType instanceof INakedInterface){
			INakedInterface ni = (INakedInterface) nakedBaseType;
			Collection<INakedBehavioredClassifier> ic = ni.getImplementingClassifiers();
			for(INakedBehavioredClassifier bc:ic){
				addSubclasses(result, bc);
			}
		}
	}
	private SashWindowsMngr recreateWindowsManager(String resourceUri){
		Resource diagramsResource = getResource(resourceUri, "di");
		diagramsResource.getContents().clear();
		SashWindowsMngr m = DiFactory.eINSTANCE.createSashWindowsMngr();
		m.setPageList(DiFactory.eINSTANCE.createPageList());
		m.setSashModel(DiFactory.eINSTANCE.createSashModel());
		diagramsResource.getContents().add(m);
		return m;
	}
	protected final Resource getResource(String id,String extenstion){
		URI formUri = emfWorkspace.getDirectoryUri().appendSegment("simulation");
		String formId = id;
		formUri = formUri.appendSegment(formId);
		formUri = formUri.appendFileExtension(extenstion);
		Resource resource = null;
		try{
			resource = emfWorkspace.getResourceSet().getResource(formUri, false);
			resource.load(new HashMap<Object,Object>());
		}catch(Exception e){
			try{
				resource.delete(new HashMap<Object,Object>());
			}catch(Exception e2){
			}
			resource = emfWorkspace.getResourceSet().createResource(formUri);
		}
		try{
			resource.save(null);
		}catch(IOException e){
			e.printStackTrace();
		}
		return resource;
	}
	private Resource recreateDiagrams(String resourceUri){
		Resource diagramsResource = getResource(resourceUri, "notation");
		diagramsResource.getContents().clear();
		return diagramsResource;
	}
}
