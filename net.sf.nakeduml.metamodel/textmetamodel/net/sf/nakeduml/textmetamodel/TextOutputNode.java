package net.sf.nakeduml.textmetamodel;

public abstract class TextOutputNode{
	protected TextOutputNode parent;
	protected String name;
	protected TextOutputNode(TextOutputNode parent,String name){
		this(name);
		this.parent = parent;
	}
	protected TextOutputNode(String name){
		super();
		this.name = name;
	}
	public TextOutputNode getParent(){
		return this.parent;
	}
	public String getName(){
		return name;
	}
	public abstract boolean hasContent();
}
