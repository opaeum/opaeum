package org.opaeum.metamodel.core.internal;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.opaeum.metamodel.commonbehaviors.internal.NakedBehavioredClassifierImpl;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedProperty;

public class NakedEntityImpl extends NakedBehavioredClassifierImpl implements INakedEntity{
	private static final long serialVersionUID = -257231836042506513L;
	public static final String META_CLASS = "entity";
	private INakedProperty primaryKeyProperty;
	public NakedEntityImpl(){
	}
	public boolean isPersistent(){
		return true;
	}
	public boolean hasComposite(){
		return getEndToComposite() != null;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
	}
	/**
	 * Includes all appropriately qualified relationships and one-to-one relationships
	 * 
	 * @param entity
	 * @return
	 */
	public List<INakedProperty> getUniquenessConstraints(){
		List<INakedProperty> list = new ArrayList<INakedProperty>();
		List<INakedProperty> attributes = getOwnedAttributes();
		for(INakedProperty attribute:attributes){
			// REMEMBER that appropriately qualified relationships would
			// have multiplicity of 0..1 or 1..1
			boolean bothEndsSingleObjects = attribute.getNakedMultiplicity().isSingleObject() && attribute.getOtherEnd() != null
					&& attribute.getOtherEnd().getNakedMultiplicity().isSingleObject();
			if(bothEndsSingleObjects && (!attribute.isInverse() || attribute.getOtherEnd().getQualifierNames().length > 0)
					&& !attribute.isDerived()){
				list.add(attribute);
			}
		}
		return list;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	@Override
	public INakedProperty getPrimaryKeyProperty(){
		if(primaryKeyProperty == null){
			List<INakedProperty> effectiveAttributes = getEffectiveAttributes();
			for(INakedProperty element:effectiveAttributes){
				if(element.getStereotype(StereotypeNames.ATTRIBUTE) != null){
					INakedInstanceSpecification st = element.getStereotype(StereotypeNames.ATTRIBUTE);
					if(st.hasValueForFeature(TagNames.IS_PRIMARY_KEY) && st.getFirstValueFor(TagNames.IS_PRIMARY_KEY).booleanValue()){
						primaryKeyProperty = (INakedProperty) element;
					}
				}
			}
		}
		return primaryKeyProperty;
	}
	@Override
	public Collection<INakedProperty> getPrimaryKeyProperties(){
		Collection<INakedProperty> result = new ArrayList<INakedProperty>();
		List<INakedProperty> effectiveAttributes = getEffectiveAttributes();
		for(INakedProperty element:effectiveAttributes){
			if(element.isPrimaryKeyProperty()){
				result.add(element);
			}
		}
		return result;
	}
}