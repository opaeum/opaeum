package net.sf.nakeduml.metamodel.core.internal.emulated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import nl.klasse.octopus.expressions.internal.types.PathName;

public class EmulatingElement implements INakedElement {
	String documentation;

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	protected INakedElement element;
	private List<INakedComment> comments = new ArrayList<INakedComment>();

	public EmulatingElement(INakedElement element) {
		super();
		this.element = element;
	}

	public List<INakedComment> getComments() {
		return comments;
	}

	public void addStereotype(INakedInstanceSpecification stereotype) {
	}

	public String getId() {
		return element.getId();
	}

	public IMappingInfo getMappingInfo() {
		return element.getMappingInfo();
	}

	public String getMetaClass() {
		return element.getMetaClass();
	}

	public INakedNameSpace getNameSpace() {
		return element.getNameSpace();
	}

	public INakedElementOwner getOwnerElement() {
		return element.getOwnerElement();
	}

	public INakedInstanceSpecification getStereotype(String name) {
		return element.getStereotype(name);
	}

	public Collection<? extends INakedInstanceSpecification> getStereotypes() {
		return element.getStereotypes();
	}

	public <T> T getTaggedValue(String stereotype, String tag) {
		Object o = element.getTaggedValue(stereotype, tag);
		return (T) o;
	}

	public boolean hasStereotype(String name) {
		return element.hasStereotype(name);
	}

	public boolean hasTaggedValue(String stereotype, String tag) {
		return element.hasTaggedValue(stereotype, tag);
	}

	public void initialize(String id, String name) {
	}

	public void setMappingInfo(IMappingInfo vi) {
	}

	public void setName(String umlName) {
	}

	public void setOwnerElement(INakedElementOwner element) {
	}

	public String getName() {
		return element.getName();
	}

	public PathName getPathName() {
		return element.getPathName();
	}

	public Collection<? extends INakedElement> getOwnedElements() {
		return element.getOwnedElements();
	}

	public void addOwnedElement(INakedElement element) {
	}

	public void removeOwnedElement(INakedElement element) {
	}
}