package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJInterface;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;

abstract public class OJPackageGEN extends OJElement {
	private Set<OJClass> classes = new HashSet<OJClass>();
	private Set<OJInterface> interfaces = new HashSet<OJInterface>();
	private OJPackage parent = null;
	private Set<OJPackage> subpackages = new HashSet<OJPackage>();

	protected OJPackageGEN(String name) {
		super(name);
	}

	public OJPathName getPathName() {
		return this.getParent().getPathName().append(this.getName());
	}
	public OJClass findClass(OJPathName path) {
		return (path.isSingleName() ? this.findLocalClass(path.getLast()) : this.findLocalPackage(path.getFirst())
				.findClass(path.getTail()));
	}
	protected OJClass findLocalClass(String name) {
		return any1(name);
	}
	public OJPackage findPackage(OJPathName path) {
		return (path.isSingleName() ? this.findLocalPackage(path.getLast()) : this.findLocalPackage(path.getFirst()).findPackage(
				path.getTail()));
	}
	protected OJPackage findLocalPackage(String name) {
		return any2(name);
	}
	public  void addToClasses(OJClass element) {
		if (element == null) {
			return;
		}
		if (this.classes.contains(element)) {
			this.classes.remove(element);
		}
		if (element.getMyPackage() != null) {
			element.getMyPackage().z_internalRemoveFromClasses(element);
		}
		this.classes.add(element);
		element.z_internalAddToMyPackage((OJPackage) ((OJPackage) this));
	}

	public void removeFromClasses(OJClass element) {
		if (element == null) {
			return;
		}
		this.classes.remove(element);
		element.z_internalRemoveFromMyPackage((OJPackage) ((OJPackage) this));
	}

	public Set<OJClass> getClasses() {
		if (classes != null) {
			return Collections.unmodifiableSet(classes);
		} else {
			return null;
		}
	}

	/**
	 * This operation should NOT be used by clients. It implements the correct
	 * addition of an element in an association.
	 * 
	 * @param element
	 */
	public void z_internalAddToClasses(OJClass element) {
		this.classes.add(element);
	}

	/**
	 * This operation should NOT be used by clients. It implements the correct
	 * removal of an element in an association.
	 * 
	 * @param element
	 */
	public void z_internalRemoveFromClasses(OJClass element) {
		this.classes.remove(element);
	}

	public void setInterfaces(Set<OJInterface> elements) {
		if (this.interfaces != elements) {
			Iterator<OJInterface> it = this.interfaces.iterator();
			while (it.hasNext()) {
				OJInterface x = (OJInterface) it.next();
				x.z_internalRemoveFromMyPackage((OJPackage) ((OJPackage) this));
			}
			this.interfaces = elements;
			if (interfaces != null) {
				it = interfaces.iterator();
				while (it.hasNext()) {
					OJInterface x = (OJInterface) it.next();
					x.z_internalAddToMyPackage((OJPackage) ((OJPackage) this));
				}
			}
		}
	}

	public void addToInterfaces(OJInterface element) {
		if (element == null) {
			return;
		}
		if (this.interfaces.contains(element)) {
			this.interfaces.remove(element);
		}
		if (element.getMyPackage() != null) {
			element.getMyPackage().z_internalRemoveFromInterfaces(element);
		}
		this.interfaces.add(element);
		element.z_internalAddToMyPackage((OJPackage) ((OJPackage) this));
	}

	public void removeFromInterfaces(OJInterface element) {
		if (element == null) {
			return;
		}
		this.interfaces.remove(element);
		element.z_internalRemoveFromMyPackage((OJPackage) ((OJPackage) this));
	}

	public Set<OJInterface> getInterfaces() {
		if (interfaces != null) {
			return Collections.unmodifiableSet(interfaces);
		} else {
			return null;
		}
	}
	public void z_internalAddToInterfaces(OJInterface element) {
		this.interfaces.add(element);
	}
	public void z_internalRemoveFromInterfaces(OJInterface element) {
		this.interfaces.remove(element);
	}

	public void setParent(OJPackage element) {
		if (this.parent != element) {
			if (this.parent != null) {
				this.parent.z_internalRemoveFromSubpackages((OJPackage) ((OJPackage) this));
			}
			this.parent = element;
			if (element != null) {
				element.z_internalAddToSubpackages((OJPackage) ((OJPackage) this));
			}
		}
	}
	@Override
	public void finalize(){
		super.finalize();
	}
	

	public OJPackage getParent() {
		return parent;
	}

	public void z_internalAddToParent(OJPackage element) {
		this.parent = element;

	}

	public void z_internalRemoveFromParent(OJPackage element) {
		this.parent = null;
	}

	public void addToSubpackages(OJPackage element) {
		if (element == null) {
			return;
		}
		if (this.subpackages.contains(element)) {
			this.subpackages.remove(element);
		}
		if (element.getParent() != null) {
			element.getParent().z_internalRemoveFromSubpackages(element);
		}
		this.subpackages.add(element);
		element.z_internalAddToParent((OJPackage) ((OJPackage) this));
	}

	public void removeFromSubpackages(OJPackage element) {
		if (element == null) {
			return;
		}
		this.subpackages.remove(element);
		element.z_internalRemoveFromParent((OJPackage) ((OJPackage) this));
	}
	public Set<OJPackage> getSubpackages() {
		if (subpackages != null) {
			return Collections.unmodifiableSet(subpackages);
		} else {
			return null;
		}
	}

	public void z_internalAddToSubpackages(OJPackage element) {
		for (OJPackage pkg : subpackages) {
			if (pkg.getName().equals(element.getName()))
				throw new RuntimeException();
		}
		this.subpackages.add(element);
	}
	public void z_internalRemoveFromSubpackages(OJPackage element) {
		this.subpackages.remove(element);
	}

	private OJClass any1(String name) {
		OJClass result = null;
		Iterator<OJClass> it = this.getClasses().iterator();
		while (it.hasNext()) {
			OJClass c = (OJClass) it.next();
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return result;
	}

	private OJPackage any2(String name) {
		OJPackage result = null;
		Iterator<OJPackage> it = this.getSubpackages().iterator();
		while (it.hasNext()) {
			OJPackage c = (OJPackage) it.next();
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return result;
	}

	public void copyInfoInto(OJPackage copy) {
		super.copyInfoInto(copy);
		Iterator<OJClass> classesIt = new ArrayList<OJClass>(getClasses()).iterator();
		while (classesIt.hasNext()) {
			OJClass elem = (OJClass) classesIt.next();
			copy.addToClasses(elem);
		}
		Iterator<OJInterface> interfacesIt = new ArrayList<OJInterface>(getInterfaces()).iterator();
		while (interfacesIt.hasNext()) {
			OJInterface elem = (OJInterface) interfacesIt.next();
			copy.addToInterfaces(elem);
		}
		if (getParent() != null) {
			copy.setParent(getParent());
		}
		Iterator<OJPackage> subpackagesIt = new ArrayList<OJPackage>(getSubpackages()).iterator();
		while (subpackagesIt.hasNext()) {
			OJPackage elem = (OJPackage) subpackagesIt.next();
			copy.addToSubpackages(elem);
		}
	}
}