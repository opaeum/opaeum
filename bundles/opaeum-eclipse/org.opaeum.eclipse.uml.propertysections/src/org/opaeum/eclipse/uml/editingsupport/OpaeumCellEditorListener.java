package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.jface.viewers.ICellEditorListener;

public interface OpaeumCellEditorListener extends ICellEditorListener{
	void activated();
	void deactivated();
}
