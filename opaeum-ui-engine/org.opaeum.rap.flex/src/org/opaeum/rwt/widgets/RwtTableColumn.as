package org.opaeum.rwt.widgets
{
	import mx.core.ClassFactory;
	
	import spark.components.gridClasses.GridColumn;
	
	public class RwtTableColumn extends GridColumn implements IRwtWidget
	{
		private var _id:String;
		public function RwtTableColumn(columnName:String=null)
		{
			super(columnName);
			itemRenderer=new ClassFactory(RwtTableItemRenderer);

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