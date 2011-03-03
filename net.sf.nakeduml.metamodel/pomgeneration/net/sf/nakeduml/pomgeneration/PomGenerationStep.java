package net.sf.nakeduml.pomgeneration;

import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Plugin;
import org.apache.maven.pom.Profile;

public abstract class PomGenerationStep implements TransformationStep {
	protected NakedUmlConfig config;
	protected INakedModelWorkspace workspace;
	protected INakedRootObject model;

	protected abstract OutputRoot getExampleTargetDir();

	public void initialize(NakedUmlConfig config, INakedModelWorkspace workspace) {
		this.config = config;
		this.workspace = workspace;
	}
	public String getVersionVariable(){
		return "${" +workspace.getDirectoryName() + ".version}";
	}
	public String getPackaging() {
		return "jar";
	}

	public Dependency[] getDependencies() {
		return new Dependency[0];
	}

	public Plugin[] getPlugins() {
		return new Plugin[0];
	}

	public boolean useWorkspaceName() {
		return this.getExampleTargetDir().useWorkspaceName();
	}

	public String getProjectName() {
		if (useWorkspaceName()) {
			return this.workspace.getName() + getExampleTargetDir().getProjectSuffix();
		} else {
			return this.model.getFileName() + getExampleTargetDir().getProjectSuffix();
		}
	}

	public boolean hasFinalName() {
		return false;
	}


	public Properties getParentPomProperties() {
		return new Properties();
	}

	public Profile[] getProfiles() {
		return new Profile[0];
	}

	public void setModel(INakedRootObject model) {
		this.model = model;
	}

	public Properties getProperties() {
		return new Properties();
	}
}
