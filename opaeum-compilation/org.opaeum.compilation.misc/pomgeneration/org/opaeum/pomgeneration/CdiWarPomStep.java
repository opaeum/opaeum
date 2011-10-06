package org.opaeum.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.opaeum.feature.StepDependency;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.Profile;
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(phase = PomGenerationPhase.class,requires = {
	BasicIntegratedAdaptorPomStep.class
})
public class CdiWarPomStep extends PomGenerationStep{
	@Override
	public Plugin[] getPlugins(){
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
		result.add(excludeIntegrationTests());
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}
	@Override
	public Dependency[] getDependencies(){
		List<Dependency> dependencies = new ArrayList<Dependency>();
		addJbossJee6Spec(dependencies);
		addCdi(dependencies);
		addSeamServlet(dependencies);
		addSeamServletImpl(dependencies);
		addSeamConfig(dependencies);
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}
	@Override
	public Properties getParentPomProperties(){
		Properties p = super.getParentPomProperties();
		p.put("jboss.home", "${env.JBOSS_HOME}");
		p.put("jboss.domain", "default");
		p.put("seam.servlet.version", "3.0.0.Final");
		return p;
	}
	@Override
	public boolean hasFinalName(){
		return true;
	}
	@Override
	public String getPackaging(){
		return "war";
	}
	@Override
	public SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(TextSourceFolderIdentifier.WEBAPP_RESOURCE);
	}
	@Override
	public Profile[] getProfiles(){
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
	@Override
	public boolean isIntegrationStep(){
		return true;
	}
}
