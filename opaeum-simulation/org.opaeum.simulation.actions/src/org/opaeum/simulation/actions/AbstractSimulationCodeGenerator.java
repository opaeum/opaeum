package org.opaeum.simulation.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextWorkspace;

public class AbstractSimulationCodeGenerator extends AbstractJavaProducingVisitor{
	protected SimulationModel simulationModel;
	public static enum SimulationSourceFolderId implements ISourceFolderIdentifier{
		GEN_SRC
	}
	private Map<INakedClassifier,List<InstanceSpecification>> instances = new HashMap<INakedClassifier,List<InstanceSpecification>>();
	public AbstractSimulationCodeGenerator(){
		super();
	}
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace,SimulationModel sm){
		this.initialize(pac, config, textWorkspace, workspace);
		this.simulationModel = sm;
		TreeIterator<EObject> eAllContents = simulationModel.eAllContents();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof InstanceSpecification){
				InstanceSpecification is = (InstanceSpecification) eObject;
				if(is.getClassifiers().size() == 1){
					INakedClassifier nc = (INakedClassifier) getNakedPeer(is.getClassifiers().get(0));
					List<InstanceSpecification> list = getInstances(nc);
					list.add(is);
				}
			}
		}
		//mmmm, dangerous
		SourceFolderDefinition agssfd = config.getSourceFolderDefinition(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC);
		config.defineSourceFolder(SimulationSourceFolderId.GEN_SRC, agssfd.getProjectNameStrategy(), agssfd.getProjectSuffix(), simulationModel.getName().toLowerCase());
	}
	protected List<InstanceSpecification> getInstances(INakedClassifier nc){
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
	protected INakedElement getNakedPeer(Element is){
		String id = EmfWorkspace.getId(is);
		return this.workspace.getModelElement(id);
	}
	protected final OJPathName dataGeneratorName(INakedClassifier nc,InstanceSpecification is){
		OJPathName copy = OJUtil.packagePathname(nc.getNameSpace()).getCopy();
		copy.append(nc.getName() + NameConverter.capitalize(NameConverter.toJavaVariableName(is.getName())));
		return copy;
	}
}