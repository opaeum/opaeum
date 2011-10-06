package org.opeum.filegeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.textmetamodel.TextDirectory;
import org.opeum.textmetamodel.TextFile;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextProject;
import org.opeum.textmetamodel.TextWorkspace;

@StepDependency(phase = FileGenerationPhase.class,requires = TextFileDeleter.class,after = TextFileDeleter.class)
public class TextFileGenerator extends AbstractTextNodeVisitor implements ITransformationStep{
	boolean hasForked;
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	public TextFileGenerator(){
	}
	@Override
	public void startVisiting(TextWorkspace root){
		hasForked = false;
		super.startVisiting(root);
	}
	@Override
	protected boolean shouldMultiThread(ArrayList<TextOutputNode> children){
		final boolean b = !hasForked && children.size() >= 4;
		if(b){
			hasForked = true;
		}
		return b;// TODO make it more clever, look at the grandchildren
	}
	@VisitBefore(matchSubclasses = true)
	public void visitTextFileDirectory(TextDirectory textDir){
		getDirectoryFor(textDir);
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
	@VisitBefore(matchSubclasses = false)
	public void visitTextFile(TextFile textFile) throws IOException{
		if(textFile.hasContent()){
			File dir = getDirectoryFor(textFile.getParent());
			dir.mkdirs();
			File osFile = new File(dir, textFile.getName());
			if(!osFile.exists() || textFile.overwrite()){
				FileWriter fw = new FileWriter(osFile);
				fw.write(textFile.getContent());
				fw.flush();
				fw.close();
			}
		}
	}
}