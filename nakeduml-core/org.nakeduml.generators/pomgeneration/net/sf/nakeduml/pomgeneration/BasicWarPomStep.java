package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.TextSourceFolderIdentifier;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;

@StepDependency(phase = PomGenerationPhase.class,requires = {
	BasicIntegratedAdaptorPomStep.class
})
public class BasicWarPomStep extends PomGenerationStep{
	@Override
	public Plugin[] getPlugins(){
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
		result.add(excludeIntegrationTests());
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}
	@Override
	public Dependency[] getDependencies(){
		List<Dependency> dependencies = new ArrayList<Dependency>();
		super.addDependencyToRootObject(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, model, dependencies);
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
	public boolean isIntegrationStep(){
		return true;
	}
}
