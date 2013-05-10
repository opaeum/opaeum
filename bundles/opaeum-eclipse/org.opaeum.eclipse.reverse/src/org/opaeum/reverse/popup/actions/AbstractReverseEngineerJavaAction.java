package org.opaeum.reverse.popup.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.menu.AbstractReverseEngineerAction;

public abstract class AbstractReverseEngineerJavaAction extends AbstractReverseEngineerAction{
	public AbstractReverseEngineerJavaAction(IStructuredSelection selection,String name){
		super(selection, name);
	}
	private static CompilationUnit parse(ICompilationUnit unit){
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}
	private static CompilationUnit parse(IClassFile unit){
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
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
		CompilationUnit cu = null;
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
	protected Command buildCommand(final Package model){
		try{
			final List<ITypeBinding> types = selectTypeDeclarations(selection);
			return new AbstractCommand(){
				@Override
				public boolean canExecute(){
					return true;
				}
				@Override
				public void execute(){
					try{
						createGenerator().generateUml(types, model);
					}catch(Exception e){
						OpaeumEclipsePlugin.logError("Could not reverse Java classes", e);
						MessageDialog.openError(Display.getCurrent().getActiveShell(), "Reverse Engineering Failed", e.toString());
					}
				}
				@Override
				public void redo(){
				}
			};
		}catch(JavaModelException e){
			OpaeumEclipsePlugin.logError("Could not reverse Java classes", e);
			return DO_NOTHING;
		}
	}
	protected abstract AbstractUmlGenerator createGenerator();
}
