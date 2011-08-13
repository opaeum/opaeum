package net.sf.nakeduml.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import net.sf.nakeduml.feature.ISourceFolderIdentifier;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJClass;
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
	private Set<TextOutputNode> textFiles;
	protected INakedModelWorkspace workspace;
	public Set<TextOutputNode> getTextFiles(){
		return textFiles;
	}
	@Override
	public void initialize(OJAnnotatedPackage pac,NakedUmlConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		textFiles = new HashSet<TextOutputNode>();
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.workspace = workspace;
	}
	public NakedUmlLibrary getLibrary(){
		return workspace.getNakedUmlLibrary();
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		recalculateUtilityPackage(o);
		super.visitRecursively(o);
	}
	private void recalculateUtilityPackage(INakedElementOwner o){
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
	}
	@Override
	public void visitOnly(INakedElementOwner o){
		if(o instanceof INakedModelWorkspace){
			recalculateUtilityPackage(o);
		}else{
			recalculateUtilityPackage(((INakedElement)o).getRootObject());
		}
		super.visitOnly(o);
	}
	protected OJPathName calculateUtilPath(INakedRootObject pkg){
		String qualifiedJavaName = OJUtil.packagePathname(pkg).toJavaString();
		OJPathName utilPath = new OJPathName(qualifiedJavaName + ".util");
		return utilPath;
	}
	public TextFile createTextPath(OJClassifier c,ISourceFolderIdentifier id){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(outputRoot);
		List<String> names = c.getPathName().getHead().getNames();
		names.add(c.getName() + ".java");
		JavaTextSource jts = new JavaTextSource(c);
		TextFile file = or.findOrCreateTextFile(names, jts, outputRoot.overwriteFiles());
		file.setTextSource(jts);
		this.textFiles.add(file);
		return file;
	}
	protected SourceFolder getSourceFolder(SourceFolderDefinition outputRoot){
		String projectPrefix = outputRoot.useWorkspaceName() ? workspace.getIdentifier() : currentRootObject.getIdentifier();
		TextProject textProject = textWorkspace.findOrCreateTextProject(projectPrefix + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}
	protected void createTextPathIfRequired(OJAnnotatedPackage p,JavaSourceFolderIdentifier id){
		SourceFolderDefinition sourceFolderDefinition = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(sourceFolderDefinition);
		List<String> names = p.getPathName().getNames();
		names.add("package-info.java");
		JavaTextSource source = new JavaTextSource(p);
		TextFile txt = or.findOrCreateTextFile(names, source, sourceFolderDefinition.overwriteFiles());
		txt.setTextSource(source);
	}
	protected final OJAnnotatedPackage findOrCreatePackage(OJPathName packageName){
		OJAnnotatedPackage parent = this.javaModel;
		OJAnnotatedPackage child = null;
		Iterator<String> iter = packageName.getNames().iterator();
		while(iter.hasNext()){
			String name = iter.next();
			child = (OJAnnotatedPackage) parent.findPackage(new OJPathName(name));
			if(child == null){
				child = new OJAnnotatedPackage(name);
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
	protected void deleteClass(JavaSourceFolderIdentifier id,OJPathName ojPathName){
		deleteTextNode(id, ojPathName, true);
		OJClass pkg = javaModel.findClass(ojPathName);
		if(pkg != null){
			pkg.setMyPackage(null);
		}
	}
	private void deleteTextNode(JavaSourceFolderIdentifier id,OJPathName ojPathName,boolean targetIsFile){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(outputRoot);
		List<String> names = ojPathName.getNames();
		if(targetIsFile){
			names.set(names.size() - 1, names.get(names.size() - 1) + ".java");
		}
		getTextFiles().add(or.markNodeForDeletion(names, targetIsFile));
	}
	protected void deletePackage(JavaSourceFolderIdentifier id,OJPathName ojPathName){
		deleteTextNode(id, ojPathName, false);
		OJPackage pkg = javaModel.findPackage(ojPathName);
		if(pkg != null){
			pkg.setParent(null);
		}
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
	protected boolean isIntegrationPhase(){
		return transformationContext.isIntegrationPhase();
	}
}
