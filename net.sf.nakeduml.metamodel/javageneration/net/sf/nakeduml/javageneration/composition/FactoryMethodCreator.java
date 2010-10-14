package net.sf.nakeduml.javageneration.composition;

import java.util.Iterator;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;

/**
 * This class adds a factory method for compositional relationships. This would be used in conjunction by the CompositionNode semantics to
 * allow users to create object directly from the user interface within the composition hierarchy
 * 
 * @author ampie
 * 
 */
public class FactoryMethodCreator extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void attribute(INakedProperty aw){
		OJAnnotatedClass myOwner = findJavaClass(aw.getOwner());
		if(!aw.isDerived() && isPersistent(aw.getNakedBaseType()) && aw.isComposite()){
			createFactoryMethod(aw, myOwner);
		}
	}
	private void createFactoryMethod(INakedProperty pw,OJClass owner){
		INakedClassifier type = pw.getNakedBaseType();
		Iterator ops = owner.getOperations().iterator();
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
			creator = new OJAnnotatedOperation();
			owner.addToOperations(creator);
			creator.setName(createOperName);
		}
		creator.setReturnType(new OJPathName(type.getMappingInfo().getQualifiedJavaName()));
		OJBlock body = new OJBlock();
		body.addToStatements((type).getMappingInfo().getJavaName() + " newInstance= new " + type.getMappingInfo().getJavaName() + "()");
//		if(pw.getOtherEnd() != null && pw.getOtherEnd().isNavigable()){
//			NameWrapper javaName = pw.getOtherEnd().getMappingInfo().getJavaName();
//			body.addToStatements("newInstance.set" + javaName.getCapped() + "((" + owner.getName() + ")this)");
//		}else if(pw.isOne()){
//			body.addToStatements("this.set" + pw.getMappingInfo().getJavaName().getCapped() + "(newInstance)");
//		}else{
//			body.addToStatements("this.addTo" + pw.getMappingInfo().getJavaName().getCapped() + "(newInstance)");
//		}
		if(type instanceof INakedEntity){
			body.addToStatements("newInstance.init(this)");
		}
		body.addToStatements("return newInstance");
		creator.setBody(body);
	}
}
