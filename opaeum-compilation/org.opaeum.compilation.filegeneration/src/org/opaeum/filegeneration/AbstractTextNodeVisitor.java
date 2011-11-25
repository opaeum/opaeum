package org.opaeum.filegeneration;

import java.util.Collection;
import java.util.Collections;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.visit.VisitorAdapter;
import org.opaeum.textmetamodel.TextDirectory;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public class AbstractTextNodeVisitor extends VisitorAdapter<TextOutputNode,TextWorkspace> implements ITransformationStep{
	protected OpaeumConfig config;
	public void initialize(OpaeumConfig config2){
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
	public void release(){
	}
}
