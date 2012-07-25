package org.eclipse.uml2.uml;




public interface INakedStructuredActivityNode extends INakedAction,ActivityNodeContainer {

	public abstract void initMessageStructure();

	public abstract INakedMessageStructure getMessageStructure();


}
