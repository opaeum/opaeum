package net.sf.nakeduml.filegeneration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextFileDirectory;

@StepDependency(phase = FileGenerationPhase.class)
public class TextFileGenerator extends AbstractTextNodeVisitor implements TransformationStep {
	@VisitBefore(matchSubclasses = true)
	public void visitTextFileDirectory(TextFileDirectory textDir) {
		File dir = getDirectoryFor(textDir);
		if (!dir.exists()) {
			if (textDir.hasContent()) {
				dir.mkdir();
			}
		} else {
			for (File child : dir.listFiles()) {
				if (!textDir.hasChild(child.getName()) && isSourceDirectory(child)) {
					deleteTree(child);
				}
			}
		}
	}

	private boolean isSourceDirectory(File child) {
		boolean b = !child.isHidden() && !child.getName().startsWith(".");
		return b;
	}

	private File getDirectoryFor(TextFileDirectory textDir) {
		try {
			File mappedRoot = config.getMappedDestination(textDir.getOutputRoot().getName());
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
	public void visitTextFile(TextFile file) throws IOException {
		if (file.hasContent()) {
			File dir = getDirectoryFor(file.getParent());
			FileWriter fw = new FileWriter(new File(dir, file.getName()));
			if (file.getTextSource() instanceof PropertiesSource) {
				((PropertiesSource)file.getTextSource()).getProperties().store(fw, "NakedUml Generated");
			} else {
				fw.write(file.getContent());
			}
			fw.flush();
			fw.close();
		}
	}
}
