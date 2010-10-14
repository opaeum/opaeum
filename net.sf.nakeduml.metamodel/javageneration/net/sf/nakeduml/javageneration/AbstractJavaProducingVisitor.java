package net.sf.nakeduml.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.linkage.InterfaceUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

public class AbstractJavaProducingVisitor extends NakedElementOwnerVisitor {
	protected static final String SINGLE_TABLE_INHERITANCE = "SingleTableInheritance";
	protected OJPackage javaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;
	protected INakedModelWorkspace workspace;

	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace) {
		this.javaModel = javaModel;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.workspace = workspace;
	}

	public void createTextPath(OJClassifier c, String outputRoot) {
		try {
			TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(outputRoot);
			List<String> names = c.getPathName().getHead().getNames();
			names.add(c.getName() + ".java");
			or.findOrCreateTextFile(names, new JavaTextSource(c));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void createTextPathIfRequired(OJAnnotatedPackage p, String outputRoot) {
		TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(outputRoot);
		List<String> names = p.getPathName().getNames();
		names.add("package-info.java");
		or.findOrCreateTextFile(names, new JavaTextSource(p));
	}

	protected final OJPackage findOrCreatePackage(OJPathName packageName) {
		OJPackage parent = this.javaModel;
		OJPackage child = null;
		Iterator iter = packageName.getNames().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			child = parent.findPackage(new OJPathName(name));
			if (child == null) {
				child = new OJPackage();
				child.setName(name);
				parent.addToSubpackages(child);
			}
			parent = child;
		}
		return child;
	}

	protected OJAnnotatedClass findJavaClass(INakedClassifier classifier) {
		OJPathName path = OJUtil.classifierPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if(owner==null){
			owner=(OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		}
		return owner;
	}

	protected static OJConstructor findConstructor(OJAnnotatedClass c, OJPathName parameter1) {
		return c.findConstructor(parameter1);
	}

	/**
	 * Some classifiers in UML would not necessarily be generated as Java
	 * classes. Returns false for NakedBehaviors that have one or less resulting
	 * parameters
	 * 
	 */
	protected static boolean hasOJClass(INakedClassifier c) {
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

	protected static boolean isPersistent(INakedClassifier c) {
		// what about interfaces implemented by persistent classifiers??????
		// They can be persisted in Hibernate but not JPA
		// Interfaces are so different from normal persisten classifiers that
		// they have to be treated separately and explicitly
		if (c instanceof INakedComplexStructure) {
			return ((INakedComplexStructure) c).isPersistent();
		} else {
			return false;
		}
	}

	protected static List<OJPathName> toOJPathNames(List<?> l) {
		List<OJPathName> result = new ArrayList<OJPathName>();
		Iterator<?> iter = l.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			ClassifierMap map = null;
			if (o instanceof INakedTypedElement) {
				map = new NakedClassifierMap(((INakedTypedElement) o).getType());
			} else if (o instanceof INakedClassifier) {
				map = new NakedClassifierMap((INakedClassifier) o);
			} else {
				map = new ClassifierMap((IClassifier) o);
			}
			result.add(map.javaTypePath());
		}
		return result;
	}

	protected OJPackage findOrCreatePackageForClass(String qualifiedJavaClassName) {
		StringTokenizer st = new StringTokenizer(qualifiedJavaClassName, ".");
		OJPackage p = this.javaModel;
		int countTokens = st.countTokens();
		for (int i = 0; i < countTokens - 1; i++) {
			String name = st.nextToken();
			OJPackage prev = p;
			p = prev.findPackage(new OJPathName(name));
			if (p == null) {
				p = new OJPackage();
				p.setName(name);
				prev.addToSubpackages(p);
			}
		}
		return p;
	}

	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		if (root instanceof INakedModelWorkspace) {
			return ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
		} else {
			return super.getChildren(root);
		}
	}

	public static boolean isInSingleTableInheritance(INakedClassifier c) {
		INakedClassifier superType = c.getSupertype();
		if (superType != null && superType.hasStereotype(SINGLE_TABLE_INHERITANCE)) {
			return true;
		} else {
			if (superType == null) {
				return false;
			}
			return isInSingleTableInheritance(superType);
		}
	}

	protected boolean hasEntityImplementationsOnly(INakedInterface ni) {
		boolean hasEntityImplementationsOnly = true;
		for (INakedClassifier child : InterfaceUtil.getImplementationsOf(ni)) {
			if (!(child instanceof INakedEntity)) {
				hasEntityImplementationsOnly = false;
			}
		}
		return hasEntityImplementationsOnly;
	}
	protected final IOclEngine getOclEngine() {
		return workspace.getOclEngine();
	}
}
