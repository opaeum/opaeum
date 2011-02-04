package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.metamodel.core.INakedProperty;

public class DataPopulatorPropertyEntry {

	private INakedProperty property;

	public INakedProperty getProperty() {
		return property;
	}

	public void setProperty(INakedProperty property) {
		this.property = property;
	}

	private String entityName;
	private DataPopulatorPropertyEntry parent;
	private List<DataPopulatorPropertyEntry> children = new ArrayList<DataPopulatorPropertyEntry>();
	private String value;
	private int level;
	private boolean many = true;

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

	public DataPopulatorPropertyEntry(String entityName) {
		super();
		this.level = 0;
		this.entityName = entityName;
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
			configurator.outputProperties(this.entityName, defaultValue);
		} else {
			configurator.outputProperties(getParent().getValue() + "." + this.entityName, defaultValue);
		}
		setValue(defaultValue.substring(1, defaultValue.length() - 1));
		for (DataPopulatorPropertyEntry child : children) {
			child.walk(configurator);
		}
	}

	public static void walk(List<DataPopulatorPropertyEntry> roots, ConfigurableCompositionPropertiesGenerator configurator) {
		for (DataPopulatorPropertyEntry root : roots) {
			List<DataPopulatorPropertyEntry> distinctChildren = root.getDisctinctChildren();
			for (DataPopulatorPropertyEntry child : distinctChildren) {
				configurator.outputProperties(root.value+"."+child.entityName.substring(0, child.entityName.length()-7)+".size", "3");
			}
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

		// List<DataPopulatorPropertyEntry> result = new
		// ArrayList<DataPopulatorPropertyEntry>();
		// result.add(root0);
		// uhm2(result, 0, 1);
		// for (DataPopulatorPropertyEntry dataPopulatorPropertyEntry : result)
		// {
		// dataPopulatorPropertyEntry.walk();
		// }

		System.out.println(root0.getDepth());

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

	public static void uhm2(List<DataPopulatorPropertyEntry> copiedLevel, int depth, int numberOfCopies) {
		List<DataPopulatorPropertyEntry> result = new ArrayList<DataPopulatorPropertyEntry>();
		for (DataPopulatorPropertyEntry dataPopulatorPropertyEntry : copiedLevel) {
			dataPopulatorPropertyEntry.getForLevel(result, depth);
		}
		if (result.isEmpty()) {
			return;
		}
		result = copyLevel(result, numberOfCopies);
		uhm2(result, ++depth, numberOfCopies);
		if (depth == 1) {
			copiedLevel.clear();
			copiedLevel.addAll(result);
		}

	}

}
