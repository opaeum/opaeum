package rwt.remote{
	import com.adobe.serialization.json.JSON;
	
	import flash.events.ErrorEvent;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.events.TimerEvent;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.URLStream;
	import flash.utils.Dictionary;
	import flash.utils.Timer;
	
	import mx.core.UIComponent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	import org.opaeum.rap.EventUtil;
	
	import qx.core.Target;
	
	import rwt.protocol.MessageProcessor;
	import rwt.protocol.MessageWriter;
	import rwt.runtime.ErrorHandler;
	import rwt.util.Object;
	
	public class Server extends EventDispatcher{
		private static var instance:Server; 
		private var _retryHandler:Object;
		private var _url:String;
		public var _parameters:Dictionary=new Dictionary();
		private var _uiRootId:String;
		private var _requestCounter:Number;
		private var _sendTimer:Timer;
		private var _waitHintTimer:Timer;
		private var _writer:MessageWriter;
		private var _event:Object;
		private var messageProcessor:MessageProcessor;
		public static function initialize(mp:MessageProcessor):void{
			instance=new Server(mp);
		}
		public function Server(mp:MessageProcessor) {
			this._url = "";
			this.messageProcessor=mp;
			this._parameters = new Dictionary();
			this._writer = null;
			this._event = null;
			this._uiRootId = "";
			this._requestCounter = 0;//TODO should be -1 but somehow it never gets set??
			this._sendTimer = new Timer( 60 );
			this._sendTimer.addEventListener(TimerEvent.TIMER, function() :void{
				sendImmediate( true );
			});
			this._waitHintTimer = new Timer( 500 );
			this._waitHintTimer.addEventListener( TimerEvent.TIMER, this._showWaitHint);
			this._retryHandler = null;
		}
		public static function getInstance():Server{
			return instance;
		}
		public function destruct():void {
			this._retryHandler = null;
			this._sendTimer = null;
			this._waitHintTimer = null;
		}
		public function setUrl( url :String) :void{
			this._url = url;
		}
		
		public function getUrl() :String{
			return this._url;
		}
		
		public function setUIRootId( uiRootId:String ) :void{
			this._uiRootId = uiRootId;
		}
		
		public function getUIRootId() :String{
			return this._uiRootId;
		}
		
		public function setRequestCounter( requestCounter:Number ) :void{
			this._requestCounter = requestCounter;
		}
		
		public function getRequestCounter() :Number{
			return this._requestCounter;
		}
		
		public function addParameter( name:String, value :*):void {
			this._parameters[ name ] = value;
			var nameArr:Array = typeof name === "string" ? name.split( "." ) : null;
			if( nameArr ) {
				if( this._event && name.indexOf( this._event[ 1 ] ) === 0 ) {
					this._event[ 2 ][ nameArr.pop() ] = value;
				} else {
					this._flushEvent();
					var id :String= nameArr.shift();
					var property :String= nameArr.join( "." );// there are cases of event-types with "."
					this.getMessageWriter().appendSet( id, property, value );
				}
			} else {
				// Currently results of TSD are not compatible with protocol
				//console.trace();
			}
		}
		
		public function removeParameter( name :String):void {
			delete this._parameters[ name ];
		}
		
		public function getParameter( name:String ):String {
			return this._parameters[ name ];
		}
		
		public function addEvent( eventType:String, sourceId:String ) :void{
			this._parameters[ eventType ] = sourceId;
			this._flushEvent();
			this._event = [ sourceId, eventType, {} ];
		}
		
		public function _flushEvent():void {
			if( this._event ) {
				var writer:MessageWriter = this.getMessageWriter();
				var s:String=this._event[ 1 ];
				this._event[ 1 ] = s.split( "." ).pop();
				writer.appendNotify.apply( writer, this._event );
				this._event = null;
			}
		}
		
		public function send():void {
			this._sendTimer.start();
		}
		
		public function sendImmediate( async:Boolean ) :void{
			this._sendTimer.stop();
//			if( this._requestCounter === -1 ) {
//				// NOTE: Delay sending the request until requestCounter is set
//				// TOOD [tb] : This would not work with synchronous requests - bug?
//				this.send();
//			} else {
				this.dispatchSimpleEvent( "send" ,"",false);
				this._parameters[ "uiRoot" ] = this._uiRootId;
				this.getMessageWriter().appendMeta( "uiRoot", this._uiRootId );
				if( this._requestCounter >=0) {
					this._parameters[ "requestCounter" ] = this._requestCounter;
					this.getMessageWriter().appendMeta( "requestCounter", this._requestCounter );
					this._requestCounter = -1;
				}
				var request:Request = this._createRequest();
				request.setAsynchronous( async );
				this._attachParameters( request);
				this._waitHintTimer.start();
				this._parameters = new Dictionary();
				request.send();
//			}
		}
		
		private function dispatchSimpleEvent(param0:String, param1:String, param2:Boolean):void
		{
			var e:Event=new Event(param0,param2);
			dispatchEvent(e);
			// TODO Auto Generated method stub
			
		}
		
		public function getMessageWriter() :MessageWriter{
			if( this._writer === null ) {
				this._writer = new MessageWriter();
			}
			return this._writer;
		}
		
		////////////
		// Internals
		
		public function _attachParameters( request:Request ) :void{
			var data:Array = [];
			for( var key:String in this._parameters ) {
				data.push(
					encodeURIComponent( key ) + "=" + encodeURIComponent( this._parameters[ key ] ));
//					data.push(key + "=" + this._parameters[ key ] );
			}
			this._flushEvent();
			var msg:String=this.getMessageWriter().createMessage();
			data.push( "message=" + encodeURIComponent( msg ) );
			this._writer.dispose();
			this._writer = null;
			request.setData(data.join( "&" ) );
		}
		
		public function _createRequest():Request {
			var result:Request = new Request(this._url,  this._url, "POST", "application/javascript" );
			result.setSuccessHandler( this._handleSuccess, this );
			result.setErrorHandler( this._handleError, this );
			return result;
		}
		
		////////////////////////
		// Handle request events
		
		public function _handleError( event:FaultEvent,request:Request ):void {
			this._handleConnectionError( event,request );
			this._hideWaitHint();
		}
		
		public function _handleSuccess( event:ResultEvent, request:Request ) :void{
			try {
				var messageObject:* = JSON.decode( String(event.result));
				EventUtil.setSuspended( true );
				messageProcessor.processMessage( messageObject );
				EventUtil.setSuspended( false );
				this.dispatchSimpleEvent( "received","",false );
			} catch( ex:Error ) {
				ErrorHandler.processJavaScriptErrorInResponse( event.result, ex, request );
			}
			this._hideWaitHint();
		}
		
		///////////////////////////////
		// Handling connection problems
		
		public function _handleConnectionError( event:FaultEvent,failedRequest:Request ):void {
			var msg:String="ConnectionError:";
			var o:Object=event.fault.rootCause;
			ErrorHandler.showErrorBox( msg+ event.message.body, false );
			this._retryHandler = function() :void{
				var request:Request = this._createRequest();
				request.setAsynchronous( failedRequest.getAsynchronous() );
				request.setData( failedRequest.getData() );
				request.send();
			};
		}
		
		public function _retry() :void{
			try {
				rwt.runtime.ErrorHandler.hideErrorBox();
				this._showWaitHint(null);
				this._retryHandler();
			} catch( ex :Error) {
				rwt.runtime.ErrorHandler.processJavaScriptError( ex );
			}
		}
		
		public function _showWaitHint(evt:Event):void {
			this._waitHintTimer.stop();
		}
		
		public function _hideWaitHint() :void{
			this._waitHintTimer.stop();
		}
		public function setProperty( id:String, key:String, value:String ) :void{
			getMessageWriter().appendSet( id, key, value );
		}
		
		public function notify( id:String,event:String, properties:* ):void {
			var actualProps:* = properties ? properties : {};
			getMessageWriter().appendNotify( id, event, actualProps );
			send();
		}
		
		public function call (id:String, method:String, properties:* ) :void{
			var actualProps:* = properties ? properties : {};
			getMessageWriter().appendCall( id, method, actualProps );
			send();
		}

	}
}
