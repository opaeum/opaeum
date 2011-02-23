package net.sf.nakeduml.filegeneration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextDirectory;

@StepDependency(phase = FileGenerationPhase.class)
public class TextFileGenerator extends AbstractTextNodeVisitor implements TransformationStep {

	public TextFileGenerator() {
	}


	@VisitBefore(matchSubclasses = true)
	public void visitTextFileDirectory(TextDirectory textDir) {
		File dir = getDirectoryFor(textDir);
		if (!dir.exists()) {
			if (textDir.hasContent()) {
				dir.mkdir();
			}
		} else {
			for (File child : dir.listFiles()) {
				if (textDir.getSourceFolder().shouldClean() && !textDir.hasChild(child.getName()) && isSourceDirectory(child)) {
					deleteTree(child);
				}
			}
		}
	}

	private boolean isSourceDirectory(File child) {
		boolean b = !child.isHidden() && !child.getName().startsWith(".");
		return b;
	}

	private File getDirectoryFor(TextDirectory textDir) {
		try {
			File mappedRoot = config.getOutputRoot();
			File dir = new File(mappedRoot, textDir.getRelativePath());
			return dir;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void deleteTree(File tree) {
		if (tree.isDirectory()) {
			for (File f : tree.listFiles()) {
				if (!f.getName().startsWith(".")) {
					// don't mess around with eclipse internal files
					if (f.isFile()) {
						f.delete();
					} else if (f.isDirectory() && f.list().length == 0) {
						f.delete();
					} else {
						deleteTree(f);
					}
				}
			}
		}
		tree.delete();
	}

	@VisitBefore(matchSubclasses = false)
	public void visitTextFile(TextFile textFile) throws IOException {
		if (textFile.hasContent() ) {
			File dir = getDirectoryFor(textFile.getParent());
			dir.mkdirs();
			File osFile = new File(dir, textFile.getName());
			if (!osFile.exists() || textFile.overwrite()) {
				FileWriter fw = new FileWriter(osFile);
				fw.write(textFile.getContent());
				fw.flush();
				fw.close();
			}
		}
	}

}
