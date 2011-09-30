package org.opeum.filegeneration;

import java.util.Collection;
import java.util.Collections;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.visit.VisitorAdapter;
import org.opeum.textmetamodel.TextDirectory;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextProject;
import org.opeum.textmetamodel.TextWorkspace;

public class AbstractTextNodeVisitor extends VisitorAdapter<TextOutputNode,TextWorkspace> implements ITransformationStep{
	protected OpeumConfig config;
	public void initialize(OpeumConfig config2){
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
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
}
