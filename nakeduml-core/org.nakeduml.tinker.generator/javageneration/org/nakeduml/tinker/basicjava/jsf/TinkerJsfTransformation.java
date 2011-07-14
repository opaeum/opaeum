package org.nakeduml.tinker.basicjava.jsf;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.tinker.basicjava.tinker.TinkerUtil;

public class TinkerJsfTransformation extends StereotypeAnnotator {

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (OJUtil.hasOJClass(p.getOwner())) {
			if (p.getAssociation() instanceof INakedAssociationClass) {
			} else {
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}

	private void visitProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		if (!OJUtil.isBuiltIn(p)) {
			if (p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
			} else if (p.isDerived() || p.isReadOnly()) {
			} else {
				implementAttributeFully(umlOwner, map);
			}
		}
	}

	private void implementAttributeFully(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		if (map.isMany() && !map.getProperty().isOrdered()) {
			makeSetDataModel(umlOwner, owner, map);
		} else if (map.isOne() && isPersistent(p.getNakedBaseType()) || p.getBaseType() instanceof INakedInterface) {
		}
	}
	
	private void makeSetDataModel(INakedClassifier umlOwner, OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		if (umlOwner instanceof INakedEntity) {
			INakedEntity entity = (INakedEntity) umlOwner;
			if (entity.getEndToComposite() != null) {
				NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(entity.getEndToComposite());
				OJConstructor constructorWithOwner = owner.findConstructor(compositeEndMap.javaBaseTypePath());
				OJTryStatement tryForCollectionWithOwner = (OJTryStatement) constructorWithOwner.getBody().findStatement("collectionInitializer");
				OJStatement collectionInit = tryForCollectionWithOwner.getTryPart().findStatementRecursive(map.fieldname());
				tryForCollectionWithOwner.getTryPart().removeFromStatements(collectionInit);
				tryForCollectionWithOwner.getTryPart().addToStatements(TinkerUtil.constructTinkerCollectionInit(owner, map, true));
			}

			OJConstructor defaultConstructor = owner.findConstructor(new OJPathName("java.lang.Boolean"));
			OJTryStatement tryForCollectionWithOwner = (OJTryStatement) defaultConstructor.getBody().findStatement("collectionInitializer");
			OJStatement collectionInit = tryForCollectionWithOwner.getTryPart().findStatementRecursive(map.fieldname());
			tryForCollectionWithOwner.getTryPart().removeFromStatements(collectionInit);
			tryForCollectionWithOwner.getTryPart().addToStatements(TinkerUtil.constructTinkerCollectionInit(owner, map, true));
			if (map.getProperty().getOtherEnd() != null) {
				owner.addToImports(TinkerUtil.tinkerJsfHashSetImpl);
			} else {
				owner.addToImports(TinkerUtil.tinkerEmbeddedHashSetImpl);
				owner.addToImports(TinkerUtil.tinkerEmbeddedArrayListImpl);
			}
		}
	}	
}
