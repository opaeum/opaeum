package nl.klasse.octopus.model.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.modelVisitors.IPackageVisitor;


public class InterfaceImpl extends ClassifierImpl implements IInterface {
	static public InterfaceImpl DUMMY = new InterfaceImpl("dummyInterface");
	private boolean isFacade = false;

	private List<IClassifier> implementingClasses;

	/**
	 * 
	 */
	public InterfaceImpl(String n) {
		super(n);
		implementingClasses = new ArrayList<IClassifier>();
	}
	
	/**
	 * @param impl
	 */
	public void addImplementingClass(ClassifierImpl impl) {
		implementingClasses.add(impl);	
	}
	
	/**
	 * @param impl
	 */
	public void removeImplementingClass(ClassifierImpl impl) {
		implementingClasses.remove(impl);	
	}

	/**
	 * @return
	 */
	public Collection<IClassifier> getImplementingClasses() {
		return Collections.unmodifiableList(implementingClasses);	
	}

	/**
	 * @return
	 */
	public boolean isFacade() {
		return isFacade;
	}

	/**
	 * @param b
	 */
	public void setFacade(boolean b) {
		isFacade = b;
	}

}
