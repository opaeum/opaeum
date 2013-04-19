package org.opaeum.rap
{
	import flash.events.ContextMenuEvent;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	import flash.utils.Dictionary;
	import flash.utils.describeType;
	
	import flexlib.mdi.containers.MDIWindow;
	
	import mx.containers.Canvas;
	import mx.controls.Alert;
	import mx.controls.Menu;
	import mx.controls.MenuBar;
	import mx.core.IVisualElement;
	import mx.core.IVisualElementContainer;
	import mx.core.UIComponent;
	import mx.events.EventListenerRequest;
	import mx.events.MenuEvent;
	
	import org.opaeum.rwt.items.RwtMenuItem;
	import org.opaeum.rwt.widgets.IRwtControl;
	import org.opaeum.rwt.widgets.IRwtMenu;
	import org.opaeum.rwt.widgets.RwtMenu;
	
	import rwt.protocol.ObjectEntry;
	import rwt.protocol.ObjectRegistry;
	import rwt.remote.Request;
	import rwt.remote.Server;
	
	import spark.components.BorderContainer;
	import spark.components.Group;
	import spark.components.Image;
	import spark.components.Panel;
	import spark.components.SkinnableContainer;
	import spark.components.TitleWindow;
	import spark.components.supportClasses.SkinnableComponent;
	import spark.components.supportClasses.SkinnableContainerBase;
	import spark.primitives.BitmapImage;
	import spark.skins.spark.TitleWindowSkin;
	
	public class WidgetAdapter
	{
		private var eventMappings:Dictionary=new Dictionary();
		private var callableMethodMap:Dictionary=new Dictionary();
		private var propertyMap:Dictionary=new Dictionary();
		private var swtProperties:Array=new Array();
		private var swtEventListenerTypes:Array=new Array();
		public function WidgetAdapter()
		{
			registerEventListenerType("mouse", MouseEvent.MOUSE_DOWN, EventUtil.mouseDown);
			registerEventListenerType("mouse", MouseEvent.MOUSE_UP, EventUtil.mouseUp);
			registerEventListenerType("focus", FocusEvent.FOCUS_IN, EventUtil.focusGained);
			registerEventListenerType("focus", FocusEvent.FOCUS_OUT, EventUtil.focusLost);
			registerEventListenerType( "activate",Event.ACTIVATE,function(ev:Event):void{
				if(ev.target is UIComponent && !(ev.target is IRwtMenu)){
					Server.getInstance().addEvent("org.eclipse.swt.events.controlActivated",ObjectRegistry.getId(UIComponent(ev.target)));
				}
				//TODO determine when to send
			});
			registerProperty("bounds");
			registerProperty("visibility","visible");
			registerProperty("menu");
		}
		protected function registerEventListenerType(swtListenerType:String, asEventType:String, handler:Function):void{
			var l:Array=eventMappings[swtListenerType];
			if(	l==null){
				l=eventMappings[swtListenerType]=new Array();
			}
			if(swtEventListenerTypes.indexOf(swtListenerType)==-1){
				swtEventListenerTypes.push(swtListenerType);
			}
			l.push(new EventMapping(asEventType,handler));
		}
		protected function registerCallableMethod(name:String, method:Function):void{
			callableMethodMap[name]=method;
		}
		protected function registerProperty(swtName:String, asName:String=""):void{
			if(asName!=""){
				propertyMap[swtName]=asName;
			}
			swtProperties.push(swtName);
		}
		public function setProperties(target:Object, value:*):void{
			for(var i:Number=0;i<this.swtProperties.length;i++){
				var swtName:String =this.swtProperties[i];
				if(value[swtName]!=null){
					setProperty(target,swtName,value[swtName]);
				}
			}
		}
		protected function buildContextMenu(rwtMenu:RwtMenu):ContextMenu{
			var menu:ContextMenu=new ContextMenu();
			for(var i:Number=0;i<rwtMenu.children.length;i++){
				var rwtItem:RwtMenuItem=rwtMenu.children[i];
				var item:ContextMenuItem=new ContextMenuItem(rwtItem.label);
				item.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT,function(ev:ContextMenuEvent):void{
					rwtItem.dispatchEvent(new MenuEvent(MenuEvent.ITEM_CLICK,ev.bubbles,ev.cancelable,null,null,rwtItem,null,rwtItem.label,i));
				});
				menu.customItems.push(item);
			}
			return menu;
			
			
		}
		protected function setProperty(target:Object, swtName:String,value:*):void{
			if(swtName=="menu" && target is IRwtControl){
				var oe:ObjectEntry=ObjectRegistry.getEntry(value);
				if(oe==null){
					ObjectRegistry.addRegistrationCallback(value,function(rwtMenu:RwtMenu):void{
						rwtMenu.init(IRwtControl( target));
					});
				}else{
					RwtMenu(oe.object).init(IRwtControl( target));
				}
			}else if(propertyMap[swtName]!=null && (value is Number || value is String || value is Boolean)){
				if(target == null){
					trace(swtName);
				}else{
					target[propertyMap[swtName]]=value;
				}
			}else if(target is UIComponent){
				var uic:UIComponent=UIComponent(target);
				if(swtName=="bounds" ){
					uic.x=value[0];
					if(uic.parent is IVisualElement){//&& uic.parent.parent is TitleWindow
						var parent:IVisualElement=ObjectRegistry.findControl(IVisualElement( uic.parent));
						if(parent is MDIWindow){
							var height:Number=MDIWindow(parent).titleBarOverlay.height;
							uic.y=(value[1]-height )/*because they are added to the contentGroup*/ + 4 /*arb number based on spacing in SWT*/;
							uic.y=Math.max(0,uic.y);//negative
						}else{
							uic.y=value[1];
						}
					}else{
						uic.y=value[1];
					}
					uic.width=value[2];
					uic.height=value[3];
					if(uic.width==0 || uic.height==0)
					{
						uic.visible=false;
					}
					uic.invalidateSize();	
					uic.validateSize(false);
					
					
				}else if(swtName=="minimumSize"){
					uic.minWidth=value[0];
					uic.minHeight=value[1];
					uic.invalidateSize();	
				}else{
					setObjectProperty(target,swtName,value);
				}
			}else{
				setObjectProperty(target,swtName,value);
			}
		}
		protected function buildImage(img:Array):Image{
			var result:Image=new Image();
			result.source=img[0];
			result.width=img[1];
			result.height=img[2];
			return result;
		}
		protected function buildBitmapImage(img:Array):BitmapImage{
			var result:BitmapImage=new BitmapImage();
			result.source=img[0];
			result.width=img[1];
			result.height=img[2];
			return result;
		}
		
		protected function setObjectProperty(target:Object, swtName:String, value:*):void
		{
			
		}
		public function processListeners(object:Object, swtListenerConfig:*):void{
			if(object is EventDispatcher){
				var target:EventDispatcher=EventDispatcher(object);
				for( var i:Number =0; i < this.swtEventListenerTypes.length;i++){
					var type:String =this.swtEventListenerTypes[i];
					if(swtListenerConfig[type]!=undefined){
						var selected:Boolean=swtListenerConfig[type];
						if(selected==true){
							addEventListener(target,type);
						}else{
							removeEventListener(target,type);
						}
					}
				}
			}
		}
		private function addEventListener(target:EventDispatcher, swtEventListenerType:String):void{
			var events:Array=eventMappings[swtEventListenerType];		
			if( events!=null){
				for (var i:Number = 0; i<events.length;i++) 
				{
					var em:EventMapping=events[i];
					target.addEventListener(em.asEventType,em.handler);
				}
			}
		}
		private function removeEventListener(target:EventDispatcher, swtEventListenerType:String):void{
			var events:Array=eventMappings[swtEventListenerType];		
			if( events!=null){
				for each (var i:int in events) 
				{
					var em:EventMapping=events[i];
					target.removeEventListener(em.asEventType,em.handler);
				}
			}
		}
		public function call(target:Object, method:String, args:*):void{
			if(callableMethodMap[method]!=undefined){
				var m:Function=callableMethodMap[method];
				m(target,args);
			}
			
		}
		public function create(parent:Object, args:*):Object{
			var result:Object=newInstance(args);
			if(parent is UIComponent && result is UIComponent){
				if(parent is MDIWindow){
					var w:MDIWindow=MDIWindow(parent);
					BorderContainer (w.getChildAt(0)).addElement(UIComponent( result));
				}else  if(parent is IVisualElementContainer){
					IVisualElementContainer(parent).addElement(UIComponent(result));
				}else {
					UIComponent(parent).addChild(UIComponent(result));
				}
			}
			return result;
		}
		
		protected function newInstance(args:*):Object
		{
			return null;
		}
		public function destroy(target:Object):void{
			if(target is UIComponent){
				var uic:UIComponent=UIComponent(target)
				if(uic.parent is IVisualElement){
					var parent:IVisualElement=ObjectRegistry.findControl(IVisualElement( uic.parent));
					if(parent is SkinnableContainer){
						SkinnableContainer(parent).removeElement(uic);
					}else{
						try{
							uic.parent.removeChild(uic);
						}catch(e:Error){
							if(uic.parent is IVisualElementContainer){
								IVisualElementContainer(uic.parent).removeElement(uic);
							}else{
								uic.parent.removeChild(uic);
							}
						}
					}
				}else if(uic.parent!=null){
					uic.parent.removeChild(uic);
				}
			}
		}
	}
}