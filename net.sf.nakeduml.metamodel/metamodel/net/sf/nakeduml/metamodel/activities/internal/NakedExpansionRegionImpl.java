package net.sf.nakeduml.metamodel.activities.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedStructuredActivityNode;
import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.VisibilityKind;

public class NakedExpansionRegionImpl extends NakedStructuredActivityNode implements INakedExpansionRegion {
	// NB this is not the containment relationship - these objects will be
	// duplicated in ownedElements
	private List<INakedExpansionNode> inputElement = new ArrayList<INakedExpansionNode>();
	private List<INakedExpansionNode> outputElement = new ArrayList<INakedExpansionNode>();

	@Override
	public Collection<INakedActivityNode> getStartNodes() {
		Collection<INakedActivityNode> results = new ArrayList<INakedActivityNode>();
		for (INakedActivityNode node : getChildren()) {
			if (node.getAllEffectiveIncoming().isEmpty()) {
				if (node instanceof INakedExpansionNode) {
					//Only add expansionNodes that lead to subsequent nodes 
					if (node.getAllEffectiveOutgoing().size() > 0) {
						results.add(node);
					}
				} else {
					results.add(node);
				}
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
