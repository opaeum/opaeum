package org.opeum.javageneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.opeum.feature.ISourceFolderIdentifier;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.SourceFolderDefinition;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.opeum.javageneration.maps.NakedClassifierMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedMultiplicityElement;
import org.opeum.metamodel.core.INakedNameSpace;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.core.INakedTypedElement;
import org.opeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.metamodel.workspace.OpeumLibrary;
import org.opeum.textmetamodel.SourceFolder;
import org.opeum.textmetamodel.TextDirectory;
import org.opeum.textmetamodel.TextFile;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextProject;
import org.opeum.textmetamodel.TextWorkspace;

public class AbstractJavaProducingVisitor extends NakedElementOwnerVisitor implements JavaTransformationStep{
	protected static final String SINGLE_TABLE_INHERITANCE = "SingleTableInheritance";
	protected OJPackage javaModel;
	protected OpeumConfig config;
	protected TextWorkspace textWorkspace;
	protected Set<TextOutputNode> textFiles;
	protected INakedModelWorkspace workspace;
	public Set<TextOutputNode> getTextFiles(){
		return textFiles;
	}
	public <T extends INakedElement>Set<T> getElementsOfType(Class<T> type,Collection<? extends INakedRootObject> roots){
		Set<T> result = new HashSet<T>();
		for(INakedRootObject r:roots){
			result.addAll(r.getDirectlyAccessibleElementOfType(type));
		}
		final Iterator<T> iterator = result.iterator();
		while(iterator.hasNext()){
			T t = (T) iterator.next();
			if(t.isMarkedForDeletion()){
				iterator.remove();
			}
		}
		return result;
	}
	@Override
	public void initialize(OJPackage pac,OpeumConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		textFiles = new HashSet<TextOutputNode>();
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.workspace = workspace;
	}
	public OpeumLibrary getLibrary(){
		return workspace.getOpeumLibrary();
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		recalculateUtilityPackage(o);
		super.visitRecursively(o);
	}
	private void recalculateUtilityPackage(INakedElementOwner o){
		if(o instanceof INakedRootObject){
			INakedRootObject pkg = (INakedRootObject) o;
			this.setCurrentRootObject(pkg);
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
			recalculateUtilityPackage(((INakedElement) o).getRootObject());
		}
		super.visitOnly(o);
	}
	protected OJPathName calculateUtilPath(INakedRootObject pkg){
		String qualifiedJavaName = OJUtil.packagePathname(pkg).toJavaString();
		OJPathName utilPath = new OJPathName(qualifiedJavaName + ".util");
		return utilPath;
	}
	public synchronized TextFile createTextPath(OJClassifier c,ISourceFolderIdentifier id){
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
	protected synchronized SourceFolder getSourceFolder(SourceFolderDefinition outputRoot){
		String projectPrefix = outputRoot.useWorkspaceName() ? workspace.getIdentifier() : getCurrentRootObject().getIdentifier();
		TextProject textProject = textWorkspace.findOrCreateTextProject(projectPrefix + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}
	protected final OJAnnotatedPackageInfo findOrCreatePackageInfo(OJPathName packageName,JavaSourceFolderIdentifier id){
		SourceFolderDefinition sourceFolderDefinition = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(sourceFolderDefinition);
		List<String> names = packageName.getNames();
		TextDirectory txtDir = or.findOrCreateTextDirectory(names);
		TextFile txtFile = txtDir.findOrCreateTextFile(Arrays.asList("package-info.java"), null, sourceFolderDefinition.overwriteFiles());
		if(txtFile.getTextSource() == null){
			OJAnnotatedPackageInfo pkgInfo = new OJAnnotatedPackageInfo();
			findOrCreatePackage(packageName).addToPackageInfo(pkgInfo);
			txtFile.setTextSource(new JavaTextSource(pkgInfo));
		}
		return (OJAnnotatedPackageInfo) ((JavaTextSource) txtFile.getTextSource()).getJavaSource();
	}
	protected final OJPackage findOrCreatePackage(OJPathName packageName){
		OJPackage parent = this.javaModel;
		OJPackage child = null;
		Iterator<String> iter = packageName.getNames().iterator();
		while(iter.hasNext()){
			String name = iter.next();
			child = (OJPackage) parent.findPackage(new OJPathName(name));
			if(child == null){
				child = new OJPackage(name);
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

	@Override
	protected boolean visitChildren(INakedElementOwner o){
		return o instanceof INakedNameSpace;
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
}
