package net.sf.nakeduml.pomgeneration;

import java.util.Collection;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.metamodel.core.INakedRootObject;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;

@StepDependency(requires = {}, before = {}, after = {}, phase = PomGenerationPhase.class)
public class BasicJavaIntegratedAdaptorPomStep extends AbstractBasicJavaPomStep {
	//TODO properties  - jmock
	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> dependencies = getTestDepedencies();
		for (INakedRootObject rootObject : workspace.getPrimaryRootObjects()) {
			addDependencyToRootObject("-adaptor", rootObject,dependencies);
		}
		addNumlTestAdaptor(dependencies);
		addSeamServlet(dependencies);
		return (Dependency[]) dependencies.toArray(new Dependency[dependencies.size()]);
	}

	@Override
	public Plugin[] getPlugins() {
		return super.getPlugins();
	}

	@Override
	protected OutputRoot getExampleTargetDir() {
		return config.getOutputRoot(JavaTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC);
	}
}
