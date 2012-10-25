package org.opaeum.filegeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.textmetamodel.TextDirectory;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

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
	protected void visitParentsRecursively(TextOutputNode node){
		if(node != null){
			visitParentsRecursively(node.getParent());
			for(VisitSpec v:methodInvokers. beforeMethods){
				maybeVisit(node, v);
			}
		}
	}
	public void visitUpFirst(TextOutputNode element){
		visitParentsRecursively(element.getParent());
		visitOnly(element);
	}
}
