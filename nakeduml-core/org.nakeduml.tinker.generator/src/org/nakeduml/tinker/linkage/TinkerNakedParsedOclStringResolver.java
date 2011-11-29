package org.nakeduml.tinker.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.linkage.EnumerationValuesAttributeAdder;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.linkage.PinLinker;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.linkage.ReferenceResolver;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.linkage.TypeResolver;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.validation.CoreValidationRule;
import net.sf.nakeduml.validation.namegeneration.UmlNameRegenerator;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.oclengine.IOclContext;

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
