package nl.klasse.octopus.codegen.umlToJava.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.common.CompareVarDeclsByType;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.expressions.TupleLiteralPart;
import org.eclipse.ocl.uml.TupleLiteralExp;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.javageneration.util.OJUtil;

public class TupleTypeMap extends ClassifierMap {
	private TupleType modelClass = null;


	public TupleTypeMap(OJUtil ojUtil, TupleType modelClass) {
		super(ojUtil,modelClass);
		this.modelClass = modelClass;
	}

	/**
	 * @param in
	 * @return
	 */
	public String getClassName() {
		String[] typeNames = get_typenames();
		String result = "TupleType";
		for (int i = 0; i < typeNames.length; i++) {
			result = result + "_" + typeNames[i];
		}
		return result;
	}

	public String[] get_typenames() {
		Collection<Property> parts = modelClass.oclProperties();
		String[] typeNames = new String[parts.size()];
		Iterator<?> it = sort_parts().iterator();
		int j = 0;
		while (it.hasNext()) {
			Property var = (Property) it.next();
			// TODO remove all 'strange' characters from the typeName
			String name = var.getType().toString();
			name = StringHelpers.replaceAllSubstrings(name, "(", "Of");
			name = StringHelpers.replaceAllSubstrings(name, ")", "");
			typeNames[j++] = name;
		}
		return typeNames;
	}

	public Collection<Property> sort_parts() {
		Collection<Property> parts = modelClass.oclProperties();
		return sort(parts);
	}

	public Collection<Property> sort_literal_parts(TupleLiteralExp in) {
		Collection<TupleLiteralPart<Classifier, Property>> parts = in.getPart();
		List<Property> ps = new ArrayList<Property>();
		for (TupleLiteralPart<Classifier, Property> tupleLiteralPart : parts) {
			ps.add(tupleLiteralPart.getAttribute());
		}
		return sort(ps);
	}

	/**
	 * @param parts
	 * @return
	 */
	private Collection<Property> sort(Collection<Property> parts) {
		Comparator<Property> comp = new CompareVarDeclsByType();
		List<Property> sortedCollection = new ArrayList<Property>();
		sortedCollection.addAll(parts);
		Collections.sort(sortedCollection, comp);
		return sortedCollection;
	}

}
