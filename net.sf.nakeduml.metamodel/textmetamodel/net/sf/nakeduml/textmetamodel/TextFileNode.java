package net.sf.nakeduml.textmetamodel;

public abstract class TextFileNode{
	protected TextFileNode parent;
	protected String name;
	protected TextFileNode(TextFileNode parent,String name){
		this(name);
		this.parent = parent;
	}
	protected TextFileNode(String name){
		super();
		this.name = name;
	}
	public TextFileNode getParent(){
		return this.parent;
	}
	public String getName(){
		return name;
	}
	public abstract boolean hasContent();
}
