package org.nakeduml.tinker.linkage;

import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.oclengine.IOclContext;

import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.INakedValueSpecification;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.linkage.EnumerationValuesAttributeAdder;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.linkage.MappedTypeLinker;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.linkage.PinLinker;
import org.opaeum.linkage.ProcessIdentifier;
import org.opaeum.linkage.ReferenceResolver;
import org.opaeum.linkage.SourcePopulationResolver;
import org.opaeum.linkage.TypeResolver;
import org.opaeum.validation.namegeneration.UmlNameRegenerator;

@StepDependency(phase = LinkagePhase.class, after = { EnumerationValuesAttributeAdder.class, PinLinker.class, MappedTypeLinker.class, SourcePopulationResolver.class,
		ReferenceResolver.class, TypeResolver.class, ProcessIdentifier.class }, requires = { MappedTypeLinker.class, PinLinker.class, ReferenceResolver.class, TypeResolver.class,
		UmlNameRegenerator.class, EnumerationValuesAttributeAdder.class }, replaces = NakedParsedOclStringResolver.class)
public class TinkerNakedParsedOclStringResolver extends NakedParsedOclStringResolver {

	@VisitBefore(matchSubclasses = true)
	public void visitProperty(INakedProperty attr){
		INakedClassifier c;
		if (attr.getOwnerElement() instanceof INakedProperty) {
			c = (INakedClassifier) ((INakedProperty)attr.getOwnerElement()).getBaseType();
		} else {
			c = attr.getOwner();
		}
		INakedValueSpecification iv = attr.getInitialValue();
		if(iv != null && iv.getValue() instanceof ParsedOclString){
			ParsedOclString oclValue = (ParsedOclString) iv.getOclValue();
			oclValue.setContext(c, iv);
			Environment env = environmentFactory.createClassifierEnvironment(c);
			iv.setValue(replaceSingleParsedOclString(oclValue, c, attr.getType(), env));
			IOclContext oc = iv.getOclValue();
			if(iv.isValidOclValue()){
				if(oc.getExpression().getExpressionType().isCollectionKind()){
					if(attr.getMultiplicity().isSingleObject() && attr.getQualifiers().isEmpty()){
						getErrorMap().putError(iv, CoreValidationRule.OCL, "Expression returns multiple values, but the defining property only supports a single value");
					}
				}else{
					if(attr.getMultiplicity().getUpper() > 1 || !attr.getQualifiers().isEmpty()){
						getErrorMap().putError(iv, CoreValidationRule.OCL, "Expression returns a single value, but the defining property expects multiple values");
					}
				}
			}
			if(iv.isValidOclValue() && attr.isDerived()){
				overridePinType(attr, iv.getOclValue().getExpression().getExpressionType());
			}
		}
	}
	
}
