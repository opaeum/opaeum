package org.opaeum.javageneration.hibernate;

import java.util.List;

import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;

public class EnumerationLiteralNameAdder extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	@SuppressWarnings({
			"rawtypes","unchecked"
	})
	public void visitClass(INakedEnumeration ne){
		OJEnum je = (OJEnum) findJavaClass(ne);
		addSqlNameInitialization(je);
		List<INakedEnumerationLiteral> literals = (List) ne.getLiterals();
		for(INakedEnumerationLiteral l:literals){
			OJAnnotatedField field = new OJAnnotatedField("sqlName", new OJPathName("String"));
			field.setInitExp("\"" + l.getMappingInfo().getPersistentName() + "\"");
			OJEnumLiteral jl = je.findLiteral(l.getMappingInfo().getJavaName().getAsIs());
			jl.addToAttributeValues(field);
		}
	}
	private void addSqlNameInitialization(OJEnum je){
		if(je.getConstructors().isEmpty()){
			OJConstructor constr = new OJConstructor();
			constr.setVisibility(OJVisibilityKindGEN.PRIVATE);
			je.addToConstructors(constr);
		}
		for(OJConstructor constr:je.getConstructors()){
			constr.addParam("sqlName", new OJPathName("String"));
			constr.getBody().addToStatements("this.sqlName=sqlName");
		}
		OJUtil.addMethod(je, "getSqlName", "String", "this.sqlName");
		OJAnnotatedField field = new OJAnnotatedField("sqlName", new OJPathName("String"));
		field.setComment("This field will be used as values for the column where this enum is stored");
		je.addToFields(field);
	}
}
