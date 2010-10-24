package net.sf.nakeduml.textmetamodel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;

public class TextWorkspace extends TextFileNode {
	Set<TextOutputRoot> roots = new HashSet<TextOutputRoot>();

	public TextWorkspace() {
		super("Workspace");
	}

	public TextOutputRoot findOrCreateTextOutputRoot(String name) {
		for (TextOutputRoot r : roots) {
			if (r.name.equals(name)) {
				return r;
			}
		}
		TextOutputRoot result = new TextOutputRoot(this, name);
		roots.add(result);
		return result;
	}

	public Collection<TextOutputRoot> getTextOutputRoots() {
		return roots;
	}

	@Override
	public boolean hasContent(){
		return true;
	}
}
