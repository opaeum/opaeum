package net.sf.nakeduml.filegeneration;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.textmetamodel.TextDirectory;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class AbstractTextNodeVisitor extends VisitorAdapter<TextOutputNode,TextWorkspace> implements ITransformationStep{
	NakedUmlConfig config;
	public void initialize(NakedUmlConfig config2){
		this.config = config2;
	}
	@Override
	public Collection<? extends TextOutputNode> getChildren(TextOutputNode root){
		if(root.shouldDelete()){
			return Collections.emptySet();
		}else if(root instanceof TextWorkspace){
			return ((TextWorkspace) root).getTextProjects();
		}else if(root instanceof TextProject){
			return ((TextProject) root).getSourceFolders();
		}else if(root instanceof TextDirectory){
			return ((TextDirectory) root).getChildren();
		}else{
			return Collections.emptySet();
		}
	}
}
