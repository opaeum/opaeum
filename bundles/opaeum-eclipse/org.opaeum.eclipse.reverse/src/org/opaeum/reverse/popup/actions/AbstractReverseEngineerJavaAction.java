package org.opaeum.reverse.popup.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
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
	private Map<ITypeBinding,AbstractTypeDeclaration> selectTypeDeclarations(IStructuredSelection selection) throws JavaModelException{
		Object[] selections = selection.toArray();
		Map<ITypeBinding,AbstractTypeDeclaration> types = new HashMap<ITypeBinding,AbstractTypeDeclaration>();
		for(Object object:selections){
			addTypesIn(types, object);
		}
		return types;
	}
	protected void addTypesIn(Map<ITypeBinding,AbstractTypeDeclaration> types,Object object) throws JavaModelException{
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
				types.put(type.resolveBinding(),type);
			}
		}
	}
	@Override
	protected Command buildCommand(final Package model){
		try{
			final Map<ITypeBinding,AbstractTypeDeclaration> types = selectTypeDeclarations(selection);
			return new AbstractCommand(){
				@Override
				public boolean canExecute(){
					return true;
				}
				@Override
				public void execute(){
					ProgressMonitorDialog dlg = null;
					try{
						dlg = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
						dlg.setBlockOnOpen(false);
						dlg.open();
						createGenerator().generateUml(types, model, dlg.getProgressMonitor());
					}catch(Exception e){
						OpaeumEclipsePlugin.logError("Could not reverse Java classes", e);
						MessageDialog.openError(Display.getCurrent().getActiveShell(), "Reverse Engineering Failed", e.toString());
					}finally{
						if(dlg != null){
							if(dlg.getProgressMonitor() != null){
								dlg.getProgressMonitor().done();
							}
							dlg.close();
						}
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
