package net.sf.nakeduml.javageneration.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import net.sf.nakeduml.name.NameConverter;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;
import nl.klasse.tools.common.StringHelpers;

public class OJUtil {
	private static final Set<String> BUILT_IN_ATTRIBUTES = new HashSet<String>();
	static {
		BUILT_IN_ATTRIBUTES.add("now");
		BUILT_IN_ATTRIBUTES.add("currentUser");
		BUILT_IN_ATTRIBUTES.add("today");
	}

	public static boolean isBuiltIn(INakedTypedElement f) {
		return BUILT_IN_ATTRIBUTES.contains(f.getName());
	}

	public static OJAnnotatedOperation buildMain(OJAnnotatedClass ojClass) {
		OJAnnotatedOperation main = new OJAnnotatedOperation();
		main.setName("main");
		main.setStatic(true);
		main.addParam("args", new OJPathName("String[]"));
		ojClass.addToOperations(main);
		return main;
	}

	public static NakedStructuralFeatureMap buildStructuralFeatureMap(INakedClassifier owner, INakedTypedElement typedAndOrdered) {
		NakedStructuralFeatureMap linkedParameter;
		if (typedAndOrdered instanceof INakedProperty) {
			linkedParameter = OJUtil.buildStructuralFeatureMap((INakedProperty) typedAndOrdered);
		} else {
			linkedParameter = new NakedStructuralFeatureMap(new TypedElementPropertyBridge(owner, typedAndOrdered));
		}
		return linkedParameter;
	}

	public static NakedStructuralFeatureMap buildStructuralFeatureMap(INakedProperty sf) {
		return new NakedStructuralFeatureMap(sf);
	}

	public static NakedStructuralFeatureMap buildAssociationClassMap(INakedProperty sf, IOclLibrary l) {
		INakedAssociationClass ac = (INakedAssociationClass) sf.getAssociation();
		class NakedAssociationClassPropertyMap extends NakedStructuralFeatureMap {
			private INakedAssociationClass assocClass;

			public NakedAssociationClassPropertyMap(INakedProperty sf, INakedAssociationClass baseType, IClassifier type) {
				super(sf);
				this.assocClass = baseType;
				baseTypeMap = new NakedClassifierMap(baseType);
				featureTypeMap = new NakedClassifierMap(type);
			}

			public String umlName() {
				return buildAssocEndName(assocClass, getProperty());
			}

			protected boolean otherEndIsOne() {
				return true;
			}

			public String getter() {
				String name = buildAssocEndName(assocClass, getProperty());
				return "get" + StringHelpers.firstCharToUpper(name);
			}

			public String setter() {
				String name = buildAssocEndName(assocClass, getProperty());
				return "set" + StringHelpers.firstCharToUpper(name);
			}

			public String adder() {
				String name = buildAssocEndName(assocClass, getProperty());
				return "z_internalAddTo" + StringHelpers.firstCharToUpper(name);
			}

			public String remover() {
				String name = buildAssocEndName(assocClass, getProperty());
				return "z_internalRemoveFrom" + StringHelpers.firstCharToUpper(name);
			}

			public String buildAssocEndName(IAssociationClass assoc, INakedProperty end) {
				String name = assoc.getName();
				IAssociationEnd otherEnd = assoc.getOtherEnd(end);
				boolean useNameExtension = (end.getNakedBaseType() == otherEnd.getBaseType());
				if (useNameExtension) {
					name = assoc.getName() + "_" + otherEnd.getName();
				}
				return name;
			}
		}
		;
		if (sf.getType() instanceof ICollectionType) {
			return new NakedAssociationClassPropertyMap(sf, ac, l.lookupCollectionType(((ICollectionType) sf.getType()).getMetaType(), ac));
		} else {
			return new NakedAssociationClassPropertyMap(sf, ac, ac);
		}
	}

	public static NakedStructuralFeatureMap buildStructuralFeatureMap(INakedCallAction action, IOclLibrary lib) {
		ActionFeatureBridge bridge = new ActionFeatureBridge(action);
		if (action.getTargetElement() != null) {
			IClassifier type = action.getTargetElement().getType();
			if (type instanceof StdlibCollectionType) {
				bridge.setType(lib.lookupCollectionType(((StdlibCollectionType) type).getMetaType(), bridge.getNakedBaseType()));
			} else {
				bridge.setType(bridge.getNakedBaseType());
			}
		} else {
			bridge.setType(bridge.getNakedBaseType());
		}
		return new NakedStructuralFeatureMap(bridge);
	}

	private static void addParentsToPath(INakedNameSpace c, OJPathName path) {
		INakedNameSpace parent = c.getParent();
		if (parent != null) {
			addParentsToPath(parent, path);
			path.addToNames(parent.getName().toLowerCase());
		}
	}

	/**
	 * A NakedUml specific algorithm that takes mapped implementation types into
	 * account as well as classifier nesting. With UML classifier nesting a
	 * package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static OJPathName packagePathname(INakedNameSpace p) {
		OJPathName path = new OJPathName();
		addParentsToPath(p, path);
		path.addToNames(p.getName().toLowerCase());
		return path;
	}

	/**
	 * A NakedUml specific algorithm that takes mapped implementation types into
	 * account as well as classifier nesting. With UML classifier nesting a
	 * package is generated for every classifier with nested classifiers
	 * 
	 * @param classifier
	 * @return
	 */
	public static OJPathName classifierPathname(INakedClassifier classifier) {
		if (classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null) {
			return new OJPathName((classifier).getMappedImplementationType());
		} else {
			OJPathName path = packagePathname(classifier.getNameSpace());
			path.addToNames(classifier.getName());
			return path;
		}
	}

	public static final OJOperation addMethod(OJClass theClass, String name, String type, String expression) {
		OJOperation get = OJUtil.findOperation(theClass, name);
		if (get == null) {
			get = new OJAnnotatedOperation();
			theClass.addToOperations(get);
		} else {
			get.setBody(new OJBlock());
		}
		get.setName(name);
		get.setReturnType(new OJPathName(type));
		get.getBody().addToStatements("return " + expression);
		return get;
	}

	public static void addConstructor(OJClass ojClass, OJField... params) {
		OJConstructor constructor = new OJConstructor();
		for (OJField ojField : params) {
			constructor.addParam(ojField.getName(), ojField.getType());
			OJSimpleStatement setField = new OJSimpleStatement("this." + ojField.getName() + " = " + ojField.getName());
			constructor.getBody().addToStatements(setField);
		}
		constructor.setOwningClass(ojClass);
	}

	public static OJAnnotatedField addProperty(OJClassifier ojClass, String name, OJPathName type, boolean withBody) {
		ojClass.addToImports(type);
		OJOperation set = new OJAnnotatedOperation();
		String capped = NameConverter.capitalize(name);
		set.setName("set" + capped);
		set.addParam(name, type);
		set.setBody(new OJBlock());
		ojClass.addToOperations(set);
		OJOperation get = new OJAnnotatedOperation();
		get.setName("get" + capped);
		get.setReturnType(type);
		get.setBody(new OJBlock());
		ojClass.addToOperations(get);
		if (withBody) {
			set.getBody().addToStatements("this." + name + "=" + name);
			get.getBody().addToStatements("return " + name);
			OJAnnotatedField field = new OJAnnotatedField();
			field.setName(name);
			field.setType(type);
			((OJClass) ojClass).addToFields(field);
			return field;
		}
		return null;
	}

	// TODO move to annotated class
	public static OJOperation findOperation(OJClass theClass, String name) {
		// if (theClass.getName().equals("Network")) {
		// StringBuffer sb = new StringBuffer();
		// StackTraceElement[] stackTrace = Thread.currentThread()
		// .getStackTrace();
		// for (int i = 0; i < stackTrace.length; i++) {
		// sb.append(stackTrace[i].toString());
		// }
		// System.out.println("called " + sb.toString());
		// }
		Iterator iter = theClass.getOperations().iterator();
		while (iter.hasNext()) {
			OJOperation o = (OJOperation) iter.next();
			if (o.getName().equals(name)) {
				return o;
			}
		}
		return null;
	}

	public static void addFailedConstraints(OJOperation execute) {
		String failedConstraints = UtilityCreator.getUtilPathName() + ".FailedConstraintsException";
		execute.getOwner().addToImports(failedConstraints);
		execute.addToThrows(failedConstraints);
	}

	/**
	 * Some classifiers in UML would not necessarily be generated as Java
	 * classes. Returns false for NakedBehaviors that have one or less resulting
	 * parameters
	 * 
	 */
	public static boolean hasOJClass(INakedClassifier c) {
		if (c instanceof INakedClassifier) {
			INakedClassifier nc = c;
			if (nc.getCodeGenerationStrategy().isNone()) {
				return false;
			} else if (c instanceof INakedBehavior) {
				return BehaviorUtil.hasExecutionInstance((IParameterOwner) c);
			} else if (c instanceof INakedAssociation) {
				return ((INakedAssociation) c).isClass();
			} else if (c instanceof MessageStructureImpl) {
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

}
