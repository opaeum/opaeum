package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.uml.UMLEnvironment;
import org.eclipse.ocl.uml.UMLEnvironmentFactory;
import org.eclipse.ocl.uml.UMLFactory;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.ocl.uml.options.EvaluationMode;
import org.eclipse.ocl.uml.options.UMLEvaluationOptions;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.eclipse.EmfBehaviorUtil;
import org.nakeduml.eclipse.EmfElementFinder;
import org.topcased.modeler.uml.oclinterpreter.DelegatingPackageRegistry;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.UMLOCLFactory;

public final class NakedUmlOclFactory extends UMLOCLFactory{
	private Element context;
	@Override
	public void setContext(EObject context){
		this.context=(Element) context;
		super.setContext( EmfBehaviorUtil.getSelf((Element) context));
	}
	@Override
	public Object getContextClassifier(EObject object){
		return EmfBehaviorUtil.getSelf((Element) object);
	}
	@Override
	public OCL<?,?,?,?,?,?,?,?,?,?,?,?> createOCL(ModelingLevel level){
		UMLEnvironmentFactory factory = new UMLEnvironmentFactory(new DelegatingPackageRegistry(getContext().eResource().getResourceSet().getPackageRegistry(),
				EPackage.Registry.INSTANCE), getContext().eResource().getResourceSet()){

					@Override
					public UMLEnvironment createEnvironment(){
						NakedUmlEnvironment result = new NakedUmlEnvironment(getResourceSet().getPackageRegistry(), getResourceSet());
						result.setFactory(this);
						if(context instanceof Property && context.getOwner() instanceof Classifier){
							Element owningObject = context.getOwner().getOwner();
							//TODO check if there is an end to composite first
							while(!(owningObject instanceof Classifier || owningObject==null)){
								owningObject=owningObject.getOwner();
							}
							if(owningObject instanceof Classifier){
								Variable  var = UMLFactory.eINSTANCE.createVariable();
								var.setType((Type) owningObject);
								var.setName("owningObject");
								result.addElement(var.getName(),var,true);

							}
						}else{
							Classifier contextObject = EmfBehaviorUtil.getContext(context);
							if(contextObject != null){
								Variable var = UMLFactory.eINSTANCE.createVariable();
								var.setType(contextObject);
								var.setName("contextObject");
								result.addElement(var.getName(),var,true);
							}
						}
						for(TypedElement te:EmfElementFinder.getTypedElementsInScope(context)){
							if(te instanceof org.eclipse.uml2.uml.Variable || te instanceof Parameter){
								Variable var = UMLFactory.eINSTANCE.createVariable();
								var.setType(te.getType());
								var.setName(te.getName());
								result.addElement(var.getName(),var,true);
							}
						}

						return result;
					}
			
		};
		OCL<?,?,?,?,?,?,?,?,?,?,?,?> result = OCL.newInstance(factory);
		switch(level){
		case M2:
			EvaluationOptions.setOption(result.getEvaluationEnvironment(), UMLEvaluationOptions.EVALUATION_MODE, EvaluationMode.RUNTIME_OBJECTS);
			break;
		default:
			EvaluationOptions.setOption(result.getEvaluationEnvironment(), UMLEvaluationOptions.EVALUATION_MODE, EvaluationMode.INSTANCE_MODEL);
			break;
		}
		return result;
	}
	@Override
	public OCL<?,?,?,?,?,?,?,?,?,?,?,?> createOCL(ModelingLevel level,Resource res){
		// TODO Auto-generated method stub
		return super.createOCL(level, res);
	}
}