package org.opaeum.rwt.items
{
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.controls.Alert;
	import mx.controls.Menu;
	import mx.controls.MenuBar;
	import mx.core.mx_internal;
	import mx.events.MenuEvent;
	
	import org.opaeum.rwt.widgets.IRwtControl;
	import org.opaeum.rwt.widgets.IRwtMenu;
	import org.opaeum.rwt.widgets.IRwtWidget;
	import org.opaeum.rwt.widgets.RwtMenu;
	
	public class RwtMenuItem extends EventDispatcher implements IRwtWidget
	{
		private var _label:String;
		private var _id:String;
		public var subMenu:RwtMenu;
		public var parentMenu:IRwtMenu;
		public var keyEquivalent:String;
		public function RwtMenuItem(parentMenu:IRwtMenu)
		{
			this.parentMenu=parentMenu;
			this.parentMenu.children.addItem(this);
			this.parentMenu.invalidateDisplayList();
			var self:RwtMenuItem=this;
			var listener:Function=function(ev:MenuEvent):void{
				if(_label=="Stuff"){
					trace(id+"<>" + RwtMenu(ev.menu).hostWidget.id);
					
					
				}
				if(ev.item==self || (ev.menu is RwtMenu && RwtMenu(ev.menu).hostWidget==self)){
					dispatchEvent(ev);
					if(subMenu!=null){
						subMenu.dispatchEvent(new MenuEvent(MenuEvent.MENU_SHOW,ev.bubbles,ev.cancelable,ev.menuBar,ev.menu,subMenu,null,label,ev.index));
					}
				}
			};
			this.parentMenu.addEventListener( MenuEvent.ITEM_CLICK,listener);
			this.parentMenu.addEventListener( MenuEvent.MENU_SHOW,listener);
		}
		
		override public function addEventListener(type:String, listener:Function, useCapture:Boolean=false, priority:int=0, useWeakReference:Boolean=false):void
		{
			super.addEventListener(type, listener, useCapture, priority, useWeakReference);
		}
		
		
		public function destroy():void{
			parentMenu.children.removeItemAt(parentMenu.children.getItemIndex(this));
			parentMenu.invalidateDisplayList();
		}
		public function get label():String{
			return _label;
		}
		public function set label(s:String):void{
			this._label=s;
			if(s!=null){
				for(var i:Number=0; i < s.length;i++){
					if(s.charAt(i)=='&'){
						keyEquivalent=s.charAt(i+1);
						this._label=s.replace("&", "");
						break;
					}
				}
			}
		}
		public function get children():ListCollectionView
		{
			return subMenu==null?null:subMenu.children;
			
		}
		public function get id():String
		{
			return _id;
		}
		
		public function set id(s:String):void
		{
			_id=s;
			
		}
		
	}
}