package org.opaeum.rwt.adapters
{
	import mx.core.IVisualElement;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.items.RwtTabItem;
	import org.opaeum.rwt.widgets.IRwtControl;
	import org.opaeum.rwt.widgets.RwtTabFolder;
	
	import rwt.protocol.ObjectEntry;
	import rwt.protocol.ObjectRegistry;
	
	public class TabItemAdapter extends WidgetAdapter
	{
		public function TabItemAdapter()
		{
			super();
			registerProperty("control");
			registerProperty("text","text");
		}
		
		override protected function setObjectProperty(target:Object, swtName:String, value:*):void
		{
			var item:RwtTabItem=RwtTabItem(target);
			switch(swtName){
				case "control":
					var entry:ObjectEntry=ObjectRegistry.getEntry(value);
					if(entry== null){
						ObjectRegistry.addRegistrationCallback(value,function(c:Object):void{
								item.control=IVisualElement( c);
						});
					}else{
						var control:IVisualElement=IVisualElement(entry.object);
						item.control=control;
					}
					break;
					
			}
		}
		
		
		override public function create(parent:Object, args:*):Object
		{
			var tf:RwtTabFolder=RwtTabFolder(parent);
			var result:RwtTabItem=new RwtTabItem(tf, args.style);
			return result;
		}
		
		
	}
}