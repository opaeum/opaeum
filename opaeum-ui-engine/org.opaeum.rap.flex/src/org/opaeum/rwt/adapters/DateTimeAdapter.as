package org.opaeum.rwt.adapters
{
	import mx.events.DateChooserEvent;
	
	import org.opaeum.rap.EventUtil;
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.widgets.RwtDateTime;
	
	import rwt.remote.Server;
	
	public class DateTimeAdapter extends WidgetAdapter
	{
		public function DateTimeAdapter()
		{
			super();
			registerEventListenerType("selection",DateChooserEvent.SCROLL,function(ev:DateChooserEvent):void{
				var req:Server=Server.getInstance();
				var target:RwtDateTime=RwtDateTime(ev.target);
				req.addParameter(target.id +".year",target.year);
				req.addParameter(target.id +".month",target.month);
				req.addParameter(target.id +".day",target.day);
				EventUtil.widgetSelected(ev);
			});
		}
		
		override protected function newInstance(args:*):Object
		{
			var result:RwtDateTime=new RwtDateTime(args.style);
			return result;
		}
		
		override protected function setObjectProperty(target:Object, swtName:String, value:*):void
		{
			// TODO Auto Generated method stub
			super.setObjectProperty(target, swtName, value);
		}
		
		
	}
}