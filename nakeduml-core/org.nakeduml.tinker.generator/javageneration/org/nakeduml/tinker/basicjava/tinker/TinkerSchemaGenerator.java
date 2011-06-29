package org.nakeduml.tinker.basicjava.tinker;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public class TinkerSchemaGenerator extends AbstractJavaProducingVisitor {

	public static final String CREATE_SCHEMA = "createSchema";
	public static final String GET_CLASSNAMES = "getClassNames";
	public static final String TINKER_SCHEMA_GENERATOR = "TinkerSchemaGenerator";
	private OJAnnotatedClass schemaGenerator;
	private OJAnnotatedOperation createSchema;
	private OJAnnotatedOperation getClassNames;
	
	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitModelBefore(INakedModel model) {
		schemaGenerator = new OJAnnotatedClass(TINKER_SCHEMA_GENERATOR);
		schemaGenerator.addToImplementedInterfaces(TinkerUtil.tinkerSchemaHelperPathName);
		createSchema = new OJAnnotatedOperation(CREATE_SCHEMA);
		createSchema.setVisibility(OJVisibilityKind.PUBLIC);
		createSchema.setStatic(true);
		createSchema.addParam("rawGraph", TinkerUtil.oGraphDatabase);
		UtilityCreator.getUtilPack().addToClasses(schemaGenerator);
		schemaGenerator.addToOperations(createSchema);
		OJField schema = new OJField();
		schema.setName("schema");
		schema.setType(TinkerUtil.schemaPathName);
		schema.setInitExp("rawGraph.getMetadata().getSchema()");
		schemaGenerator.addToImports(TinkerUtil.schemaPathName);
		schemaGenerator.addToImports(TinkerUtil.storagePathName);
		createSchema.getBody().addToLocals(schema);
		
		getClassNames = new OJAnnotatedOperation(GET_CLASSNAMES);
		getClassNames.setVisibility(OJVisibilityKind.PUBLIC);
		OJPathName listOfStrings = new OJPathName("java.util.List");
		listOfStrings.addToElementTypes(new OJPathName("java.lang.String"));
		getClassNames.setReturnType(listOfStrings);
		schemaGenerator.addToOperations(getClassNames);		
		
		OJField result = new OJField();
		result.setName("result");
		result.setType(listOfStrings);
		result.setInitExp("new ArrayList<String>()");
		getClassNames.getBody().addToLocals(result);
		schemaGenerator.addToImports("java.util.List");
		schemaGenerator.addToImports("java.util.ArrayList");
	}

	@VisitAfter(matchSubclasses = true)
	public void visitModelAfter(INakedModel model) {
		createSchema.getBody().addToStatements("schema.save()");
		getClassNames.getBody().addToStatements("return result");
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType) && !c.getIsAbstract()) {
			OJIfStatement ifNotHasSchema = new OJIfStatement("!rawGraph.getMetadata().getSchema().existsClass(\""+c.getMappingInfo().getJavaName()+"\")");
			ifNotHasSchema.addToThenPart("rawGraph.getMetadata().getSchema().createClass(\""+c.getMappingInfo().getJavaName()+"\", rawGraph.getMetadata().getSchema().getClass(\"OGraphVertex\"), rawGraph.getStorage().addCluster(\""+c.getMappingInfo().getJavaName()+"\", OStorage.CLUSTER_TYPE.LOGICAL))");
			createSchema.getBody().addToStatements(ifNotHasSchema);
			
			getClassNames.getBody().addToStatements("result.add(\"" + c.getMappingInfo().getJavaName() + "\")");
		}
		super.createTextPath(schemaGenerator, OutputRootId.DOMAIN_GEN_SRC);
	}

}
