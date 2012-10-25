package org.opaeum.pomgeneration;

import java.util.Collection;
import java.util.HashSet;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.POMFactory;
import org.opaeum.feature.StepDependency;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolderDefinition;

@StepDependency(requires = {},before = {},after = {},phase = PomGenerationPhase.class)
public class MigrationPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = new HashSet<Dependency>();
		Dependency d = POMFactory.eINSTANCE.createDependency();
		d.setGroupId(config.getMavenGroupId());
		d.setVersion(getVersionVariable());
		d.setScope("compile");
		d.setType("jar");
		d.setVersion(super.migrationWorkspace.getFromWorkspace().getVersion().toVersionString());
		d.setArtifactId(workspace.getIdentifier() + super.migrationWorkspace.getFromWorkspace().getVersion().getSuffix());
		result.add(d);
		Dependency d2 = POMFactory.eINSTANCE.createDependency();
		d2.setGroupId(config.getMavenGroupId());
		d2.setVersion(getVersionVariable());
		d2.setScope("compile");
		d2.setType("jar");
		d2.setVersion(super.migrationWorkspace.getToWorkspace().getVersion().toVersionString());
		d2.setArtifactId(workspace.getIdentifier() + super.migrationWorkspace.getToWorkspace().getVersion().getSuffix());
		result.add(d2);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	@Override
	protected SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.MIGRATION_GEN_SRC);
	}
}
