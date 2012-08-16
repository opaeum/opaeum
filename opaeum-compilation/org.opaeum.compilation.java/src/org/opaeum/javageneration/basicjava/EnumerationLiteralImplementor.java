package org.opaeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class EnumerationLiteralImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void generateExtraConstructor(Enumeration c){
		if(ojUtil.getCodeGenerationStrategy(c) != CodeGenerationStrategy.NO_CODE){
			OJEnum myClass = (OJEnum) findJavaClass(c);
			ClassifierMap map = ojUtil.buildClassifierMap(c, CollectionKind.SET_LITERAL);
			OJOperation values = myClass.getUniqueOperation("getValues");
			if(values == null){
				values = new OJAnnotatedOperation("getValues", map.javaTypePath());
				myClass.addToOperations(values);
			}
			values.setStatic(true);
			values.setReturnType(map.javaTypePath());
			values.getBody().removeAllFromStatements();
			myClass.addToImports("java.util.HashSet");
			values.getBody().addToStatements("return new HashSet<" + c.getName() + ">(java.util.Arrays.asList(values()))");
			OJConstructor constr = new OJConstructor();
			myClass.addToConstructors(constr);
			constr.setVisibility(OJVisibilityKindGEN.PRIVATE);
			List<? extends Property> allAttributes = getLibrary().getEffectiveAttributes(c);
			boolean hasDuplicates = hasDuplicates(allAttributes);
			if(!hasDuplicates){
				for(Property attr:allAttributes){
					if(!(EmfPropertyUtil.isDerived( attr))){
						addToConstructor(constr, myClass, attr, c);
					}
				}
				OJUtil.addField(myClass, constr, "uuid", new OJPathName("String"));
				OJAnnotatedOperation getUid = new OJAnnotatedOperation("getUid", new OJPathName("String"));
				getUid.initializeResultVariable("getUuid()");
				myClass.addToOperations(getUid);
				OJUtil.addField(myClass, constr, "opaeumId", new OJPathName("long"));
				for(EnumerationLiteral el:c.getOwnedLiterals()){
					EnumerationLiteral nl = (EnumerationLiteral) el;
					OJUtil.addParameter(myClass.findLiteral(OJUtil.toJavaLiteral(nl)), "uuid",
							"\"" + EmfWorkspace.getId(nl) + "\"," + EmfWorkspace.getOpaeumId(nl) + "l");
				}
				if(!constr.getParameters().isEmpty()){
					myClass.addToConstructors(constr);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void generateStaticMethods(Enumeration c){
		OJEnum myClass = (OJEnum) findJavaClass(c);
		// Does lookups on arbitrary string properties
		List<? extends Property> allAttributes = getLibrary().getEffectiveAttributes(c);
		for(Property prop:allAttributes){
			if(prop.getType().getName().equals("String") && prop.getUpper() == 1 && !EmfPropertyUtil.isDerived( prop)){
				// TODO support for other types??
				OJAnnotatedOperation staticOp = new OJAnnotatedOperation("from" + NameConverter.capitalize(prop.getName()));
				staticOp.setStatic(true);
				OJPathName path = ojUtil.classifierPathname(c);
				staticOp.setReturnType(path);
				OJParameter ojParameter = new OJParameter(prop.getName(),ojUtil.classifierPathname((Classifier) prop.getType()));
				staticOp.addToParameters(ojParameter);
				List<EnumerationLiteral> literals = c.getOwnedLiterals();
				for(EnumerationLiteral el:literals){
					OJIfStatement ifSPS = new OJIfStatement();
					List<Slot> slots = el.getSlots();
					for(Slot nakedSlot:slots){
						if(nakedSlot.getDefiningFeature().equals(prop) && nakedSlot.getValues().size() == 1){
							ifSPS.setCondition(prop.getName() + ".equals("
									+ valueSpecificationUtil.expressValue(myClass, nakedSlot.getValues().get(0), getLibrary().getActualType(prop), true)
									+ ")");
							ifSPS.addToThenPart("return " + OJUtil.toJavaLiteral(el));
							break;
						}
					}
					staticOp.getBody().addToStatements(ifSPS);
				}
				staticOp.getBody().addToStatements("return null");
				myClass.addToOperations(staticOp);
			}
		}
	}
	private boolean hasDuplicates(List<? extends Property> allAttributes){
		boolean result = false;
		List<String> names = new ArrayList<String>();
		for(Property attr:allAttributes){
			if(names.contains(attr.getName())){
				return true;
			}else{
				names.add(attr.getName());
			}
		}
		return result;
	}
	private void addToConstructor(OJConstructor constr,OJClass myClass,Property feat,Enumeration c){
		PropertyMap mapper = ojUtil.buildStructuralFeatureMap(feat);
		OJPathName type = mapper.javaTypePath();
		myClass.addToImports(type);
		String parname = feat.getName();
		constr.addParam(parname, type);
		String setter = mapper.setter();
		constr.getBody().addToStatements("this." + setter + "(" + parname + ")");
		OJEnum oje = (OJEnum) myClass;
		for(EnumerationLiteral l:c.getOwnedLiterals()){
			OJEnumLiteral ojl = oje.findLiteral(OJUtil.toJavaLiteral(l));
			OJField f = ojl.findAttributeValue(mapper.fieldname());
			EnumerationLiteral nakedLiteral = (EnumerationLiteral) l;
			final Slot slot = EmfValueSpecificationUtil.getSlotForFeature(nakedLiteral, feat);
			if(slot != null){
				f.setInitExp(valueSpecificationUtil.expressSlot(myClass, slot));
			}
		}
	}
}
