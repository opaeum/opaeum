package net.sf.nakeduml.javametamodel.annotation;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.generated.OJVisibilityKindGEN;
import net.sf.nakeduml.javametamodel.utilities.JavaStringHelpers;
import net.sf.nakeduml.javametamodel.utilities.JavaUtil;

public class OJEnum extends OJAnnotatedClass{
	List<OJEnumLiteral> f_literals = new ArrayList<OJEnumLiteral>();
	public void addToLiterals(OJEnumLiteral literal){
		this.f_literals.add(literal);
	}
	public List<OJEnumLiteral> getLiterals(){
		return this.f_literals;
	}
	@Override
	public String toJavaString(){
		this.calcImports();
		StringBuffer classInfo = new StringBuffer();
		classInfo.append(getMyPackage().toJavaString());
		classInfo.append("\n");
		classInfo.append(imports());
		classInfo.append("\n");
		addJavaDocComment(classInfo);
		if(this.getNeedsSuppress()){
			classInfo.append("@SuppressWarnings(\"serial\")\n");
		}
		classInfo.append(JavaStringHelpers.indent(JavaUtil.collectionToJavaString(this.getAnnotations(), "\n"), 0));
		if(this.isAbstract()){
			classInfo.append("abstract ");
		}
		classInfo.append(visToJava(this) + " ");
		classInfo.append("enum " + getName());
		if(getSuperclass() != null){
			classInfo.append(" extends " + getSuperclass().getLast());
		}
		classInfo.append(implementedInterfaces());
		classInfo.append(" {\n");
		classInfo.append(JavaStringHelpers.indent(literals(), 1));
		classInfo.append(";\n");
		classInfo.append(JavaStringHelpers.indent(fields(), 1));
		classInfo.append("\n");
		classInfo.append(JavaStringHelpers.indent(constructors(), 1));
		classInfo.append("\n");
		classInfo.append(JavaStringHelpers.indent(operations(), 1));
		classInfo.append("\n}");
		return classInfo.toString();
	}
	@Override
	public StringBuffer constructors(){
		StringBuffer result = new StringBuffer();
		for(OJConstructor c:getConstructors()){
			c.setVisibility(OJVisibilityKindGEN.PRIVATE);
			result.append(c.toJavaString());
		}
		return result;
	}
	private String literals(){
		return JavaStringHelpers.indent(JavaUtil.collectionToJavaString(this.getLiterals(), ",\n"), 0);
	}
	public OJEnumLiteral findLiteral(String name){
		for(OJEnumLiteral l:this.getLiterals()){
			if(l.getName().equals(name)){
				return l;
			}
		}
		return null;
	}
}
