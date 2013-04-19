package org.opaeum.rwt.adapters
{
	import flash.events.Event;
	
	import mx.controls.Alert;
	import mx.events.MenuEvent;
	
	import org.opaeum.rap.EventUtil;
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.items.RwtMenuItem;
	import org.opaeum.rwt.widgets.IRwtMenu;
	import org.opaeum.rwt.widgets.RwtMenu;
	
	import rwt.protocol.ObjectRegistry;
	
	public class MenuItemAdapter extends WidgetAdapter
	{
		public function MenuItemAdapter()
		{
			super();
			registerProperty("text","label");
			registerEventListenerType("selection", MenuEvent.ITEM_CLICK,EventUtil.widgetSelected);
		}
		
		override public function destroy(target:Object):void
		{
			RwtMenuItem(target).destroy();
		}
		
		
		override public function create(parent:Object, args:*):Object
		{
			var styles:Array=args["style"];
			var result:RwtMenuItem;
			result=new RwtMenuItem(IRwtMenu(parent));
			if(args.menu!=undefined){
				ObjectRegistry.addRegistrationCallback(args.menu,function (menu:RwtMenu):void{
					result.subMenu=menu;
					menu.init(result);
				});
			}
			return result;
		}
		
		
	}
}