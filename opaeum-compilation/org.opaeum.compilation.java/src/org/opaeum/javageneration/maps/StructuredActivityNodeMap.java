package org.opaeum.javageneration.maps;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.core.INakedElement;

public class StructuredActivityNodeMap implements ExceptionRaisingMap{
	INakedStructuredActivityNode node;
	public StructuredActivityNodeMap(INakedStructuredActivityNode node){
		super();
		this.node = node;
	}

	@Override
	public String exceptionOperName(INakedElement e){
		return baseMethodName() + e.getMappingInfo().getJavaName().getCapped();
	}

	@Override
	public String unhandledExceptionOperName(){
		return baseMethodName() + "UnhandledException";
	}
	protected String baseMethodName(){
		return "on" + node.getMappingInfo().getJavaName().getCapped();
	}
	@Override
	public OJPathName messageStructurePath(){
		return OJUtil.classifierPathname(node.getMessageStructure());
	}

	public String completeMethodName(){
		return baseMethodName()+"Completed";
	}
}
