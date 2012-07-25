package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.List;

import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Pin;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.ocl.uml.OclActionContext;

public class OclActionCaller extends SimpleNodeBuilder<OpaqueAction>{
	public OclActionCaller(OpaeumLibrary oclEngine,OpaqueAction action,AbstractObjectNodeExpressor expressor){
		super(oclEngine, action, expressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		Pin returnPin = EmfActionUtil.getReturnPin(node);
		if(returnPin == null){
			// TODO What now?
		}else{
			List<InputPin> args = node.getInputValues();
			for(InputPin arg:args){
				// Make args available as vars to OCL
				// Will lead to duplicate variables in simple synchronous methods
				NakedStructuralFeatureMap argMap = OJUtil.buildStructuralFeatureMap(getContainingActivity(), arg, false);
				OJAnnotatedField argField = new OJAnnotatedField(argMap.fieldname(), argMap.javaTypePath());
				argField.setInitExp(super.readPin(operation, block, arg));
				block.addToLocals(argField);
			}
			OclActionContext oclActionContext = getLibrary().getOclActionContext(node);
			if(!oclActionContext.hasErrors()){
				String expr = valueSpecificationUtil.expressOcl(oclActionContext, operation, getLibrary().getActualType(returnPin));
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(getContainingActivity(), returnPin);
				expressor.buildResultVariable(operation, block, map);
				expr = expressor.storeResults(map, expr, returnPin.isMultivalued());
				block.addToStatements(expr);
			}
		}
	}
}
