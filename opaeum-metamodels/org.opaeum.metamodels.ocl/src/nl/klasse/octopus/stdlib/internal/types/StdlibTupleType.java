/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.model.internal.types.AttributeImpl;
import nl.klasse.octopus.model.internal.types.MultiplicityKindImpl;


/** StdlibTupleType represents all tuple types in OCL. The parts of a tupletype
 *  are represented by its Attributes. Therefore this type has no additional
 *  operations, all is inherited from IClassifier.
 * 
 * @author  Jos Warmer
 * @version $Id: StdlibTupleType.java,v 1.2 2008/01/19 13:31:09 annekekleppe Exp $
 */
public class StdlibTupleType extends StdlibDataType implements ITupleType {  
	
	private List<IVariableDeclaration> parts 	= new ArrayList<IVariableDeclaration>();		// holds VariableDeclarations

	/**
	 * Constructor for TupleTypeImpl.
	 * @param n
	 */
	public StdlibTupleType(String n) {
		super(n);
	}
	
	public void addPart(IVariableDeclaration var) {
		parts.add(var);
	}
	
	public IAttribute findAttribute(String name){
		IAttribute result = null;
		Iterator it = parts.iterator();
		while ( it.hasNext()) {
			IVariableDeclaration var = (IVariableDeclaration) it.next();	
			if (var.getName().equals(name)) {
				result = new AttributeImpl(name, var.getType(), new MultiplicityKindImpl(0, 1));
			}
		}
		return result;
	}
	
	public String getName() {
		String partsStr = "";
		String separator = "";
		Iterator it = parts.iterator();
		while ( it.hasNext()) {
			IVariableDeclaration var = (IVariableDeclaration) it.next();
			partsStr = partsStr + separator + var.getName();
			separator = ", ";
		}
		return "TupleType(" + partsStr + ")";
	}
	
	/**
	 * Sets the parts.
	 * @param parts The parts to set
	 */
	public void setParts(List<IVariableDeclaration> parts) {
		this.parts = parts;
	}

	/**
	 * @return
	 */
	public Collection<IVariableDeclaration> getParts() {
		return parts;
	}

}
