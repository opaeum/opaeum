package org.opaeum.uimodeler.modelexplorer.handler;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.cube.CubeFactory;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.CubeQueryEditor;

public class CreateCubeQueryEditorCommandHandler extends CreateCommandHandler{
	@Override
	protected EReference getFeature(){
		return UimPackage.eINSTANCE.getClassUserInteractionModel_CubeQueryEditor();
	}
	@Override
	protected EObject getNewObject(){
		CubeQueryEditor p = CubeFactory.eINSTANCE.createCubeQueryEditor();
		p.setName("CubeQueryEditor");
		return p;
	}
}
