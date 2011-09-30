package org.opeum.javageneration.persistence;

import java.util.List;

import org.opeum.feature.visit.VisitAfter;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedGeneralization;
import org.opeum.metamodel.core.INakedPowerType;
import org.opeum.metamodel.core.INakedProperty;
import nl.klasse.octopus.model.IAttribute;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJPathName;

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
		
		List<IAttribute> attributes = entity.getAllAttributes();
		for (IAttribute attr : attributes) {
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
