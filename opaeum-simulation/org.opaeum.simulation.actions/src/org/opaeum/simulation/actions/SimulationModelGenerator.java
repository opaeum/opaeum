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
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedPrimitiveType;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodels.simulation.simulation.ActualInstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.AllInstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation;
import org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation;
import org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation;
import org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution;
import org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulatedSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.metamodels.simulation.simulation.StringValueSimulation;

public class SimulationModelGenerator{
	private EmfWorkspace emfWorkspace = null;
	private INakedModelWorkspace nws = null;
	private int numberOfObjectsPerLevel = 3;
	private Map<INakedClassifier,List<ActualInstanceSimulation>> actualInstances = new HashMap<INakedClassifier,List<ActualInstanceSimulation>>();
	private Map<INakedClassifier,AllInstanceSimulation> allInstances = new HashMap<INakedClassifier,AllInstanceSimulation>();
	private SimulationModel simModel;
	public SimulationModelGenerator(EmfWorkspace emfWorkspace,INakedModelWorkspace nws){
		super();
		this.emfWorkspace = emfWorkspace;
		this.nws = nws;
	}
	public void run(){
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
				if(p.isComposite()){
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
	}
	private void setReferenceStrategies(){
		Collection<List<ActualInstanceSimulation>> values = actualInstances.values();
		for(List<ActualInstanceSimulation> list:values){
			for(ActualInstanceSimulation ais:list){
				setReferenceStrategies(ais);
			}
		}
		for(AllInstanceSimulation ais:allInstances.values()){
			setReferenceStrategies(ais);
		}
	}
	private void buildAllInstanceSimulations(){
		for(INakedElement e:nws.getAllElements()){
			if(e instanceof INakedComplexStructure && ((INakedComplexStructure) e).isPersistent()){
				if(!actualInstances.containsKey(e) && emfWorkspace.getElement(e.getId()) != null){
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
					if(actualInstances.containsKey(p.getNakedBaseType())){
						List<ActualInstanceSimulation> list = actualInstances.get(p.getNakedBaseType());
						for(ActualInstanceSimulation ais:list){
							ReferencedInstanceSimulation ris = SimulationFactory.eINSTANCE.createReferencedInstanceSimulation();
							slot.getValues().add(ris);
							ris.setInstance(ais);
							ris.setWeight(1d / list.size());
						}
					}else{
						InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
						slot.getValues().add(iv);
						iv.setInstance(allInstances.get(p.getNakedBaseType()));
					}
				}
			}
		}
	}
	private ActualInstanceSimulation buildActualInstance(INakedClassifier nc,int count,int numberOfLevelsOfActualInstances){
		Classifier element = (Classifier) emfWorkspace.getElement(nc.getId());
		ActualInstanceSimulation ais = SimulationFactory.eINSTANCE.createActualInstanceSimulation();
		List<ActualInstanceSimulation> list = getInstanceSimulationsFOr(nc);
		list.add(ais);
		ais.setName(element.getName() + "ActualInstance" + count);
		ais.getClassifiers().add(element);
		ais.getSlots().clear();// May have been created automatically
		for(INakedProperty p:nc.getEffectiveAttributes()){
			if(!p.isDerived()){
				StructuralFeature feature = (StructuralFeature) emfWorkspace.getElement(p.getId());
				if(feature != null){
					SimulatedSlot slot = SimulationFactory.eINSTANCE.createSimulatedSlot();
					ais.getSlots().add(slot);
					slot.setDefiningFeature(feature);
					if(p.getNakedBaseType() instanceof INakedPrimitiveType){
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
							slot.getValues().add(UMLFactory.eINSTANCE.createLiteralString());
						}else if(pt.conformsTo(nws.getOpaeumLibrary().getStringType())){
							LiteralString s = UMLFactory.eINSTANCE.createLiteralString();
							s.setValue(p.getName() + count);
							slot.getValues().add(s);
						}
					}else if(p.getNakedBaseType() instanceof INakedEnumeration){
						INakedEnumeration ne = (INakedEnumeration) p.getNakedBaseType();
						if(ne.getLiterals().size() > 0){
							INakedEnumerationLiteral firstLiteral = (INakedEnumerationLiteral) ne.getLiterals().get(0);
							InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
							iv.setInstance((EnumerationLiteral) emfWorkspace.getElement(firstLiteral.getId()));
							slot.getValues().add(iv);
						}
					}else if(p.getNakedBaseType() instanceof INakedComplexStructure){
						if(p.isComposite()){
							if(numberOfLevelsOfActualInstances >= 0){
								Collection<INakedClassifier> subClasses = getConcreteImplementationsOf(p);
								if(subClasses.size() > 0){
									int i = this.numberOfObjectsPerLevel;
									outer:while(i >= 0){
										for(IClassifier c:subClasses){
											if(i < 0){
												break outer;
											}else if(emfWorkspace.getElement(((INakedElement) c).getId()) != null){
													ContainedInstanceValueSimulation civs = SimulationFactory.eINSTANCE.createContainedInstanceValueSimulation();
													slot.getValues().add(civs);
													civs.setContainedInstance(this.buildActualInstance((INakedClassifier) c, i + 1,
															numberOfLevelsOfActualInstances - 1));
											}
											i--;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return ais;
	}
	private List<ActualInstanceSimulation> getInstanceSimulationsFOr(INakedClassifier nc){
		List<ActualInstanceSimulation> list = actualInstances.get(nc);
		if(list == null){
			list = new ArrayList<ActualInstanceSimulation>();
			actualInstances.put(nc, list);
		}
		return list;
	}
	private AllInstanceSimulation buildAllInstanceSimulation(INakedClassifier nc){
		// TODO specify algorithms for inheritance
		Classifier element = (Classifier) emfWorkspace.getElement(nc.getId());
		AllInstanceSimulation ais = SimulationFactory.eINSTANCE.createAllInstanceSimulation();
		simModel.getPackagedElements().add(ais);
		allInstances.put(nc, ais);
		ais.setName(element.getName() + "AllInstanceSimulation");
		ais.getClassifiers().add(element);
		ais.getSlots().clear();// May have been created automatically
		for(INakedProperty p:nc.getEffectiveAttributes()){
			StructuralFeature feature = (StructuralFeature) emfWorkspace.getElement(p.getId());
			if(feature != null){
				SimulatedSlot slot = SimulationFactory.eINSTANCE.createSimulatedSlot();
				ais.getSlots().add(slot);
				slot.setDefiningFeature(feature);
				if(p.getNakedBaseType() instanceof INakedPrimitiveType){
					INakedPrimitiveType pt = (INakedPrimitiveType) p.getNakedBaseType();
					if(pt.conformsTo(nws.getOpaeumLibrary().getBooleanType())){
						BooleanValueSimulation bvs1 = SimulationFactory.eINSTANCE.createBooleanValueSimulation();
						bvs1.setWeight(50d);
						slot.getValues().add(bvs1);
						BooleanValueSimulation bvs2 = SimulationFactory.eINSTANCE.createBooleanValueSimulation();
						bvs2.setWeight(50d);
						slot.getValues().add(bvs2);
					}else if(pt.conformsTo(nws.getOpaeumLibrary().getIntegerType()) || pt.conformsTo(nws.getOpaeumLibrary().getRealType())){
						NumberRangeDistribution nrd1 = SimulationFactory.eINSTANCE.createNumberRangeDistribution();
						nrd1.setLowerValue(0d);
						nrd1.setUpperValue(40d);
						nrd1.setWeight(33d);
						slot.getValues().add(nrd1);
						NumberRangeDistribution nrd2 = SimulationFactory.eINSTANCE.createNumberRangeDistribution();
						nrd2.setLowerValue(40d);
						nrd2.setUpperValue(80d);
						nrd2.setWeight(33d);
						slot.getValues().add(nrd2);
						NumberRangeDistribution nrd3 = SimulationFactory.eINSTANCE.createNumberRangeDistribution();
						nrd3.setLowerValue(80d);
						nrd3.setUpperValue(120d);
						nrd3.setWeight(33d);
						slot.getValues().add(nrd3);
					}else if(pt.conformsTo(nws.getOpaeumLibrary().getStringType())){
						StringValueSimulation svs1 = SimulationFactory.eINSTANCE.createStringValueSimulation();
						svs1.setValue(p.getName() + 1);
						svs1.setWeight(33d);
						slot.getValues().add(svs1);
						StringValueSimulation svs2 = SimulationFactory.eINSTANCE.createStringValueSimulation();
						svs2.setValue(p.getName() + 2);
						svs2.setWeight(33d);
						slot.getValues().add(svs2);
						StringValueSimulation svs3 = SimulationFactory.eINSTANCE.createStringValueSimulation();
						svs3.setValue(p.getName() + 3);
						svs3.setWeight(33d);
						slot.getValues().add(svs3);
					}
				}else if(p.getNakedBaseType() instanceof INakedEnumeration){
					INakedEnumeration ne = (INakedEnumeration) p.getNakedBaseType();
					for(IEnumLiteral l:ne.getLiterals()){
						InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
						EnumLiteralSimulation els = SimulationFactory.eINSTANCE.createEnumLiteralSimulation();
						els.setLiteral((EnumerationLiteral) emfWorkspace.getElement(((INakedEnumerationLiteral) l).getId()));
						els.setWeight(1d / ne.getLiterals().size());
						slot.getValues().add(iv);
					}
				}
			}
		}
		return ais;
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
