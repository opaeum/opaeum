package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;

import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(phase = PomGenerationPhase.class, requires = { ProjectEarModulePomStep.class, ProjectEjbModulePomStep.class, ProjectWarModulePomStep.class })
public class ProjectRootPomStep extends PomGenerationStep {

	@Override
	public Properties getProperties() {
		Properties p = super.getProperties();
		p.put("jboss.home", "${env.JBOSS_HOME}");
		p.put("jboss.domain", "default");
		p.put("seam.persistence.version", "3.0.0.Beta2");
		p.put("seam.solder.version", "3.0.0.Beta1");
		p.put("seam.servlet.version", "3.0.0.Alpha3");
		p.put("numl.version", "1.0.0.4-SNAPSHOT");
		p.put("hibernate.version", "3.6.0.Final");
		p.put("drools.version", "5.2.0-alpha1");
		p.put("jbpm.version", "5.0-SNAPSHOT");
		p.put("arquillian.version", "1.0.0.Alpha4");
		return p;
	}

	@Override
	public String[] getModules() {
		return new String[] { this.config.getProjectName() + "-ear", this.config.getProjectName() + "-ejb",
				this.config.getProjectName() + "-war" };
	}

	@Override
	public String getTargetDir() {
		return JavaTextSource.NAKED_PROJECT_ROOT;
	}

	@Override
	public String getPackaging() {
		return "pom";
	}

	@Override
	public String getArtifactSuffix() {
		return "";
	}

	@Override
	public Dependency[] getDependencyManagementDependencies() {
		List<Dependency> dependencies = new ArrayList<Dependency>();

		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.spec");
		dependency.setArtifactId("jboss-javaee-6.0");
		dependency.setVersion("1.0.0.Final");
		dependency.setScope("provided");
		dependency.setType("pom");
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("javax.enterprise");
		dependency.setArtifactId("cdi-api");
		dependency.setVersion("1.0-SP1");
		dependency.setScope("provided");
		dependency.setType("jar");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.spec");
		dependency.setArtifactId("jboss-javaee-6.0");
		dependency.setVersion("1.0.0.Final");
		dependency.setScope("provided");
		dependency.setType("pom");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("javax.enterprise");
		dependency.setArtifactId("cdi-api");
		dependency.setVersion("1.0-SP1");
		dependency.setScope("provided");
		dependency.setType("jar");
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.slf4j");
		dependency.setArtifactId("slf4j-log4j12");
		dependency.setVersion("1.6.1");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-api");
		dependency.setVersion("${seam.servlet.version}");
		dependency.setScope("compile");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-impl");
		dependency.setVersion("${seam.servlet.version}");
		dependency.setScope("runtime");
		Exclusion exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.jboss.logging");
		exclusion.setArtifactId("jboss-logging");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.persistence");
		dependency.setArtifactId("seam-persistence-api");
		dependency.setVersion("${seam.persistence.version}");
		dependency.setScope("compile");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.persistence");
		dependency.setArtifactId("seam-persistence-impl");
		dependency.setVersion("${seam.persistence.version}");
		dependency.setScope("runtime");
		dependency.setType("ejb");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-api");
		dependency.setVersion("${seam.solder.version}");
		dependency.setScope("compile");
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.jboss.logging");
		exclusion.setArtifactId("jboss-logging");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-impl");
		dependency.setVersion("${seam.solder.version}");
		dependency.setScope("runtime");
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.config");
		dependency.setArtifactId("seam-config-xml");
		dependency.setVersion("3.0.0.Beta2");
		dependency.setScope("test");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId(getGroupId());
		dependency.setArtifactId(super.getName() + "-ear");
		dependency.setVersion("${project.version}");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId(getGroupId());
		dependency.setArtifactId(super.getName() + "-ejb");
		dependency.setVersion("${project.version}");
		dependency.setType("ejb");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId(getGroupId());
		dependency.setArtifactId(super.getName() + "-war");
		dependency.setVersion("${project.version}");
		dependency.setType("war");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.nakeduml");
		dependency.setArtifactId("util");
		dependency.setVersion("${numl.version}");
		dependency.setType("ejb");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());

		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-core");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-annotations");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("javax.transaction");
		exclusion.setArtifactId("jta");
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-flow");
		dependency.setVersion("${jbpm.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-flow-builder");
		dependency.setVersion("${jbpm.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-bpmn2");
		dependency.setVersion("${jbpm.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-persistence-jpa");
		dependency.setVersion("${jbpm.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-annotations");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-entitymanager");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-core");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-commons-annotations");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("javax.persistence");
		exclusion.setArtifactId("persistence-api");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("javax.transaction");
		exclusion.setArtifactId("jta");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("javassist");
		exclusion.setArtifactId("javassist");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("dom4j");
		exclusion.setArtifactId("dom4j");
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jbpm");
		dependency.setArtifactId("jbpm-bam");
		dependency.setVersion("${jbpm.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-core");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("javax.persistence");
		exclusion.setArtifactId("persistence-api");
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.drools");
		dependency.setArtifactId("drools-api");
		dependency.setVersion("${drools.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.drools");
		dependency.setArtifactId("drools-persistence-jpa");
		dependency.setVersion("${drools.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.drools");
		dependency.setArtifactId("drools-compiler");
		dependency.setVersion("${drools.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.antlr");
		exclusion.setArtifactId("antlr-runtime");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.drools");
		dependency.setArtifactId("drools-core");
		dependency.setVersion("${drools.version}");
		dependency.setType("jar");
		dependency.setScope("provided");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.hibernate");
		dependency.setArtifactId("hibernate-entitymanager");
		dependency.setVersion("${hibernate.version}");
		dependency.setScope("provided");
		dependency.setType("jar");
		exclusion = POMFactory.eINSTANCE.createExclusion();
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		exclusion.setGroupId("antlr");
		exclusion.setArtifactId("antlr");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("cglib");
		exclusion.setArtifactId("cglib");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("commons-collections");
		exclusion.setArtifactId("commons-collections");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("dom4j");
		exclusion.setArtifactId("dom4j");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("javassist");
		exclusion.setArtifactId("javassist");
		dependency.getExclusions().getExclusion().add(exclusion);
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("javax.transaction");
		exclusion.setArtifactId("jta");
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.hibernate");
		dependency.setArtifactId("hibernate-validator");
		dependency.setVersion("3.1.0.GA");
		dependency.setType("jar");
		dependency.setScope("provided");
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-commons-annotations");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);

		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.hibernate");
		exclusion.setArtifactId("hibernate-core");
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);
		
		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian");
		dependency.setArtifactId("arquillian-junit");
		dependency.setVersion("${arquillian.version}");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("junit");
		dependency.setArtifactId("junit");
		dependency.setVersion("4.8.2");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian.container");
		dependency.setArtifactId("arquillian-jbossas-managed-6");
		dependency.setVersion("${arquillian.version}");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.jbossas");
		dependency.setArtifactId("jboss-server-manager");
		dependency.setVersion("1.0.3.GA");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);

		dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.jbossas");
		dependency.setArtifactId("jboss-as-client");
		dependency.setVersion("6.0.0.20101110-CR1");
		dependency.setType("pom");
		dependency.setScope("test");
		exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("cglib");
		exclusion.setArtifactId("cglib");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);

		return dependencies.toArray(new Dependency[dependencies.size()]);
	}

	@Override
	public Plugin[] getPluginManagements() {
		Plugin[] result = new Plugin[2];
		result[0] = POMFactory.eINSTANCE.createPlugin();
		result[0].setGroupId("org.apache.maven.plugins");
		result[0].setArtifactId("maven-compiler-plugin");
		result[0].setVersion("2.3.2");
		ConfigurationType2 cfg = POMFactory.eINSTANCE.createConfigurationType2();
		addAnyElement(cfg.getAny(), "source", "1.6");
		addAnyElement(cfg.getAny(), "target", "1.6");
		result[0].setConfiguration(cfg);

		result[1] = POMFactory.eINSTANCE.createPlugin();
		result[1].setGroupId("org.codehaus.mojo");
		result[1].setArtifactId("build-helper-maven-plugin");
		result[1].setVersion("1.5");

		return result;
	}

}
