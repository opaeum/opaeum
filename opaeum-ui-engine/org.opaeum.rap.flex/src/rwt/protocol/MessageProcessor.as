
package rwt.protocol {
	import flash.display.DisplayObject;
	import flash.utils.describeType;
	import flash.utils.getDefinitionByName;
	
	import mx.controls.Alert;
	import mx.core.Container;
	import mx.core.IVisualElement;
	import mx.core.UIComponent;
	import mx.managers.PopUpManager;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.adapters.ButtonAdapter;
	import org.opaeum.rwt.widgets.IRwtWidget;
	
	import rwt.remote.Server;
	import rwt.util.String;
	
	import spark.components.Application;
	import spark.components.SkinnableContainer;
	
	public class MessageProcessor  {
		private var application:rap;
		public function MessageProcessor  (application:rap){
			this.application=application;
		}
		public function processMessage( messageObject:* ):void {
			this.processMeta( messageObject.meta );
			var operations:Array = messageObject.operations;
			for( var i:Number = 0; i < operations.length; i++ ) {
				this.processOperationArray( operations[ i ] );
			}
			for(var j:Number=0; j < application.mdiCanvas.windowManager.windowList.length ;j++){
//				printTree("", DisplayObject(application.mdiCanvas.windowManager.windowList[j]));
			}
		}
		public function printTree(padding:String, ve:DisplayObject):void{
			var classInfo:XML=describeType(ve);
			trace(padding +  classInfo.@name  +  "[" + ve.name + "]:x="+ ve.x + ";y=" +ve.y +";width=" + ve.width + ";height="+ve.height); 
			if(ve is UIComponent){
				var c:UIComponent=UIComponent(ve);
				for(var i:Number=0; i < c.numChildren;i++){
					printTree(padding + " " , c.getChildAt(i));
				}
			} 
		}
		
		public function processMeta( meta:* ):void {
			if( meta.requestCounter !== undefined ) {
				Server.getInstance().setRequestCounter( meta.requestCounter );
			}
			if( meta.redirect !== undefined ) {
				RapApplication.document.location = meta.redirect;
			}
		}
		
		public function processOperationArray( operation:Array ):void {
			var action :String = operation[ 0 ];
			try {
				switch( action ) {
					case "create":
						this._processCreate( operation[ 1 ], operation[ 2 ], operation[ 3 ] );
						break;
					case "set":
						this._processSet( operation[ 1 ], operation[ 2 ] );
						break;
					case "listen":
						this._processListen( operation[ 1 ], operation[ 2 ] );
						break;
					case "call":
						this._processCall( operation[ 1 ], operation[ 2 ], operation[ 3 ] );
						break;
					case "destroy":
						this._processDestroy( operation[ 1 ] );
						break;
				}
			} catch( ex:Error ) {
				this._processError( ex, operation );
			}
		}
		
		
		////////////
		// Internals
		
		public function _processCreate( targetId:String, type:String, properties:* ) :void{
//			if(targetId=="w7" || (properties!=null && properties.bounds!=null && properties.bounds[2]==0)){
//				return;
//			}
			var adapter:WidgetAdapter = ObjectRegistry.resolveAdapter(type);
			var targetObject:Object=null;
			if(adapter!=null){
				if(type=="rwt.Display"){
					targetObject=application.mdiCanvas;
					Server.getInstance().setUIRootId(targetId);
				}else{
					if(properties["parent"] != undefined){
						targetObject=adapter.create(ObjectRegistry.getObject(properties["parent"]),properties);
					}else{
						targetObject=adapter.create(null,properties);
					}
				}
				var oe:ObjectEntry= this._addTarget( targetObject, targetId ,adapter);
				if(targetObject is DisplayObject){
					DisplayObject(targetObject).name=targetId;
				}
				if(targetObject is UIComponent){
					UIComponent(targetObject).id=targetId;
				}
				if(targetObject is IRwtWidget){
					IRwtWidget(targetObject).id=targetId;
				}
				if(properties!=undefined){
					this._processSetImpl( oe, properties );
				}
			}
		}
		
		public function _processDestroy( targetId :String):void {
			var objectEntry:ObjectEntry = ObjectRegistry.getEntry( targetId );
			if(objectEntry!=null){
				var targetObject:Object = objectEntry.object;
				objectEntry.adapter.destroy(targetObject);
				ObjectRegistry.remove( targetId );
			}
		}
		
		public function _processSet( targetId:String, properties:* ):void {
			var objectEntry:ObjectEntry = ObjectRegistry.getEntry( targetId );
			if(objectEntry!=null){
				this._processSetImpl( objectEntry, properties );
			}
		}
		
		public function _processSetImpl( objectEntry:ObjectEntry, properties :*):void {
			if( properties ) {
				objectEntry.adapter.setProperties(objectEntry.object,properties);
			}
		}
		
		public function _processCall( targetId:String, method:String, properties:* ) :void{
			var objectEntry:ObjectEntry = ObjectRegistry.getEntry( targetId );
			var adapter:WidgetAdapter = objectEntry.adapter;
			var targetObject:Object = objectEntry.object;
			adapter.call(targetObject,method,properties);
		}
		
		public function _processListen( targetId:String, properties:* ) :void{
			var objectEntry:ObjectEntry = ObjectRegistry.getEntry( targetId );
			if(objectEntry!=null){
					var adapter:WidgetAdapter = objectEntry.adapter;
				var targetObject:Object = objectEntry.object;
				adapter.processListeners(targetObject,properties);
			}
		}
		
		////////////
		// Internals
		
		public function _processError( error:Error, operation :Array):void {
			trace(error.message);
			trace(error.getStackTrace());
			var errorstr:String;
			if( error ) {
				errorstr = error.message ? error.message : error.toString();
			} else {
				errorstr = "No Error given!";
			}
			var msg:String = "Operation \"" + operation[ 0 ] + "\"";
			msg += " on target \"" +  operation[ 1 ] + "\"";
			var objectEntry:ObjectEntry = ObjectRegistry.getEntry( operation[ 1 ] );
			var target:Object = objectEntry ? objectEntry.object : null;
			msg += " of type \"" +  typeof target + "\"";
			msg += " failed:";
			msg += "\n" + errorstr +"\n";
			msg += "Properties: \n" + this._getPropertiesString( operation );
			throw new Error( msg );
		}
		
		public function _getPropertiesString( operation:Array ):String{
			var result:String = "";
			var properties:Object;
			switch( operation[ 0 ] ) {
				case "set":
				case "listen":
					properties = operation[ 2 ];
					break;
				case "create":
				case "call":
					properties = operation[ 3 ];
					break;
				default:
					properties = [];
					break;
			}
			if(properties is Array){
				for( var key:String in properties ) {
					result += key + " = " + properties[ key ] + "\n";
				}
			}else{
				Alert.show(typeof properties);
			}
			return result;
		}
		
		public function _addTarget( target:Object, targetId:String, adapter:WidgetAdapter ):ObjectEntry {
			return ObjectRegistry.add( targetId, target, adapter );
		}
		
	}
}
