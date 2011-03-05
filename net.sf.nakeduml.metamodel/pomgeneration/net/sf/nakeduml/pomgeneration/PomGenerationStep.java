package net.sf.nakeduml.pomgeneration;

import java.util.Collection;
import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
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

	public final String getProjectName() {
		if (useWorkspaceName()) {
			return this.workspace.getDirectoryName() + getExampleTargetDir().getProjectSuffix();
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

	protected void addArquillian(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.arquillian");
		dependency.setArtifactId("arquillian-junit");
		dependency.setVersion("${arquillian.version}");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);
	}

	protected void addSeamServlet(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.seam.servlet");
		dependency.setArtifactId("seam-servlet-api");
		dependency.setVersion("${seam.servlet.version}");
		dependency.setScope("compile");
		dependencies.add(dependency);
	}

	protected void addCdi(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("javax.enterprise");
		dependency.setArtifactId("cdi-api");
		dependency.setVersion("1.0-SP1");
		dependency.setScope("provided");
		dependency.setType("jar");
		dependencies.add(dependency);
	}

	protected void addJbossJeeSpec(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("org.jboss.spec");
		dependency.setArtifactId("jboss-javaee-6.0");
		dependency.setVersion("1.0.0.Final");
		dependency.setScope("provided");
		dependency.setType("pom");
		dependencies.add(dependency);
	}

	protected void addJunit(Collection<Dependency> dependencies) {
		Dependency dependency = POMFactory.eINSTANCE.createDependency();
		dependency.setGroupId("junit");
		dependency.setArtifactId("junit");
		dependency.setVersion("4.8.2");
		dependency.setType("jar");
		dependency.setScope("test");
		dependencies.add(dependency);
	}
}
