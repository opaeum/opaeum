

package rwt.protocol {
	import flash.utils.Dictionary;
	
	import mx.core.IVisualElement;
	import mx.core.UIComponent;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.adapters.ButtonAdapter;
	import org.opaeum.rwt.adapters.ComboAdapter;
	import org.opaeum.rwt.adapters.CompositeAdapter;
	import org.opaeum.rwt.adapters.DateTimeAdapter;
	import org.opaeum.rwt.adapters.DisplayAdapter;
	import org.opaeum.rwt.adapters.GridAdapter;
	import org.opaeum.rwt.adapters.GridColumnAdapter;
	import org.opaeum.rwt.adapters.GridItemAdapter;
	import org.opaeum.rwt.adapters.LabelAdapter;
	import org.opaeum.rwt.adapters.MenuAdapter;
	import org.opaeum.rwt.adapters.MenuItemAdapter;
	import org.opaeum.rwt.adapters.ShellAdapter;
	import org.opaeum.rwt.adapters.TabFolderAdapter;
	import org.opaeum.rwt.adapters.TabItemAdapter;
	import org.opaeum.rwt.adapters.TextAdapter;
	import org.opaeum.rwt.adapters.ToolbarAdapter;
	
	public class ObjectRegistry  {
		
		private static var _map :Dictionary=new Dictionary();
		private static var _idMap:Dictionary= new Dictionary();
		private static  var _callbacks:Dictionary=new Dictionary();
		private static var _adapters:Dictionary;
		public static function findControl(from:IVisualElement):IVisualElement{
			while(from!=null && getId(from)==null){
				from=from.parent is IVisualElement?IVisualElement( from.parent):null;
			}
			return from;
		}
		public static function add( id:String, object:Object, adapter:WidgetAdapter ) :ObjectEntry{
			
			var result:ObjectEntry  = new ObjectEntry(id,object,adapter);
			_map[ id ] =result;
			_idMap[object]=id;
			if( _callbacks[ id ]!=undefined ) {
				var callbackArray:Array=_callbacks[id];
				for( var i:Number = 0; i < callbackArray.length; i++ ) {
					callbackArray[ i ]( object );
				}
				delete _callbacks[ id ];
			}
			return result;
		}
		
		public static function remove( id:String ):void {
			if( id != null ) {
				var object:Object=_map[id];
				delete _map[ id ];
				delete _idMap[object];
			}
		}
		
		public static function getId( object:Object ) :String{
			return _idMap[object];
		}
		
		public static function getObject( id:String ) :Object{
			return _map[ id ] ? _map[ id ].object : undefined;
		}
		
		public static function getEntry( id:String ) :ObjectEntry{
			return _map[ id ];
		}
		
		public static function addRegistrationCallback( id:String, fun:Function ) :void{
			if( _callbacks[ id ] ==undefined) {
				_callbacks[ id ] = new Array();
			}
			_callbacks[ id ].push( fun );
		}
		
		public static function resolveAdapter(type:String):WidgetAdapter
		{
			if(_adapters==null){
				_adapters=new Dictionary();
				_adapters["rwt.Display"]= new DisplayAdapter();
				_adapters["rwt.widgets.Button"]=new ButtonAdapter();
				_adapters["rwt.widgets.Composite"]=new CompositeAdapter();
				_adapters["rwt.widgets.Menu"]=new MenuAdapter();
				_adapters["rwt.widgets.Shell"]=new ShellAdapter();
//				_adapters["rwt.widgets.Toolbar"]=new ToolbarAdapter();
				_adapters["rwt.widgets.MenuItem"]=new MenuItemAdapter();
				_adapters["rwt.widgets.Grid"]=new GridAdapter();
				_adapters["rwt.widgets.GridItem"]=new GridItemAdapter();
				_adapters["rwt.widgets.GridColumn"]=new GridColumnAdapter();
				_adapters["rwt.widgets.Text"]=new TextAdapter();
				_adapters["rwt.widgets.Label"]=new LabelAdapter();
				_adapters["rwt.widgets.DateTime"]=new DateTimeAdapter();
				_adapters["rwt.widgets.Combo"]=new ComboAdapter();
				_adapters["rwt.widgets.TabItem"]=new TabItemAdapter();
				_adapters["rwt.widgets.TabFolder"]=new TabFolderAdapter();

			}
			var result:WidgetAdapter =  _adapters[type];
//			if(result==null){
//				result=_adapters["rwt.widgets.Composite"];
//			}
			return result;
		}
	}
}
