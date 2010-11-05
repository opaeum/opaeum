package net.sf.nakeduml.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.model.ParameterDirectionKind;

public class NakedStructuredActivityNode extends NakedActivityNodeImpl implements INakedStructuredActivityNode {
	Collection<INakedActivityNode> children = new ArrayList<INakedActivityNode>();
	Collection<INakedActivityVariable> variables = new ArrayList<INakedActivityVariable>();

	public Collection<INakedActivityVariable> getVariables() {
		return variables;
	}

	public void setVariables(Collection<INakedActivityVariable> variables) {
		this.variables = variables;
	}

	public Collection<INakedActivityNode> getChildren() {
		return children;
	}

	public void setChildren(Collection<INakedActivityNode> children) {
		this.children = children;
	}

	@Override
	public void addOwnedElement(INakedElement element) {
		super.addOwnedElement(element);
		if (element instanceof INakedActivityNode) {
			children.add((INakedActivityNode) element);
		}
		if (element instanceof INakedActivityVariable) {
			variables.add((INakedActivityVariable) element);
		}
	}

	@Override
	public Collection<INakedActivityNode> getStartNodes() {
		Collection<INakedActivityNode> results = new ArrayList<INakedActivityNode>();
		for(INakedActivityNode node:getChildren()){
			if(node.getAllEffectiveIncoming().isEmpty()){
				results.add(node);
			}
		}
		return results;
	}
}
