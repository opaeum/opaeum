package org.opaeum.javageneration.composition;

import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.EndToAssociationClass;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.name.NameConverter;

/**
 * This class adds a factory method for compositional relationships. This would be used in conjunction by the CompositionNode semantics to
 * allow users to create object directly from the user interface within the composition hierarchy
 * 
 * @author ampie
 * 
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class})
public class FactoryMethodCreator extends AbstractStructureVisitor{
	private void createFactoryMethod(Property pw,OJClass owner){
		PropertyMap map = ojUtil.buildStructuralFeatureMap(pw);
		Iterator<OJOperation> ops = owner.getOperations().iterator();
		OJOperation creator = null;
		String createOperName = "create" + NameConverter.capitalize(NameConverter.toJavaVariableName(pw.getName()));
		while(ops.hasNext()){
			OJOperation op = (OJOperation) ops.next();
			if(op.getParameters().size() == pw.getQualifiers().size() && op.getName().equals(createOperName)){
				creator = op;
				break;
			}
		}
		if(creator == null){
			creator = new OJAnnotatedOperation(createOperName);
			owner.addToOperations(creator);
			for(Property p:pw.getQualifiers()){
				PropertyMap m = ojUtil.buildStructuralFeatureMap(p);
				creator.addParam(m.fieldname(), m.javaTypePath());
			}
		}
		creator.setReturnType(map.javaBaseTypePath());
		OJBlock body = new OJBlock();
		body.addToStatements(map.javaBaseType() + " newInstance= new " + map.javaBaseType() + "()");
		for(Property p:pw.getQualifiers()){
			PropertyMap m = ojUtil.buildStructuralFeatureMap(p);
			body.addToStatements("newInstance." + m.setter() + "(" + m.fieldname() + ")");
		}
		// if(pw.getOtherEnd() != null && pw.getOtherEnd().isNavigable()){
		// NameWrapper javaName = pw.getOtherEnd().getName();
		// body.addToStatements("newInstance.set" + javaName.getCapped() + "((" + owner.getName() + ")this)");
		// }else if(pw.isOne()){
		// body.addToStatements("this.set" + pw.getName().getCapped() + "(newInstance)");
		// }else{
		// body.addToStatements("this.addTo" + pw.getName().getCapped() + "(newInstance)");
		// }
		if(EmfClassifierUtil.isCompositionParticipant(map.getBaseType())){
			body.addToStatements("newInstance.init(this)");
		}
		body.addToStatements("return newInstance");
		creator.setBody(body);
	}
	@Override
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap map){
		visitProperty(findJavaClass(c), c, map.getMap());
	}
	@Override
	protected void visitProperty(OJAnnotatedClass myOwner,Classifier owner,PropertyMap map){
		Property aw = map.getProperty();
		if(!EmfPropertyUtil.isDerived(aw) && isPersistent(map.getBaseType()) && aw.isComposite()
				&& !((Classifier) map.getBaseType()).isAbstract() && !(map.getProperty() instanceof EndToAssociationClass)){
			createFactoryMethod(aw, myOwner);
		}
	}
	@Override
	protected void visitInterfaceProperty(OJAnnotatedClass oj,Interface owner,PropertyMap map){
		visitProperty(oj, owner, map);
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass c,Classifier umlOwner){
		return true;
	}
}
