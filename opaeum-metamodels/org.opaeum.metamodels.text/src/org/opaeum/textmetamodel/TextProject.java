package org.opaeum.textmetamodel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TextProject extends TextOutputNode{
	Set<SourceFolder> sourceFolders = new HashSet<SourceFolder>();

	public TextProject(TextWorkspace textWorkspace, String name) {
		super(name);
		super.parent=textWorkspace;
		
	}


	public synchronized SourceFolder findOrCreateSourceFolder(String name, boolean shouldClean) {
		for (SourceFolder r : sourceFolders) {
			if (r.name.equals(name)) {
				return r;
			}
		}
		SourceFolder result = new SourceFolder(this, name,shouldClean);
		sourceFolders.add(result);
		return result;
	}

	public Collection<SourceFolder> getSourceFolders() {
		return sourceFolders;
	}

	@Override
	public boolean hasContent(){
		return sourceFolders.size()>0;
	}

}
