package org.opeum.javageneration.persistence;

import java.util.Collection;

import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJSwitchCase;
import org.opeum.java.metamodel.OJSwitchStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJEnum;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.runtime.domain.EnumResolver;
import org.opeum.runtime.domain.IEnum;
import org.opeum.textmetamodel.JavaSourceFolderIdentifier;

public abstract class AbstractEnumResolverImplementor extends AbstractJavaProducingVisitor{
	public AbstractEnumResolverImplementor(){
		super();
	}
	protected void createResolver(OJEnum ojEnum,Collection<? extends INakedElement> els,String oldQualifiedName){
		if(oldQualifiedName != null){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(oldQualifiedName + "Resolver"));
		}
		OJAnnotatedClass resolver = new OJAnnotatedClass(ojEnum.getName() + "Resolver");
		ojEnum.getMyPackage().addToClasses(resolver);
		createTextPath(resolver, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
		resolver.addToImplementedInterfaces(new OJPathName(EnumResolver.class.getName()));
		resolver.addToOperations(buildToOpeumId(ojEnum, els));
		resolver.addToOperations(buildFromOpeumId(ojEnum, els));
		OJAnnotatedOperation returnedClass = new OJAnnotatedOperation("returnedClass", new OJPathName("Class<?>"));
		returnedClass.getBody().addToStatements("return " + ojEnum.getPathName() + ".class");
		resolver.addToOperations(returnedClass);
		resolver.setSuperclass(new OJPathName("org.opeum.hibernate.domain.AbstractEnumResolver"));
	}
	private OJAnnotatedOperation buildToOpeumId(OJEnum oje, Collection<? extends INakedElement> els){
		OJAnnotatedOperation toOpeumId = new OJAnnotatedOperation("toOpeumId",new OJPathName("int"));
		toOpeumId.addParam("en", new OJPathName(IEnum.class.getName()));
		OJAnnotatedField result = new OJAnnotatedField("result", new OJPathName("int"));
		result.setInitExp("-1");
		toOpeumId.getBody().addToLocals(result);
		OJSwitchStatement sst= new OJSwitchStatement();
		toOpeumId.getBody().addToStatements(sst);
		sst.setCondition("("+ oje.getName()+")en");
		for(INakedElement l:els){
			OJSwitchCase sc = new OJSwitchCase();
			sc.setLabel(getLiteralName(l));
			sc.getBody().addToStatements("result = "  + l.getMappingInfo().getOpeumId());
			sst.addToCases(sc);
		}
		toOpeumId.getBody().addToStatements("return result");
		return toOpeumId;
	}
	protected abstract String getLiteralName(INakedElement l);
	private OJAnnotatedOperation buildFromOpeumId(OJEnum oje,Collection<? extends INakedElement> els){
		OJAnnotatedOperation toOpeumId = new OJAnnotatedOperation("fromOpeumId", new OJPathName(IEnum.class.getName()));
		toOpeumId.addParam("i", new OJPathName("int"));
		OJAnnotatedField result = new OJAnnotatedField("result", new OJPathName(IEnum.class.getName()));
		result.setInitExp("null");
		toOpeumId.getBody().addToLocals(result);
		OJSwitchStatement sst = new OJSwitchStatement();
		toOpeumId.getBody().addToStatements(sst);
		sst.setCondition("i");
		for(INakedElement l:els){
			OJSwitchCase sc = new OJSwitchCase();
			sc.setLabel(l.getMappingInfo().getOpeumId() + "");
			sc.getBody().addToStatements("result = " + oje.getName() + "." + getLiteralName(l));
			sst.addToCases(sc);
		}
		toOpeumId.getBody().addToStatements("return result");
		return toOpeumId;
	}
}