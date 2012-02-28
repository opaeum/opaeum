package org.opaeum.javageneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.opaeum.feature.MappingInfo;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextDirectory;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.visitor.TextFileGeneratingVisitor;

public class AbstractJavaProducingVisitor extends TextFileGeneratingVisitor implements JavaTransformationStep{
	protected static final String SINGLE_TABLE_INHERITANCE = "SingleTableInheritance";
	protected OJWorkspace javaModel;
	public <T extends INakedElement>Set<T> getElementsOfType(Class<T> type,Collection<? extends INakedRootObject> roots){
		SortedSet<T> result = new TreeSet<T>(new Comparator<T>(){
			@Override
			public int compare(T o1,T o2){
				MappingInfo mappingInfo = o1.getMappingInfo();
				MappingInfo mappingInfo2 = o2.getMappingInfo();
				String qualifiedUmlName = mappingInfo.getQualifiedUmlName();
				String qualifiedUmlName2 = mappingInfo2.getQualifiedUmlName();
				return qualifiedUmlName.compareTo(qualifiedUmlName2);
			}
		});
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
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		textFiles = new HashSet<TextOutputNode>();
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.workspace = workspace;
	}
	public OpaeumLibrary getLibrary(){
		return workspace.getOpaeumLibrary();
	}
	@Override
	public void visitRecursively(INakedElementOwner o){
		if(o instanceof INakedModelWorkspace){
			OJPackage util = setWorkspaceUtilPackage();
			visitBeforeMethods(o);
			visitChildren(o);
			// UtilPackage Would have been overridden by contained root objects
			UtilityCreator.setUtilPackage(util);
			visitAfterMethods(o);
			// Free memory
			UtilityCreator.setUtilPackage(null);
		}else if(o instanceof INakedRootObject){
			INakedRootObject pkg = (INakedRootObject) o;
			setRootObjectUtilPackage(pkg);
			this.setCurrentRootObject(pkg);
			visitBeforeMethods(o);
			visitChildren(o);
			setRootObjectUtilPackage(pkg);
			visitAfterMethods(o);
			if(o instanceof INakedRootObject){
				setCurrentRootObject(null);// NB!! needs to be cleared from every thread
			}
			UtilityCreator.setUtilPackage(null);
		}else{
			super.visitRecursively(o);
		}
	}
	protected void setRootObjectUtilPackage(INakedRootObject pkg){
		if(javaModel != null){
			OJPathName utilPath = calculateUtilPath(pkg);
			UtilityCreator.setUtilPackage(findOrCreatePackage(utilPath));
		}
	}
	protected OJPackage setWorkspaceUtilPackage(){
		OJPackage util = null;
		if(javaModel != null){
			OJPathName utilPath = new OJPathName(config.getMavenGroupId() + ".util");
			util = findOrCreatePackage(utilPath);
			UtilityCreator.setUtilPackage(util);
		}
		return util;
	}
	@Override
	public void visitOnly(INakedElementOwner o){
		if(o instanceof INakedModelWorkspace){
			setWorkspaceUtilPackage();
		}else{
			setRootObjectUtilPackage(((INakedElement) o).getRootObject());
		}
		super.visitOnly(o);
		UtilityCreator.setUtilPackage(null);
	}
	protected OJPathName calculateUtilPath(INakedRootObject pkg){
		String qualifiedJavaName = OJUtil.packagePathname(pkg).toJavaString();
		OJPathName utilPath = new OJPathName(qualifiedJavaName + ".util");
		return utilPath;
	}
	protected final OJAnnotatedPackageInfo findOrCreatePackageInfo(OJPathName packageName,JavaSourceFolderIdentifier id){
		SourceFolderDefinition sourceFolderDefinition = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(sourceFolderDefinition);
		List<String> names = packageName.getNames();
		TextDirectory txtDir = or.findOrCreateTextDirectory(names);
		TextFile txtFile = txtDir.findOrCreateTextFile(Arrays.asList("package-info.java"), sourceFolderDefinition.overwriteFiles());
		if(txtFile.getTextSource() == null){
			OJAnnotatedPackageInfo pkgInfo = new OJAnnotatedPackageInfo();
			findOrCreatePackage(packageName).addToPackageInfo(pkgInfo);
			txtFile.setTextSource(new JavaTextSource(pkgInfo));
		}
		return (OJAnnotatedPackageInfo) ((JavaTextSource) txtFile.getTextSource()).getJavaSource();
	}
	protected final OJPackage findOrCreatePackage(OJPathName packageName){
		return this.javaModel.findOrCreatePackage(packageName);
	}
	protected OJAnnotatedClass findJavaClass(INakedClassifier classifier){
		OJPathName path = OJUtil.classifierPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findClass(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findClass(path);
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
				map = OJUtil.buildClassifierMap(((INakedTypedElement) o).getType());
			}else if(o instanceof INakedClassifier){
				map = OJUtil.buildClassifierMap((INakedClassifier) o);
			}else{
				map = new ClassifierMap((IClassifier) o);
			}
			result.add(map.javaTypePath());
		}
		return result;
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
	protected boolean shouldVisitChildren(INakedElementOwner o){
		return o instanceof INakedNameSpace;
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
	public synchronized TextFile createTextPath(OJClassifier c,ISourceFolderIdentifier id){
		List<String> names = c.getPathName().getHead().getNames();
		names.add(c.getName() + ".java");
		TextFile file = createTextPath(id, names);
		JavaTextSource jts = new JavaTextSource(c);
		file.setTextSource(jts);
		return file;
	}
	@Override
	public void release(){
		super.release();
		this.javaModel = null;
	}
}
