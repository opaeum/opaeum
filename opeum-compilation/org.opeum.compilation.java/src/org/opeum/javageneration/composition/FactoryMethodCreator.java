package org.opeum.javageneration.composition;

import java.util.Iterator;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.CompositionEmulator;
import org.opeum.metamodel.core.ICompositionParticipant;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedProperty;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

/**
 * This class adds a factory method for compositional relationships. This would be used in conjunction by the CompositionNode semantics to
 * allow users to create object directly from the user interface within the composition hierarchy
 * 
 * @author ampie
 * 
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {
	CompositionEmulator.class,OperationAnnotator.class
},after = {
OperationAnnotator.class
})

public class FactoryMethodCreator extends AbstractStructureVisitor{
	private void createFactoryMethod(INakedProperty pw,OJClass owner){
		INakedClassifier type = pw.getNakedBaseType();
		Iterator<OJOperation> ops = owner.getOperations().iterator();
		OJOperation creator = null;
		String createOperName = "create" + pw.getMappingInfo().getJavaName().getSingular().getCapped();
		while(ops.hasNext()){
			OJOperation op = (OJOperation) ops.next();
			if(op.getParameters().size() == 0 && op.getName().equals(createOperName)){
				creator = op;
				break;
			}
		}
		if(creator == null){
			creator = new OJAnnotatedOperation(createOperName);
			owner.addToOperations(creator);
		}
		creator.setReturnType(new OJPathName(type.getMappingInfo().getQualifiedJavaName()));
		OJBlock body = new OJBlock();
		body.addToStatements((type).getMappingInfo().getJavaName() + " newInstance= new " + type.getMappingInfo().getJavaName() + "()");
		// if(pw.getOtherEnd() != null && pw.getOtherEnd().isNavigable()){
		// NameWrapper javaName = pw.getOtherEnd().getMappingInfo().getJavaName();
		// body.addToStatements("newInstance.set" + javaName.getCapped() + "((" + owner.getName() + ")this)");
		// }else if(pw.isOne()){
		// body.addToStatements("this.set" + pw.getMappingInfo().getJavaName().getCapped() + "(newInstance)");
		// }else{
		// body.addToStatements("this.addTo" + pw.getMappingInfo().getJavaName().getCapped() + "(newInstance)");
		// }
		if(type instanceof ICompositionParticipant){
			body.addToStatements("newInstance.init(this)");
		}
		body.addToStatements("return newInstance");
		creator.setBody(body);
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap map){
		INakedProperty aw = map.getProperty();
		OJAnnotatedClass myOwner = findJavaClass(owner);
		if(!aw.isDerived() && isPersistent(aw.getNakedBaseType()) && aw.isComposite() && !aw.getNakedBaseType().getIsAbstract()){
			createFactoryMethod(aw, myOwner);
		}
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		if(OJUtil.hasOJClass(umlOwner)){
			for(INakedProperty p:umlOwner.getEffectiveAttributes()){
				if(p.getOwner() instanceof INakedInterface){
					visitProperty(umlOwner, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
	}
}
