package org.opaeum.validation.namegeneration;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.name.SingularNameWrapper;

@StepDependency(phase = NameGenerationPhase.class, requires = { UmlNameRegenerator.class }, after = { UmlNameRegenerator.class })
public class HumanNameGenerator extends AbstractNameGenerator {
	private static final String HUMAN_NAME = "humanName";
	private static final String PLURAL = "plural";

	@VisitBefore(matchSubclasses = true)
	public void updateHumanName(INakedElement ne) {
		ne.getMappingInfo().setHumanName(generateHumanName(ne));
	}

	private NameWrapper generateHumanName(INakedElement mew) {
		NameWrapper generatedName;
		INakedValueSpecification humanName = getTaggedValue(mew, HUMAN_NAME);
		if (mew instanceof INakedClassifier) {
			INakedClassifier nc = (INakedClassifier) mew;
			generatedName = generateHumanNameForClassifier(nc);
		} else if (mew instanceof INakedMultiplicityElement) {
			INakedTypedElement tew = (INakedTypedElement) mew;
			generatedName = generateNameForTypedElement(tew);
		} else if (humanName != null) {
			// generate plural
			generatedName = new SingularNameWrapper(humanName.stringValue(), null);
		} else {
			if (mew.getName() == null || mew.getName().length() == 0) {
				generatedName = new SingularNameWrapper(mew.getId(), null).getSeparateWords();
			} else {
				generatedName = new SingularNameWrapper(mew.getName(), null).getSeparateWords();
			}
		}
		return generatedName;
	}

	private NameWrapper generateNameForTypedElement(INakedTypedElement tew) {
		NameWrapper generatedName;
		NameWrapper typeName = generateHumanNameForClassifier(tew.getNakedBaseType());
		String nameInModel = tew.getName();
		boolean noName = nameInModel == null || nameInModel.length() == 0 || nameInModel.equalsIgnoreCase(tew.getNakedBaseType().getName());
		NameWrapper separateWords = new SingularNameWrapper(nameInModel, null).getCapped().getSeparateWords();
		INakedValueSpecification humanName = getTaggedValue(tew, HUMAN_NAME);
		INakedValueSpecification plural = getTaggedValue(tew, PLURAL);
		if (humanName != null) {
			if (plural != null) {
				// Everything supplied, nothing to be generated
				generatedName = new SingularNameWrapper(humanName.stringValue(), plural.stringValue());
			} else {
				if (noName) {
					// Neither name nor plural supplied - generate plural from
					// type
					generatedName = new SingularNameWrapper(humanName.stringValue(), typeName.getPlural().toString());
				} else {
					// Name supplied but plural not supplied - generate plural
					// from name
					generatedName = new SingularNameWrapper(humanName.stringValue(), separateWords.getPlural().toString());
				}
			}
		} else {
			// No human name available
			if (plural != null) {
				if (noName) {
					// No name supplied - Get singular name from type
					generatedName = new SingularNameWrapper(typeName.toString(), plural.stringValue());
				} else {
					generatedName = new SingularNameWrapper(nameInModel, plural.stringValue());
				}
			} else {
				// No plural or singular supplied
				if (noName) {
					// No name supplied - generate singular and plural
					// from type
					generatedName = typeName;
				} else {
					generatedName = separateWords;
				}
			}
		}
		return generatedName;
	}

	private NameWrapper generateHumanNameForClassifier(INakedClassifier classifier) {
		NameWrapper generatedName;
		INakedValueSpecification plural = getTaggedValue((INakedElement) classifier, PLURAL);
		INakedValueSpecification humanName = getTaggedValue((INakedElement) classifier, HUMAN_NAME);
		if (humanName != null) {
			if (plural != null) {
				// use both taggedValues
				generatedName = new SingularNameWrapper(humanName.stringValue(), plural.stringValue());
			} else {
				// generate plural
				generatedName = new SingularNameWrapper(humanName.stringValue(), new SingularNameWrapper(classifier.getName(), null).getPlural()
						.getCapped().getSeparateWords().toString());
			}
		} else {
			if (plural != null) {
				// generate singular
				generatedName = new SingularNameWrapper(new SingularNameWrapper(classifier.getName(), null).getCapped().getSeparateWords()
						.toString(), plural.stringValue());
			} else {
				// generate singular and plural
				generatedName = new SingularNameWrapper(classifier.getName(), null).getSeparateWords();
			}
		}
		return generatedName;
	}

	@Override
	protected boolean hasName(INakedElement p){
		return p.getMappingInfo().getHumanName()!=null;
	}
}
