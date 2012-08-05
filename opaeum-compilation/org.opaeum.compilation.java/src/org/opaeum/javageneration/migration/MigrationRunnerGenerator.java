package org.opaeum.javageneration.migration;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.environment.MigrationContext;
import org.opaeum.runtime.environment.VersionNumber;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = MigrationGenerationPhase.class)
public class MigrationRunnerGenerator extends AbstractMigrationCodeGenerator{
	private OJAnnotatedOperation main;
	@Override
	protected int getThreadPoolSize(){
		return 0;
	}
	@Override
	public void release(){
		super.release();
		main = null;
	}
	@Override
	public void startVisiting(EmfWorkspace root){
		initRunner();
		super.startVisiting(root);
		for(Classifier cls:getElementsOfType(Classifier.class, root.getRootObjects())){
			if(!cls.isAbstract()
					&& (cls instanceof Class && getLibrary().getEndToComposite(cls) == null || EmfClassifierUtil.isBusinessCollaboration(cls))){
				OJPathName pn = classifierPathName(cls, getFromVersion());
				OJForStatement forEach = new OJForStatement("tmp", pn, "context.readFromRootObjects(" + pn + ".class)");
				main.getBody().addToStatements(forEach);
				OJPathName migratorPath = migratorPath(cls);
				OJAnnotatedField migrator = new OJAnnotatedField("migrator", migratorPath);
				migrator.setInitExp("(" + migratorPath.getLast() + ")context.getMigratorFor(tmp)");
				forEach.getBody().addToLocals(migrator);
				forEach.getBody().addToStatements("migrator.migrateTree(tmp)");
			}
		}
	}
	private void initRunner(){
		OJPackage pkg = findOrCreatePackage(new OJPathName(config.getMavenGroupId() + "." + workspace.getName().toLowerCase()));
		String name = NameConverter.capitalize(workspace.getName()) + workspace.getWorkspaceMappingInfo().getVersion().getSuffix()
				+ "MigrationRunner";
		OJAnnotatedClass runner = new OJAnnotatedClass(name);
		pkg.addToClasses(runner);
		createTextPath(runner, JavaSourceFolderIdentifier.MIGRATION_GEN_SRC);
		this.main = new OJAnnotatedOperation("main");
		this.main.setStatic(true);
		main.addParam("args", "String[]");
		main.addToThrows("Exception");
		runner.addToOperations(main);
		OJAnnotatedField context = new OJAnnotatedField("context", new OJPathName(MigrationContext.class.getName()));
		main.getBody().addToLocals(context);
		String toVersion = workspace.getWorkspaceMappingInfo().getVersion().toVersionString();
		String fromVersion = getFromVersion().toVersionString();
		runner.addToImports(VersionNumber.class.getName());
		context.setInitExp("new MigrationContext(new VersionNumber(\"" + fromVersion + "\"),new VersionNumber(\"" + toVersion + "\"),args[0])");
	}
	protected void visitComplexStructure(Classifier to){
		if(EmfClassifierUtil.isCompositionParticipant(to) && isPersistent(to)){
			if(!(to instanceof Interface)){
				Classifier toClass = (Classifier) to;
				Classifier fromClass = (Classifier) fromWorkspace.getModelElement(EmfWorkspace.getId(toClass));
				if(fromClass != null && !toClass.isAbstract()){
					OJPathName migratorPath = migratorPath(toClass);
					main.getBody().addToStatements(
							"context.registerMigrator(" + classifierPathName(fromClass, getFromVersion()) + ".class,new " + migratorPath + "())");
					main.getBody().addToStatements(
							"context.registerMigrator(" + classifierPathName(toClass, getToVersion()) + ".class,new " + migratorPath + "())");
				}
			}
		}
	}
}
