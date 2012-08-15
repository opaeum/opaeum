package org.opaeum.javageneration.migration;

import java.util.HashSet;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.metamodel.workspace.MigrationWorkspace;
import org.opaeum.runtime.environment.VersionNumber;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class AbstractMigrationCodeGenerator extends AbstractStructureVisitor{
	@Override
	public void release(){
		super.release();
		this.fromWorkspace=null;
	}
	protected EmfWorkspace fromWorkspace;
	@Override
	protected abstract void visitComplexStructure(Classifier umlOwner);
	protected final OJPathName migratorPath(Classifier toClass){
		OJPathName pkg = ojUtil.packagePathname(toClass.getNamespace()).getCopy();
		pkg.addToNames(toClass.getName() + getFromVersion().getSuffix() + "Migrator");
		return pkg;
	}
	protected final VersionNumber getFromVersion(){
		return fromWorkspace.getWorkspaceMappingInfo().getVersion();
	}
	protected final VersionNumber getToVersion(){
		return workspace.getWorkspaceMappingInfo().getVersion();
	}
	protected final OJPathName classifierPathName(Type a,VersionNumber version){
		if(EmfClassifierUtil.isSimpleType(a ) || ojUtil.getCodeGenerationStrategy( (Classifier) a)==CodeGenerationStrategy.NO_CODE){
			return ojUtil.classifierPathname(a);
		}else{
			return new OJPathName(ojUtil.classifierPathname(a).toJavaString()+ version.getSuffix());
		}
	}
	@Override
	protected void visitProperty(Classifier owner,PropertyMap buildStructuralFeatureMap){
	}
	public void initialize(OpaeumConfig config,OJWorkspace javaModel,TextWorkspace textWorkspace,MigrationWorkspace workspace){
		super.javaModel = javaModel;
		super.textWorkspace = textWorkspace;
		super.workspace = workspace.getToWorkspace();
		this.fromWorkspace = workspace.getFromWorkspace();
		super.config = config;
		this.textFiles=new HashSet<TextOutputNode>();
	}
}
