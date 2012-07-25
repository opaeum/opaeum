package org.opaeum.visitor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.opaeum.EmfElementVisitor;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class TextFileGeneratingVisitor extends EmfElementVisitor {
	protected TextWorkspace textWorkspace;
	protected Set<TextOutputNode> textFiles;
	protected OpaeumConfig config;
	protected EmfWorkspace workspace;
	protected ThreadLocal<Package> currentRootObject=new ThreadLocal<Package>();
	protected TransformationContext transformationContext;

	protected void visitParentsRecursively(Element parent) {
		if (EmfElementFinder.getContainer(parent)!=null) {
			visitParentsRecursively((Element) EmfElementFinder.getContainer(parent));
			for (VisitSpec v : methodInvokers.beforeMethods) {
				maybeVisit(parent, v);
			}
		}
	}

	public void visitUpThenDown(Element e) {
		visitParentsRecursively(e);
		visitRecursively(e);
	}

	@Override
	public void visitRecursively(Element o) {
		if (EmfPackageUtil.isRootObject(o)) {
			this.setCurrentRootObject((Package) o);
		}
		visitBeforeMethods(o);
		visitChildren(o);
		visitAfterMethods(o);
		if (EmfPackageUtil.isRootObject(o)) {
			setCurrentRootObject(null);// NB!! needs to be cleared from every
										// thread
		}
	}

	@Override
	public void visitOnly(Element o) {
		setCurrentRootObject(EmfElementFinder.getRootObject(o));
		super.visitOnly(o);
		setCurrentRootObject(null);
	}

	protected Collection<Package> getModelInScope() {
		Collection<Package> result = EmfPackageUtil.getAllDependencies(getCurrentRootObject());
		result.add(getCurrentRootObject());
		return result;
	}

	public final void setTransformationContext(TransformationContext c) {
		this.transformationContext = c;
	}

	protected Package getCurrentRootObject() {
		return currentRootObject.get();
	}

	protected void setCurrentRootObject(Package currentRootObject) {
		this.currentRootObject.set(currentRootObject);
	}

	protected String getProjectName(SourceFolderDefinition outputRoot) {
		String projectPrefix = null;
		switch (outputRoot.getProjectNameStrategy()) {
		case QUALIFIED_WORKSPACE_NAME_AND_SUFFIX:
			projectPrefix = config.getMavenGroupId() + "."
					+ workspace.getIdentifier();
			break;
		case SUFFIX_ONLY:
			projectPrefix = "";
			break;
		case MODEL_NAME_AND_SUFFIX:
			projectPrefix = EmfPackageUtil.getIdentifier(getCurrentRootObject());
			break;
		case WORKSPACE_NAME_AND_SUFFIX:
			projectPrefix = workspace.getIdentifier();
			break;
		case QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER:
			projectPrefix = config.getMavenGroupId() + "."
					+ workspace.getIdentifier();
			break;
		case WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER:
			projectPrefix = workspace.getIdentifier();
			break;
		}
		return projectPrefix + outputRoot.getProjectSuffix();
	}

	public Set<TextOutputNode> getTextFiles() {
		return textFiles;
	}

	protected TextFile createTextPath(ISourceFolderIdentifier id,
			List<String> names) {
		SourceFolderDefinition outputRoot = config
				.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(outputRoot);
		TextFile file = or.findOrCreateTextFile(names,
				outputRoot.overwriteFiles());
		this.textFiles.add(file);
		return file;
	}

	protected synchronized SourceFolder getSourceFolder(
			SourceFolderDefinition outputRoot) {
		TextProject textProject = textWorkspace
				.findOrCreateTextProject(getProjectName(outputRoot));
		String sourceFolder = outputRoot.getSourceFolder();
		if (outputRoot.prefixModelIdentifierToSourceFolder()) {
			// force multiple source folders per model
			sourceFolder = EmfPackageUtil.getIdentifier(getCurrentRootObject()) + "/"
					+ outputRoot.getSourceFolder();
		}
		SourceFolder or = textProject.findOrCreateSourceFolder(sourceFolder,
				outputRoot.cleanDirectories());
		return or;
	}

	public void release() {
		textFiles = null;
		textWorkspace = null;
		workspace = null;
	}
}
