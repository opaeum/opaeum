package org.opaeum.sourcefolderstrategies;

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
		mapIntegratedAdaptorProject(cfg);
		mapWebProject(cfg);
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_GEN_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX,
				"-migrator", "src/main/generated");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, "-migrator",
				"src/main/java").dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapWebProject(OpaeumConfig cfg){
		SourceFolderDefinition webAppRoot = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEBAPP_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src/main/webapp");
		webAppRoot.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition webProjectRoot = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_PROJECT_ROOT,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "");
		webProjectRoot.dontCleanDirectories();
	}
	private static void mapDomainProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp", "gen-src");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp", "gen-test-src");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "src");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "test-src");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp",
				"gen-test-src");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX_PREFIX_MODEL_NAME_TO_SOURCE_FOLDER, ".webapp",
				"gen-src");
	}
	private static void mapIntegratedAdaptorProject(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX,
				".webapp", "gen-src");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "gen-src");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_GEN_RESOURCE,
				ProjectNameStrategy.QUALIFIED_WORKSPACE_NAME_AND_SUFFIX, ".webapp", "gen-test-src");
	}
	@Override
	public boolean isSingleProjectStrategy(){
		return true;
	}
	@Override
	public File calculateOutputRoot(File projectRoot,String workspaceIdentifier){
		return new File(projectRoot.getParentFile(), workspaceIdentifier);
	}
}
