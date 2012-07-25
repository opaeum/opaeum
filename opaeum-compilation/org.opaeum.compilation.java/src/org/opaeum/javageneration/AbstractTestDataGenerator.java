package org.opaeum.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.model.IEnumerationType;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;

public abstract class AbstractTestDataGenerator extends AbstractJavaProducingVisitor {
	public AbstractTestDataGenerator() {
		super();
	}


	protected List<BehavioredClassifier> getConcreteImplementations(Interface i) {
		return new ArrayList<BehavioredClassifier>(EmfClassifierUtil.getConcreteEntityImplementationsOf(i,getModelInScope()));
	}



	protected OJPathName getTestDataPath(Classifier child) {
		OJPathName testPath;
		if (child instanceof Interface) {
			Collection<BehavioredClassifier> implementors =getConcreteImplementations((Interface) child);
			Classifier next = implementors.iterator().next();
			NakedClassifierMap map = OJUtil.buildClassifierMap(next,(CollectionKind)null);
			testPath = map.javaTypePath().getCopy();
			testPath.replaceTail(getTestDataName(next));
		} else {
			NakedClassifierMap map = OJUtil.buildClassifierMap(child,(CollectionKind)null);
			testPath = map.javaTypePath().getCopy();
			testPath.replaceTail(getTestDataName(child));
		}
		return testPath;
	}


	protected abstract String getTestDataName(Classifier child);

	protected String calculateDefaultValue(OJAnnotatedClass test, OJBlock block, Property f) {
		String value = calculateDefaultValue(f);
		if (EmfClassifierUtil.isSimpleType(f.getType())) {
			DataType baseType = (DataType) f.getType();
			test.addToImports(OJUtil.classifierPathname(baseType));
			if (EmfClassifierUtil.hasStrategy(baseType,TestModelValueStrategy.class)) {
			}
		} else if (f.getType() instanceof IEnumerationType) {
			OJAnnotatedClass javaType = findJavaClass((Classifier) f.getType());
			test.addToImports(javaType.getPathName());
		} else if (getConcreteImplementations((Interface) f.getType()).size()>0) {
			return lookup(test, f);
		}
		return value;
	}

	public String calculateDefaultStringValue(Property f) {
		if (f.getType() instanceof IEnumerationType) {
			OJEnum javaType = (OJEnum) findJavaClass((Classifier) f.getType());
			if (javaType.getLiterals().size() > 0) {
				return javaType.getLiterals().get(0).getName();
			} else {
				return javaType.getName() + ".has no literals!!!!";
			}
		} else if (EmfClassifierUtil.isSimpleType(f.getType())) {
			DataType baseType = (DataType) f.getType();
			if (EmfClassifierUtil.hasStrategy(baseType,ConfigurableDataStrategy.class)) {
				return EmfClassifierUtil.getStrategy(baseType,ConfigurableDataStrategy.class).getDefaultStringValue();
			} else if (f.getType() instanceof PrimitiveType) {
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

	public String calculateDefaultValue(Property f) {
		double value = Math.random() * 123456;
	if (EmfClassifierUtil.isSimpleType(f.getType())) {
			DataType baseType = (DataType) f.getType();
			if (EmfClassifierUtil.hasStrategy(baseType,TestModelValueStrategy.class)) {
				return EmfClassifierUtil.getStrategy(baseType,TestModelValueStrategy.class).getDefaultStringValue(12341);
			} else if (workspace.getOpaeumLibrary().getDateType() != null && f.getType().conformsTo(workspace.getOpaeumLibrary().getDateType())) {
				String javaDate = OJUtil.classifierPathname(baseType).toJavaString();
				if (javaDate.equals("java.util.Date")) {
					return "new Date()";
				} else if (javaDate.equals("java.util.Calendar")) {
					return "Calendar.getInstance()";
				} else {
					return "new Date()";
				}
			} else if (f.getType() instanceof PrimitiveType) {
				PrimitiveType t = (PrimitiveType) f.getType();
				if (EmfClassifierUtil.comformsToLibraryType(t,"Integer")) {
					return "" + new Double(value).intValue();
				} else if (EmfClassifierUtil.comformsToLibraryType(t,"Real")) {
					return "" + new Double(value).floatValue();
				} else if (EmfClassifierUtil.comformsToLibraryType(t,"Boolean")) {
					return "" + ((Math.round(value) % 2) == 1);
				} else if (f.getName().equals("name")) {
					return "\"" + ((Classifier) f.getOwner()).getName() + value + "\"";
				} else {
					return "\"" + ((Classifier) f.getOwner()).getName() + "." + f.getName() + value + "\"";
				}
			}
			return "no TestValueStrategy found ";
		} else if (f.getType() instanceof IEnumerationType) {
			return f.getType().getName() + ".values()[0]";
		} else if (f.getType() instanceof Interface && getConcreteImplementations((Interface) f.getType()).size()>0) {
			return lookup(f);
		} else {
			return "\"" + ((Classifier) f.getOwner()).getName() + "::" + f.getName() + new Double(value).intValue() + "\"";
		}
	}

	protected String lookup(Property f) {
		OJPathName featureTest = getTestDataPath((Classifier) f.getType());
		if (new NakedStructuralFeatureMap(f).isOneToOne()) {
			return featureTest.getLast() + ".createNew()";
		} else {
			return featureTest.getLast() + ".getInstance()";
		}
	}

	protected String lookup(OJAnnotatedClass test, Property f) {
		OJPathName featureTest = getTestDataPath((Classifier) f.getType());
		test.addToImports(featureTest);
		return lookup(f);
	}


	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	public Collection<Classifier> getConcreteSubclassifiersOf(Classifier nakedBaseType){
		return (Collection)EmfClassifierUtil.getAllSubClassifiers(nakedBaseType, getModelInScope());
	}
}