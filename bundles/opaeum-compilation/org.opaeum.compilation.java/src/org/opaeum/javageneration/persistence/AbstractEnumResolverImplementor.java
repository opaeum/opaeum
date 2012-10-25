package org.opaeum.javageneration.persistence;

import java.util.Collection;

import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSwitchCase;
import org.opaeum.java.metamodel.OJSwitchStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.runtime.domain.EnumResolver;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

public abstract class AbstractEnumResolverImplementor extends AbstractJavaProducingVisitor{
	public AbstractEnumResolverImplementor(){
		super();
	}
	protected void createResolver(OJEnum ojEnum,Collection<? extends NamedElement> els,String oldQualifiedName){
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
	private OJAnnotatedOperation buildToOpaeumId(OJEnum oje, Collection<? extends NamedElement> els){
		OJAnnotatedOperation toOpaeumId = new OJAnnotatedOperation("toOpaeumId",new OJPathName("long"));
		toOpaeumId.addParam("en", new OJPathName(IEnum.class.getName()));
		toOpaeumId.initializeResultVariable("-1");
		OJSwitchStatement sst= new OJSwitchStatement();
		toOpaeumId.getBody().addToStatements(sst);
		sst.setCondition("("+ oje.getName()+")en");
		for(NamedElement l:els){
			OJSwitchCase sc = new OJSwitchCase();
			sc.setLabel(getLiteralName(l));
			sc.getBody().addToStatements("result = "  + EmfWorkspace.getOpaeumId(l)  + "l");
			sst.addToCases(sc);
		}
		return toOpaeumId;
	}
	protected abstract String getLiteralName(NamedElement l);
	private OJAnnotatedOperation buildFromOpaeumId(OJEnum oje,Collection<? extends NamedElement> els){
		OJAnnotatedOperation toOpaeumId = new OJAnnotatedOperation("fromOpaeumId", new OJPathName(IEnum.class.getName()));
		toOpaeumId.addParam("i", new OJPathName("long"));
		toOpaeumId.initializeResultVariable("null");
		OJBlock elsePart =toOpaeumId.getBody();
		for(NamedElement l:els){
			OJIfStatement sc = new OJIfStatement("i=="+EmfWorkspace.getOpaeumId(l) + "l","result = " + oje.getName() + "." + getLiteralName(l));
			elsePart.addToStatements(sc);
			sc.setElsePart(new OJBlock());
			elsePart=sc.getElsePart();
		}
		return toOpaeumId;
	}
}