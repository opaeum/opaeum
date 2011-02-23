package net.sf.nakeduml.metamodel.core.internal;

import java.io.File;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.core.CodeGenerationStrategy;
import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociation;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.modelVisitors.IPackageVisitor;

public class NakedRootObjectImpl extends NakedPackageImpl implements INakedRootObject {
	private File modelFile;
	
	public File getModelFile() {
		return modelFile;
	}

	public void setModelFile(File modelFile) {
		this.modelFile = modelFile;
	}

	@Override
	public String getFileName() {
		return modelFile.getName().substring(0,modelFile.getName().indexOf("."));
	}
}
