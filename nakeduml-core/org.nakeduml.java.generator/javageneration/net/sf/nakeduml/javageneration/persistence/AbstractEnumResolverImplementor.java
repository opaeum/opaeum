package net.sf.nakeduml.javageneration.persistence;

import java.util.Collection;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.metamodel.core.INakedElement;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSwitchCase;
import org.nakeduml.java.metamodel.OJSwitchStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJEnum;
import org.nakeduml.runtime.domain.EnumResolver;
import org.nakeduml.runtime.domain.IEnum;

public abstract class AbstractEnumResolverImplementor extends AbstractJavaProducingVisitor{
	public AbstractEnumResolverImplementor(){
		super();
	}

	protected void createResolver(OJEnum ojEnum,Collection<? extends INakedElement> els, String oldQualifiedName){
		if(oldQualifiedName!=null){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(oldQualifiedName+"Resolver"));
		}
		OJAnnotatedClass resolver = new OJAnnotatedClass(ojEnum.getName() + "Resolver");
		ojEnum.getMyPackage().addToClasses(resolver);
		createTextPath(resolver, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		resolver.addToImplementedInterfaces(new OJPathName(EnumResolver.class.getName()));
		resolver.addToOperations(buildToNakedUmlId(ojEnum,els));
		resolver.addToOperations(buildFromNakedUmlId(ojEnum,els));
		OJAnnotatedOperation returnedClass = new OJAnnotatedOperation("returnedClass", new OJPathName("Class<?>"));
		returnedClass.getBody().addToStatements("return " + ojEnum.getPathName() + ".class");
		resolver.addToOperations(returnedClass);
		resolver.setSuperclass(new OJPathName("org.nakeduml.hibernate.domain.AbstractEnumResolver"));
	}

	private OJAnnotatedOperation buildToNakedUmlId(OJEnum oje, Collection<? extends INakedElement> els){
		OJAnnotatedOperation toNakedUmlId = new OJAnnotatedOperation("toNakedUmlId",new OJPathName("int"));
		toNakedUmlId.addParam("en", new OJPathName(IEnum.class.getName()));
		OJAnnotatedField result = new OJAnnotatedField("result", new OJPathName("int"));
		result.setInitExp("-1");
		toNakedUmlId.getBody().addToLocals(result);
		OJSwitchStatement sst= new OJSwitchStatement();
		toNakedUmlId.getBody().addToStatements(sst);
		sst.setCondition("("+ oje.getName()+")en");
		for(INakedElement l:els){
			OJSwitchCase sc = new OJSwitchCase();
			sc.setLabel(l.getName().toUpperCase());
			sc.getBody().addToStatements("result = "  + l.getMappingInfo().getNakedUmlId());
			sst.addToCases(sc);
		}
		toNakedUmlId.getBody().addToStatements("return result");
		return toNakedUmlId;
	}

	private OJAnnotatedOperation buildFromNakedUmlId(OJEnum oje, Collection<? extends INakedElement> els){
		OJAnnotatedOperation toNakedUmlId = new OJAnnotatedOperation("fromNakedUmlId",new OJPathName(IEnum.class.getName()));
		toNakedUmlId.addParam("i", new OJPathName("int"));
		OJAnnotatedField result = new OJAnnotatedField("result", new OJPathName(IEnum.class.getName()));
		result.setInitExp("null");
		toNakedUmlId.getBody().addToLocals(result);
		OJSwitchStatement sst= new OJSwitchStatement();
		toNakedUmlId.getBody().addToStatements(sst);
		sst.setCondition("i");
		for(INakedElement l:els){
			OJSwitchCase sc = new OJSwitchCase();
			sc.setLabel(l.getMappingInfo().getNakedUmlId()+"");
			sc.getBody().addToStatements("result = " + oje.getName() + "." +l.getName().toUpperCase());
			sst.addToCases(sc);
		}
		toNakedUmlId.getBody().addToStatements("return result");
		return toNakedUmlId;
	}
}