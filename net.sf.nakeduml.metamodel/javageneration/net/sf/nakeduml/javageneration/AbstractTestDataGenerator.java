package net.sf.nakeduml.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import net.sf.nakeduml.javageneration.composition.ConfigurableDataStrategy;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IEnumerationType;

public abstract class AbstractTestDataGenerator extends AbstractJavaProducingVisitor {
	public AbstractTestDataGenerator() {
		super();
	}

	protected boolean isHierarchical(INakedEntity c) {
		for (INakedProperty a : c.getEffectiveAttributes()) {
			if (isHierarchical(c, a)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInHierarchical(INakedEntity c) {
		for (INakedProperty a : c.getEffectiveAttributes()) {
			if (isHierarchical(c, a)) {
				return true;
			}
		}
		if (c.getEndToComposite() != null && isInHierarchical((INakedEntity) c.getEndToComposite().getBaseType())) {
			return true;
		} else {
			return false;
		}
	}

	protected List<INakedEntity> getConcreteImplementations(IClassifier entity) {
		if (entity instanceof INakedInterface) {
			return getConcreteImplementations((INakedInterface)entity);
		} else {
			List<INakedEntity> result = new ArrayList<INakedEntity>();
			Collection<IClassifier> subs = entity.getSubClasses();
			for (IClassifier sub : subs) {
				if (!sub.equals(entity)) {
					if (sub instanceof INakedEntity) {
						result.add((INakedEntity) sub);
					} else {
						System.out.println("sssssssssssssss");
					}
				}
			}
			return result;
		}
	}

	protected List<INakedEntity> getConcreteImplementations(INakedInterface entity) {
		List<INakedEntity> result = new ArrayList<INakedEntity>();
		Collection<INakedClassifier> subs = entity.getImplementingClassifiers();
		for (IClassifier sub : subs) {
			if (!sub.equals(entity)) {
				result.add((INakedEntity) sub);
			}
		}
		return result;
	}

	protected List<INakedEntity> getHierarchicalRoots(INakedEntity entity) {
		List<INakedEntity> result = new ArrayList<INakedEntity>();
		List<IClassifier> generalizations = entity.getGeneralizations();
		for (IClassifier generalization : generalizations) {
			INakedEntity gen = (INakedEntity) generalization;
			Collection<IClassifier> subs = gen.getSubClasses();
			for (IClassifier sub : subs) {
				if (!sub.equals(entity)) {
					result.add((INakedEntity) sub);
				}
			}
		}
		return result;
	}

	protected boolean isHierarchical(INakedEntity c, INakedProperty p) {
		if (p.isComposite() && p.getNakedBaseType().equals(c)) {
			return true;
		}
		return false;
	}

	protected void createHierarchicalEntries(INakedEntity entity, List<StringBuilder> theList, StringBuilder currentPath) {
		if (currentPath.toString().isEmpty()) {
			currentPath.append(entity.getName());
		} else {
			currentPath.append("." + entity.getName());
		}
		if (isHierarchical(entity)) {
			theList.remove(currentPath);
			List<INakedEntity> hierarchicalRoots = getHierarchicalRoots(entity);
			for (INakedEntity hierarchicalEntityRoot : hierarchicalRoots) {
				StringBuilder alternativePath = new StringBuilder(currentPath.toString());
				theList.add(alternativePath);
				createHierarchicalEntries(hierarchicalEntityRoot, theList, alternativePath);
			}
		} else if (isCompositeParentAbstract(entity)) {
			theList.remove(currentPath);
			List<INakedEntity> concreteImpls = getConcreteImplementations((INakedEntity) entity.getEndToComposite().getBaseType());
			for (INakedEntity concreteImpl : concreteImpls) {
				StringBuilder alternativePath = new StringBuilder(currentPath.toString());
				theList.add(alternativePath);
				createHierarchicalEntries(concreteImpl, theList, alternativePath);
			}
			// } else if (compositeOwnersInverseIsInterface(entity)) {
			// NakedStructuralFeatureMap map = new
			// NakedStructuralFeatureMap(entity.getEndToComposite());
			// if (map.isOne()) {
			// theList.remove(currentPath);
			// List<INakedEntity> concreteImpls =
			// getConcreteImplementations((INakedEntity)
			// entity.getEndToComposite().getBaseType());
			// for (INakedEntity concreteImpl : concreteImpls) {
			// StringBuilder alternativePath = new
			// StringBuilder(currentPath.toString());
			// theList.add(alternativePath);
			// createHierarchicalEntries(concreteImpl, theList,
			// alternativePath);
			// }
			// }
		} else {
			if (entity.getEndToComposite() != null) {
				createHierarchicalEntries((INakedEntity) entity.getEndToComposite().getBaseType(), theList, currentPath);
			}
		}

	}

	private boolean isCompositeParentAbstract(INakedEntity entity) {
		if (entity.getEndToComposite() == null) {
			return false;
		} else {
			return entity.getEndToComposite().getBaseType().getIsAbstract();
		}
	}

	protected OJPathName getTestDataPath(INakedClassifier child) {
		OJPathName testPath;
		if (child instanceof INakedInterface) {
			Collection<INakedClassifier> implementors = new ArrayList<INakedClassifier>();
			addImplementors((INakedInterface) child, implementors);
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

	private void addImplementors(INakedInterface child, Collection<INakedClassifier> implementors) {
		implementors.addAll(child.getImplementingClassifiers());
		for (IClassifier c : child.getSubClasses()) {
			if (c instanceof INakedInterface) {
				addImplementors((INakedInterface) c, implementors);
			}
		}
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
		} else if (map.couldBasetypeBePersistent()) {
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
			} else if (workspace.getMappedTypes().getDateType() != null && f.getNakedBaseType().conformsTo(workspace.getMappedTypes().getDateType())) {
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
		} else if (map.couldBasetypeBePersistent()) {
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
}