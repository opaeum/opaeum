package org.opaeum.linkage;

import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.activities.INakedValuePin;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedPrimitiveType;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.core.internal.NakedPrimitiveTypeImpl;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

/**
 */
@StepDependency(phase = LinkagePhase.class,requires = {
	PinLinker.class
},after = {
	PinLinker.class
})
public class TypeResolver extends AbstractModelElementLinker{
	public static class DefaultPrimitiveType extends NakedPrimitiveTypeImpl{
	}
	private static INakedSimpleType DEFAULT_TYPE = null;
	@Override
	public void visitRecursively(INakedElementOwner o){
		super.visitRecursively(o);
		if(o instanceof INakedOperation && BehaviorUtil.hasExecutionInstance((IParameterOwner) o)){
			visitRecursively(((INakedOperation) o).getMessageStructure());
		}else if(o instanceof INakedEmbeddedTask){
			visitRecursively(((INakedEmbeddedTask) o).getMessageStructure());
		}else if(o instanceof INakedCallAction && BehaviorUtil.hasMessageStructure((INakedAction) o)){
			visitRecursively(((INakedCallAction) o).getMessageStructure());
		}else if(o instanceof INakedStructuredActivityNode && ((INakedStructuredActivityNode) o).getMessageStructure() != null){
			visitRecursively(((INakedStructuredActivityNode) o).getMessageStructure());
		}
	}
	@VisitBefore
	public void resolveClassifier(INakedInstanceSpecification is){
		if(is.getClassifier() == null){
			// For Keywords only
			is.setClassifier(getBuiltInTypes().getStringType());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void resolveType(INakedTypedElement aw){
		if(!(aw instanceof INakedValuePin && aw.getNakedBaseType() == null)){
			if(aw instanceof INakedTimeObservation){
				aw.setBaseType(this.workspace.getOpaeumLibrary().getDateType());
			}else if(aw instanceof INakedDurationObservation){
				aw.setBaseType(this.workspace.getOpaeumLibrary().getDurationType());
			}
			// VAlue pins will have their basetype calculated from the ocl
			INakedClassifier baseType = aw.getNakedBaseType();
			if(baseType == null){
				(aw).setBaseType(getDefaultType());
				baseType = getDefaultType();
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
			}else{
				IOclLibrary lib = this.workspace.getOclEngine().getOclLibrary();
				resolveCollection(aw, type, lib);
			}
		}
	}
	protected INakedSimpleType getDefaultType(){
		INakedSimpleType dt = this.workspace.getOpaeumLibrary().getDefaultType();
		if(DEFAULT_TYPE == null && dt instanceof INakedPrimitiveType){
			DEFAULT_TYPE = new DefaultPrimitiveType();
			DEFAULT_TYPE.initialize(dt.getId(), dt.getName(), false);
			DEFAULT_TYPE.setCodeGenerationStrategy(dt.getCodeGenerationStrategy());
			DEFAULT_TYPE.setMappedImplementationType(dt.getMappedImplementationType());
			DEFAULT_TYPE.setMappingInfo(dt.getMappingInfo());
			DEFAULT_TYPE.setStrategyFactory(dt.getStrategyFactory());
			DEFAULT_TYPE.setName(dt.getName());
			DEFAULT_TYPE.setOwnerElement(dt.getOwnerElement());
			((NakedPrimitiveTypeImpl) DEFAULT_TYPE).setOclType(((INakedPrimitiveType) dt).getOclType());
		}
		return DEFAULT_TYPE;
	}
	@Override
	public void startVisiting(INakedModelWorkspace root){
		getDefaultType();
		super.startVisiting(root);
	}
	public void release(){
		super.release();
		DEFAULT_TYPE = null;
	}
	// TODO move to utility class
	public static void resolveCollection(INakedTypedElement aw,IClassifier type,IOclLibrary lib){
		if(!aw.isOrdered() && aw.isUnique()){
			aw.setType(lib.lookupCollectionType(CollectionMetaType.SET, type));
		}else if(aw.isOrdered() && aw.isUnique()){
			aw.setType(lib.lookupCollectionType(CollectionMetaType.SEQUENCE, type));
		}else if(!aw.isOrdered() && !aw.isUnique()){
			aw.setType(lib.lookupCollectionType(CollectionMetaType.BAG, type));
		}else if(aw.isOrdered() && !aw.isUnique()){
			aw.setType(lib.lookupCollectionType(CollectionMetaType.SEQUENCE, type));
		}
	}
}
