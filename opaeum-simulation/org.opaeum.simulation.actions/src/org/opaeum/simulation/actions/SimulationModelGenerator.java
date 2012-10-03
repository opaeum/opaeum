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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.sashwindows.di.DiFactory;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashWindowsMngr;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.javageneration.TestModelValueStrategy;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.ContainedActualInstance;
import org.opaeum.metamodels.simulation.simulation.InstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.LiteralSimpleType;
import org.opaeum.metamodels.simulation.simulation.NormalDistribution;
import org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.metamodels.simulation.simulation.SimulationStrategy;
import org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue;
import org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;
import org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue;
import org.opaeum.metamodels.simulation.simulation.WeightedStringValue;

public class SimulationModelGenerator{
	private EmfWorkspace emfWorkspace = null;
	private int numberOfObjectsPerLevel = 3;
	private Map<Classifier,List<ActualInstance>> actualInstances = new HashMap<Classifier,List<ActualInstance>>();
	private Map<Classifier,InstanceSimulation> allInstances = new HashMap<Classifier,InstanceSimulation>();
	private SimulationModel simModel;
	private Set<Classifier> libClasses;
	public SimulationModelGenerator(EmfWorkspace emfWorkspace){
		super();
		this.emfWorkspace = emfWorkspace;
	}
	public SimulationModel run(){
		libClasses = new HashSet<Classifier>();
		libClasses.add(emfWorkspace.getOpaeumLibrary().getProcessObject());
		libClasses.add(emfWorkspace.getOpaeumLibrary().getProcessRequest());
		libClasses.add(emfWorkspace.getOpaeumLibrary().getResponsibilityObject());
		libClasses.add(emfWorkspace.getOpaeumLibrary().getTaskObject());
		libClasses.add(emfWorkspace.getOpaeumLibrary().getTaskRequest());
//		libClasses.add(emfWorkspace.getOpaeumLibrary().getBusiness());
//		libClasses.add(emfWorkspace.getOpaeumLibrary().getBusinessActor());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		this.simModel = SimulationFactory.eINSTANCE.createSimulationModel();
		simModel.setName(emfWorkspace.getName() + "_" + sdf.format(new Date()));
		recreateDiagrams(simModel.getName());
		recreateWindowsManager(simModel.getName());
		Resource rs = getResource(simModel.getName(), "uml");
		rs.getContents().add(simModel);
		if(emfWorkspace.getOpaeumLibrary().getBusinessNetwork() != null){
			simModel.getPackagedElements().add(buildActualInstance(emfWorkspace.getOpaeumLibrary().getBusinessNetwork(), 2));
		}
		Classifier applicationRoot = emfWorkspace.getApplicationRoot();
		if(EmfClassifierUtil.isCompositionParticipant(applicationRoot)){
			List<? extends Property> attrs = emfWorkspace.getOpaeumLibrary().getEffectiveAttributes(applicationRoot);
			Set<Classifier> rootsImplemented = new HashSet<Classifier>();
			for(Property p:attrs){
				if(p.isComposite() && !libClasses.contains(p.getType())){
					for(Classifier c:getConcreteImplementationsOf(p)){
						if(!rootsImplemented.contains(c)){// Only a predefined number of each
							rootsImplemented.add(c);
							if(c instanceof Actor){
								simModel.getPackagedElements().add(buildActualInstance(c, 3));
								simModel.getPackagedElements().add(buildActualInstance(c, 3));
								simModel.getPackagedElements().add(buildActualInstance(c, 3));
								simModel.getPackagedElements().add(buildActualInstance(c, 3));
							}else{
								simModel.getPackagedElements().add(buildActualInstance(c, 3));
							}
						}
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
		TreeIterator<EObject> eAllContents = emfWorkspace.eAllContents();
		while(eAllContents.hasNext()){
			EObject e = (EObject) eAllContents.next();
			boolean isAssociation = e instanceof Association && !EmfAssociationUtil.isClass(((Association) e));
			if(e instanceof Classifier){
				Classifier c = (Classifier) e;
				if((EmfClassifierUtil.isCompositionParticipant(c) || EmfClassifierUtil.isStructuredDataType(c))
						&& !(isAssociation || ((Classifier) e).isAbstract()) && !(e instanceof Behavior)){
					if(!actualInstances.containsKey(e) && !libClasses.contains(e)){
						buildAllInstanceSimulation((Classifier) e);
					}
				}
			}
		}
	}
	private void setReferenceStrategies(InstanceSpecification is){
		EList<Slot> slots = is.getSlots();
		for(Slot slot:slots){
			if(slot.getValues().isEmpty()){
				Property p = (Property) emfWorkspace.getModelElement(EmfWorkspace.getId(slot.getDefiningFeature()));
				if(EmfClassifierUtil.isComplexStructure(p.getType())){
					SimulatingSlot ss = (SimulatingSlot) slot;
					if(actualInstances.containsKey(p.getType())){
						List<ActualInstance> list = actualInstances.get(p.getType());
						for(ActualInstance ais:list){
							WeightedInstanceValue ris = SimulationFactory.eINSTANCE.createWeightedInstanceValue();
							ss.getValues().add(ris);
							ris.setInstance(ais);
							ris.setWeight(1d / list.size());
							ss.setSimulationStrategy(SimulationStrategy.WEIGHTED_DISTRIBUTION);
						}
					}else{
						Collection<Classifier> subclasses = getConcreteImplementationsOf(p);
						NormalDistribution nd = SimulationFactory.eINSTANCE.createNormalDistribution();
						ss.setSizeDistribution(nd);
						nd.setName("NormalDistribution");
						nd.setMean(4d);
						nd.setStandardDeviation(1.5d);
						ss.setSimulationStrategy(SimulationStrategy.INSTANCE_SIMULATION);
						for(Classifier c:subclasses){
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
	private ActualInstance buildActualInstance(Classifier nc,int numberOfLevelsOfActualInstances){
		ActualInstance ais = SimulationFactory.eINSTANCE.createActualInstance();
		List<ActualInstance> list = getInstanceSimulationsFOr(nc);
		list.add(ais);
		int count = getInstanceSimulationsFOr(nc).size();
		ais.setName(nc.getName() + "ActualInstance" + count);
		ais.getClassifiers().add(nc);
		for(Property p:this.emfWorkspace.getOpaeumLibrary().getEffectiveAttributes(nc)){
			if(!(p.isDerived() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite()))){
				addSlotForProperty(count, numberOfLevelsOfActualInstances - 1, ais, p);
			}
		}
		return ais;
	}
	private void addSlotForProperty(int count,int numberOfLevelsOfActualInstances,ActualInstance ais,Property feature){
		if(feature != null && !libClasses.contains(feature.getType())){
			SimulatingSlot slot = SimulationFactory.eINSTANCE.createSimulatingSlot();
			ais.getSlots().add(slot);
			slot.setDefiningFeature(feature);
			slot.setSimulationStrategy(SimulationStrategy.GIVEN_VALUE);
			if(EmfClassifierUtil.isSimpleType(feature.getType())
					&& EmfClassifierUtil.hasStrategy((DataType) feature.getType(), TestModelValueStrategy.class)){
				addValueType(count, slot, (DataType) feature.getType());
			}else if(feature.getType() instanceof PrimitiveType){
				addPrimitiveValue(count, feature, slot);
			}else if(feature.getType() instanceof Enumeration){
				addEnumerationValue(feature, slot);
			}else if(EmfClassifierUtil.isComplexStructure(feature.getType())){
				if(feature.isComposite() && numberOfLevelsOfActualInstances > 0
						&& !(EmfClassifierUtil.isCompositionParticipant(feature.getType()) && EmfClassifierUtil.isFact(feature.getType()))){
					if(feature.getQualifiers().size() == 1 && feature.getQualifiers().get(0).getType() instanceof Enumeration){
						addQualifiedContainedInstance(numberOfLevelsOfActualInstances, feature, slot, feature.getQualifiers().get(0));
					}else{
						addContainedAtualInstance(numberOfLevelsOfActualInstances, feature, slot);
					}
				}
			}
		}
	}
	public void addValueType(int count,SimulatingSlot slot,DataType vt){
		if(EmfClassifierUtil.hasStrategy(vt, TestModelValueStrategy.class)){
			LiteralSimpleType i = SimulationFactory.eINSTANCE.createLiteralSimpleType();
			i.setStringValue(EmfClassifierUtil.getStrategy(vt, TestModelValueStrategy.class).getDefaultStringValue(count));
			i.setRuntimeStrategyFactory(EmfClassifierUtil.getStrategyFactory(vt).getRuntimeStrategyFactory());
			slot.getValues().add(i);
		}
	}
	private void addQualifiedContainedInstance(int numberOfLevelsOfActualInstances,Property p,SimulatingSlot owningSlot,Property qualifer){
		Enumeration en = (Enumeration) qualifer.getType();
		int i = 0;
		for(EnumerationLiteral l:en.getOwnedLiterals()){
			Collection<Classifier> subClasses = getConcreteImplementationsOf(p);
			if(subClasses.size() == 1){// TODO handle polymorphism
				for(Classifier c:subClasses){
					ContainedActualInstance civs = SimulationFactory.eINSTANCE.createContainedActualInstance();
					owningSlot.getValues().add(civs);
					ActualInstance actualInstance = this.buildActualInstance((Classifier) c, numberOfLevelsOfActualInstances);
					for(Slot childSlot:actualInstance.getSlots()){
						if(childSlot.getDefiningFeature().getName().equals(qualifer.getName())){
							InstanceValue enumInstanceValue = UMLFactory.eINSTANCE.createInstanceValue();
							EList<ValueSpecification> values = childSlot.getValues();
							values.add(enumInstanceValue);
							enumInstanceValue.setInstance(l);
						}
					}
					civs.setContainedInstance(actualInstance);
				}
			}
			i++;
		}
	}
	private void addContainedAtualInstance(int numberOfLevelsOfActualInstances,Property p,SimulatingSlot slot){
		Collection<Classifier> subClasses = getConcreteImplementationsOf(p);
		if(subClasses.size() > 0){
			int i = EmfPropertyUtil.isMany(p) ? this.numberOfObjectsPerLevel : 1;
			outer:while(i >= 1){
				for(Classifier c:subClasses){
					ContainedActualInstance civs = SimulationFactory.eINSTANCE.createContainedActualInstance();
					slot.getValues().add(civs);
					civs.setContainedInstance(this.buildActualInstance((Classifier) c, numberOfLevelsOfActualInstances));
					if(i == 1){
						break outer;
					}
					i--;
				}
			}
		}
	}
	private void addEnumerationValue(Property p,SimulatingSlot slot){
		Enumeration ne = (Enumeration) p.getType();
		if(ne.getOwnedLiterals().size() > 0){
			EnumerationLiteral firstLiteral = (EnumerationLiteral) ne.getOwnedLiterals().get(0);
			InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
			iv.setInstance(firstLiteral);
			slot.getValues().add(iv);
		}
	}
	private void addPrimitiveValue(int count,Property p,SimulatingSlot slot){
		PrimitiveType pt = (PrimitiveType) p.getType();
		if(pt.conformsTo(emfWorkspace.getOpaeumLibrary().getBooleanType())){
			LiteralBoolean b = UMLFactory.eINSTANCE.createLiteralBoolean();
			slot.getValues().add(b);
			b.setValue(count % 2 == 0);
		}else if(pt.conformsTo(emfWorkspace.getOpaeumLibrary().getIntegerType())){
			LiteralInteger i = UMLFactory.eINSTANCE.createLiteralInteger();
			i.setValue(count);
			slot.getValues().add(i);
		}else if(pt.conformsTo(emfWorkspace.getOpaeumLibrary().getRealType())){
			LiteralReal r = UMLFactory.eINSTANCE.createLiteralReal();
			slot.getValues().add(r);
			r.setValue(1d / count);
		}else if(pt.conformsTo(emfWorkspace.getOpaeumLibrary().getStringType())){
			LiteralString s = UMLFactory.eINSTANCE.createLiteralString();
			String name = p.getName();
			slot.getValues().add(s);
			// TODO do this with strategies
			if(StereotypesHelper.hasStereotype(pt, "Email")){
				name = "john.doe@gmail.co";
			}else if(StereotypesHelper.hasStereotype(pt, "Digits")){
				name = "0823436840";
			}
			s.setValue(name + count);
		}
	}
	private List<ActualInstance> getInstanceSimulationsFOr(Classifier nc){
		List<ActualInstance> list = actualInstances.get(nc);
		if(list == null){
			list = new ArrayList<ActualInstance>();
			actualInstances.put(nc, list);
		}
		return list;
	}
	private InstanceSimulation buildAllInstanceSimulation(Classifier element){
		// TODO specify algorithms for inheritance
		InstanceSimulation ais = SimulationFactory.eINSTANCE.createInstanceSimulation();
		simModel.getPackagedElements().add(ais);
		allInstances.put(element, ais);
		ais.setName(element.getName() + "InstanceSimulation");
		ais.getClassifiers().add(element);
		for(Property feature:EmfElementFinder.getPropertiesInScope(element)){
			if(!libClasses.contains(feature.getType()) && !feature.isReadOnly() && !feature.isDerived()
					&& !(feature.getOtherEnd() != null && feature.getOtherEnd().isComposite())){
				SimulatingSlot slot = SimulationFactory.eINSTANCE.createSimulatingSlot();
				ais.getSlots().add(slot);
				slot.setDefiningFeature(feature);
				if(EmfClassifierUtil.isSimpleType(feature.getType())
						&& EmfClassifierUtil.hasStrategy((DataType) feature.getType(), TestModelValueStrategy.class)){
					DataType st = (DataType) feature.getType();
					addWeightSimpleType(slot, st);
				}else if(feature.getType() instanceof PrimitiveType){
					PrimitiveType pt = (PrimitiveType) feature.getType();
					if(pt.conformsTo(emfWorkspace.getOpaeumLibrary().getBooleanType())){
						addWeightedBooleanValues(slot);
					}else if(pt.conformsTo(emfWorkspace.getOpaeumLibrary().getIntegerType())
							|| pt.conformsTo(emfWorkspace.getOpaeumLibrary().getRealType())){
						addNumberRangeDistributions(slot);
					}else if(pt.conformsTo(emfWorkspace.getOpaeumLibrary().getStringType())){
						addWeightedStringValues(feature, slot);
					}
				}else if(feature.getType() instanceof Enumeration){
					addWeightedEnumLiteralValues(feature, slot);
				}
			}
		}
		return ais;
	}
	public void addWeightSimpleType(SimulatingSlot slot,DataType st){
		WeightedSimpleTypeValue wvt1 = SimulationFactory.eINSTANCE.createWeightedSimpleTypeValue();
		wvt1.setWeight(20d);
		wvt1.setStringValue(EmfClassifierUtil.getStrategy(st, TestModelValueStrategy.class).getDefaultStringValue(97));
		wvt1.setRuntimeStrategyFactory(EmfClassifierUtil.getStrategyFactory(st).getRuntimeStrategyFactory());
		slot.getValues().add(wvt1);
		WeightedSimpleTypeValue wvt2 = SimulationFactory.eINSTANCE.createWeightedSimpleTypeValue();
		wvt2.setWeight(30d);
		wvt2.setStringValue(EmfClassifierUtil.getStrategy(st, TestModelValueStrategy.class).getDefaultStringValue(3));
		wvt2.setRuntimeStrategyFactory(EmfClassifierUtil.getStrategyFactory(st).getRuntimeStrategyFactory());
		slot.getValues().add(wvt2);
		WeightedSimpleTypeValue wvt3 = SimulationFactory.eINSTANCE.createWeightedSimpleTypeValue();
		wvt3.setWeight(40d);
		wvt3.setStringValue(EmfClassifierUtil.getStrategy(st, TestModelValueStrategy.class).getDefaultStringValue(58));
		wvt3.setRuntimeStrategyFactory(EmfClassifierUtil.getStrategyFactory(st).getRuntimeStrategyFactory());
		slot.getValues().add(wvt3);
	}
	private void addWeightedEnumLiteralValues(Property p,SimulatingSlot slot){
		Enumeration ne = (Enumeration) p.getType();
		for(EnumerationLiteral l:ne.getOwnedLiterals()){
			WeightedEnumLiteralValue els = SimulationFactory.eINSTANCE.createWeightedEnumLiteralValue();
			els.setLiteral(l);
			els.setWeight(1d / ne.getOwnedLiterals().size());
			slot.getValues().add(els);
		}
		slot.setSimulationStrategy(SimulationStrategy.WEIGHTED_DISTRIBUTION);
	}
	private void addWeightedStringValues(Property p,SimulatingSlot slot){
		WeightedStringValue svs1 = SimulationFactory.eINSTANCE.createWeightedStringValue();
		String name = p.getName();
		// TODO do this with strategies
		if(StereotypesHelper.hasStereotype(p, "Email") || StereotypesHelper.hasStereotype(p.getType(), "Email")){
			name = "john.doe@gmail.co";
		}else if(StereotypesHelper.hasStereotype(p, "Digits") || StereotypesHelper.hasStereotype(p.getType(), "Digits")){
			name = "0823436840";
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
	private Collection<Classifier> getConcreteImplementationsOf(Property p){
		Set<Classifier> result = new HashSet<Classifier>();
		addSubclasses(result, (Classifier) p.getType());
		return result;
	}
	private void addSubclasses(Set<Classifier> result,Classifier nakedBaseType){
		if(nakedBaseType.isAbstract() == false){
			result.add(nakedBaseType);
		}
		// Assume they will all be loaded as this operation takes
		// place over the directory
		for(Classifier c:EmfClassifierUtil.getSubClasses(nakedBaseType)){
			addSubclasses(result, (Classifier) c);
		}
		if(nakedBaseType instanceof Interface){
			Interface ni = (Interface) nakedBaseType;
			Collection<BehavioredClassifier> ic = EmfClassifierUtil.getImplementingClassifiers(ni);
			for(BehavioredClassifier bc:ic){
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
