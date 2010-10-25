package net.sf.nakeduml.javametamodel;

import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.utilities.JavaStringHelpers;
import net.sf.nakeduml.javametamodel.utilities.JavaUtil;

public class OJAnnonymousInnerClass extends OJAnnotatedField {
	private OJAnnotatedClass classDeclaration = new OJAnnotatedClass();

	public OJAnnonymousInnerClass(String string, OJPathName ojPathName) {
		super(string,ojPathName);
		classDeclaration.setSuperclass(ojPathName);
		// TODO Auto-generated constructor stub
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
		return sb.toString();
	}
	public OJAnnotatedField getDeepCopy(){
		OJAnnonymousInnerClass copy= new OJAnnonymousInnerClass();
		copyDeepInto(copy);
		return copy;
	}

	public void copyDeepInto(OJAnnonymousInnerClass copy) {
		copy.classDeclaration=classDeclaration;
		super.copyDeepInfoInto(copy);
	}
}
