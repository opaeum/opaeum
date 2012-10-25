package org.opaeum.rwt.widgets
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.geom.Point;
	import flash.geom.Rectangle;
	
	import mx.collections.ArrayList;
	import mx.collections.ICollectionView;
	import mx.collections.ListCollectionView;
	import mx.containers.ApplicationControlBar;
	import mx.controls.Menu;
	import mx.controls.MenuBar;
	import mx.controls.menuClasses.IMenuBarItemRenderer;
	import mx.controls.menuClasses.MenuBarItem;
	import mx.core.EventPriority;
	import mx.core.LayoutDirection;
	import mx.core.UIComponentGlobals;
	import mx.core.mx_internal;
	import mx.events.MenuEvent;
	import mx.managers.ISystemManager;
	import mx.managers.PopUpManager;
	
	import org.opaeum.rwt.items.RwtMenuItem;

	use namespace mx_internal;
	public class RwtMenuBar extends MenuBar implements IRwtMenu
	{
		private var dataProviderChanged:Boolean = false;
		private var isInsideACB:Boolean;

		public function RwtMenuBar()
		{
			super();
			dataProvider=new ListCollectionView(new ArrayList());

		}
		public function get children():ListCollectionView{
			return ListCollectionView(dataProvider);
		}
		override public function set dataProvider(value:Object):void{
			super.dataProvider=value;
			dataProviderChanged=true;
			//TODO listen to collection changes
		}

		override public function getMenuAt(index:int):Menu
		{
			if (dataProviderChanged)
				commitProperties();
			
			var item:IMenuBarItemRenderer = menuBarItems[index];
			
			var mdp:RwtMenuItem = RwtMenuItem(item.data);
			var menu:RwtMenu = RwtMenu(menus[index]);
			
			if (menu == null)
			{
				menu = mdp.subMenu==null? new RwtMenu():mdp.subMenu;
				menu.showRoot = false;
				
				var menuStyleName:Object = getStyle("menuStyleName");
				if (menuStyleName)
					menu.styleName = menuStyleName;
				
				menu.sourceMenuBar = this;
				menu.owner = this;
//				menu.addEventListener("menuHide", eventHandler);
//				menu.addEventListener("itemRollOver", eventHandler);
//				menu.addEventListener("itemRollOut", eventHandler);
//				menu.addEventListener("menuShow", eventHandler);
//				menu.addEventListener("itemClick", eventHandler);
//				menu.addEventListener("change", eventHandler);
				menu.iconField = iconField;
				menu.labelField = labelField;
				menu.labelFunction = labelFunction;
				menu.dataDescriptor = dataDescriptor;
				menu.invalidateSize();
				
				menus[index] = menu;
				menu.sourceMenuBarItem = item; // menu needs this for a hitTest when clicking outside menu area
				Menu.popUpMenu(menu, this, mdp.children);
			}
			
			return menu;
		}
		private function eventHandler(event:Event):void
		{
			//these events come from the menu's themselves. 
			//we'll redispatch all of them. 
			if (event is MenuEvent) 
			{
				var t:String = event.type;
				
				if (event.type == MenuEvent.MENU_HIDE && 
					MenuEvent(event).menu == menus[selectedIndex])
				{
					menuBarItems[selectedIndex].menuBarItemState = "itemUpSkin";
					selectedIndex = -1;
					dispatchEvent(event as MenuEvent);
				}
				else
					dispatchEvent(event);
			}
		}
		public function showSubMenu(menu:RwtMenu):void{
			var item:IMenuBarItemRenderer = menuBarItems[menus.indexOf(menu)];
			
			// The getMenuAt function will create the Menu if it doesn't
			// already exist.
			var sm:ISystemManager = systemManager.topLevelSystemManager;
			var screen:Rectangle = sm.getVisibleApplicationRect(null, true);
			
			// pop it up if we haven't already.  this allows us to validate the menu and get correct sizes
			if (menu.parentDisplayObject && (!menu.parent || !menu.parent.contains(menu.parentDisplayObject)))
			{
				PopUpManager.addPopUp(menu, this, false);
				menu.addEventListener(MenuEvent.MENU_HIDE, menuHideHandler, false, EventPriority.DEFAULT_HANDLER);
			}
			
			// pop it up if we haven't already.  this allows us to validate the menu and get correct sizes
			if (menu.parentDisplayObject && (!menu.parent || !menu.parent.contains(menu.parentDisplayObject)))
			{
				PopUpManager.addPopUp(menu, this, false);
				menu.addEventListener(MenuEvent.MENU_HIDE, menuHideHandler, false, EventPriority.DEFAULT_HANDLER);
			}
			
			UIComponentGlobals.layoutManager.validateClient(menu, true);
			
			// popups go on the root of the swf which if loaded, is not
			// necessarily at 0,0 in global coordinates
			var pt:Point = new Point(0, 0);
			pt = DisplayObject(item).localToGlobal(pt);
			
			// If the layout has been mirrored, then the 0,0 is the uppper
			// right corner; compensate here.
			if (layoutDirection == LayoutDirection.RTL)
				pt.x -= menu.getExplicitOrMeasuredWidth();
			
			// check to see if we'll go offscreen
			if (pt.y + item.height + 1 + menu.getExplicitOrMeasuredHeight() > screen.height + screen.y)
				pt.y -= menu.getExplicitOrMeasuredHeight();
			else
				pt.y += item.height + 1;
			if (pt.x + menu.getExplicitOrMeasuredWidth() > screen.width + screen.x)
				pt.x = screen.x + screen.width - menu.getExplicitOrMeasuredWidth();
			pt.x = Math.max(screen.x, pt.x);
			pt = sm.getSandboxRoot().globalToLocal(pt);
			
			// If inside an ACB, slight offset looks much better.
			if (isInsideACB)
				pt.y += 2;
			
			menu.show(pt.x, pt.y);
		}
		override protected function createChildren():void
		{
			super.createChildren();
			
			// Check if this MenuBar is inside an ACB.
			for (var p:Object = parent; p; p = p.parent)
			{
				if (p is ApplicationControlBar)
				{
					isInsideACB = true;
					break;
				}
			}
			
		}

		private static function menuHideHandler(event:MenuEvent):void
		{
			var menu:Menu = Menu(event.target);
			if (!event.isDefaultPrevented() && event.menu == menu)
			{
				menu.supposedToLoseFocus = true;
				PopUpManager.removePopUp(menu);
				menu.removeEventListener(MenuEvent.MENU_HIDE, menuHideHandler);
			}
		}

	}
}