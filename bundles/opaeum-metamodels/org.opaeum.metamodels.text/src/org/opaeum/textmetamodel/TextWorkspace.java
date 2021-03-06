package org.opaeum.textmetamodel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TextWorkspace extends TextOutputNode{
	Set<TextProject> projects = new HashSet<TextProject>();
	public TextWorkspace(){
		super("Workspace");
	}
	public synchronized TextProject findOrCreateTextProject(String name){
		TextProject result = findTextProject(name);
		if(result == null){
			result = new TextProject(this, name);
			projects.add(result);
		}
		if(!result.isRegenerated()){
			result.setRegenerated(true);
		}
		
		return result;
	}
	public TextProject findTextProject(String name){
		for(TextProject r:projects){
			if(r.name.equals(name)){
				return r;
			}
		}
		return null;
	}
	public Collection<TextProject> getTextProjects(){
		return projects;
	}
	@Override
	public boolean hasContent(){
		return projects.size() > 0;
	}
	public Set<TextFile> prepareForVersioning(String suffix){
		Set<TextFile> affectedFiles = new HashSet<TextFile>();
		for(TextProject textProject:new HashSet<TextProject>(getTextProjects())){
			textProject.appendVersion(suffix, affectedFiles);
		}
		return affectedFiles;
	}
	public TextProject createExistingTextProject(String name){
		TextProject result = findOrCreateTextProject(name);
		result.setRegenerated(false);
		return result;
	}
}
