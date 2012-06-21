package org.opaeum.eclipse.newchild;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PlatformUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class UMLEditorMenu extends MenuManager{
	public UMLEditorMenu(){
		super("Create child");
	}
	public UMLEditorMenu(String text){
		super(text);
	}
}
