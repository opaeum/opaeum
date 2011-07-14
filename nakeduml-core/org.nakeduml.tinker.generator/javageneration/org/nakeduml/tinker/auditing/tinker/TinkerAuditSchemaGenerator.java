package org.nakeduml.tinker.auditing.tinker;

import java.util.Arrays;
import java.util.Collections;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.apache.commons.lang.StringUtils;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.tinker.basicjava.tinker.TinkerSchemaGenerator;
import org.nakeduml.tinker.basicjava.tinker.TinkerUtil;

public class TinkerAuditSchemaGenerator extends AbstractJavaProducingVisitor {

	private OJAnnotatedClass schemaGenerator;
//	private OJAnnotatedOperation createSchema;
	private OJAnnotatedOperation getClassNames;

	public void initialize(OJAnnotatedPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) {
		super.initialize(javaModel, config, textWorkspace, context);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitModelBefore(INakedModel model) {
		schemaGenerator = (OJAnnotatedClass) this.javaModel.findIntfOrCls(new OJPathName(UtilityCreator.getUtilPathName() + "."
				+ TinkerSchemaGenerator.TINKER_SCHEMA_GENERATOR));
//		createSchema = (OJAnnotatedOperation) schemaGenerator.findOperation(TinkerSchemaGenerator.CREATE_SCHEMA, Arrays.asList(TinkerUtil.oGraphDatabase));
		getClassNames = (OJAnnotatedOperation) schemaGenerator.findOperation(TinkerSchemaGenerator.GET_CLASSNAMES, Collections.EMPTY_LIST);
	}

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType) && !c.getIsAbstract()) {
//			OJIfStatement ifNotHasSchema = new OJIfStatement("!rawGraph.getMetadata().getSchema().existsClass(\"" + c.getMappingInfo().getJavaName()
//					+ TinkerAuditCreator.AUDIT + "\")");
//			ifNotHasSchema.addToThenPart("rawGraph.getMetadata().getSchema().createClass(\"" + StringUtils.replace(c.getMappingInfo().getQualifiedPersistentName(), ".", "_") + TinkerAuditCreator.AUDIT
//					+ "\", rawGraph.getMetadata().getSchema().getClass(\"OGraphVertex\") , rawGraph.getStorage().addCluster(\""
//					+ c.getMappingInfo().getJavaName() + TinkerAuditCreator.AUDIT + "\", OStorage.CLUSTER_TYPE.LOGICAL))");
//			createSchema.getBody().getStatements().add(createSchema.getBody().getStatements().size() - 1, ifNotHasSchema);

//			getClassNames.getBody().getStatements().add(getClassNames.getBody().getStatements().size() - 1, new OJSimpleStatement("result.put(" +c.getMappingInfo().getQualifiedJavaName()+ TinkerAuditCreator.AUDIT + ".class, \"" + StringUtils.replace(c.getMappingInfo().getQualifiedPersistentName(), ".", "_") + TinkerAuditCreator.AUDIT  + "\")"));
			getClassNames.getBody().getStatements().add(getClassNames.getBody().getStatements().size() - 1, 
					new OJSimpleStatement("result.put(\"" + StringUtils.replace(c.getMappingInfo().getQualifiedPersistentName(), ".", "_") + TinkerAuditCreator.AUDIT  + "\", "+ c.getMappingInfo().getQualifiedJavaName()+ TinkerAuditCreator.AUDIT + ".class)"));
		}
	}

}
