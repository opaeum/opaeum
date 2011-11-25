package org.opaeum.javageneration.migration;

import java.util.HashSet;

import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.CodeGenerationStrategy;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
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
	protected INakedModelWorkspace fromWorkspace;
	@Override
	protected abstract void visitComplexStructure(INakedComplexStructure umlOwner);
	protected final OJPathName migratorPath(ICompositionParticipant toEntity){
		OJPathName pkg = OJUtil.packagePathname(toEntity.getNameSpace()).getCopy();
		pkg.addToNames(toEntity.getMappingInfo().getJavaName().getAsIs() + getFromVersion().getSuffix() + "Migrator");
		return pkg;
	}
	protected final VersionNumber getFromVersion(){
		return fromWorkspace.getWorkspaceMappingInfo().getVersion();
	}
	protected final VersionNumber getToVersion(){
		return workspace.getWorkspaceMappingInfo().getVersion();
	}
	protected final OJPathName classifierPathName(INakedClassifier a,VersionNumber version){
		if(a instanceof INakedSimpleType || a.getCodeGenerationStrategy()==CodeGenerationStrategy.NO_CODE){
			return new OJPathName(a.getMappingInfo().getQualifiedJavaName());
		}else{
			return new OJPathName(a.getMappingInfo().getQualifiedJavaName() + version.getSuffix());
		}
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
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
