package org.opeum.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.opeum.javageneration.composition.ConfigurableDataStrategy;
import org.opeum.javageneration.maps.NakedClassifierMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.GeneralizationUtil;
import org.opeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedPrimitiveType;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import nl.klasse.octopus.model.IEnumerationType;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJEnum;

public abstract class AbstractTestDataGenerator extends AbstractJavaProducingVisitor {
	public AbstractTestDataGenerator() {
		super();
	}


	protected List<INakedBehavioredClassifier> getConcreteImplementations(INakedInterface entity) {
		return new ArrayList<INakedBehavioredClassifier>(GeneralizationUtil.getConcreteEntityImplementationsOf(entity,getModelInScope()));
	}



	protected OJPathName getTestDataPath(INakedClassifier child) {
		OJPathName testPath;
		if (child instanceof INakedInterface) {
			Collection<INakedBehavioredClassifier> implementors =getConcreteImplementations((INakedInterface) child);
			INakedClassifier next = implementors.iterator().next();
			NakedClassifierMap map = new NakedClassifierMap(next);
			testPath = map.javaTypePath().getCopy();
			testPath.replaceTail(getTestDataName(next));
		} else {
			NakedClassifierMap map = new NakedClassifierMap(child);
			testPath = map.javaTypePath().getCopy();
			testPath.replaceTail(getTestDataName(child));
		}
		return testPath;
	}


	protected abstract String getTestDataName(INakedClassifier child);

	protected String calculateDefaultValue(OJAnnotatedClass test, OJBlock block, INakedProperty f) {
		String value = calculateDefaultValue(f);
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
		if (f.getNakedBaseType() instanceof INakedSimpleType) {
			INakedSimpleType baseType = (INakedSimpleType) f.getNakedBaseType();
			test.addToImports(new OJPathName((baseType).getMappingInfo().getQualifiedJavaName()));
			if (baseType.hasStrategy(TestValueStrategy.class)) {
				baseType.getStrategy(TestValueStrategy.class).transformClass(test, block);
			}
		} else if (f.getNakedBaseType() instanceof IEnumerationType) {
			OJAnnotatedClass javaType = findJavaClass(f.getNakedBaseType());
			test.addToImports(javaType.getPathName());
		} else if (getConcreteImplementations((INakedInterface) f.getNakedBaseType()).size()>0) {
			return lookup(test, f);
		}
		return value;
	}

	public String calculateDefaultStringValue(INakedProperty f) {
		if (f.getNakedBaseType() instanceof IEnumerationType) {
			OJEnum javaType = (OJEnum) findJavaClass(f.getNakedBaseType());
			if (javaType.getLiterals().size() > 0) {
				return javaType.getLiterals().get(0).getName();
			} else {
				return javaType.getName() + ".has no literals!!!!";
			}
		} else if (f.getNakedBaseType() instanceof INakedSimpleType) {
			INakedSimpleType baseType = (INakedSimpleType) f.getNakedBaseType();
			if (baseType.hasStrategy(ConfigurableDataStrategy.class)) {
				return baseType.getStrategy(ConfigurableDataStrategy.class).getDefaultStringValue();
			} else if (f.getNakedBaseType() instanceof INakedPrimitiveType) {
				String calculateDefaultValue = calculateDefaultValue(f);
				if (calculateDefaultValue.startsWith("\"") && calculateDefaultValue.endsWith("\"")) {
					calculateDefaultValue = calculateDefaultValue.substring(1, calculateDefaultValue.length() - 1);
				}
				return calculateDefaultValue;
			} else {
				return "no ConfigurableDataStrategy strategy!!!";
			}
		}
		return "BLASDFASDFadsf";
	}

	public String calculateDefaultValue(INakedProperty f) {
		double value = Math.random() * 123456;
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
		if (f.getNakedBaseType() instanceof INakedSimpleType) {
			INakedSimpleType baseType = (INakedSimpleType) f.getNakedBaseType();
			if (baseType.hasStrategy(TestValueStrategy.class)) {
				return baseType.getStrategy(TestValueStrategy.class).getDefaultValue();
			} else if (workspace.getOpeumLibrary().getDateType() != null && f.getNakedBaseType().conformsTo(workspace.getOpeumLibrary().getDateType())) {
				String javaDate = baseType.getMappingInfo().getQualifiedJavaName();
				if (javaDate.equals("java.util.Date")) {
					return "new Date()";
				} else if (javaDate.equals("java.util.Calendar")) {
					return "Calendar.getInstance()";
				} else {
					return "new Date()";
				}
			} else if (f.getNakedBaseType() instanceof INakedPrimitiveType) {
				INakedPrimitiveType t = (INakedPrimitiveType) f.getNakedBaseType();
				if (t.getOclType().getName().equals("Integer")) {
					return "" + new Double(value).intValue();
				} else if (t.getOclType().getName().equals("Real")) {
					return "" + new Double(value).floatValue();
				} else if (t.getOclType().getName().equals("Boolean")) {
					return "" + ((Math.round(value) % 2) == 1);
				} else if (f.getName().equals("name")) {
					return "\"" + f.getOwner().getName() + value + "\"";
				} else {
					return "\"" + f.getOwner().getName() + "." + f.getName() + value + "\"";
				}
			}
			return "no TestValueStrategy found ";
		} else if (f.getNakedBaseType() instanceof IEnumerationType) {
			return f.getNakedBaseType().getName() + ".values()[0]";
		} else if (f.getNakedBaseType() instanceof INakedInterface && getConcreteImplementations((INakedInterface) f.getNakedBaseType()).size()>0) {
			return lookup(f);
		} else {
			return "\"" + f.getOwner().getName() + "::" + f.getName() + new Double(value).intValue() + "\"";
		}
	}

	protected String lookup(INakedProperty f) {
		OJPathName featureTest = getTestDataPath(f.getNakedBaseType());
		if (new NakedStructuralFeatureMap(f).isOneToOne()) {
			return featureTest.getLast() + ".createNew()";
		} else {
			return featureTest.getLast() + ".getInstance()";
		}
	}

	protected String lookup(OJAnnotatedClass test, INakedProperty f) {
		OJPathName featureTest = getTestDataPath(f.getNakedBaseType());
		test.addToImports(featureTest);
		return lookup(f);
	}


	public Collection<INakedClassifier> getConcreteSubclassifiersOf(INakedClassifier nakedBaseType){
		return (Collection)GeneralizationUtil.getAllSubClassifiers(nakedBaseType, getModelInScope());
	}
}