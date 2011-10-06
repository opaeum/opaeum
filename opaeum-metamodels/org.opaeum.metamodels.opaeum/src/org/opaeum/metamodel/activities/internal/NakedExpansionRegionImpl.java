package org.opaeum.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedExpansionNode;
import org.opaeum.metamodel.activities.INakedExpansionRegion;
import org.opaeum.metamodel.core.INakedElement;

public class NakedExpansionRegionImpl extends NakedStructuredActivityNodeImpl implements INakedExpansionRegion {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6052492266261216015L;
	// NB this is not the containment relationship - these objects will be
	// duplicated in ownedElements
	private List<INakedExpansionNode> inputElement = new ArrayList<INakedExpansionNode>();
	private List<INakedExpansionNode> outputElement = new ArrayList<INakedExpansionNode>();

	@Override
	public Collection<INakedActivityNode> getStartNodes() {
		Collection<INakedActivityNode> results = new ArrayList<INakedActivityNode>();
		for (INakedActivityNode node : getActivityNodes()) {
			if (node instanceof INakedExpansionNode && (((INakedExpansionNode) node).isInputElement())) {
				results.add(node);
			} else if (node.getAllEffectiveIncoming().isEmpty()) {
				results.add(node);
			}
		}
		return results;
	}

	@Override
	public Set<INakedActivityEdge> getAllEffectiveOutgoing() {
		HashSet<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getAllEffectiveOutgoing());
		List<INakedExpansionNode> ip = getOutputElement();
		for (INakedExpansionNode en : ip) {
			result.addAll(en.getAllEffectiveOutgoing());
		}
		return result;
	}

	@Override
	public Set<INakedActivityEdge> getAllEffectiveIncoming() {
		HashSet<INakedActivityEdge> result = new HashSet<INakedActivityEdge>(super.getAllEffectiveIncoming());
		List<INakedExpansionNode> ip = getInputElement();
		for (INakedExpansionNode en : ip) {
			result.addAll(en.getAllEffectiveIncoming());
		}
		return result;
	}

	public List<INakedExpansionNode> getInputElement() {
		return inputElement;
	}

	public void setInputElement(List<INakedExpansionNode> inputElement) {
		this.inputElement = inputElement;
	}

	public List<INakedExpansionNode> getOutputElement() {
		return outputElement;
	}

	public void setOutputElement(List<INakedExpansionNode> outputElement) {
		this.outputElement = outputElement;
	}

	@Override
	public Collection<INakedElement> getOwnedElements() {
		Collection<INakedElement> result = super.getOwnedElements();
		return result;
	}
}
