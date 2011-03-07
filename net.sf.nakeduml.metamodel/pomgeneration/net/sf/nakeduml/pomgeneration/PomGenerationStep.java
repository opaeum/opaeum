package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.model.IImportedElement;

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
		dependency.setArtifactId("arquillian-testng");
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

	protected void addNumlTestAdaptor(Collection<Dependency> result) {
		Dependency numlAdaptor=POMFactory.eINSTANCE.createDependency();
		numlAdaptor.setGroupId("org.nakeduml");
		numlAdaptor.setArtifactId("nakeduml-test-adaptor");
		numlAdaptor.setScope("test");
		numlAdaptor.setVersion(PomGenerationPhase.NUML_VERSION);
		result.add(numlAdaptor);
	}

	protected Collection<Dependency> getTestDepedencies() {
		Collection<Dependency> result = new ArrayList<Dependency>();
		Dependency jMock = POMFactory.eINSTANCE.createDependency();
		jMock.setGroupId("org.jmock");
		jMock.setArtifactId("jmock");
		jMock.setVersion("2.5.1");
		jMock.setType("jar");
		jMock.setScope("test");
		result.add(jMock);
		Dependency jMockLegacy = POMFactory.eINSTANCE.createDependency();
		jMockLegacy.setGroupId("org.jmock");
		jMockLegacy.setArtifactId("jmock-legacy");
		jMockLegacy.setVersion("2.5.1");
		jMockLegacy.setScope("test");
		jMockLegacy.setType("jar");
		result.add(jMockLegacy);
		Dependency testNg = POMFactory.eINSTANCE.createDependency();
		testNg.setGroupId("org.testng");
		testNg.setArtifactId("testng");
		testNg.setVersion("5.14.9");
		testNg.setScope("test");
		testNg.setType("jar");
		result.add(testNg);
		return result;
	}

	protected Collection<Dependency> getBasicDependencies(String projectSuffix) {
		Collection<Dependency> result = getTestDepedencies();
		Collection<IImportedElement> imports = this.model.getImports();
		for (IImportedElement imp : imports) {
			if (imp.getElement() instanceof INakedRootObject) {
				addDependencyToRootObject(projectSuffix, (INakedRootObject) imp.getElement(),result);
			}
		}
		return result;
	}

	protected void addDependencyToRootObject(String projectSuffix, INakedRootObject rootObject, Collection<Dependency> result) {
		if (workspace.isPrimaryModel(rootObject)) {
			Dependency d = POMFactory.eINSTANCE.createDependency();
			d.setGroupId(config.getMavenGroupId());
			d.setArtifactId(rootObject.getFileName()+projectSuffix);
			d.setVersion(getVersionVariable());
			d.setScope("compile");
			d.setType("jar");
			result.add(d);
		}else{
			//TODO Model level stereotype, or numlconfig.properties get group id and version artifactid=filename
		}
	}
}
