package org.opaeum.runtime.bpm.costing;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.UiidBasedInterfaceValue;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_gDOkoPl6EeGYn9aDKdPmKA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="quantified_resource_price_per_unit",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"price_per_unit_id","deleted_on"}))
@Entity(name="QuantifiedResourcePricePerUnit")
public class QuantifiedResourcePricePerUnit implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="quantified_resource_price_per_unit",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends QuantifiedResourcePricePerUnit> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="price_per_unit_id",name="idx_quantified_resource_price_per_unit_price_per_unit_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="price_per_unit_id",nullable=true)
	protected PricePerUnit pricePerUnit;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="quantified_resource"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="quantified_resource_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue quantifiedResource;
	static final private long serialVersionUID = 8011962670272454180l;
	private String uid;

	/** Constructor for QuantifiedResourcePricePerUnit
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public QuantifiedResourcePricePerUnit(IQuantifiedResource end1, PricePerUnit end2) {
		this.z_internalAddToQuantifiedResource(end1);
		this.z_internalAddToPricePerUnit(end2);
	}
	
	/** Constructor for QuantifiedResourcePricePerUnit
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public QuantifiedResourcePricePerUnit(PricePerUnit end2, IQuantifiedResource end1) {
		this.z_internalAddToQuantifiedResource(end1);
		this.z_internalAddToPricePerUnit(end2);
	}
	
	/** Default constructor for QuantifiedResourcePricePerUnit
	 */
	public QuantifiedResourcePricePerUnit() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends QuantifiedResourcePricePerUnit> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.costing.QuantifiedResourcePricePerUnit.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("pricePerUnit") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6577051702531583967")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PricePerUnit curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setPricePerUnit(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clear() {
		getPricePerUnit().z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(this);
		this.z_internalRemoveFromPricePerUnit(getPricePerUnit());
		getQuantifiedResource().z_internalRemoveFromQuantifiedResourcePricePerUnit_pricePerUnit(this);
		this.z_internalRemoveFromQuantifiedResource(getQuantifiedResource());
		markDeleted();
	}
	
	public void createComponents() {
		if ( getPricePerUnit()==null ) {
			setPricePerUnit(new PricePerUnit());
		}
	}
	
	public PricePerUnit createPricePerUnit() {
		PricePerUnit newInstance= new PricePerUnit();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof QuantifiedResourcePricePerUnit ) {
			return other==this || ((QuantifiedResourcePricePerUnit)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "QuantifiedResourcePricePerUnit["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getPricePerUnit();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6577051702531583967l,opposite="quantifiedResourcePricePerUnit_quantifiedResource",uuid="252060@_gDOkpPl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_gDOkoPl6EeGYn9aDKdPmKA@252060@_gDOkpPl6EeGYn9aDKdPmKA")
	public PricePerUnit getPricePerUnit() {
		PricePerUnit result = this.pricePerUnit;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2883529675759277875l,opposite="quantifiedResourcePricePerUnit_pricePerUnit",uuid="252060@_gDOkofl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_gDOkoPl6EeGYn9aDKdPmKA@252060@_gDOkofl6EeGYn9aDKdPmKA")
	public IQuantifiedResource getQuantifiedResource() {
		IQuantifiedResource result = null;
		if ( this.quantifiedResource==null ) {
			this.quantifiedResource=new UiidBasedInterfaceValue();
		}
		result=(IQuantifiedResource)this.quantifiedResource.getValue(persistence);
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
		this.z_internalAddToPricePerUnit((PricePerUnit)owner);
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getQuantifiedResource()!=null ) {
			getQuantifiedResource().z_internalRemoveFromQuantifiedResourcePricePerUnit_pricePerUnit(this);
		}
		if ( getPricePerUnit()!=null ) {
			getPricePerUnit().markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("quantifiedResource") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2883529675759277875")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setQuantifiedResource((IQuantifiedResource)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("pricePerUnit") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6577051702531583967")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PricePerUnit)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setPricePerUnit(PricePerUnit pricePerUnit) {
		PricePerUnit oldValue = this.getPricePerUnit();
		propertyChangeSupport.firePropertyChange("pricePerUnit",getPricePerUnit(),pricePerUnit);
		if ( oldValue==null ) {
			if ( pricePerUnit!=null ) {
				QuantifiedResourcePricePerUnit oldOther = (QuantifiedResourcePricePerUnit)pricePerUnit.getQuantifiedResourcePricePerUnit_quantifiedResource();
				pricePerUnit.z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromPricePerUnit(pricePerUnit);
				}
				pricePerUnit.z_internalAddToQuantifiedResourcePricePerUnit_quantifiedResource((QuantifiedResourcePricePerUnit)this);
			}
			this.z_internalAddToPricePerUnit(pricePerUnit);
		} else {
			if ( !oldValue.equals(pricePerUnit) ) {
				oldValue.z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(this);
				z_internalRemoveFromPricePerUnit(oldValue);
				if ( pricePerUnit!=null ) {
					QuantifiedResourcePricePerUnit oldOther = (QuantifiedResourcePricePerUnit)pricePerUnit.getQuantifiedResourcePricePerUnit_quantifiedResource();
					pricePerUnit.z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromPricePerUnit(pricePerUnit);
					}
					pricePerUnit.z_internalAddToQuantifiedResourcePricePerUnit_quantifiedResource((QuantifiedResourcePricePerUnit)this);
				}
				this.z_internalAddToPricePerUnit(pricePerUnit);
			}
		}
	}
	
	public void setQuantifiedResource(IQuantifiedResource quantifiedResource) {
		propertyChangeSupport.firePropertyChange("quantifiedResource",getQuantifiedResource(),quantifiedResource);
		if ( this.getQuantifiedResource()!=null ) {
			this.getQuantifiedResource().z_internalRemoveFromQuantifiedResourcePricePerUnit_pricePerUnit(this);
		}
		this.z_internalAddToQuantifiedResource(quantifiedResource);
		if ( quantifiedResource!=null ) {
			quantifiedResource.z_internalAddToQuantifiedResourcePricePerUnit_pricePerUnit(this);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<QuantifiedResourcePricePerUnit uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<QuantifiedResourcePricePerUnit ");
		sb.append("classUuid=\"252060@_gDOkoPl6EeGYn9aDKdPmKA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.costing.QuantifiedResourcePricePerUnit\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getQuantifiedResource()==null ) {
			sb.append("\n<quantifiedResource/>");
		} else {
			sb.append("\n<quantifiedResource propertyId=\"2883529675759277875\">");
			sb.append("\n" + getQuantifiedResource().toXmlReferenceString());
			sb.append("\n</quantifiedResource>");
		}
		if ( getPricePerUnit()==null ) {
			sb.append("\n<pricePerUnit/>");
		} else {
			sb.append("\n<pricePerUnit propertyId=\"6577051702531583967\">");
			sb.append("\n" + getPricePerUnit().toXmlString());
			sb.append("\n</pricePerUnit>");
		}
		sb.append("\n</QuantifiedResourcePricePerUnit>");
		return sb.toString();
	}
	
	public void z_internalAddToPricePerUnit(PricePerUnit pricePerUnit) {
		if ( pricePerUnit.equals(getPricePerUnit()) ) {
			return;
		}
		this.pricePerUnit=pricePerUnit;
	}
	
	public void z_internalAddToQuantifiedResource(IQuantifiedResource quantifiedResource) {
		if ( quantifiedResource.equals(getQuantifiedResource()) ) {
			return;
		}
		if ( this.quantifiedResource==null ) {
			this.quantifiedResource=new UiidBasedInterfaceValue();
		}
		this.quantifiedResource.setValue(quantifiedResource);
	}
	
	public void z_internalRemoveFromPricePerUnit(PricePerUnit pricePerUnit) {
		if ( getPricePerUnit()!=null && pricePerUnit!=null && pricePerUnit.equals(getPricePerUnit()) ) {
			this.pricePerUnit=null;
			this.pricePerUnit=null;
		}
	}
	
	public void z_internalRemoveFromQuantifiedResource(IQuantifiedResource quantifiedResource) {
		if ( getQuantifiedResource()!=null && quantifiedResource!=null && quantifiedResource.equals(getQuantifiedResource()) ) {
			this.quantifiedResource.setValue(null);
		}
	}

}