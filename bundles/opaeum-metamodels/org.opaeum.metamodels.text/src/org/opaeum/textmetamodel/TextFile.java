package org.opaeum.textmetamodel;

import java.io.File;

public class TextFile extends TextOutputNode{
	private TextSource textSource;
	private char[] content;
	private boolean overwrite;
	private boolean isVersionDependent;
	public TextFile(TextDirectory parent,String fileName,boolean overwrite){
		super(parent, fileName);
		this.overwrite = overwrite;
	}
	public void setDependsOnVersion(boolean b){
		isVersionDependent = b;
	}
	public String getWorkspaceRelativePath(){
		String string = getOutputRoot().getParent().getName() + File.separator + getOutputRoot().getRelativePath() + File.separator + getSourceFolderRelativePath();
		return string; 
	}
	private String getSourceFolderRelativePath(){
		if(getParent() instanceof SourceFolder){
			return getName();
		}else{
			return ((TextDirectory) getParent()).getRelativePath() + File.separator + getName();
		}
	}
	/**
	 * No content constructor, so delete the file
	 * 
	 * @param textDirectory
	 * @param string
	 */
	public TextFile(TextDirectory parent,String name){
		super(parent, name);
		super.shouldDelete = true;
	}
	public TextSource getTextSource(){
		return this.textSource;
	}
	public void setTextSource(TextSource t){
		content = null;
		this.textSource = t;
	}
	public boolean overwrite(){
		return overwrite;
	}
	public char[] getContent(){
		if(content == null){
			content = textSource.toCharArray();
		}
		return content;
	}
	public SourceFolder getOutputRoot(){
		TextDirectory parent = getParent();
		while(true){
			if(parent instanceof SourceFolder){
				return (SourceFolder) parent;
			}else if(parent.getParent() instanceof TextDirectory){
				parent = (TextDirectory) parent.getParent();
			}else{
				throw new IllegalStateException("No TextOutputRoot found in file hierarchy");
			}
		}
	}
	@Override
	public TextDirectory getParent(){
		return (TextDirectory) super.getParent();
	}
	@Override
	public boolean hasContent(){
		return !shouldDelete() && textSource!=null && textSource.hasContent();
	}
	public void appendVersion(String version){
		if(isVersionDependent){
			name = name.replaceFirst("\\.", version + ".");
		}
	}
	public boolean isVersionDependent(){
		return isVersionDependent;
	}
}
