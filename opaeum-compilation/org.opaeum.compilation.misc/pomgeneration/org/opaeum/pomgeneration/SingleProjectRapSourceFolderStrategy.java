package org.opaeum.pomgeneration;

import java.io.File;

import org.opaeum.feature.ISourceFolderStrategy;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

public class SingleProjectRapSourceFolderStrategy implements ISourceFolderStrategy{
	@Override
	public void defineSourceFolders(OpaeumConfig config){
		mapDefaultMavenOutputRoots(config);
	}
	private static void mapDefaultMavenOutputRoots(OpaeumConfig cfg){
		mapDomainProjects(cfg);
		mapAdaptorProjects(cfg);
		mapIntegratedAdaptorProject(cfg);
		mapWebProject(cfg);
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_GEN_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, "-migrator", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, "-migrator", "src/main/java")
				.dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapWebProject(OpaeumConfig cfg){
		SourceFolderDefinition webTestResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_TEST_RESOURCE, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/test/resources");
		webTestResources.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_TEST_RESOURCE_JBOSSAS, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.WEBAPP_GEN_TEST_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/generated-java");
		SourceFolderDefinition webAppRoot = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEBAPP_RESOURCE, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/main/webapp");
		webAppRoot.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition webProjectRoot = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_PROJECT_ROOT,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "");
		webProjectRoot.dontCleanDirectories();
	}
	private static void mapDomainProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp", "src/test/generated-java");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp", "src/test/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp", "src/main/generated-resources");
	}
	private static void mapAdaptorProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/main/generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_RESOURCE, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/main/generated-resources");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/test/generated-java");
		SourceFolderDefinition testSource = cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_TEST_SRC,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/java");
		testSource.dontCleanDirectories();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE_JBOSSAS,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition testResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/resources");
		testResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_TEST_RESOURCE, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp",
				"src/test/generated-resources");
		SourceFolderDefinition mainResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/main/resources");
		mainResources.dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapIntegratedAdaptorProject(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_TEST_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/generated-java");
		SourceFolderDefinition integratedTestSource = cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX,
				".webapp", "src/test/java");
		integratedTestSource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition integratedTestResource = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/resources");
		integratedTestResource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition src = cfg
				.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/main/java");
		src.dontCleanDirectories();
		SourceFolderDefinition integratedResource = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_RESOURCE, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX,
				".webapp", "src/main/resources");
		integratedResource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition integratedJboss = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/jboss-resources");
		integratedJboss.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/main/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_GEN_RESOURCE, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/test/generated-resources");
	}
	@Override
	public boolean isSingleProjectStrategy(){
		return true;
	}
	@Override
	public File calculateOutputRoot(File configFile,File projectRoot,String workspaceIdentifier){
		return new File(projectRoot.getParentFile(), workspaceIdentifier);
	}
}