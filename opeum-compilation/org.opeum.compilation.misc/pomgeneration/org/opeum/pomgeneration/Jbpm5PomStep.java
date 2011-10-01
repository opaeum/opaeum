package org.opeum.pomgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.opeum.feature.StepDependency;
import org.opeum.textmetamodel.SourceFolderDefinition;
import org.opeum.textmetamodel.TextSourceFolderIdentifier;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.POMFactory;

@StepDependency(phase = PomGenerationPhase.class, requires = { BasicJavaDomainPomStep.class })
public class Jbpm5PomStep extends PomGenerationStep {
	@Override
	public Dependency[] getDependencies() {
		List<Dependency> dependencies = new ArrayList<Dependency>();
//		addJbpmFlow(dependencies);
//		addJbpmFlowBuilder(dependencies);
////		addJbpmPersistenceJpa(dependencies);
//		addDroolsCore(dependencies);
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}

	protected void addJbpmFlow(List<Dependency> dependencies) {
		Dependency jbpmFlow = POMFactory.eINSTANCE.createDependency();
		jbpmFlow.setGroupId("org.jbpm");
		jbpmFlow.setArtifactId("jbpm-flow");
		jbpmFlow.setVersion("${jbpm.version}");
		jbpmFlow.setType("jar");
		jbpmFlow.setScope("provided");
		dependencies.add(jbpmFlow);
	}

	protected void addJbpmFlowBuilder(List<Dependency> dependencies) {
		Dependency jbpmFlowBuilder = POMFactory.eINSTANCE.createDependency();
		jbpmFlowBuilder.setGroupId("org.jbpm");
		jbpmFlowBuilder.setArtifactId("jbpm-flow-builder");
		jbpmFlowBuilder.setVersion("${jbpm.version}");
		jbpmFlowBuilder.setType("jar");
		jbpmFlowBuilder.setScope("provided");
		dependencies.add(jbpmFlowBuilder);
	}

	protected void addDroolsCore(List<Dependency> dependencies) {
		Dependency droolsCore = POMFactory.eINSTANCE.createDependency();
		droolsCore.setGroupId("org.drools");
		droolsCore.setArtifactId("drools-core");
		droolsCore.setVersion("${drools.version}");
		droolsCore.setType("jar");
		droolsCore.setScope("provided");
		dependencies.add(droolsCore);
	}

	protected void addJbpmPersistenceJpa(List<Dependency> dependencies) {
		Dependency jbpmPersistenceJpa = POMFactory.eINSTANCE.createDependency();
		jbpmPersistenceJpa.setGroupId("org.jbpm");
		jbpmPersistenceJpa.setArtifactId("jbpm-persistence-jpa");
		jbpmPersistenceJpa.setVersion("${jbpm.version}");
		jbpmPersistenceJpa.setType("jar");
		jbpmPersistenceJpa.setScope("provided");
		jbpmPersistenceJpa.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		exludeHibernateAnnotations(jbpmPersistenceJpa);
		excludeHibernateEntityManager(jbpmPersistenceJpa);
		excludeHibernateCore(jbpmPersistenceJpa);
		excludeHibernateCommonsAnnotations(jbpmPersistenceJpa);
		excludeJavaxPersistenceApi(jbpmPersistenceJpa);
		excludeJta(jbpmPersistenceJpa);
		excludeJavassist(jbpmPersistenceJpa);
		excludeDom4j(jbpmPersistenceJpa);
		dependencies.add(jbpmPersistenceJpa);
	}

	protected void excludeDom4j(Dependency jbpmPersistenceJpa) {
		Exclusion excludeLoggin = POMFactory.eINSTANCE.createExclusion();
		excludeLoggin.setGroupId("dom4j");
		excludeLoggin.setArtifactId("dom4j");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(excludeLoggin);
	}

	protected void excludeJavassist(Dependency jbpmPersistenceJpa) {
		Exclusion excludeLoggin = POMFactory.eINSTANCE.createExclusion();
		excludeLoggin.setGroupId("javassist");
		excludeLoggin.setArtifactId("javassist");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(excludeLoggin);
	}

	protected void excludeJta(Dependency jbpmPersistenceJpa) {
		Exclusion excludeLoggin = POMFactory.eINSTANCE.createExclusion();
		excludeLoggin.setGroupId("javax.transaction");
		excludeLoggin.setArtifactId("jta");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(excludeLoggin);
	}

	protected void excludeJavaxPersistenceApi(Dependency jbpmPersistenceJpa) {
		Exclusion excludePersistenceApi = POMFactory.eINSTANCE.createExclusion();
		excludePersistenceApi.setGroupId("javax.persistence");
		excludePersistenceApi.setArtifactId("persistence-api");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(excludePersistenceApi);
	}

	protected void excludeHibernateCommonsAnnotations(Dependency jbpmPersistenceJpa) {
		Exclusion ecludeHibernateAnnotations2 = POMFactory.eINSTANCE.createExclusion();
		ecludeHibernateAnnotations2.setGroupId("org.hibernate");
		ecludeHibernateAnnotations2.setArtifactId("hibernate-commons-annotations");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(ecludeHibernateAnnotations2);
	}

	protected void excludeHibernateEntityManager(Dependency jbpmPersistenceJpa) {
		Exclusion excludeHibernateEntityManager = POMFactory.eINSTANCE.createExclusion();
		excludeHibernateEntityManager.setGroupId("org.hibernate");
		excludeHibernateEntityManager.setArtifactId("hibernate-entitymanager");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(excludeHibernateEntityManager);
	}

	protected void exludeHibernateAnnotations(Dependency jbpmPersistenceJpa) {
		Exclusion excludeHibernateAnnotations2 = POMFactory.eINSTANCE.createExclusion();
		excludeHibernateAnnotations2.setGroupId("org.hibernate");
		excludeHibernateAnnotations2.setArtifactId("hibernate-annotations");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(excludeHibernateAnnotations2);
	}

	protected void excludeHibernateCore(Dependency jbpmPersistenceJpa) {
		Exclusion excludeHibernateCore2 = POMFactory.eINSTANCE.createExclusion();
		excludeHibernateCore2.setGroupId("org.hibernate");
		excludeHibernateCore2.setArtifactId("hibernate-core");
		jbpmPersistenceJpa.getExclusions().getExclusion().add(excludeHibernateCore2);
	}

	@Override
	public Properties getParentPomProperties() {
		Properties p = super.getParentPomProperties();
		p.put("drools.version", "5.2.0.M1");
		p.put("jbpm.version", "5.0-SNAPSHOT");
		return p;
	}

	@Override
	public boolean hasFinalName() {
		return true;
	}

	@Override
	public String getPackaging() {
		return "jar";
	}

	@Override
	public SourceFolderDefinition getExampleTargetDir() {
		return config.getSourceFolderDefinition(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE);
	}
}
