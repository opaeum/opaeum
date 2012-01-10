package org.opaeum.javageneration.persistence;

import java.util.Collection;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSwitchCase;
import org.opaeum.java.metamodel.OJSwitchStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

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
		resolver.addToOperations(buildToOpaeumId(ojEnum, els));
		resolver.addToOperations(buildFromOpaeumId(ojEnum, els));
		OJAnnotatedOperation returnedClass = new OJAnnotatedOperation("returnedClass", new OJPathName("Class<?>"));
		returnedClass.getBody().addToStatements("return " + ojEnum.getPathName() + ".class");
		resolver.addToOperations(returnedClass);
		resolver.setSuperclass(new OJPathName("org.opaeum.hibernate.domain.AbstractEnumResolver"));
	}
	private OJAnnotatedOperation buildToOpaeumId(OJEnum oje, Collection<? extends INakedElement> els){
		OJAnnotatedOperation toOpaeumId = new OJAnnotatedOperation("toOpaeumId",new OJPathName("int"));
		toOpaeumId.addParam("en", new OJPathName(IEnum.class.getName()));
		OJAnnotatedField result = new OJAnnotatedField("result", new OJPathName("int"));
		result.setInitExp("-1");
		toOpaeumId.getBody().addToLocals(result);
		OJSwitchStatement sst= new OJSwitchStatement();
		toOpaeumId.getBody().addToStatements(sst);
		sst.setCondition("("+ oje.getName()+")en");
		for(INakedElement l:els){
			OJSwitchCase sc = new OJSwitchCase();
			sc.setLabel(getLiteralName(l));
			sc.getBody().addToStatements("result = "  + l.getMappingInfo().getOpaeumId());
			sst.addToCases(sc);
		}
		toOpaeumId.getBody().addToStatements("return result");
		return toOpaeumId;
	}
	protected abstract String getLiteralName(INakedElement l);
	private OJAnnotatedOperation buildFromOpaeumId(OJEnum oje,Collection<? extends INakedElement> els){
		OJAnnotatedOperation toOpaeumId = new OJAnnotatedOperation("fromOpaeumId", new OJPathName(IEnum.class.getName()));
		toOpaeumId.addParam("i", new OJPathName("int"));
		OJAnnotatedField result = new OJAnnotatedField("result", new OJPathName(IEnum.class.getName()));
		result.setInitExp("null");
		toOpaeumId.getBody().addToLocals(result);
		OJBlock elsePart =toOpaeumId.getBody();
		for(INakedElement l:els){
			OJIfStatement sc = new OJIfStatement("i=="+l.getMappingInfo().getOpaeumId() + "","result = " + oje.getName() + "." + getLiteralName(l));
			elsePart.addToStatements(sc);
			sc.setElsePart(new OJBlock());
			elsePart=sc.getElsePart();
		}
		toOpaeumId.getBody().addToStatements("return result");
		return toOpaeumId;
	}
}