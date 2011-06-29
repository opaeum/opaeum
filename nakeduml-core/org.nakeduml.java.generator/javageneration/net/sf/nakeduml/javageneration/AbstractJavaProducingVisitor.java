package net.sf.nakeduml.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJClassifier;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public class AbstractJavaProducingVisitor extends NakedElementOwnerVisitor implements JavaTransformationStep{
	protected static final String SINGLE_TABLE_INHERITANCE = "SingleTableInheritance";
	protected OJAnnotatedPackage javaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;
	protected TransformationContext transformationContext;
	@Override
	public void initialize(OJAnnotatedPackage pac,NakedUmlConfig config,TextWorkspace textWorkspace){
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
		
	}
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		this.transformationContext=context;
		startVisiting(workspace);
		
	}
	@Deprecated
	public void initialize(OJAnnotatedPackage javaModel,NakedUmlConfig config,TextWorkspace textWorkspace,TransformationContext context){
		this.javaModel = javaModel;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.transformationContext = context;
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(o instanceof INakedRootObject){
			INakedRootObject pkg = (INakedRootObject) o;
			this.currentRootObject = pkg;
			if(javaModel != null){
				OJPathName utilPath = calculateUtilPath(pkg);
				UtilityCreator.setUtilPackage(findOrCreatePackage(utilPath));
			}
		}else if(o instanceof INakedModelWorkspace){
			if(javaModel != null){
				OJPathName utilPath = new OJPathName(config.getMavenGroupId() + ".util");
				UtilityCreator.setUtilPackage(findOrCreatePackage(utilPath));
			}
		}
		super.visitRecursively(o);
	}
	protected OJPathName calculateUtilPath(INakedRootObject pkg) {
		String qualifiedJavaName = OJUtil.packagePathname(pkg).toJavaString();
		
		OJPathName utilPath = new OJPathName(qualifiedJavaName + ".util");
		return utilPath;
	}
	public TextFile createTextPath(OJClassifier c,Enum<?> id){
		OutputRoot outputRoot = config.getOutputRoot(id);
		SourceFolder or = getSourceFolder(outputRoot);
		List<String> names = c.getPathName().getHead().getNames();
		names.add(c.getName() + ".java");
		TextFile file = or.findOrCreateTextFile(names, new JavaTextSource(c), outputRoot.overwriteFiles());
		return file;
	}
	protected SourceFolder getSourceFolder(OutputRoot outputRoot){
		String projectPrefix = outputRoot.useWorkspaceName() ? workspace.getDirectoryName() : currentRootObject.getFileName();
		TextProject textProject = textWorkspace.findOrCreateTextProject(projectPrefix + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}
	protected void createTextPathIfRequired(OJAnnotatedPackage p,JavaTextSource.OutputRootId id){
		OutputRoot outputRoot = config.getOutputRoot(id);
		SourceFolder or = getSourceFolder(outputRoot);
		List<String> names = p.getPathName().getNames();
		names.add("package-info.java");
		or.findOrCreateTextFile(names, new JavaTextSource(p), outputRoot.overwriteFiles());
	}
	protected final OJAnnotatedPackage findOrCreatePackage(OJPathName packageName){
		OJAnnotatedPackage parent = this.javaModel;
		OJAnnotatedPackage child = null;
		Iterator<String> iter = packageName.getNames().iterator();
		while(iter.hasNext()){
			String name = iter.next();
			child = (OJAnnotatedPackage) parent.findPackage(new OJPathName(name));
			if(child == null){
				child = new OJAnnotatedPackage();
				child.setName(name);
				parent.addToSubpackages(child);
			}
			parent = child;
		}
		return child;
	}
	protected OJAnnotatedClass findJavaClass(INakedClassifier classifier){
		OJPathName path = OJUtil.classifierPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		}
		return owner;
	}
	protected OJAnnotatedClass findAuditJavaClass(INakedClassifier classifier){
		OJPathName path = OJUtil.classifierAuditPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		}
		return owner;
	}
	
	protected static OJConstructor findConstructor(OJAnnotatedClass c,OJPathName parameter1){
		return c.findConstructor(parameter1);
	}
	public static boolean isPersistent(INakedClassifier c){
		// what about interfaces implemented by persistent classifiers??????
		// They can be persisted in Hibernate but not JPA
		// Interfaces are so different from normal persisten classifiers that
		// they have to be treated separately and explicitly
		if(c instanceof INakedComplexStructure){
			return ((INakedComplexStructure) c).isPersistent();
		}else{
			return false;
		}
	}
	protected static List<OJPathName> toOJPathNames(List<?> l){
		List<OJPathName> result = new ArrayList<OJPathName>();
		Iterator<?> iter = l.iterator();
		while(iter.hasNext()){
			Object o = iter.next();
			ClassifierMap map = null;
			if(o instanceof INakedMultiplicityElement){
				map = new NakedClassifierMap(((INakedTypedElement) o).getType());
			}else if(o instanceof INakedClassifier){
				map = new NakedClassifierMap((INakedClassifier) o);
			}else{
				map = new ClassifierMap((IClassifier) o);
			}
			result.add(map.javaTypePath());
		}
		return result;
	}
	protected OJPackage findOrCreatePackageForClass(String qualifiedJavaClassName){
		StringTokenizer st = new StringTokenizer(qualifiedJavaClassName, ".");
		OJPackage p = this.javaModel;
		int countTokens = st.countTokens();
		for(int i = 0;i < countTokens - 1;i++){
			String name = st.nextToken();
			OJPackage prev = p;
			p = prev.findPackage(new OJPathName(name));
			if(p == null){
				p = new OJPackage();
				p.setName(name);
				prev.addToSubpackages(p);
			}
		}
		return p;
	}
	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
		if(root instanceof INakedModelWorkspace){
			List<INakedRootObject> generatingModelsOrProfiles = ((INakedModelWorkspace) root).getGeneratingModelsOrProfiles();
			return generatingModelsOrProfiles;
		}else{
			return super.getChildren(root);
		}
	}
	public static boolean isInSingleTableInheritance(INakedClassifier c){
		INakedClassifier superType = c.getSupertype();
		if(superType != null && superType.hasStereotype(SINGLE_TABLE_INHERITANCE)){
			return true;
		}else{
			if(superType == null){
				return false;
			}
			return isInSingleTableInheritance(superType);
		}
	}
	protected final IOclEngine getOclEngine(){
		return workspace.getOclEngine();
	}

}
