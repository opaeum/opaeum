package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.tools.common.StringHelpers;

import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

/**
 * NavToAssocClassMap : This class holds all the information on how a UML association end that is part of an association class is
 * transformed.
 * 
 * An association end that is part of an association class is like a 'normal' association end transformed into a field, getter, setter,
 * adder, remover, etc. The difference is that the type of the field is the Java class generated from its association class. Likewise, the
 * types of the operations are changed.
 * 
 * This class is able to give information on the following items. -
 */
public class NavToAssocClassMap{
	protected AssociationClass assocClass = null;
	protected Property assocEnd = null;
	public NavToAssocClassMap(Property ae){
		assocEnd = ae;
		assocClass = (AssociationClass) ae.getAssociation();
	}
	public String umlName(){
		return assocClass.getName();
	}
	public String getter(){
		String name = buildAssocEndName(assocClass, assocEnd);
		return "get" + NameConverter.capitalize(name);
	}

	public String buildAssocEndName(AssociationClass assoc,Property end){
		String name = assoc.getName();
		Property otherEnd = end.getOtherEnd();
		boolean useNameExtension = (end.getType() == otherEnd.getType());
		if(useNameExtension){
			name = assoc.getName() + "_" + otherEnd.getName();
		}
		return name;
	}

}
