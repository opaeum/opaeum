package net.sf.nakeduml.pomgeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource;

import org.apache.maven.pom.ConfigurationType2;
import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.apache.maven.pom.Plugin;
import org.eclipse.emf.ecore.xml.type.AnyType;

@StepDependency(requires = { ProjectRootPomStep.class }, before = {}, after = { ProjectRootPomStep.class }, phase = PomGenerationPhase.class)
public class ProjectEarModulePomStep extends PomGenerationStep {

	@Override
	public boolean hasParent() {
		return true;
	}

	@Override
	public String getPackaging() {
		return "ear";
	}

	@Override
	public Dependency[] getDependencies() {
		Dependency[] result = new Dependency[3];
		result[0] = POMFactory.eINSTANCE.createDependency();
		result[0].setGroupId(getGroupId());
		result[0].setArtifactId(super.getName() + "-ejb");
		result[0].setType("ejb");
		result[1] = POMFactory.eINSTANCE.createDependency();
		result[1].setGroupId(getGroupId());
		result[1].setArtifactId(super.getName() + "-war");
		result[1].setType("war");
		result[2] = POMFactory.eINSTANCE.createDependency();
		result[2].setGroupId("org.nakeduml");
		result[2].setArtifactId("util");
		result[2].setType("ejb");
		return result;
	}

	@Override
	public String getTargetDir() {
		return JavaTextSource.NAKED_PROJECT_EAR_ROOT;
	}

	@Override
	public String getArtifactSuffix() {
		return "-ear";
	}

	@Override
	public String getName() {
		return super.getName() + "-ear";
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
	public Plugin[] getPlugins() {
		Plugin[] result = new Plugin[1];
		result[0] = POMFactory.eINSTANCE.createPlugin();
		result[0].setGroupId("org.apache.maven.plugins");
		result[0].setArtifactId("maven-ear-plugin");
		result[0].setVersion("2.4.2");
		ConfigurationType2 cfg = POMFactory.eINSTANCE.createConfigurationType2();
		addAnyElement(cfg.getAny(), "defaultLibBundleDir", "lib");
		AnyType anyType = addAnyElement(cfg.getAny(), "modules");
		anyType = addAnyElement(anyType.getAny(), "webModule");
		addAnyElement(anyType.getAny(), "groupId", getGroupId());
		addAnyElement(anyType.getAny(), "artifactId", super.getName() + "-war");
		addAnyElement(anyType.getAny(), "contextRoot", "/" + super.getName());
		result[0].setConfiguration(cfg);
		return result;
	}

	@Override
	public boolean hasFinalName() {
		return true;
	}

}
