package net.sf.nakeduml.textmetamodel;

public abstract class TextOutputNode{
	protected TextOutputNode parent;
	protected String name;
	boolean shouldDelete;
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
	public void markForDeletion(){
		shouldDelete = true;
	}
	public boolean shouldDelete(){
		return shouldDelete;
	}
	public void restore(){
		shouldDelete = false;
	}
	public String toString(){
		if(parent != null){
			return getParent().toString() + "/" + getName();
		}else{
			return getName();
		}
	}
}
