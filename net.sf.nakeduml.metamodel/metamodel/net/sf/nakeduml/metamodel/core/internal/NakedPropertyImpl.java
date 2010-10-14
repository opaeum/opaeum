package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public class NakedPropertyImpl extends NakedStructuralFeature implements INakedProperty{
	private static final long serialVersionUID = -6082767755715076655L;
	public static final String INVERSE = "inverse";
	private List<INakedProperty> qualifiers=new ArrayList<INakedProperty>();
	private INakedProperty otherEnd;
	private String[] qualifierNames;
	private boolean isQualifierForComposite;
	private boolean isQualifier;
	private int argumentIndex = -1;
	private boolean isDerivedUnion;
	private Collection<INakedProperty> subsettedProperties = new ArrayList<INakedProperty>();
	private Collection<INakedProperty> redefinedProperties = new ArrayList<INakedProperty>();
	private INakedValueSpecification initialValue;
	private boolean navigable;
	private INakedAssociation association;
	public NakedPropertyImpl(){
		super();
	}
	public boolean isStatic(){
		return hasClassScope();
	}
	public INakedAssociation getAssociation(){
		return association;
	}
	public void setAssociation(INakedAssociation association){
		this.association = association;
	}
	public boolean isDerivedUnion(){
		return this.isDerivedUnion;
	}
	public void setDerivedUnion(boolean isDerivedUnion){
		this.isDerivedUnion = isDerivedUnion;
	}
	public Collection<INakedProperty> getRedefinedProperties(){
		return this.redefinedProperties;
	}
	public void setRedefinedProperties(Collection<INakedProperty> redefinedProperties){
		this.redefinedProperties = redefinedProperties;
	}
	public Collection<INakedProperty> getSubsettedProperties(){
		return this.subsettedProperties;
	}
	public void setSubsettedProperties(Collection<INakedProperty> subsettedProperties){
		this.subsettedProperties = subsettedProperties;
	}
	public int getOwnedAttributeIndex(){
		return this.argumentIndex;
	}
	public void setOwnedAttributeIndex(int argumentIndex){
		this.argumentIndex = argumentIndex;
	}
	public boolean isQualifier(){
		return this.isQualifier;
	}
	public void setIsQualifier(boolean isQualifier){
		this.isQualifier = isQualifier;
	}
	public boolean isQualifierForComposite(){
		return this.isQualifierForComposite;
	}
	public void setIsQualifierForComposite(boolean isQualifierForComposite){
		this.isQualifierForComposite = isQualifierForComposite;
	}
	public boolean isDiscriminator(){
		if(getNakedBaseType() instanceof INakedPowerType){
			return getOwner().conformsTo(((INakedPowerType) getNakedBaseType()).getRepresentedSupertype());
		}
		return false;
	}
	public boolean hasQualifier(String name){
		for(String n:getQualifierNames()){
			if(n.equals(name)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean isRequired(){
		int i = getNakedMultiplicity().getLower();
		return i >= 1 || isQualifier() || (getOtherEnd() != null && getOtherEnd().hasQualifiers());
	}
	public String[] getQualifierNames(){
		if(qualifierNames == null){
			qualifierNames = new String[0];
		}
		return this.qualifierNames;
	}
	public void setQualifierNames(String[] qualifierNames){
		this.qualifiers = new ArrayList<INakedProperty>();
		this.qualifierNames = qualifierNames;
	}
	public boolean hasQualifiers(){
		return this.qualifierNames != null && this.qualifierNames.length > 0;
	}
	public List<INakedProperty> getQualifiers(){
		if(this.qualifiers==null){
			throw new RuntimeException("Qualifiers null!");
		}
		return this.qualifiers;
	}
	public INakedValueSpecification getInitialValue(){
		return initialValue;
	}
	public void setInitialValue(INakedValueSpecification initialValue){
		this.initialValue = initialValue;
		super.addOwnedElement(initialValue);
	}
	public INakedProperty getOtherEnd(){
		return this.otherEnd;
	}
	public void setOtherEnd(INakedProperty otherEnd){
		this.otherEnd = otherEnd;
	}
	@Override
	public String getMetaClass(){
		return "property";
	}
	@Override
	public boolean isDerived(){
		return super.isDerived() || (getAssociation() != null && getAssociation().isDerived() || isDerivedUnion);
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
	}
	public void setQualifiers(List<INakedProperty> qualifiers){
		if(qualifiers==null){
			throw new NullPointerException();
		}
		this.qualifiers = qualifiers;
	}
	public boolean isNavigable(){
		return navigable;
	}
	public void setNavigable(boolean navigable){
		this.navigable = navigable;
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		Collection<INakedElement> result = new HashSet<INakedElement>(super.getOwnedElements());
		if(getInitialValue() != null){
			result.add(getInitialValue());
		}
		return result;
	}
}