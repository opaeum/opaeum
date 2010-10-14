package net.sf.nakeduml.javageneration.seam;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.javametamodel.annotation.OJEnumLiteral;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class BooleanFilterMaker extends AbstractJavaProducingVisitor {
	private OJEnum booleanFilter;
	@Override
	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		booleanFilter = new OJEnum();
		booleanFilter.setName("BooleanFilter");
		OJPathName path = new OJPathName("util");
		OJPackage pack =  this.javaModel.findPackage(path);
		booleanFilter.setMyPackage(pack);
		
		OJEnumLiteral ojLiteral = new OJEnumLiteral("NONE");
		OJField none = new OJField();
		none.setInitExp("null");
		ojLiteral.addToAttributeValues(none);
		booleanFilter.addToLiterals(ojLiteral);
		ojLiteral = new OJEnumLiteral("TRUE");
		OJField truth = new OJField();
		truth.setInitExp("true");
		truth.setType(new OJPathName("java.lang.Boolean"));
		ojLiteral.addToAttributeValues(truth);
		booleanFilter.addToLiterals(ojLiteral);
		ojLiteral = new OJEnumLiteral("FALSE");
		OJField falsehood = new OJField();
		falsehood.setInitExp("false");
		truth.setType(new OJPathName("java.lang.Boolean"));
		ojLiteral.addToAttributeValues(falsehood);
		booleanFilter.addToLiterals(ojLiteral);
		
		OJField field = new OJField();
		field.setName("bool");
		field.setType(new OJPathName("java.lang.Boolean"));
		booleanFilter.addToFields(field);
		
		OJConstructor constructor = new OJConstructor();
		constructor.addParam("bool", new OJPathName("java.lang.Boolean"));
		booleanFilter.addToConstructors(constructor);
		OJStatement statement = new OJSimpleStatement("this.bool = bool");
		constructor.getBody().addToStatements(statement);
		
		buildGetter(booleanFilter, new OJPathName("java.lang.Boolean"));
		buildSetter(booleanFilter, new OJPathName("java.lang.Boolean"));
		
		super.createTextPath(booleanFilter, JavaTextSource.GEN_SRC);
	}
	
	private OJOperation buildGetter(OJEnum owner,OJPathName javaTypePath){
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName("getBool");
		getter.setReturnType(javaTypePath);
		owner.addToOperations(getter);
		getter.getBody().addToStatements("return bool");
		return getter;
	}
	
	private void buildSetter(OJEnum owner,OJPathName javaTypePath) {
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName("setBool");
		setter.addParam("bool", javaTypePath);
		owner.addToOperations(setter);
		setter.getBody().addToStatements("this.bool = bool");
	}
	
}
