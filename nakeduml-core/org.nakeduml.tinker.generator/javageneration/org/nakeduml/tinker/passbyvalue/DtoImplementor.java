package org.nakeduml.tinker.passbyvalue;

import java.io.Serializable;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;

public class DtoImplementor extends StereotypeAnnotator {

	@VisitBefore(matchSubclasses = true)
	public void visitFeature(INakedClassifier c) {
		if (OJUtil.hasOJClass(c)) {
			OJAnnotatedClass myClass;
			if (c instanceof INakedInterface) {
				myClass = new OJAnnotatedInterface();
				if (c.getSupertype()!=null) {
					((OJAnnotatedInterface)myClass).addToSuperInterfaces(OJUtil.classifierDtoPathname(c.getSupertype()));
					myClass.addToImports(OJUtil.classifierDtoPathname(c.getSupertype()));	
				}
			} else {
				myClass = new OJAnnotatedClass();
				myClass.getDefaultConstructor();
				myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
				OJAnnotatedField seri = new OJAnnotatedField("serialVersionUID", new OJPathName("long"));
				seri.setStatic(true);
				seri.setFinal(true);
				seri.setVisibility(OJVisibilityKind.PRIVATE);
				seri.setInitExp(c.getMappingInfo().getNakedUmlId() + "");
				myClass.addToFields(seri);
				for (INakedInterfaceRealization ir : c.getInterfaceRealizations()) {
					myClass.addToImplementedInterfaces(OJUtil.classifierDtoPathname(ir.getContract()));
				}
				if (c.getSupertype()!=null) {
					myClass.setSuperclass(OJUtil.classifierDtoPathname(c.getSupertype()));
				}
			}
			myClass.setName(c.getName() + DtoImplementationStep.DTO);
			OJPathName path = OJUtil.packagePathname(c.getNameSpace());
			OJPackage pack = findOrCreatePackage(path);
			myClass.setMyPackage(pack);
			if (c instanceof INakedInterface) {
				addInterfaceProperties((OJAnnotatedInterface) myClass, c);
			} else {
				addClassProperties(myClass, c);
			}
			super.createTextPath(myClass, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
		}
	}

	private void addClassProperties(OJAnnotatedClass ojClass, INakedClassifier c) {
		for (INakedProperty p : c.getEffectiveAttributes()) {
			if (!p.isDerived() && !p.isDerivedUnion()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
				if (!(p.getNakedBaseType() instanceof INakedEntity) && !(p.getNakedBaseType() instanceof INakedInterface)) {
					OJUtil.addProperty(ojClass, map.umlName(), map.javaTypePath(), true);
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isOne()) {
					OJUtil.addProperty(ojClass, map.umlName() + "Id", new OJPathName("java.lang.Long"), true);
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isManyToMany()) {
					OJPathName manyIdPath = new OJPathName("java.util.List");
					manyIdPath.addToElementTypes(new OJPathName("java.lang.Long"));
					OJUtil.addProperty(ojClass, map.umlName() + "Ids", manyIdPath, true);
				}
			}
		}
		OJUtil.addProperty(ojClass, "id", new OJPathName("java.lang.Long"), true);
	}

	private void addInterfaceProperties(OJAnnotatedInterface ojClass, INakedClassifier c) {
		for (INakedProperty p : c.getEffectiveAttributes()) {
			if (!p.isDerived() && !p.isDerivedUnion()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
				if (!(p.getNakedBaseType() instanceof INakedEntity) && !(p.getNakedBaseType() instanceof INakedInterface)) {
					OJUtil.addProperty(ojClass, map.umlName(), map.javaTypePath(), false);
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isOne()) {
					OJUtil.addProperty(ojClass, map.umlName() + "Id", new OJPathName("java.lang.Long"), false);
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isManyToMany()) {
					OJPathName manyIdPath = new OJPathName("java.util.List");
					manyIdPath.addToElementTypes(new OJPathName("java.lang.Long"));
					OJUtil.addProperty(ojClass, map.umlName() + "Ids", manyIdPath, false);
				}
			}
		}
		OJUtil.addProperty(ojClass, "id", new OJPathName("java.lang.Long"), false);
	}

}
