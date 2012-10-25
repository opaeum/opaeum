package org.opaeum.eclipse.uml.propertysections.common;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;

public class OpaeumFeatureDialog extends FeatureEditorDialog{
	static interface IFeatureInfo{
		Object getObject();
		EClassifier getType();
		List<?> getCurrentValues();
	}
	public OpaeumFeatureDialog(Shell shell,ILabelProvider labelProvider,IFeatureInfo i,IStructuredSelection selection, String displayName, IChoiceProvider cp){
		super(shell, labelProvider, i.getObject(), i.getType(), selection.toList(), displayName, Arrays.asList(cp.getChoices()), true, true, true);
	}
}
