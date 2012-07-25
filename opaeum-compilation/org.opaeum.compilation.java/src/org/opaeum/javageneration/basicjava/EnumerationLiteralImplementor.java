package org.opaeum.javageneration.basicjava;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
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
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class EnumerationLiteralImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void generateExtraConstructor(Enumeration c){
		if(EmfClassifierUtil.getCodeGenerationStrategy( c)!=CodeGenerationStrategy.NO_CODE){
			OJEnum myClass = (OJEnum) findJavaClass(c);
			Property valuesAttr = c.getAttribute("values",null);
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap((Property) valuesAttr);
			OJOperation values = myClass.getUniqueOperation("getValues");
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
			List<? extends Property> allAttributes = EmfElementFinder.getPropertiesInScope(c);
			boolean hasDuplicates = hasDuplicates(allAttributes);
			if(!hasDuplicates){
				for(Property attr:allAttributes){
					if(!(attr.isDerived())){
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
					OJUtil.addParameter(myClass.findLiteral(el.getName().toUpperCase()), "uuid", "\"" + EmfWorkspace.getId(nl) + "\"," +  EmfWorkspace.getOpaeumId(nl) + "l");
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
		List<? extends Property> allAttributes = EmfElementFinder.getPropertiesInScope(c);
		for(Property prop:allAttributes){
			if(prop.getType().getName().equals("String") && prop.getUpper()==1 && !prop.isDerived()){
				// TODO support for other types??
				OJAnnotatedOperation staticOp = new OJAnnotatedOperation("from" + NameConverter.capitalize(prop.getName()));
				staticOp.setStatic(true);
				OJPathName path = OJUtil.classifierPathname(c);
				staticOp.setReturnType(path);
				OJParameter ojParameter = new OJParameter();
				ojParameter.setName(prop.getName());
				ojParameter.setType(OJUtil.classifierPathname((Classifier) prop.getType()));
				staticOp.addToParameters(ojParameter);
				List<EnumerationLiteral> literals = c.getOwnedLiterals();
				for(EnumerationLiteral EnumerationLiteral:literals){
					OJIfStatement ifSPS = new OJIfStatement();
					EnumerationLiteral nakedLiteral = (EnumerationLiteral) EnumerationLiteral;
					List<Slot> slots = nakedLiteral.getSlots();
					for(Slot nakedSlot:slots){
						if(nakedSlot.getDefiningFeature().equals(prop)){
							ifSPS.setCondition(prop.getName() + ".equals("
									+ ValueSpecificationUtil.expressValue(myClass, nakedSlot.getFirstValue(), getLibrary().getActualType( prop), true) + ")");
							ifSPS.addToThenPart("return " + EnumerationLiteral.getName().toUpperCase());
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
		NakedStructuralFeatureMap mapper = OJUtil.buildStructuralFeatureMap(feat);
		OJPathName type = mapper.javaTypePath();
		myClass.addToImports(type);
		String parname = feat.getName();
		constr.addParam(parname, type);
		String setter = mapper.setter();
		constr.getBody().addToStatements("this." + setter + "(" + parname + ")");
		OJEnum oje = (OJEnum) myClass;
		for(EnumerationLiteral l:c.getOwnedLiterals()){
			OJEnumLiteral ojl = oje.findLiteral(l.getName().toUpperCase());
			OJField f = ojl.findAttributeValue(mapper.fieldname());
			EnumerationLiteral nakedLiteral = (EnumerationLiteral) l;
			final Slot slot = nakedLiteral.getSlotForFeature(feat.getName());
			if(slot != null){
				f.setInitExp(valueSpecificationUtil.expressSlot(myClass, slot));
			}
		}
	}
}
