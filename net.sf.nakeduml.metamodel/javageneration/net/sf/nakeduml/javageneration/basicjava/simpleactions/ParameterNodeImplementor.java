package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedParameterNode;
import net.sf.nakeduml.name.NameConverter;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.oclengine.IOclEngine;

public class ParameterNodeImplementor extends SimpleActionBuilder<INakedParameterNode>{
	public ParameterNodeImplementor(IOclEngine oclEngine,INakedParameterNode action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getParameter().isResult()){
			if(node.getParameter().isException()){
				operation.getOwner().addToImports(maybeCreateUtilException());
				block.addToStatements("throw new ExceptionParameter(\"" + node.getParameter().getName() + "\","
						+ expressInputPinOrOutParam(block, node) + ")");
			}else{
				block.addToStatements("return " + expressInputPinOrOutParam(block, node));
			}
		}
	}
	private OJPathName maybeCreateUtilException(){
		OJClass ojClass = UtilityCreator.getUtilPack().findClass(new OJPathName("ExceptionParameter"));
		if(ojClass == null){
			OJAnnotatedClass c = new OJAnnotatedClass();
			c.setName("ExceptionParameter");
			c.setSuperclass(new OJPathName("java.lang.RuntimeException"));
			OJConstructor con = new OJConstructor();
			c.addToConstructors(con);
			addField(con, "parameterName", "String");
			addField(con, "value", "Object");
			OJAnnotatedOperation oper = new OJAnnotatedOperation();
			oper.setName("isParameter");
			oper.addParam("name", "String");
			oper.getBody().addToStatements("return name.equals(parameterName)");
			oper.setReturnType(new OJPathName("boolean"));
			c.addToOperations(oper);
			ojClass = c;
			UtilityCreator.getUtilPack().addToClasses(c);
		}
		return ojClass.getPathName();
	}
	private void addField(OJConstructor con,String name,String type){
		OJAnnotatedField field = new OJAnnotatedField();
		field.setName(name);
		field.setType(new OJPathName(type));
		((OJAnnotatedClass) con.getOwner()).addToFields(field);
		con.addParam(name, type);
		con.getBody().addToStatements("this." + name + "=" + name);
		OJAnnotatedOperation getValue = new OJAnnotatedOperation();
		getValue.setName("get" + NameConverter.capitalize(name));
		getValue.getBody().addToStatements("return " + name);
		getValue.setReturnType(new OJPathName(type));
		con.getOwner().addToOperations(getValue);
	}
}
