package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.CharArrayTextSource;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Exclusion;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.Profile;
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(phase = PomGenerationPhase.class, requires = { IntegratedSeam3PomStep.class })
public class WarPomStep extends PomGenerationStep {

	
	@Override
	public Plugin[] getPlugins() {
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
		result.add(excludeIntegrationTests());
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}

	@Override
	public Dependency[] getDependencies() {
		List<Dependency> dependencies = new ArrayList<Dependency>();
		Dependency integrated = POMFactory.eINSTANCE.createDependency();
		integrated.setGroupId(config.getMavenGroupId());
		integrated.setArtifactId(workspace.getDirectoryName() + "-integrated");
		integrated.setVersion(config.getMavenGroupVersion());
		integrated.setScope("compile");
		dependencies.add(integrated);
		addJbossJeeSpec(dependencies);
		addCdi(dependencies);
		addSeamServlet(dependencies);
		addSeamServletImpl(dependencies);
		addSolderApi(dependencies);
		addSolderImpl(dependencies);
		addSeamConfig(dependencies);
		addArquillian(dependencies);
		addNumlTestAdaptor(dependencies);
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}

	private void addSeamConfig(List<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.config");
		dependency.setArtifactId("seam-config-xml");
		dependency.setVersion("3.0.0.Beta2");
		dependency.setScope("test");
		dependencies.add(dependency);
	}

	private void addSolderImpl(List<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-impl");
		dependency.setVersion("${seam.solder.version}");
		dependency.setScope("runtime");
		dependencies.add(dependency);
	}

	private void addSolderApi(List<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.solder");
		dependency.setArtifactId("seam-solder-api");
		dependency.setVersion("${seam.solder.version}");
		dependency.setScope("compile");
		Exclusion exclusion = POMFactory.eINSTANCE.createExclusion();
		exclusion.setGroupId("org.jboss.logging");
		exclusion.setArtifactId("jboss-logging");
		dependency.setExclusions(POMFactory.eINSTANCE.createExclusionsType());
		dependency.getExclusions().getExclusion().add(exclusion);
		dependencies.add(dependency);
	}

	@Override
	public Properties getParentPomProperties() {
		Properties p = super.getParentPomProperties();
		p.put("jboss.home", "${env.JBOSS_HOME}");
		p.put("jboss.domain", "default");
		p.put("arquillian.version", ARQUILLIAN_VERSION);
		return p;
	}

	@Override
	public boolean hasFinalName() {
		return true;
	}

	@Override
	public String getPackaging() {
		return "war";
	}

	@Override
	public OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE);
	}

	@Override
	public Profile[] getProfiles() {
		Profile[] profiles = new Profile[2];
		profiles[0] = createArquillianProfile();
		Profile profile = POMFactory.eINSTANCE.createProfile();
		profile.setId("copy-war");
		profile.setBuild(POMFactory.eINSTANCE.createBuildBase());
		profile.getBuild().setPlugins(POMFactory.eINSTANCE.createPluginsType());
		Plugin plugin = POMFactory.eINSTANCE.createPlugin();
		plugin.setGroupId("org.apache.maven.plugins");
		plugin.setArtifactId("maven-antrun-plugin");
		plugin.setVersion("1.1");
		plugin.setGoals(POMFactory.eINSTANCE.createGoalsType());
		profile.getBuild().getPlugins().getPlugin().add(plugin);
		PomUtil.addAnyElementWithContent(plugin.getGoals().getAny(), "goal", "run");
		plugin.setConfiguration(POMFactory.eINSTANCE.createConfigurationType2());
		AnyType taskAnyType = PomUtil.addEmptyAnyElement(plugin.getConfiguration().getAny(), "tasks");
		AnyType anyType = PomUtil.addEmptyAnyElement(taskAnyType.getAny(), "delete");
		PomUtil.addAnyAttribute(anyType, "dir", "${jboss.home}/server/${jboss.domain}/deploy/${project.build.finalName}.war");
		anyType = PomUtil.addEmptyAnyElement(taskAnyType.getAny(), "copy");
		PomUtil.addAnyAttribute(anyType, "todir", "${jboss.home}/server/${jboss.domain}/deploy/${project.build.finalName}.war/");
		anyType = PomUtil.addEmptyAnyElement(anyType.getAny(), "fileset");
		PomUtil.addAnyAttribute(anyType, "dir", "target/${project.build.finalName}");
		anyType = PomUtil.addEmptyAnyElement(anyType.getAny(), "include");
		PomUtil.addAnyAttribute(anyType, "name", "**/*");
		profiles[1] = profile;
		return profiles;
	}
}
