package org.opaeum.simulation.actions;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodels.simulation.simulation.ContainedActualInstance;
import org.opaeum.metamodels.simulation.simulation.ExponentialDistribution;
import org.opaeum.metamodels.simulation.simulation.NormalDistribution;
import org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution;
import org.opaeum.metamodels.simulation.simulation.NumericValueDistribution;
import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.UniformDistribution;
import org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue;
import org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;
import org.opaeum.metamodels.simulation.simulation.WeightedStringValue;

public class SimulationRunnerGenerator extends AbstractSimulationCodeGenerator{
	@VisitBefore(matchSubclasses = true)
	public void visitWorkspace(INakedModelWorkspace nmws){
		OJPackage pkg = findOrCreatePackage(UtilityCreator.getUtilPathName());
		OJAnnotatedClass clss = new OJAnnotatedClass(nmws.getMappingInfo().getJavaName().getCapped() + "DataGenerator");
		pkg.addToClasses(clss);
		createTextPath(clss, SimulationSourceFolderId.GEN_SRC);
		clss.addToImports("org.opaeum.simulation.SimulationMetaData");
		OJAnnotatedOperation main = new OJAnnotatedOperation("main");
		main.addParam("args", new OJPathName("String[]"));
		main.setStatic(true);
		clss.addToOperations(main);
		TreeIterator<EObject> eAllContents = simulationModel.eAllContents();
		int i = 0;
		OJAnnotatedOperation registerABunch = null;
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof SimulatingSlot){
				if(i % 200 == 0){
					registerABunch = new OJAnnotatedOperation("register" + i / 200);
					clss.addToOperations(registerABunch);
					registerABunch.setStatic(true);
					main.getBody().addToStatements(registerABunch.getName() + "()");
				}
				i++;
				processSlot(clss, registerABunch, (SimulatingSlot) eObject);
			}
		}
		if(getLibrary().getBusinessNetwork() != null && getInstances(getLibrary().getBusinessNetwork()).size() > 0){
			InstanceSpecification bni = getInstances(getLibrary().getBusinessNetwork()).get(0);
			OJPathName bnpn = OJUtil.classifierPathname(getLibrary().getBusinessNetwork());
			OJBlock registerBlock = new OJBlock();
			main.getBody().addToStatements(registerBlock);
			registerBlock.addToStatements("SimulationMetaData.getInstance().registerEntityInstanceSimulation(\"" + bni.getQualifiedName()
					+ "\", \"NONE\"," + dataGeneratorName(getLibrary().getBusinessNetwork(), bni) + ".INSTANCE," + 1 + ")");
			OJBlock intializeBlock = new OJBlock();
			main.getBody().addToStatements(intializeBlock);
			OJAnnotatedField bnField = new OJAnnotatedField("businessNetwork", bnpn);
			bnField.setInitExp("(" + bnpn.getLast() + ")" + dataGeneratorName(getLibrary().getBusinessNetwork(), bni)
					+ ".INSTANCE.generateInstance(null)");
			intializeBlock.addToLocals(bnField);
			if(nmws.getApplicationRoot() != null){
				OJPathName bcpn = OJUtil.classifierPathname(nmws.getApplicationRoot());
				OJAnnotatedField rootField = new OJAnnotatedField("businessCollaboration", bcpn);
				rootField.setInitExp("new " + bcpn.getLast() + "(businessNetwork)");
				intializeBlock.addToLocals(rootField);
				for(INakedProperty p:nmws.getApplicationRoot().getEffectiveAttributes()){
					if(!p.isDerived() && p.isComposite()){
						for(InstanceSpecification is:getInstances(p.getNakedBaseType())){
							registerBlock.addToStatements("SimulationMetaData.getInstance().registerEntityInstanceSimulation(\"" + is.getQualifiedName()
									+ "\", \"NONE\"," + dataGeneratorName(p.getNakedBaseType(), is) + ".INSTANCE," + 1 + ")");
							intializeBlock.addToStatements(dataGeneratorName(p.getNakedBaseType(), is)
									+ ".INSTANCE.generateInstance(businessCollaboration)");
						}
					}
				}
			}
			clss.addToImports("org.opaeum.runtime.environment.Environment");
			main.getBody().addToStatements("Environment.getInstance().getUmtPersistence().beginTransaction()");
			main.getBody().addToStatements("Environment.getInstance().getUmtPersistence().persist(businessNetwork)");
			main.getBody().addToStatements("Environment.getInstance().getUmtPersistence().commitTransaction()");
			main.getBody().addToStatements("Environment.getInstance().getUmtPersistence().beginTransaction()");
			main.getBody().addToStatements("SimulationMetaData.getInstance().populateReferences()");
			main.getBody().addToStatements("Environment.getInstance().getUmtPersistence().commitTransaction()");
		}
	}
	private void processSlot(OJAnnotatedClass clss,OJAnnotatedOperation main,SimulatingSlot slot){
		INakedClassifier c = (INakedClassifier) getNakedPeer(slot.getOwningInstance().getClassifiers().get(0));
		OJPathName pn = OJUtil.classifierPathname(c);
		if(slot.getSizeDistribution() != null){
			registerNumberValueDistribution(main, slot, slot.getSizeDistribution(), "registerPropertySizeGenerator");
		}
		for(ValueSpecification vs:slot.getValues()){
			if(vs instanceof WeightedBooleanValue){
				WeightedBooleanValue wbv = (WeightedBooleanValue) vs;
				main.getBody().addToStatements(buildRegistration(slot, pn, wbv.isValue(), wbv.getWeight()));
			}else if(vs instanceof WeightedEnumLiteralValue){
				WeightedEnumLiteralValue welv = (WeightedEnumLiteralValue) vs;
				INakedEnumerationLiteral literal = (INakedEnumerationLiteral) getNakedPeer(welv.getLiteral());
				OJPathName epn = OJUtil.classifierPathname((INakedClassifier) literal.getEnumeration());
				clss.addToImports(epn);
				main.getBody()
						.addToStatements(buildRegistration(slot, pn, epn.getLast() + "." + literal.getName().toUpperCase(), welv.getWeight()));
			}else if(vs instanceof WeightedStringValue){
				WeightedStringValue wbv = (WeightedStringValue) vs;
				main.getBody().addToStatements(buildRegistration(slot, pn, "\"" + wbv.getValue() + "\"", wbv.getWeight()));
			}else if(vs instanceof NumericValueDistribution){
				INakedClassifier type = (INakedClassifier) getNakedPeer(slot.getDefiningFeature().getType());
				if(type.conformsTo(workspace.getOpaeumLibrary().getIntegerType())){
					registerNumberValueDistribution(main, slot, vs, "registerIntegerValueDistribution");
				}else{
					registerNumberValueDistribution(main, slot, vs, "registerRealValueDistribution");
				}
			}else{
				if(vs instanceof WeightedInstanceValue){
					WeightedInstanceValue wiv = (WeightedInstanceValue) vs;
					InstanceSpecification instance = wiv.getInstance();
					if(instance != null){
						INakedClassifier nakedPeer = (INakedClassifier) getNakedPeer(instance.getClassifiers().get(0));
						String methodName;
						if(nakedPeer instanceof INakedStructuredDataType){
							methodName = "registerStructInstanceSimulation";
						}else{
							methodName = "registerEntityInstanceSimulation";
						}
						main.getBody().addToStatements(
								"SimulationMetaData.getInstance()." + methodName + "(\"" + slot.getOwningInstance().getQualifiedName() + "\", \""
										+ slot.getDefiningFeature().getName() + "\",new " + dataGeneratorName(nakedPeer, instance) + "(),"
										+ (wiv.getWeight() == null ? 1 : wiv.getWeight()) + ")");
					}
				}else if(vs instanceof ContainedActualInstance){
					InstanceSpecification instance = ((ContainedActualInstance) vs).getContainedInstance();
					if(instance != null){
						INakedClassifier nakedPeer = (INakedClassifier) getNakedPeer(instance.getClassifiers().get(0));
						String methodName;
						if(nakedPeer instanceof INakedStructuredDataType){
							methodName = "registerStructInstanceSimulation";
						}else{
							methodName = "registerEntityInstanceSimulation";
						}
						main.getBody().addToStatements(
								"SimulationMetaData.getInstance()." + methodName + "(\"" + slot.getOwningInstance().getQualifiedName() + "\", \""
										+ slot.getDefiningFeature().getName() + "\"," + dataGeneratorName(nakedPeer, instance) + ".INSTANCE," + 1 + ")");
					}
				}
			}
		}
	}
	private void registerNumberValueDistribution(OJAnnotatedOperation main,Slot slot,ValueSpecification vs,String methodName){
		if(vs instanceof NumberRangeDistribution){
			NumberRangeDistribution nrd = (NumberRangeDistribution) vs;
			main.getOwner().addToImports("org.opaeum.simulation.NumberRange");
			main.getBody().addToStatements(
					"SimulationMetaData.getInstance()." + methodName + "(\"" + slot.getOwningInstance().getQualifiedName() + "\", \""
							+ slot.getDefiningFeature().getName() + "\",new NumberRange(" + nrd.getLowerValue() + "," + nrd.getUpperValue() + ","
							+ nrd.getWeight() + "))");
		}else if(vs instanceof NormalDistribution){
			NormalDistribution nd = (NormalDistribution) vs;
			main.getOwner().addToImports("org.apache.commons.math3.distribution.NormalDistribution");
			main.getBody().addToStatements(
					"SimulationMetaData.getInstance()." + methodName + "(\"" + slot.getOwningInstance().getQualifiedName() + "\", \""
							+ slot.getDefiningFeature().getName() + "\",new NormalDistribution(" + nd.getMean() + "," + nd.getStandardDeviation() + "))");
		}else if(vs instanceof ExponentialDistribution){
			ExponentialDistribution ed = (ExponentialDistribution) vs;
			main.getOwner().addToImports("org.apache.commons.math3.distribution.ExponentialDistribution");
			main.getBody().addToStatements(
					"SimulationMetaData.getInstance()." + methodName + "(\"" + slot.getOwningInstance().getQualifiedName() + "\", \""
							+ slot.getDefiningFeature().getName() + "\",new ExponentialDistribution(" + ed.getMean() + "))");
		}else if(vs instanceof UniformDistribution){
			UniformDistribution ud = (UniformDistribution) vs;
			main.getOwner().addToImports("org.apache.commons.math3.distribution.UniformRealDistribution");
			main.getBody().addToStatements(
					"SimulationMetaData.getInstance()." + methodName + "(\"" + slot.getOwningInstance().getQualifiedName() + "\", \""
							+ slot.getDefiningFeature().getName() + "\",new UniformRealDistribution(" + ud.getLowerLimit() + "," + ud.getUpperLimit()
							+ "))");
		}
	}
	private String buildRegistration(Slot slot,OJPathName pn,Object value,Double weight){
		return "SimulationMetaData.getInstance().registerPropertyValueDistributionRatio(\"" + slot.getOwningInstance().getQualifiedName()
				+ "\", \"" + slot.getDefiningFeature().getName() + "\"," + value + "," + weight + ")";
	}
}
