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

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
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
	protected ValueSpecificationUtil valueSpecificationUtil;
	protected EventUtil eventUtil;
	protected OJUtil ojUtil;
	@SuppressWarnings("unchecked")
	public <T extends NamedElement>Set<T> getElementsOfType(Class<T> type,Collection<? extends Package> roots){
		SortedSet<T> result = new TreeSet<T>(new Comparator<T>(){
			@Override
			public int compare(T o1,T o2){
				return o1.getQualifiedName().compareTo(o2.getQualifiedName());
			}
		});
		for(Package r:roots){
			TreeIterator<EObject> iter = r.eAllContents();
			while(iter.hasNext()){
				EObject eObject = (EObject) iter.next();
				if(type.isInstance(eObject)){
					result.add((T) eObject);
				}
			}
		}
		final Iterator<T> iterator = result.iterator();
		while(iterator.hasNext()){
			T t = (T) iterator.next();
			if(EmfElementUtil.isMarkedForDeletion(t)){
				iterator.remove();
			}
		}
		return result;
	}
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,OJUtil ojUtil){
		EmfClassifierUtil.setClassRegistry(OpaeumConfig.getClassRegistry());
		textFiles = new HashSet<TextOutputNode>();
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.workspace = workspace;
		this.eventUtil = new EventUtil(ojUtil);
		this.valueSpecificationUtil = new ValueSpecificationUtil(ojUtil);
		this.ojUtil=ojUtil;
	}
	public OpaeumLibrary getLibrary(){
		return workspace.getOpaeumLibrary();
	}
	@Override
	public void visitRecursively(Element o){
		if(o instanceof EmfWorkspace){
			OJPackage util = setWorkspaceUtilPackage();
			visitBeforeMethods(o);
			visitChildren(o);
			// UtilPackage Would have been overridden by contained root objects
			UtilityCreator.setUtilPackage(util);
			visitAfterMethods(o);
			// Free memory
			UtilityCreator.setUtilPackage(null);
		}else if(EmfPackageUtil.isRootObject(o)){
			Package pkg = (Package) o;
			setRootObjectUtilPackage(pkg);
			this.setCurrentRootObject(pkg);
			visitBeforeMethods(o);
			visitChildren(o);
			setRootObjectUtilPackage(pkg);
			visitAfterMethods(o);
			setCurrentRootObject(null);// NB!! needs to be cleared from every thread
			UtilityCreator.setUtilPackage(null);
		}else{
			super.visitRecursively(o);
		}
	}
	protected void setRootObjectUtilPackage(Package pkg){
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
	public void visitOnly(Element o){
		if(o instanceof EmfWorkspace){
			setWorkspaceUtilPackage();
		}else{
			Element nakedElement = (Element) o;
			Package rootObject = EmfElementFinder.getRootObject(nakedElement);
			setRootObjectUtilPackage(rootObject);
		}
		super.visitOnly(o);
		UtilityCreator.setUtilPackage(null);
	}
	protected OJPathName calculateUtilPath(Package pkg){
		String qualifiedJavaName = ojUtil.packagePathname(pkg).toJavaString();
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
	protected OJAnnotatedClass findJavaClass(Classifier classifier){
		OJPathName path = ojUtil.classifierPathname(classifier);
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
	public static boolean isPersistent(Type type){
		return EmfClassifierUtil.isPersistent(type);
	}
	@Override
	public Collection<Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			return new ArrayList<Element>(((EmfWorkspace) root).getGeneratingModelsOrProfiles());
		}else{
			return super.getChildren(root);
		}
	}
	public static boolean isInSingleTableInheritance(Classifier c){
		Classifier superType = c.getGenerals().size() == 1 ? c.getGenerals().get(0) : null;
		if(superType != null && StereotypesHelper.hasStereotype(superType, SINGLE_TABLE_INHERITANCE)){
			return true;
		}else{
			if(superType == null){
				return false;
			}
			return isInSingleTableInheritance(superType);
		}
	}
	@Override
	protected boolean shouldVisitChildren(Element o){
		return o instanceof Namespace;
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
