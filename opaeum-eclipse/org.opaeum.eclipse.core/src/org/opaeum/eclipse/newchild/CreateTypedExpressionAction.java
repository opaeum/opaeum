package org.opaeum.eclipse.newchild;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.metamodel.workspace.LibraryType;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;

public class CreateTypedExpressionAction extends AbstractCreateChildAction implements ICreateChildAction{
	private LibraryType typeOfChild;
	public CreateTypedExpressionAction(MatchingOwner[] matchingOwners,String featureName,LibraryType typeOfChild){
		super(matchingOwners, featureName, UMLPackage.eINSTANCE.getOpaqueExpression());
		this.typeOfChild=typeOfChild;
	}
	public CreateTypedExpressionAction(MatchingOwner s,String fromExpression,LibraryType typeOfChild){
		this(new MatchingOwner[]{s}, fromExpression, typeOfChild);
	}
	@Override
	public CreateChildAction createAction(IWorkbenchPart workbenchPart,IStructuredSelection selection, OpaeumLibrary library){
		if(typeOfChild==null){
			return null;
		}
		CommandParameter descriptor = createCommandParameter(selection);
		if(descriptor == null){
			return null;
		}
		Type type = library.getLibraryType(typeOfChild);
		((OpaqueExpression) descriptor.getValue()).setType(type);
		CreateChildAction result;
		String childName = null;
		result = new CreateChildAndSelectAction(workbenchPart, selection, descriptor);
		childName = NameConverter.capitalize(NameConverter.separateWords(type.getName() + "Expression"));
		if(name == null){
			result.setText(NameConverter.capitalize(NameConverter.separateWords(featureName)) + "|" + childName);
		}else{
			result.setText(name);
		}
		return result;
	}
	public LibraryType getExpressionType(){
		return typeOfChild;
	}
}
