package org.nakeduml.uml2uim;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.nakeduml.emf.extraction.EmfElementVisitor;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.ISourceFolderIdentifier;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.TextSourceFolderIdentifier;
import net.sf.nakeduml.pomgeneration.BasicWarPomStep;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@StepDependency(phase = ModelCopyPhase.class,requires={BasicWarPomStep.class})
public class ModelCopyStep extends EmfElementVisitor implements ITransformationStep{
	private NakedUmlConfig config;
	private TextWorkspace textWorkspace;
	private EmfWorkspace workspace;
	public void init(NakedUmlConfig config,TextWorkspace textWorkspace){
		this.config = config;
		this.textWorkspace = textWorkspace;
	}
	public void startVisiting(EmfWorkspace emfWorkspace){
		this.workspace =emfWorkspace;
		File dir = emfWorkspace.getResourceHelper().resolveUri(emfWorkspace.getDirectoryUri());
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
	protected SourceFolder getSourceFolder(SourceFolderDefinition outputRoot) {
		TextProject textProject = textWorkspace.findOrCreateTextProject(workspace.getIdentifier() + outputRoot.getProjectSuffix());
		SourceFolder or = textProject.findOrCreateSourceFolder(outputRoot.getSourceFolder(), outputRoot.cleanDirectories());
		return or;
	}
	protected void findOrCreateTextFile(CharArrayWriter outputBuilder, ISourceFolderIdentifier outputRootId, String... names) {
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(outputRootId);
		SourceFolder sourceFolder = this.getSourceFolder(outputRoot);
		sourceFolder.findOrCreateTextFile(Arrays.asList(names), new CharArrayTextSource(outputBuilder), outputRoot.overwriteFiles());
	}
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
}
