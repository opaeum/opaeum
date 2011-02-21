package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityPartition;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedTimeEventImpl;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IModelElement;

public abstract class AbstractJavaNameGenerator extends AbstractNameGenerator {
	protected final NameWrapper generateJavaName(INakedElement element) {
		String name = element.getName();
		if (element instanceof INakedClassifier) {
			INakedClassifier nc = (INakedClassifier) element;
			if (nc.getCodeGenerationStrategy().isNone()) {
				// Use the name of the mapped class in java
				name = generateQualifiedJavaName(nc);
				if (name.indexOf(".") > -1) {
					name = name.substring(name.lastIndexOf(".") + 1);
				}
			}
		} else if (element instanceof INakedPackage) {
			name = element.getName();
			INakedPackage np = (INakedPackage) element;
			if (np.getCodeGenerationStrategy().isNone()) {
				name = generateQualifiedJavaName(np);
				if (name.indexOf(".") > -1) {
					name = name.substring(name.lastIndexOf(".") + 1);
				}
			}
		} else if (element instanceof INakedEnumerationLiteral) {
			INakedEnumerationLiteral nakedLiteral = ((INakedEnumerationLiteral) element);
			// Octopus does this too
			name = nakedLiteral.getName().toUpperCase();
		} else if (element instanceof INakedActivityPartition) {
			if (element.getName() == null || element.getName().length() == 0) {
				INakedActivityPartition p = (INakedActivityPartition) element;
				if (p.getRepresents() == null) {
					name = "RepresentsNothing" + element.getMappingInfo().getNakedUmlId();
				} else {
					name = generateJavaName(p.getRepresents()).toString();
				}
			}
		} else if (element instanceof INakedActivityEdge) {
			if (element.getName() == null || element.getName().length() == 0) {
				INakedActivityEdge e = (INakedActivityEdge) element;
				name = "to" + generateJavaName(e.getTarget());
			}
		} else if (element instanceof NakedTimeEventImpl) {
			if (element.getName() == null || element.getName().length() == 0) {
				name = "Timer" + element.getMappingInfo().getNakedUmlId();
			}
		} else if (element instanceof INakedControlNode) {
			if (element.getName() == null || element.getName().length() == 0) {
				INakedControlNode node = (INakedControlNode) element;
				name = node.getControlNodeType().name() + node.getMappingInfo().getNakedUmlId();
			}
		}
		return new SingularNameWrapper(name, null);
	}

	protected final String generateQualifiedJavaName(IModelElement me) {
		String generatedName = null;
		if (me instanceof INakedPackage) {
			INakedPackage nakedPackage = ((INakedPackage) me);
			if (nakedPackage.getMappedImplementationPackage() != null) {
				generatedName = nakedPackage.getMappedImplementationPackage();
			} else {
				if (nakedPackage.isRootPackage() || nakedPackage.getParent() == null) {
					generatedName = me.getName();
				} else {
					generatedName = generateQualifiedJavaName(nakedPackage.getParent()).toLowerCase() + "." + me.getName();
				}
			}
		} else if (me instanceof INakedAssociation) {
			generatedName = pathname(me.getPathName()).toJavaString();
		} else if (me instanceof INakedClassifier) {
			INakedClassifier nakedClassifier = (INakedClassifier) me;
			if (nakedClassifier.getCodeGenerationStrategy().isNone()) {
				generatedName = nakedClassifier.getMappedImplementationType();
			}
			if (generatedName == null) {
				String generatedQualifiedJavaName = generateQualifiedJavaName(nakedClassifier.getNameSpace());
				// Always keep packages in lowercase
				generatedName = generatedQualifiedJavaName.toLowerCase() + "." + me.getName();
				nakedClassifier.setMappedImplementationType(generatedName);
			}
		} else if (me instanceof INakedOperation) {
			INakedOperation oper = (INakedOperation) me;
			// TODO support for mapping of Responsibilities
			// generatedName = type.getMappedImplementationType();
			String generatedQualifiedJavaName = generateQualifiedJavaName(oper.getOwner());
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName.toLowerCase() + "." + me.getName();
		} else if (me instanceof INakedOpaqueAction) {
			INakedOpaqueAction action = (INakedOpaqueAction) me;
			// TODO support for mapping of Responsibilities, OpaqueActions or
			// OpaqueBehavior
			// generatedName = type.getMappedImplementationType();
			String generatedQualifiedJavaName = generateQualifiedJavaName(action.getMessageStructure().getNameSpace());
			// Always keep packages in lowercase
			generatedName = generatedQualifiedJavaName + "." + me.getName();
		} else if (me instanceof INakedState) {
			INakedState state = (INakedState) me;
			if (state.hasEnclosingState()) {
				generatedName = generateQualifiedJavaName(state.getEnclosingState());
			} else {
				generatedName = generateQualifiedJavaName(state.getStateMachine());
			}
			generatedName = generatedName + "." + generateJavaName(state);
		} else {
			// TODO for actions and valuespecs, maybe ensure that the owning
			// behavior is the direct java namespace
			generatedName = pathname(me.getPathName()).toJavaString();
		}
		generatedName = generatedName.trim();
		return generatedName;
	}

	private OJPathName pathname(PathName pathName) {
		OJPathName result = new OJPathName();
		for (String s : pathName.getNames()) {
			result.addToNames(s);
		}
		return result;
	}
}
