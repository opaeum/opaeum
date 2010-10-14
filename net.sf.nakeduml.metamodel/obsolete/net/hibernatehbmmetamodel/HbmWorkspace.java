package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class HbmWorkspace extends HbmElement implements CompositionNode {
	private Set<HibernateConfiguration> hibernateConfiguration = new HashSet<HibernateConfiguration>();

	/** Default constructor for 
	 */
	public HbmWorkspace() {
	}

	public void addAllToHibernateConfiguration(Set<HibernateConfiguration> hibernateConfiguration) {
		for ( HibernateConfiguration o : hibernateConfiguration ) {
			addToHibernateConfiguration(o);
		}
	}
	
	public void addToHibernateConfiguration(HibernateConfiguration hibernateConfiguration) {
		hibernateConfiguration.setHbmWorkspace(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void clearHibernateConfiguration() {
		removeAllFromHibernateConfiguration(getHibernateConfiguration());
	}
	
	public void copyShallowState(HbmWorkspace from, HbmWorkspace to) {
		to.setQualifiedName(from.getQualifiedName());
	}
	
	public void copyState(HbmWorkspace from, HbmWorkspace to) {
		to.setQualifiedName(from.getQualifiedName());
		for ( HibernateConfiguration child : from.getHibernateConfiguration() ) {
			to.addToHibernateConfiguration(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public HibernateConfiguration createHibernateConfiguration() {
		HibernateConfiguration newInstance= new HibernateConfiguration();
		newInstance.init(this);
		return newInstance;
	}
	
	public Set<HibernateConfiguration> getHibernateConfiguration() {
		return hibernateConfiguration;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getHibernateConfiguration());
		return ownedElementSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	public HbmWorkspace makeCopy() {
		HbmWorkspace result = new HbmWorkspace();
		copyState((HbmWorkspace)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		for ( HibernateConfiguration child : new ArrayList<HibernateConfiguration>(getHibernateConfiguration()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromHibernateConfiguration(Set<HibernateConfiguration> hibernateConfiguration) {
		for ( HibernateConfiguration o : hibernateConfiguration ) {
			removeFromHibernateConfiguration(o);
		}
	}
	
	public void removeFromHibernateConfiguration(HibernateConfiguration hibernateConfiguration) {
		hibernateConfiguration.setHbmWorkspace(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setHibernateConfiguration(Set<HibernateConfiguration> hibernateConfiguration) {
		for ( HibernateConfiguration o : new HashSet<HibernateConfiguration>(this.hibernateConfiguration) ) {
			o.setHbmWorkspace(null);
		}
		for ( HibernateConfiguration o : hibernateConfiguration ) {
			o.setHbmWorkspace((HbmWorkspace)this);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		if ( getOwner()==null ) {
			sb.append("owner=null;");
		} else {
			sb.append("owner="+getOwner().getClass().getSimpleName()+"[");
			sb.append(getOwner().hashCode());
			sb.append("];");
		}
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
		sb.append(";");
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getQualifiedName()==null ) {
			sb.append("<qualifiedName/>");
		} else {
			sb.append("<qualifiedName>");
			sb.append(getQualifiedName());
			sb.append("</qualifiedName>");
			sb.append("\n");
		}
		for ( HibernateConfiguration hibernateConfiguration : getHibernateConfiguration() ) {
			sb.append("<hibernateConfiguration>");
			sb.append(hibernateConfiguration.toXmlString());
			sb.append("</hibernateConfiguration>");
			sb.append("\n");
		}
		return sb.toString();
	}

}