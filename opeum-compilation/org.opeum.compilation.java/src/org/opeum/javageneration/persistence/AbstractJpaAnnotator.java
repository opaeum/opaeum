package org.opeum.javageneration.persistence;

import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opeum.java.metamodel.annotation.OJAnnotationValue;
import org.opeum.java.metamodel.annotation.OJEnumValue;
import org.opeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.core.INakedStructuredDataType;
import org.opeum.metamodel.core.internal.StereotypeNames;

public abstract class AbstractJpaAnnotator extends AbstractStructureVisitor {

	protected final void mapXToOneEnumeration(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		JpaUtil.addColumn(field, f.getMappingInfo().getPersistentName().getAsIs(), !f.isRequired());
		OJAnnotationValue enumerated = new OJAnnotationValue(new OJPathName("javax.persistence.Enumerated"));
		enumerated.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.EnumType"), "STRING"));
		field.addAnnotationIfNew(enumerated);
	}

	protected final boolean isOtherEndOrdered(INakedProperty f) {
		return f instanceof INakedProperty && (f).getOtherEnd() != null && (f).getOtherEnd().isOrdered();
	}

	protected final void mapXToOneSimpleType(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		if (this.workspace.getOpeumLibrary().getDateType() != null
				&& f.getNakedBaseType().conformsTo(this.workspace.getOpeumLibrary().getDateType())) {
			OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"));
			temporal.addEnumValue(new OJEnumValue(new OJPathName("javax.persistence.TemporalType"), "DATE"));
			field.addAnnotationIfNew(temporal);
		}
		OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		column.putAttribute("name", f.getMappingInfo().getPersistentName().getAsIs());
		field.addAnnotationIfNew(column);

		INakedSimpleType simpleType = (INakedSimpleType)f.getNakedBaseType();
		if (simpleType.hasStrategy(JpaStrategy.class)) {
			simpleType.getStrategy(JpaStrategy.class).annotate(field, f);
		}

	}

	protected final void mapXToOnePersistentType(INakedProperty f, OJAnnotatedClass owner, OJAnnotatedField field) {
		// Entities and behaviors
		// Inverse is always OneToOne
		String toOneType = f.isInverse() ? "javax.persistence.OneToOne" : "javax.persistence.ManyToOne";
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName(toOneType));
		JpaUtil.fetchLazy(toOne);
		if (f.getNakedBaseType() instanceof INakedStructuredDataType || f.isComposite()) {
			//TODO validate that INakedStructuredDataType cannot participate in bidirectional relationships
			// Compositional semantics - should also delete Orphan
			JpaUtil.cascadeAll(toOne);
		}
		// TODO with oneToOne components map a relationship
		// table.
		if (f.isInverse() && !(f.getAssociation()!=null && f.getAssociation().isClass())) {
			// Implies navigable other end and INakedProperty
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((f).getOtherEnd());
			toOne.putAttribute(new OJAnnotationAttributeValue("mappedBy", otherMap.umlName()));
		} else {
			// Remember that oneToOne uniqueness will be added as a
			// uniqueConstraint
			OJAnnotationValue column = JpaUtil.addJoinColumn(field, f.getMappingInfo().getPersistentName().getAsIs(), !f.isRequired());
			if (isOtherEndOrdered(f)) {
				// Emulate "inverse" behavior
				column.putAttribute(new OJAnnotationAttributeValue("insertable", false));
				column.putAttribute(new OJAnnotationAttributeValue("updatable", false));
			}
		}
		field.addAnnotationIfNew(toOne);
	}

	protected final void mapXToOne(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty f=map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		OJAnnotatedField field = (OJAnnotatedField) owner.findField(map.umlName());
		if (f.getNakedBaseType() instanceof INakedEnumeration) {
			mapXToOneEnumeration(f, owner, field);
		} else if (f.getNakedBaseType() instanceof INakedSimpleType) {
			mapXToOneSimpleType(f, owner, field);
		} else if (isPersistent(f.getNakedBaseType())) {
			mapXToOnePersistentType(f, owner, field);
		}else if(f.getBaseType() instanceof INakedBehavior || f.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)){
			field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
		}
	}
}
