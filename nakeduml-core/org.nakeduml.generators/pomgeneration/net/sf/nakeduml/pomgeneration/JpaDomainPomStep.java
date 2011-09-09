package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = {},before = {},after = {},phase = PomGenerationPhase.class)
public class JpaDomainPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = getBasicDependencies(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		addNakedumlHibernate(result);
		// add provided dependencies from above
		addHibernate(result);
		addHsqlDbForTest(result);

		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	private void addNakedumlHibernate(Collection<Dependency> result){
		//only insert when BpmUsingJbpm5 is selected
		Dependency nakedUmlBpm= POMFactory.eINSTANCE.createDependency();
		nakedUmlBpm.setGroupId("org.nakeduml");
		nakedUmlBpm.setArtifactId("nakeduml-runtime-bpm-hibernate");
		nakedUmlBpm.setVersion("${numl.version}");
		nakedUmlBpm.setScope("compile");
		nakedUmlBpm.setType("jar");
		result.add(nakedUmlBpm);
		Dependency em = POMFactory.eINSTANCE.createDependency();
		em.setGroupId("org.hibernate");
		em.setArtifactId("hibernate-entitymanager");
		em.setVersion("3.4.0.GA");
		em.setScope("provided");
		em.setType("jar");
		result.add(em);
		Dependency nakedUmlUtil = POMFactory.eINSTANCE.createDependency();
		nakedUmlUtil.setGroupId("org.nakeduml");
		nakedUmlUtil.setArtifactId("nakeduml-runtime-jpa");
		nakedUmlUtil.setVersion("${numl.version}");
		nakedUmlUtil.setScope("compile");
		nakedUmlUtil.setType("jar");
		result.add(nakedUmlUtil);
		excludeSlf4j(nakedUmlUtil);
	}
	@Override
	public Plugin[] getPlugins(){
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
		Plugin sureFire = addSurefire();	
		result.add(sureFire);
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}
	@Override
	protected SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}
	@Override
	public Properties getParentPomProperties(){
		Properties props = super.getParentPomProperties();
		props.put("hibernate.version", HIBERNATE_VERSION);
		props.put("numl.version", PomGenerationPhase.NUML_VERSION);
		return props;
	}
}
