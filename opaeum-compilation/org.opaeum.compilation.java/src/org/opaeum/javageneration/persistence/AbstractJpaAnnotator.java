package org.opaeum.javageneration.persistence;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.name.NameWrapper;

public abstract class AbstractJpaAnnotator extends AbstractStructureVisitor{
	protected final void mapXToOneEnumeration(INakedProperty f,OJAnnotatedClass owner,OJAnnotatedField field){
		JpaUtil.addColumn(field, f.getMappingInfo().getPersistentName().getAsIs(), !f.isRequired());
		OJAnnotationValue enumerated = new OJAnnotationValue(new OJPathName("javax.persistence.Enumerated"));
		enumerated.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.EnumType"), "STRING"));
		field.addAnnotationIfNew(enumerated);
	}
	protected final boolean isOtherEndOrdered(INakedProperty f){
		return f instanceof INakedProperty && (f).getOtherEnd() != null && (f).getOtherEnd().isOrdered();
	}
	protected final void mapXToOneSimpleType(INakedProperty f,OJAnnotatedClass owner,OJAnnotatedField field){
		if(this.workspace.getOpaeumLibrary().getDateType() != null
				&& f.getNakedBaseType().conformsTo(this.workspace.getOpaeumLibrary().getDateType())){
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"));
			temporal.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.TemporalType"), "DATE"));
			field.addAnnotationIfNew(temporal);
		}
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute("name", JpaUtil.getValidSqlName(f.getMappingInfo().getPersistentName().getAsIs()));
		field.addAnnotationIfNew(column);
		INakedSimpleType simpleType = (INakedSimpleType) f.getNakedBaseType();
		if(simpleType.hasStrategy(JpaStrategy.class)){
			simpleType.getStrategy(JpaStrategy.class).annotate(field, f);
		}
	}
	protected final void mapXToOnePersistentType(INakedProperty f,OJAnnotatedClass owner,OJAnnotatedField field){
		// Entities and behaviors
		// Inverse is always OneToOne
		String toOneType = f.isInverse() ? "javax.persistence.OneToOne" : "javax.persistence.ManyToOne";
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName(toOneType));
		JpaUtil.fetchLazy(toOne);
		if(f.getNakedBaseType() instanceof INakedStructuredDataType || f.isComposite()){
			// TODO validate that INakedStructuredDataType cannot participate in bidirectional relationships
			// Compositional semantics - should also delete Orphan
			JpaUtil.cascadeAll(toOne);
		}
		// TODO with oneToOne components map a relationship
		// table.
		if(f.isInverse() && !(f.getAssociation() != null && f.getAssociation().isClass())){
			// Implies navigable other end and INakedProperty
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((f).getOtherEnd());
			toOne.putAttribute(new OJAnnotationAttributeValue("mappedBy", otherMap.fieldname()));
		}else{
			// Remember that oneToOne uniqueness will be added as a
			// uniqueConstraint
			NameWrapper persistentName = f.getMappingInfo().getPersistentName();
			String asIs = persistentName.getAsIs();
			OJAnnotationValue column = JpaUtil.addJoinColumn(field, asIs, !f.isRequired());
			if(isOtherEndOrdered(f)){
				// Emulate "inverse" behavior
				column.putAttribute(new OJAnnotationAttributeValue("insertable", false));
				column.putAttribute(new OJAnnotationAttributeValue("updatable", false));
			}
		}
		field.addAnnotationIfNew(toOne);
	}
	protected final void mapXToOne(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		mapXToOne(map, owner);
	}
	public void mapXToOne(NakedStructuralFeatureMap map,OJAnnotatedClass owner){
		INakedProperty f = map.getProperty();
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.fieldname());
		if(field != null){
			// Field might have been replaced by a name-value type map
			if(f.getNakedBaseType() instanceof INakedEnumeration){
				mapXToOneEnumeration(f, owner, field);
			}else if(f.getNakedBaseType() instanceof INakedSimpleType){
				mapXToOneSimpleType(f, owner, field);
			}else if(isPersistent(f.getNakedBaseType())){
				mapXToOnePersistentType(f, owner, field);
			}else if(f.getBaseType() instanceof INakedBehavior || f.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			}
			for(INakedProperty p:map.getProperty().getPropertiesQualified()){
				NakedStructuralFeatureMap qualifiedMap = OJUtil.buildStructuralFeatureMap(p);
				OJAnnotatedField qf = (OJAnnotatedField) owner.findField(qualifiedMap.qualifierProperty());
				if(qf != null){
					OJAnnotationValue qColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
					qf.putAnnotation(qColumn);
					qColumn.putAttribute("name", JpaUtil.generateIndexColumnName(qualifiedMap, "key"));
				}
			}
			if(f.isPrimaryKeyProperty() && !owner.getName().endsWith("Id")){
				OJAnnotationValue col= field.findAnnotation(new OJPathName("javax.persistence.Column"));
				if(col==null){
					col=field.findAnnotation(new OJPathName("javax.persistence.JoinColumn"));
				}
				if(col!=null){
					col.putAttribute("insertable", false);
					col.putAttribute("updatable", false);
				}
			}
		}
	}
}
