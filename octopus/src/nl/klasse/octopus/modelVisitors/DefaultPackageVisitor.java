/*
 * Created on Apr 30, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.modelVisitors;

import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.IState;

/**
 * DefaultPackageVisitor : 
 */
public class DefaultPackageVisitor implements IPackageVisitor {

	public DefaultPackageVisitor() {
		super();
	}

	public void package_Before(IPackage p) {
	}

	public void package_After(IPackage p) {
	}

	public void class_Before(IClassifier c) {
	}

	public void class_After(IClassifier c) {
	}

	public void interface_Before(IInterface c) {
	}

	public void interface_After(IInterface c) {
	}

	public void operation_Before(IOperation o) {
	}

	public void operation_After(IOperation o) {
	}

	public void parameter(IParameter o) {
	}

	public void attribute(IAttribute a) {
	}

	public void state(IState s) {
	}

	public void association(IAssociation a) {
	}

	public void navigation(IAssociationEnd assend) {
	}

	public boolean visitClasses() {
		return true;
	}

	public boolean visitInterfaces() {
		return true;
	}

	public boolean visitAttributes() {
		return true;
	}

	public boolean visitOperations() {
		return true;
	}

	public boolean visitParameters() {
		return true;
	}

	public boolean visitStates() {
		return true;
	}

	public boolean visitAssociations() {
		return true;
	}

	public boolean visitNavigations() {
		return true;
	}

}
