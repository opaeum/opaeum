package net.sf.nakeduml.javageneration;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
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

	protected OJPathName getTestDataPath(INakedClassifier child) {
		OJPathName testPath;
		if (child instanceof INakedInterface) {
			Collection<INakedClassifier> implementors = new ArrayList<INakedClassifier>();
			addImplementors((INakedInterface) child, implementors);
			INakedClassifier next = implementors.iterator().next();
			OJClass javaChild = findJavaClass(next);
			testPath = javaChild.getPathName().getCopy();
			testPath.replaceTail(getTestDataName(next));
		} else {
			OJClass javaChild = findJavaClass(child);
			testPath = javaChild.getPathName().getCopy();
			testPath.replaceTail(getTestDataName(child));
		}
		return testPath;
	}

	private void addImplementors(INakedInterface child, Collection<INakedClassifier> implementors) {
		implementors.addAll(child.getImplementingClassifiers());
		for(IClassifier c:child.getSubClasses()){
			if(c instanceof INakedInterface){
				addImplementors((INakedInterface) c, implementors);
			}
		}
	}

	protected abstract String getTestDataName(INakedClassifier child);

	protected String calculateDefaultValue(OJAnnotatedClass test, OJBlock block, INakedProperty f) {
		String value  = calculateDefaultValue(f);
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