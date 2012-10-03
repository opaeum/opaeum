package org.opaeum.rwt.widgets
{
	import mx.controls.DateChooser;
	import mx.controls.DateField;
	import mx.events.DateChooserEvent;
	import mx.events.FlexEvent;
	
	import spark.components.HGroup;
	
	public class RwtDateTime extends HGroup implements IRwtControl
	{
		public var date:DateField;
		public var calendar:DateChooser;
//		public var time:MAs
		public function RwtDateTime(style:Array)
		{
			super();
			var date:Boolean=style.indexOf("DATE")>-1;
			var time:Boolean=style.indexOf("TIME")>-1;
			var dropdown:Boolean=style.indexOf("DROP_DOWN")>-1;
			var calendar:Boolean=style.indexOf("CALENDAR")>-1;
			if(date){
				if(dropdown){
					addElement(this.date=new DateField());
				}else if(calendar){
					addElement(this.calendar=new DateChooser());
				}
			}
			this.date.addEventListener(FlexEvent.VALUE_COMMIT,function (ev:FlexEvent):void{
				dispatchEvent(new DateChooserEvent(DateChooserEvent.SCROLL,false,""));
			});
		}
		public function get year():Number{
			return date.selectedDate.fullYear;
		}
		public function get month():Number{
			return date.selectedDate.month;
		}
		public function get day():Number{
			return date.selectedDate.getDate();
		}
	}
}