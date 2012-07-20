package org.opaeum.javageneration.persistence;

import java.util.List;

import nl.klasse.octopus.model.IAttribute;

import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedPowerType;
import org.opaeum.metamodel.core.INakedProperty;

/**
 */
public class DiscriminatorImplementor extends AbstractJavaProducingVisitor {
//	@VisitAfter(matchSubclasses=true)
//	public void buildDefaultConstructorBody(INakedProperty attr) {
//		if (attr.isDiscriminator()) {
//			INakedEntity entity = (INakedEntity) attr.getOwner();
//			OJPathName path = OJUtil.classifierPathname(entity);
//			OJBlock dcBody = new OJBlock();
//			OJClass ojClass = this.javaModel.findClass(path);
//			INakedPowerType powerType = (INakedPowerType) attr.getBaseType();
//			if (entity.isPowerTypeInstance()) {
//				INakedGeneralization generalization = entity.getNakedGeneralizations().iterator().next();
//				String literal = powerType.getMappingInfo().getQualifiedJavaName() + "."
//						+ generalization.getPowerTypeLiteral().getMappingInfo().getJavaName().getUpperCase();
//				dcBody.addToStatements("set" + attr.getMappingInfo().getJavaName().getCapped() + "(" + literal + ")");
//			}
//			ojClass.getDefaultConstructor().setBody(dcBody);
//			// TODO make setter protected
//		}
//	}
	
	@VisitAfter(matchSubclasses=true)
	public void buildDefaultConstructorBody(INakedEntity entity) {
		
		List<? extends INakedProperty> attributes = entity.getEffectiveAttributes();
		for (INakedProperty attr : attributes) {
			INakedProperty property = (INakedProperty)attr;
			if (property.isDiscriminator()) {
				OJPathName path = OJUtil.classifierPathname(entity);
				OJBlock dcBody = new OJBlock();
				OJClass ojClass = this.javaModel.findClass(path);
				INakedPowerType powerType = (INakedPowerType) property.getNakedBaseType();
				if (entity.isPowerTypeInstance()) {
					INakedGeneralization generalization = entity.getNakedGeneralizations().iterator().next();
					String literal = powerType.getMappingInfo().getQualifiedJavaName() + "."
							+ generalization.getPowerTypeLiteral().getMappingInfo().getJavaName().getUpperCase();
					dcBody.addToStatements("set" + property.getMappingInfo().getJavaName().getCapped() + "(" + literal + ")");
				}
				ojClass.getDefaultConstructor().setBody(dcBody);
				// TODO make setter protected
			}			
		}
	}	

}
