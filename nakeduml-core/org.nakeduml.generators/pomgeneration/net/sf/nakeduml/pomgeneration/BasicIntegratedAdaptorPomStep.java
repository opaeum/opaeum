package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.metamodel.core.INakedRootObject;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = {},before = {},after = {},phase = PomGenerationPhase.class)
public class BasicIntegratedAdaptorPomStep extends PomGenerationStep{
	@Override
	public boolean isIntegrationStep(){
		return true;
	}
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> dependencies = getTestDepedencies();
		for(INakedRootObject rootObject:workspace.getPrimaryRootObjects()){
			addDependencyToRootObject(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC, rootObject, dependencies);
		}
		addHibernate(dependencies);
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}
	@Override
	public Properties getParentPomProperties(){
		Properties p = super.getParentPomProperties();
		p.put("jboss.home", "${env.JBOSS_HOME}");
		p.put("jboss.domain", "default");
		p.put("numl.version", PomGenerationPhase.NUML_VERSION);
		return p;
	}
	@Override
	public Plugin[] getPlugins(){
		Collection<Plugin> result = new ArrayList<Plugin>(Arrays.asList(super.getPlugins()));
		result.add(excludeIntegrationTests());
		return (Plugin[]) result.toArray(new Plugin[result.size()]);
	}
	@Override
	protected SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
	}
}
