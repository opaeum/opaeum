package org.opaeum.rwt.adapters
{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;
	
	import flexlib.mdi.containers.MDIWindow;
	import flexlib.mdi.events.MDIWindowEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.Menu;
	import mx.controls.MenuBar;
	import mx.controls.menuClasses.MenuBarItem;
	import mx.core.FlexGlobals;
	import mx.core.ScrollPolicy;
	import mx.core.UIComponent;
	import mx.effects.Resize;
	import mx.managers.IFocusManagerComponent;
	import mx.managers.PopUpManager;
	import mx.skins.halo.WindowBackground;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.items.RwtMenuItem;
	import org.opaeum.rwt.widgets.IRwtMenu;
	
	import rwt.protocol.ObjectRegistry;
	import rwt.remote.Server;
	
	import spark.components.BorderContainer;
	import spark.components.ResizeMode;
	import spark.components.TitleWindow;
	import spark.skins.spark.TitleWindowSkin;

	public class ShellAdapter extends WidgetAdapter
	{
		public function ShellAdapter()
		{
			super.registerProperty("text", "title");
			super.registerProperty("parent");
			super.registerProperty("resizable", "resizable");
			super.registerEventListenerType("shell",MDIWindowEvent.FOCUS_END,shellDeactivated); 
			super.registerEventListenerType("shell",MDIWindowEvent.FOCUS_START,shellActivated); 
			super.registerEventListenerType("shell",MDIWindowEvent.CLOSE,shellClosed); 
		}
		private function onKeydown ( ev:KeyboardEvent):void {
			if(    ev.keyCode==Keyboard.ENTER && !ev.shiftKey
				&& !ev.altKey  && !ev.ctrlKey )
			{
				//TODO
//				var defButton:Button  = MDIWindow(ev.target).closeBtn;
//				if( defButton != null && defButton.visible ) {
//					defButton.dispatchEvent(new KeyboardEvent(KeyboardEvent.KEY_DOWN,,,,,Keyboard.P ));
//				}
			} else if( ev.keyCode== Keyboard.ESCAPE) {
				MDIWindow(ev.target).close();
			}
		}
		private function shellDeactivated(ev:MDIWindowEvent):void{
			var id:String = ObjectRegistry.getId(ev.window);
			var req:Server = Server.getInstance();
			req.addEvent( "org.eclipse.swt.events.shellDeactivated", id );
			req.send();
		}
		private function shellActivated(ev:MDIWindowEvent):void{
			var id:String = ObjectRegistry.getId(ev.window);
			var req:Server = Server.getInstance();
			req.addEvent( "org.eclipse.swt.events.shellActivated", id );
			req.send();

		}
		private function locationChanged(ev:MDIWindowEvent):void{
			var req:Server = Server.getInstance();
			var id:String = ObjectRegistry.getId( ev.window );
			var left:Number = ev.window.x;
			var top:Number = ev.window.y;
				req.addParameter( id + ".bounds.x", left );
				req.addParameter( id + ".bounds.y", top );
			req.send()
			
		}
		private function resized(ev:MDIWindowEvent):void{
			var req:Server = Server.getInstance();
			var id:String = ObjectRegistry.getId( ev.window );
			var height:Number = ev.window.height;
			var width:Number = ev.window.width;
			req.addParameter( id + ".bounds.height", height );
			req.addParameter( id + ".bounds.width", width );
			req.send()
			
		}
		private function shellClosed(ev:MDIWindowEvent):void{
			var id:String = ObjectRegistry.getId(ev.window);
			var req:Server = Server.getInstance();
			req.addEvent( "org.eclipse.swt.events.shellClosed", id );
			req.addEvent( "rwt.widgets.Shell_close", id );//VOoDOo
			
			req.send();
		}
		
		public override function create(parent:Object, args:*):Object{
			var result:MDIWindow=new MDIWindow();
//			var result:TitleWindow = TitleWindow(PopUpManager.createPopUp(RapApplication.rapInstance,TitleWindow));
//			RapApplication.rapInstance.activeShells.push(result);
			result.x=args.bounds[0];
			result.y=args.bounds[1];
			result.width=args.bounds[2];
			result.height=args.bounds[3];
			var c:BorderContainer = new BorderContainer();
			c.x=0;
			c.y=0;
			c.percentHeight=100;
			c.percentWidth=100;
			result.addChild(c);
			result.resizable=false;
			result.showCloseButton=false;
			result.showControls=false;
			result.verticalScrollPolicy=ScrollPolicy.OFF;
			result.horizontalScrollPolicy=ScrollPolicy.OFF;
			var styles:Array= args.style;
			for(var i:Number =0; i < styles.length; i++){
				switch(styles[i]){
					case "RESIZE":
						result.resizable=true;
						break;
					case "MAX":
						result.showControls=true;
						//TODO can maximise
						break;
					case "MIN":
						result.showControls=true;
						//TODO can minimise
						break;
					case "CLOSE":
						result.showCloseButton=true;
						//TODO can minimise
						break;
					case "TITLE":
						result.title=args.title;
						//TODO can minimise
						break;
						
				}
			}
			Server.getInstance().addEventListener("send",function(ev:Event):void{
				var req:Server = Server.getInstance();
				if( result.hasFocus ) {
					var focusedChild:IFocusManagerComponent = RapApplication.rapInstance.focusManager.getFocus();
					if( focusedChild !=null  && !(focusedChild is IRwtMenu)) {
						//Menus are not controls in Eclipse
						var focusedChildId:String = ObjectRegistry.getId( focusedChild );
						req.addParameter( req.getUIRootId() + ".focusControl", focusedChildId );
						req.addParameter( ObjectRegistry.getId(result) + ".activeControl", focusedChildId);
					}
				}
			});
			result.addEventListener( MDIWindowEvent.DRAG_END,locationChanged);
			result.addEventListener( MDIWindowEvent.RESIZE_END,resized);
			result.addEventListener(KeyboardEvent.KEY_DOWN,onKeydown);

			//TODO remove
				
			RapApplication.rapInstance.mdiCanvas.windowManager.add(result);
			return result;
		}

		override protected function newInstance(args:*):Object
		{
			var style:Array=args["style"];
//			var result:TitleWindow= new TitleWindow();
//			for each (var i:int in style) 
//			{
//				switch(style[i]){
//					case "resize":
//						result.title="";
//				}	
//			}
			return null;
		}
		
		override protected function setProperty(target:Object, swtName:String, value:*):void
		{
			super.setProperty(target, swtName, value);
			if(swtName=="bounds"){
				MDIWindow(target).invalidateSize();
			}

		}
		
		
		override protected function setObjectProperty(target:Object, swtName:String, value:*):void
		{
		}
		
		
		
	}
}