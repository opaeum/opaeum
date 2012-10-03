package org.opaeum.rwt.adapters
{
	import flash.display.DisplayObjectContainer;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import flexlib.mdi.containers.MDIWindow;
	
	import mx.collections.ArrayCollection;
	import mx.controls.MenuBar;
	import mx.controls.menuClasses.MenuBarItem;
	import mx.core.IVisualElementContainer;
	import mx.core.UIComponent;
	import mx.core.mx_internal;
	import mx.events.MenuEvent;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.items.RwtMenuItem;
	import org.opaeum.rwt.widgets.IRwtWidget;
	import org.opaeum.rwt.widgets.RwtMenu;
	import org.opaeum.rwt.widgets.RwtMenuBar;
	
	import rwt.protocol.ObjectRegistry;
	import rwt.remote.Server;
	
	import spark.components.BorderContainer;
	
	public class MenuAdapter extends WidgetAdapter
	{
		public function MenuAdapter()
		{
			super();
			super.registerEventListenerType("menu", MenuEvent.MENU_SHOW,function(ev:MenuEvent):void{
				var req:Server = Server.getInstance();
				req.addEvent( "org.eclipse.swt.events.menuShown", ObjectRegistry.getId(getSubMenuFrom(ev)));
				var onReceive:Function=function(e:Event):void{
					var hostWidget:IRwtWidget=getSubMenuFrom(ev).hostWidget;
					if(hostWidget is RwtMenuItem){
						RwtMenuItem(hostWidget).parentMenu.showSubMenu(getSubMenuFrom(ev));
					}else if(hostWidget is UIComponent){
						var uic:UIComponent=UIComponent(hostWidget);
						getSubMenuFrom(ev).show(uic.x,uic.y); 
					}
					req.removeEventListener("received", onReceive);
				};
				req.addEventListener("received", onReceive);
				req.send();
			});				
			super.registerEventListenerType("menu", MenuEvent.MENU_HIDE,function(ev:MenuEvent):void{
				var req:Server = Server.getInstance();
				req.addEvent( "org.eclipse.swt.events.menuHidden", ObjectRegistry.getId(getSubMenuFrom(ev)));
			});				
			super.registerProperty("text","label");
		}
		private function getSubMenuFrom(ev:MenuEvent):RwtMenu{
			if(ev.menu!=null){
				return RwtMenu(ev.menu);
			}
			return null;
		}
		
		override public function destroy(target:Object):void
		{
			if( target is MenuBar){
				var mb:MenuBar=MenuBar(target);
				var parent:DisplayObjectContainer=mb.parent;
				if(parent is IVisualElementContainer){
					IVisualElementContainer(parent).removeElement(mb);
				}else{
					parent.removeChild(mb);
				}
			}else{
				//TODO how to destroy?
			}
		}
		
		
		public override function create(parent:Object, args:*):Object{
			var result:Object=null;
			var styles:Array=args["style"];
			for(var i:Number=0; i < styles.length;i++){
				var style:String=styles[i];
				switch (style){
					case "BAR":
						var menuBar:RwtMenuBar=new RwtMenuBar();
						menuBar.labelField="label";
						menuBar.dataProvider=new ArrayCollection();
						if(parent is MDIWindow){
							var w:MDIWindow=MDIWindow(parent);
							BorderContainer(w.getChildAt(0)).addElement(UIComponent( menuBar));
						}
						result= menuBar;
						break;
					case "DROP_DOWN":
					case "POP_UP":
						var rwtMenu:RwtMenu=new RwtMenu();
						result= rwtMenu;
						break;
				}
			}
			return result;
		}

		
		
	}
	
}