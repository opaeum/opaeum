package net.sf.nakeduml.javageneration.hibernate;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJEnum;
import org.nakeduml.java.metamodel.annotation.OJEnumLiteral;
import org.nakeduml.java.metamodel.generated.OJVisibilityKindGEN;

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
