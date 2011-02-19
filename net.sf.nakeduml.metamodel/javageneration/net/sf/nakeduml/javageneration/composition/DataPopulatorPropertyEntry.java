package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.nakeduml.metamodel.core.INakedProperty;

public class DataPopulatorPropertyEntry {

	// TODO refactor entityname to be 3 fields, name, name property and number
	private String entityName;
	private String entityQualifiedName;
	private DataPopulatorPropertyEntry parent;
	private List<DataPopulatorPropertyEntry> children = new ArrayList<DataPopulatorPropertyEntry>();
	private String value;
	private int level;
	private boolean many = true;
	private boolean isOne = false;
	private boolean isInterface = false;
	private String oneValue = "";
	private String implementingInterface = "";
	private String oneName = "";
	List<INakedProperty> properties = new ArrayList<INakedProperty>();

	public DataPopulatorPropertyEntry(int level, String entityQualifiedName, String entityName) {
		super();
		this.level = level;
		this.entityQualifiedName = entityQualifiedName;
		this.entityName = entityName;
	}
	
	public DataPopulatorPropertyEntry(int level, String entityQualifiedName, String entityName, boolean isInterface, String implementingInterface) {
		super();
		this.level = level;
		this.entityQualifiedName = entityQualifiedName;
		this.entityName = entityName;
		this.isInterface = isInterface;
		this.implementingInterface = implementingInterface;
	}	

	public DataPopulatorPropertyEntry(String entityQualifiedName, String entityName, boolean isOne, String oneName, String oneValue) {
		super();
		this.entityQualifiedName = entityQualifiedName;
		this.entityName = entityName;
		this.isOne = isOne;
		this.oneName = oneName;
		this.oneValue = oneValue;
		this.level = 0;
	}

	public int getLevel() {
		return level;
	}

	public String getEntityQualifiedName() {
		return entityQualifiedName;
	}

	public String getOneValue() {
		return oneValue;
	}

	public void setOneValue(String oneValue) {
		this.oneValue = oneValue;
	}

	public void addToOtherProperties(INakedProperty p) {
		properties.add(p);
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
		}
	}

	public List<DataPopulatorPropertyEntry> getChildren() {
		return children;
	}

	public void setChildren(List<DataPopulatorPropertyEntry> children) {
		this.children = children;
	}

	public void outputCompositeProperties(ConfigurableCompositionPropertiesGenerator configurator) {
		String defaultValue = UUID.randomUUID().toString();
		
		if (isRoot()) {
			configurator.outputProperties(this.entityName, defaultValue.substring(1, defaultValue.length() - 1));
		} else {
			configurator.outputProperties(getParent().getValue() + "." + this.entityName, defaultValue.substring(1, defaultValue.length() - 1));
		}
		setValue(defaultValue.substring(1, defaultValue.length() - 1));
		for (INakedProperty p : properties) {
			String otherDefaultValue = configurator.calculateDefaultStringValue(p);
			if (isRoot()) {
				configurator.outputProperties(
						this.entityName.substring(0, this.entityName.length() - 5) + p.getName()
								+ this.entityName.substring(this.entityName.length() - 2, this.entityName.length()), otherDefaultValue);
			} else {
				configurator.outputProperties(getParent().getValue() + "." + this.entityName.substring(0, this.entityName.length() - 5) + p.getName()
						+ this.entityName.substring(this.entityName.length() - 2, this.entityName.length()), otherDefaultValue);
			}
		}
		for (DataPopulatorPropertyEntry child : children) {
			child.outputCompositeProperties(configurator);
		}
	}

	public void outputToOneProperties(ConfigurableCompositionPropertiesGenerator configurator) {
		if (isOne) {
			configurator.outputProperties(value + "." + oneName, oneValue);
		}
		for (DataPopulatorPropertyEntry child : children) {
			child.outputToOneProperties(configurator);
		}
	}
	
	public void outputToCompositeOneInterface(ConfigurableCompositionPropertiesGenerator configurator) {
		if (isInterface) {
			configurator.outputProperties(value, implementingInterface);
		}
		for (DataPopulatorPropertyEntry child : children) {
			child.outputToCompositeOneInterface(configurator);
		}
	}	
	
	public static void outputSizeProperties(List<DataPopulatorPropertyEntry> nodes, ConfigurableCompositionPropertiesGenerator configurator, int size) {
		for (DataPopulatorPropertyEntry node : nodes) {
			if (node.isRoot()) {
				configurator.outputProperties(node.entityName.substring(0, node.entityName.length() - 6) + ".size", String.valueOf(size));
			}
			List<DataPopulatorPropertyEntry> distinctChildren = node.getDisctinctChildren();
			for (DataPopulatorPropertyEntry child : distinctChildren) {
				configurator.outputProperties(child.getParent().value + "." + child.entityName.substring(0, child.entityName.length() - 6) + ".size", String.valueOf(size));
			}
			outputSizeProperties(node.getChildren(), configurator, size);
		}
	}

	public List<DataPopulatorPropertyEntry> getDisctinctChildren() {

		Map<String, DataPopulatorPropertyEntry> distinctNodeMap = new HashMap<String, DataPopulatorPropertyEntry>();
		for (DataPopulatorPropertyEntry child : children) {
			if (!distinctNodeMap.containsKey(child.getEntityQualifiedName())) {
				distinctNodeMap.put(child.getEntityQualifiedName(), child);
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
		DataPopulatorPropertyEntry copy = new DataPopulatorPropertyEntry(level,entityQualifiedName, entityName);
		copy.setParent(parent);
//		copy.setProperty(getProperty());
		copy.properties = new ArrayList<INakedProperty>(this.properties);
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
		DataPopulatorPropertyEntry root0 = new DataPopulatorPropertyEntry(0,null, "root0.name0");
		DataPopulatorPropertyEntry child0 = new DataPopulatorPropertyEntry(1,null, "child0.name0");
		child0.setParent(root0);
		DataPopulatorPropertyEntry child1 = new DataPopulatorPropertyEntry(1,null, "child1.name0");
		child1.setMany(false);
		child1.setParent(root0);
		DataPopulatorPropertyEntry child2 = new DataPopulatorPropertyEntry(1,null, "child2.name0");
		child2.setParent(root0);
		DataPopulatorPropertyEntry child00 = new DataPopulatorPropertyEntry(2,null, "child00.name0");
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

	public void getEntityInstances(List<DataPopulatorPropertyEntry> entities, String entityQualifiedName) {
		if (this.entityQualifiedName.equals(entityQualifiedName)) {
			entities.add(this);
		} else {
			List<DataPopulatorPropertyEntry> tempChildren = new ArrayList<DataPopulatorPropertyEntry>(this.getChildren());
			for (DataPopulatorPropertyEntry child : tempChildren) {
				child.getEntityInstances(entities, entityQualifiedName);
			}
		}
	}

	public DataPopulatorPropertyEntry getCommonAncestor(DataPopulatorPropertyEntry one) {
		if (entityQualifiedName.equals(one.entityQualifiedName) && value.equals(one.value)) {
			return this;
		} else {
			if (parent != null && one.parent != null) {
				return parent.getCommonAncestor(one.parent);
			} else if (parent != null && one.parent == null) {
				return parent.getCommonAncestor(one);
			} else if (parent == null && one.parent != null) {
				return getCommonAncestor(one.parent);
			} else {
				return null;
			}
		}
	}
	
	public void count(IntegerWrapper cnt) {
		cnt.increment();
		for (DataPopulatorPropertyEntry child : children) {
			child.count(cnt);
		}
	}

	public static Integer count(DataPopulatorPropertyEntry entry) {
		IntegerWrapper count = entry.new IntegerWrapper();
		entry.count(count);
		return count.count;
	}
	
	public class IntegerWrapper {
		int count;
		void increment() {
			count++;
		}
	}
}
