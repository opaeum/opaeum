package net.sf.nakeduml.uigeneration;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

import org.nakeduml.java.metamodel.OJPackage;

@PhaseDependency(after = JavaTransformationPhase.class/*,before = SeamTransformationPhase.class*/)
public class UimTransformationPhase implements TransformationPhase<AbstractUimTransformationStep>{
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private UserInteractionWorkspace uiWorkspace;
	@InputModel
	private OJPackage javaModel;
	private NakedUmlConfig config;
	public Object[] execute(List<AbstractUimTransformationStep> features,TransformationContext context){
		INakedPackage generatingModel = modelWorkspace.getGeneratingModelsOrProfiles().get(0);
		uiWorkspace.setName(generatingModel .getName());
		for(AbstractUimTransformationStep d:features){
			d.initialize(generatingModel, textWorkspace, uiWorkspace, javaModel,config);
			d.startVisiting(modelWorkspace);
		}
		StringBuilder xml = new StringBuilder();
		xml.append("<userInteractionWorkspace>");
		for(UserInteractionFolder dp:uiWorkspace.getChildFolder()){
			xml.append(dp.toXmlString());
		}
		xml.append("</userInteractionWorkspace>");
		return new Object[]{};
	}
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
}
