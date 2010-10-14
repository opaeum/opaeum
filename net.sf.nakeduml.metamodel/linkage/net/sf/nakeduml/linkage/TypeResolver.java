package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;

/**
 */
@StepDependency(phase = LinkagePhase.class,requires = {PinLinker.class},after = {PinLinker.class})
public class TypeResolver extends AbstractModelElementLinker{
	@VisitBefore
	public void resolveClassifier(INakedInstanceSpecification is) {
		if (is.getClassifier() == null) {
			// For Keywords only
			is.setClassifier(getBuiltInTypes().getStringType());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void resolveType(INakedTypedElement aw){
		INakedClassifier baseType = aw.getNakedBaseType();
		if(baseType == null){
			(aw).setBaseType(this.workspace.getMappedTypes().getDefaultType());
			baseType = this.workspace.getMappedTypes().getDefaultType();
		}
		IClassifier type = baseType;
		if(baseType instanceof INakedPrimitiveType){
			INakedPrimitiveType pt = ((INakedPrimitiveType) baseType);
			if(pt.getOclType() != null){
				// typically from non-uml metamodels
				type = pt.getOclType();
			}
		}
		boolean singleObject = aw.getNakedMultiplicity().isSingleObject();
		if(aw instanceof INakedProperty){
			singleObject = singleObject && ((INakedProperty) aw).getQualifierNames().length == 0;
		}
		if(singleObject){
			aw.setType(type);
		}else if(!aw.isOrdered() && aw.isUnique()){
			aw.setType(this.workspace.getOclEngine().getOclLibrary().lookupCollectionType(CollectionMetaType.SET, type));
		}else if(aw.isOrdered() && aw.isUnique()){
			aw.setType(this.workspace.getOclEngine().getOclLibrary().lookupCollectionType(CollectionMetaType.SEQUENCE, type));
		}else if(!aw.isOrdered() && !aw.isUnique()){
			aw.setType(this.workspace.getOclEngine().getOclLibrary().lookupCollectionType(CollectionMetaType.BAG, type));
		}else if(aw.isOrdered() && !aw.isUnique()){
			aw.setType(this.workspace.getOclEngine().getOclLibrary().lookupCollectionType(CollectionMetaType.SEQUENCE, type));
		}
	}
}
