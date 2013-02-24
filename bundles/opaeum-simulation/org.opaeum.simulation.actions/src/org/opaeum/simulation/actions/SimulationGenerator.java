package org.opaeum.simulation.actions;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.ContainedActualInstance;
import org.opaeum.metamodels.simulation.simulation.LiteralSimpleType;
import org.opaeum.metamodels.simulation.simulation.NumericValueDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue;
import org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;
import org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue;
import org.opaeum.metamodels.simulation.simulation.WeightedStringValue;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.CompositionNode;

public class SimulationGenerator extends AbstractSimulationCodeGenerator{
	@VisitBefore(match = {Interface.class,Component.class,Class.class,DataType.class,Association.class,Actor.class,Collaboration.class},matchSubclasses = true)
	public void visitClassifier(Classifier nc){
		List<InstanceSpecification> iss = getInstances(nc);
		if(iss.size() > 0){
			for(InstanceSpecification is:iss){
				OJAnnotatedClass dataGenerator = new OJAnnotatedClass(dataGeneratorName(nc, is).getLast());
				findOrCreatePackage(ojUtil.packagePathname(nc.getNamespace())).addToClasses(dataGenerator);
				OJPathName pn;
				if(EmfClassifierUtil.isStructuredDataType(nc)){
					pn = new OJPathName("org.opaeum.simulation.StructInstanceSimulation");
					dataGenerator.setSuperclass(pn);
				}else{
					pn = new OJPathName("org.opaeum.simulation.EntityInstanceSimulation");
					dataGenerator.setSuperclass(pn);
				}
				createTextPath(dataGenerator, SimulationSourceFolderId.GEN_SRC);
				dataGenerator.addToImports("org.opaeum.simulation.SimulationMetaData");
				buildCreator(nc, dataGenerator, is);
				OJAnnotatedOperation populator = new OJAnnotatedOperation("populateReferences");
				populator.addToThrows(Exception.class.getName());
				dataGenerator.addToOperations(populator);
				populator.getOwner().addToImports(Exception.class.getName());
				if(EmfClassifierUtil.isStructuredDataType(nc)){
					populator.addParam("in", new OJPathName(Object.class.getName()));
				}else{
					populator.addParam("in", new OJPathName(CompositionNode.class.getName()));
				}
				OJAnnotatedField instance = new OJAnnotatedField("instance", ojUtil.classifierPathname(nc));
				populator.getBody().addToLocals(instance);
				instance.setInitExp("(" + ojUtil.classifierPathname(nc).getLast() + ")in");
				for(Slot s:is.getSlots()){
					SimulatingSlot slot = (SimulatingSlot) s;
					if(slot.getDefiningFeature() != null){
						Property p = (Property) slot.getDefiningFeature();
						if(EmfClassifierUtil.isCompositionParticipant(p.getType()) && !p.isComposite()
								&& !(p.getOtherEnd() != null && p.getOtherEnd().isComposite() && !EmfPropertyUtil.isInverse(p))){
							PropertyMap m = ojUtil.buildStructuralFeatureMap(getLibrary().findEffectiveAttribute(nc, p.getName()));
							OJWhileStatement whileSize = buildLoopForSize(is, populator, slot, m);
							populator.getOwner().addToImports(m.javaBaseTypePath());
							String getReference = "(" + m.javaBaseType() + ")SimulationMetaData.getInstance().getEntityValueProvider(\"" + is.getQualifiedName() + "\",\""
									+ m.umlName() + "\").getNextReference()";
							;
							if(m.isMany()){
								whileSize.getBody().addToStatements("instance." + m.adder() + "(" + getReference + ")");
							}else{
								whileSize.getBody().addToStatements("instance." + m.setter() + "(" + getReference + ")");
							}
						}
					}
				}
			}
		}
	}
	private OJAnnotatedOperation buildCreator(Classifier nc,OJAnnotatedClass dataGenerator,InstanceSpecification is){
		OJAnnotatedOperation creator = initializeCreator(nc, dataGenerator, is);
		for(Slot sl:is.getSlots()){
			SimulatingSlot slot = (SimulatingSlot) sl;
			if(slot.getDefiningFeature() != null){
				Property p = (Property) slot.getDefiningFeature();
				if(EmfClassifierUtil.isSimpleType(p.getType()) || p.getType() instanceof Enumeration){
					generateDataTypeValue(is, creator, slot, p);
					// Get from bucket
				}else if(EmfClassifierUtil.isCompositionParticipant(p.getType()) && p.isComposite()){
					generateComplexStructuredData(dataGenerator, is, creator, slot, p);
				}else if(EmfClassifierUtil.isStructuredDataType(p.getType())){
					generateComplexStructuredData(dataGenerator, is, creator, slot, p);
				}
			}
		}
		if(EmfClassifierUtil.isCompositionParticipant(nc) && EmfPropertyUtil.getEndToComposite(nc) != null){
			creator.getBody().addToStatements("result.addToOwningObject()");
		}
		return creator;
	}
	private void generateComplexStructuredData(OJAnnotatedClass dataGenerator,InstanceSpecification is,OJAnnotatedOperation creator,SimulatingSlot slot,Property p){
		PropertyMap m = ojUtil.buildStructuralFeatureMap(p);
		boolean hasEntityValueProvider = false;
		for(ValueSpecification vs:slot.getValues()){
			if(vs instanceof ContainedActualInstance){
				ContainedActualInstance cai = (ContainedActualInstance) vs;
				OJPathName generatorPathName = super.dataGeneratorName(cai.getContainedInstance().getClassifiers().get(0), cai.getContainedInstance());
				dataGenerator.addToImports(generatorPathName);
				if(EmfClassifierUtil.isStructuredDataType(p.getType())){
					creator.getBody().addToStatements(generatorPathName.getLast() + ".INSTANCE.generateInstance()");
				}else{
					creator.getBody().addToStatements(generatorPathName.getLast() + ".INSTANCE.generateInstance(result)");// Will be
					// added by
					// addToOwningObject
				}
			}else if(vs instanceof WeightedInstanceValue){
				hasEntityValueProvider = true;
			}
		}
		if(hasEntityValueProvider){
			createNewInstanceFromSimulation(is, creator, slot, m, EmfClassifierUtil.isStructuredDataType(p.getType()) ? "Struct" : "Entity");
		}
	}
	private void createNewInstanceFromSimulation(InstanceSpecification is,OJAnnotatedOperation creator,SimulatingSlot slot,PropertyMap m,String obsolete){
		OJWhileStatement whileSize = buildLoopForSize(is, creator, slot, m);
		creator.getOwner().addToImports(m.javaBaseTypePath());
		// Will be added by addToOwningObject
		if(EmfClassifierUtil.isStructuredDataType(m.getProperty().getType())){
			if(m.isMany()){
				whileSize.getBody().addToStatements(
						"result." + m.adder() + "((" + m.javaBaseType() + ")SimulationMetaData.getInstance().getStructValueProvider(\"" + is.getQualifiedName() + "\",\""
								+ m.umlName() + "\").createNewInstance())");
			}else{
				whileSize.getBody().addToStatements(
						"result." + m.setter() + "((" + m.javaBaseType() + ")SimulationMetaData.getInstance().getStructValueProvider(\"" + is.getQualifiedName() + "\",\""
								+ m.umlName() + "\").createNewInstance())");
			}
		}else{
			whileSize.getBody().addToStatements(
					"SimulationMetaData.getInstance().getEntityValueProvider(\"" + is.getQualifiedName() + "\",\"" + m.umlName() + "\").createNewInstance(result)");
		}
	}
	private String calculateSize(InstanceSpecification is,SimulatingSlot slot,PropertyMap m){
		String size;
		if(slot.getSizeDistribution() != null && m.isMany()){
			size = "SimulationMetaData.getInstance().getNextPropertySize(\"" + is.getQualifiedName() + "\",\"" + m.umlName() + "\")";
		}else{
			size = "1";
		}
		return size;
	}
	private void generateDataTypeValue(InstanceSpecification is,OJAnnotatedOperation creator,SimulatingSlot slot,Property p){
		boolean hasDoneSimulatedValue = false;
		for(ValueSpecification vs:slot.getValues()){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(p);
			if(vs instanceof LiteralBoolean){
				LiteralBoolean b = (LiteralBoolean) vs;
				addGivenValue(creator, map, (Object) b.booleanValue());
			}else if(vs instanceof LiteralReal){
				LiteralReal b = (LiteralReal) vs;
				addGivenValue(creator, map, (Object) b.realValue());
			}else if(vs instanceof LiteralInteger){
				LiteralInteger b = (LiteralInteger) vs;
				addGivenValue(creator, map, (Object) b.integerValue());
			}else if(vs instanceof LiteralSimpleType){
				LiteralSimpleType b = (LiteralSimpleType) vs;
				OJPathName strat = new OJPathName(b.getRuntimeStrategyFactory());
				creator.getOwner().addToImports(strat);
				creator.getOwner().addToImports(map.javaBaseTypePath());
				creator.getOwner().addToImports(Exception.class.getName());
				creator.addToThrows(Exception.class.getName());
				addGivenValue(creator, map, "(" + map.javaBaseType() + ")new " + strat.getLast()
						+ "().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString(\"" + b.getStringValue() + "\")");
			}else if(vs instanceof LiteralString){
				LiteralString b = (LiteralString) vs;
				addGivenValue(creator, map, "\"" + b.stringValue() + "\"");
			}else if(vs instanceof InstanceValue){
				// Should be enum
				InstanceValue b = (InstanceValue) vs;
				if(b.getInstance() instanceof EnumerationLiteral){
					EnumerationLiteral lit = (EnumerationLiteral) b.getInstance();
					OJPathName pn = ojUtil.classifierPathname((Classifier) lit.getEnumeration());
					creator.getOwner().addToImports(pn);
					addGivenValue(creator, map, pn.getLast() + "." + lit.getName().toUpperCase());
				}
			}else if((vs instanceof WeightedBooleanValue || vs instanceof WeightedEnumLiteralValue || vs instanceof WeightedStringValue
					|| vs instanceof NumericValueDistribution || vs instanceof WeightedSimpleTypeValue)
					&& !hasDoneSimulatedValue){
				// Only execute this code once for all distributions
				hasDoneSimulatedValue = true;
				OJWhileStatement whileSize = buildLoopForSize(is, creator, slot, map);
				// Will be added by addToOwningObject
				creator.getOwner().addToImports(map.javaBaseDefaultTypePath());
				String newVal = "(" + map.javaBaseType() + ")SimulationMetaData.getInstance().getNextValueForProperty(\"" + is.getQualifiedName() + "\",\""
						+ slot.getDefiningFeature().getName() + "\")";
				if(map.isMany()){
					whileSize.getBody().addToStatements("result." + map.adder() + "(" + newVal + ")");
				}else{
					whileSize.getBody().addToStatements("result." + map.setter() + "(" + newVal + ")");
				}
			}
		}
	}
	private OJWhileStatement buildLoopForSize(InstanceSpecification is,OJAnnotatedOperation creator,SimulatingSlot slot,PropertyMap map){
		OJBlock block = new OJBlock();
		creator.getBody().addToStatements(block);
		OJAnnotatedField countField = new OJAnnotatedField(map.umlName() + "Count", new OJPathName("int"));
		block.addToLocals(countField);
		String size = calculateSize(is, slot, map);
		countField.setInitExp("0");
		OJWhileStatement whileSize = new OJWhileStatement(countField.getName() + "<" + size);
		block.addToStatements(whileSize);
		whileSize.getBody().addToStatements(countField.getName() + "++");
		return whileSize;
	}
	private OJAnnotatedOperation initializeCreator(Classifier nc,OJAnnotatedClass dataGenerator,InstanceSpecification is){
		String name = "createNewObject";
		OJPathName pn1 = ojUtil.classifierPathname(nc);
		OJAnnotatedOperation creator = new OJAnnotatedOperation(name);
		dataGenerator.addToOperations(creator);
		creator.addToThrows(Exception.class.getName());
		creator.setReturnType(pn1);
		if(EmfClassifierUtil.isCompositionParticipant(nc)){
			creator.addParam("parent", new OJPathName(CompositionNode.class.getName()));
			if(is instanceof ActualInstance){
				OJAnnotatedField constant = new OJAnnotatedField(NameConverter.toJavaVariableName(is.getName()).toUpperCase(), pn1);
				dataGenerator.addToFields(constant);
				constant.setStatic(true);
				constant.setFinal(true);
				constant.setInitExp("new " + pn1.getLast() + "()");
				creator.initializeResultVariable(constant.getName());
				OJAnnotatedField INSTANCE = new OJAnnotatedField("INSTANCE", dataGenerator.getPathName());
				dataGenerator.addToFields(INSTANCE);
				INSTANCE.setStatic(true);
				INSTANCE.setFinal(true);
				INSTANCE.setVisibility(OJVisibilityKind.PUBLIC);
				INSTANCE.setInitExp("new " + dataGenerator.getName() + "()");
			}else{
				creator.initializeResultVariable("new " + pn1.getLast() + "()");
			}
			if(EmfPropertyUtil.getEndToComposite(nc) != null){
				creator.getBody().addToStatements("result.init(parent)");
			}
		}else{
			creator.initializeResultVariable("new " + creator.getReturnType().getLast() + "()");
		}
		return creator;
	}
	private void addGivenValue(OJAnnotatedOperation creator,PropertyMap map,Object value){
		if(map.isMany()){
			creator.getBody().addToStatements("result." + map.adder() + "(" + value + ")");
		}else{
			creator.getBody().addToStatements("result." + map.setter() + "(" + value + ")");
		}
	}
}
