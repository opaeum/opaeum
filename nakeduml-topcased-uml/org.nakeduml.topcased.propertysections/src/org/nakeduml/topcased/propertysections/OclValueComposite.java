package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.expressions.ExpressionsFactory;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.nakeduml.eclipse.EmfBehaviorUtil;
import org.nakeduml.eclipse.EmfElementFinder;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;
import org.topcased.modeler.uml.oclinterpreter.ModelingLevel;
import org.topcased.modeler.uml.oclinterpreter.OCLDocument;
import org.topcased.modeler.uml.oclinterpreter.OCLSourceViewer;
import org.topcased.modeler.uml.oclinterpreter.UMLOCLFactory;

public class OclValueComposite extends Composite{
	Composite composite = null;
	private OCLSourceViewer viewer;
	private OCLDocument document;
	private UMLOCLFactory factory;
	private ArrayList<Variable<Object,Object>> variables;
	private Class contextAsClassifier;
	public OclValueComposite(Composite parent,FormToolkit toolkit){
		super(parent, SWT.NONE);
		setLayout(new GridLayout(1, false));
		viewer = new OCLSourceViewer(this, new ColorManager(), SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		document = new OCLDocument();
		factory = new UMLOCLFactory(){
			@Override
			public Object getContextClassifier(EObject object){
				return contextAsClassifier;
			}
			@Override
			public OCL<?,?,?,?,?,?,?,?,?,?,?,?> createOCL(ModelingLevel level){
				OCL<?,?,?,?,?,?,?,?,?,?,?,?> ocl = super.createOCL(level);
				for(Variable variable:variables){
					ocl.getEnvironment().addElement(variable.getName(), variable, true);
					// TODO add "now"
				}
				return ocl;
			}
			@Override
			public OCL<?,?,?,?,?,?,?,?,?,?,?,?> createOCL(ModelingLevel level,Resource res){
				// TODO Auto-generated method stub
				return super.createOCL(level, res);
			}
		};
		document.setOCLFactory(factory);
		document.setModelingLevel(ModelingLevel.M1);
		viewer.setInput(document);
		manageContentAssist();
	}
	public StyledText getTextControl(){
		return viewer.getTextWidget();
	}
	private void manageContentAssist(){
		viewer.enableOperation(SourceViewer.CONTENTASSIST_PROPOSALS, true);
		viewer.enableOperation(SourceViewer.QUICK_ASSIST, true);
		viewer.enableOperation(SourceViewer.CONTENTASSIST_CONTEXT_INFORMATION, true);
	}
	public String getCompositeValue(){
		return document.get();
	}
	public void setValueElement(final Element vp){
		if(viewer != null){
			this.variables = new ArrayList<Variable<Object,Object>>();
			this.contextAsClassifier = new ClassImpl(){
				public Resource eResource(){
					return vp.eResource();
				}
			};
			for(TypedElement te:EmfElementFinder.getTypedElementsInScope(vp)){
				if(te instanceof org.eclipse.uml2.uml.Variable || te instanceof Parameter){
					Variable<Object,Object> var = ExpressionsFactory.eINSTANCE.createVariable();
					var.setType(te.getType());
					var.setName(te.getName());
					variables.add(var);
				}else{
					contextAsClassifier.createOwnedAttribute(te.getName(), te.getType());
				}
			}
			Classifier contextObject = EmfBehaviorUtil.getContext(vp);
			if(contextObject != null){
				Variable<Object,Object> var = ExpressionsFactory.eINSTANCE.createVariable();
				var.setType(contextObject);
				var.setName("contextObject");
				variables.add(var);
			}
			Classifier self = EmfBehaviorUtil.getSelf(vp);
			if(self == null){
				contextAsClassifier.setName("Anonymous");
			}else{
				contextAsClassifier.setName(self.getName());
			}
			factory.setContext(contextAsClassifier);
			document.setOCLContext(contextAsClassifier);
			manageContentAssist();
		}
	}
	public void setCompositeValue(String text){
		document.set(text);
	}
}
