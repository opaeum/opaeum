package net.sf.nakeduml.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedElement;

public class NakedStructuredActivityNodeImpl extends NakedActionImpl implements INakedStructuredActivityNode {
	Collection<INakedActivityNode> children = new ArrayList<INakedActivityNode>();
	Collection<INakedActivityEdge> activityEdges = new ArrayList<INakedActivityEdge>();
	Collection<INakedActivityVariable> variables = new ArrayList<INakedActivityVariable>();
	private Collection<INakedOutputPin> output = new ArrayList<INakedOutputPin>();
	private Collection<INakedInputPin> input = new ArrayList<INakedInputPin>();

	public Collection<INakedActivityEdge> getActivityEdges() {
		return activityEdges;
	}

	public Collection<INakedActivityVariable> getVariables() {
		return variables;
	}

	public void setVariables(Collection<INakedActivityVariable> variables) {
		this.variables = variables;
	}

	public Collection<INakedActivityNode> getActivityNodes() {
		return children;
	}

	public void setChildren(Collection<INakedActivityNode> children) {
		this.children = children;
	}
	public void removeOwnedElement(INakedElement element){
		super.removeOwnedElement(element);
		if (element instanceof INakedActivityNode) {
			children.remove((INakedActivityNode) element);
		}
		if (element instanceof INakedActivityVariable) {
			variables.remove((INakedActivityVariable) element);
		}
		if (element instanceof INakedActivityEdge) {
			INakedActivityEdge e = (INakedActivityEdge) element;
			e.setSource(null);
			e.setTarget(null);
			this.activityEdges.remove(e);
		}
		if(element instanceof INakedInputPin){
			input.remove((INakedInputPin) element);
		}
		if(element instanceof INakedOutputPin){
			output.remove((INakedOutputPin) element);
		}
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
		if (element instanceof INakedActivityEdge) {
			activityEdges.add((INakedActivityEdge) element);
		}
		if(element instanceof INakedInputPin){
			input.add((INakedInputPin) element);
		}
		if(element instanceof INakedOutputPin){
			output.add((INakedOutputPin) element);
		}
	}

	@Override
	public Collection<INakedActivityNode> getStartNodes() {
		Collection<INakedActivityNode> results = new ArrayList<INakedActivityNode>();
		for (INakedActivityNode node : getActivityNodes()) {
			if (!(node instanceof INakedPin) && node.getAllEffectiveIncoming().isEmpty()) {
				if (!(node instanceof INakedAction && ((INakedAction) node).handlesException())) {
					results.add(node);
				}
			}
		}
		return results;
	}

	@Override
	public Collection<INakedInputPin> getInput() {
		return this.input;
	}

	@Override
	public Collection<INakedOutputPin> getOutput() {
		return this.output;
	}

	public void setOutput(Collection<INakedOutputPin> output) {
		this.output = output;
	}

	public void setInput(Collection<INakedInputPin> input) {
		this.input = input;
	}
}
