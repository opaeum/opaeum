package org.opeum.metamodel.core.internal.emulated;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IMultiplicityKind;
import nl.klasse.octopus.model.VisibilityKind;

import org.opeum.feature.MappingInfo;
import org.opeum.metamodel.core.INakedAssociation;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedMultiplicity;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedValueSpecification;

public abstract class AbstractPropertyBridge extends EmulatingElement implements INakedProperty{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1760596536166579100L;
	protected INakedClassifier owner;
	protected boolean isComposite;
	protected String id;
	protected MappingInfo mappingInfo;
	protected IClassifier type;
	private INakedProperty otherEnd;

	public AbstractPropertyBridge(INakedClassifier owner, INakedElement element){
		super(element);
		this.id=owner.getId() + element.getId();
		this.owner=owner;
		this.mappingInfo=element.getMappingInfo().getCopy();
		this.mappingInfo.setIdInModel(id);

	}
	@Override
	public INakedElementOwner getOwnerElement() {
		return owner;
	}

	@Override
	public final int hashCode(){
		return id.hashCode();
	}

	@Override
	public final String getId(){
		return id;
	}

	@Override
	public final MappingInfo getMappingInfo(){
		return mappingInfo;
	}

	@Override
	public String toString(){
		return getName();
	}
	public IMultiplicityKind getMultiplicity(){
		return getNakedMultiplicity();
	}

	public abstract INakedMultiplicity getNakedMultiplicity();
	public abstract INakedClassifier getNakedBaseType();

	public IClassifier getBaseType(){
		return getNakedBaseType();
	}

	public boolean isInverse(){
		return false;
	}


	public boolean isRequired(){
		return getNakedMultiplicity().getLower() == 1;
	}

	public INakedClassifier getOwner(){
		return owner;
	}

	public boolean isOclDef(){
		return false;
	}


	@SuppressWarnings("deprecation")
	public String getSignature(){
		return owner.getPathName() + "::" + getName();
	}


	public boolean hasClassScope(){
		return false;
	}

	public boolean isAggregate(){
		return false;
	}

	public boolean isComposite(){
		return this.isComposite;
	}

	public boolean isDerived(){
		return false;
	}

	public VisibilityKind getVisibility(){
		return VisibilityKind.PUBLIC;
	}

	public boolean isReadOnly(){
		return false;
	}

	public void setVisibility(VisibilityKind kind){
	}

	public void setBaseType(INakedClassifier nakedPeer){
	}

	public void setIsOrdered(boolean ordered){
	}

	public void setIsUnique(boolean unique){
	}

	public void setMultiplicity(INakedMultiplicity nakedMultiplicityImpl){
	}
	@Override
	public IClassifier getType() {
		return type;
	}

	public void setType(IClassifier type){
		this.type=type;
	}

	public INakedValueSpecification getInitialValue(){
		return null;
	}

	public INakedProperty getOtherEnd(){
		return otherEnd;
	}

	public int getOwnedAttributeIndex(){
		return 0;
	}

	public String[] getQualifierNames(){
		return new String[0];
	}

	public List<INakedProperty> getQualifiers(){
		return Collections.emptyList();
	}

	public Collection<INakedProperty> getRedefinedProperties(){
		return Collections.emptyList();
	}

	public Collection<INakedProperty> getSubsettedProperties(){
		return Collections.emptyList();
	}

	public boolean hasQualifier(String name){
		return false;
	}

	public boolean hasQualifiers(){
		return false;
	}

	public boolean isDerivedUnion(){
		return false;
	}

	public boolean isDiscriminator(){
		return false;
	}

	public boolean isQualifier(){
		return false;
	}

	public boolean isQualifierForComposite(){
		return false;
	}

	public boolean isStatic(){
		return false;
	}

	public void setAssociation(INakedAssociation impl){
	}

	public void setComposite(boolean b){
	}

	public void setDerived(boolean b){
	}

	public void setDerivedUnion(boolean b){
	}

	public void setInitialValue(INakedValueSpecification v){
	}

	public void setInverse(boolean inverse){
	}

	public void setIsQualifier(boolean isQualifier){
	}

	public void setIsQualifierForComposite(boolean isQualifierForComposite){
	}

	public void setNavigable(boolean b){
	}

	public void setOtherEnd(INakedProperty end2){
		this.otherEnd=end2;
		if(end2!=null && end2.getOtherEnd()!=this){
			end2.setOtherEnd(this);
		}
	}

	public void setOwnedAttributeIndex(int elementIndex){
	}

	public void setQualifierNames(String[] qualifierNames){
	}

	public void setQualifiers(List<INakedProperty> qualifiers){
	}

	public void setReadOnly(boolean readOnly){
	}

	public void setRedefinedProperties(Collection<INakedProperty> p){
	}

	public void setSubsettedProperties(Collection<INakedProperty> p){
	}

	public IAssociation getAssociation(){
		return null;
	}

	public boolean isNavigable(){
		return true;
	}
}