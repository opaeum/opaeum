package net.sf.nakeduml.pomgeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.ExecutionsType;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.PluginExecution;
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(requires = { ProjectRootPomStep.class }, before = {}, after = {ProjectRootPomStep.class}, phase = PomGenerationPhase.class)
public class ProjectWarPomStep extends PomGenerationStep {

	@Override
	public boolean hasFinalName() {
		return true;
	}

	public String getParentGroupId() {
		return super.getGroupId();
	}

	public String getParentArtifactId() {
		return super.getName();
	}

	public String getParentVersion() {
		return "0.0.1";
	}

	@Override
	public boolean hasParent() {
		return true;
	}

	@Override
	public String getPackaging() {
		return "war";
	}

	@Override
	public String getTargetDir() {
		return JavaTextSource.NAKED_PROJECT_WAR_ROOT;
	}

	@Override
	public String getArtifactSuffix() {
		return "-war";
	}

	@Override
	public Plugin[] getPlugins() {
		Plugin[] result = new Plugin[2];
		result[0] = POMFactory.eINSTANCE.createPlugin();
		result[0].setGroupId("org.apache.maven.plugins");
		result[0].setArtifactId("maven-compiler-plugin");
		
		result[1] = POMFactory.eINSTANCE.createPlugin();
		result[1].setGroupId("org.codehaus.mojo");
		result[1].setArtifactId("build-helper-maven-plugin");

		ExecutionsType execution = POMFactory.eINSTANCE.createExecutionsType();
		PluginExecution pluginExecution = POMFactory.eINSTANCE.createPluginExecution();
		execution.getExecution().add(pluginExecution);
		pluginExecution.setId("add-source");
		pluginExecution.setPhase("generate-sources");
		pluginExecution.setGoals(POMFactory.eINSTANCE.createGoalsType1());
		pluginExecution.getGoals().getGoal().add("add-source");
		
		pluginExecution.setConfiguration(POMFactory.eINSTANCE.createConfigurationType3());
		AnyType anyType = addAnyElement(pluginExecution.getConfiguration().getAny(), "sources");
		addAnyElement(anyType.getAny(),"source", "src/main/webapp");
		
		result[1].setExecutions(execution);
		
		return result;
	}

	@Override
	public String getName() {
		return super.getName() + "-war";
	}

}
