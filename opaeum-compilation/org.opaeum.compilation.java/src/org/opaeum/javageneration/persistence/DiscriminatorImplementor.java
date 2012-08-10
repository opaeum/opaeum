package org.opaeum.javageneration.persistence;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;

/**
 */
public class DiscriminatorImplementor extends AbstractJavaProducingVisitor {
//	@VisitAfter(matchSubclasses=true)
//	public void buildDefaultConstructorBody(Property attr) {
//		if (attr.isDiscriminator()) {
//			Class entity = (Class) attr.getOwner();
//			OJPathName path = ojUtil.classifierPathname(entity);
//			OJBlock dcBody = new OJBlock();
//			OJClass ojClass = this.javaModel.findClass(path);
//			PowerType powerType = (PowerType) attr.getType();
//			if (entity.isPowerTypeInstance()) {
//				Generalization generalization = entity.getGeneralizations().iterator().next();
//				String literal = powerType.getQualifiedJavaName() + "."
//						+ generalization.getPowerTypeLiteral().getName().getUpperCase();
//				dcBody.addToStatements("set" + attr.getName().getCapped() + "(" + literal + ")");
//			}
//			ojClass.getDefaultConstructor().setBody(dcBody);
//			// TODO make setter protected
//		}
//	}
	
	@VisitAfter(matchSubclasses=true)
	public void buildDefaultConstructorBody(Class entity) {
		
		List<? extends Property> attributes = getLibrary().getEffectiveAttributes(entity);
		for (Property attr : attributes) {
			if (EmfPropertyUtil.isDiscriminator( attr)) {
				OJPathName path = ojUtil.classifierPathname(entity);
				StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(attr);
				OJBlock dcBody = new OJBlock();
				OJClass ojClass = this.javaModel.findClass(path);
				Enumeration powerType = (Enumeration)attr.getType();
				if (EmfClassifierUtil.isPowerTypeInstanceOn( entity,powerType)) {
					Generalization generalization = entity.getGeneralizations().iterator().next();
					String literal = ojUtil.classifierPathname(powerType) + "."
							+ EmfClassifierUtil.getPowerTypeLiteral( generalization,(Enumeration)attr.getType()).getName().toUpperCase();
					dcBody.addToStatements(map.setter() + "(" + literal + ")");
				}
				ojClass.getDefaultConstructor().setBody(dcBody);
				// TODO make setter protected
			}			
		}
	}	

}
