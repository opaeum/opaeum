package net.sf.nakeduml.metamodel.core.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import nl.klasse.octopus.expressions.internal.types.PathName;

/**
 * @author Ampie Barnard
 */
public abstract class NakedModelElementImpl implements Serializable, INakedElement {
	private static final long serialVersionUID = -825314743586339864L;
	/**
	 * A The ID of this element's namespace as supplied by the modelling tool of
	 * choice The Modelelement class will resolve the links itself. This should
	 * make it easier to populate the Modelelement from various sources
	 */
	protected String id;
	protected IMappingInfo mappingInfo;
	private Map<String, INakedInstanceSpecification> stereotypes = new HashMap<String, INakedInstanceSpecification>();
	private INakedElementOwner ownerElement;
	private Collection<INakedElement> ownedElements = new HashSet<INakedElement>();
	private List<INakedComment> comments = new ArrayList<INakedComment>();
	private String name;
	private String documentation;

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<INakedElement> getOwnedElements() {
		return ownedElements;
	}

	public void addOwnedElement(INakedElement element) {
		ownedElements.add(element);
		if (element instanceof INakedComment) {
			comments.add((INakedComment) element);
		}
		element.setOwnerElement(this);
	}

	public void removeOwnedElement(INakedElement element) {
		ownedElements.remove(element);
		if (element instanceof INakedComment) {
			comments.remove(element);
		} else if (element instanceof INakedValueSpecification) {
			INakedValueSpecification sa = (INakedValueSpecification) element;
			if (stereotypes.containsKey(element)) {
				stereotypes.remove(sa.getName());
			}
		}
	}

	public NakedModelElementImpl() {
	}

	public List<INakedComment> getComments() {
		return comments;
	}

	public Collection<INakedInstanceSpecification> getStereotypes() {
		return this.stereotypes.values();
	}

	/**
	 * Returns a the UML meta class being wrapped,
	 * 
	 * @return
	 */
	public abstract String getMetaClass();

	/**
	 * This method is used primarily by Octopus. When a qualifiedUmlName has
	 * been specified, it returns a PathName based on it.
	 */
	public PathName getPathName() {
		PathName result = null;
		if (getMappingInfo().getQualifiedUmlName() == null) {
			if (getOwnerElement() instanceof INakedElement) {
				result = ((INakedElement) getOwnerElement()).getPathName().getCopy();
				result.addString(getName());
			} else {
				result = new PathName(getName());
			}
		} else {
			result = new PathName();
			StringTokenizer st = new StringTokenizer(getMappingInfo().getQualifiedUmlName(), ":");
			while (st.hasMoreElements()) {
				String s = (String) st.nextElement();
				if (s.length() > 0 && !s.equals(":")) {
					result.addString(s);
				}
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NakedModelElementImpl)) {
			return false;
		} else if (this == obj) {
			return true;
		} else {
			INakedElement other = (INakedElement) obj;
			if (getId() != null) {
				return getId().equals(other.getId());
			} else {
				return false;
			}
		}
	}

	public void initialize(String id, String name) {
		this.id = id;
		this.setName(name == null || name.trim().length() == 0 ? null : name);
	}

	public void setMappingInfo(IMappingInfo vi) {
		this.mappingInfo = vi;
	}

	public IMappingInfo getMappingInfo() {
		return this.mappingInfo;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + getName() + "]";
	}

	public INakedNameSpace getNameSpace() {
		INakedElement e = (INakedElement) this.ownerElement;
		while (e != null && !(e instanceof INakedNameSpace)) {
			e = (INakedElement) e.getOwnerElement();
		}
		return (INakedNameSpace) e;
	}

	public final String getId() {
		return this.id;
	}

	public boolean hasStereotype(String name) {
		return this.stereotypes.containsKey(name);
	}

	public void addStereotype(INakedInstanceSpecification stereotype) {
		this.ownedElements.add(stereotype);
		this.stereotypes.put(stereotype.getName(), stereotype);
	}

	public INakedInstanceSpecification getStereotype(String name) {
		return this.stereotypes.get(name);
	}

	public boolean hasTaggedValue(String stereotype, String tag) {
		INakedInstanceSpecification s = getStereotype(stereotype);
		return s != null && s.hasValueForFeature(tag);
	}

	public <T> T getTaggedValue(String stereotype, String tag) {
		INakedInstanceSpecification s = getStereotype(stereotype);
		if (s != null && s.hasValueForFeature(tag)) {
			INakedValueSpecification vs = s.getFirstValueFor(tag);
			return (T) vs.getValue();
		} else {
			return null;
		}
	}

	public INakedElementOwner getOwnerElement() {
		return this.ownerElement;
	}

	public void setOwnerElement(INakedElementOwner ownerElement) {
		this.ownerElement = ownerElement;
	}
}
