package org.opaeum.rwt.widgets
{
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	
	import mx.collections.ArrayList;
	import mx.collections.ICollectionView;
	import mx.collections.ListCollectionView;
	import mx.controls.Menu;
	import mx.controls.listClasses.IListItemRenderer;
	import mx.core.mx_internal;
	use namespace mx_internal;
	import org.opaeum.rwt.items.RwtMenuItem;
	import mx.controls.menuClasses.IMenuItemRenderer;
	import mx.managers.PopUpManager;
	import mx.controls.menuClasses.IMenuBarItemRenderer;
	import mx.events.MenuEvent;
	import flash.utils.describeType;
	
	public class RwtMenu extends Menu implements IRwtMenu
	{
		public var hostWidget:IRwtWidget;
		private var mouseDown:Number=0;
		public function RwtMenu()
		{
			super();
			labelField="label";
			dataProvider=new ListCollectionView(new ArrayList());
		}
		public function get children():ListCollectionView{
			return ListCollectionView(dataProvider);
		}
		
		override mx_internal function openSubMenu(row:IListItemRenderer):void{
			if (!IMenuItemRenderer(row).menu){
				var r:Menu=getRootMenu();
				var menu:RwtMenu=RwtMenuItem(row.data).subMenu;
				var listener:Function=function(ev:MenuEvent):void{
					if(ev.menu==menu){
						menu.dispatchEvent(ev);
					}
					
				};
				getRootMenu().addEventListener(MenuEvent.ITEM_CLICK,menu.dispatchEvent); 
				getRootMenu().addEventListener(MenuEvent.CHANGE,menu.dispatchEvent); 
				getRootMenu().addEventListener(MenuEvent.MENU_HIDE,menu.dispatchEvent); 
				getRootMenu().addEventListener(MenuEvent.MENU_SHOW,menu.dispatchEvent); 
				getRootMenu().addEventListener(MenuEvent.ITEM_ROLL_OUT,menu.dispatchEvent); 
				getRootMenu().addEventListener(MenuEvent.ITEM_ROLL_OVER,menu.dispatchEvent); 
				menu.parentMenu = this;
				menu.owner = this;
				menu.showRoot = showRoot;
				menu.dataDescriptor = r.dataDescriptor;
				menu.styleName = r;
				menu.labelField = r.labelField;
				menu.labelFunction = r.labelFunction;
				menu.iconField = r.iconField;
				menu.iconFunction = r.iconFunction;
				menu.itemRenderer = r.itemRenderer;
				menu.rowHeight = r.rowHeight;
				menu.scaleY = r.scaleY;
				menu.scaleX = r.scaleX;
				
				// if there's data and it has children then add the items
				if (row.data && 
					_dataDescriptor.isBranch(row.data) &&
					_dataDescriptor.hasChildren(row.data))
				{
					menu.dataProvider = _dataDescriptor.getChildren(row.data);
				}
				menu.sourceMenuBar = sourceMenuBar;
				menu.sourceMenuBarItem = sourceMenuBarItem;
				
				IMenuItemRenderer(row).menu = menu;
				PopUpManager.addPopUp(menu, r, false);
			}
			super.openSubMenu(row);
			
		}
		
		override public function show(xShow:Object=null, yShow:Object=null):void
		{
			super.show(xShow, yShow);
			if(!visible && children.length==0){
				//Send event for lazy loading
				var menuEvent:MenuEvent = new MenuEvent(MenuEvent.MENU_SHOW);
				menuEvent.menu = this;
				menuEvent.menuBar = sourceMenuBar;
				dispatchEvent(menuEvent);
			}
		}
		
		
		public function init(hostWidget:IRwtWidget):void{
			this.hostWidget=hostWidget;
			if(!(hostWidget is RwtMenuItem)){
				popUpMenu(this,null,this.dataProvider);
				hostWidget.addEventListener(MouseEvent.MOUSE_DOWN, function(ev:MouseEvent):void{
					var t:Timer=new Timer(400,1);
					mouseDown=new Date().time;
					t.addEventListener(TimerEvent.TIMER_COMPLETE, function(evt:TimerEvent):void{
						if(mouseDown>0 && new Date().time-mouseDown>=400){
							show(ev.stageX,ev.stageY);
						}
					});
					t.start();
				});
				hostWidget.addEventListener(MouseEvent.MOUSE_UP, function(ev:MouseEvent):void{
					mouseDown=0;
				});
			}
			
		}
		
		public function showSubMenu(sm:RwtMenu):void
		{
			for (var i:int = 0; i < listItems.length; i++)
			{
				if(listItems[i][0].menu==sm){
					openSubMenu(listItems[i][0]);
				}
			}
		}
		
	}
}