package net.sf.nakeduml.pomgeneration;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.DependencyManagement;
import org.apache.maven.pom.DocumentRoot;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires={JpaPomStep.class},before={},after={JpaPomStep.class}, phase = PomGenerationPhase.class)
public class HibernatePomStep extends PomGenerationStep {

	@Override
	public Dependency[] getDependencies() {
		Dependency[] result = new Dependency[8];
		result[0]=POMFactory.eINSTANCE.createDependency();
		result[0].setGroupId("dom4j");
		result[0].setArtifactId("dom4j");
		result[0].setVersion("1.6.1");
		result[0].setScope("compile");
		result[0].setType("jar");
		result[1]=POMFactory.eINSTANCE.createDependency();
		result[1].setGroupId("commons-collections");
		result[1].setArtifactId("commons-collections");
		result[1].setVersion("3.2.1");
		result[1].setScope("compile");
		result[1].setType("jar");
		result[2]=POMFactory.eINSTANCE.createDependency();
		result[2].setGroupId("org.hibernate");
		result[2].setArtifactId("hibernate-core");
		result[2].setVersion("3.3.2.GA");
		result[2].setScope("compile");
		result[2].setType("jar");
		result[3]=POMFactory.eINSTANCE.createDependency();
		result[3].setGroupId("javassist");
		result[3].setArtifactId("javassist");
		result[3].setVersion("3.11.0.GA");
		result[3].setScope("compile");
		result[3].setType("jar");
		result[4]=POMFactory.eINSTANCE.createDependency();
		result[4].setGroupId("xml-apis");
		result[4].setArtifactId("xml-apis");
		result[4].setVersion("2.0.2");
		result[4].setScope("compile");
		result[4].setType("jar");
		result[5]=POMFactory.eINSTANCE.createDependency();
		result[5].setGroupId("org.hibernate");
		result[5].setArtifactId("hibernate-entitymanager");
		result[5].setVersion("3.3.2.GA");
		result[5].setScope("compile");
		result[5].setType("jar");
		result[6]=POMFactory.eINSTANCE.createDependency();
		result[6].setGroupId("org.slf4j");
		result[6].setArtifactId("slf4j-log4j12");
		result[6].setVersion("1.5.8");
		result[6].setScope("compile");
		result[6].setType("jar");
		result[7]=POMFactory.eINSTANCE.createDependency();
		result[7].setGroupId("org.hibernate");
		result[7].setArtifactId("hibernate-entitymanager");
		result[7].setVersion("3.4.0.GA");
		result[7].setScope("compile");
		result[7].setType("jar");
		return result;
	}

	@Override
	protected OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
	}

	@Override
	public Plugin[] getPlugins() {
		Plugin[] result = new Plugin[0];
		return result;
	}

}
