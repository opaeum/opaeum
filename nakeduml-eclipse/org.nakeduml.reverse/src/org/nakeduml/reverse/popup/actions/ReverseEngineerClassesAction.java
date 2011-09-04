package org.nakeduml.reverse.popup.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class ReverseEngineerClassesAction extends AbstractHandler implements IObjectActionDelegate{
	/**
	 * Constructor for Action1.
	 */
	IStructuredSelection selection;
	private Shell shell;
	public ReverseEngineerClassesAction(){
		super();
	}
	private static CompilationUnit parse(ICompilationUnit unit){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}
	private static CompilationUnit parse(IClassFile unit){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		Shell shell = HandlerUtil.getActiveShell(event);
		try{
			execute(selection, shell);
		}catch(JavaModelException e){
			e.printStackTrace();
		}
		return null;
	}
	protected void execute(IStructuredSelection selection,Shell shell) throws JavaModelException{
		List<ITypeBinding> types = selectTypeDeclarations(selection);
		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(shell, new WorkbenchLabelProvider(), new BaseWorkbenchContentProvider());
		dialog.setTitle("Tree Selection");
		dialog.setMessage("Select the elements from the tree:");
		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
		dialog.addFilter(new ViewerFilter(){
			@Override
			public boolean select(Viewer viewer,Object parentElement,Object element){
				if(element instanceof IFile){
					return ((IFile) element).getFileExtension().equals("uml");
				}
				return true;
			}
		});
		dialog.open();
		if(dialog.getFirstResult() != null){
			IFile file = (IFile) dialog.getFirstResult();
			try{
				new UmlGenerator().generateUml(types, file.getLocation().toFile());
				file.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
			}catch(Exception e){
				e.printStackTrace();
			}
			MessageDialog.openInformation(shell, "org.nakeduml.reverse.Reverse", "Successes!");
		}
	}
	private List<ITypeBinding> selectTypeDeclarations(IStructuredSelection selection) throws JavaModelException{
		Object[] selections = selection.toArray();
		List<ITypeBinding> types = new ArrayList<ITypeBinding>();
		for(Object object:selections){
			addTypesIn(types, object);
		}
		return types;
	}
	protected void addTypesIn(List<ITypeBinding> types,Object object) throws JavaModelException{
		CompilationUnit cu=null;
		if(object instanceof ICompilationUnit){
			cu = parse(((ICompilationUnit) object));
		}else if(object instanceof IClassFile){
			cu = parse((IClassFile) object);
		}else if(object instanceof IPackageFragment){
			IPackageFragment pf = (IPackageFragment) object;
			for(IJavaElement c:pf.getChildren()){
				addTypesIn(types, c);
			}
		}
		if(cu != null){
			@SuppressWarnings("unchecked")
			List<AbstractTypeDeclaration> typeDeclarations = cu.types();
			for(AbstractTypeDeclaration type:typeDeclarations){
				types.add(type.resolveBinding());
			}
		}
	}
	@Override
	public void run(IAction action){
		try{
			execute(selection, shell);
		}catch(JavaModelException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void selectionChanged(IAction action,ISelection selection){
		if(selection instanceof IStructuredSelection){
			this.selection = (IStructuredSelection) selection;
		}
	}
	@Override
	public void setActivePart(IAction action,IWorkbenchPart targetPart){
		this.shell = targetPart.getSite().getShell();
	}
}
