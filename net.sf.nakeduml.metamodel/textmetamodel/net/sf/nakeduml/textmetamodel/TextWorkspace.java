package net.sf.nakeduml.textmetamodel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TextWorkspace extends TextOutputNode {
	Set<TextProject> projects = new HashSet<TextProject>();

	public TextWorkspace() {
		super("Workspace");
	}

	public TextProject findOrCreateTextProject(String name) {
		for (TextProject r : projects) {
			if (r.name.equals(name)) {
				return r;
			}
		}
		TextProject result = new TextProject(this, name);
		projects.add(result);
		return result;
	}

	public Collection<TextProject> getTextProjects() {
		return projects;
	}

	@Override
	public boolean hasContent(){
		return projects.size()>0;
	}

}
