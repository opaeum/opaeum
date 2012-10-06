package org.opaeum.eclipse.uml.propertysections.ocl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.uml.options.EvaluationMode;
import org.eclipse.ocl.uml.options.UMLEvaluationOptions;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.metamodel.workspace.OpaeumOcl;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.UMLOCLFactory;

public final class OpaeumOclFactory extends UMLOCLFactory{
	private Map<String,Classifier> variables = new HashMap<String,Classifier>();
	private Element context;
	OpaeumOcl library;
	public OpaeumOclFactory(OpaeumOcl library){
		super();
		this.library = library;
	}
	@Override
	public void setContext(EObject context){
		this.context = (Element) context;
		super.setContext(EmfBehaviorUtil.getSelf((Element) context));
	}
	@Override
	public Object getContextClassifier(EObject object){
		return EmfBehaviorUtil.getSelf((Element) object);
	}
	@Override
	public OCL<?,?,?,?,?,?,?,?,?,?,?,?> createOCL(ModelingLevel level){
		try{
			OCL<?,?,?,?,?,?,?,?,?,?,?,?> result = library.createOcl(context, variables);
			switch(level){
			case M2:
				EvaluationOptions.setOption(result.getEvaluationEnvironment(), UMLEvaluationOptions.EVALUATION_MODE, EvaluationMode.RUNTIME_OBJECTS);
				break;
			default:
				EvaluationOptions.setOption(result.getEvaluationEnvironment(), UMLEvaluationOptions.EVALUATION_MODE, EvaluationMode.INSTANCE_MODEL);
				break;
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Override
	public OCL<?,?,?,?,?,?,?,?,?,?,?,?> createOCL(ModelingLevel level,Resource res){
		return super.createOCL(level, res);
	}
	public void addVariable(String name,Classifier type){
		variables.put(name, type);
	}
}