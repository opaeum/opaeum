package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.metamodel.core.INakedProperty;

public class DataPopulatorPropertyEntry {

	private INakedProperty property;
	//TODO refactor entityname to be 3 fields, name, name property and number
	private String entityName;
	private DataPopulatorPropertyEntry parent;
	private List<DataPopulatorPropertyEntry> children = new ArrayList<DataPopulatorPropertyEntry>();
	private String value;
	private int level;
	private boolean many = true;
	List<INakedProperty> properties = new ArrayList<INakedProperty>();

	public DataPopulatorPropertyEntry(String entityName) {
		super();
		this.level = 0;
		this.entityName = entityName;
	}
	
	public void addToOtherProperties(INakedProperty p) {
		properties.add(p);
	}
	
	public INakedProperty getProperty() {
		return property;
	}

	public void setProperty(INakedProperty property) {
		this.property = property;
	}

	public boolean isMany() {
		return many;
	}

	public void setMany(boolean many) {
		this.many = many;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
			this.level = parent.level + 1;
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

	public void walk(ConfigurableCompositionPropertiesGenerator configurator) {
		String defaultValue = configurator.calculateDefaultValue(this.property);
		if (isRoot()) {
			configurator.outputProperties(this.entityName, defaultValue.substring(1,defaultValue.length()-1));
		} else {
			configurator.outputProperties(getParent().getValue() + "." + this.entityName, defaultValue.substring(1,defaultValue.length()-1));
		}
		setValue(defaultValue.substring(1, defaultValue.length() - 1));
		for (INakedProperty p : properties) {
			String otherDefaultValue = configurator.calculateDefaultValue(p);
			if (isRoot()) {
				configurator.outputProperties(this.entityName.substring(0, this.entityName.length()-6) + p.getName() + this.entityName.substring(this.entityName.length()-2, this.entityName.length()), otherDefaultValue);
			} else {
				configurator.outputProperties(getParent().getValue() + "." + this.entityName.substring(0, this.entityName.length()-6) + p.getName() + this.entityName.substring(this.entityName.length()-2, this.entityName.length()), otherDefaultValue);
			}
		}
		for (DataPopulatorPropertyEntry child : children) {
			child.walk(configurator);
		}
	}

	public static void walk(List<DataPopulatorPropertyEntry> distinctRoots, ConfigurableCompositionPropertiesGenerator configurator) {
		for (DataPopulatorPropertyEntry root : distinctRoots) {
			if (root.isRoot()) {
				configurator.outputProperties(root.entityName.substring(0,root.entityName.length()-7) + ".size", "3");
			} else {
				configurator.outputProperties(root.getParent().value + "." + root.entityName.substring(0, root.entityName.length() - 7) + ".size", "3");
			}
			List<DataPopulatorPropertyEntry> distinctChildren = root.getDisctinctChildren();
			walk(distinctChildren, configurator);
		}
	}

	public List<DataPopulatorPropertyEntry> getDisctinctChildren() {

		Map<INakedProperty, DataPopulatorPropertyEntry> distinctNodeMap = new HashMap<INakedProperty, DataPopulatorPropertyEntry>();
		for (DataPopulatorPropertyEntry child : children) {
			if (!distinctNodeMap.containsKey(child.getProperty())) {
				distinctNodeMap.put(child.getProperty(), child);
			}
		}
		return new ArrayList<DataPopulatorPropertyEntry>(distinctNodeMap.values());
	}

	public void walk() {
		System.out.println(getParent() == null ? entityName : getParent().getEntityName() + "::" + entityName);
		for (DataPopulatorPropertyEntry child : children) {
			child.walk();
		}
	}

	public DataPopulatorPropertyEntry copy(DataPopulatorPropertyEntry parent) {
		DataPopulatorPropertyEntry copy = new DataPopulatorPropertyEntry(entityName);
		copy.setParent(parent);
		copy.setProperty(getProperty());
		copy.properties=new ArrayList<INakedProperty>(this.properties);
		List<DataPopulatorPropertyEntry> tempChildren = new ArrayList<DataPopulatorPropertyEntry>(children);
		for (DataPopulatorPropertyEntry child : tempChildren) {
			child.copy(copy);
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

	public void getForLevel(List<DataPopulatorPropertyEntry> result, int level) {
		if (this.level == level) {
			result.add(this);
		} else {
			List<DataPopulatorPropertyEntry> tempChildren = new ArrayList<DataPopulatorPropertyEntry>(this.getChildren());
			for (DataPopulatorPropertyEntry child : tempChildren) {
				child.getForLevel(result, level);
			}
		}
	}

	public int getDepth() {
		return getDepth(0);
	}

	private int getDepth(Integer depth) {
		if (level > depth.intValue()) {
			depth = level;
		}
		List<DataPopulatorPropertyEntry> tempChildren = new ArrayList<DataPopulatorPropertyEntry>(getChildren());
		for (DataPopulatorPropertyEntry child : tempChildren) {
			depth = child.getDepth(depth);
		}
		return depth;
	}

	public void rename(String suffix) {
		setEntityName(getEntityName().substring(0, getEntityName().length() - 1) + suffix);
	}

	public static void main(String[] args) {
		DataPopulatorPropertyEntry root0 = new DataPopulatorPropertyEntry("root0.name0");
		DataPopulatorPropertyEntry child0 = new DataPopulatorPropertyEntry("child0.name0");
		child0.setParent(root0);
		DataPopulatorPropertyEntry child1 = new DataPopulatorPropertyEntry("child1.name0");
		child1.setMany(false);
		child1.setParent(root0);
		DataPopulatorPropertyEntry child2 = new DataPopulatorPropertyEntry("child2.name0");
		child2.setParent(root0);
		DataPopulatorPropertyEntry child00 = new DataPopulatorPropertyEntry("child00.name0");
		child00.setParent(child0);

		List<DataPopulatorPropertyEntry> result = new ArrayList<DataPopulatorPropertyEntry>();
		result.add(root0);
		copyTreeRecursive(result, 0, 2);
		for (DataPopulatorPropertyEntry dataPopulatorPropertyEntry : result) {
			dataPopulatorPropertyEntry.walk();
		}
	}

	public static List<DataPopulatorPropertyEntry> copyLevel(List<DataPopulatorPropertyEntry> levels, int numberOfCopies) {
		List<DataPopulatorPropertyEntry> result = new ArrayList<DataPopulatorPropertyEntry>();
		result.addAll(levels);
		for (DataPopulatorPropertyEntry levelNode : levels) {
			int i = 0;
			while (i < numberOfCopies) {
				i++;
				DataPopulatorPropertyEntry levelCopy = levelNode.copy(levelNode.getParent());
				levelCopy.rename(String.valueOf(i));
				result.add(levelCopy);
			}
		}
		return result;
	}

	public static void copyTreeRecursive(List<DataPopulatorPropertyEntry> copiedLevel, int depth, int numberOfCopies) {
		List<DataPopulatorPropertyEntry> result = new ArrayList<DataPopulatorPropertyEntry>();
		for (DataPopulatorPropertyEntry dataPopulatorPropertyEntry : copiedLevel) {
			dataPopulatorPropertyEntry.getForLevel(result, depth);
		}
		if (result.isEmpty()) {
			return;
		}
		result = copyLevel(result, numberOfCopies);
		copyTreeRecursive(result, ++depth, numberOfCopies);
		if (depth == 1) {
			copiedLevel.clear();
			copiedLevel.addAll(result);
		}

	}

}
