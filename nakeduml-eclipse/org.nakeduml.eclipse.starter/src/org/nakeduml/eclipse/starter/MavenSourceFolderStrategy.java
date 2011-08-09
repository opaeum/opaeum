package org.nakeduml.eclipse.starter;

import net.sf.nakeduml.feature.ISourceFolderStrategy;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.MavenProjectCodeGenerator;
import net.sf.nakeduml.javageneration.TextSourceFolderIdentifier;

public class MavenSourceFolderStrategy implements ISourceFolderStrategy{
	@Override
	public void defineSourceFolders(NakedUmlConfig config){
		mapDefaultMavenOutputRoots(config);
	}
	private static void mapDefaultMavenOutputRoots(NakedUmlConfig cfg){
		mapDomainProjects(cfg);
		mapAdaptorProjects(cfg);
		mapIntegratedAdaptorProject(cfg);
		mapWebProject(cfg);
	}
	private static void mapWebProject(NakedUmlConfig cfg){
		SourceFolderDefinition webTestResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_TEST_RESOURCE, true, "-web", "src/test/resources");
		webTestResources.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEB_TEST_RESOURCE_JBOSSAS, true, "-web", "src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.WEBAPP_GEN_TEST_SRC, true, "-web", "src/test/generated-java");
		SourceFolderDefinition webAppRoot = cfg.defineSourceFolder(TextSourceFolderIdentifier.WEBAPP_RESOURCE, true, "-web", "src/main/webapp");
		webAppRoot.dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapDomainProjects(NakedUmlConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, false, "-domain", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, false, "-domain", "src/test/generated-java");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC, false, "-domain", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, false, "-domain", "src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE, false, "-domain", "src/test/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, false, "-domain", "src/main/generated-resources");
	}
	private static void mapAdaptorProjects(NakedUmlConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC, false, "-adaptor", "src/main/generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_RESOURCE, false, "-adaptor", "src/main/generated-resources");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC, false, "-adaptor", "src/test/generated-java");
		SourceFolderDefinition testSource = cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_TEST_SRC, false, "-adaptor", "src/test/java");
		testSource.dontCleanDirectories();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE_JBOSSAS, false, "-adaptor",
				"src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition testResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE, false, "-adaptor", "src/test/resources");
		testResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_TEST_RESOURCE, false, "-adaptor", "src/test/generated-resources");
		SourceFolderDefinition mainResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_RESOURCE, false, "-adaptor", "src/main/resources");
		mainResources.dontCleanDirectoriesOrOverwriteFiles();
	}
	private static void mapIntegratedAdaptorProject(NakedUmlConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, true, "-integrated", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_TEST_SRC, true, "-integrated", "src/test/generated-java");
		SourceFolderDefinition integratedTestSource = cfg
				.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_SRC, true, "-integrated", "src/test/java");
		integratedTestSource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition integratedTestResource = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE, true, "-integrated",
				"src/test/resources");
		integratedTestResource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition src = cfg.defineSourceFolder(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_SRC, true, "-integrated", "src/main/java");
		src.dontCleanDirectories();
		SourceFolderDefinition integratedResource = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_RESOURCE, true, "-integrated",
				"src/main/resources");
		integratedResource.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition integratedJboss = cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS, true, "-integrated",
				"src/test/jboss-resources");
		integratedJboss.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, true, "-integrated", "src/main/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_TEST_GEN_RESOURCE, true, "-integrated", "src/test/generated-resources");
	}

}
