package net.sf.nakeduml.filegeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.textmetamodel.TextDirectory;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

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
		File dir = getDirectoryFor(textDir);
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
