package net.sf.nakeduml.javageneration.hibernate;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.javametamodel.annotation.OJEnumLiteral;
import net.sf.nakeduml.javametamodel.generated.OJVisibilityKindGEN;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;

public class EnumerationLiteralNameAdder extends AbstractJavaProducingVisitor {
	@VisitBefore(matchSubclasses=true)
	public void visitClass(INakedEnumeration ne) {
			OJEnum je = (OJEnum) findJavaClass(ne);
			addSqlNameInitialization(je);
			List<INakedEnumerationLiteral> literals = (ArrayList)ne.getLiterals();
			for (INakedEnumerationLiteral l : literals) {
				OJAnnotatedField field = new OJAnnotatedField();
				field.setType(new OJPathName("String"));
				field.setName("sqlName");
				field.setInitExp("\"" + l.getMappingInfo().getPersistentName() + "\"");
				OJEnumLiteral jl = je.findLiteral(l.getMappingInfo().getJavaName().getAsIs());
				jl.addToAttributeValues(field);
			}
	}

	private void addSqlNameInitialization(OJEnum je) {
		if (je.getConstructors().isEmpty()) {
			OJConstructor constr = new OJConstructor();
			constr.setVisibility(OJVisibilityKindGEN.PRIVATE);
			je.addToConstructors(constr);
		}
		for (OJConstructor constr : je.getConstructors()) {
			constr.addParam("sqlName", new OJPathName("String"));
			constr.getBody().addToStatements("this.sqlName=sqlName");
		}
		OJUtil.addMethod(je, "getSqlName", "String", "this.sqlName");
		OJAnnotatedField field = new OJAnnotatedField();
		field.setName("sqlName");
		field.setComment("This field will be used as values for the column where this enum is stored");
		field.setType(new OJPathName("String"));
		je.addToFields(field);
	}

}
