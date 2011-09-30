/*
 * Created on Jan 28, 2005
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.common.CompareVarDeclsByType;
import nl.klasse.octopus.expressions.ITupleLiteralExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.tools.common.StringHelpers;

/**
 * @author anneke
 *
 * TupleTypeMap ...
 */
public class TupleTypeMap extends ClassifierMap {
	private ITupleType modelClass = null;

	/**
	 * @param modelClass
	 */
	public TupleTypeMap(ITupleType modelClass) {
		super(modelClass);
		this.modelClass = modelClass;
	}

	/**
	 * @param in
	 * @return
	 */
	public String getClassName() {
		String[] typeNames = get_typenames();
		String result = "TupleType";
		for(int i=0; i<typeNames.length; i++) {
			result = result + "_" + typeNames[i];
		}
		return result;
	}
	
	public String[] get_typenames() {
		Collection<IVariableDeclaration> parts = modelClass.getParts();
		String[] typeNames = new String[parts.size()];
		Iterator<?> it = sort_parts().iterator();
		int j = 0;
		while(it.hasNext()) {
			IVariableDeclaration var = (IVariableDeclaration) it.next();
			// TODO remove all 'strange' characters from the typeName
			String name = var.getType().toString();
			name = StringHelpers.replaceAllSubstrings(name, "(", "Of");
			name = StringHelpers.replaceAllSubstrings(name, ")", "");
			typeNames[j++] = name;
		}
		return typeNames;
	}

	public Collection<IVariableDeclaration> sort_parts() {
		Collection<IVariableDeclaration> parts = modelClass.getParts();
		return sort(parts);
	}
	
	public Collection<IVariableDeclaration> sort_literal_parts(ITupleLiteralExp in) {
		Collection<IVariableDeclaration> parts = in.getTupleParts();
		return sort(parts);
	}

	/**
	 * @param parts
	 * @return
	 */
	private Collection<IVariableDeclaration> sort(Collection<IVariableDeclaration> parts) {
		Comparator<IVariableDeclaration> comp = new CompareVarDeclsByType();
		List<IVariableDeclaration> sortedCollection = new ArrayList<IVariableDeclaration>();
		sortedCollection.addAll(parts);
		Collections.sort(sortedCollection, comp);
		return sortedCollection;
	}

}
