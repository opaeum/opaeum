package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.annotation.OJEnumValue;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

public class AbstractJpaAnnotator extends AbstractJavaProducingVisitor {

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
		if (this.workspace.getMappedTypes().getDateType() != null
				&& f.getNakedBaseType().conformsTo(this.workspace.getMappedTypes().getDateType())) {
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
			// Compositional semantics - should also delete Orphan
			JpaUtil.cascadeAll(toOne);
		}
		// TODO with oneToOne components map a relationship
		// table.
		if (f.isInverse() && !(f.getAssociation() instanceof INakedAssociationClass)) {
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
