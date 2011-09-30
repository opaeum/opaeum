package org.opeum.filegeneration;

import java.io.File;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.textmetamodel.TextDirectory;
import org.opeum.textmetamodel.TextProject;

@StepDependency(phase = FileGenerationPhase.class)
public class TextFileDeleter extends AbstractTextNodeVisitor implements ITransformationStep{
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public TextFileDeleter(){
	}
	@VisitBefore(matchSubclasses = true)
	public void visitTextFileDirectory(TextDirectory textDir){
		File dir = getDirectoryFor(textDir);
		if(!dir.exists()){
			if(textDir.hasContent()){
				dir.mkdir();
			}
		}else{
			for(File child:dir.listFiles()){
				if(textDir.getSourceFolder().shouldClean() && !textDir.hasChild(child.getName()) && isSourceDirectory(child)){
					deleteTree(child);
				}
			}
		}
	}
	private boolean isSourceDirectory(File child){
		boolean b = !child.isHidden() && !child.getName().startsWith(".");
		return b;
	}
	private File getDirectoryFor(TextDirectory textDir){
		try{
			File mappedRoot = config.getOutputRoot();
			if(!mappedRoot.exists()){
				mappedRoot.mkdirs();
			}
			TextProject textProject = (TextProject) textDir.getSourceFolder().getParent();
			File projectDir = new File(mappedRoot, textProject.getName());
			if(!projectDir.exists() && textProject.hasContent()){
				projectDir.mkdirs();
			}
			File dir = new File(projectDir, textDir.getRelativePath());
			return dir;
		}catch(RuntimeException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	private void deleteTree(File tree){
		if(tree.isDirectory()){
			for(File f:tree.listFiles()){
				if(!f.getName().startsWith(".")){
					// don't mess around with eclipse internal files
					if(f.isFile() || (f.isDirectory() && f.list().length == 0)){
						try{
							f.delete();
						}catch(Exception e){
						}
					}else{
						deleteTree(f);
					}
				}
			}
		}
		try{
			tree.delete();
		}catch(Exception e){
		}
	}
}
