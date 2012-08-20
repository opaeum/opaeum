package org.opaeum.javageneration.migration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.metamodel.workspace.MigrationWorkspace;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.TextWorkspace;

@PhaseDependency(after = {},before = {})
public class MigrationGenerationPhase implements TransformationPhase<AbstractMigrationCodeGenerator,Element>{
	@InputModel
	MigrationWorkspace workspace;
	@InputModel
	OJWorkspace javaModel;
	@InputModel
	TextWorkspace textWorkspace;
	private Collection<AbstractMigrationCodeGenerator> steps;
	private OpaeumConfig config;
	@Override
	public void execute(TransformationContext context){
		String versionString = workspace.getToWorkspace().getWorkspaceMappingInfo().getVersion().getSuffix();
		config.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_GEN_SRC, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, versionString + "-migrator", "src/main/generated-java");
		config.defineSourceFolder(JavaSourceFolderIdentifier.MIGRATION_SRC, ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX, versionString + "-migrator", "src/main/java").dontCleanDirectoriesOrOverwriteFiles();
		for(AbstractMigrationCodeGenerator s:this.steps){
			s.startVisiting(workspace.getToWorkspace());
		}
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		// No can do, yet
		return Collections.emptySet();
	}
	@Override
	public void initialize(OpaeumConfig config,List<AbstractMigrationCodeGenerator> features){
		this.config = config;
		this.steps = features;
	}
	@Override
	public Collection<AbstractMigrationCodeGenerator> getSteps(){
		return this.steps;
	}
	@Override
	public void initializeSteps(){
		for(AbstractMigrationCodeGenerator s:steps){
			s.initialize(config, javaModel, textWorkspace, workspace);
		}
	}
	@Override
	public void release(){
		this.javaModel=null;
		this.textWorkspace=null;
		this.workspace=null;
		for(AbstractMigrationCodeGenerator m:this.steps){
			m.release();
		}
		
	}
}
