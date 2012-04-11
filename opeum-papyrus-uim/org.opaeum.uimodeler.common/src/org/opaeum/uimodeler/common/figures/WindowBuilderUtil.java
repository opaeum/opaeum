package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.os.OSSupport;

public class WindowBuilderUtil{
	public static Table findTable(Composite root){
		if(root instanceof Table){
			return (Table) root;
		}else{
			Control[] children = root.getChildren();
			for(Control control:children){
				if(control instanceof Composite){
					Table t = findTable((Composite) control);
					if(t != null){
						return t;
					}
				}
			}
		}
		return null;
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
	public static void clearNeedsImage(Composite root){
		Object data = root.getData(OSSupport.WBP_IMAGE);
		root.setData(OSSupport.WBP_NEED_IMAGE, null);
		if(data != null){
			root.setData("OPAEUM_IMAGE", data);
		}
		Control[] children = root.getChildren();
		for(Control control:children){
			if(control instanceof Composite){
				clearNeedsImage((Composite) control);
			}else{
				if(control.getData(OSSupport.WBP_IMAGE) != null){
					control.setData("OPAEUM_IMAGE", control.getData(OSSupport.WBP_IMAGE));
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
}
