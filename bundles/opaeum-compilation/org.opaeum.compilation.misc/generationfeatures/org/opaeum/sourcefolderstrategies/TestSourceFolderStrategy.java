package org.opaeum.sourcefolderstrategies;

import java.io.File;

import org.opaeum.feature.ISourceFolderStrategy;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

public class TestSourceFolderStrategy implements ISourceFolderStrategy{
	@Override
	public void defineSourceFolders(OpaeumConfig config){
		mapDefaultMavenOutputRoots(config);
		config.getConfigFile().getParentFile();
	}
	private static void mapDefaultMavenOutputRoots(OpaeumConfig cfg){
		mapDomainProjects(cfg);
		mapIntegratedAdaptorProject(cfg);
		mapWebProject(cfg);
		for(SourceFolderDefinition sd:cfg.getSourceFolderDefinitions().values()){
			sd.dontCleanDirectories();
		}
	}
	private static void mapWebProject(OpaeumConfig cfg){
		cfg.defineSourceFolder(TextSourceFolderIdentifier.WEBAPP_RESOURCE, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_PROJECT_ROOT, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-resources");
	}
	private static void mapDomainProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-resources");
	}
	private static void mapIntegratedAdaptorProject(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_GEN_RESOURCE, ProjectNameStrategy.SUFFIX_ONLY, "", "generated-resources");
	}
	@Override
	public boolean isSingleProjectStrategy(){
		return true;
	}
	@Override
	public File calculateOutputRoot(File configFile,File projectRoot,String workspaceIdentifier){
		return configFile.getParentFile().getParentFile(); // src/main/test
	}
}
