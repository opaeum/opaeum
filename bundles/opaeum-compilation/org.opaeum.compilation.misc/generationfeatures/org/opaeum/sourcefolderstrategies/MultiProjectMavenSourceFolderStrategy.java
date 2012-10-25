package org.opaeum.sourcefolderstrategies;

import java.io.File;

import org.opaeum.feature.ISourceFolderStrategy;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

public class MultiProjectMavenSourceFolderStrategy implements ISourceFolderStrategy{
	@Override
	public void defineSourceFolders(OpaeumConfig config){
		mapDefaultMavenOutputRoots(config);
	}
	private static void mapDefaultMavenOutputRoots(OpaeumConfig cfg){
		mapDomainProjects(cfg);
		mapIntegratedAdaptorProject(cfg);
		mapWebProject(cfg);
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_GEN_SRC, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, "-migrator",
				"src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_SRC, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, "-migrator",
				"src/main/java").dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapWebProject(OpaeumConfig cfg){
		SourceFolderDefinition webAppRoot = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEBAPP_RESOURCE,
				ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, "-web", "src/main/webapp");
		webAppRoot.dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapDomainProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ProjectNameStrategy.MODEL_NAME_AND_SUFFIX, "-domain",
				"src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, ProjectNameStrategy.MODEL_NAME_AND_SUFFIX, "-domain",
				"src/test/generated-java");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC,
				ProjectNameStrategy.MODEL_NAME_AND_SUFFIX, "-domain", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC,
				ProjectNameStrategy.MODEL_NAME_AND_SUFFIX, "-domain", "src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE, ProjectNameStrategy.MODEL_NAME_AND_SUFFIX, "-domain",
				"src/test/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, ProjectNameStrategy.MODEL_NAME_AND_SUFFIX, "-domain",
				"src/main/generated-resources");
	}
	private static void mapIntegratedAdaptorProject(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX,
				"-integrated", "src/main/generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX,
				"-integrated", "src/main/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_GEN_RESOURCE, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX,
				"-integrated", "src/test/generated-resources");
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
