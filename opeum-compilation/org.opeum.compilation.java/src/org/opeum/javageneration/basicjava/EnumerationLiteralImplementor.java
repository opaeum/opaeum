package org.opeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IEnumLiteral;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJParameter;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJEnum;
import org.opeum.java.metamodel.annotation.OJEnumLiteral;
import org.opeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedEnumerationLiteral;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSlot;
import org.opeum.metamodel.core.internal.NakedEnumerationLiteralImpl;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class EnumerationLiteralImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void generateExtraConstructor(INakedEnumeration c){
		if(!(c.getCodeGenerationStrategy().isNone())){
			OJEnum myClass = (OJEnum) findJavaClass(c);
			IAttribute valuesAttr = c.findClassAttribute("values");
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap((INakedProperty) valuesAttr);
			OJOperation values = OJUtil.findOperation(myClass, "getValues");
			// TODO find out why the getter is not generated
			if(values == null){
				values = new OJAnnotatedOperation("getValues", map.javaTypePath());
				myClass.addToOperations(values);
			}
			values.setStatic(true);
			OJPathName results = new OJPathName("java.util.Set");
			results.addToElementTypes(OJUtil.classifierPathname(c));
			values.setReturnType(results);
			values.getBody().removeAllFromStatements();
			myClass.addToImports("java.util.HashSet");
			values.getBody().addToStatements("return new HashSet<" + c.getName() + ">(java.util.Arrays.asList(values()))");
			OJConstructor constr = new OJConstructor();
			myClass.addToConstructors(constr);
			constr.setVisibility(OJVisibilityKindGEN.PRIVATE);
			List<? extends INakedProperty> allAttributes = c.getEffectiveAttributes();
			boolean hasDuplicates = hasDuplicates(allAttributes);
			if(!hasDuplicates){
				for(INakedProperty attr:allAttributes){
					if(!(attr.isDerived() || attr.isOclDef())){
						addToConstructor(constr, myClass, attr, c);
					}
				}
				OJUtil.addField(myClass, constr, "uuid", new OJPathName("String"));
				for(IEnumLiteral el:c.getLiterals()){
					INakedEnumerationLiteral nl = (INakedEnumerationLiteral) el;
					OJUtil.addParameter(myClass.findLiteral(el.getName().toUpperCase()), "uuid", "\"" + nl.getMappingInfo().getIdInModel() + "\"");
				}
				if(!constr.getParameters().isEmpty()){
					myClass.addToConstructors(constr);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void generateStaticMethods(INakedEnumeration c){
		OJEnum myClass = (OJEnum) findJavaClass(c);
		// Does lookups on arbitrary string properties
		List<? extends INakedProperty> allAttributes = c.getEffectiveAttributes();
		for(INakedProperty prop:allAttributes){
			if(prop.getType().getName().equals("String") && prop.getNakedMultiplicity().isOne() && !prop.isDerived()){
				// TODO support for other types??
				OJAnnotatedOperation staticOp = new OJAnnotatedOperation("from" + prop.getMappingInfo().getJavaName().getCapped());
				staticOp.setStatic(true);
				OJPathName path = OJUtil.classifierPathname(c);
				staticOp.setReturnType(path);
				OJParameter ojParameter = new OJParameter();
				ojParameter.setName(prop.getName());
				ojParameter.setType(OJUtil.classifierPathname(prop.getNakedBaseType()));
				staticOp.addToParameters(ojParameter);
				List<IEnumLiteral> literals = c.getLiterals();
				for(IEnumLiteral iEnumLiteral:literals){
					OJIfStatement ifSPS = new OJIfStatement();
					NakedEnumerationLiteralImpl nakedLiteral = (NakedEnumerationLiteralImpl) iEnumLiteral;
					List<INakedSlot> slots = nakedLiteral.getSlots();
					for(INakedSlot nakedSlot:slots){
						if(nakedSlot.getDefiningFeature().equals(prop)){
							ifSPS.setCondition(prop.getName() + ".equals("
									+ ValueSpecificationUtil.expressValue(myClass, nakedSlot.getFirstValue(), prop.getType(), true) + ")");
							ifSPS.addToThenPart("return " + iEnumLiteral.getName().toUpperCase());
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
	private boolean hasDuplicates(List<? extends INakedProperty> allAttributes){
		boolean result = false;
		List<String> names = new ArrayList<String>();
		for(INakedProperty attr:allAttributes){
			if(names.contains(attr.getName())){
				return true;
			}else{
				names.add(attr.getName());
			}
		}
		return result;
	}
	private void addToConstructor(OJConstructor constr,OJClass myClass,INakedProperty feat,INakedEnumeration c){
		NakedStructuralFeatureMap mapper = OJUtil.buildStructuralFeatureMap(feat);
		OJPathName type = mapper.javaTypePath();
		myClass.addToImports(type);
		String parname = feat.getName();
		constr.addParam(parname, type);
		String setter = mapper.setter();
		constr.getBody().addToStatements("this." + setter + "(" + parname + ")");
		OJEnum oje = (OJEnum) myClass;
		for(IEnumLiteral l:c.getLiterals()){
			OJEnumLiteral ojl = oje.findLiteral(l.getName().toUpperCase());
			OJField f = ojl.findAttributeValue(mapper.umlName());
			INakedEnumerationLiteral nakedLiteral = (INakedEnumerationLiteral) l;
			final INakedSlot slot = nakedLiteral.getSlotForFeature(feat.getName());
			if(slot != null){
				f.setInitExp(ValueSpecificationUtil.expressSlot(myClass, slot));
			}
		}
	}
}
