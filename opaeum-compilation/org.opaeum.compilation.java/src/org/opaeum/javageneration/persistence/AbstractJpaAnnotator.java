package org.opaeum.javageneration.persistence;




import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.metamodel.name.NameWrapper;

public abstract class AbstractJpaAnnotator extends AbstractStructureVisitor{
	protected final void mapXToOneEnumeration(Property f,OJAnnotatedClass owner,OJAnnotatedField field){
		JpaUtil.addColumn(field, PersistentNameUtil.getPersistentName( f).getAsIs(), !EmfPropertyUtil.isRequired( f));
		OJAnnotationValue enumerated = new OJAnnotationValue(new OJPathName("javax.persistence.Enumerated"));
		enumerated.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.EnumType"), "STRING"));
		field.addAnnotationIfNew(enumerated);
	}
	protected final boolean isOtherEndOrdered(Property f){
		return f instanceof Property && (f).getOtherEnd() != null && (f).getOtherEnd().isOrdered();
	}
	protected final void mapXToOneSimpleType(PropertyMap map,OJAnnotatedClass owner,OJAnnotatedField field){
		if(this.workspace.getOpaeumLibrary().getDateType() != null
				&& map.getBaseType().conformsTo(this.workspace.getOpaeumLibrary().getDateType())){
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"));
			temporal.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.TemporalType"), "DATE"));
			field.addAnnotationIfNew(temporal);
		}
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute("name", JpaUtil.getValidSqlName(map.getPersistentName().getAsIs()));
		field.addAnnotationIfNew(column);
		DataType simpleType = (DataType) map.getBaseType();
		if(EmfClassifierUtil.hasStrategy(simpleType,JpaStrategy.class)){
			EmfClassifierUtil.getStrategy(simpleType,JpaStrategy.class).annotate(field, map.getProperty());
		}
	}
	protected final void mapXToOnePersistentType(PropertyMap map,OJAnnotatedClass owner,OJAnnotatedField field){
		// Entities and behaviors
		// Inverse is always OneToOne
		String toOneType = map.isInverse() ? "javax.persistence.OneToOne" : "javax.persistence.ManyToOne";
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName(toOneType));
		JpaUtil.fetchLazy(toOne);
		Property f=map.getProperty();
		if(EmfClassifierUtil.isStructuredDataType(map.getBaseType()) || f.isComposite()){
			// TODO validate that StructuredDataType cannot participate in bidirectional relationships
			// Compositional semantics - should also delete Orphan
			JpaUtil.cascadeAll(toOne);
		}
		// TODO with oneToOne components map a relationship
		// table.
		if(map.isInverse() && !(f.getAssociation() != null && EmfAssociationUtil .isClass(f.getAssociation()))){
			// Implies navigable other end and Property
			PropertyMap otherMap = ojUtil.buildStructuralFeatureMap((f).getOtherEnd());
			toOne.putAttribute(new OJAnnotationAttributeValue("mappedBy", otherMap.fieldname()));
		}else{
			// Remember that oneToOne uniqueness will be added as a
			// uniqueConstraint
			NameWrapper persistentName = map.getPersistentName();
			String asIs = persistentName.getAsIs();
			OJAnnotationValue column = JpaUtil.addJoinColumn(field, asIs, !EmfPropertyUtil .isRequired(f));
			if(isOtherEndOrdered(f)){
				// Emulate "inverse" behavior
				column.putAttribute(new OJAnnotationAttributeValue("insertable", false));
				column.putAttribute(new OJAnnotationAttributeValue("updatable", false));
			}
		}
		field.addAnnotationIfNew(toOne);
	}
	protected final void mapXToOne(Classifier umlOwner,PropertyMap map){
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		mapXToOne(map, owner);
	}
	public void mapXToOne(PropertyMap map,OJAnnotatedClass owner){
		Property f = map.getProperty();
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.fieldname());
		if(field != null){
			// Field might have been replaced by a name-value type map
			if(map.getBaseType() instanceof Enumeration){
				mapXToOneEnumeration(f, owner, field);
			}else if(EmfClassifierUtil.isSimpleType( map.getBaseType() )){
				mapXToOneSimpleType(map, owner, field);
			}else if(isPersistent((Classifier) map.getBaseType())){
				mapXToOnePersistentType(map, owner, field);
			}else if(map.getBaseType() instanceof Behavior || EmfClassifierUtil.isHelper(map.getBaseType())){
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
			}
			for(Property p:EmfPropertyUtil.getPropertiesQualified( map.getProperty())){
				PropertyMap qualifiedMap = ojUtil.buildStructuralFeatureMap(p);
				OJAnnotatedField qf = (OJAnnotatedField) owner.findField(qualifiedMap.qualifierProperty());
				if(qf != null){
					OJAnnotationValue qColumn = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
					qf.putAnnotation(qColumn);
					qColumn.putAttribute("name", JpaUtil.generateIndexColumnName(qualifiedMap, "key"));
				}
			}
			if(EmfPropertyUtil.isMarkedAsPrimaryKey( f) && !owner.getName().endsWith("Id")){
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
