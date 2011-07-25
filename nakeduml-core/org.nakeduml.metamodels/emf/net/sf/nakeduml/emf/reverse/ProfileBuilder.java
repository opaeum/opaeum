/*
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - initial API and implementation
 *
 * $Id: IntroductionToUML2Profiles.java,v 1.3 2006/07/18 14:13:48 khussey Exp $
 */
package net.sf.nakeduml.emf.reverse;
import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
/**
 * Source code for the "Introduction to UML2 Profiles" article.
 */
public class ProfileBuilder extends EmfElementCreator {
	protected static Generalization createGeneralization(Classifier specificClassifier, Classifier generalClassifier) {
		Generalization generalization = specificClassifier.createGeneralization(generalClassifier);
		out("Generalization " + specificClassifier.getQualifiedName() + " ->> " + generalClassifier.getQualifiedName()
				+ " created.");
		return generalization;
	}
	public static Property createAttribute(org.eclipse.uml2.uml.Class class_, String name, Type type,
			int lowerBound, int upperBound) {
		Property attribute = class_.createOwnedAttribute(name, type, lowerBound, upperBound);
		return attribute;
	}
	public static Profile createProfile(String name) {
		Profile profile = UMLFactory.eINSTANCE.createProfile();
		profile.setName(name);
		return profile;
	}
	protected static PrimitiveType importPrimitiveType(org.eclipse.uml2.uml.Package package_, String name) {
		Model umlLibrary = (Model) load(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		PrimitiveType primitiveType = (PrimitiveType) umlLibrary.getOwnedType(name);
		package_.createElementImport(primitiveType);
		return primitiveType;
	}
	public static Stereotype createStereotype(Profile profile, String name, boolean isAbstract) {
		return profile.createOwnedStereotype(name, isAbstract);
	}
	protected static org.eclipse.uml2.uml.Class referenceMetaclass(Profile profile, String name) {
		Model umlMetamodel = (Model) load(URI.createURI(UMLResource.UML_METAMODEL_URI));
		org.eclipse.uml2.uml.Class metaclass = (org.eclipse.uml2.uml.Class) umlMetamodel.getOwnedType(name);
		profile.createMetaclassReference(metaclass);
		return metaclass;
	}
	protected static Extension createExtension(org.eclipse.uml2.uml.Class metaclass, Stereotype stereotype,
			boolean required) {
		return stereotype.createExtension(metaclass, required);
	}
	protected static void defineProfile(Profile profile) {
		profile.define();
	}
	protected static void applyProfile(org.eclipse.uml2.uml.Package package_, Profile profile) {
		package_.applyProfile(profile);
	}
	protected static void applyStereotype(NamedElement namedElement, Stereotype stereotype) {
		namedElement.applyStereotype(stereotype);
	}
	protected static Object getStereotypePropertyValue(NamedElement namedElement, Stereotype stereotype,
			Property property) {
		return namedElement.getValue(stereotype, property.getName());
	}
	protected static void setStereotypePropertyValue(NamedElement namedElement, Stereotype stereotype,
			Property property, Object value) {
		namedElement.setValue(stereotype, property.getName(), value);
		out("Value of stereotype property '" + property.getQualifiedName() + "' on originalElement '"
				+ namedElement.getQualifiedName() + "' set to " + String.valueOf(value) + ".");
	}
	public static void main(String[] args) {
		if (2 != args.length) {
			err("Usage: java IntroductionToUML2Profiles <URI> <URI>");
			System.exit(1);
		}
		registerResourceFactories();
		registerPathmaps(URI.createURI(args[1]));
		out("Creating profile...");
		Profile ecoreProfile = createProfile("ecore");
		out("Importing primitive types...");
		PrimitiveType booleanPrimitiveType = importPrimitiveType(ecoreProfile, "Boolean");
		PrimitiveType stringPrimitiveType = importPrimitiveType(ecoreProfile, "String");
		out("Creating enumerations...");
		Enumeration visibilityKindEnumeration = createEnumeration(ecoreProfile, "VisibilityKind");
		Enumeration featureKindEnumeration = createEnumeration(ecoreProfile, "FeatureKind");
		out("Creating enumeration literals...");
		createEnumerationLiteral(visibilityKindEnumeration, "Unspecified");
		createEnumerationLiteral(visibilityKindEnumeration, "None");
		createEnumerationLiteral(visibilityKindEnumeration, "ReadOnly");
		createEnumerationLiteral(visibilityKindEnumeration, "ReadWrite");
		createEnumerationLiteral(visibilityKindEnumeration, "ReadOnlyUnsettable");
		createEnumerationLiteral(visibilityKindEnumeration, "ReadWriteUnsettable");
		createEnumerationLiteral(featureKindEnumeration, "Unspecified");
		createEnumerationLiteral(featureKindEnumeration, "Simple");
		createEnumerationLiteral(featureKindEnumeration, "Attribute");
		createEnumerationLiteral(featureKindEnumeration, "Element");
		createEnumerationLiteral(featureKindEnumeration, "AttributeWildcard");
		createEnumerationLiteral(featureKindEnumeration, "ElementWildcard");
		createEnumerationLiteral(featureKindEnumeration, "Group");
		out("Creating stereotypes...");
		Stereotype eStructuralFeatureStereotype = createStereotype(ecoreProfile, "EStructuralFeature", true);
		Stereotype eAttributeStereotype = createStereotype(ecoreProfile, "EAttribute", false);
		Stereotype eReferenceStereotype = createStereotype(ecoreProfile, "EReference", false);
		out("Creating stereotype generalizations...");
		createGeneralization(eAttributeStereotype, eStructuralFeatureStereotype);
		createGeneralization(eReferenceStereotype, eStructuralFeatureStereotype);
		out("Creating stereotype properties...");
		Property isTransientProperty = createAttribute(eStructuralFeatureStereotype, "isTransient",
				booleanPrimitiveType, 0, 1);
		createAttribute(eStructuralFeatureStereotype, "isUnsettable", booleanPrimitiveType, 0, 1);
		Property isVolatileProperty = createAttribute(eStructuralFeatureStereotype, "isVolatile", booleanPrimitiveType,
				0, 1);
		createAttribute(eStructuralFeatureStereotype, "visibility", visibilityKindEnumeration, 0, 1);
		createAttribute(eStructuralFeatureStereotype, "xmlName", stringPrimitiveType, 0, 1);
		createAttribute(eStructuralFeatureStereotype, "xmlNamespace", stringPrimitiveType, 0, 1);
		createAttribute(eStructuralFeatureStereotype, "xmlFeatureKind", featureKindEnumeration, 0, 1);
		createAttribute(eAttributeStereotype, "attributeName", stringPrimitiveType, 0, 1);
		createAttribute(eAttributeStereotype, "isID", booleanPrimitiveType, 0, 1);
		createAttribute(eReferenceStereotype, "referenceName", stringPrimitiveType, 0, 1);
		Property isResolveProxiesProperty = createAttribute(eReferenceStereotype, "isResolveProxies",
				booleanPrimitiveType, 0, 1);
		isResolveProxiesProperty.setBooleanDefaultValue(true);
		out("Referencing metaclasses...");
		org.eclipse.uml2.uml.Class propertyMetaclass = referenceMetaclass(ecoreProfile, UMLPackage.Literals.PROPERTY
				.getName());
		out("Creating extensions...");
		createExtension(propertyMetaclass, eAttributeStereotype, false);
		createExtension(propertyMetaclass, eReferenceStereotype, false);
		out("Defining profile...");
		defineProfile(ecoreProfile);
		out("Saving profile...");
		save(ecoreProfile, URI.createURI(args[0]).appendSegment("Ecore").appendFileExtension(
				UMLResource.PROFILE_FILE_EXTENSION));
		out("Loading model...");
		Model epo2Model = (Model) load(URI.createURI(args[0]).appendSegment("ExtendedPO2").appendFileExtension(
				UMLResource.FILE_EXTENSION));
		out("Applying profile...");
		applyProfile(epo2Model, ecoreProfile);
		out("Applying stereotypes...");
		org.eclipse.uml2.uml.Class supplierClass = (org.eclipse.uml2.uml.Class) epo2Model.getOwnedType("Supplier");
		Property pendingOrdersProperty = supplierClass.getOwnedAttribute("pendingOrders", null);
		applyStereotype(pendingOrdersProperty, eReferenceStereotype);
		Property shippedOrdersProperty = supplierClass.getOwnedAttribute("shippedOrders", null);
		applyStereotype(shippedOrdersProperty, eReferenceStereotype);
		org.eclipse.uml2.uml.Class purchaseOrderClass = (org.eclipse.uml2.uml.Class) epo2Model
				.getOwnedType("PurchaseOrder");
		Property totalAmountProperty = purchaseOrderClass.getOwnedAttribute("totalAmount", null);
		applyStereotype(totalAmountProperty, eAttributeStereotype);
		Property previousOrderProperty = purchaseOrderClass.getOwnedAttribute("previousOrder", null);
		applyStereotype(previousOrderProperty, eReferenceStereotype);
		Property customerProperty = purchaseOrderClass.getOwnedAttribute("customer", null);
		applyStereotype(customerProperty, eReferenceStereotype);
		org.eclipse.uml2.uml.Class customerClass = (org.eclipse.uml2.uml.Class) epo2Model.getOwnedType("Customer");
		Property ordersProperty = customerClass.getOwnedAttribute("orders", null);
		applyStereotype(ordersProperty, eReferenceStereotype);
		out("Getting stereotype property values...");
		getStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isVolatileProperty);
		getStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isTransientProperty);
		getStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isVolatileProperty);
		getStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isTransientProperty);
		getStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(totalAmountProperty, eAttributeStereotype, isVolatileProperty);
		getStereotypePropertyValue(totalAmountProperty, eAttributeStereotype, isTransientProperty);
		getStereotypePropertyValue(previousOrderProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(customerProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(ordersProperty, eReferenceStereotype, isResolveProxiesProperty);
		out("Setting stereotype property values...");
		setStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isVolatileProperty, Boolean.TRUE);
		setStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isTransientProperty, Boolean.TRUE);
		setStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isResolveProxiesProperty, Boolean.FALSE);
		setStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isVolatileProperty, Boolean.TRUE);
		setStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isTransientProperty, Boolean.TRUE);
		setStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isResolveProxiesProperty, Boolean.FALSE);
		setStereotypePropertyValue(totalAmountProperty, eAttributeStereotype, isVolatileProperty, Boolean.TRUE);
		setStereotypePropertyValue(totalAmountProperty, eAttributeStereotype, isTransientProperty, Boolean.TRUE);
		setStereotypePropertyValue(previousOrderProperty, eReferenceStereotype, isResolveProxiesProperty, Boolean.FALSE);
		setStereotypePropertyValue(customerProperty, eReferenceStereotype, isResolveProxiesProperty, Boolean.FALSE);
		setStereotypePropertyValue(ordersProperty, eReferenceStereotype, isResolveProxiesProperty, Boolean.FALSE);
		out("Getting stereotype property values...");
		getStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isVolatileProperty);
		getStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isTransientProperty);
		getStereotypePropertyValue(pendingOrdersProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isVolatileProperty);
		getStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isTransientProperty);
		getStereotypePropertyValue(shippedOrdersProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(totalAmountProperty, eAttributeStereotype, isVolatileProperty);
		getStereotypePropertyValue(totalAmountProperty, eAttributeStereotype, isTransientProperty);
		getStereotypePropertyValue(previousOrderProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(customerProperty, eReferenceStereotype, isResolveProxiesProperty);
		getStereotypePropertyValue(ordersProperty, eReferenceStereotype, isResolveProxiesProperty);
		out("Saving model...");
		save(epo2Model, URI.createURI(args[0]).appendSegment("ExtendedPO2_Ecore").appendFileExtension(
				UMLResource.FILE_EXTENSION));
	}
	public static Enumeration createEnumeration(org.eclipse.uml2.uml.Package package_, String name) {
		Enumeration enumeration = (Enumeration) package_.createOwnedEnumeration(name);
		out("Enumeration '" + enumeration.getQualifiedName() + "' created.");
		return enumeration;
	}
	public static EnumerationLiteral createEnumerationLiteral(Enumeration enumeration, String name) {
		EnumerationLiteral enumerationLiteral = enumeration.createOwnedLiteral(name);
		out("Enumeration literal '" + enumerationLiteral.getQualifiedName() + "' created.");
		return enumerationLiteral;
	}
}