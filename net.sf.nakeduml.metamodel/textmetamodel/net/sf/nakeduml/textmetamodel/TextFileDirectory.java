package net.sf.nakeduml.textmetamodel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextFileDirectory extends TextFileNode{
	private Set<TextFileNode> children = new HashSet<TextFileNode>();
	public TextFileDirectory(TextFileNode parent,String name){
		super(parent, name);
	}
	public TextFileDirectory(){
		super(null, "root");
	}
	public Set<TextFileNode> getChildren(){
		return children;
	}
	public TextOutputRoot getOutputRoot(){
		return ((TextFileDirectory) getParent()).getOutputRoot();
	}
	public TextFile findOrCreateTextFile(List<String> path,TextSource source){
		TextFileNode root = findNode(path.get(0));
		if(path.size() == 1){
			if(root == null){
				root = new TextFile(this, path.get(0), source);
				children.add(root);
			}
			return (TextFile) root;
		}else{
			if(root == null){
				root = new TextFileDirectory(this, path.get(0));
				children.add(root);
			}
			return ((TextFileDirectory) root).findOrCreateTextFile(path.subList(1, path.size()), source);
		}
	}
	private TextFileNode findNode(String name){
		for(TextFileNode n:children){
			if(n.name.equals(name)){
				return n;
			}
		}
		return null;
	}
	public boolean hasChild(String name){
		TextFileNode child = findNode(name);
		return child != null && child.hasContent();
	}
	public String getRelativePath(){
		StringBuilder sb = new StringBuilder();
		appendNameToRelativePath(sb);
		return sb.substring(1);
	}
	protected void appendNameToRelativePath(StringBuilder sb){
		((TextFileDirectory) getParent()).appendNameToRelativePath(sb);
		sb.append("/");
		sb.append(getName());
	}
	@Override
	public boolean hasContent(){
		for(TextFileNode child:this.getChildren()){
			if(child.hasContent()){
				return true;
			}
		}
		return false;
	}
}
