package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uimodeler.common.UimFigureUtil;

public class WindowBuilderUtil{
	private static String SECOND_LEVEL_NEED_IMAGE = "SECOND_LEVEL_NEED_IMAGE";
	public static void hideSecondLevel(Control root){
		if(root instanceof ISecondLayerWidget){
			root.setVisible(false);
			stashSecondLevelImageRequirement(root);
		}
		if(root instanceof Composite){
			Control[] children = ((Composite) root).getChildren();
			for(Control control:children){
				if(control instanceof Composite){
					hideSecondLevel((Composite) control);
				}
			}
		}
	}
	public static void revealSecondLevel(Control root){
		if(root instanceof ISecondLayerWidget){
			root.setVisible(true);
			markSecondLevelImageRequirement(root);
		}
		if(root instanceof Composite){
			Control[] children = ((Composite) root).getChildren();
			for(Control control:children){
				if(control instanceof Composite){
					revealSecondLevel((Composite) control);
				}
			}
		}
	}
	private static void markSecondLevelImageRequirement(Control root){
		if(root.getData(SECOND_LEVEL_NEED_IMAGE) != null){
			System.out.println("###"+root);
			root.setData(SECOND_LEVEL_NEED_IMAGE, null);
			root.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		}
		if(root instanceof Composite){
			for(Control control:((Composite) root).getChildren()){
				markSecondLevelImageRequirement(control);
			}
		}
	}
	protected static void stashSecondLevelImageRequirement(Control root){
		if(root.getData(OSSupport.WBP_NEED_IMAGE) != null){
			root.setData(OSSupport.WBP_NEED_IMAGE, null);
			root.setData(SECOND_LEVEL_NEED_IMAGE, Boolean.TRUE);
		}
		if(root instanceof Composite){
			for(Control control:((Composite) root).getChildren()){
				stashSecondLevelImageRequirement(control);
			}
		}
	}
	public static void layoutRecursively(Composite root){
		root.layout();
		Control[] children = root.getChildren();
		for(Control control:children){
			if(control instanceof Composite){
				layoutRecursively((Composite) control);
			}
		}
	}
	public static boolean needsComponentShot(Composite root){
		if(root.getData(OSSupport.WBP_NEED_IMAGE) != null){
			return true;
		}
		Control[] children = root.getChildren();
		for(Control control:children){
			if(control instanceof Composite && needsComponentShot((Composite) control)){
				return true;
			}else if(control.getData(OSSupport.WBP_NEED_IMAGE) != null){
				return true;
			}
		}
		return false;
	}
	public static void printNeedsComponentShot(Composite root){
		if(root.getData(OSSupport.WBP_NEED_IMAGE) != null){
			System.out.println("Needs image:" +  root);
		}
		Control[] children = root.getChildren();
		for(Control control:children){
			if(control instanceof Composite){
				printNeedsComponentShot((Composite) control);
			}
		}
	}
	public static void clearNeedsImage(Composite root){
		Object data = root.getData(OSSupport.WBP_IMAGE);
		root.setData(OSSupport.WBP_NEED_IMAGE, null);
		if(data != null){
			root.setData(UimFigureUtil.OPAEUM_IMAGE, data);
		}
		Control[] children = root.getChildren();
		for(Control control:children){
			if(control instanceof Composite){
				clearNeedsImage((Composite) control);
			}else{
				if(control.getData(OSSupport.WBP_IMAGE) != null){
					control.setData(UimFigureUtil.OPAEUM_IMAGE, control.getData(OSSupport.WBP_IMAGE));
				}
				control.setData(OSSupport.WBP_NEED_IMAGE, null);
			}
		}
	}
	public static void activateRootComposite(Composite widget){
		if(widget.getParent() instanceof Shell){
			((StackLayout) widget.getParent().getLayout()).topControl = widget;
			widget.getParent().layout();
		}else{
			activateRootComposite(widget.getParent());
		}
	}
//	public static void markRecursivelyForShot(Control widget){
//		if(widget.getData(UimFigureUtil.FIGURE) != null){
//			widget.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
//		}
//		if(widget instanceof Composite){
//			for(Control child:((Composite) widget).getChildren()){
//				markRecursivelyForShot(child);
//			}
//		}
//	}
}
