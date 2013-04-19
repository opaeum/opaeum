package org.opaeum.textmetamodel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TextProject extends TextOutputNode{
	Set<SourceFolder> sourceFolders = new HashSet<SourceFolder>();
	private boolean isRegenerated=true;
	public TextProject(TextWorkspace textWorkspace,String name){
		super(name);
		super.parent = textWorkspace;
	}
	public synchronized SourceFolder findOrCreateSourceFolder(String name,boolean shouldClean){
		for(SourceFolder r:sourceFolders){
			if(r.name.equals(name)){
				if(!r.isRegenerated()){
					r.shouldClean(shouldClean);
				}
				return r;
			}
		}
		SourceFolder result = new SourceFolder(this, name, shouldClean);
		sourceFolders.add(result);
		return result;
	}
	public Collection<SourceFolder> getSourceFolders(){
		return sourceFolders;
	}
	@Override
	public boolean hasContent(){
		return sourceFolders.size() > 0;
	}
	public SourceFolder createExistingSourceFolder(String name){
		SourceFolder result = findOrCreateSourceFolder(name, false);
		result.setRegenerated(false);
		return result;
	}
	public void appendVersion(String suffix, Set<TextFile> affectedFiles){
		
		this.name = name + suffix;
		for(SourceFolder sf:sourceFolders){
			sf.appendVersion(suffix,affectedFiles);
		}
	}
	public boolean isRegenerated(){
		return isRegenerated;
	}
	public void setRegenerated(boolean b){
		this.isRegenerated=b;
	}
}
