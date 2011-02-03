package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
import java.util.List;

public class DataPopulatorPropertyEntry {

	private String entityName;
	private String entityNameWithName;
	private DataPopulatorPropertyEntry parent;
	private List<DataPopulatorPropertyEntry> children = new ArrayList<DataPopulatorPropertyEntry>();
	private String value;
	private int level;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DataPopulatorPropertyEntry(String entityName) {
		super();
		this.level = 0;
		this.entityName = entityName;
	}

	public DataPopulatorPropertyEntry(String entityName, String entityNameWithName) {
		this.entityName = entityName;
		this.entityNameWithName = entityNameWithName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public DataPopulatorPropertyEntry getParent() {
		return parent;
	}

	public void setParent(DataPopulatorPropertyEntry parent) {
		this.parent = parent;
		if (parent != null) {
			this.parent.children.add(this);
			this.level = parent.level+1;
		} else {
			this.level = 0;
		}
	}

	public List<DataPopulatorPropertyEntry> getChildren() {
		return children;
	}

	public void setChildren(List<DataPopulatorPropertyEntry> children) {
		this.children = children;
	}

	public void walk(OutputProperties outputProperties) {
		if (isRoot()) {
			outputProperties.outputProperties(this.entityName, value);
		} else {
			outputProperties.outputProperties(getParent().getEntityName() + "." + this.entityName, value);
		}
		for (DataPopulatorPropertyEntry child : children) {
			child.walk(outputProperties);
		}
	}

	public void walk() {
		System.out.println(entityName);
		for (DataPopulatorPropertyEntry child : children) {
			child.walk();
		}
	}
	
	public DataPopulatorPropertyEntry walkAndCopy(DataPopulatorPropertyEntry parent) {
		DataPopulatorPropertyEntry copy = copy(parent,"_x");
		List<DataPopulatorPropertyEntry> tempChildren = new ArrayList<DataPopulatorPropertyEntry>(children);
		for (DataPopulatorPropertyEntry child : tempChildren) {
			child.walkAndCopy(copy);
		}
		return copy;
	}

	public DataPopulatorPropertyEntry copy(DataPopulatorPropertyEntry parent, String suffix) {
		DataPopulatorPropertyEntry copy = new DataPopulatorPropertyEntry(entityName+suffix);
		copy.setParent(parent);
		List<DataPopulatorPropertyEntry> tempChildren = new ArrayList<DataPopulatorPropertyEntry>(children);
		for (DataPopulatorPropertyEntry child : tempChildren) {
			child.copy(copy, suffix);
		}
		return copy;
	}

	public boolean isRoot() {
		return getParent() == null;
	}

	public DataPopulatorPropertyEntry getRoot() {
		if (getParent() == null) {
			return this;
		} else {
			return getParent().getRoot();
		}
	}
	
	public static void getForLevel(List<DataPopulatorPropertyEntry> result, DataPopulatorPropertyEntry node, int level) {
		if (node.level==level) {
			result.add(node);
		} else {
			List<DataPopulatorPropertyEntry> tempChildren = new ArrayList<DataPopulatorPropertyEntry>(node.getChildren());
			for (DataPopulatorPropertyEntry child : tempChildren) {
				getForLevel(result, child, level);
			}
		}
	}

	public static void main(String[] args) {
		DataPopulatorPropertyEntry root = new DataPopulatorPropertyEntry("root");
		DataPopulatorPropertyEntry child1 = new DataPopulatorPropertyEntry("child1");
		child1.setParent(root);

		DataPopulatorPropertyEntry child11 = new DataPopulatorPropertyEntry("child11");
		child11.setParent(child1);
		DataPopulatorPropertyEntry child111 = new DataPopulatorPropertyEntry("child111");
		child111.setParent(child11);
		
		DataPopulatorPropertyEntry child2 = new DataPopulatorPropertyEntry("child2");
		child2.setParent(root);
		DataPopulatorPropertyEntry child3 = new DataPopulatorPropertyEntry("child3");
		child3.setParent(root);
		
//		DataPopulatorPropertyEntry copy = root.copy(null, "_1");
//		root.walk();
//		System.out.println("==========");
//		DataPopulatorPropertyEntry copy = root.walkAndCopy(null);
//		root.walk();
//		System.out.println("==========");
//		copy.walk();

		List<DataPopulatorPropertyEntry> level2 = new ArrayList<DataPopulatorPropertyEntry>(); 
		getForLevel(level2,root,1);
		for (DataPopulatorPropertyEntry dataPopulatorPropertyEntry : level2) {
			System.out.println(dataPopulatorPropertyEntry.getEntityName());
		}
	}
}
