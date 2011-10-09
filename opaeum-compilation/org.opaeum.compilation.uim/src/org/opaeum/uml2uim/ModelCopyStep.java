package org.opaeum.uml2uim;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opaeum.emf.extraction.EmfElementVisitor;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.textmetamodel.CharArrayTextSource;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;
import org.opaeum.textmetamodel.TextWorkspace;

@StepDependency(phase = ModelCopyPhase.class,requires = {})
public class ModelCopyStep extends EmfElementVisitor implements ITransformationStep{
	private OpaeumConfig config;
	private TextWorkspace textWorkspace;
	private EmfWorkspace workspace;
	public void init(OpaeumConfig config,TextWorkspace textWorkspace){
		this.config = config;
		this.textWorkspace = textWorkspace;
	}
	public void startVisiting(EmfWorkspace emfWorkspace){
		this.workspace = emfWorkspace;
		File dir = emfWorkspace.getUriToFileConverter().resolveUri(emfWorkspace.getDirectoryUri());
		List<String> path = new ArrayList<String>();
		path.add("uml");
		copyContent(dir, path);
	}
	private void copyContent(File dir,List<String> path){
		copyFiles(dir, path);
		File[] dirs = dir.listFiles(new FileFilter(){
			@Override
			public boolean accept(File pathname){
				return pathname.isDirectory();
			}
		});
		for(File childDir:dirs){
			List<String> newPath = new ArrayList<String>(path);
			newPath.add(childDir.getName());
			copyContent(childDir, newPath);
		}
	}
	private void copyFiles(File dir,List<String> dirPath){
		try{
			File[] files = dir.listFiles(new FileFilter(){
				@Override
				public boolean accept(File pathname){
					return pathname.getName().endsWith("uim") || pathname.getName().endsWith("uml");
				}
			});
			for(File file:files){
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				CharArrayWriter writer = new CharArrayWriter();
				while((line = reader.readLine()) != null){
					writer.write(line);
					writer.write('\n');
				}
				List<String> filePath = new ArrayList<String>(dirPath);
				filePath.add(file.getName());
				findOrCreateTextFile(writer, TextSourceFolderIdentifier.WEBAPP_RESOURCE, filePath.toArray(new String[filePath.size()]));
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	protected SourceFolder getSourceFolder(SourceFolderDefinition outputRoot){
		String identifier = outputRoot.getProjectNameStrategy() == ProjectNameStrategy.SUFFIX_ONLY ? "" : workspace.getIdentifier();
		TextProject textProject = textWorkspace.findOrCreateTextProject(identifier + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}
	protected void findOrCreateTextFile(CharArrayWriter outputBuilder,ISourceFolderIdentifier outputRootId,String...names){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(outputRootId);
		SourceFolder sourceFolder = this.getSourceFolder(outputRoot);
		sourceFolder.findOrCreateTextFile(Arrays.asList(names), outputRoot.overwriteFiles()).setTextSource(new CharArrayTextSource(outputBuilder));
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
}
