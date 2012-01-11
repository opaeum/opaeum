package org.opaeum.pomgeneration;

import java.io.File;

import org.opaeum.feature.ISourceFolderStrategy;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

public class MultipleProjectMavenSourceFolderStrategy implements ISourceFolderStrategy{
	@Override
	public void defineSourceFolders(OpaeumConfig config){
		mapDefaultMavenOutputRoots(config);
	}
	private static void mapDefaultMavenOutputRoots(OpaeumConfig cfg){
		mapDomainProjects(cfg);
//		mapAdaptorProjects(cfg);
//		mapIntegratedAdaptorProject(cfg);
//		mapWebProject(cfg);
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_GEN_SRC, true, "-migrator", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_SRC, true, "-migrator", "src/main/java").dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapDomainProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, "", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, "", "src/test/generated-java");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC, true, "", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, true, "", "src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, "", "src/test/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, "", "src/main/generated-resources");
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
