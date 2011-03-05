package net.sf.nakeduml.pomgeneration;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import nl.klasse.octopus.model.IImportedElement;

public abstract class AbstractBasicJavaPomStep extends PomGenerationStep {

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
		jMockLegacy.setArtifactId("jmock-egacy");
		jMockLegacy.setVersion("2.5.1");
		jMockLegacy.setScope("test");
		jMockLegacy.setType("jar");
		result.add(jMockLegacy);
		Dependency testNg = POMFactory.eINSTANCE.createDependency();
		testNg.setGroupId("org.testng");
		testNg.setArtifactId("testng");
		testNg.setVersion("5.14");
		testNg.setScope("compile");
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

	protected void addDependencyToRootObject(String projectSuffix, INakedRootObject rootObject,Collection<Dependency> result ) {
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
