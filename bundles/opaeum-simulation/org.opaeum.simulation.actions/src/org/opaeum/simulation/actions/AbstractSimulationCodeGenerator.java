package org.opaeum.simulation.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Type;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextWorkspace;

public class AbstractSimulationCodeGenerator extends AbstractJavaProducingVisitor{
	protected SimulationModel simulationModel;
	public static enum SimulationSourceFolderId implements ISourceFolderIdentifier{
		GEN_SRC
	}
	private Map<Type,List<InstanceSpecification>> instances = new HashMap<Type,List<InstanceSpecification>>();
	public AbstractSimulationCodeGenerator(){
		super();
	}
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,SimulationModel sm,OJUtil ojUtil){
		this.initialize(pac, config, textWorkspace, workspace, ojUtil);
		this.simulationModel = sm;
		TreeIterator<EObject> eAllContents = simulationModel.eAllContents();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof InstanceSpecification){
				InstanceSpecification is = (InstanceSpecification) eObject;
				if(is.getClassifiers().size() == 1){
					Classifier nc = (Classifier) is.getClassifiers().get(0);
					List<InstanceSpecification> list = getInstances(nc);
					list.add(is);
				}
			}
		}
		// mmmm, dangerous
		SourceFolderDefinition agssfd = config.getSourceFolderDefinition(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
		config.defineSourceFolder(SimulationSourceFolderId.GEN_SRC, agssfd.getProjectNameStrategy(), agssfd.getProjectQualifier(), simulationModel.getName()
				.toLowerCase());
	}
	@Override
	public Collection<Element> getChildren(Element root){
		if(root instanceof EmfWorkspace){
			return new HashSet<Element>(((EmfWorkspace) root).getPotentialGeneratingModels());
		}else{
			return super.getChildren(root);
		}
	}
	protected List<InstanceSpecification> getInstances(Type nc){
		List<InstanceSpecification> list = instances.get(nc);
		if(list == null){
			list = new ArrayList<InstanceSpecification>();
			instances.put(nc, list);
		}
		return list;
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
	protected final OJPathName dataGeneratorName(Type nc,InstanceSpecification is){
		OJPathName copy = ojUtil.packagePathname(nc.getNamespace()).getCopy();
		copy.append(NameConverter.capitalize(NameConverter.toJavaVariableName(is.getName() + is.eResource().getURIFragment(is))));
		return copy;
	}
}