
package org.eclipse.swt
{
	import flash.text.StyleSheet;
	import flash.text.TextField;
	import flash.text.TextFormat;
	
	import mx.controls.Label;
	import mx.core.UIComponent;
	import mx.core.UITextField;
	import mx.styles.StyleManager;
	
	import rwt.protocol.ObjectRegistry;
	import rwt.remote.Server;
	
	public class FontSizeCalculation {
		
		
		public static function probe(ui:UIComponent, probeList:Array):void {
			var asdf:Number=probeList.length;
			for( var i:Number = 0; i < probeList.length; i++ ) {
				var item:* = probeList[ i ];
				var size:Array = _measureItem( item);
				var param :String= size[ 0 ] + "," + size[ 1 ];
				var id:String = item[ 0 ];
				_addRequestParam( id, param );
				_storeMeasurement( ObjectRegistry.getId(ui), id, size );
			}
		}
		
		public static function measureStringItems(ui:UIComponent, items:* ):void {
			var targetId:String =ObjectRegistry.getId(ui);
			for( var i:Number = 0; i < items.length; i++ ) {
				var item:Array = items[ i ];
				var isMarkup:Boolean = item[ 7 ];
				var size:Array = _measureItem( item);
				var param:String = size[ 0 ] + "," + size[ 1 ];
				var id:String = item[ 0 ];
				_addRequestParam( id, param );
				_storeMeasurement( targetId, id, size );
				//rwt.remote.Server.getInstance().send();
			}
		}
		
		public static function _measureItem(item:*):Array {
			var ui:TextField=new UITextField();
			var target:TextFormat=new TextFormat();
			target.font=item[2];
			target.size=item[3]+"px";
			target.bold=item[4];
			target.italic=item[5];
			ui.setTextFormat(target);
			ui.text=item[1];
			return [ui.textWidth,ui.textHeight];
		}
		
		
		public static function _addRequestParam ( name:String, value:String ):void {
			var request:Server = Server.getInstance();
			request.addParameter( name, value );
		}
		
		public static function _storeMeasurement( targetId:String, probeId:String, size:Array ):void {
			Server.getInstance().call(targetId, "storeMeasurement", {
				"id" : probeId,
				"size" : size
			} );
		}
		
		
		
	}
}
