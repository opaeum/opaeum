package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.PackageableElementMap;

import org.eclipse.uml2.uml.ActivityNode;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;


public class ActivityNodeMap extends PackageableElementMap{
	private ActivityNode node;

	public ActivityNodeMap(OJUtil ojUtil,ActivityNode node) {
		super(ojUtil,node);
		this.node = node;
	}
	public String doActionMethod(){
		return "do"+NameConverter.capitalize(node.getName()) + EmfWorkspace.getOpaeumId(node);
	}

}
