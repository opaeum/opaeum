package org.opaeum.simulation.actions;

import java.text.ParseException;
import java.util.List;

import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.components.INakedComponent;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.usecases.INakedActor;
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
	@VisitBefore(match = {INakedInterface.class,INakedComponent.class,INakedEntity.class,INakedStructuredDataType.class,
			INakedAssociation.class,INakedActor.class},matchSubclasses = true)
	public void visitClassifier(INakedClassifier nc){
		List<InstanceSpecification> iss = getInstances(nc);
		if(iss.size() > 0){
			for(InstanceSpecification is:iss){
				OJAnnotatedClass dataGenerator = new OJAnnotatedClass(dataGeneratorName(nc, is).getLast());
				findOrCreatePackage(OJUtil.packagePathname(nc.getNameSpace())).addToClasses(dataGenerator);
				OJPathName pn;
				if(nc instanceof INakedStructuredDataType){
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
				populator.addToThrows(ParseException.class.getName());
				dataGenerator.addToOperations(populator);
				populator.getOwner().addToImports(ParseException.class.getName());
				if(nc instanceof INakedStructuredDataType){
					populator.addParam("in", new OJPathName(Object.class.getName()));
				}else{
					populator.addParam("in", new OJPathName(CompositionNode.class.getName()));
				}
				OJAnnotatedField instance = new OJAnnotatedField("instance", OJUtil.classifierPathname(nc));
				populator.getBody().addToLocals(instance);
				instance.setInitExp("(" + OJUtil.classifierPathname(nc).getLast() + ")in");
				for(Slot s:is.getSlots()){
					SimulatingSlot slot = (SimulatingSlot) s;
					if(slot.getDefiningFeature() != null){
						INakedProperty p = (INakedProperty) getNakedPeer(slot.getDefiningFeature());
						if(p.getNakedBaseType() instanceof ICompositionParticipant && !p.isComposite()
								&& !(p.getOtherEnd() != null && p.getOtherEnd().isComposite() && !p.isInverse())){
							NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(p);
							OJWhileStatement whileSize = buildLoopForSize(is, populator, slot, m);
							populator.getOwner().addToImports(m.javaBaseTypePath());
							String getReference = "(" + m.javaBaseType() + ")SimulationMetaData.getInstance().getEntityValueProvider(\""
									+ is.getQualifiedName() + "\",\"" + m.umlName() + "\").getNextReference()";
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
	private OJAnnotatedOperation buildCreator(INakedClassifier nc,OJAnnotatedClass dataGenerator,InstanceSpecification is){
		OJAnnotatedOperation creator = initializeCreator(nc, dataGenerator, is);
		for(Slot sl:is.getSlots()){
			SimulatingSlot slot = (SimulatingSlot) sl;
			if(slot.getDefiningFeature() != null){
				INakedProperty p = (INakedProperty) getNakedPeer(slot.getDefiningFeature());
				if(p.getNakedBaseType() instanceof INakedSimpleType || p.getNakedBaseType() instanceof INakedEnumeration){
					generateDataTypeValue(is, creator, slot, p);
					// Get from bucket
				}else if(p.getNakedBaseType() instanceof ICompositionParticipant && p.isComposite()){
					generateComplexStructuredData(dataGenerator, is, creator, slot, p);
				}else if(p.getNakedBaseType() instanceof INakedStructuredDataType){
					generateComplexStructuredData(dataGenerator, is, creator, slot, p);
				}
			}
		}
		if(nc instanceof ICompositionParticipant && ((ICompositionParticipant) nc).getEndToComposite() != null){
			creator.getBody().addToStatements("result.addToOwningObject()");
		}
		return creator;
	}
	private void generateComplexStructuredData(OJAnnotatedClass dataGenerator,InstanceSpecification is,OJAnnotatedOperation creator,
			SimulatingSlot slot,INakedProperty p){
		NakedStructuralFeatureMap m = OJUtil.buildStructuralFeatureMap(p);
		boolean hasEntityValueProvider = false;
		for(ValueSpecification vs:slot.getValues()){
			if(vs instanceof ContainedActualInstance){
				OJPathName generatorPathName = super.dataGeneratorName(p.getNakedBaseType(), ((ContainedActualInstance) vs).getContainedInstance());
				dataGenerator.addToImports(generatorPathName);
				if(p.getNakedBaseType() instanceof INakedStructuredDataType){
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
			createNewInstanceFromSimulation(is, creator, slot, m, p.getNakedBaseType() instanceof INakedStructuredDataType ? "Struct" : "Entity");
		}
	}
	private void createNewInstanceFromSimulation(InstanceSpecification is,OJAnnotatedOperation creator,SimulatingSlot slot,
			NakedStructuralFeatureMap m,String obsolete){
		OJWhileStatement whileSize = buildLoopForSize(is, creator, slot, m);
		creator.getOwner().addToImports(m.javaBaseTypePath());
		// Will be added by addToOwningObject
		if(m.getProperty().getNakedBaseType() instanceof INakedStructuredDataType){
			if(m.isMany()){
				whileSize.getBody().addToStatements(
						"result." + m.adder() + "((" + m.javaBaseType() + ")SimulationMetaData.getInstance().getStructValueProvider(\""
								+ is.getQualifiedName() + "\",\"" + m.umlName() + "\").createNewInstance())");
			}else{
				whileSize.getBody().addToStatements(
						"result." + m.setter() + "((" + m.javaBaseType() + ")SimulationMetaData.getInstance().getStructValueProvider(\""
								+ is.getQualifiedName() + "\",\"" + m.umlName() + "\").createNewInstance())");
			}
		}else{
			whileSize.getBody().addToStatements(
					"SimulationMetaData.getInstance().getEntityValueProvider(\"" + is.getQualifiedName() + "\",\"" + m.umlName()
							+ "\").createNewInstance(result)");
		}
	}
	private String calculateSize(InstanceSpecification is,SimulatingSlot slot,NakedStructuralFeatureMap m){
		String size;
		if(slot.getSizeDistribution() != null && m.isMany()){
			size = "SimulationMetaData.getInstance().getNextPropertySize(\"" + is.getQualifiedName() + "\",\"" + m.umlName() + "\")";
		}else{
			size = "1";
		}
		return size;
	}
	private void generateDataTypeValue(InstanceSpecification is,OJAnnotatedOperation creator,SimulatingSlot slot,INakedProperty p){
		boolean hasDoneSimulatedValue = false;
		for(ValueSpecification vs:slot.getValues()){
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
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
				creator.getOwner().addToImports(ParseException.class.getName());
				creator.addToThrows(ParseException.class.getName());
				addGivenValue(creator, map, "(" + map.javaBaseType() + ")new " + strat.getLast()
						+ "().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString(\"" + b.getStringValue() + "\")");
			}else if(vs instanceof LiteralString){
				LiteralString b = (LiteralString) vs;
				addGivenValue(creator, map, "\"" + b.stringValue() + "\"");
			}else if(vs instanceof InstanceValue){
				// Should be enum
				InstanceValue b = (InstanceValue) vs;
				if(b.getInstance() instanceof EnumerationLiteral){
					INakedEnumerationLiteral lit = (INakedEnumerationLiteral) getNakedPeer(b.getInstance());
					OJPathName pn = OJUtil.classifierPathname((INakedClassifier) lit.getEnumeration());
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
				String newVal = "(" + map.javaBaseType() + ")SimulationMetaData.getInstance().getNextValueForProperty(\"" + is.getQualifiedName()
						+ "\",\"" + slot.getDefiningFeature().getName() + "\")";
				if(map.isMany()){
					whileSize.getBody().addToStatements("result." + map.adder() + "(" + newVal + ")");
				}else{
					whileSize.getBody().addToStatements("result." + map.setter() + "(" + newVal + ")");
				}
			}
		}
	}
	private OJWhileStatement buildLoopForSize(InstanceSpecification is,OJAnnotatedOperation creator,SimulatingSlot slot,
			NakedStructuralFeatureMap map){
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
	private OJAnnotatedOperation initializeCreator(INakedClassifier nc,OJAnnotatedClass dataGenerator,InstanceSpecification is){
		String name = "createNewObject";
		OJPathName pn1 = OJUtil.classifierPathname(nc);
		OJAnnotatedOperation creator = new OJAnnotatedOperation(name);
		dataGenerator.addToOperations(creator);
		creator.addToThrows(ParseException.class.getName());
		creator.setReturnType(pn1);
		if(nc instanceof ICompositionParticipant){
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
			if(((ICompositionParticipant) nc).getEndToComposite() != null){
				creator.getBody().addToStatements("result.init(parent)");
			}
		}else{
			creator.initializeResultVariable("new " + creator.getReturnType().getLast() + "()");
		}
		return creator;
	}
	private void addGivenValue(OJAnnotatedOperation creator,NakedStructuralFeatureMap map,Object value){
		if(map.isMany()){
			creator.getBody().addToStatements("result." + map.adder() + "(" + value + ")");
		}else{
			creator.getBody().addToStatements("result." + map.setter() + "(" + value + ")");
		}
	}
}
