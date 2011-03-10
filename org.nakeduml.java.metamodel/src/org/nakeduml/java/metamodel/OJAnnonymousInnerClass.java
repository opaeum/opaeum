package org.nakeduml.java.metamodel;

import java.util.Map;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.utilities.JavaStringHelpers;
import org.nakeduml.java.metamodel.utilities.JavaUtil;


public class OJAnnonymousInnerClass extends OJAnnotatedField {
	private OJAnnotatedClass classDeclaration = new OJAnnotatedClass();
	private OJPathName outer;

	public OJAnnonymousInnerClass(OJPathName outer, String string, OJPathName ojPathName) {
		super(string, ojPathName);
		classDeclaration.setSuperclass(ojPathName);
		this.outer = outer;
	}

	@Override
	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		// TODO Auto-generated method stub
		super.renameAll(renamePathNames, newName);
		outer.renameAll(renamePathNames, newName);
		classDeclaration.renameAll(renamePathNames, newName);
	}

	public OJAnnonymousInnerClass() {
		// TODO Auto-generated constructor stub
	}

	public OJAnnotatedClass getClassDeclaration() {
		return classDeclaration;
	}

	public void setClassDeclaration(OJAnnotatedClass classDeclaration) {
		this.classDeclaration = classDeclaration;
	}

	@Override
	public String toJavaString() {
		StringBuilder sb = new StringBuilder();
		if (!getComment().equals("")) {
			sb.append("\t// ");
			sb.append(getComment());
			sb.append("\n");
		}
		if (getAnnotations().size() > 0) {
			sb.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(getAnnotations(), "\n"), 0));
			sb.append("\n");
		}
		if (this.getOwner() != null) { // field is part of block statement
			sb.append(visToJava(this));
		}
		if (sb.length() > 0) {
			sb.append(' ');
		}
		sb.append(getType().getCollectionTypeName());
		sb.append(' ');
		sb.append(getName());
		sb.append(" = new ");
		sb.append(getType());
		sb.append("(){");
		sb.append(JavaStringHelpers.indent(classDeclaration.fields(), 2));
		sb.append("\n");
		sb.append(JavaStringHelpers.indent(classDeclaration.operations(), 2));
		sb.append("};");
		// TODO bit aggressive
		return sb.toString().replaceAll("this", outer.getLast() + ".this");
	}

	public OJAnnotatedField getDeepCopy() {
		OJAnnonymousInnerClass copy = new OJAnnonymousInnerClass();
		copyDeepInto(copy);
		return copy;
	}

	public void copyDeepInto(OJAnnonymousInnerClass copy) {
		copy.classDeclaration = this.classDeclaration.getDeepCopy(copy.classDeclaration.getMyPackage());
		copy.outer = this.outer.getDeepCopy();
		super.copyDeepInfoInto(copy);
	}
}
