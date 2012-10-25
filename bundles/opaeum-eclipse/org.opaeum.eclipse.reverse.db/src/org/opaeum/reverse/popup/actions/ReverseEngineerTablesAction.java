package org.opaeum.reverse.popup.actions;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.menu.AbstractReverseEngineerAction;
import org.opaeum.eclipse.reverse.db.UmlGenerator;
public class ReverseEngineerTablesAction extends AbstractReverseEngineerAction{
	public ReverseEngineerTablesAction(IStructuredSelection selection){
		super(selection, "Reverse Engineer Tables");
	}
	public AbstractCommand buildCommand(final Package model){
		return new AbstractCommand(){
			@Override
			public boolean canExecute(){
				return true;
			}
			@Override
			public void redo(){
				new UmlGenerator().generateUml(calculateTables(), model);
			}
			@Override
			public void execute(){
				redo();
			}
		};
	}
	private Collection<JDBCTable> calculateTables(){
		return SelectedTableCollector.collectEffectivelySelectedTables(selection);
	}
}
