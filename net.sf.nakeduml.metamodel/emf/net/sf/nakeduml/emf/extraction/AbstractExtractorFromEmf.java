package net.sf.nakeduml.emf.extraction;

import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackageableElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.PreAndPostConstrained;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedTypedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;

public abstract class AbstractExtractorFromEmf extends EmfElementVisitor implements TransformationStep {
	protected INakedModelWorkspace workspace;

	@Override
	protected Object resolvePeer(Element o, Class peerClass) {
		Element e = o;
		INakedElement ne = getNakedPeer(e);
		if (ne == null) {
			try {
				ne = (INakedElement) peerClass.newInstance();
			} catch (IllegalAccessException e1) {
				throw new RuntimeException(e1);
			} catch (InstantiationException e1) {
				throw new RuntimeException(e1);
			}
			initialize(ne, e, e.getOwner());
		}
		return ne;
	}

	// /Visiting logic ends---//
	protected void initialize(INakedElement wrapper, Element modelElement, Element owner) {
		if (wrapper.getOwnerElement() == null && owner != null && getId(owner) != null) {
			wrapper.setOwnerElement(getNakedPeer(owner));
		}
		if (wrapper.getOwnerElement() == null && owner != null && !(modelElement instanceof Behavior)) {
			// BEhaviors sometimes fall outside of a namespace in the
			// containment hierarchy - transitions, edges ,states
			System.out.println("ownerElement not found:" + owner);
		}
		if (wrapper instanceof INakedPackageableElement) {
			INakedPackageableElement pe = (INakedPackageableElement) wrapper;
			pe.setVisibility(resolveVisibility(modelElement));
		}
		String name = null;
		if (modelElement instanceof NamedElement) {
			name = ((NamedElement) modelElement).getName();
		}
		if (wrapper.getId() == null || wrapper.getName() == null) {
			// allow callers to do own initilization
			wrapper.initialize(getId(modelElement), name);
		}
		getErrorMap().linkElement(wrapper, modelElement);
		this.workspace.putModelElement(wrapper);
		if (modelElement.getOwnedComments().size() > 0) {
			Comment comment = modelElement.getOwnedComments().iterator().next();
			wrapper.setDocumentation(comment.getBody());
		}
		if (wrapper.getOwnerElement() != null) {
			wrapper.getOwnerElement().addOwnedElement(wrapper);
		}
	}

	private VisibilityKind resolveVisibility(Element modelElement) {
		VisibilityKind octopusKind = VisibilityKind.PUBLIC;
		if (modelElement instanceof PackageableElement) {
			PackageableElement epe = (PackageableElement) modelElement;
			if (epe.getVisibility() == org.eclipse.uml2.uml.VisibilityKind.PROTECTED_LITERAL) {
				octopusKind = VisibilityKind.PROTECTED;
			} else if (epe.getVisibility() == org.eclipse.uml2.uml.VisibilityKind.PRIVATE_LITERAL) {
				octopusKind = VisibilityKind.PRIVATE;
			} else if (epe.getVisibility() == org.eclipse.uml2.uml.VisibilityKind.PACKAGE_LITERAL) {
				octopusKind = VisibilityKind.NONE;
			}
		}
		return octopusKind;
	}

	protected String getId(EObject e) {
		return EcoreUtil.getURI(e).toString();
	}

	protected INakedElement getNakedPeer(Element e) {
		if (e == null || e instanceof EmfWorkspace) {
			return null;
		} else {
			return this.workspace.getModelElement(getId(e));
		}
	}

	/**
	 * Returns a ValueSpecification for use InstanceSpecfications, pins and
	 * guards on activity edges and state transitions
	 * 
	 */
	protected INakedValueSpecification getValueSpecification(INakedClassifier context, ValueSpecification value, OclUsageType usage) {
		NakedValueSpecificationImpl nakedValueSpecification = (NakedValueSpecificationImpl) getNakedPeer(value);
		if (nakedValueSpecification == null) {
			if (value instanceof OpaqueExpression) {
				ParsedOclString bodyExpression = parseOclStringFromValue(((OpaqueExpression) value), value.getName(), usage);
				if (bodyExpression != null) {
					nakedValueSpecification = new NakedValueSpecificationImpl();
					bodyExpression.setContext(context, context);
					nakedValueSpecification.setValue(bodyExpression);
					initialize(nakedValueSpecification, value, value.getOwner());
					if (value.getType() != null) {
						// TODO - type may not be available yet
						nakedValueSpecification.setType((INakedClassifier) getNakedPeer(value.getType()));
					}
				}
			} else if (value instanceof TimeExpression) {
				ParsedOclString bodyExpression = parseOclStringFromValue(((TimeExpression) value), value.getName(), usage);
				if (bodyExpression != null) {
					nakedValueSpecification = new NakedValueSpecificationImpl();
					bodyExpression.setContext(context, context);
					nakedValueSpecification.setValue(bodyExpression);
					initialize(nakedValueSpecification, value, value.getOwner());
					if (value.getType() != null) {
						// TODO - type may not be available yet
						nakedValueSpecification.setType((INakedClassifier) getNakedPeer(value.getType()));
					}
				}
			} else {
				if (value instanceof LiteralString) {
					nakedValueSpecification = buildStringLiteral(value);
				} else if (value instanceof LiteralBoolean) {
					nakedValueSpecification = buildBooleanLiteral(value);
				} else if (value instanceof LiteralInteger) {
					nakedValueSpecification = buildIntegerLiteral(value);
				} else if (value instanceof LiteralUnlimitedNatural) {
					nakedValueSpecification = buildUnlimitiedLiteral(value);
				} else if (value instanceof InstanceValue) {
					nakedValueSpecification = buildInstanceValue(context, value, usage);
				}
			}
		}
		return nakedValueSpecification;
	}

	private NakedValueSpecificationImpl buildInstanceValue(INakedClassifier context, ValueSpecification value, OclUsageType usage) {
		NakedValueSpecificationImpl nakedValueSpecification = null;
		InstanceValue instanceValue = (InstanceValue) value;
		if (instanceValue.getInstance() != null) {
			if (instanceValue.getInstance().getSpecification() != null) {
				// simply resolve this instancespecification as
				// another
				// value
				nakedValueSpecification = (NakedValueSpecificationImpl) getValueSpecification(context, instanceValue.getInstance()
						.getSpecification(), usage);
			} else {
				nakedValueSpecification = new NakedValueSpecificationImpl();
				// Most likely an enum literal
				nakedValueSpecification.setValueId(getId(instanceValue.getInstance()));
				initialize(nakedValueSpecification, value, value.getOwner());
				// Take note that INakedInstanceSpecifications
				// SHOULD be
				// created by the InstanceBuilder
				// Slot will be created in the InstanceBuilder
			}
		}
		return nakedValueSpecification;
	}

	private NakedValueSpecificationImpl buildUnlimitiedLiteral(ValueSpecification value) {
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		LiteralUnlimitedNatural lun = (LiteralUnlimitedNatural) value;
		nakedValueSpecification.setValue(new Integer(lun.unlimitedValue()));
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}

	private NakedValueSpecificationImpl buildIntegerLiteral(ValueSpecification value) {
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		nakedValueSpecification.setValue(new Integer(((LiteralInteger) value).integerValue()));
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}

	private NakedValueSpecificationImpl buildBooleanLiteral(ValueSpecification value) {
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		nakedValueSpecification.setValue(new Boolean(((LiteralBoolean) value).booleanValue()));
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}

	private NakedValueSpecificationImpl buildStringLiteral(ValueSpecification value) {
		NakedValueSpecificationImpl nakedValueSpecification = new NakedValueSpecificationImpl();
		nakedValueSpecification.setValue(((LiteralString) value).stringValue());
		nakedValueSpecification.setType(getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName));
		initialize(nakedValueSpecification, value, value.getOwner());
		return nakedValueSpecification;
	}

	protected ParsedOclString parseOclStringFromValue(TimeExpression te, String name, OclUsageType usageType) {
		if (te.getExpr() != null) {
			String body = te.getExpr().stringValue();
			if (body != null && body.trim().length() > 0) {
				ParsedOclString string = new ParsedOclString(name == null ? body : name, usageType);
				string.setExpressionString(body);
				this.getErrorMap().linkElement(string, te);
				return string;
			}
		}
		return null;
	}

	protected ParsedOclString parseOclStringFromValue(OpaqueExpression oe, String name, OclUsageType usageType) {
		String body = null;
		for (int i = 0; i < oe.getLanguages().size(); i++) {
			String language = oe.getLanguages().get(i);
			if (language.equals("OCL") && oe.getBodies().size() > i) {
				body = oe.getBodies().get(i);
				break;
			}
		}
		if (body == null && oe.getBodies().size() == 1) {
			// Need something here
			body = oe.getBodies().get(0);
		}
		if (body != null && body.trim().length() > 0) {
			ParsedOclString string = new ParsedOclString(name == null ? body : name, usageType);
			string.setExpressionString(body);
			this.getErrorMap().linkElement(string, oe);
			return string;
		}
		return null;
	}

	protected IOclLibrary getOclLibrary() {
		return this.workspace.getOclEngine().getOclLibrary();
	}

	protected void addConstraints(PreAndPostConstrained nakedOper, List preconditions, List postconditions) {
		Iterator iter = preconditions.iterator();
		while (iter.hasNext()) {
			Constraint c = (Constraint) iter.next();
			ParsedOclString string = buildConstraint(nakedOper, OclUsageType.PRE, c, nakedOper.getContext());
			if (string != null) {
				nakedOper.addPreCondition(string);
			}
		}
		Iterator post = postconditions.iterator();
		while (post.hasNext()) {
			Constraint c = (Constraint) post.next();
			ParsedOclString string = buildConstraint(nakedOper, OclUsageType.POST, c, nakedOper.getContext());
			if (string != null) {
				nakedOper.addPostCondition(string);
			}
		}
	}

	protected ParsedOclString buildConstraint(IModelElement wrapper, OclUsageType oclUsageType, Constraint c, INakedClassifier context) {
		ValueSpecification defaultValue = c.getSpecification();
		String name = c.getName();
		ParsedOclString result = null;
		if (c.getSpecification() instanceof LiteralBoolean) {
			LiteralBoolean literal = (LiteralBoolean) c.getSpecification();
			String booleanValue = String.valueOf(literal.booleanValue());
			ParsedOclString string = new ParsedOclString(name == null ? booleanValue : name, oclUsageType);
			string.setExpressionString(booleanValue);
			this.getErrorMap().linkElement(string, c);
			result = string;
		} else if (defaultValue instanceof OpaqueExpression) {
			result = parseOclStringFromValue((OpaqueExpression) defaultValue, name, oclUsageType);
		} else {
			// VALIDATION put an error here - valuespecification has to be an
			// ocl string or an opaque expression
			return null;
		}
		result.setContext(context, wrapper);
		return result;
	}

	protected ErrorMap getErrorMap() {
		return workspace.getErrorMap();
	}

	public void initialize(INakedModelWorkspace workspace2) {
		this.workspace = workspace2;
	}

	private NakedMultiplicityImpl toNakedMultiplicity(MultiplicityElement te) {
		int lower = te.getLower();
		int upper = te.getUpper();
		if (upper == -1) {
			upper = Integer.MAX_VALUE;
		}
		return new NakedMultiplicityImpl(lower, upper);
	}

	protected void populateMultiplicityAndBaseType(MultiplicityElement emfNode, Type type, NakedTypedElementImpl ae) {
		ae.setMultiplicity(toNakedMultiplicity(emfNode));
		ae.setIsOrdered(emfNode.isOrdered());
		ae.setIsUnique(emfNode.isUnique());
		ae.setBaseType((INakedClassifier) getNakedPeer(type));
	}
}