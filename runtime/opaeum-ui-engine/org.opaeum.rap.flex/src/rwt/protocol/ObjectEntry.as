package rwt.protocol
{
	import mx.core.UIComponent;
	
	import org.opaeum.rap.WidgetAdapter;

	public class ObjectEntry
	{
		public var id:String;
		public var object:Object;
		public var adapter:WidgetAdapter;
		public function ObjectEntry(id:String,object:Object, adapter:WidgetAdapter)
		{
			this.id=id;
			this.object=object;
			this.adapter=adapter;
		}
	}
}