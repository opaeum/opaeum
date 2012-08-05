package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.PackageableElementMap;

import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class StructuredActivityNodeMap extends PackageableElementMap implements ExceptionRaisingMap{
	StructuredActivityNode node;
	public StructuredActivityNodeMap(OJUtil ojUtil, StructuredActivityNode node){
		super(ojUtil,node);
		this.node = node;
	}

	@Override
	public String exceptionOperName(NamedElement e){
		return baseMethodName() + NameConverter.capitalize(e.getName());
	}

	@Override
	public String unhandledExceptionOperName(){
		return baseMethodName() + "UnhandledException";
	}
	protected String baseMethodName(){
		return "on" + NameConverter.capitalize(node.getName());
	}
	@Override
	public OJPathName messageStructurePath(){
		return ojUtil.classifierPathname(node);
	}

	public String completeMethodName(){
		return baseMethodName()+"Completed";
	}
}
