package org.opeum.pomgeneration;

import java.io.File;

import org.opeum.feature.ISourceFolderStrategy;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.SourceFolderDefinition;
import org.opeum.javageneration.JavaSourceFolderIdentifier;
import org.opeum.javageneration.TextSourceFolderIdentifier;

public class SingleProjectMavenSourceFolderStrategy implements ISourceFolderStrategy{
	@Override
	public void defineSourceFolders(OpeumConfig config){
		mapDefaultMavenOutputRoots(config);
	}
	private static void mapDefaultMavenOutputRoots(OpeumConfig cfg){
		mapDomainProjects(cfg);
		mapAdaptorProjects(cfg);
		mapIntegratedAdaptorProject(cfg);
		mapWebProject(cfg);
	}
	private static void mapWebProject(OpeumConfig cfg){
		SourceFolderDefinition webTestResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_TEST_RESOURCE, true, "", "src/test/resources");
		webTestResources.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_TEST_RESOURCE_JBOSSAS, true, "", "src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.WEBAPP_GEN_TEST_SRC, true, "", "src/test/generated-java");
		SourceFolderDefinition webAppRoot = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEBAPP_RESOURCE, true, "", "src/main/webapp");
		webAppRoot.dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapDomainProjects(OpeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, true, "", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, true, "", "src/test/generated-java");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC, true, "", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, true, "", "src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE, true, "", "src/test/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, true, "", "src/main/generated-resources");
	}
	private static void mapAdaptorProjects(OpeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC, true, "", "src/main/generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_RESOURCE, true, "", "src/main/generated-resources");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC, true, "", "src/test/generated-java");
		SourceFolderDefinition testSource = cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_TEST_SRC, true, "", "src/test/java");
		testSource.dontCleanDirectories();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE_JBOSSAS, true, "", "src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition testResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE, true, "", "src/test/resources");
		testResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_TEST_RESOURCE, true, "", "src/test/generated-resources");
		SourceFolderDefinition mainResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_RESOURCE, true, "", "src/main/resources");
		mainResources.dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapIntegratedAdaptorProject(OpeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, true, "", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_TEST_SRC, true, "", "src/test/generated-java");
		SourceFolderDefinition integratedTestSource = cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_SRC, true, "", "src/test/java");
		integratedTestSource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition integratedTestResource = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE, true, "",
				"src/test/resources");
		integratedTestResource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition src = cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_SRC, true, "", "src/main/java");
		src.dontCleanDirectories();
		SourceFolderDefinition integratedResource = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_RESOURCE, true, "", "src/main/resources");
		integratedResource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition integratedJboss = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS, true, "",
				"src/test/jboss-resources");
		integratedJboss.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, true, "", "src/main/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_GEN_RESOURCE, true, "", "src/test/generated-resources");
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
